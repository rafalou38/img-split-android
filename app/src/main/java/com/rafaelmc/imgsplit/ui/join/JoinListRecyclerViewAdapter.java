package com.rafaelmc.imgsplit.ui.join;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rafaelmc.imgsplit.databinding.FragmentJoinElementBinding;
//import com.rafaelmc.imgsplit.ui.join.placeholder.PlaceholderContent.PlaceholderItem;
//import com.rafaelmc.imgsplit.ui.join.databinding.FragmentJoinElementBinding;

import java.util.ArrayList;
import java.util.List;

public class JoinListRecyclerViewAdapter extends RecyclerView.Adapter<JoinListRecyclerViewAdapter.ViewHolder> {

    private final List<JoinElem> mValues;

    public JoinListRecyclerViewAdapter(List<JoinElem> elements) {
        mValues = elements;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentJoinElementBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
//        holder.mIdView.setText(mValues.get(position).id);
//        holder.mContentView.setText(mValues.get(position).content);
        holder.mImageView.setImageURI(mValues.get(position).imageUri);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        public final TextView mIdView;
//        public final TextView mContentView;
        public final ImageView mImageView;
        public JoinElem mItem;

        public ViewHolder(FragmentJoinElementBinding binding) {
            super(binding.getRoot());

//            mIdView = binding.itemNumber;
//            mContentView = binding.content;
            mImageView = binding.image;
        }
    }
}