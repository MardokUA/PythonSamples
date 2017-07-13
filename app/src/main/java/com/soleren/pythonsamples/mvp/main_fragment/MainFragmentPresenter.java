package com.soleren.pythonsamples.mvp.main_fragment;

import android.os.Bundle;

import com.soleren.pythonsamples.model.Item;

import java.util.ArrayList;

/**
 * Created by den on 2017-05-22.
 */

public interface MainFragmentPresenter {

    ArrayList<String> getListTitles(String name);
    void selectTitle(Bundle bundle);
    void selectFragment(String fName,int position);
    ArrayList<Item> getItemsFromXML(int res);
    ArrayList<String> getTitlesFromXML(int res);
    void destroy();
}
