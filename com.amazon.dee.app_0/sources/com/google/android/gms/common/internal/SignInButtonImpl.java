package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.core.graphics.drawable.DrawableCompat;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.base.R;
import com.google.android.gms.common.util.DeviceProperties;
/* loaded from: classes2.dex */
public final class SignInButtonImpl extends Button {
    public SignInButtonImpl(Context context) {
        this(context, null);
    }

    private static int zaa(int i, int i2, int i3, int i4) {
        if (i != 0) {
            if (i == 1) {
                return i3;
            }
            if (i != 2) {
                throw new IllegalStateException(GeneratedOutlineSupport1.outline27(33, "Unknown color scheme: ", i));
            }
            return i4;
        }
        return i2;
    }

    public final void configure(Resources resources, SignInButtonConfig signInButtonConfig) {
        configure(resources, signInButtonConfig.getButtonSize(), signInButtonConfig.getColorScheme());
    }

    public SignInButtonImpl(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 16842824);
    }

    public final void configure(Resources resources, int i, int i2) {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(14.0f);
        int i3 = (int) ((resources.getDisplayMetrics().density * 48.0f) + 0.5f);
        setMinHeight(i3);
        setMinWidth(i3);
        int i4 = R.drawable.common_google_signin_btn_icon_dark;
        int i5 = R.drawable.common_google_signin_btn_icon_light;
        int zaa = zaa(i2, i4, i5, i5);
        int i6 = R.drawable.common_google_signin_btn_text_dark;
        int i7 = R.drawable.common_google_signin_btn_text_light;
        int zaa2 = zaa(i2, i6, i7, i7);
        if (i == 0 || i == 1) {
            zaa = zaa2;
        } else if (i != 2) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline27(32, "Unknown button size: ", i));
        }
        Drawable wrap = DrawableCompat.wrap(resources.getDrawable(zaa));
        DrawableCompat.setTintList(wrap, resources.getColorStateList(R.color.common_google_signin_btn_tint));
        DrawableCompat.setTintMode(wrap, PorterDuff.Mode.SRC_ATOP);
        setBackgroundDrawable(wrap);
        int i8 = R.color.common_google_signin_btn_text_dark;
        int i9 = R.color.common_google_signin_btn_text_light;
        setTextColor((ColorStateList) Preconditions.checkNotNull(resources.getColorStateList(zaa(i2, i8, i9, i9))));
        if (i == 0) {
            setText(resources.getString(R.string.common_signin_button_text));
        } else if (i == 1) {
            setText(resources.getString(R.string.common_signin_button_text_long));
        } else if (i == 2) {
            setText((CharSequence) null);
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline27(32, "Unknown button size: ", i));
        }
        setTransformationMethod(null);
        if (DeviceProperties.isWearable(getContext())) {
            setGravity(19);
        }
    }
}
