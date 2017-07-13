package com.soleren.pythonsamples.mvp.main_fragment;

import android.support.v4.app.Fragment;

import com.soleren.pythonsamples.activities.MainActivity;

/**
 * Created by den on 2017-05-22.
 */

public interface MainFragmentView {
    void setTitle(String title);
    void setFragment(MainActivity activity,Fragment fragment);
}
