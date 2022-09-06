package com.amazon.alexa.enrollment.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.WeakHashMap;
/* loaded from: classes7.dex */
public final class FontHelper {
    private static WeakHashMap<String, Typeface> cache = new WeakHashMap<>();
    private static final String TAG = GeneratedOutlineSupport1.outline39(FontHelper.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));

    private FontHelper() {
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
            Log.w(TAG, "Failed to list " + str, e);
            return null;
        }
    }

    public static Typeface getFont(AssetManager assetManager, String str) {
        if (!TextUtils.isEmpty(str)) {
            Typeface typeface = cache.get(str);
            if (typeface != null) {
                return typeface;
            }
            String findPath = findPath(assetManager, com.amazon.tarazed.ui.FontHelper.FONTS_PATH, str);
            GeneratedOutlineSupport1.outline158("Font path: ", findPath);
            if (findPath != null) {
                Typeface createFromAsset = Typeface.createFromAsset(assetManager, findPath);
                cache.put(str, createFromAsset);
                return createFromAsset;
            }
            throw new RuntimeException(GeneratedOutlineSupport1.outline72("Failed to find font asset with name ", str));
        }
        return null;
    }

    public static void setFont(TextView textView, String str) {
        textView.setTypeface(getFont(textView.getContext().getAssets(), str));
    }
}
