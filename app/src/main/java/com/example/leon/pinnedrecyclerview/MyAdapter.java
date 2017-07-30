package com.example.leon.pinnedrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leon.pinnedrecyclerview.models.FormatData;
import com.example.leon.pinnedrecyclerview.models.ItemData;
import com.example.leon.pinnedrecyclerview.models.MyData;

import java.util.ArrayList;

/**
 * Created by leon on 2017/7/29.
 */

public class MyAdapter extends RecyclerView.Adapter {

    public static final int FIRST_STICKY_VIEW = 1;
    public static final int HAS_STICKY_VIEW = 2;
    public static final int NONE_STICKY_VIEW = 3;

    private int HEADER_ITEM = 0;
    private int CONTAINER_ITEM = 1;

    private Context context;
    private ArrayList<MyData> myDatas = new ArrayList<>();
    private ArrayList<FormatData> formatDatas = new ArrayList<>();

    public MyAdapter(Context context, ArrayList<MyData> myDatas) {
        this.context = context;
        this.myDatas = myDatas;
        formatData(myDatas);
    }

    private void formatData(ArrayList<MyData> myDatas) {
        for (MyData myData : myDatas) {
            FormatData headerData = new FormatData();
            headerData.category = myData.category;
            formatDatas.add(headerData);
            for (ItemData itemData : myData.listData) {
                FormatData format = new FormatData();
                format.category = myData.category;
                format.title = itemData.title;
                format.subTitle = itemData.subTitle;
                formatDatas.add(format);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.view_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.view_container, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            headerHolder.header_view.setText(formatDatas.get(position).category);
            headerHolder.itemView.setContentDescription(formatDatas.get(position).category);
            if (position == 0) {
                headerHolder.itemView.setVisibility(View.VISIBLE);
                headerHolder.itemView.setTag(FIRST_STICKY_VIEW);
            } else {
                headerHolder.itemView.setVisibility(View.VISIBLE);
                headerHolder.itemView.setTag(HAS_STICKY_VIEW);
            }
        } else {
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            itemHolder.itemView.setContentDescription(formatDatas.get(position).category);
            itemHolder.itemView.setTag(NONE_STICKY_VIEW);
            itemHolder.title.setText(formatDatas.get(position).title);
            itemHolder.subTitle.setText(formatDatas.get(position).subTitle);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (!formatDatas.get(position).title.isEmpty()) {
            Log.e("leon", "" + position + CONTAINER_ITEM);
            return CONTAINER_ITEM;
        } else {
            Log.e("leon", "" + position + HEADER_ITEM);
            return HEADER_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return formatDatas.size();
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView header_view;


        public HeaderViewHolder(View itemView) {
            super(itemView);

            header_view = itemView.findViewById(R.id.header_view);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subTitle;


        public ItemViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subTitle);
        }
    }

}
