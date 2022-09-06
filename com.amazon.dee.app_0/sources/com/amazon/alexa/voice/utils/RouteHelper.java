package com.amazon.alexa.voice.utils;

import android.net.Uri;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public final class RouteHelper {
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String PARAMETER_REFERER = "referer";
    private static final String PARAMETER_SEPARATOR = "&";

    private RouteHelper() {
    }

    public static String getReferer(Map<String, String> map) {
        String str = map.get("referer");
        if (str == null) {
            map.put("referer", "UNKNOWN");
            return "UNKNOWN";
        }
        return str;
    }

    public static Map<String, String> parseQueryParameters(String str) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : Uri.decode(str).split("&")) {
                String[] split = str2.split("=");
                if (split.length == 2) {
                    hashMap.put(split[0], split[1]);
                }
            }
        }
        return hashMap;
    }
}
