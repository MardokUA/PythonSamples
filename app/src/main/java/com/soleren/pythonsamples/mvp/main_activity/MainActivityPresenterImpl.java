package com.soleren.pythonsamples.mvp.main_activity;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.fragments.MainFragment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by den on 2017-05-22.
 */

public class MainActivityPresenterImpl implements MainActivityPresenter {
    private MainActivityView view;
    private Fragment fragment;
    private String[] drawerListTitles;
    private Context context;
    public MainActivityPresenterImpl(Context context) {
        this.context = context;
        this.view = (MainActivityView)context;
    }




    @Override
    public ArrayList<String> getListTitles() {
        drawerListTitles = context.getResources().getStringArray(R.array.menu_names);
        return  new ArrayList<String>(Arrays.asList(drawerListTitles));
    }

    @Override
    public void selectActionBarTitle(int position) {
        drawerListTitles = context.getResources().getStringArray(R.array.menu_names);
//        Log.d("!!!",drawerListTitles[position]+"->"+position);
        if (position == 999)
            view.setActionBarTitle("Python Samples");
        else
            view.setActionBarTitle(drawerListTitles[position]);
    }

//    @Override
//    public void selectFragment(int itemId) {
//        int fNumber = 0;
//        Log.d("!!!",itemId+"");
//        switch (itemId){
//            case R.id.strings:
//                fragment = MainFragment.newInstance(Const.MENU_STRINGS);
//                break;
//            case R.id.dictionaries:
//                fragment = MainFragment.newInstance(Const.MENU_DICTIONARIES);
//                fNumber = 1;
//                break;
//            case R.id.exeption:
//                fragment = MainFragment.newInstance(Const.MENU_EXCEPTIONS);
//                fNumber = 2;
//                break;
//            case R.id.files:
//                fragment = MainFragment.newInstance(Const.MENU_FILES);
//                fNumber = 3;
//                break;
//            case R.id.loops:
//                fragment = MainFragment.newInstance(Const.MENU_LOOPS);
//                fNumber = 4;
//                break;
//            case R.id.function:
//                fragment = MainFragment.newInstance(Const.MENU_FUNCTIONS);
//                fNumber = 5;
//                break;
//            case R.id.generators:
//                fragment = MainFragment.newInstance(Const.MENU_GENERATORS);
//                fNumber = 6;
//                break;
//            case R.id.conditions:
//                fragment = MainFragment.newInstance(Const.MENU_CONDITIONS);
//                fNumber = 7;
//                break;
//            case R.id.lists:
//                fragment = MainFragment.newInstance(Const.MENU_LISTS);
//                fNumber = 8;
//                break;
//            case R.id.oop:
//                fragment = MainFragment.newInstance(Const.MENU_OOP);
//                fNumber = 9;
//                break;
//            case R.id.time:
//                fragment = MainFragment.newInstance(Const.MENU_TIME);
//                fNumber = 10;
//                break;
//            case R.id.tuples:
//                fragment = MainFragment.newInstance(Const.MENU_TUPLES);
//                fNumber = 11;
//                break;
//            case R.id.sorting:
//                fragment = MainFragment.newInstance(Const.MENU_SORTING);
//                fNumber = 12;
//                break;
//            case R.id.utils:
//                fragment = MainFragment.newInstance(Const.MENU_UTILS);
//                fNumber = 13;
//                break;
//        }
//        Log.d("!!!",fragment.toString());
//        selectActionBarTitle(fNumber);
//        view.setActiveFragment(fragment);
//        view.setActionBarTitle(drawerListTitles[fNumber]);
//    }
@Override
public void selectFragment(int itemId) {
//    Log.d("!!!",itemId+"");
    switch (itemId){
        case 0:
            fragment = MainFragment.newInstance(Const.MENU_STRINGS);
            break;
        case 1:
            fragment = MainFragment.newInstance(Const.MENU_DICTIONARIES);
            break;
        case 2:
            fragment = MainFragment.newInstance(Const.MENU_EXCEPTIONS);
            break;
        case 3:
            fragment = MainFragment.newInstance(Const.MENU_FILES);
            break;
        case 4:
            fragment = MainFragment.newInstance(Const.MENU_LOOPS);
            break;
        case 5:
            fragment = MainFragment.newInstance(Const.MENU_FUNCTIONS);
            break;
        case 6:
            fragment = MainFragment.newInstance(Const.MENU_GENERATORS);
            break;
        case 7:
            fragment = MainFragment.newInstance(Const.MENU_CONDITIONS);
            break;
        case 8:
            fragment = MainFragment.newInstance(Const.MENU_LISTS);
            break;
        case 9:
            fragment = MainFragment.newInstance(Const.MENU_OOP);
            break;
        case 10:
            fragment = MainFragment.newInstance(Const.MENU_TIME);
            break;
        case 11:
            fragment = MainFragment.newInstance(Const.MENU_TUPLES);
            break;
        case 12:
            fragment = MainFragment.newInstance(Const.MENU_SORTING);
            break;
        case 13:
            fragment = MainFragment.newInstance(Const.MENU_NUMPY);
            break;
        case 14:
            fragment = MainFragment.newInstance(Const.MENU_PANDAS);
            break;
        case 15:
            fragment = MainFragment.newInstance(Const.MENU_UTILS);
            break;
    }

    selectActionBarTitle(itemId);
    view.setActiveFragment(fragment);
    view.setActionBarTitle(drawerListTitles[itemId]);
}

    @Override
    public void destroy() {
        this.view = null;
    }
}
