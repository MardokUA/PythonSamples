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
    //names of navbar menu
    private FragmentManager fm;
    private AdRequest adRequest;
    private boolean isScreenOn;
    private XMLParser parser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        listMenu = new ArrayList<>();
        fm = getSupportFragmentManager();
//        if(savedInstanceState != null) {
//            if(fm.getBackStackEntryCount() != 0) {
//                binding.mainActivityRecycler.setVisibility(View.GONE);
//            }
//        }
//        parser = XMLParser.getXmlParser(this,R.xml.main);

        drawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open_drawer, R.string.close_drawer);
        binding.drawerLayout.addDrawerListener(drawerToggle);
//        listItems = getListMenu(R.xml.main);
//        Log.d("!!!",listItems.size()+"");
//        adapter = new MainAdapter(listMenu);
//        binding.mainActivityRecycler.setAdapter(adapter);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        binding.mainActivityRecycler.setLayoutManager(manager);
        setActiveFragment(MenuFragment.newInstance());
//
//        adapter.setListener(new MainAdapter.AdapterListener() {
//            @Override
//            public void onClick(int position) {
//                presenter.selectFragment(position);
//                binding.mainActivityRecycler.setVisibility(View.GONE);
//                if(binding.adView != null)
//                    binding.adView.destroy();
//            }
//        });
//        toolbar = binding.toolbar;
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

        //actions after clicking on navbar menu
//        binding.navigtionView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Log.d("!!!",item.getItemId()+"");
//                presenter.selectFragment(item.getItemId());
//                if(binding.adView != null)
//                    binding.adView.destroy();
//                return true;
//            }
//        });

//        adRequest = new AdRequest.Builder().build();
//        binding.adView.loadAd(adRequest);
    }

    private ArrayList<Item> getListMenu(int res){
        listItems = XMLParser.getXmlParser(this,res).parse();
        for (Item listItem : listItems) {
            if (!listMenu.contains(listItem.getMenu()) && listItem.getMenu() != null)
                listMenu.add(listItem.getMenu());
        }
        return listItems;
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("visible",true);
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        //change view of drawerToggle
//        drawerToggle.onConfigurationChanged(newConfig);
//    }


//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        // sync  onRestoreInstanceState.
//        drawerToggle.syncState();
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (drawerToggle.onOptionsItemSelected(item))// let drawerToggle act
//            return true;
//        return super.onOptionsItemSelected(item);
//    }

    public void setActionBarTitle(String title) {
        toolbar.setTitle(title);
    }


    public void setActiveFragment(Fragment fragment) {

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container,fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

//        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                Fragment currentBackStackFragment = getSupportFragmentManager().findFragmentByTag("MainFragment");
//                if(currentBackStackFragment != null){
//                    Log.d("!!!",currentBackStackFragment.toString());
//                }
//            }
//        });
//        binding.drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(binding.adView != null)
            binding.adView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(binding.adView != null)
            binding.adView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(binding.adView != null)
            binding.adView.destroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if(fm != null) {
            if(fm.getBackStackEntryCount() == 0) {
                binding.mainActivityRecycler.setVisibility(View.VISIBLE);
//                binding.adView.loadAd(adRequest);
            }
        }
    }
}
