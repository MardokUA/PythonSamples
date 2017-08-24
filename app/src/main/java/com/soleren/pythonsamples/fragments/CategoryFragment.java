package com.soleren.pythonsamples.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.activities.MainActivity;
import com.soleren.pythonsamples.adapters.MainAdapter;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.mvp.category_fragment.CategoryFragmentContract;
import com.soleren.pythonsamples.mvp.category_fragment.CategoryFragmentPresenterImpl;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements CategoryFragmentContract.View {

    private static final String FRAGMENT_NAME = "Name";
    private static final String FRAGMENT_TITLE = "Title";

    private ArrayList<String> listTitles;
    private MainAdapter adapter;
    private CategoryFragmentPresenterImpl presenter;
    private String fragmentName, title;
    private Bundle bundle;
    private AdView adView;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(String fragmentName, int title) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(FRAGMENT_NAME, fragmentName);
        args.putInt(FRAGMENT_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new CategoryFragmentPresenterImpl(this);
        if (getArguments() != null) {
            fragmentName = getArguments().getString(FRAGMENT_NAME);
            int title = getArguments().getInt(FRAGMENT_TITLE);
            listTitles = presenter.getListTitles(fragmentName);
            presenter.selectTitle(title);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null)
            adView.resume();
        fragmentName = getArguments().getString(FRAGMENT_NAME);
        presenter = new CategoryFragmentPresenterImpl(this);
        listTitles = presenter.getListTitles(fragmentName);
        presenter.selectTitle(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        RecyclerView root = (RecyclerView) view.findViewById(R.id.fragment_category_recycler);

        adapter = new MainAdapter(listTitles);
        root.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        root.setLayoutManager(manager);

//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView = (AdView)view.findViewById(R.id.adView);
//        adView.loadAd(adRequest);

        adapter.setListener(new MainAdapter.AdapterListener() {
            @Override
            public void onClick(int position) {
                if (adView != null)
                    adView.destroy();
                presenter.selectFragment(position);
            }
        });
        return view;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
        if (((MainActivity) getActivity()).getSupportActionBar() != null) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void setFragment(MainActivity activity, Fragment fragment) {
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adView != null)
            adView.pause();
        bundle = new Bundle();
        bundle.putString(Const.CATEGORY_FRAGMENT_NAME, title);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.destroy();
        if (adView != null)
            adView.destroy();
    }
}
