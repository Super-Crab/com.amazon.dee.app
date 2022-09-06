package com.amazon.alexa.voice.ui.onedesign.util;

import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
/* loaded from: classes11.dex */
public final class ViewUtils {
    private ViewUtils() {
    }

    public static void addStatusBarHeightAsTopPadding(View... viewArr) {
        if (viewArr == null || viewArr.length == 0) {
            return;
        }
        int statusBarHeight = getStatusBarHeight(viewArr[0].getResources());
        for (View view : viewArr) {
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    public static int getStatusBarHeight(android.content.res.Resources resources) {
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static SpannableStringBuilder getStyledSpan(int i, CharSequence charSequence, CharSequence charSequence2) {
        int indexOf;
        if (TextLocaleUtil.isEmpty(charSequence)) {
            return new SpannableStringBuilder();
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        if (TextLocaleUtil.isEmpty(charSequence2) || (indexOf = charSequence.toString().toLowerCase().indexOf(charSequence2.toString().toLowerCase())) < 0) {
            return spannableStringBuilder;
        }
        spannableStringBuilder.setSpan(new StyleSpan(i), indexOf, charSequence2.length() + indexOf, 33);
        return spannableStringBuilder;
    }

    public static void setDrawableOrRemove(ImageView imageView, Drawable drawable) {
        if (drawable == null) {
            imageView.setVisibility(8);
            return;
        }
        imageView.setImageDrawable(drawable);
        imageView.setVisibility(0);
    }

    public static void setTextOrRemove(TextView textView, CharSequence charSequence) {
        setTextOrRemove(textView, charSequence, null);
    }

    public static void setTextOrRemove(TextView textView, CharSequence charSequence, CharSequence charSequence2) {
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
            return;
        }
        textView.setText(charSequence);
        if (!TextUtils.isEmpty(charSequence2)) {
            textView.setContentDescription(charSequence2);
        }
        textView.setVisibility(0);
    }
}
