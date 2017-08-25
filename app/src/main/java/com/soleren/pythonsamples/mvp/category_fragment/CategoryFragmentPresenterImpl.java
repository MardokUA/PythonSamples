package com.soleren.pythonsamples.mvp.category_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.soleren.pythonsamples.main_activity.MainActivity;
import com.soleren.pythonsamples.application.PythonSamples;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.fragments.ItemFragment;
import com.soleren.pythonsamples.model.Item;
import com.soleren.pythonsamples.mvp.base.BasePresenterAdapter;
import com.soleren.pythonsamples.utils.XMLParser;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by den on 2017-05-23.
 */

public class CategoryFragmentPresenterImpl extends BasePresenterAdapter implements CategoryFragmentContract.Presenter {
    private CategoryFragmentContract.View view;
    private MainActivity activity;
    private Fragment fragment;
    private ArrayList<Item> items;
    private String title;


    public CategoryFragmentPresenterImpl(Fragment fragment) {
        this.view = (CategoryFragmentContract.View) fragment;
        this.activity = (MainActivity) fragment.getActivity();
    }


    @Override
    public void selectTitle(Bundle bundle) {
        if (bundle != null) {
            title = bundle.getString(Const.CATEGORY_FRAGMENT_NAME);
        } else {
            title = (String) activity.getSupportActionBar().getTitle();
        }
        view.setTitle(title);
    }


    @Override
    public void selectTitle(int res) {
        title = PythonSamples.getAppContext().getResources().getString(res);
        view.setTitle(title);
    }


    @Override
    public ArrayList<String> getListTitles(String fragmentName) {
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
        return XMLParser.getXmlParser(res).parse();
    }

    @Override
    public ArrayList<String> getTitlesFromXML(int res) {
        items = getItemsFromXML(res);
        ArrayList<String> strings = new ArrayList<>();
        for (Item item : items) {
            if ((Locale.getDefault().toString()).equals("en_US"))
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
