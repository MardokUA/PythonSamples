package com.soleren.pythonsamples.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.activities.MainActivity;
import com.soleren.pythonsamples.databinding.FragmentItemBinding;
import com.soleren.pythonsamples.model.Item;
import com.soleren.pythonsamples.mvp.item_fragment.ItemFragmentPresenterImpl;
import com.soleren.pythonsamples.mvp.item_fragment.ItemFragmentView;
import com.google.android.gms.ads.AdRequest;


public class ItemFragment extends Fragment implements ItemFragmentView{

    private static final String ARG_PARAM1 = "Name";
    private ItemFragmentPresenterImpl presenter;
    private FragmentItemBinding binding;
    private Item item;
    private ShareActionProvider shareActionProvider = null;
    private String content;
    private boolean isScreenOn = false;
    public ItemFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }


    public static ItemFragment newInstance(Item item) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(binding.adView != null)
            binding.adView.resume();
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ItemFragmentPresenterImpl(this);
        if (getArguments() != null) {
            item = getArguments().getParcelable(ARG_PARAM1);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_item,container,false);

        presenter.getItem(item);

//        AdRequest adRequest = new AdRequest.Builder().build();
//        binding.adView.loadAd(adRequest);


        return binding.getRoot();
    }

    @Override
    public void showData(String content, String print) {
        this.content = content;
        binding.content.setText(content);
        binding.print.setText(print);
    }

    @Override
    public void setTitle(String title) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_share, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
//        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        shareActionProvider = new ShareActionProvider(getActivity()){
            @Override
            public View onCreateActionView() {
                return null;
            }
        };

        MenuItemCompat.setActionProvider(menuItem,shareActionProvider);
        presenter.makeIntent(shareActionProvider,content);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(binding.adView != null)
            binding.adView.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.destroy();
        if(binding.adView != null)
            binding.adView.destroy();
    }
}
