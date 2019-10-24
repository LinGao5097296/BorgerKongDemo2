package com.demo.borgerkongdemo.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.demo.borgerkongdemo.R;
import com.demo.borgerkongdemo.adapter.OrderListAdapter;
import com.demo.borgerkongdemo.database.OrderDBManager;
import com.demo.borgerkongdemo.database.Order;
import com.demo.borgerkongdemo.util.LogUtils;
import com.demo.borgerkongdemo.util.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * order list
 */
public class OrderFragment extends Fragment implements  AdapterView.OnItemLongClickListener{

    private Handler handler;
    private  SwipeRefreshLayout swipe;
    private ListView listView;
    private TextView tvNum;
    private OrderDBManager dm;
    private List<Order> orderDataList = new ArrayList<>();
    private OrderListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_keep,container,false);
        tvNum=(TextView)view.findViewById(R.id.tv_num) ;
        listView=(ListView) view.findViewById(R.id.list_meal);
        swipe=(SwipeRefreshLayout) view.findViewById(R.id.swipe);

        initView();
        return view;
    }


    /**
     * init view
     */
    private void initView() {

        listView.setOnItemClickListener(new NoteClickListener());
        listView.setOnItemLongClickListener(this);


        swipe.setColorSchemeColors(getResources().getColor(R.color.text_red), getResources().getColor(R.color.text_red));

        swipe.setDistanceToTriggerSync(200);

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


    private class NoteClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

            final Order order = (Order) ((OrderListAdapter) adapterView.getAdapter()).getItem(i);
            if (order == null) {

            }
            final int id = order.getId();
            final  String bid= order.getBid();
            new android.app.AlertDialog.Builder(getActivity())
                    .setMessage("do you want to pay？")
                    .setNegativeButton("no",null)
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            OrderDBManager.getInstance(getActivity()).deleteNote(id);
                            adapter.removeItem(i);
                             Toast.makeText(getActivity(), "Pay Order successfully,Remove from my order!", Toast.LENGTH_SHORT).show();
                            delLibID(bid);
                           load();
                        }
                    }).show();
        }
    }
    /**
     * del ID
     */
    public void delLibID(String mid) {
        SharedPreferencesUtils helper = new SharedPreferencesUtils(getContext(), "MID");
        helper.putValues(new SharedPreferencesUtils.ContentValue(mid, ""));

    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        return false;
    }
    // load data
    public void load() {
        orderDataList.clear();
        Log.v(LogUtils.filename(new Exception()), LogUtils.funAndLine(new Exception()));
        dm = new OrderDBManager(getContext());
        dm.readFromDB(orderDataList);

        adapter = new OrderListAdapter(orderDataList);
        listView.setAdapter(adapter);
        double total=0.00;
        for (int i = 0; i< orderDataList.size(); i++)
        {
            total=total+  Float.parseFloat(orderDataList.get(i).getContent())*Float.parseFloat(orderDataList.get(i).getTime());
        }
        tvNum.setText(total+"");

    }
}
