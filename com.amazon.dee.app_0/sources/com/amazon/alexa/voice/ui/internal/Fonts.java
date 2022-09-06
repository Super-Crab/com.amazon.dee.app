package com.amazon.alexa.voice.ui.internal;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.amazon.tarazed.ui.FontHelper;
import com.google.android.material.tabs.TabLayout;
import java.io.IOException;
/* loaded from: classes11.dex */
public enum Fonts {
    EMBER_BOLD("ember-bold"),
    EMBER_LIGHT("ember-light"),
    EMBER_MEDIUM("ember-medium"),
    EMBER_REGULAR("ember-regular"),
    BOOKERLY_ITALIC("bookerly-italic"),
    BOOKERLY_REGULAR("bookerly-regular");
    
    private Typeface font;
    private final String name;

    Fonts(String str) {
        this.name = str;
    }

    private void applyToAllTextViews(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                applyToAllTextViews((ViewGroup) childAt);
            } else if (childAt instanceof TextView) {
                apply((TextView) childAt);
            }
        }
    }

    private static String excludeExtension(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 ? str.substring(0, lastIndexOf) : str;
    }

    private static String findPath(AssetManager assetManager, String str, String str2) {
        String[] list;
        try {
            for (String str3 : assetManager.list(str)) {
                if (excludeExtension(str3).equals(str2)) {
                    return str + "/" + str3;
                }
            }
            return null;
        } catch (IOException e) {
            Logger.error("Failed to list " + str, e);
            return null;
        }
    }

    private Typeface getFont(AssetManager assetManager, String str) {
        if (!TextUtils.isEmpty(str)) {
            Typeface typeface = this.font;
            if (typeface != null) {
                return typeface;
            }
            synchronized (this) {
                if (this.font != null) {
                    return this.font;
                }
                String findPath = findPath(assetManager, FontHelper.FONTS_PATH, str);
                if (findPath != null) {
                    this.font = Typeface.createFromAsset(assetManager, findPath);
                    return this.font;
                }
                throw new IllegalArgumentException("Failed to find font asset with name " + str);
            }
        }
        throw new IllegalArgumentException("Font name cannot be null or empty");
    }

    public void apply(@NonNull TextView textView) {
        textView.setTypeface(getFont(textView.getContext().getAssets(), this.name));
    }

    public Typeface getTypeface(Context context) {
        return getFont(context.getAssets(), this.name);
    }

    public void apply(TextView... textViewArr) {
        for (TextView textView : textViewArr) {
            apply(textView);
        }
    }

    public void apply(@NonNull TabLayout tabLayout) {
        applyToAllTextViews(tabLayout);
    }

    public void apply(@NonNull Context context, @NonNull Paint paint) {
        paint.setTypeface(getFont(context.getAssets(), this.name));
    }
}
