package com.soleren.pythonsamples.fragments;

import com.soleren.pythonsamples.adapters.MainAdapter;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.utils.CategoryFactory;

public class TitleFragment extends HierarchyFragment {

    public TitleFragment() {
    }

    @Override
    protected void createAdapter() {
        mMainAdapter = new MainAdapter(CategoryFactory.geTitlesList());
    }

    @Override
    public void onItemClick(String adapterItemTitle) {
        if (mFragmentChangeListener != null && CategoryFactory.isTitleContainsData(adapterItemTitle)) {
            CategoryFactory.setCurrentTitleKey(adapterItemTitle);
            mFragmentChangeListener.changeCurrentVisibleFragment(adapterItemTitle, Const.CONTENT_ID);
        }
    }
}
