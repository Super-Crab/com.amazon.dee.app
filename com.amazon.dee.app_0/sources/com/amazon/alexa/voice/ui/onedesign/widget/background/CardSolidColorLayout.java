package com.amazon.alexa.voice.ui.onedesign.widget.background;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public class CardSolidColorLayout extends CardBaseLayout implements CardBackgroundColorLayout {
    private int backgroundColor;

    public CardSolidColorLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.CardLayout, 0, 0);
        this.backgroundColor = obtainStyledAttributes.getColor(R.styleable.CardLayout_backgroundColor, ContextCompat.getColor(context, R.color.voice_ui_od_gradient_background_base));
        obtainStyledAttributes.recycle();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.background.CardBaseLayout
    @NonNull
    protected Bitmap createImageBitmap(int i, int i2, int i3, int i4) {
        Bitmap createBitmap = Bitmap.createBitmap(i3 - i, i4 - i2, Bitmap.Config.ARGB_8888);
        createBitmap.eraseColor(this.backgroundColor);
        return createBitmap;
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
}
