package com.amazon.alexa.voice.ui.onedesign.widget.background;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.voice.ui.onedesign.widget.gradient.Gradient;
import com.amazon.alexa.voice.ui.onedesign.widget.gradient.GradientBackground;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public class CardLayout extends CardBaseLayout implements CardBackgroundColorLayout {
    private int backgroundColor;
    private Drawable backgroundDrawable;

    public CardLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.CardLayout, 0, 0);
        this.backgroundColor = obtainStyledAttributes.getColor(R.styleable.CardLayout_backgroundColor, ContextCompat.getColor(context, R.color.voice_ui_od_gradient_background_base));
        this.backgroundDrawable = obtainStyledAttributes.getDrawable(R.styleable.CardLayout_src);
        obtainStyledAttributes.recycle();
    }

    private Bitmap createGradientBackground(int i, int i2) {
        return getGradientCompositor().createBitmapWithBackgroundColor(new Gradient.Builder().topColor(getGradientTopColor()).bottomColor(getGradientBottomColor()).width(i).height(i2).build(), this.backgroundColor);
    }

    private Bitmap createGradientBackgroundWithDrawable(int i, int i2) {
        return getGradientCompositor().createBitmapWithBackgroundColorAndDrawable(new Gradient.Builder().topColor(getGradientTopColor()).bottomColor(getGradientBottomColor()).width(i).height(i2).build(), new GradientBackground.Builder().drawable(this.backgroundDrawable).color(this.backgroundColor).build());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.background.CardBaseLayout
    @NonNull
    protected Bitmap createImageBitmap(int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        if (this.backgroundDrawable == null) {
            return createGradientBackground(i5, i6);
        }
        return createGradientBackgroundWithDrawable(i5, i6);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.background.CardBackgroundColorLayout
    @ColorInt
    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    @Override // android.view.View, com.amazon.alexa.voice.ui.onedesign.widget.background.CardBackgroundColorLayout
    public void setBackgroundColor(@ColorInt int i) {
        this.backgroundColor = i;
        invalidate();
        requestLayout();
    }

    public void setBackgroundColorAndDrawable(@ColorInt int i, @Nullable Drawable drawable) {
        this.backgroundColor = i;
        this.backgroundDrawable = drawable;
        invalidate();
        requestLayout();
    }

    public void setBackgroundColorAndDrawable(@ColorRes int i, @DrawableRes int i2) {
        setBackgroundColorAndDrawable(ContextCompat.getColor(getContext(), i), ContextCompat.getDrawable(getContext(), i2));
    }
}
