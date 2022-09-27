package com.rafaelmc.imgsplit.ui.join;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
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
        holder.mFileNameView.setText(mValues.get(position).filename);

        if (position == 0){
            holder.binding.btnUp.setEnabled(false);
        }else{
            holder.binding.btnUp.setEnabled(true);
            holder.binding.btnUp.setOnClickListener(view -> move(position, position-1));
        }

        if (position == mValues.size()-1){
            holder.binding.btnDown.setEnabled(false);
        }else{
            holder.binding.btnDown.setEnabled(true);
            holder.binding.btnDown.setOnClickListener(view -> move(position, position+1));
        }
    }

    private void move(int from, int to){
        JoinElem temp = mValues.get(from);
        mValues.set(from, mValues.get(to));
        mValues.set(to, temp);


        notifyItemRangeChanged(Math.min(from, to), 2);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        public final TextView mIdView;
//        public final TextView mContentView;
        public final ImageView mImageView;
        public final TextView mFileNameView;
        public final FragmentJoinElementBinding binding;
        public JoinElem mItem;

        public ViewHolder(FragmentJoinElementBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

//            mIdView = binding.itemNumber;
//            mContentView = binding.content;
            mImageView = binding.image;
            mFileNameView = binding.fileName;

        }
    }
}