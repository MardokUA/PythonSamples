package com.soleren.pythonsamples.mvp.main_activity;

import android.support.v4.app.Fragment;

/**
 * Created by den on 2017-05-23.
 */

public interface MainActivityView {
    void setActionBarTitle(String title);
    void setActiveFragment(Fragment fragment);
}
