package com.soleren.pythonsamples.mvp.category_fragment;

import android.support.v4.app.Fragment;

import com.soleren.pythonsamples.main_activity.MainActivity;
import com.soleren.pythonsamples.model.Item;
import com.soleren.pythonsamples.mvp.base.BasePresenter;

import java.util.ArrayList;

public interface CategoryFragmentContract {

    interface Presenter extends BasePresenter {

        ArrayList<String> getListTitles(String name);

        ArrayList<Item> getItemsFromXML(int res);

        ArrayList<String> getTitlesFromXML(int res);
    }

    interface View {

        void setTitle(String title);

        void setFragment(MainActivity activity, Fragment fragment);
    }
}
