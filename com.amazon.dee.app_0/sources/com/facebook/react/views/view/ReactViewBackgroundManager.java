package com.facebook.react.views.view;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
/* loaded from: classes2.dex */
public class ReactViewBackgroundManager {
    @Nullable
    private ReactViewBackgroundDrawable mReactBackgroundDrawable;
    private View mView;

    public ReactViewBackgroundManager(View view) {
        this.mView = view;
    }

    private ReactViewBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mReactBackgroundDrawable == null) {
            this.mReactBackgroundDrawable = new ReactViewBackgroundDrawable(this.mView.getContext());
            Drawable background = this.mView.getBackground();
            ViewCompat.setBackground(this.mView, null);
            if (background == null) {
                ViewCompat.setBackground(this.mView, this.mReactBackgroundDrawable);
            } else {
                ViewCompat.setBackground(this.mView, new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, background}));
            }
        }
        return this.mReactBackgroundDrawable;
    }

    public void setBackgroundColor(int i) {
        if (i == 0 && this.mReactBackgroundDrawable == null) {
            return;
        }
        getOrCreateReactViewBackground().setColor(i);
    }

    public void setBorderColor(int i, float f, float f2) {
        getOrCreateReactViewBackground().setBorderColor(i, f, f2);
    }

    public void setBorderRadius(float f) {
        getOrCreateReactViewBackground().setRadius(f);
    }

    public void setBorderStyle(@Nullable String str) {
        getOrCreateReactViewBackground().setBorderStyle(str);
    }

    public void setBorderWidth(int i, float f) {
        getOrCreateReactViewBackground().setBorderWidth(i, f);
    }

    public void setBorderRadius(float f, int i) {
        getOrCreateReactViewBackground().setRadius(f, i);
    }
}
