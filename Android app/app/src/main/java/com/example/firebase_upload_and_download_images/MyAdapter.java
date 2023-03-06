package com.example.firebase_upload_and_download_images;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<ImageHolder> {


    private List<Model> imgList;
    private Context context;

    public MyAdapter(Context context, List<Model> imgList){
        this.context = context;
        this.imgList = imgList;
    }


    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ImageHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        Model model = imgList.get(position);
        Picasso.get().load(model.getImageUrl()).into(holder.igView);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase fDatabase = FirebaseDatabase.getInstance();
                DatabaseReference dRef = fDatabase.getReference("Image").child(model.getKey());
                dRef.removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if (ref == null){
                            Toast.makeText(context, "Delete: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }else {
                            FirebaseStorage fStore = FirebaseStorage.getInstance();
                            StorageReference sRef = fStore.getReference().child(model.getStorageKey());
                            sRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    int pos = imgList.indexOf(model);
                                    imgList.remove(pos);
                                    notifyItemRemoved(pos);
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }
}
