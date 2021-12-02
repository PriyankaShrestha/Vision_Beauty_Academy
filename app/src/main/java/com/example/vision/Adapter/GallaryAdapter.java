package com.example.vision.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vision.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class GallaryAdapter extends RecyclerView.Adapter<GallaryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> images;

    public GallaryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String current = images.get(position);
        Uri myUri = Uri.parse(current);
        Log.i("TAG//////////////", "onBindViewHolder: //////////////////////////////////////////////" + myUri);
        Picasso.with(context).load(myUri).fit().centerCrop().into(holder.imageView);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void setImageItems(ArrayList<String> img) {
        this.images = img;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_uploads);
        }
    }
}
