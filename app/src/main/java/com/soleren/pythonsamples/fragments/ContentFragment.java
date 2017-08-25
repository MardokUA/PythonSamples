package com.soleren.pythonsamples.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.databinding.FragmentItemBinding;
import com.soleren.pythonsamples.model.Item;

public class ContentFragment extends Fragment {

    private static final String ARG_PARAM1 = "Name";
    private FragmentItemBinding binding;
    private Item item;
    private ShareActionProvider shareActionProvider = null;
    private String content;

    public ContentFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    public static ContentFragment newInstance(Item item) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
//        args.putParcelable(ARG_PARAM1, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (binding.adView != null)
            binding.adView.resume();
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = getArguments().getParcelable(ARG_PARAM1);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item, container, false);

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_share, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = new ShareActionProvider(getActivity()) {
            @Override
            public View onCreateActionView() {
                return null;
            }
        };

        MenuItemCompat.setActionProvider(menuItem, shareActionProvider);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (binding.adView != null)
            binding.adView.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binding.adView != null)
            binding.adView.destroy();
    }
}
