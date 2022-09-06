package com.amazon.blueshift.bluefront.android.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
/* loaded from: classes11.dex */
public class URLWrapper {
    private final URL mUrl;

    public URLWrapper(String str) throws MalformedURLException {
        this.mUrl = new URL(str);
    }

    public URLConnection openConnection() throws IOException {
        return this.mUrl.openConnection();
    }

    public String toString() {
        return this.mUrl.toString();
    }
}
