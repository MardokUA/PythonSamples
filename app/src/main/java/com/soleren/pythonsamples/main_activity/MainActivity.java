package com.soleren.pythonsamples.main_activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.adapters.MainAdapter;
import com.soleren.pythonsamples.databinding.ActivityMainBinding;
import com.soleren.pythonsamples.fragments.MenuFragment;
import com.soleren.pythonsamples.model.Item;
import com.soleren.pythonsamples.utils.XMLParser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mActivityBindings;
    private ActionBarDrawerToggle mArrowToggle;
    private Toolbar toolbar;

    private MainAdapter mAdapter;
    private ToolBarController mToolBarController;

    private ArrayList<String> listMenu;
    private ArrayList<Item> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBindings();
        initToolBarController();

        listMenu = new ArrayList<>();
        listItems = getListMenu(R.xml.main);
        mAdapter = new MainAdapter(listMenu);
        mActivityBindings.mainActivityRecycler.setAdapter(mAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mActivityBindings.mainActivityRecycler.setLayoutManager(manager);
        setActiveFragment(MenuFragment.newInstance());
    }

    private void initBindings() {
        mActivityBindings = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mArrowToggle = new ActionBarDrawerToggle(this, mActivityBindings.drawerLayout, R.string.open_drawer, R.string.close_drawer);
        mActivityBindings.drawerLayout.addDrawerListener(mArrowToggle);
    }

    private void initToolBarController() {
        mToolBarController = ToolBarController
                .createBuilder()
                .withToolBar(toolbar)
                .withActionBarDrawerToggle(mArrowToggle)
                .build();
    }

    private ArrayList<Item> getListMenu(int res) {
        listItems = XMLParser.getXmlParser(res).parse();
        for (Item listItem : listItems) {
            if (!listMenu.contains(listItem.getMenu()) && listItem.getMenu() != null)
                listMenu.add(listItem.getMenu());
        }
        return listItems;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("visible", true);
    }

    public void setActionBarTitle(String title) {
        toolbar.setTitle(title);
    }

    public void setActiveFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container, fragment)
                .addToBackStack(Fragment.class.getName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mActivityBindings.adView != null)
            mActivityBindings.adView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mActivityBindings.adView != null)
            mActivityBindings.adView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mActivityBindings.adView != null)
            mActivityBindings.adView.destroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            mActivityBindings.mainActivityRecycler.setVisibility(View.VISIBLE);
        }
    }
}
