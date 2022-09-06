package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ObjectJsonStreamer {
    private static final String FILTERED_PLACEHOLDER = "[FILTERED]";
    private static final String OBJECT_PLACEHOLDER = "[OBJECT]";
    String[] filters = {MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD};

    private boolean shouldFilter(@Nullable String str) {
        String[] strArr = this.filters;
        if (strArr != null && str != null) {
            for (String str2 : strArr) {
                if (str.contains(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void objectToStream(@Nullable Object obj, @NonNull JsonStream jsonStream) throws IOException {
        if (obj == null) {
            jsonStream.nullValue();
        } else if (obj instanceof String) {
            jsonStream.value((String) obj);
        } else if (obj instanceof Number) {
            jsonStream.value((Number) obj);
        } else if (obj instanceof Boolean) {
            jsonStream.value((Boolean) obj);
        } else if (obj instanceof Map) {
            jsonStream.beginObject();
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                if (key instanceof String) {
                    String str = (String) key;
                    jsonStream.mo6745name(str);
                    if (shouldFilter(str)) {
                        jsonStream.value(FILTERED_PLACEHOLDER);
                    } else {
                        objectToStream(entry.getValue(), jsonStream);
                    }
                }
            }
            jsonStream.endObject();
        } else if (obj instanceof Collection) {
            jsonStream.beginArray();
            for (Object obj2 : (Collection) obj) {
                objectToStream(obj2, jsonStream);
            }
            jsonStream.endArray();
        } else if (obj.getClass().isArray()) {
            jsonStream.beginArray();
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                objectToStream(Array.get(obj, i), jsonStream);
            }
            jsonStream.endArray();
        } else {
            jsonStream.value(OBJECT_PLACEHOLDER);
        }
    }
}
