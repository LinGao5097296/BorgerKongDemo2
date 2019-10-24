package com.demo.borgerkongdemo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.demo.borgerkongdemo.EmailDetailsActivity;
import com.demo.borgerkongdemo.R;
import com.demo.borgerkongdemo.adapter.MealListAdapter;
import com.demo.borgerkongdemo.database.Food;
import com.demo.borgerkongdemo.database.FoodProvider;
import com.demo.borgerkongdemo.util.LogUtils;

import java.util.ArrayList;
import java.util.List;



/**
 * 套餐列表
 */
public class MealFragment extends Fragment implements  AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{
    private static final int LOAD_FOOD_LIST=101;
    private List<Food> foodList =new ArrayList<>();

    private SwipeRefreshLayout swipe;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_meal,container,false);
        listView=view.findViewById(R.id.list_meal);

        swipe=view.findViewById(R.id.swipe);

        initView();


        return view;
    }


    /**
     * 初始化view
     */
    private void initView() {
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        //change color
        swipe.setColorSchemeColors(getResources().getColor(R.color.text_red), getResources().getColor(R.color.text_red));
        //set value of distance
        swipe.setDistanceToTriggerSync(200);
        //set location
        swipe.setProgressViewEndTarget(false, 200);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(false);

                load();
            }
        });

        load();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView tv_note_title = (TextView) view.findViewById(R.id.item_tv_title);
        String item_title = tv_note_title.getText().toString();
        TextView tv_note_author = (TextView) view.findViewById(R.id.item_tv_price);
        String item_price = tv_note_author.getText().toString();
        TextView tv_note_body = (TextView) view.findViewById(R.id.item_tv_body);
        String item_body = tv_note_body.getText().toString();
        TextView tv_note_id = (TextView) view.findViewById(R.id.item_tv_id);
        String item_id = tv_note_id.getText().toString();
        // saveItemData(item_id,item_name,libcontent,item_price);

        Food details=new Food(item_price,item_title,item_body,item_id);
        Intent intent=new Intent(getActivity(), EmailDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("food",  details);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        return true;
    }

    // load list data
    public void load() {
        foodList.clear();
        getdata();
        Log.v(LogUtils.filename(new Exception()), LogUtils.funAndLine(new Exception()));
        Message m = listHandler.obtainMessage();//
        m.what = LOAD_FOOD_LIST;
        listHandler.sendMessage(m);//
    }
    private Handler listHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LOAD_FOOD_LIST://load
                    Log.v(LogUtils.filename(new Exception()), LogUtils.funAndLine(new Exception())+ foodList.size());
                    MealListAdapter adapter = new MealListAdapter(foodList);
                    listView.setAdapter(adapter);
                    break;
            }
        }
    };

    //
    public void getdata() {
        foodList = FoodProvider.getEmails(getContext());
    }

}
