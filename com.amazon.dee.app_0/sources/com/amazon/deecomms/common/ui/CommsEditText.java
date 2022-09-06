package com.amazon.deecomms.common.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;
/* loaded from: classes12.dex */
public class CommsEditText extends EditText {
    public CommsEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Typeface typefaceFromAttributes = FontHelper.getTypefaceFromAttributes(context, attributeSet);
        if (typefaceFromAttributes != null) {
            setTypeface(typefaceFromAttributes);
        }
    }
}
