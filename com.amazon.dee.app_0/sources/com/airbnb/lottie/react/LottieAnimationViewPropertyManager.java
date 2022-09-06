package com.airbnb.lottie.react;

import android.graphics.Color;
import android.widget.ImageView;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public class LottieAnimationViewPropertyManager {
    private String animationJson;
    private String animationName;
    private boolean animationNameDirty;
    private ReadableArray colorFilters;
    private Boolean enableMergePaths;
    private String imageAssetsFolder;
    private Boolean loop;
    private Float progress;
    private ImageView.ScaleType scaleType;
    private Float speed;
    private final WeakReference<LottieAnimationView> viewWeakReference;

    public LottieAnimationViewPropertyManager(LottieAnimationView lottieAnimationView) {
        this.viewWeakReference = new WeakReference<>(lottieAnimationView);
    }

    public void commitChanges() {
        LottieAnimationView lottieAnimationView = this.viewWeakReference.get();
        if (lottieAnimationView == null) {
            return;
        }
        String str = this.animationJson;
        if (str != null) {
            lottieAnimationView.setAnimationFromJson(str, Integer.toString(str.hashCode()));
            this.animationJson = null;
        }
        if (this.animationNameDirty) {
            lottieAnimationView.setAnimation(this.animationName);
            this.animationNameDirty = false;
        }
        Float f = this.progress;
        if (f != null) {
            lottieAnimationView.setProgress(f.floatValue());
            this.progress = null;
        }
        Boolean bool = this.loop;
        if (bool != null) {
            lottieAnimationView.setRepeatCount(bool.booleanValue() ? -1 : 0);
            this.loop = null;
        }
        Float f2 = this.speed;
        if (f2 != null) {
            lottieAnimationView.setSpeed(f2.floatValue());
            this.speed = null;
        }
        ImageView.ScaleType scaleType = this.scaleType;
        if (scaleType != null) {
            lottieAnimationView.setScaleType(scaleType);
            this.scaleType = null;
        }
        String str2 = this.imageAssetsFolder;
        if (str2 != null) {
            lottieAnimationView.setImageAssetsFolder(str2);
            this.imageAssetsFolder = null;
        }
        Boolean bool2 = this.enableMergePaths;
        if (bool2 != null) {
            lottieAnimationView.enableMergePathsForKitKatAndAbove(bool2.booleanValue());
            this.enableMergePaths = null;
        }
        ReadableArray readableArray = this.colorFilters;
        if (readableArray == null || readableArray.size() <= 0) {
            return;
        }
        for (int i = 0; i < this.colorFilters.size(); i++) {
            ReadableMap mo6944getMap = this.colorFilters.mo6944getMap(i);
            lottieAnimationView.addValueCallback(new KeyPath(mo6944getMap.getString("keypath"), "**"), (KeyPath) LottieProperty.COLOR_FILTER, (LottieValueCallback<KeyPath>) new LottieValueCallback(new SimpleColorFilter(Color.parseColor(mo6944getMap.getString("color")))));
        }
    }

    public void setAnimationJson(String str) {
        this.animationJson = str;
    }

    public void setAnimationName(String str) {
        this.animationName = str;
        this.animationNameDirty = true;
    }

    public void setColorFilters(ReadableArray readableArray) {
        this.colorFilters = readableArray;
    }

    public void setEnableMergePaths(boolean z) {
        this.enableMergePaths = Boolean.valueOf(z);
    }

    public void setImageAssetsFolder(String str) {
        this.imageAssetsFolder = str;
    }

    public void setLoop(boolean z) {
        this.loop = Boolean.valueOf(z);
    }

    public void setProgress(Float f) {
        this.progress = f;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
    }

    public void setSpeed(float f) {
        this.speed = Float.valueOf(f);
    }
}
