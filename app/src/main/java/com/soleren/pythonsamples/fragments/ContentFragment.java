package com.soleren.pythonsamples.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.model.Title;
import com.soleren.pythonsamples.utils.ContentFactory;

public class ContentFragment extends HierarchyFragment {

    private TextView mTvContent;
    private TextView mTvPrint;

    public ContentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_item, container, false);
        initViews(root);
        setContent();
        return root;
    }

    private void initViews(View root) {
        mTvContent = (TextView) root.findViewById(R.id.content);
        mTvPrint = (TextView) root.findViewById(R.id.print);
    }

    private void setContent() {
        Title title = ContentFactory.getCurrentTitleData();
        mTvContent.setText(title.getTitleContent());
        mTvPrint.setText(title.getmTitlePrint());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_share, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        ShareActionProvider shareActionProvider = new ShareActionProvider(getActivity()) {
            @Override
            public View onCreateActionView() {
                return null;
            }
        };

        MenuItemCompat.setActionProvider(menuItem, shareActionProvider);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
