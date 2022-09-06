package com.amazon.alexa.font;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes8.dex */
public class FontTextView extends TextView {
    public FontTextView(Context context) {
        super(context);
    }

    @VisibleForTesting
    void applyFontFromAttributes(Context context, AttributeSet attributeSet) {
        super.setTypeface(FontResolver.getFont(context, Font.fromInt(context.obtainStyledAttributes(attributeSet, R.styleable.FontTextView).getInt(R.styleable.FontTextView_fontName, Font.AMAZON_EMBER_REGULAR.getValue()))));
    }

    public FontTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        applyFontFromAttributes(context, attributeSet);
    }

    public FontTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        applyFontFromAttributes(context, attributeSet);
    }

    public FontTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        applyFontFromAttributes(context, attributeSet);
    }
}
