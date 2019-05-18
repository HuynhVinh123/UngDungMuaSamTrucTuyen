package com.example.huynhvinh.applazada_java.model.ObjectClass;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class LoadMoreAllProductScroll  extends RecyclerView.OnScrollListener {

    int itemdautien = 0;
    int tongitem = 0;       // Tổng số item hiện có trong gridview trong Ryccer VD: 20 item
    int itemloadtruoc = 10;  // số item được hiển thị   ẩn 10 item trong tổng số item
    int CheckLoadMore = 1;


    ILoadMore loadMore;
    RecyclerView.LayoutManager layoutManager;

    public LoadMoreAllProductScroll(RecyclerView.LayoutManager layoutManager,ILoadMore loadMore) {
       this.loadMore = loadMore;
       this.layoutManager  = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        tongitem = layoutManager.getItemCount();
        itemdautien = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
        if(tongitem <= itemloadtruoc + itemdautien)
        {
         //   loadMore.LoadMore(tongitem);
        }


    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}