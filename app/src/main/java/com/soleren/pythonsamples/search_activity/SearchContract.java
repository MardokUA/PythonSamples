package com.soleren.pythonsamples.search_activity;

import com.soleren.pythonsamples.model.Title;

import java.util.List;

interface SearchContract {

    interface Presenter {

        void onBindView(SearchContract.SearchView view);

        void onUnbindView(SearchContract.SearchView view);

        void onSearchRequestChanged(String searchQuery);
    }

    interface SearchView {

        void updateSearchQuery(List<Title> queryTitles);
    }
}
