package com.amazon.identity.auth.device;

import com.amazon.dee.app.BuildConfig;
import java.util.Locale;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ij {
    public static String di(String str) {
        if (str == null) {
            return lz.a(Locale.getDefault());
        }
        if (str.endsWith(BuildConfig.RETAIL_HOST_JP)) {
            return lz.a(Locale.JAPAN);
        }
        if (str.endsWith(BuildConfig.RETAIL_HOST_CN)) {
            return lz.a(Locale.CHINA);
        }
        return lz.a(Locale.getDefault());
    }
}
