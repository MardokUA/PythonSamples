package com.soleren.pythonsamples.mvp.main_activity;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by den on 2017-05-23.
 */

public interface MainActivityPresenter {
    ArrayList<String> getListTitles();
    void selectActionBarTitle(int position);
    void selectFragment(int fNumber);
    void destroy();
}
