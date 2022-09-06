package com.amazon.identity.auth.device;

import android.os.Build;
import com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider;
import com.amazon.identity.auth.device.framework.RetryLogic;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* compiled from: DCP */
/* renamed from: com.amazon.identity.auth.device.do  reason: invalid class name */
/* loaded from: classes12.dex */
class Cdo {
    private static final String TAG = "com.amazon.identity.auth.device.do";
    private final HttpURLConnection jR;
    private final Map<String, List<String>> jS = new HashMap();
    private final ByteArrayOutputStream jT = new ByteArrayOutputStream();
    private Integer jU = null;
    private Long jV = null;

    public Cdo(URL url) throws IOException {
        if (url != null) {
            URLConnection c = ew.c(url);
            if (c instanceof HttpURLConnection) {
                this.jR = (HttpURLConnection) c;
                return;
            }
            throw new IllegalArgumentException("url must be at least http");
        }
        throw new IllegalArgumentException("url cannot be null");
    }

    private void a(String str, String str2, boolean z) {
        List<String> list = this.jS.get(str);
        if (list == null) {
            list = new ArrayList<>();
            this.jS.put(str, list);
        }
        if (z) {
            list.clear();
        }
        list.add(str2);
    }

    public void addRequestProperty(String str, String str2) {
        a(str, str2, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.setRequestMethod(this.jR.getRequestMethod());
            Integer num = this.jU;
            if (num != null) {
                httpURLConnection.setChunkedStreamingMode(num.intValue());
            }
            Long l = this.jV;
            if (l != null) {
                int i = Build.VERSION.SDK_INT;
                httpURLConnection.setFixedLengthStreamingMode(l.longValue());
            }
            httpURLConnection.setInstanceFollowRedirects(this.jR.getInstanceFollowRedirects());
            httpURLConnection.setAllowUserInteraction(this.jR.getAllowUserInteraction());
            httpURLConnection.setConnectTimeout(this.jR.getConnectTimeout());
            httpURLConnection.setDefaultUseCaches(this.jR.getDefaultUseCaches());
            httpURLConnection.setDoInput(this.jR.getDoInput());
            httpURLConnection.setDoOutput(this.jR.getDoOutput());
            httpURLConnection.setIfModifiedSince(this.jR.getIfModifiedSince());
            httpURLConnection.setReadTimeout(this.jR.getReadTimeout());
            httpURLConnection.setUseCaches(this.jR.getUseCaches());
            for (Map.Entry<String, List<String>> entry : this.jS.entrySet()) {
                for (String str : entry.getValue()) {
                    httpURLConnection.addRequestProperty(entry.getKey(), str);
                }
            }
        } catch (ProtocolException unused) {
            throw new IllegalStateException("Connection has been already executed");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpURLConnection cU() {
        return this.jR;
    }

    public final HttpURLConnection cV() throws IOException {
        HttpURLConnection cW = cW();
        OutputStream outputStream = null;
        if (cW == null) {
            return null;
        }
        c(cW);
        RetryLogic.a(cW.getURL());
        String requestMethod = cW.getRequestMethod();
        if ("POST".equalsIgnoreCase(requestMethod) || SmartDeviceDataProvider.METHOD_HTTP_PUT.equalsIgnoreCase(requestMethod)) {
            try {
                try {
                    outputStream = cW.getOutputStream();
                    outputStream.write(this.jT.toByteArray());
                } catch (SecurityException e) {
                    String str = TAG;
                    io.e(str, "Got a security exception while trying to establish connection. Verify that your app has internet access permission android.permission.INTERNET." + e.getMessage());
                    throw new IOException(e.getMessage());
                }
            } finally {
                jd.a(outputStream);
            }
        }
        return cW;
    }

    protected HttpURLConnection cW() {
        try {
            return (HttpURLConnection) ew.c(this.jR.getURL());
        } catch (IOException e) {
            io.e(TAG, "IOException while cloning connection. Should not happen", e);
            return null;
        }
    }

    public boolean getAllowUserInteraction() {
        return this.jR.getAllowUserInteraction();
    }

    public int getConnectTimeout() {
        return this.jR.getConnectTimeout();
    }

    public boolean getDefaultUseCaches() {
        return this.jR.getDefaultUseCaches();
    }

    public boolean getDoInput() {
        return this.jR.getDoInput();
    }

    public boolean getDoOutput() {
        return this.jR.getDoOutput();
    }

    public long getIfModifiedSince() {
        return this.jR.getIfModifiedSince();
    }

    public boolean getInstanceFollowRedirects() {
        return this.jR.getInstanceFollowRedirects();
    }

    public OutputStream getOutputStream() {
        return this.jT;
    }

    public int getReadTimeout() {
        return this.jR.getReadTimeout();
    }

    public String getRequestMethod() {
        return this.jR.getRequestMethod();
    }

    public Map<String, List<String>> getRequestProperties() {
        return Collections.unmodifiableMap(this.jS);
    }

    public String getRequestProperty(String str) {
        List<String> list = this.jS.get(str);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public URL getURL() {
        return this.jR.getURL();
    }

    public boolean getUseCaches() {
        return this.jR.getUseCaches();
    }

    public void setAllowUserInteraction(boolean z) {
        this.jR.setAllowUserInteraction(z);
    }

    public void setChunkedStreamingMode(int i) {
        this.jU = Integer.valueOf(i);
    }

    public void setConnectTimeout(int i) {
        this.jR.setConnectTimeout(i);
    }

    public void setDefaultUseCaches(boolean z) {
        this.jR.setDefaultUseCaches(z);
    }

    public void setDoInput(boolean z) {
        this.jR.setDoInput(z);
    }

    public void setDoOutput(boolean z) {
        this.jR.setDoOutput(z);
    }

    public void setFixedLengthStreamingMode(long j) {
        this.jV = Long.valueOf(j);
    }

    public void setIfModifiedSince(long j) {
        this.jR.setIfModifiedSince(j);
    }

    public void setInstanceFollowRedirects(boolean z) {
        this.jR.setInstanceFollowRedirects(z);
    }

    public void setReadTimeout(int i) {
        this.jR.setReadTimeout(i);
    }

    public void setRequestMethod(String str) throws ProtocolException {
        this.jR.setRequestMethod(str);
    }

    public void setRequestProperty(String str, String str2) {
        a(str, str2, true);
    }

    public void setUseCaches(boolean z) {
        this.jR.setUseCaches(z);
    }

    public String toString() {
        return this.jR.toString();
    }

    public boolean usingProxy() {
        return this.jR.usingProxy();
    }
}
