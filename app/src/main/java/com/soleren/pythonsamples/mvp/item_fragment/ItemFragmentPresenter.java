package com.soleren.pythonsamples.mvp.item_fragment;

import android.app.Activity;
import android.support.v7.widget.ShareActionProvider;

import com.soleren.pythonsamples.model.Item;

/**
 * Created by den on 2017-05-23.
 */

public interface ItemFragmentPresenter {
    void getItem(Item item);
    void makeIntent(ShareActionProvider provider, String text);
    void destroy();
}
