package com.soleren.pythonsamples.mvp.item_fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.ShareActionProvider;
import android.view.WindowManager;

import com.soleren.pythonsamples.model.Item;

import java.util.Locale;

/**
 * Created by den on 2017-05-23.
 */

public class ItemFragmentPresenterImpl implements ItemFragmentPresenter {
    private ItemFragmentView view;

    public ItemFragmentPresenterImpl(ItemFragmentView view) {
        this.view = view;
    }

    @Override
    public void getItem(Item item) {
        String title,content,print;
//        if((Locale.getDefault().toString()).equals("en_US")) {
//            title = item.getTitle();
//            content = item.getContent();
//            print = item.getPrint();
//        }else{
            title = item.getTitle();
            content = item.getContent();
            print = item.getPrint();
//        }
        view.setTitle(title);
        view.showData(content,print);
    }


    @Override
    public void makeIntent(ShareActionProvider provider, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,text);
        provider.setShareIntent(intent);
    }

    @Override
    public void destroy() {
        this.view = null;
    }


}
