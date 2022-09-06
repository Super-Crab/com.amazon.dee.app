package com.amazon.identity.auth.device.api;

import android.os.Bundle;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.ew;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.ji;
import com.amazon.identity.auth.device.jq;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class AuthenticatedURLConnection extends HttpsURLConnection {
    private static final String TAG = AuthenticatedURLConnection.class.getName();
    private final AuthenticatedHttpsURLConnection fI;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class AccountNeedsRecoveryException extends IOException {
        private final Bundle mAccountRecoverContextBundle;

        @FireOsSdk
        public AccountNeedsRecoveryException(String str, Bundle bundle) {
            super(str);
            this.mAccountRecoverContextBundle = bundle;
        }

        @FireOsSdk
        public final Bundle getAccountRecoverContextBundle() {
            return this.mAccountRecoverContextBundle;
        }

        public final Bundle getExtraBundle() {
            Bundle bundle = new Bundle();
            bundle.putBundle("com.amazon.identity.mobi.account.recover.context", this.mAccountRecoverContextBundle);
            return bundle;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class AuthenticationFailureIOException extends IOException {
        @FireOsSdk
        public AuthenticationFailureIOException(String str) {
            super(str);
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class NoCredentialsException extends IOException {
        private static final long serialVersionUID = 1;

        @FireOsSdk
        public NoCredentialsException(String str) {
            super(str);
        }
    }

    @Deprecated
    public AuthenticatedURLConnection(URL url, AuthenticationMethod authenticationMethod) throws IOException {
        super(url);
        this.fI = (AuthenticatedHttpsURLConnection) openConnection(url, authenticationMethod);
    }

    @FireOsSdk
    public static HttpURLConnection openConnection(URL url, AuthenticationMethod authenticationMethod) throws IOException {
        if (url != null) {
            if (authenticationMethod != null) {
                URLConnection c = ew.c(url);
                if (c instanceof HttpURLConnection) {
                    return openConnection((HttpURLConnection) c, authenticationMethod);
                }
                throw new IllegalArgumentException("Url must be an Https or Http Url");
            }
            throw new IllegalArgumentException("Must Specify an Authentication Method");
        }
        throw new IllegalArgumentException("Must Specify a URL");
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void addRequestProperty(String str, String str2) {
        this.fI.addRequestProperty(str, str2);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void connect() throws IOException {
        try {
            this.fI.connect();
        } catch (IOException e) {
            if (e instanceof SSLHandshakeException) {
                io.e(TAG, "SSL Handshake fail when establishing connect", e);
            }
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public void disconnect() {
        this.fI.disconnect();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public boolean getAllowUserInteraction() {
        return this.fI.getAllowUserInteraction();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public String getCipherSuite() {
        return this.fI.getCipherSuite();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public int getConnectTimeout() {
        return this.fI.getConnectTimeout();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public Object getContent() throws IOException {
        return this.fI.getContent();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public String getContentEncoding() {
        return this.fI.getContentEncoding();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public int getContentLength() {
        return this.fI.getContentLength();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public String getContentType() {
        return this.fI.getContentType();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public long getDate() {
        return this.fI.getDate();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public boolean getDefaultUseCaches() {
        return this.fI.getDefaultUseCaches();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public boolean getDoInput() {
        return this.fI.getDoInput();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public boolean getDoOutput() {
        return this.fI.getDoOutput();
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public InputStream getErrorStream() {
        return this.fI.getErrorStream();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public long getExpiration() {
        return this.fI.getExpiration();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    @FireOsSdk
    public String getHeaderField(int i) {
        return this.fI.getHeaderField(i);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    @FireOsSdk
    public long getHeaderFieldDate(String str, long j) {
        return this.fI.getHeaderFieldDate(str, j);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public int getHeaderFieldInt(String str, int i) {
        return this.fI.getHeaderFieldInt(str, i);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    @FireOsSdk
    public String getHeaderFieldKey(int i) {
        return this.fI.getHeaderFieldKey(i);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public Map<String, List<String>> getHeaderFields() {
        return this.fI.getHeaderFields();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public HostnameVerifier getHostnameVerifier() {
        return this.fI.getHostnameVerifier();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public long getIfModifiedSince() {
        return this.fI.getIfModifiedSince();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public InputStream getInputStream() throws IOException {
        try {
            return this.fI.getInputStream();
        } catch (IOException e) {
            if (e instanceof SSLHandshakeException) {
                io.e(TAG, "SSL Handshake fail when getInputStream", e);
            }
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public boolean getInstanceFollowRedirects() {
        return this.fI.getInstanceFollowRedirects();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public long getLastModified() {
        return this.fI.getLastModified();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public Certificate[] getLocalCertificates() {
        return this.fI.getLocalCertificates();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public Principal getLocalPrincipal() {
        return this.fI.getLocalPrincipal();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public OutputStream getOutputStream() throws IOException {
        try {
            return this.fI.getOutputStream();
        } catch (IOException e) {
            if (e instanceof SSLHandshakeException) {
                io.e(TAG, "SSL HandShake fail when getOutputStream", e);
            }
            throw e;
        }
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        return this.fI.getPeerPrincipal();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    @FireOsSdk
    public Permission getPermission() throws IOException {
        return this.fI.getPermission();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public int getReadTimeout() {
        return this.fI.getReadTimeout();
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public String getRequestMethod() {
        return this.fI.getRequestMethod();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public Map<String, List<String>> getRequestProperties() {
        return this.fI.getRequestProperties();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public String getRequestProperty(String str) {
        return this.fI.getRequestProperty(str);
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public int getResponseCode() throws IOException {
        try {
            return this.fI.getResponseCode();
        } catch (IOException e) {
            if (e instanceof SSLHandshakeException) {
                io.e(TAG, "SSL Handshake fail when getResponseCode", e);
            }
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public String getResponseMessage() throws IOException {
        return this.fI.getResponseMessage();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public SSLSocketFactory getSSLSocketFactory() {
        return this.fI.getSSLSocketFactory();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
        return this.fI.getServerCertificates();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public URL getURL() {
        return this.fI.getURL();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public boolean getUseCaches() {
        return this.fI.getUseCaches();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setAllowUserInteraction(boolean z) {
        this.fI.setAllowUserInteraction(z);
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public void setChunkedStreamingMode(int i) {
        this.fI.setChunkedStreamingMode(i);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setConnectTimeout(int i) {
        this.fI.setConnectTimeout(i);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setDefaultUseCaches(boolean z) {
        this.fI.setDefaultUseCaches(z);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setDoInput(boolean z) {
        this.fI.setDoInput(z);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setDoOutput(boolean z) {
        this.fI.setDoOutput(z);
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public void setFixedLengthStreamingMode(int i) {
        this.fI.setFixedLengthStreamingMode(i);
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.fI.setHostnameVerifier(hostnameVerifier);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setIfModifiedSince(long j) {
        this.fI.setIfModifiedSince(j);
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public void setInstanceFollowRedirects(boolean z) {
        this.fI.setInstanceFollowRedirects(z);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setReadTimeout(int i) {
        this.fI.setReadTimeout(i);
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public void setRequestMethod(String str) throws ProtocolException {
        this.fI.setRequestMethod(str);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setRequestProperty(String str, String str2) {
        this.fI.setRequestProperty(str, str2);
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.fI.setSSLSocketFactory(sSLSocketFactory);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setUseCaches(boolean z) {
        this.fI.setUseCaches(z);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public String toString() {
        return this.fI.toString();
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public boolean usingProxy() {
        return this.fI.usingProxy();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public Object getContent(Class[] clsArr) throws IOException {
        return this.fI.getContent(clsArr);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public String getHeaderField(String str) {
        return this.fI.getHeaderField(str);
    }

    @FireOsSdk
    public static HttpURLConnection openConnection(HttpURLConnection httpURLConnection, AuthenticationMethod authenticationMethod) throws IOException {
        if (httpURLConnection != null) {
            if (authenticationMethod != null) {
                AuthenticationType parse = AuthenticationType.parse(authenticationMethod.fJ);
                if (httpURLConnection instanceof HttpsURLConnection) {
                    if (parse == AuthenticationType.OAuth && !ji.gP()) {
                        authenticationMethod.a(new jq<HttpURLConnection>(httpURLConnection) { // from class: com.amazon.identity.auth.device.api.AuthenticatedURLConnection.1
                            @Override // com.amazon.identity.auth.device.jq, com.amazon.identity.auth.device.js
                            public byte[] getBody() {
                                return new byte[0];
                            }
                        });
                        return httpURLConnection;
                    }
                    return new AuthenticatedHttpsURLConnection((HttpsURLConnection) httpURLConnection, authenticationMethod);
                } else if (parse != null && parse.requireHttps()) {
                    throw new SecurityException(String.format("The authentication type %s can only be used over HTTPS. ", authenticationMethod.fJ));
                } else {
                    URL url = httpURLConnection.getURL();
                    String host = url != null ? url.getHost() : null;
                    StringBuilder sb = new StringBuilder("Using http for ");
                    sb.append(host);
                    sb.append(" with auth type ");
                    sb.append(parse);
                    io.gD();
                    return new AuthenticatedHttpURLConnection(httpURLConnection, authenticationMethod);
                }
            }
            throw new IllegalArgumentException("Must Specify an Authentication Method");
        }
        throw new IllegalArgumentException("Must Specify a URL");
    }
}
