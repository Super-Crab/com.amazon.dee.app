package com.amazon.alexa.enrollment.ui.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.AppCompatButton;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class BoldTextButton extends AppCompatButton {
    private static final String TAG = GeneratedOutlineSupport1.outline39(BoldTextButton.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    @VisibleForTesting
    static Typeface defaultTypeface;

    public BoldTextButton(Context context) {
        super(context);
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

    public BoldTextButton(Context context, AttributeSet attributeSet, Typeface typeface) {
        super(context, attributeSet);
        setTypeFace(context, typeface);
    }
}
