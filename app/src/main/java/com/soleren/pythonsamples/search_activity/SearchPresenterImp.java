package com.soleren.pythonsamples.search_activity;

import com.soleren.pythonsamples.model.Title;
import com.soleren.pythonsamples.utils.ContentFactory;

import java.util.ArrayList;
import java.util.List;

class SearchPresenterImp implements SearchContract.Presenter {

    private SearchContract.SearchView mSearchView;
    private List<Title> mQueryList;

    SearchPresenterImp() {
        mQueryList = new ArrayList<>(250);
    }

    public void onBindView(SearchContract.SearchView view) {
        mSearchView = view;
    }

    @Override
    public void onUnbindView(SearchContract.SearchView view) {
        mSearchView = null;
    }

    @Override
    public void onSearchRequestChanged(String searchQuery) {
        mQueryList.clear();
        if (searchQuery.length() > 2) {
            for (Title title : ContentFactory.getTitleList()) {
                if (title.getTitle() != null && !title.getTitle().isEmpty() && !title.getTitleContent().isEmpty()) {
                    if (title.getTitle().toLowerCase().contains(searchQuery.toLowerCase())) {
                        mQueryList.add(title);
                    }
                }
            }
        }
        if (mSearchView != null) {
            mSearchView.updateSearchQuery(mQueryList);
        }
    }
}
