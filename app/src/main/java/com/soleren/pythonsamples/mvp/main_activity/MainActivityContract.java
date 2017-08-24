package com.soleren.pythonsamples.mvp.main_activity;

import android.support.v4.app.Fragment;

import com.soleren.pythonsamples.mvp.base.BasePresenter;

import java.util.ArrayList;

public interface MainActivityContract {

    interface Presenter extends BasePresenter {

        ArrayList<String> getListTitles();

        void selectActionBarTitle(int position);
    }

    interface View {

        void setActionBarTitle(String title);

        void setActiveFragment(Fragment fragment);
    }
}
