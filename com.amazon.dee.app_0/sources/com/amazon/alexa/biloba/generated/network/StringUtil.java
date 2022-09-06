package com.amazon.alexa.biloba.generated.network;

import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Date;
/* loaded from: classes6.dex */
public final class StringUtil {
    private StringUtil() {
    }

    public static boolean containsIgnoreCase(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str == null && str2 == null) {
                return true;
            }
            if (str != null && str.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        return false;
    }

    public static String escapeString(String str) {
        try {
            return URLEncoder.encode(str, "utf8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    public static String join(String[] strArr, String str) {
        int length = strArr.length;
        if (length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(strArr[0]);
        for (int i = 1; i < length; i++) {
            sb.append(str);
            sb.append(strArr[i]);
        }
        return sb.toString();
    }

    public static String parameterToString(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof Date) {
            String json = new Gson().toJson(obj);
            return escapeString(json.substring(1, json.length() - 1));
        } else if (obj instanceof Collection) {
            StringBuilder sb = new StringBuilder();
            for (Object obj2 : (Collection) obj) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(obj2);
            }
            return escapeString(sb.toString());
        } else {
            return escapeString(String.valueOf(obj));
        }
    }
}
