package com.soleren.pythonsamples.fragments;

import com.soleren.pythonsamples.fragments.adapter.FragmentAdapter;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.model.SubMenuComparator;
import com.soleren.pythonsamples.utils.ContentFactory;

import java.util.Collections;
import java.util.List;

public class SubMenuFragment extends HierarchyFragment {

    public SubMenuFragment() {
    }

    @Override
    protected void createAdapter() {
        List<String> subMenuList = ContentFactory.getSubMenuList();
        Collections.sort(subMenuList, new SubMenuComparator());
        mMainAdapter = new FragmentAdapter(subMenuList,Const.VIEW_TYPE_CATEGORY);
    }

    @Override
    public void onItemClick(String subMenuTitle) {
        if (mFragmentChangeListener != null) {
            ContentFactory.setCurrentSubMenuKey(subMenuTitle);
            mFragmentChangeListener.changeCurrentVisibleFragment(subMenuTitle, Const.TITLE_ID);
        }
    }
}
