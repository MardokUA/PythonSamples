package com.soleren.pythonsamples.search_activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.model.Title;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Title> mSearchedTitles;

    public SearchAdapter() {
        mSearchedTitles = new ArrayList<>();
    }

    public void updateAdapter(List<Title> searchedTitles) {
        mSearchedTitles = searchedTitles;
        notifyDataSetChanged();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout holderLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.search_view, parent, false);
        return new SearchViewHolder(holderLayout);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        Title title = mSearchedTitles.get(position);
        holder.mTitle.setText(title.getTitle());

        if (title.getTitleContent().isEmpty()) {
            holder.mContentPreview.setVisibility(View.GONE);
        } else {
            holder.mContentPreview.setVisibility(View.VISIBLE);
            holder.mContentPreview.setText(title.getTitleContent());
        }
    }

    @Override
    public int getItemCount() {
        return mSearchedTitles.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mContentPreview;

        SearchViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.search_item_title);
            mContentPreview = (TextView) itemView.findViewById(R.id.search_item_content_preview);
        }
    }
}
