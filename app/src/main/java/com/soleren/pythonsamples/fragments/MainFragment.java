package com.soleren.pythonsamples.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.main_activity.MainActivity;
import com.soleren.pythonsamples.adapters.MainAdapter;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.databinding.FragmentMainBinding;
import com.soleren.pythonsamples.mvp.main_fragment.MainFragmentContract;
import com.soleren.pythonsamples.mvp.main_fragment.MainFragmentPresenterImpl;

import java.util.ArrayList;

public class MainFragment extends Fragment implements MainFragmentContract.View {

    private FragmentMainBinding binding;
    private ArrayList<String> listTitles;
    private MainAdapter adapter;
    private MainFragmentPresenterImpl presenter;
    private static final String ARG_PARAM1 = "Name";
    private String fragmentName, title;
    private Bundle bundle;
    private FragmentManager fm;

    public MainFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(String fragmentName) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, fragmentName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (binding.adView != null)
            binding.adView.resume();
        fragmentName = getArguments().getString(ARG_PARAM1);
        presenter = new MainFragmentPresenterImpl(this);
        listTitles = presenter.getListTitles(fragmentName);
        presenter.selectTitle(bundle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        presenter = new MainFragmentPresenterImpl(this);
        if (getArguments() != null) {
            fragmentName = getArguments().getString(ARG_PARAM1);
            listTitles = presenter.getListTitles(fragmentName);
        }
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        adapter = new MainAdapter(listTitles);

        binding.mainCategoryRecycler.setAdapter(adapter);


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.mainCategoryRecycler.setLayoutManager(manager);

        adapter.setListener(new MainAdapter.AdapterListener() {
            @Override
            public void onClick(int position) {
                presenter.selectFragment(fragmentName, position);
                if (binding.adView != null)
                    binding.adView.destroy();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    @Override
    public void setFragment(MainActivity activity, Fragment fragment) {
        fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }

    @Override
    public void onPause() {
        super.onPause();
        if (binding.adView != null)
            binding.adView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
        bundle = new Bundle();
        bundle.putString(Const.MAIN_FRAGMENT_NAME, title);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.destroy();
        if (binding.adView != null)
            binding.adView.destroy();
    }
}
