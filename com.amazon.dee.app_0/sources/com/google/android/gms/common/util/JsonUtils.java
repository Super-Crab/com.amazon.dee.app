package com.google.android.gms.common.util;

import android.text.TextUtils;
import com.amazon.alexa.accessory.internal.util.DeviceDatabaseUtils;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@VisibleForTesting
@KeepForSdk
/* loaded from: classes2.dex */
public final class JsonUtils {
    private static final Pattern zzhd = Pattern.compile("\\\\.");
    private static final Pattern zzhe = Pattern.compile("[\\\\\"/\b\f\n\r\t]");

    private JsonUtils() {
    }

    @KeepForSdk
    public static boolean areJsonValuesEquivalent(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        if ((obj instanceof JSONObject) && (obj2 instanceof JSONObject)) {
            JSONObject jSONObject = (JSONObject) obj;
            JSONObject jSONObject2 = (JSONObject) obj2;
            if (jSONObject.length() != jSONObject2.length()) {
                return false;
            }
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (!jSONObject2.has(str)) {
                    return false;
                }
                if (!areJsonValuesEquivalent(jSONObject.get(str), jSONObject2.get(str))) {
                    return false;
                }
            }
            return true;
        } else if ((obj instanceof JSONArray) && (obj2 instanceof JSONArray)) {
            JSONArray jSONArray = (JSONArray) obj;
            JSONArray jSONArray2 = (JSONArray) obj2;
            if (jSONArray.length() != jSONArray2.length()) {
                return false;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    if (!areJsonValuesEquivalent(jSONArray.get(i), jSONArray2.get(i))) {
                        return false;
                    }
                } catch (JSONException unused) {
                    return false;
                }
            }
            return true;
        } else {
            return obj.equals(obj2);
        }
    }

    @KeepForSdk
    public static String escapeString(String str) {
        if (!TextUtils.isEmpty(str)) {
            Matcher matcher = zzhe.matcher(str);
            StringBuffer stringBuffer = null;
            while (matcher.find()) {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer();
                }
                char charAt = matcher.group().charAt(0);
                if (charAt == '\f') {
                    matcher.appendReplacement(stringBuffer, "\\\\f");
                } else if (charAt == '\r') {
                    matcher.appendReplacement(stringBuffer, "\\\\r");
                } else if (charAt == '\"') {
                    matcher.appendReplacement(stringBuffer, "\\\\\\\"");
                } else if (charAt == '/') {
                    matcher.appendReplacement(stringBuffer, "\\\\/");
                } else if (charAt != '\\') {
                    switch (charAt) {
                        case '\b':
                            matcher.appendReplacement(stringBuffer, "\\\\b");
                            continue;
                        case '\t':
                            matcher.appendReplacement(stringBuffer, "\\\\t");
                            continue;
                        case '\n':
                            matcher.appendReplacement(stringBuffer, "\\\\n");
                            continue;
                    }
                } else {
                    matcher.appendReplacement(stringBuffer, "\\\\\\\\");
                }
            }
            if (stringBuffer == null) {
                return str;
            }
            matcher.appendTail(stringBuffer);
            return stringBuffer.toString();
        }
        return str;
    }

    @KeepForSdk
    public static String unescapeString(String str) {
        if (!TextUtils.isEmpty(str)) {
            String unescape = zzd.unescape(str);
            Matcher matcher = zzhd.matcher(unescape);
            StringBuffer stringBuffer = null;
            while (matcher.find()) {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer();
                }
                char charAt = matcher.group().charAt(1);
                if (charAt == '\"') {
                    matcher.appendReplacement(stringBuffer, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
                } else if (charAt == '/') {
                    matcher.appendReplacement(stringBuffer, "/");
                } else if (charAt == '\\') {
                    matcher.appendReplacement(stringBuffer, "\\\\");
                } else if (charAt == 'b') {
                    matcher.appendReplacement(stringBuffer, "\b");
                } else if (charAt == 'f') {
                    matcher.appendReplacement(stringBuffer, "\f");
                } else if (charAt == 'n') {
                    matcher.appendReplacement(stringBuffer, "\n");
                } else if (charAt == 'r') {
                    matcher.appendReplacement(stringBuffer, StringUtils.CR);
                } else if (charAt == 't') {
                    matcher.appendReplacement(stringBuffer, DeviceDatabaseUtils.DELIMITER);
                } else {
                    throw new IllegalStateException("Found an escaped character that should never be.");
                }
            }
            if (stringBuffer == null) {
                return unescape;
            }
            matcher.appendTail(stringBuffer);
            return stringBuffer.toString();
        }
        return str;
    }
}
