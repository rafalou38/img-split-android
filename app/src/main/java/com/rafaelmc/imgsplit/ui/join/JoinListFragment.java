package com.rafaelmc.imgsplit.ui.join;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rafaelmc.imgsplit.R;

import java.util.ArrayList;
import java.util.List;
//import com.rafaelmc.imgsplit.ui.join.placeholder.PlaceholderContent;

/**
 * A fragment representing a list of Items.
 */
public class JoinListFragment extends Fragment {


    private List<JoinElem> elements;
    private JoinListRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public JoinListFragment() {
        elements = new ArrayList<>();
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static JoinListFragment newInstance(int columnCount) {
        JoinListFragment fragment = new JoinListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_list, container, false);



        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new JoinListRecyclerViewAdapter(elements);
            recyclerView.setAdapter(adapter);

        }
        return view;
    }

    public void addItem(Uri imageUri){
        elements.add(new JoinElem(imageUri));
        adapter.notifyItemInserted(elements.size()-1);
    }
}