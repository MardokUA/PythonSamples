package com.soleren.pythonsamples.fragments;

import com.soleren.pythonsamples.fragments.adapter.FragmentAdapter;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.utils.ContentFactory;

public class TitleFragment extends HierarchyFragment {

    public TitleFragment() {
    }

    @Override
    protected void createAdapter() {
        int viewCategory = ContentFactory.isSubMenuNeedExtendViewType() ? Const.VIEW_TYPE_NOTES : Const.VIEW_TYPE_CATEGORY;
        mMainAdapter = new FragmentAdapter(ContentFactory.getTitlesList(), viewCategory);
    }

    @Override
    public void onItemClick(String adapterItemTitle) {
        if (mFragmentChangeListener != null && !ContentFactory.getCurrentSubMenuKey().equals(Const.NOTES) &&
                !ContentFactory.getCurrentSubMenuKey().equals(Const.TIPS) &&
                !ContentFactory.getCurrentSubMenuKey().equals(Const.DEFINITIONS)) {
            ContentFactory.setCurrentTitleKey(adapterItemTitle);
            mFragmentChangeListener.changeCurrentVisibleFragment(adapterItemTitle, Const.CONTENT_ID);
        }
    }
}
