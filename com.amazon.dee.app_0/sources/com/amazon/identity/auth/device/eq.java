package com.amazon.identity.auth.device;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLHandshakeException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class eq extends HttpURLConnection {
    private static final String TAG = eq.class.getName();
    private HttpURLConnection fB;
    private IOException kU;
    private boolean lK;

    /* JADX INFO: Access modifiers changed from: protected */
    public eq(URL url) {
        super(url);
        this.lK = false;
        this.kU = null;
    }

    private void a(IOException iOException) {
        if (iOException instanceof SSLHandshakeException) {
            URL url = getURL();
            if (url != null) {
                new StringBuilder("Request host:").append(url.getHost());
            }
            io.gE();
        }
    }

    private synchronized void el() throws IOException {
        if (this.lK) {
            return;
        }
        if (this.kU == null) {
            try {
                this.fB = performOnConnectionRequested();
                if (this.fB != null) {
                    this.lK = true;
                    return;
                }
                throw new IOException("Connection could not be established");
            } catch (IOException e) {
                this.kU = e;
                if (e instanceof SSLHandshakeException) {
                    io.e(TAG, "SSL Handshake fail when performOnConnectionRequested", e);
                }
                throw e;
            }
        }
        io.i(TAG, "The first connection attempt ended in IOException so throwing the same");
        throw this.kU;
    }

    @Override // java.net.URLConnection
    public abstract void addRequestProperty(String str, String str2);

    @Override // java.net.URLConnection
    public void connect() throws IOException {
        try {
            el();
            this.fB.connect();
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    public synchronized void disconnect() {
        if (this.lK) {
            this.fB.disconnect();
        }
    }

    @Override // java.net.URLConnection
    public abstract boolean getAllowUserInteraction();

    @Override // java.net.URLConnection
    public abstract int getConnectTimeout();

    @Override // java.net.URLConnection
    public Object getContent() throws IOException {
        try {
            el();
            return this.fB.getContent();
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public String getContentEncoding() {
        try {
            el();
            return this.fB.getContentEncoding();
        } catch (IOException e) {
            a(e);
            io.e(TAG, "Could not get Content Encoding", e);
            return null;
        }
    }

    @Override // java.net.URLConnection
    public int getContentLength() {
        try {
            el();
            return this.fB.getContentLength();
        } catch (IOException e) {
            a(e);
            io.e(TAG, "Could not get Content Length", e);
            return 0;
        }
    }

    @Override // java.net.URLConnection
    public String getContentType() {
        try {
            el();
            return this.fB.getContentType();
        } catch (IOException e) {
            a(e);
            io.e(TAG, "Could not get Content Type", e);
            return null;
        }
    }

    @Override // java.net.URLConnection
    public long getDate() {
        try {
            el();
            return this.fB.getDate();
        } catch (IOException e) {
            a(e);
            io.e(TAG, "Could not get Date", e);
            return 0L;
        }
    }

    @Override // java.net.URLConnection
    public abstract boolean getDefaultUseCaches();

    @Override // java.net.URLConnection
    public abstract boolean getDoInput();

    @Override // java.net.URLConnection
    public abstract boolean getDoOutput();

    @Override // java.net.HttpURLConnection
    public InputStream getErrorStream() {
        HttpURLConnection httpURLConnection = this.fB;
        if (httpURLConnection != null) {
            return httpURLConnection.getErrorStream();
        }
        return null;
    }

    @Override // java.net.URLConnection
    public long getExpiration() {
        try {
            el();
            return this.fB.getExpiration();
        } catch (IOException e) {
            a(e);
            io.e(TAG, "Could not get expiration", e);
            return 0L;
        }
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderField(int i) {
        try {
            el();
            return this.fB.getHeaderField(i);
        } catch (IOException e) {
            a(e);
            io.e(TAG, "Could not get header field", e);
            return null;
        }
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public long getHeaderFieldDate(String str, long j) {
        try {
            el();
            return this.fB.getHeaderFieldDate(str, j);
        } catch (IOException e) {
            a(e);
            io.e(TAG, "Could not get header field date", e);
            return j;
        }
    }

    @Override // java.net.URLConnection
    public int getHeaderFieldInt(String str, int i) {
        try {
            el();
            return this.fB.getHeaderFieldInt(str, i);
        } catch (IOException e) {
            a(e);
            io.e(TAG, "Could not get header field int", e);
            return i;
        }
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderFieldKey(int i) {
        try {
            el();
            return this.fB.getHeaderFieldKey(i);
        } catch (IOException e) {
            a(e);
            io.e(TAG, "Could not get header field key", e);
            return null;
        }
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getHeaderFields() {
        try {
            el();
            return this.fB.getHeaderFields();
        } catch (IOException e) {
            a(e);
            io.e(TAG, "Could not get header fields", e);
            return new HashMap();
        }
    }

    @Override // java.net.URLConnection
    public abstract long getIfModifiedSince();

    @Override // java.net.URLConnection
    public InputStream getInputStream() throws IOException {
        try {
            el();
            return this.fB.getInputStream();
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    public abstract boolean getInstanceFollowRedirects();

    @Override // java.net.URLConnection
    public long getLastModified() {
        try {
            el();
            return this.fB.getLastModified();
        } catch (IOException e) {
            a(e);
            io.e(TAG, "Could not get last modified date", e);
            return 0L;
        }
    }

    @Override // java.net.URLConnection
    public abstract OutputStream getOutputStream() throws IOException;

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public Permission getPermission() throws IOException {
        try {
            el();
            return this.fB.getPermission();
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public abstract int getReadTimeout();

    @Override // java.net.HttpURLConnection
    public abstract String getRequestMethod();

    @Override // java.net.URLConnection
    public abstract Map<String, List<String>> getRequestProperties();

    @Override // java.net.URLConnection
    public abstract String getRequestProperty(String str);

    @Override // java.net.HttpURLConnection
    public int getResponseCode() throws IOException {
        try {
            el();
            return this.fB.getResponseCode();
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    public String getResponseMessage() throws IOException {
        try {
            el();
            return this.fB.getResponseMessage();
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public abstract URL getURL();

    @Override // java.net.URLConnection
    public abstract boolean getUseCaches();

    protected abstract HttpURLConnection performOnConnectionRequested() throws IOException;

    @Override // java.net.URLConnection
    public abstract void setAllowUserInteraction(boolean z);

    @Override // java.net.HttpURLConnection
    public abstract void setChunkedStreamingMode(int i);

    @Override // java.net.URLConnection
    public abstract void setConnectTimeout(int i);

    @Override // java.net.URLConnection
    public abstract void setDefaultUseCaches(boolean z);

    @Override // java.net.URLConnection
    public abstract void setDoInput(boolean z);

    @Override // java.net.URLConnection
    public abstract void setDoOutput(boolean z);

    @Override // java.net.HttpURLConnection
    public abstract void setFixedLengthStreamingMode(int i);

    @Override // java.net.HttpURLConnection
    public abstract void setFixedLengthStreamingMode(long j);

    @Override // java.net.URLConnection
    public abstract void setIfModifiedSince(long j);

    @Override // java.net.HttpURLConnection
    public abstract void setInstanceFollowRedirects(boolean z);

    @Override // java.net.URLConnection
    public abstract void setReadTimeout(int i);

    @Override // java.net.HttpURLConnection
    public abstract void setRequestMethod(String str) throws ProtocolException;

    @Override // java.net.URLConnection
    public abstract void setRequestProperty(String str, String str2);

    @Override // java.net.URLConnection
    public abstract void setUseCaches(boolean z);

    @Override // java.net.URLConnection
    public abstract String toString();

    @Override // java.net.HttpURLConnection
    public abstract boolean usingProxy();

    @Override // java.net.URLConnection
    public Object getContent(Class[] clsArr) throws IOException {
        try {
            el();
            return this.fB.getContent(clsArr);
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public String getHeaderField(String str) {
        try {
            el();
            return this.fB.getHeaderField(str);
        } catch (IOException e) {
            a(e);
            io.e(TAG, "Could not get header field", e);
            return null;
        }
    }
}
