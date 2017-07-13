package com.soleren.pythonsamples.mvp.category_fragment;

import android.os.Bundle;

import com.soleren.pythonsamples.model.Item;

import java.util.ArrayList;

/**
 * Created by den on 2017-05-23.
 */

public interface CategoryFragmentPresenter {
    void selectTitle(Bundle bundle);
    void selectTitle(int res);
    ArrayList<String> getListTitles(String name);
    void selectFragment(int position);
    ArrayList<Item> getItemsFromXML(int res);
    ArrayList<String> getTitlesFromXML(int res);
    void destroy();
}
