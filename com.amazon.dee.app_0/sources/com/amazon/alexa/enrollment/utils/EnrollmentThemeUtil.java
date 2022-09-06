package com.amazon.alexa.enrollment.utils;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class EnrollmentThemeUtil {
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentThemeUtil.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));

    private static int getColorFromAttribute(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (context == null || context.getTheme() == null || !context.getTheme().resolveAttribute(i, typedValue, true)) {
            return 0;
        }
        return typedValue.data;
    }

    public void setBackgroundColorToView(Context context, int i, View... viewArr) {
        if (viewArr == null) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                view.setBackgroundColor(getColorFromAttribute(context, i));
            }
        }
    }

    public void setBackgroundToView(Context context, int i, View... viewArr) {
        if (viewArr == null) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                view.setBackground(ContextCompat.getDrawable(context, i));
            }
        }
    }

    public void setImage(ImageView imageView, int i) {
        if (imageView == null) {
            return;
        }
        imageView.setImageResource(i);
    }

    public void setTextColor(Context context, int i, TextView... textViewArr) {
        if (textViewArr == null) {
            return;
        }
        for (TextView textView : textViewArr) {
            if (textView != null) {
                textView.setTextColor(getColorFromAttribute(context, i));
            }
        }
    }

    public void setTheme(Context context) {
        Log.i(TAG, "Setting mosaic theme...");
        context.setTheme(R.style.Theme_Mosaic_Jasper);
    }

    public void setTintColorToImageView(ImageView imageView, Context context, int i) {
        if (imageView == null) {
            return;
        }
        imageView.setColorFilter(getColorFromAttribute(context, i));
    }
}
