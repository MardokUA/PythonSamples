package com.soleren.pythonsamples.search_activity;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.model.Title;
import com.soleren.pythonsamples.search_activity.adapter.SearchAdapter;

import java.util.List;

class SearchViewHolder {

    private static String LOG_TAG = SearchViewHolder.class.getName();

    private AppCompatEditText mSearchField;
    private ImageView mSearchErrorImage;
    private TextView mSearchErrorText;
    private RecyclerView mRecycler;

    private SearchAdapter mSearchAdapter;
    private SearchFieldListener mSearchFieldListener;

    private SearchViewHolder() {
        mSearchAdapter = new SearchAdapter();
    }

    static SearchViewHolder initViewHolder() {
        return new SearchViewHolder();
    }

    SearchViewHolder withContainer(View container) {
        mSearchErrorText = (TextView) container.findViewById(R.id.search_error_text);
        mSearchErrorImage = (ImageView) container.findViewById(R.id.search_error_image);
        mRecycler = (RecyclerView) container.findViewById(R.id.search_recycler);

        mSearchField = (AppCompatEditText) container.findViewById(R.id.search_field);
        mSearchField.addTextChangedListener(mTextWatcher);
        initRecycler(container);
        return this;
    }

    private void initRecycler(View container) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(container.getContext());
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mSearchAdapter);
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (mSearchFieldListener != null) {
                mSearchFieldListener.onTextChanged(editable.toString());
                Log.i(LOG_TAG, editable.toString());
            }
        }
    };

    void setSearchFieldListener(SearchFieldListener searchFieldListener) {
        mSearchFieldListener = searchFieldListener;
    }

    void updateQuery(List<Title> queryTitles) {
        changeErrorViewVisibility(queryTitles.isEmpty());
        mSearchAdapter.updateAdapter(queryTitles);
    }

    private void changeErrorViewVisibility(boolean isShow) {
        if (isShow) {
            mSearchErrorText.setVisibility(View.VISIBLE);
            mSearchErrorImage.setVisibility(View.VISIBLE);
        } else {
            mSearchErrorText.setVisibility(View.GONE);
            mSearchErrorImage.setVisibility(View.GONE);
        }
    }

    interface SearchFieldListener {
        void onTextChanged(String textQuery);
    }
}
