package com.soleren.pythonsamples.search_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.model.Title;

import java.util.List;

public class SearchActivity extends AppCompatActivity
        implements SearchContract.SearchView, SearchViewHolder.SearchFieldListener {

    private Toolbar mToolBar;
    private SearchViewHolder mViewHolder;
    private SearchPresenterImp mSearchPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchPresenter = new SearchPresenterImp();

        initToolBar();
        initViewHolder();
    }

    private void initToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.search_tool_bar);
        setSupportActionBar(mToolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initViewHolder() {
        ConstraintLayout container = (ConstraintLayout) findViewById(R.id.search_container);
        mViewHolder = SearchViewHolder.initViewHolder().withContainer(container);
        mViewHolder.setSearchFieldListener(this);
    }

    @Override
    public void onTextChanged(String textQuery) {
        if (mSearchPresenter != null) {
            mSearchPresenter.onSearchRequestChanged(textQuery);
        }
    }

    @Override
    public void updateSearchQuery(List<Title> queryTitles) {
        if (mViewHolder != null) {
            mViewHolder.updateQuery(queryTitles);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSearchPresenter.onBindView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSearchPresenter.onUnbindView(this);
    }
}
