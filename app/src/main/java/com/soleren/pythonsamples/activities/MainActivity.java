package com.soleren.pythonsamples.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.adapters.MainAdapter;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.databinding.ActivityMainBinding;
import com.soleren.pythonsamples.mvp.main_activity.MainActivityContract;
import com.soleren.pythonsamples.mvp.main_activity.MainActivityPresenterImpl;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private ActivityMainBinding mDataBinding;
    private Toolbar mToolbar;

    private MainActivityPresenterImpl mActivityPresenter;
    private FragmentManager mFragmentManager;
    private MainAdapter mMainAdapter;

    private ArrayList<String> mListTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initToolBar();
        initFragmentManager(savedInstanceState);
        initPresenter();
        initAdapter();
        bindAdapterToView();
    }

    private void initToolBar() {
        mToolbar = mDataBinding.toolbar;
        setSupportActionBar(mToolbar);
    }

    private void initFragmentManager(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            if (mFragmentManager.getBackStackEntryCount() != 0) {
                mDataBinding.mainActivityRecycler.setVisibility(View.GONE);
            }
        }
    }

    private void initPresenter() {
        mActivityPresenter = new MainActivityPresenterImpl(this);
        mListTitles = mActivityPresenter.getListTitles();
    }

    private void initAdapter() {
        mMainAdapter = new MainAdapter(mListTitles);
        mMainAdapter.setListener(new MainAdapter.AdapterListener() {
            @Override
            public void onClick(int position) {
                mActivityPresenter.selectFragment(position);
                mDataBinding.mainActivityRecycler.setVisibility(View.GONE);
                if (mDataBinding.adView != null)
                    mDataBinding.adView.destroy();
            }
        });
    }

    private void bindAdapterToView() {
        mDataBinding.mainActivityRecycler.setAdapter(mMainAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mDataBinding.mainActivityRecycler.setLayoutManager(manager);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("visible", true);
    }

    @Override
    public void setActionBarTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void setActiveFragment(Fragment fragment) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDataBinding.adView != null)
            mDataBinding.adView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDataBinding.adView != null)
            mDataBinding.adView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityPresenter.destroy();
        if (mDataBinding.adView != null)
            mDataBinding.adView.destroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (mFragmentManager != null) {
            if (mFragmentManager.getBackStackEntryCount() == 0) {
                mActivityPresenter.selectActionBarTitle(Const.TITLE);
                mDataBinding.mainActivityRecycler.setVisibility(View.VISIBLE);
            }
        }
    }
}
