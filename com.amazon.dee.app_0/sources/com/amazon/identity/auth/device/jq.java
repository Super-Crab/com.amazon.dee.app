package com.amazon.identity.auth.device;

import android.net.Uri;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class jq<T extends HttpURLConnection> implements js {
    private final T rN;

    public jq(T t) {
        this.rN = t;
    }

    @Override // com.amazon.identity.auth.device.js
    public abstract byte[] getBody();

    @Override // com.amazon.identity.auth.device.js
    public String getHeader(String str) {
        return this.rN.getRequestProperty(str);
    }

    @Override // com.amazon.identity.auth.device.js
    public Map<String, List<String>> getHeaders() {
        return this.rN.getRequestProperties();
    }

    @Override // com.amazon.identity.auth.device.js
    public String getHttpVerb() {
        return this.rN.getRequestMethod();
    }

    @Override // com.amazon.identity.auth.device.js
    public Uri getUri() {
        return Uri.parse(this.rN.getURL().toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T getUrlConnection() {
        return this.rN;
    }

    @Override // com.amazon.identity.auth.device.js
    public void setHeader(String str, String str2) {
        this.rN.setRequestProperty(str, str2);
    }
}
