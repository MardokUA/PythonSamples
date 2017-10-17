package com.soleren.pythonsamples.search_activity;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soleren.pythonsamples.R;

class SearchViewHolder {

    private AppCompatEditText mSearchField;
    private ImageView mSearchErrorImage;
    private TextView mSearchText;
    private RecyclerView mRecycler;

    private SearchFieldListener mSearchFieldListener;

    private SearchViewHolder() {
    }

    static SearchViewHolder initViewHolder() {
        return new SearchViewHolder();
    }

    SearchViewHolder withContainer(View container) {
        mSearchField = (AppCompatEditText) container.findViewById(R.id.search_field);
        mSearchErrorImage = (ImageView) container.findViewById(R.id.search_error_image);
        mRecycler = (RecyclerView) container.findViewById(R.id.search_recycler);
        mSearchField.addTextChangedListener(mTextWatcher);
        return this;
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
            }
        }
    };

    void setSearchFieldListener(SearchFieldListener searchFieldListener) {
        mSearchFieldListener = searchFieldListener;
    }

    interface SearchFieldListener {
        void onTextChanged(String textQuery);
    }
}
