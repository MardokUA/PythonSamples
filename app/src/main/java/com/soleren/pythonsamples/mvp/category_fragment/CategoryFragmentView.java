package com.soleren.pythonsamples.mvp.category_fragment;

import android.support.v4.app.Fragment;

import com.soleren.pythonsamples.activities.MainActivity;

/**
 * Created by den on 2017-05-23.
 */

public interface CategoryFragmentView {
    void setTitle(String title);
    void setFragment(MainActivity activity, Fragment fragment);
}
