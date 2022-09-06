package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public class CustomStyleSpan extends MetricAffectingSpan implements ReactSpan {
    private final AssetManager mAssetManager;
    @Nullable
    private final String mFeatureSettings;
    @Nullable
    private final String mFontFamily;
    private final int mStyle;
    private final int mWeight;

    public CustomStyleSpan(int i, int i2, @Nullable String str, @Nullable String str2, @NonNull AssetManager assetManager) {
        this.mStyle = i;
        this.mWeight = i2;
        this.mFeatureSettings = str;
        this.mFontFamily = str2;
        this.mAssetManager = assetManager;
    }

    private static void apply(Paint paint, int i, int i2, @Nullable String str, @Nullable String str2, AssetManager assetManager) {
        Typeface applyStyles = ReactTypefaceUtils.applyStyles(paint.getTypeface(), i, i2, str2, assetManager);
        paint.setFontFeatureSettings(str);
        paint.setTypeface(applyStyles);
        paint.setSubpixelText(true);
    }

    @Nullable
    public String getFontFamily() {
        return this.mFontFamily;
    }

    public int getStyle() {
        int i = this.mStyle;
        if (i == -1) {
            return 0;
        }
        return i;
    }

    public int getWeight() {
        int i = this.mWeight;
        if (i == -1) {
            return 0;
        }
        return i;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        apply(textPaint, this.mStyle, this.mWeight, this.mFeatureSettings, this.mFontFamily, this.mAssetManager);
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(@NonNull TextPaint textPaint) {
        apply(textPaint, this.mStyle, this.mWeight, this.mFeatureSettings, this.mFontFamily, this.mAssetManager);
    }
}
