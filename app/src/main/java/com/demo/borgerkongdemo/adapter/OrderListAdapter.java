package com.demo.borgerkongdemo.adapter;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.demo.borgerkongdemo.R;
import com.demo.borgerkongdemo.database.Order;
import com.demo.borgerkongdemo.util.LogUtils;

import java.util.List;




public class OrderListAdapter extends BaseAdapter {

        private List<Order> list;
        private ListView listview;
        private LruCache<String, BitmapDrawable> mImageCache;

        public OrderListAdapter(List<Order> list) {
            super();
            this.list = list;


        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (listview == null) {
                listview = (ListView) parent;
            }
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.detail_list_item, null);
                holder = new ViewHolder();
                holder.iv = (ImageView) convertView.findViewById(R.id.item_iv_cover);
                holder.item_tv_id = (TextView) convertView.findViewById(R.id.item_tv_id);
                holder.item_tv_bid = (TextView) convertView.findViewById(R.id.item_tv_bid);
                holder.item_tv_title = (TextView) convertView.findViewById(R.id.item_tv_title);
                holder.item_tv_type = (TextView) convertView.findViewById(R.id.item_tv_type);
                holder.item_tv_desc = (TextView) convertView.findViewById(R.id.item_tv_desc);
                holder.item_tv_price = (TextView) convertView.findViewById(R.id.item_tv_price);
                holder.item_tv_date = (TextView) convertView.findViewById(R.id.item_tv_id);
                holder.item_tv_num = (TextView) convertView.findViewById(R.id.item_tv_num);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final Order meal = list.get(position);
            holder.item_tv_id.setText(meal.getId()+"");
            holder.item_tv_bid.setText(meal.getBid());
            holder.item_tv_title.setText(meal.getTitle());
            holder.item_tv_price .setText(meal.getContent());
            holder.item_tv_num.setText(meal.getTime());
            Log.v(LogUtils.filename(new Exception()), LogUtils.funAndLine(new Exception())+meal.getTitle());
            final int[] ImageArray;ImageArray = new int[]{R.drawable.food_1,R.drawable.food_2,R.drawable.food_3,
                    R.drawable.food_4,R.drawable.food_5,R.drawable.food_6,R.drawable.food_7,R.drawable.food_8,
                    R.drawable.food_9,R.drawable.food_10,R.drawable.food_11,R.drawable.food_12,R.drawable.food_13,
                    R.drawable.food_14,R.drawable.food_15};
            holder.iv.setImageResource(ImageArray[Integer.valueOf(meal.getBid())-1]);
            return convertView;
        }

    //从List移除对象
    public void removeItem(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }
    class ViewHolder {
            ImageView iv;
            TextView item_tv_id,item_tv_bid,item_tv_title,item_tv_type,item_tv_desc,item_tv_cover, item_tv_num,item_tv_price,item_tv_date;
        }


}
