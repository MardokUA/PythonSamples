package com.soleren.pythonsamples.mvp.item_fragment;

import android.support.v7.widget.ShareActionProvider;

import com.soleren.pythonsamples.model.Item;
import com.soleren.pythonsamples.mvp.base.BasePresenter;

public interface ItemFragmentContract {

    interface Presenter extends BasePresenter {

        void getItem(Item item);

        void makeIntent(ShareActionProvider provider, String text);
    }

    interface View {
        void showData(String content,String print);

        void setTitle(String title);
    }
}
