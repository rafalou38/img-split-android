package com.rafaelmc.imgsplit.ui.join;

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
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)  {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == -1) {
            if (requestCode == 1) {
                Uri selected = data.getData();
                if (null != selected) {
//                    Log.d("Photopicker", getFileName(selected));
//                    selectedUri = selected;
//                    binding.imageView.setImageURI(selected);
//                    binding.slider.setEnabled(true);
//                    binding.processButton.setEnabled(true);
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