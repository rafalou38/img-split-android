package com.rafaelmc.imgsplit.ui.cut;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rafaelmc.imgsplit.databinding.FragmentCutBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CutFragment extends Fragment {

    private FragmentCutBinding binding;
//    private File selectedImage;
    private Uri selectedUri;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentCutBinding.inflate(inflater, container, false);
        final View root = binding.getRoot();
        binding.imageSelectButton.setOnClickListener(view -> chooseImage(view));
        binding.processButton.setOnClickListener(view -> process());


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    void showInfo(String text){
        TextView displayResult = new TextView(this.getContext());
        displayResult.setText(text);
        binding.resultList.addView(displayResult);
    }

    public void process(){
        showInfo("#############\n START SPLIT \n#############");
        int slices = (int)binding.slider.getValue();
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS);
        path.mkdirs();

        String baseFileName = getFileName(selectedUri);

        try {
            InputStream stream = this.getContext().getContentResolver().openInputStream(selectedUri);
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(stream, false);
            int sliceHeight = decoder.getHeight() / slices;
            int sliceWidth = decoder.getWidth();
            for (int i = 0; i < slices; i++){
                int y = sliceHeight * i;
                Rect rect = new Rect(0, y, sliceWidth, y+sliceHeight);
                Bitmap region = decoder.decodeRegion(rect, null);

                String targetFileName = baseFileName + "_" + (i+1) + "-" + slices +".jpg";
                File file = new File(path, targetFileName);


                OutputStream fOut = new FileOutputStream(file);
                if (!region.compress(Bitmap.CompressFormat.JPEG, 90, fOut)) {
                    Log.e("Log", "error while saving bitmap " + file.getCanonicalPath());
                }

                binding.progressBar.setProgress((i/slices)*100);

                showInfo((i+1)+"/"+slices+": "+  file.getCanonicalPath());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chooseImage(View view) {
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
                    Log.d("Photopicker", getFileName(selected));
                    selectedUri = selected;
                    binding.imageView.setImageURI(selected);
                    binding.slider.setEnabled(true);
                    binding.processButton.setEnabled(true);
                }else{
                    binding.slider.setEnabled(false);
                    binding.processButton.setEnabled(false);
                }
            }
        }
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    result = cursor.getString(index);
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}