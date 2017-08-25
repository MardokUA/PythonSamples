package com.soleren.pythonsamples.fragments;

import com.soleren.pythonsamples.data.Const;

public class TitleFragment extends HierarchyFragment {

    public TitleFragment() {
    }

    @Override
    public void onItemClick(String adapterItemTitle) {
        if (mFragmentChangeListener != null) {
            mFragmentChangeListener.changeCurrentVisibleFragment(adapterItemTitle, Const.CONTENT_ID);
        }
    }
}
