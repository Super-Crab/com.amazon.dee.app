package com.amazon.identity.auth.device.api;

import android.annotation.TargetApi;
import com.amazon.identity.auth.device.eq;
import com.amazon.identity.auth.device.jq;
import com.amazon.identity.auth.device.jr;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
class AuthenticatedHttpURLConnection extends eq {
    private static final String TAG = AuthenticatedHttpURLConnection.class.getName();
    private final HttpURLConnection fB;
    private final AuthenticationMethod fC;
    private final Object[] fD;
    private jr fE;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthenticatedHttpURLConnection(HttpURLConnection httpURLConnection, AuthenticationMethod authenticationMethod) {
        this(httpURLConnection.getURL(), httpURLConnection, authenticationMethod);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void addRequestProperty(String str, String str2) {
        this.fB.addRequestProperty(str, str2);
    }

    byte[] bd() {
        synchronized (this.fD) {
            if (this.fE == null) {
                return new byte[0];
            }
            return this.fE.gV();
        }
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public boolean getAllowUserInteraction() {
        return this.fB.getAllowUserInteraction();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public int getConnectTimeout() {
        return this.fB.getConnectTimeout();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public boolean getDefaultUseCaches() {
        return this.fB.getDefaultUseCaches();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public boolean getDoInput() {
        return this.fB.getDoInput();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public boolean getDoOutput() {
        return this.fB.getDoOutput();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public long getIfModifiedSince() {
        return this.fB.getIfModifiedSince();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    public boolean getInstanceFollowRedirects() {
        return this.fB.getInstanceFollowRedirects();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public OutputStream getOutputStream() throws IOException {
        jr jrVar;
        synchronized (this.fD) {
            if (this.fE == null) {
                this.fE = new jr(this.fB);
            }
            jrVar = this.fE;
        }
        return jrVar;
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public int getReadTimeout() {
        return this.fB.getReadTimeout();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    public String getRequestMethod() {
        return this.fB.getRequestMethod();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public Map<String, List<String>> getRequestProperties() {
        return this.fB.getRequestProperties();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public String getRequestProperty(String str) {
        return this.fB.getRequestProperty(str);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public URL getURL() {
        return this.fB.getURL();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public boolean getUseCaches() {
        return this.fB.getUseCaches();
    }

    @Override // com.amazon.identity.auth.device.eq
    public HttpURLConnection performOnConnectionRequested() throws IOException {
        this.fC.a(new jq<AuthenticatedHttpURLConnection>(this) { // from class: com.amazon.identity.auth.device.api.AuthenticatedHttpURLConnection.1
            @Override // com.amazon.identity.auth.device.jq, com.amazon.identity.auth.device.js
            public byte[] getBody() {
                return getUrlConnection().bd();
            }
        });
        synchronized (this.fD) {
            if (this.fE != null) {
                this.fE.gW();
            }
        }
        return this.fB;
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setAllowUserInteraction(boolean z) {
        this.fB.setAllowUserInteraction(z);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    public void setChunkedStreamingMode(int i) {
        this.fB.setChunkedStreamingMode(i);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setConnectTimeout(int i) {
        this.fB.setConnectTimeout(i);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setDefaultUseCaches(boolean z) {
        this.fB.setDefaultUseCaches(z);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setDoInput(boolean z) {
        this.fB.setDoInput(z);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setDoOutput(boolean z) {
        this.fB.setDoOutput(z);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(int i) {
        this.fB.setFixedLengthStreamingMode(i);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setIfModifiedSince(long j) {
        this.fB.setIfModifiedSince(j);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    public void setInstanceFollowRedirects(boolean z) {
        this.fB.setInstanceFollowRedirects(z);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setReadTimeout(int i) {
        this.fB.setReadTimeout(i);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    public void setRequestMethod(String str) throws ProtocolException {
        this.fB.setRequestMethod(str);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setRequestProperty(String str, String str2) {
        this.fB.setRequestProperty(str, str2);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setUseCaches(boolean z) {
        this.fB.setUseCaches(z);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public String toString() {
        return this.fB.toString();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    public boolean usingProxy() {
        return this.fB.usingProxy();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthenticatedHttpURLConnection(URL url, HttpURLConnection httpURLConnection, AuthenticationMethod authenticationMethod) {
        super(url);
        this.fD = new Object[0];
        this.fC = authenticationMethod;
        this.fB = httpURLConnection;
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    @TargetApi(19)
    public void setFixedLengthStreamingMode(long j) {
        this.fB.setFixedLengthStreamingMode(j);
    }
}
