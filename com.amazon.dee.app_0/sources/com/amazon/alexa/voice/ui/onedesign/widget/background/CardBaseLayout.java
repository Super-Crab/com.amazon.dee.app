package com.amazon.alexa.voice.ui.onedesign.widget.background;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.voice.ui.onedesign.widget.gradient.DefaultGradientCompositor;
import com.amazon.alexa.voice.ui.onedesign.widget.gradient.GradientCompositor;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
/* loaded from: classes11.dex */
public abstract class CardBaseLayout extends FrameLayout {
    private final Glide glide;
    private int gradientBottomColor;
    private final GradientCompositor gradientCompositor;
    private int gradientTopColor;
    private Bitmap image;
    private int roundedCornerRadius;

    public CardBaseLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.gradientCompositor = new DefaultGradientCompositor();
        this.glide = Glide.get(context.getApplicationContext());
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.CardLayout, 0, 0);
        int color = ContextCompat.getColor(context, R.color.voice_ui_od_gradient_background_mask_top);
        int color2 = ContextCompat.getColor(context, R.color.voice_ui_od_gradient_background_mask_bottom);
        this.gradientTopColor = obtainStyledAttributes.getColor(R.styleable.CardLayout_gradientTopColor, color);
        this.gradientBottomColor = obtainStyledAttributes.getColor(R.styleable.CardLayout_gradientBottomColor, color2);
        this.roundedCornerRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardLayout_roundedCornerRadius, 0);
        obtainStyledAttributes.recycle();
    }

    @NonNull
    protected abstract Bitmap createImageBitmap(int i, int i2, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: protected */
    public int getGradientBottomColor() {
        return this.gradientBottomColor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public GradientCompositor getGradientCompositor() {
        return this.gradientCompositor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getGradientTopColor() {
        return this.gradientTopColor;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setBackground(null);
        Bitmap bitmap = this.image;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.image = null;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.image == null || z) {
            this.image = createImageBitmap(i, i2, i3, i4);
            if (this.roundedCornerRadius > 0) {
                Bitmap roundedCorners = TransformationUtils.roundedCorners(this.glide.getBitmapPool(), this.image, this.roundedCornerRadius);
                this.image.recycle();
                this.image = roundedCorners;
            }
            setBackground(new BitmapDrawable(getResources(), this.image));
        }
    }

    public void setGradientTopAndBottomColors(@ColorInt int i, @ColorInt int i2) {
        this.gradientTopColor = i;
        this.gradientBottomColor = i2;
        invalidate();
        requestLayout();
    }

    public void setRoundedCornerRadius(int i) {
        this.roundedCornerRadius = i;
        invalidate();
        requestLayout();
    }
}
