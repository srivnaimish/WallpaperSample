package com.example.mentorship.view;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.mentorship.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageActivity extends AppCompatActivity {

    boolean visible=true;
    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.downloads)
    TextView downloads;
    @BindView(R.id.profile_pic)
    ImageView user_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        String URL=getIntent().getStringExtra("URL");
        String Name=getIntent().getStringExtra("Name");
        String Pic=getIntent().getStringExtra("pic");
        String Downloads=String.valueOf(getIntent().getLongExtra("downloads",0));



        URL=URL.replace("_640","_960");

        Glide.with(this)
                .load(URL)
                .asBitmap()
                .placeholder(R.color.cardview_dark_background)
                .fitCenter()
                .into(new BitmapImageViewTarget(imageView){
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        super.onResourceReady(resource, glideAnimation);
                        if(resource!=null){
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
        name.setText(Name);
        downloads.setText(Downloads);
        Glide.with(this)
                .load(Pic)
                .fitCenter()
                .into(user_pic);

    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    private void showSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    public void imageClicked(View view) {
        if(visible){
            hideSystemUI();
            visible=false;
        }else {
            showSystemUI();
            visible=true;
        }
    }


    public void backpress(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
