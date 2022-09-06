package com.amazon.alexa.mosaic.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.ImageViewAdapter;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.mosaic.components.ThemeUtil;
/* loaded from: classes9.dex */
public final class ViewUtils {
    private static final String TAG = "ViewUtils";

    private ViewUtils() {
    }

    public static ContextThemeWrapper getThemeWrapper(Context context) {
        return new ContextThemeWrapper(context, R.style.Theme_Mosaic_Jasper);
    }

    public static void setMaxLines(@NonNull View view, int i, int i2) {
        TextView textView = (TextView) view.findViewById(i);
        if (textView == null) {
            String str = TAG;
            LogUtils.e(str, "setMaxLines view is null:" + i);
        } else if (i2 == 0) {
            String str2 = TAG;
            LogUtils.e(str2, "maxLines was given 0 for view:" + i);
        } else {
            textView.setMaxLines(i2);
        }
    }

    public static void updateBackgroundColor(@NonNull View view, int i, int i2) {
        View findViewById = view.findViewById(i);
        if (findViewById == null) {
            String str = TAG;
            LogUtils.e(str, "updateBackgroundColor View is null:" + i);
            return;
        }
        findViewById.setBackgroundColor(i2);
    }

    public static void updateImageColor(@NonNull View view, int i, ColorStateList colorStateList) {
        ImageView imageView = (ImageView) view.findViewById(i);
        if (imageView != null && colorStateList != null) {
            imageView.setImageTintList(colorStateList);
            return;
        }
        String str = TAG;
        LogUtils.e(str, "updateImageColor ImageView is null:" + i + ", or color is null");
    }

    public static void updateImageUrl(@NonNull View view, int i, String str) {
        ImageView imageView = (ImageView) view.findViewById(i);
        if (imageView == null) {
            String str2 = TAG;
            LogUtils.e(str2, "updateImageUrl ImageView is null:" + i);
        } else if (str != null) {
            ImageViewAdapter.setImageUrl(imageView, str);
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
    }

    public static void updateImageView(@NonNull View view, int i, Drawable drawable) {
        ImageView imageView = (ImageView) view.findViewById(i);
        if (imageView == null) {
            String str = TAG;
            LogUtils.e(str, "updateImageView ImageView is null:" + i);
        } else if (drawable != null) {
            imageView.setImageDrawable(drawable);
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
    }

    public static void updateTextClickable(@NonNull View view, int i, boolean z) {
        int i2;
        TextView textView = (TextView) view.findViewById(i);
        if (textView == null) {
            String str = TAG;
            LogUtils.e(str, "updateLinkClickable View is null:" + i);
            return;
        }
        textView.setClickable(z);
        if (z) {
            i2 = R.attr.mosaicAction10;
        } else {
            i2 = R.attr.mosaicNeutral30;
        }
        textView.setTextColor(ThemeUtil.getColorFromAttribute(view.getContext(), i2));
    }

    public static void updateTextColor(@NonNull View view, int i, int i2) {
        TextView textView = (TextView) view.findViewById(i);
        if (textView == null) {
            String str = TAG;
            LogUtils.e(str, "updateTextColor TextView is null:" + i);
            return;
        }
        textView.setTextColor(i2);
    }

    public static void updateTextView(@NonNull View view, int i, String str) {
        TextView textView = (TextView) view.findViewById(i);
        if (textView == null) {
            String str2 = TAG;
            LogUtils.e(str2, "updateTextView TextView is null:" + i);
        } else if (str != null) {
            textView.setText(str);
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
    }

    public static void updateViewContentDescription(@NonNull View view, int i, String str) {
        View findViewById = view.findViewById(i);
        if (findViewById == null) {
            String str2 = TAG;
            LogUtils.e(str2, "updateViewContentDescription view is null:" + i);
            return;
        }
        findViewById.setContentDescription(str);
    }

    public static void updateVisibility(@NonNull View view, int i, Boolean bool) {
        View findViewById = view.findViewById(i);
        if (findViewById == null) {
            String str = TAG;
            LogUtils.e(str, "updateVisibility View is null:" + i);
            return;
        }
        findViewById.setVisibility(bool == Boolean.TRUE ? 0 : 8);
    }

    public static void updateTextView(@NonNull View view, int i, SpannableStringBuilder spannableStringBuilder) {
        TextView textView = (TextView) view.findViewById(i);
        if (textView == null) {
            String str = TAG;
            LogUtils.e(str, "updateTextView TextView is null:" + i);
        } else if (spannableStringBuilder != null) {
            textView.setText(spannableStringBuilder, TextView.BufferType.SPANNABLE);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
    }
}
