package com.amazon.identity.auth.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class me {
    private static final String TAG = "com.amazon.identity.auth.device.me";
    private final Map<String, String> hQ = new HashMap();
    private final List<String> uO = new ArrayList();
    private long uY = 200;

    public void a(long j) {
        this.uY = j;
    }

    public void addHeader(String str, String str2) {
        if (str != null && str2 != null) {
            this.uO.add(str);
            this.hQ.put(str.toLowerCase(Locale.US), str2);
            return;
        }
        io.e(TAG, "WebResponseHeaders: addHeader: Header cannot be added. Name or value was null.");
    }

    public String eK(String str) {
        return this.hQ.get(str.toLowerCase(Locale.US));
    }

    public long iG() {
        return this.uY;
    }
}
