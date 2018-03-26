package com.example.mentorship;

import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

/**
 * Created by naimish on 23/1/18.
 */

public class AppConstants {

    public static final String BASE_URL = "https://pixabay.com";
    public static final String API_KEY = "7807108-20c2f5c144edb697185be04b3";

    public static AnimationSet generateFadeInAnimator() {
        AnimationSet set = new AnimationSet(true);
        Animation trAnimation = new TranslateAnimation(0, 0, -100, 0);
        trAnimation.setDuration(200);
        set.addAnimation(trAnimation);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(200);
        set.addAnimation(anim);
        return set;
    }
    public static AnimationSet generateFadeOutAnimator() {
        AnimationSet set = new AnimationSet(true);
        Animation trAnimation = new TranslateAnimation(0, 0, 0, 100);
        trAnimation.setDuration(200);
        set.addAnimation(trAnimation);
        Animation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(200);
        set.addAnimation(anim);
        return set;
    }
}
