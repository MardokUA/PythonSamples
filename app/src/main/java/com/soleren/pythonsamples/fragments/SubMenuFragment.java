package com.soleren.pythonsamples.fragments;

import com.soleren.pythonsamples.adapters.MainAdapter;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.utils.ContentFactory;

public class SubMenuFragment extends HierarchyFragment {

    public SubMenuFragment() {
    }

    @Override
    protected void createAdapter() {
        mMainAdapter = new MainAdapter(ContentFactory.getSubMenuList());
    }

    @Override
    public void onItemClick(String subMenuTitle) {
        if (mFragmentChangeListener != null) {
            ContentFactory.setCurrentSubMenuKey(subMenuTitle);
            mFragmentChangeListener.changeCurrentVisibleFragment(subMenuTitle, Const.TITLE_ID);
        }
    }
}
