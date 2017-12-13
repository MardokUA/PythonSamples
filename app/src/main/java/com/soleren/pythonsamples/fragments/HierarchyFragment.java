package com.soleren.pythonsamples.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.fragments.adapter.FragmentAdapter;
import com.soleren.pythonsamples.utils.ContentFactory;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
/**
 * Created by laktionov on 25.08.2017.
 * Основной фрагмент приложения. Родитель для всех остальных фрагметов, кроме {@link ContentFragment}
 */

public abstract class HierarchyFragment extends Fragment implements FragmentAdapter.AdapterListener {

    private static final String TAG = HierarchyFragment.class.getName();

    protected FragmentChangeListener mFragmentChangeListener;
    protected FragmentAdapter mMainAdapter;
    private RecyclerView mRecyclerView;
    private AdView аdView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentChangeListener = (FragmentChangeListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.hierachy_fragment, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.rv_fragment);

        AdRequest adRequest = new AdRequest.Builder().build();
        аdView = (AdView)root.findViewById(R.id.adView);
        аdView.loadAd(adRequest);

        createAdapter();
        initAdapter();
        return root;
    }

    protected void createAdapter() {
        // этот метод должен переопределить каждый фрагмент - наследник
    }

    private void initAdapter() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        mMainAdapter.setListener(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mMainAdapter);
    }

    public void setFragmentChangeListener(FragmentChangeListener fragmentChangeListener) {
        this.mFragmentChangeListener = fragmentChangeListener;
    }

    /**
     * Этот слушатель каждый фрагмент переопределяет солгласно своему поведению
     *
     * @param adapterItemTitle является ключем для контента в {@link ContentFactory}
     */
    @Override
    public void onItemClick(String adapterItemTitle) {
        Log.e(TAG, "onItemClick()");
    }

    /**
     * Слушатель, который устанавливается в каждый фрагмент. Он сообщается активити, что нужно поменять фрагмент;
     */
    public interface FragmentChangeListener {

        void changeCurrentVisibleFragment(String menuTitle, int nextFragment);
    }

    @Override
    public void onPause() {
        if (аdView != null) {
            аdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (аdView != null) {
            аdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (аdView != null) {
            аdView.destroy();
        }
        super.onDestroy();
    }
}
