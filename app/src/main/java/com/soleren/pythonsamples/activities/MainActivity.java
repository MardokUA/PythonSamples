package com.soleren.pythonsamples.activities;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.adapters.MainAdapter;


import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.databinding.ActivityMainBinding;
import com.soleren.pythonsamples.fragments.MainFragment;
import com.soleren.pythonsamples.fragments.MenuFragment;
import com.soleren.pythonsamples.model.Item;
import com.google.android.gms.ads.AdRequest;
import com.soleren.pythonsamples.utils.XMLParser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle drawerToggle;
    private ActivityMainBinding binding;
    private MainAdapter adapter;
    private Toolbar toolbar;
    private ArrayList<String> listMenu;
    private ArrayList<Item> listItems;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        listMenu = new ArrayList<>();
        fm = getSupportFragmentManager();
        drawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open_drawer, R.string.close_drawer);
        binding.drawerLayout.addDrawerListener(drawerToggle);
        listItems = getListMenu(R.xml.main);
        adapter = new MainAdapter(listMenu);
        binding.mainActivityRecycler.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.mainActivityRecycler.setLayoutManager(manager);

        setActiveFragment(MenuFragment.newInstance());
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

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (binding.adView != null)
            binding.adView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (binding.adView != null)
            binding.adView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (binding.adView != null)
            binding.adView.destroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (fm != null) {
            if (fm.getBackStackEntryCount() == 0) {
                binding.mainActivityRecycler.setVisibility(View.VISIBLE);
            }
        }
    }
}
