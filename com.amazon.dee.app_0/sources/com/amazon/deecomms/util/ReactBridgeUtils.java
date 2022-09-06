package com.amazon.deecomms.util;

import com.facebook.react.bridge.ReadableArray;
/* loaded from: classes12.dex */
public final class ReactBridgeUtils {
    private ReactBridgeUtils() {
    }

    public static String getDelimitedStringFromArray(ReadableArray readableArray, String str) {
        if (readableArray == null || readableArray.size() == 0) {
            return "";
        }
        if (readableArray.size() == 1) {
            return readableArray.getString(0);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(readableArray.getString(0));
        for (int i = 1; i < readableArray.size(); i++) {
            sb.append(str);
            sb.append(readableArray.getString(i));
        }
        return sb.toString();
    }
}
