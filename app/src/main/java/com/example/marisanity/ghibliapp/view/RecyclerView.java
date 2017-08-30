package com.example.marisanity.ghibliapp.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;


/**
 * Created by Marisanity on 8/29/2017.
 */

public class RecyclerView extends android.support.v7.widget.RecyclerView {

    public RecyclerView(Context context) {
        super(context);
    }

    public RecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private LoadMoreListener loadMoreListener;


    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.addOnScrollListener(listener);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.removeOnScrollListener(listener);
    }

    private final RecyclerView.OnScrollListener listener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(android.support.v7.widget.RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(android.support.v7.widget.RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if(getAdapter() != null && listener != null) {

                int itemCount = getAdapter().getItemCount();

                LinearLayoutManager manager = (LinearLayoutManager) getLayoutManager();

                int lastVisiblePos = manager.findLastVisibleItemPosition();

                if (lastVisiblePos > itemCount - 2) {
                    loadMoreListener.shouldLoadMore();

                }
            }
        }
    };

    public interface LoadMoreListener{

        void shouldLoadMore();

    }


}
