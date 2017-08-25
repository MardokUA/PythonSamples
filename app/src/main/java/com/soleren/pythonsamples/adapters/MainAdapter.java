package com.soleren.pythonsamples.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soleren.pythonsamples.R;

import java.util.List;

/**
 * Created by den on 2017-05-19.
 * Адаптер для всех фрагментов
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<String> titles;
    private AdapterListener listener;

    public MainAdapter(List<String> titles) {
        this.titles = titles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String currentText = titles.get(position);
        holder.mTextView.setText(currentText);
        holder.mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(currentText);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mRootView;
        private TextView mTextView;

        ViewHolder(LinearLayout itemView) {
            super(itemView);
            mRootView = itemView;
            mTextView = (TextView) mRootView.findViewById(R.id.category_item);
        }
    }

    public void setListener(AdapterListener listener) {
        this.listener = listener;
    }

    public interface AdapterListener {
        void onItemClick(String adapterItemTitle);
    }
}
