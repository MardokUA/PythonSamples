package com.soleren.pythonsamples.mvp.category_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.activities.MainActivity;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.fragments.ItemFragment;
import com.soleren.pythonsamples.model.Item;
import com.soleren.pythonsamples.utils.XMLParser;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by den on 2017-05-23.
 */

public class CategoryFragmentPresenterImpl implements CategoryFragmentPresenter {
    private CategoryFragmentView view;
    private MainActivity activity;
    private Fragment fragment;
    private Context context;
    private ArrayList<Item> items;
    private String title;


    public CategoryFragmentPresenterImpl(Fragment fragment) {
        this.view = (CategoryFragmentView) fragment;
        this.activity = (MainActivity) fragment.getActivity();
        this.context = (Context) fragment.getContext();
    }


    @Override
    public void selectTitle(Bundle bundle) {
        if (bundle != null)
            title = bundle.getString(Const.CATEEGORY_FRAGMENT_NAME);
        else
            title = (String) activity.getSupportActionBar().getTitle();

        view.setTitle(title);
    }


    @Override
    public void selectTitle(int res) {
        title = context.getResources().getString(res);
        view.setTitle(title);
    }


    @Override
    public ArrayList<String> getListTitles(String fragmentName) {
        switch (fragmentName) {
            case Const.MENU_STRING_NOTES:
                return getTitlesFromXML(R.xml.string_notes);

            case Const.MENU_STRING_OPERATIONS:
                return getTitlesFromXML(R.xml.strings_operations);

            case Const.MENU_DICTIONARIES_NOTES:
                return getTitlesFromXML(R.xml.dictionary_notes);

            case Const.MENU_DICTIONARIES_OPERATIONS:
                return getTitlesFromXML(R.xml.dictionary_operations);

            case Const.MENU_EXCEPTIONS_NOTES:
                return getTitlesFromXML(R.xml.exception_notes);

            case Const.MENU_EXCEPTIONS_OPERATIONS:
                return getTitlesFromXML(R.xml.exception_operations);

            case Const.MENU_FILES_NOTES:
                return getTitlesFromXML(R.xml.files_notes);

            case Const.MENU_FILES_OPERATIONS:
                return getTitlesFromXML(R.xml.files_operations);

            case Const.MENU_FILES_EXT_OPERATIONS:
                return getTitlesFromXML(R.xml.files_ext_operations);

            case Const.MENU_LOOPS_NOTES:
                return getTitlesFromXML(R.xml.loop_notes);

            case Const.MENU_LOOPS_OPERATIONS:
                return getTitlesFromXML(R.xml.loop_operations);

            case Const.MENU_FUNCTIONS_NOTES:
                return getTitlesFromXML(R.xml.function_notes);

            case Const.MENU_FUNCTIONS_OPERATIONS:
                return getTitlesFromXML(R.xml.function_operations);

            case Const.MENU_GENERATORS_OPERATIONS:
                return getTitlesFromXML(R.xml.generators_operations);

            case Const.MENU_CONDITIONS_OPERATIONS:
                return getTitlesFromXML(R.xml.condition_operations);

            case Const.MENU_LISTS_NOTES:
                return getTitlesFromXML(R.xml.list_notes);

            case Const.MENU_LISTS_OPERATIONS:
                return getTitlesFromXML(R.xml.list_operations);

            case Const.MENU_OOP_NOTES:
                return getTitlesFromXML(R.xml.oop_notes);

            case Const.MENU_OOP_OPERATIONS:
                return getTitlesFromXML(R.xml.oop_operations);

            case Const.MENU_OOP_PATTERNS:
                return getTitlesFromXML(R.xml.oop_patterns);

            case Const.MENU_OOP_RELOADING:
                return getTitlesFromXML(R.xml.oop_reloading_operators);

            case Const.MENU_TIME_NOTES:
                return getTitlesFromXML(R.xml.time_notes);

            case Const.MENU_TIME_OPERATIONS:
                return getTitlesFromXML(R.xml.time);

            case Const.MENU_TUPLES_NOTES:
                return getTitlesFromXML(R.xml.tuple_notes);

            case Const.MENU_TUPLES_OPERATIONS:
                return getTitlesFromXML(R.xml.tuple_operations);

            case Const.MENU_SORTING_OPERATIONS:
                return getTitlesFromXML(R.xml.sorting);

            case Const.MENU_NUMPY_NOTES:
                return getTitlesFromXML(R.xml.numpy_notes);

            case Const.MENU_NUMPY_OPERATIONS:
                return getTitlesFromXML(R.xml.numpy_operations);

            case Const.MENU_NUMPY_EXT_OPERATIONS:
                return getTitlesFromXML(R.xml.numpy_ext_operations);

            case Const.MENU_PANDAS_NOTES:
                return getTitlesFromXML(R.xml.pandas_notes);

            case Const.MENU_PANDAS_CHEATS:
                return getTitlesFromXML(R.xml.pandas_cheats);

            case Const.MENU_PANDAS_OPERATIONS:
                return getTitlesFromXML(R.xml.pandas_operations);

            case Const.MENU_PANDAS_FILES_OPERATIONS:
                return getTitlesFromXML(R.xml.pandas_files_operations);

            case Const.MENU_PANDAS_GROUP_OPERATIONS:
                return getTitlesFromXML(R.xml.pandas_group_operations);

            case Const.MENU_PANDAS_MERGE_OPERATIONS:
                return getTitlesFromXML(R.xml.pandas_merge_operations);

            case Const.MENU_UTILS_OPERATIONS:
                return getTitlesFromXML(R.xml.utils);

        }
        return null;
    }


    @Override
    public void selectFragment(int position) {
        fragment = ItemFragment.newInstance(items.get(position));
        if (items.get(position).getContent() != null) {
            view.setTitle(title);
            view.setFragment(activity, fragment);
        }
    }


    @Override
    public ArrayList<Item> getItemsFromXML(int res) {
        return XMLParser.getXmlParser((Context) activity, res).parse();
    }


    @Override
    public ArrayList<String> getTitlesFromXML(int res) {
        items = getItemsFromXML(res);
        ArrayList<String> strings = new ArrayList<>();
        for (Item item : items) {
            if((Locale.getDefault().toString()).equals("en_US"))
                strings.add(item.getTitle());
            else
                strings.add(item.getTitle());
        }
        return strings;
    }

    @Override
    public void destroy() {
        this.view = null;
    }
}
