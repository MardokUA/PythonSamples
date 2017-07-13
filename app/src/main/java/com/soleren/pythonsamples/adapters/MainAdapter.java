package com.soleren.pythonsamples.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soleren.pythonsamples.R;

import java.util.ArrayList;

/**
 * Created by den on 2017-05-19.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList<String> titles;
    private AdapterListener listener;
    public MainAdapter(ArrayList<String> titles) {
        this.titles = titles;
    }

    public void setListener(AdapterListener listener) {
        this.listener = listener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView view = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CardView cardView = holder.view;
        TextView title = (TextView)cardView.findViewById(R.id.category_item);
        title.setText(titles.get(position));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return titles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView view;
        public ViewHolder(CardView itemView) {
            super(itemView);
            view = itemView;
        }
    }
    public interface AdapterListener{
        void onClick(int position);
    }
}
