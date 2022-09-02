package com.rafaelmc.imgsplit.ui.join;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rafaelmc.imgsplit.R;
import com.rafaelmc.imgsplit.databinding.FragmentJoinBinding;

public class JoinFragment extends Fragment {

    private FragmentJoinBinding binding;
    private JoinListFragment joinList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentJoinBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        joinList = (JoinListFragment) getChildFragmentManager().findFragmentById(R.id.orderList);


        binding.choose.setOnClickListener(view -> chooseImages());


        return root;
    }

    private void chooseImages(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");

        startActivityForResult(Intent.createChooser(intent, "Select split images"), 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)  {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == -1) {
            if (requestCode == 1) {

                Uri selected = data.getData();
                ClipData clipData = data.getClipData();
                if(clipData != null){
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        joinList.addItem(clipData.getItemAt(i).getUri());
                    }
                }else if (selected != null) {
                    joinList.addItem(selected);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}