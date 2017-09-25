package com.leng.m.swipebugdemo;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BRVAHSwipeActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView111)
    RecyclerView recyclerView111;
    @Bind(R.id.easySwipeRefresh)
    SmartRefreshLayout easySwipeRefresh;

    List<String> list;
    BRVAHSwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_swipe);
        init();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    private void init() {
        setDate();
        easySwipeRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                easySwipeRefresh.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setDate();
                        easySwipeRefresh.finishRefresh();
                    }
                }, 2000);
            }
        });
    }

    private void setDate() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("item " + i);
        }
        adapter = new BRVAHSwipeAdapter(R.layout.easy_swipe_item, list);
        recyclerView111.setLayoutManager(new LinearLayoutManager(this));
        recyclerView111.setAdapter(adapter);

        ItemDragAndSwipeCallback callback = new ItemDragAndSwipeCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView111);
        adapter.setOnItemDragListener(onItemDragListener);
        adapter.setOnItemSwipeListener(onItemSwipeListener);
        adapter.enableDragItem(itemTouchHelper, R.id.easyContentLayout, true);
        adapter.enableSwipeItem();
    }

    public class BRVAHSwipeAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {

        public BRVAHSwipeAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.easyContent, item);
        }

    }

    OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
        }

        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
        }

        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
        }

        @Override
        public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
        }
    };

    OnItemDragListener onItemDragListener = new OnItemDragListener() {
        @Override
        public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
        }

        @Override
        public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
        }

        @Override
        public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
        }
    };

}
