package com.amazon.alexa.handsfree.uservoicerecognition.ui.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.AppCompatButton;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class BoldTextButton extends AppCompatButton {
    private static final String TAG = BoldTextButton.class.getSimpleName();
    @VisibleForTesting
    static Typeface defaultTypeface;

    public BoldTextButton(Context context) {
        super(context);
        Log.d(TAG, "Inside one arg constructor, so not setting any fonts");
    }

    private void setTypeFace(Context context, Typeface typeface) {
        if (typeface == null) {
            typeface = FontResolver.getFont(context, Font.AMAZON_EMBER_DISPLAY_BOLD);
        }
        setTypeface(typeface);
    }

    public BoldTextButton(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, defaultTypeface);
    }

    public BoldTextButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, defaultTypeface);
    }

    @VisibleForTesting
    BoldTextButton(Context context, AttributeSet attributeSet, Typeface typeface) {
        super(context, attributeSet);
        setTypeFace(context, typeface);
    }
}
