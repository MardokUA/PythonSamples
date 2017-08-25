package com.soleren.pythonsamples.mvp.item_fragment;

import android.content.Intent;
import android.support.v7.widget.ShareActionProvider;

import com.soleren.pythonsamples.model.Item;
import com.soleren.pythonsamples.mvp.base.BasePresenterAdapter;

public class ItemFragmentPresenterImpl extends BasePresenterAdapter implements ItemFragmentContract.Presenter {

    private ItemFragmentContract.View view;

    public ItemFragmentPresenterImpl(ItemFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void getItem(Item item) {
        String title, content, print;
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
        view.showData(content, print);
    }

    @Override
    public void makeIntent(ShareActionProvider provider, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        provider.setShareIntent(intent);
    }

    @Override
    public void destroy() {
        this.view = null;
    }
}
