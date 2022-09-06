package com.amazon.dee.app.databinding;

import android.net.Uri;
import android.text.TextUtils;
import androidx.databinding.BindingConversion;
/* loaded from: classes12.dex */
public final class Converters {
    private Converters() {
    }

    @BindingConversion
    public static Uri convertStringToUri(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Uri.parse(str);
    }
}
