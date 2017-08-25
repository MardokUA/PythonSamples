package com.soleren.pythonsamples.fragments;

import com.soleren.pythonsamples.adapters.MainAdapter;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.utils.CategoryFactory;

public class SubMenuFragment extends HierarchyFragment {

    public SubMenuFragment() {
    }

    @Override
    protected void createAdapter() {
        mMainAdapter = new MainAdapter(CategoryFactory.getSubMenuList());
    }

    @Override
    public void onItemClick(String adapterItemTitle) {
        if (mFragmentChangeListener != null) {
            mFragmentChangeListener.changeCurrentVisibleFragment(adapterItemTitle, Const.TITLE_ID);
        }
    }
}
