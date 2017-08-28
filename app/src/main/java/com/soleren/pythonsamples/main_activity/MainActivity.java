package com.soleren.pythonsamples.main_activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.fragments.ContentFragment;
import com.soleren.pythonsamples.fragments.HierarchyFragment;
import com.soleren.pythonsamples.fragments.MenuFragment;
import com.soleren.pythonsamples.fragments.SubMenuFragment;
import com.soleren.pythonsamples.fragments.TitleFragment;

import java.util.EmptyStackException;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private Toolbar mToolBar;
    private ActionBar mActionBar;
    private Stack<String> mTitleStack = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();

        initViews();
        initToolbar();
        if (savedInstanceState == null) {
            initTopFragment();
        } else {
            restoreTitle(savedInstanceState);
        }
    }

    private void initViews() {
        mToolBar = (Toolbar) findViewById(R.id.main_activity_toolbar);
    }

    private void initToolbar() {
        setSupportActionBar(mToolBar);
        mActionBar = getSupportActionBar();
    }

    private void initTopFragment() {
        MenuFragment menuFragment = new MenuFragment();
        menuFragment.setFragmentChangeListener(fragmentChangeListener);
        setActiveFragment(menuFragment);
    }

    public void setActiveFragment(MenuFragment fragment) {
        mFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(MenuFragment.class.getName())
                .commit();
    }

    private HierarchyFragment.FragmentChangeListener fragmentChangeListener = new HierarchyFragment.FragmentChangeListener() {
        @Override
        public void changeCurrentVisibleFragment(String categoryTitle, int nextFragment) {
            addToolBarTitle(categoryTitle);
            MainActivity.this.changeCurrentVisibleFragment(nextFragment);
        }
    };

    private void changeCurrentVisibleFragment(int nextFragment) {
        switch (nextFragment) {
            case Const.SUB_MENU_TITLE_ID:
                SubMenuFragment subMenuFragment = new SubMenuFragment();
                subMenuFragment.setFragmentChangeListener(fragmentChangeListener);
                mFragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_pop_exit)
                        .replace(R.id.fragment_container, subMenuFragment)
                        .addToBackStack(SubMenuFragment.class.getName())
                        .commit();
                break;
            case Const.TITLE_ID:
                TitleFragment titleFragment = new TitleFragment();
                titleFragment.setFragmentChangeListener(fragmentChangeListener);
                mFragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_pop_exit)
                        .replace(R.id.fragment_container, titleFragment)
                        .addToBackStack(TitleFragment.class.getName())
                        .commit();
                break;
            case Const.CONTENT_ID:
                ContentFragment contentFragment = new ContentFragment();
                mFragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_pop_exit)
                        .replace(R.id.fragment_container, contentFragment)
                        .addToBackStack(ContentFragment.class.getName())
                        .commit();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mFragmentManager.getBackStackEntryCount() > 1) {
            mFragmentManager.popBackStack();
            popToolBarState();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("key", mTitleStack);
        super.onSaveInstanceState(outState);
    }

    private void addToolBarTitle(String currentTitle) {
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mTitleStack.add(currentTitle);
        mToolBar.setTitle(currentTitle);
    }

    private void popToolBarState() {
        try {
            mTitleStack.pop();
            mToolBar.setTitle(mTitleStack.peek());
        } catch (EmptyStackException e) {
            setDefaultState();
        }
    }

    private void restoreTitle(Bundle savedInstanceState) {
        mTitleStack = (Stack<String>) savedInstanceState.get("key");
        try {
            mToolBar.setTitle(mTitleStack.peek());
            mActionBar.setDisplayHomeAsUpEnabled(true);
        } catch (EmptyStackException e) {
            setDefaultState();
        }

    }

    private void setDefaultState() {
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mToolBar.setTitle(R.string.app_name);
    }
}
