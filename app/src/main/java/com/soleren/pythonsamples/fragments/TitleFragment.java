package com.soleren.pythonsamples.fragments;

import com.soleren.pythonsamples.adapters.MainAdapter;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.model.SubMenuComparator;
import com.soleren.pythonsamples.utils.ContentFactory;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class TitleFragment extends HierarchyFragment {

    public TitleFragment() {
    }

    @Override
    protected void createAdapter() {
        int viewCategory = ContentFactory.isSubMenuNeedExtendViewType() ? Const.VIEW_TYPE_NOTES : Const.VIEW_TYPE_CATEGORY;
        mMainAdapter = new MainAdapter(ContentFactory.geTitlesList(), viewCategory);
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
