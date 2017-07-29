package com.example.leon.pinnedrecyclerview;

import android.content.Context;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 2017/7/29.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MainViewHolder> {

    public static final int FIRST_STICKY_VIEW = 1;
    public static final int HAS_STICKY_VIEW = 2;
    public static final int NONE_STICKY_VIEW = 3;

    private Context context;
    private ArrayList<String> strings;

    public MyAdapter(Context context, ArrayList<String> strings) {
        this.context=context;
        this.strings=strings;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.adapter_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.header_view.setText(strings.get(position));
        holder.itemView.setContentDescription(strings.get(position));
        if (position==0) {
            holder.header_view.setVisibility(View.VISIBLE);
            holder.itemView.setTag(FIRST_STICKY_VIEW);
        }
        else {
            if (!strings.get(position).equals(strings.get(position-1))) {
                holder.header_view.setVisibility(View.VISIBLE);
                holder.itemView.setTag(HAS_STICKY_VIEW);
            }
            else {
                holder.header_view.setVisibility(View.GONE);
                holder.itemView.setTag(NONE_STICKY_VIEW);
            }
        }
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        TextView header_view;

        public MainViewHolder(View itemView) {
            super(itemView);

            header_view= (TextView) itemView.findViewById(R.id.header_view);
        }
    }

}
