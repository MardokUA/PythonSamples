package com.soleren.pythonsamples.mvp.main_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.main_activity.MainActivity;
import com.soleren.pythonsamples.application.PythonSamples;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.fragments.CategoryFragment;
import com.soleren.pythonsamples.model.Item;
import com.soleren.pythonsamples.mvp.base.BasePresenterAdapter;
import com.soleren.pythonsamples.utils.XMLParser;

import java.util.ArrayList;
import java.util.Arrays;

public class MainFragmentPresenterImpl extends BasePresenterAdapter implements MainFragmentContract.Presenter {

    private MainFragmentContract.View view;
    private MainActivity activity;
    private Fragment fragment;


    public MainFragmentPresenterImpl(Fragment fragment) {
        this.view = (MainFragmentContract.View) fragment;
        this.activity = (MainActivity) fragment.getActivity();
    }

    @Override
    public ArrayList<String> getListTitles(String fragmentName) {
        ArrayList<String> titlesList;
        String[] arr;

        switch (fragmentName) {
            case Const.MENU_STRINGS:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.strings_menu_names);
                break;
            case Const.MENU_DICTIONARIES:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.dic_menu_names);
                break;
            case Const.MENU_EXCEPTIONS:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.exept_menu_names);
                break;
            case Const.MENU_FILES:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.file_menu_names);
                break;
            case Const.MENU_LOOPS:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.loop_menu_names);
                break;
            case Const.MENU_FUNCTIONS:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.func_menu_names);
                break;
            case Const.MENU_GENERATORS:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.gen_menu_names);
                break;
            case Const.MENU_CONDITIONS:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.cond_menu_names);
                break;
            case Const.MENU_LISTS:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.list_menu_names);
                break;
            case Const.MENU_OOP:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.oop_menu_names);
                break;
            case Const.MENU_TIME:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.time_menu_names);
                break;
            case Const.MENU_TUPLES:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.tup_menu_names);
                break;
            case Const.MENU_SORTING:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.sort_menu_names);
                break;
            case Const.MENU_NUMPY:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.numpy_menu_names);
                break;
            case Const.MENU_PANDAS:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.pandas_menu_names);
                break;
            case Const.MENU_UTILS:
                arr = PythonSamples.getAppContext().getResources().getStringArray(R.array.util_menu_names);
                break;
            default:
                return null;
        }

        titlesList = new ArrayList<>(Arrays.asList(arr));
        return titlesList;
    }

    @Override
    public void selectTitle(Bundle bundle) {
        String title = null;
        if (bundle != null) {
            title = bundle.getString(Const.MAIN_FRAGMENT_NAME);
        } else {
            if (activity.getSupportActionBar() != null) {
                title = (String) activity.getSupportActionBar().getTitle();
            }
        }
        view.setTitle(title);
    }

    @Override
    public void selectFragment(String fName, int position) {
        switch (fName) {

            case Const.MENU_STRINGS:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_STRING_NOTES, R.string.string_notes);
                else
                    fragment = CategoryFragment.newInstance(Const.MENU_STRING_OPERATIONS, R.string.string_operations);
                break;

            case Const.MENU_DICTIONARIES:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_DICTIONARIES_NOTES, R.string.dic_notes);
                else
                    fragment = CategoryFragment.newInstance(Const.MENU_DICTIONARIES_OPERATIONS, R.string.dic_operations);
                break;

            case Const.MENU_EXCEPTIONS:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_EXCEPTIONS_NOTES, R.string.exept_notes);
                else
                    fragment = CategoryFragment.newInstance(Const.MENU_EXCEPTIONS_OPERATIONS, R.string.exept_operations);
                break;

            case Const.MENU_FILES:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_FILES_NOTES, R.string.file_notes);
                if (position == 1)
                    fragment = CategoryFragment.newInstance(Const.MENU_FILES_OPERATIONS, R.string.file_operations);
                if (position == 2)
                    fragment = CategoryFragment.newInstance(Const.MENU_FILES_EXT_OPERATIONS, R.string.files_ext_operations);
                break;

            case Const.MENU_LOOPS:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_LOOPS_NOTES, R.string.loop_notes);
                else
                    fragment = CategoryFragment.newInstance(Const.MENU_LOOPS_OPERATIONS, R.string.loop_operations);
                break;

            case Const.MENU_FUNCTIONS:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_FUNCTIONS_NOTES, R.string.func_notes);
                else
                    fragment = CategoryFragment.newInstance(Const.MENU_FUNCTIONS_OPERATIONS, R.string.func_operations);
                break;

            case Const.MENU_GENERATORS:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_GENERATORS_OPERATIONS, R.string.gen_operations);
                break;

            case Const.MENU_CONDITIONS:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_CONDITIONS_OPERATIONS, R.string.cond_operations);
                break;

            case Const.MENU_LISTS:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_LISTS_NOTES, R.string.list_notes);
                else
                    fragment = CategoryFragment.newInstance(Const.MENU_LISTS_OPERATIONS, R.string.list_operations);
                break;

            case Const.MENU_OOP:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_OOP_NOTES, R.string.oop_notes);
                else if (position == 1)
                    fragment = CategoryFragment.newInstance(Const.MENU_OOP_OPERATIONS, R.string.oop_operations);
                else if (position == 2)
                    fragment = CategoryFragment.newInstance(Const.MENU_OOP_PATTERNS, R.string.oop_patterns);
                else
                    fragment = CategoryFragment.newInstance(Const.MENU_OOP_RELOADING, R.string.oop_reload);
                break;

            case Const.MENU_TIME:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_TIME_NOTES, R.string.time_notes);
                if (position == 1)
                    fragment = CategoryFragment.newInstance(Const.MENU_TIME_OPERATIONS, R.string.time_operations);
                break;

            case Const.MENU_TUPLES:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_TUPLES_NOTES, R.string.tup_notes);
                else
                    fragment = CategoryFragment.newInstance(Const.MENU_TUPLES_OPERATIONS, R.string.tup_operations);
                break;

            case Const.MENU_SORTING:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_SORTING_OPERATIONS, R.string.sort_operations);
                break;

            case Const.MENU_NUMPY:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_NUMPY_NOTES, R.string.numpy_notes);
                else if (position == 1)
                    fragment = CategoryFragment.newInstance(Const.MENU_NUMPY_OPERATIONS, R.string.numpy_operations);
                else
                    fragment = CategoryFragment.newInstance(Const.MENU_NUMPY_EXT_OPERATIONS, R.string.numpy_ext_operations);
                break;

            case Const.MENU_PANDAS:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_PANDAS_NOTES, R.string.pandas_notes);
                else if (position == 1)
                    fragment = CategoryFragment.newInstance(Const.MENU_PANDAS_CHEATS, R.string.pandas_cheats);
                else if (position == 2)
                    fragment = CategoryFragment.newInstance(Const.MENU_PANDAS_OPERATIONS, R.string.pandas_operations);
                else if (position == 3)
                    fragment = CategoryFragment.newInstance(Const.MENU_PANDAS_FILES_OPERATIONS, R.string.pandas_files_operations);
                else if (position == 4)
                    fragment = CategoryFragment.newInstance(Const.MENU_PANDAS_GROUP_OPERATIONS, R.string.pandas_group_operations);
                else
                    fragment = CategoryFragment.newInstance(Const.MENU_PANDAS_MERGE_OPERATIONS, R.string.pandas_merge_operations);
                break;
            case Const.MENU_UTILS:
                if (position == 0)
                    fragment = CategoryFragment.newInstance(Const.MENU_UTILS_OPERATIONS, R.string.util_operations);
                break;
        }
//        switch (position){
//            case 0:
//                fragment = CategoryFragment.newInstance(Const.MENU_STRING_NOTES,R.string.string_notes);
//                break;
//            case 1:
//                fragment = CategoryFragment.newInstance(Const.MENU_STRING_OPERATIONS,R.string.string_operations);
//                break;
//        }
//        Log.d("!!!",view+"");
        view.setFragment(activity, fragment);
    }

    @Override
    public ArrayList<Item> getItemsFromXML(int res) {
        return XMLParser.getXmlParser(res).parse();
    }


    @Override
    public ArrayList<String> getTitlesFromXML(int res) {
        ArrayList<Item> items = getItemsFromXML(res);
        ArrayList<String> strings = new ArrayList<>();
        for (Item item : items) {
            strings.add(item.getTitle());
        }
        return strings;
    }

    @Override
    public void destroy() {
        this.view = null;
    }
}
