package com.soleren.pythonsamples.fragments;

import com.soleren.pythonsamples.adapters.MainAdapter;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.utils.ContentFactory;

public class MenuFragment extends HierarchyFragment {

    public MenuFragment() {
    }

    @Override
    protected void createAdapter() {
        mMainAdapter = new MainAdapter(ContentFactory.getMenuList(), Const.VIEW_TYPE_CATEGORY);
    }

    @Override
    public void onItemClick(String menuKey) {
        if (mFragmentChangeListener != null) {
            ContentFactory.setCurrentMenuKey(menuKey);
            mFragmentChangeListener.changeCurrentVisibleFragment(menuKey, Const.SUB_MENU_TITLE_ID);
        }
    }

}
