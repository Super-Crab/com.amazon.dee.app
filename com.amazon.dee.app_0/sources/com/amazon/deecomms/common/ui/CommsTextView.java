package com.amazon.deecomms.common.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
/* loaded from: classes12.dex */
public class CommsTextView extends TextView {
    public CommsTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Typeface typefaceFromAttributes;
        if (isInEditMode() || (typefaceFromAttributes = FontHelper.getTypefaceFromAttributes(context, attributeSet)) == null) {
            return;
        }
        setTypeface(typefaceFromAttributes);
    }
}
