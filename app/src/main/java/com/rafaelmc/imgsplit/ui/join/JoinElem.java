package com.rafaelmc.imgsplit.ui.join;

import android.graphics.Bitmap;
import android.net.Uri;

public class JoinElem {
    Uri imageUri = null;
    String filename = null;
    Bitmap bitmap = null;

    public JoinElem(Uri uri, String name){
        imageUri = uri;
        filename = name;
    }
}
