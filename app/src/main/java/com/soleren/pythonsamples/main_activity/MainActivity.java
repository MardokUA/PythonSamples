package com.soleren.pythonsamples.main_activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.fragments.HierarchyFragment;
import com.soleren.pythonsamples.fragments.MenuFragment;
import com.soleren.pythonsamples.fragments.SubMenuFragment;
import com.soleren.pythonsamples.fragments.TitleFragment;

public class MainActivity extends AppCompatActivity {

    private ToolBarController mToolBarController;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initToolbar();
        initTopFragment();
    }

    private void initViews() {
        mToolBar = (Toolbar) findViewById(R.id.main_activity_toolbar);
    }

    private void initToolbar() {
        setSupportActionBar(mToolBar);
        ActionBar supportActionBar = getSupportActionBar();
        initToolBarController(supportActionBar);
    }

    private void initToolBarController(ActionBar actionBar) {
        mToolBarController = ToolBarController
                .createBuilder()
                .withActionBar(actionBar)
                .withToolBar(mToolBar)
                .build();
    }

    private void initTopFragment() {
        MenuFragment menuFragment = new MenuFragment();
        menuFragment.setFragmentChangeListener(fragmentChangeListener);
        setActiveFragment(menuFragment);
    }

    public void setActiveFragment(MenuFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(MenuFragment.class.getName())
                .commit();
    }

    private HierarchyFragment.FragmentChangeListener fragmentChangeListener = new HierarchyFragment.FragmentChangeListener() {
        @Override
        public void changeCurrentVisibleFragment(String menuTitle, int nextFragment) {
            if (mToolBarController != null) {
                mToolBarController.addToolBarTitle(new ToolBarState(menuTitle));
            }
            changeCurrentVisibilityFragment(nextFragment);
        }
    };

    private void changeCurrentVisibilityFragment(int nextFragment) {
        switch (nextFragment) {
            case Const.SUB_MENU_TITLE_ID:
                SubMenuFragment subMenuFragment = new SubMenuFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_pop_exit)
                        .replace(R.id.fragment_container, subMenuFragment)
                        .addToBackStack(SubMenuFragment.class.getName())
                        .commit();
                break;
            case Const.TITLE_ID:
                TitleFragment titleFragment = new TitleFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, titleFragment)
                        .addToBackStack(TitleFragment.class.getName())
                        .commit();
                break;
            case Const.CONTENT_ID:
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
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            mToolBarController.popToolBarState();
            super.onBackPressed();
        } else {
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("visible", true);
    }
}
