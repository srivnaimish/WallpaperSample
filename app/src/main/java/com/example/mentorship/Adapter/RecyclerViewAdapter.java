package com.example.mentorship.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.mentorship.R;
import com.example.mentorship.model.Image;
import com.example.mentorship.view.ImageActivity;
import com.example.mentorship.view.ListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by naimish on 24/1/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Image> imageArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Image> imageArrayList) {
        this.context = context;
        this.imageArrayList = imageArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Image image=imageArrayList.get(position);
        final RecyclerViewHolder recyclerViewHolder= (RecyclerViewHolder) holder;

        Log.d("tag",image.getTags());
        String url=image.getWebformatUrl().replace("_640","_150");
        Glide.with(context)
                .load(url)
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(recyclerViewHolder.imageView){
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        super.onResourceReady(resource, glideAnimation);
                        if(resource!=null){
                            recyclerViewHolder.progressBar.setVisibility(View.GONE);
                        }
                    }
                });
        recyclerViewHolder.image=image;
    }

    public void addItems(List<Image> images){
        imageArrayList= (ArrayList<Image>) images;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return imageArrayList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Image image;
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.search_box)
        CardView cardView;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context, ImageActivity.class);
            intent.putExtra("URL",image.getWebformatUrl());
            intent.putExtra("Name",image.getUserName());
            intent.putExtra("pic",image.getUserImageUrl());
            intent.putExtra("downloads",image.getDownloadsCount());

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((ListActivity)context,
                            imageView,
                            ViewCompat.getTransitionName(imageView));
            context.startActivity(intent, options.toBundle());
        }
    }
}
