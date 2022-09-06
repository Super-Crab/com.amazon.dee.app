package com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.privacy;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
/* loaded from: classes8.dex */
class EnrollmentThemeUtil {
    private static final String TAG = "EnrollmentThemeUtil";

    private static int getColorFromAttribute(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (context == null || context.getTheme() == null || !context.getTheme().resolveAttribute(i, typedValue, true)) {
            return 0;
        }
        return typedValue.data;
    }

    public void setBackgroundColorToView(Context context, int i, View... viewArr) {
        for (View view : viewArr) {
            if (view != null) {
                view.setBackgroundColor(getColorFromAttribute(context, i));
            }
        }
    }

    public void setBackgroundToView(Context context, int i, View... viewArr) {
        for (View view : viewArr) {
            if (view != null) {
                view.setBackground(ContextCompat.getDrawable(context, i));
            }
        }
    }

    public void setTextColor(Context context, int i, TextView... textViewArr) {
        for (TextView textView : textViewArr) {
            if (textView != null) {
                textView.setTextColor(getColorFromAttribute(context, i));
            }
        }
    }

    public void setTintColorToImageView(ImageView imageView, Context context, int i) {
        imageView.setColorFilter(getColorFromAttribute(context, i));
    }
}
