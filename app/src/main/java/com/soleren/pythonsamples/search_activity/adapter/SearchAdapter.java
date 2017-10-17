package com.soleren.pythonsamples.search_activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.soleren.pythonsamples.model.Title;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Title> mSearchedTitles;

    public SearchAdapter(List<Title> mSearchedTitles) {
        this.mSearchedTitles = mSearchedTitles;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mSearchedTitles.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        public SearchViewHolder(View itemView) {
            super(itemView);
        }
    }
}
