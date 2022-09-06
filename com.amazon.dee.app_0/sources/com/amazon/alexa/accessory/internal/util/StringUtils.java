package com.amazon.alexa.accessory.internal.util;

import android.util.Log;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import java.util.IllegalFormatException;
import java.util.Locale;
/* loaded from: classes.dex */
public final class StringUtils {
    private StringUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static String join(Iterable<? extends CharSequence> iterable) {
        StringBuilder sb = new StringBuilder();
        for (CharSequence charSequence : iterable) {
            sb.append(charSequence);
        }
        return sb.toString();
    }

    public static String removePrefix(String str, String str2) {
        return (str == null || str2 == null) ? str : str.replaceFirst(String.format("(?i)^%s", str2), "");
    }

    public static String safeFormat(Locale locale, String str, Object... objArr) {
        try {
            return String.format(locale, str, objArr);
        } catch (NullPointerException | IllegalFormatException e) {
            Log.e("AlexaAccessory", "String.format() caused exception", e);
            if (str == null) {
                str = "null";
            }
            StringBuilder sb = new StringBuilder(str);
            if (objArr != null) {
                for (Object obj : objArr) {
                    sb.append(AccessoryMetricsConstants.DELIMITER);
                    if (obj != null) {
                        sb.append(obj.toString());
                    } else {
                        sb.append("null");
                    }
                }
            }
            return sb.toString();
        }
    }
}
