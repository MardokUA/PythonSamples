package com.soleren.pythonsamples.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.data.Const;

import java.util.List;

/**
 * Created by den on 2017-05-19.
 * Адаптер для всех фрагментов
 */

public class MainAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<String> mTitles;
    private AdapterListener mListener;
    private int mViewType;

    /**
     * Адаптер создает представление для всех списков.
     * @param titles список отображаемых елементов
     * @param viewType тип xml'ки, который нужно применить к елементу.
     *                 VIEW_TYPE_NOTES - для "Заметок" и "Шпаргалок";
     *                 VIEW_TYPE_CATEGORY - для всех остальных;
     *
     */
    public MainAdapter(List<String> titles, int viewType) {
        mTitles = titles;
        mViewType = viewType;
    }

    @Override
    public int getItemViewType(int position) {
        return mViewType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Const.VIEW_TYPE_NOTES) {
            CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.note_view, parent, false);
            return new NoteViewHolder(view);
        } else {
            LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view, parent, false);
            return new CategoryViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mViewType == Const.VIEW_TYPE_NOTES) {
            fetchNoteData((NoteViewHolder) holder, position);
        } else {
            fetchCategoryData((CategoryViewHolder) holder, position);
        }

    }

    private void fetchCategoryData(CategoryViewHolder holder, int position) {
        final String currentText = mTitles.get(position);
        holder.mTextView.setText(currentText);
        holder.mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(currentText);
                }
            }
        });
    }

    private void fetchNoteData(NoteViewHolder holder, int position) {
        final String currentText = mTitles.get(position);
        holder.mTextView.setText(currentText);
        holder.mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(currentText);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    private class CategoryViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mRootView;
        private TextView mTextView;

        CategoryViewHolder(LinearLayout itemView) {
            super(itemView);
            mRootView = itemView;
            mTextView = (TextView) mRootView.findViewById(R.id.category_item);
        }
    }

    private class NoteViewHolder extends RecyclerView.ViewHolder {
        private CardView mRootView;
        private TextView mTextView;

        NoteViewHolder(View itemView) {
            super(itemView);
            mRootView = (CardView) itemView;
            mTextView = (TextView) mRootView.findViewById(R.id.cv_note_category_item);
        }
    }

    public void setListener(AdapterListener listener) {
        this.mListener = listener;
    }

    public interface AdapterListener {
        void onItemClick(String adapterItemTitle);
    }
}
