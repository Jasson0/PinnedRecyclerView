package com.example.leon.pinnedrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView header_view;
    RecyclerView rv_sticky;
    MyAdapter adapter;

    ArrayList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("" + i);
            strings.add("" + i);
        }

        adapter = new MyAdapter(this, strings);

        header_view = (TextView) findViewById(R.id.header_view);
        rv_sticky = (RecyclerView) findViewById(R.id.rv_sticky);
        rv_sticky.setHasFixedSize(true);
        rv_sticky.setLayoutManager(new LinearLayoutManager(this));
        rv_sticky.setAdapter(adapter);
        rv_sticky.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                View stickyInfoView = recyclerView.getChildAt(0);
                Log.e("leon","-------");
                if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
                    Log.e("leon","========");
                    header_view.setText(String.valueOf(stickyInfoView.getContentDescription()));
                }
                View transInfoView = recyclerView.findChildViewUnder(header_view.getMeasuredWidth() / 2, header_view.getMeasuredHeight() + 1);
                if (transInfoView != null && transInfoView.getTag() != null) {
                    int tag = (int) transInfoView.getTag();
                    int deltaY = transInfoView.getTop() - header_view.getMeasuredHeight();
                    if (tag == MyAdapter.HAS_STICKY_VIEW) {
                        if (transInfoView.getTop() > 0) {
                            header_view.setTranslationY(deltaY);
                        } else {
                            header_view.setTranslationY(0);
                        }
                    } else {
                        header_view.setTranslationY(0);
                    }
                }
            }
        });
    }
}
