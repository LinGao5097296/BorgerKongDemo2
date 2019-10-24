package com.demo.borgerkongdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.borgerkongdemo.database.Order;
import com.demo.borgerkongdemo.database.OrderDBManager;
import com.demo.borgerkongdemo.database.Food;
import com.demo.borgerkongdemo.util.LogUtils;
import com.demo.borgerkongdemo.util.SharedPreferencesUtils;
import com.demo.borgerkongdemo.widget.AmountView;


/**
 * review mail details
 */
public class EmailDetailsActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView et_new_title;
    private TextView et_new_id;
    private TextView tv_new_price;
    private TextView tv_new_desc;
    private AmountView mAmountView;
    private int select_num=1;
    private ImageView imageView;
    private Button btn_add;
    private OrderDBManager orderDbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏 ActionBasr放到setContentView之前
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_emaildetails);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mAmountView = (AmountView) findViewById(R.id.amount_view);
        //初始化Toolbar
        toolbar.setTitle("Single Email");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回消息更新上个Activity数据
                setResult(RESULT_OK, new Intent());
                finish();
            }
        });
        initEventAndData();
    }

    protected void initEventAndData() {
        orderDbManager = new OrderDBManager(this);

        //findViewById(R.id.tvCancel).setOnClickListener(this);
        imageView= (ImageView) findViewById(R.id.item_iv_cover);
       // et_new_id = (TextView) findViewById(R.id.et_new_id);
        et_new_title = (TextView) findViewById(R.id.et_new_title);
        et_new_id = (TextView) findViewById(R.id.et_new_id);
        tv_new_price = (TextView) findViewById(R.id.tv_new_price);
        tv_new_desc = (TextView) findViewById(R.id.et_new_content);
        btn_add=(Button) findViewById(R.id.btn_add);

        btn_add.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        Food food = (Food) bundle.getSerializable("food");
        et_new_title.setText(food.getTitle());
        et_new_id.setText(food.getId());
        tv_new_desc.setText(food.getDesc());
        tv_new_price.setText(food.getPrice());
        final int[] ImageArray;ImageArray = new int[]{R.drawable.food_1,R.drawable.food_2,R.drawable.food_3,
                R.drawable.food_4,R.drawable.food_5,R.drawable.food_6,R.drawable.food_7,R.drawable.food_8,
                R.drawable.food_9,R.drawable.food_10,R.drawable.food_11,R.drawable.food_12,R.drawable.food_13,
                R.drawable.food_14,R.drawable.food_15};
        imageView.setImageResource(ImageArray[Integer.valueOf(food.getId())-1]);
       // imageView.setImageResource(ImageArray[Integer.valueOf(food.getBid())%10]);

        mAmountView.setGoods_storage(50);
        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                // Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
                select_num=amount;
            }
        });
    }

    //
    private Food getMealFromUI() {
        String food_id= et_new_id.getText().toString();
        String food_name = et_new_title.getText().toString();
        String food_price = tv_new_price.getText().toString();
        Log.e(LogUtils.filename(new Exception()), LogUtils.funAndLine(new Exception())+Float.parseFloat(food_price)+"|"+select_num);
        String totalprice=Float.parseFloat(food_price)*select_num+"";
        String food_desc = tv_new_desc.getText().toString();
        Food s = new  Food(food_price,food_name,food_desc,food_id);
        return s;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                Food food=getMealFromUI();
                if (getLibID(food.getId())!=null && getLibID(food.getId()).equals(food.getId())) {
                    Order order=orderDbManager.getOrderByMid(food.getId());
                    String num=order.getTime();
                    int order_id=((Order) order).getId();
                    Log.e(LogUtils.filename(new Exception()), LogUtils.funAndLine(new Exception())+num+"|"+order_id);

                    orderDbManager.updateOrder(order_id,food.getId(), food.getTitle(), food.getPrice(), select_num +Integer.valueOf(num)+ "");
                }else{
                    orderDbManager.addToDB(food.getId(), food.getTitle(), food.getPrice(), select_num + "");

                }
                Toast.makeText(this, "Add to Order successfully!", Toast.LENGTH_SHORT).show();
                saveLibID(food.getId());
                finish();
                break;
        }
    }
        /**
         * get ID
         */
        public String getLibID(String mid) {
            SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "MID");
            return helper.getString(mid);

        }
    /**
     * saveID
     */
    public void saveLibID(String mid) {
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "MID");
        helper.putValues(new SharedPreferencesUtils.ContentValue(mid, mid));

    }
}
