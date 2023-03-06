package com.example.firebase_upload_and_download_images;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageHolder extends RecyclerView.ViewHolder {

    ImageView igView;
    Button delete;

    public ImageHolder(@NonNull View itemView) {
        super(itemView);
        igView = itemView.findViewById(R.id.imgLoader);
        delete = itemView.findViewById(R.id.deleteButton);
    }
}
