package com.amazon.identity.auth.device;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.util.List;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class dv extends HttpURLConnection {
    private static final String TAG = dv.class.getName();
    private final Object[] fD;
    private final HttpURLConnection kP;
    private byte[] kQ;
    private IOException kR;
    private byte[] kS;

    public dv(HttpURLConnection httpURLConnection) {
        super(httpURLConnection.getURL());
        this.fD = new Object[0];
        this.kP = httpURLConnection;
    }

    @Override // java.net.URLConnection
    public void addRequestProperty(String str, String str2) {
        this.kP.addRequestProperty(str, str2);
    }

    @Override // java.net.URLConnection
    public void connect() throws IOException {
        this.kP.connect();
    }

    @Override // java.net.HttpURLConnection
    public void disconnect() {
        this.kP.disconnect();
    }

    @Override // java.net.URLConnection
    public boolean getAllowUserInteraction() {
        return this.kP.getAllowUserInteraction();
    }

    @Override // java.net.URLConnection
    public int getConnectTimeout() {
        return this.kP.getConnectTimeout();
    }

    @Override // java.net.URLConnection
    public Object getContent() throws IOException {
        return this.kP.getContent();
    }

    @Override // java.net.URLConnection
    public String getContentEncoding() {
        return this.kP.getContentEncoding();
    }

    @Override // java.net.URLConnection
    public int getContentLength() {
        return this.kP.getContentLength();
    }

    @Override // java.net.URLConnection
    public String getContentType() {
        return this.kP.getContentType();
    }

    @Override // java.net.URLConnection
    public long getDate() {
        return this.kP.getDate();
    }

    @Override // java.net.URLConnection
    public boolean getDefaultUseCaches() {
        return this.kP.getDefaultUseCaches();
    }

    @Override // java.net.URLConnection
    public boolean getDoInput() {
        return this.kP.getDoInput();
    }

    @Override // java.net.URLConnection
    public boolean getDoOutput() {
        return this.kP.getDoOutput();
    }

    @Override // java.net.HttpURLConnection
    public InputStream getErrorStream() {
        ByteArrayInputStream byteArrayInputStream;
        synchronized (this.fD) {
            if (this.kS == null) {
                InputStream inputStream = null;
                try {
                    inputStream = this.kP.getErrorStream();
                    if (inputStream != null) {
                        this.kS = jd.a(inputStream);
                    } else {
                        io.i(TAG, "No Error Stream found");
                        this.kS = new byte[0];
                    }
                } catch (IOException unused) {
                    this.kS = new byte[0];
                }
                jd.a((Closeable) inputStream);
            }
            byteArrayInputStream = new ByteArrayInputStream(this.kS);
        }
        return byteArrayInputStream;
    }

    @Override // java.net.URLConnection
    public long getExpiration() {
        return this.kP.getExpiration();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderField(int i) {
        return this.kP.getHeaderField(i);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public long getHeaderFieldDate(String str, long j) {
        return this.kP.getHeaderFieldDate(str, j);
    }

    @Override // java.net.URLConnection
    public int getHeaderFieldInt(String str, int i) {
        return this.kP.getHeaderFieldInt(str, i);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderFieldKey(int i) {
        return this.kP.getHeaderFieldKey(i);
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getHeaderFields() {
        return this.kP.getHeaderFields();
    }

    @Override // java.net.URLConnection
    public long getIfModifiedSince() {
        return this.kP.getIfModifiedSince();
    }

    @Override // java.net.URLConnection
    public InputStream getInputStream() throws IOException {
        ByteArrayInputStream byteArrayInputStream;
        synchronized (this.fD) {
            if (this.kQ == null && this.kR == null) {
                try {
                    InputStream inputStream = this.kP.getInputStream();
                    this.kQ = jd.a(inputStream);
                    jd.a((Closeable) inputStream);
                } catch (IOException e) {
                    this.kQ = new byte[0];
                    this.kR = e;
                    throw this.kR;
                }
            }
            if (this.kR == null) {
                byteArrayInputStream = new ByteArrayInputStream(this.kQ);
            } else {
                throw this.kR;
            }
        }
        return byteArrayInputStream;
    }

    @Override // java.net.HttpURLConnection
    public boolean getInstanceFollowRedirects() {
        return this.kP.getInstanceFollowRedirects();
    }

    @Override // java.net.URLConnection
    public long getLastModified() {
        return this.kP.getLastModified();
    }

    @Override // java.net.URLConnection
    public OutputStream getOutputStream() throws IOException {
        return this.kP.getOutputStream();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public Permission getPermission() throws IOException {
        return this.kP.getPermission();
    }

    @Override // java.net.URLConnection
    public int getReadTimeout() {
        return this.kP.getReadTimeout();
    }

    @Override // java.net.HttpURLConnection
    public String getRequestMethod() {
        return this.kP.getRequestMethod();
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getRequestProperties() {
        return this.kP.getRequestProperties();
    }

    @Override // java.net.URLConnection
    public String getRequestProperty(String str) {
        return this.kP.getRequestProperty(str);
    }

    @Override // java.net.HttpURLConnection
    public int getResponseCode() throws IOException {
        return this.kP.getResponseCode();
    }

    @Override // java.net.HttpURLConnection
    public String getResponseMessage() throws IOException {
        return this.kP.getRequestMethod();
    }

    @Override // java.net.URLConnection
    public URL getURL() {
        return this.kP.getURL();
    }

    @Override // java.net.URLConnection
    public boolean getUseCaches() {
        return this.kP.getUseCaches();
    }

    @Override // java.net.URLConnection
    public void setAllowUserInteraction(boolean z) {
        this.kP.setAllowUserInteraction(z);
    }

    @Override // java.net.HttpURLConnection
    public void setChunkedStreamingMode(int i) {
        this.kP.setChunkedStreamingMode(i);
    }

    @Override // java.net.URLConnection
    public void setConnectTimeout(int i) {
        this.kP.setConnectTimeout(i);
    }

    @Override // java.net.URLConnection
    public void setDefaultUseCaches(boolean z) {
        this.kP.setDefaultUseCaches(z);
    }

    @Override // java.net.URLConnection
    public void setDoInput(boolean z) {
        this.kP.setDoInput(z);
    }

    @Override // java.net.URLConnection
    public void setDoOutput(boolean z) {
        this.kP.setDoInput(z);
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(int i) {
        this.kP.setFixedLengthStreamingMode(i);
    }

    @Override // java.net.URLConnection
    public void setIfModifiedSince(long j) {
        this.kP.setIfModifiedSince(j);
    }

    @Override // java.net.HttpURLConnection
    public void setInstanceFollowRedirects(boolean z) {
        this.kP.setInstanceFollowRedirects(z);
    }

    @Override // java.net.URLConnection
    public void setReadTimeout(int i) {
        this.kP.setReadTimeout(i);
    }

    @Override // java.net.HttpURLConnection
    public void setRequestMethod(String str) throws ProtocolException {
        this.kP.setRequestMethod(str);
    }

    @Override // java.net.URLConnection
    public void setRequestProperty(String str, String str2) {
        this.kP.setRequestProperty(str, str2);
    }

    @Override // java.net.URLConnection
    public void setUseCaches(boolean z) {
        this.kP.setUseCaches(z);
    }

    @Override // java.net.URLConnection
    public String toString() {
        return this.kP.toString();
    }

    @Override // java.net.HttpURLConnection
    public boolean usingProxy() {
        return this.kP.usingProxy();
    }

    @Override // java.net.URLConnection
    public Object getContent(Class[] clsArr) throws IOException {
        return this.kP.getContent(clsArr);
    }

    @Override // java.net.URLConnection
    public String getHeaderField(String str) {
        return this.kP.getHeaderField(str);
    }
}
