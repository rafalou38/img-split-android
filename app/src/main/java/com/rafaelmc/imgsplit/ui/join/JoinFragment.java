package com.rafaelmc.imgsplit.ui.join;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class JoinFragment extends Fragment {

    private FragmentJoinBinding binding;
    private JoinListFragment joinList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentJoinBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        joinList = (JoinListFragment) getChildFragmentManager().findFragmentById(R.id.orderList);


        binding.choose.setOnClickListener(view -> chooseImages());
        binding.sort.setOnClickListener(view -> sortFiles());
        binding.join.setOnClickListener(view -> joinFiles());


        return root;
    }

    private void joinFiles(){
        try {

            int totalHeight = 0;
            int minWidth = 1000000000;
            for (JoinElem elem: joinList.elements) {
                elem.bitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), elem.imageUri);
                totalHeight += elem.bitmap.getHeight();
                if(elem.bitmap.getWidth() < minWidth){
                    minWidth = elem.bitmap.getWidth();
                }
            }

            Bitmap.Config conf = Bitmap.Config.RGB_565;
            Bitmap result = Bitmap.createBitmap(minWidth, totalHeight, conf);
            Canvas canvas = new Canvas(result);

            int currentHeight = 0;
            for (JoinElem elem: joinList.elements) {
                int height = (elem.bitmap.getHeight() * minWidth) / elem.bitmap.getWidth();
                Rect src = new Rect(0,0,elem.bitmap.getWidth(),elem.bitmap.getHeight());
                Rect dest = new Rect(0,currentHeight,elem.bitmap.getWidth(),currentHeight+height);

                canvas.drawBitmap(elem.bitmap, src, dest, new Paint());

                currentHeight += height;
            }


            File path = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS);
            path.mkdirs();

            String currentDate = new SimpleDateFormat("dd-MM-yyyy-HH.mm.ss", Locale.getDefault()).format(new Date());
            String targetFileName = "join-" + currentDate + ".jpg";
            File file = new File(path, targetFileName);


            OutputStream fOut = new FileOutputStream(file);
            if (!result.compress(Bitmap.CompressFormat.JPEG, 90, fOut)) {
                Log.e("Log", "error while saving bitmap " + file.getCanonicalPath());
                this.binding.output.setText("Il y a eu une erreur.");
            }else {
                this.binding.join.setText("DONE");
                this.binding.output.setText(file.getCanonicalPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sortFiles() {
        joinList.sort();
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