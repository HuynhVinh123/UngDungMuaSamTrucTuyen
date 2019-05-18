package com.example.huynhvinh.applazada_java.model.ObjectClass;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class LoadMoreScroll extends RecyclerView.OnScrollListener {

    int itemdautien = 0;
    int tongitem = 0;       // Tổng số item hiện có trong gridview trong Ryccer VD: 20 item
    int itemloadtruoc = 10;  // số item được hiển thị   ẩn 10 item trong tổng số item
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;
    int CheckLoadMore   = 0;

    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager,ILoadMore iLoadMore) {
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        tongitem = layoutManager.getItemCount();  // lấy số lượng item hiện đang có trong
        if(tongitem>0) {
            if (layoutManager instanceof GridLayoutManager) {   // LayoutManager hiển thị dưới dạng LinearLayout
                itemdautien = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
            } else if (layoutManager instanceof LinearLayoutManager)    // LayoutManager hiển thì dưới dạng GridLayout
            {
                itemdautien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            }

            if (tongitem <= (itemdautien + itemloadtruoc))   // nếu số lượng item ta truy vấn trên co sở dữ liệu nhỏ hơn số lượng item đag show trên recycler View
            {
                iLoadMore.LoadMore(tongitem);
            }
        }

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}