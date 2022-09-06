package com.amazon.identity.auth.device;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class dp extends Cdo {
    private final HttpsURLConnection jW;

    public dp(URL url) throws IOException {
        super(url);
        HttpURLConnection cU = cU();
        if (cU instanceof HttpsURLConnection) {
            this.jW = (HttpsURLConnection) cU;
            return;
        }
        throw new IllegalArgumentException("url must be https");
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ void addRequestProperty(String str, String str2) {
        super.addRequestProperty(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.Cdo
    public void c(HttpURLConnection httpURLConnection) {
        super.c(httpURLConnection);
        if (!(httpURLConnection instanceof HttpsURLConnection)) {
            return;
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
        httpsURLConnection.setSSLSocketFactory(this.jW.getSSLSocketFactory());
        httpsURLConnection.setHostnameVerifier(this.jW.getHostnameVerifier());
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ boolean getAllowUserInteraction() {
        return super.getAllowUserInteraction();
    }

    public String getCipherSuite() {
        return this.jW.getCipherSuite();
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ int getConnectTimeout() {
        return super.getConnectTimeout();
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ boolean getDefaultUseCaches() {
        return super.getDefaultUseCaches();
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ boolean getDoInput() {
        return super.getDoInput();
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ boolean getDoOutput() {
        return super.getDoOutput();
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.jW.getHostnameVerifier();
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ long getIfModifiedSince() {
        return super.getIfModifiedSince();
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ boolean getInstanceFollowRedirects() {
        return super.getInstanceFollowRedirects();
    }

    public Certificate[] getLocalCertificates() {
        return this.jW.getLocalCertificates();
    }

    public Principal getLocalPrincipal() {
        return this.jW.getLocalPrincipal();
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ OutputStream getOutputStream() {
        return super.getOutputStream();
    }

    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        return this.jW.getPeerPrincipal();
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ int getReadTimeout() {
        return super.getReadTimeout();
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ String getRequestMethod() {
        return super.getRequestMethod();
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ Map getRequestProperties() {
        return super.getRequestProperties();
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ String getRequestProperty(String str) {
        return super.getRequestProperty(str);
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return this.jW.getSSLSocketFactory();
    }

    public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
        return this.jW.getServerCertificates();
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ URL getURL() {
        return super.getURL();
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ boolean getUseCaches() {
        return super.getUseCaches();
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ void setAllowUserInteraction(boolean z) {
        super.setAllowUserInteraction(z);
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ void setChunkedStreamingMode(int i) {
        super.setChunkedStreamingMode(i);
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ void setConnectTimeout(int i) {
        super.setConnectTimeout(i);
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ void setDefaultUseCaches(boolean z) {
        super.setDefaultUseCaches(z);
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ void setDoInput(boolean z) {
        super.setDoInput(z);
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ void setDoOutput(boolean z) {
        super.setDoOutput(z);
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ void setFixedLengthStreamingMode(long j) {
        super.setFixedLengthStreamingMode(j);
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.jW.setHostnameVerifier(hostnameVerifier);
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ void setIfModifiedSince(long j) {
        super.setIfModifiedSince(j);
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ void setInstanceFollowRedirects(boolean z) {
        super.setInstanceFollowRedirects(z);
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ void setReadTimeout(int i) {
        super.setReadTimeout(i);
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ void setRequestMethod(String str) throws ProtocolException {
        super.setRequestMethod(str);
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ void setRequestProperty(String str, String str2) {
        super.setRequestProperty(str, str2);
    }

    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.jW.setSSLSocketFactory(sSLSocketFactory);
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ void setUseCaches(boolean z) {
        super.setUseCaches(z);
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // com.amazon.identity.auth.device.Cdo
    public /* bridge */ /* synthetic */ boolean usingProxy() {
        return super.usingProxy();
    }
}
