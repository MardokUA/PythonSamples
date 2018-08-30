package com.soleren.pythonsamples.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.model.Title;
import com.soleren.pythonsamples.utils.ContentFactory;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ContentFragment extends HierarchyFragment {

    private Title mTitle;
    private TextView mTvContent;
    private TextView mTvPrint;
//    private AdView аdView;

    public ContentFragment() {
    }

    public static ContentFragment createInstance(Title title) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Const.SEARCHED_TITLE, title);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        obtainData();
    }

    private void obtainData() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            mTitle = ContentFactory.getCurrentTitleData();
        } else {
            mTitle = bundle.getParcelable(Const.SEARCHED_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_item, container, false);

        AdRequest adRequest = new AdRequest.Builder().build();
//        аdView = (AdView)root.findViewById(R.id.adView);
//        аdView.loadAd(adRequest);

        initViews(root);
        setContent();
        return root;
    }

    private void initViews(View root) {
        mTvContent = (TextView) root.findViewById(R.id.content);
        mTvPrint = (TextView) root.findViewById(R.id.print);
    }

    private void setContent() {
        mTvContent.setText(mTitle.getTitleContent());
        mTvPrint.setText(mTitle.getmTitlePrint());
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                createShareIntent();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, mTitle.getTitleContent() + "\n" + "\n" + mTitle.getmTitlePrint());
        shareIntent.setType("text/plain");
        String chooserText = getActivity().getResources().getString(R.string.chooser_text);
        getActivity().startActivity(Intent.createChooser(shareIntent, chooserText));

    }

    @Override
    public void onDetach() {
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onDetach();
    }

    @Override
    public void onPause() {
//        if (аdView != null) {
//            аdView.pause();
//        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (аdView != null) {
//            аdView.resume();
//        }
    }

    @Override
    public void onDestroy() {
//        if (аdView != null) {
//            аdView.destroy();
//        }
        super.onDestroy();
    }
}
