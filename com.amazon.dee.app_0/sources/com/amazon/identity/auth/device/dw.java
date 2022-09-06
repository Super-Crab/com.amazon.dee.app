package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.framework.RetryLogic;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class dw extends eq {
    private static final String TAG = dw.class.getName();
    private static final int kW = (int) TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS);
    private static final int kX = (int) TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS);
    private final ej bR;
    private final Cdo kY;
    private final RetryLogic kZ;
    private final lt la;
    private final Context mContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public dw(URL url, RetryLogic retryLogic, ej ejVar, Context context) throws IOException {
        this(new Cdo(url), retryLogic, ejVar, context);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void addRequestProperty(String str, String str2) {
        this.kY.addRequestProperty(str, str2);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public boolean getAllowUserInteraction() {
        return this.kY.getAllowUserInteraction();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public int getConnectTimeout() {
        return this.kY.getConnectTimeout();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public boolean getDefaultUseCaches() {
        return this.kY.getDefaultUseCaches();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public boolean getDoInput() {
        return this.kY.getDoInput();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public boolean getDoOutput() {
        return this.kY.getDoOutput();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public long getIfModifiedSince() {
        return this.kY.getIfModifiedSince();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    public boolean getInstanceFollowRedirects() {
        return this.kY.getInstanceFollowRedirects();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public OutputStream getOutputStream() throws IOException {
        return this.kY.getOutputStream();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public int getReadTimeout() {
        return this.kY.getReadTimeout();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    public String getRequestMethod() {
        return this.kY.getRequestMethod();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public Map<String, List<String>> getRequestProperties() {
        return this.kY.getRequestProperties();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public String getRequestProperty(String str) {
        return this.kY.getRequestProperty(str);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public URL getURL() {
        return this.kY.getURL();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public boolean getUseCaches() {
        return this.kY.getUseCaches();
    }

    @Override // com.amazon.identity.auth.device.eq
    protected HttpURLConnection performOnConnectionRequested() throws IOException {
        dv dvVar;
        RetryLogic.a a;
        do {
            try {
                dvVar = new dv(this.kY.cV());
                a = this.kZ.a(dvVar, this.la.it(), this.bR);
                if (a.isSuccess() || a.dP()) {
                    return dvVar;
                }
                dvVar.disconnect();
                int is = this.la.is();
                io.e(TAG, String.format(Locale.ENGLISH, "Connection failed. We will attempt to the %d retry", Integer.valueOf(this.la.it())));
                try {
                    Thread.sleep(is);
                } catch (InterruptedException e) {
                    io.w(TAG, "Backoff wait interrupted", e);
                }
            } catch (IOException e2) {
                this.bR.bA(mp.i(((HttpURLConnection) this).url));
                this.bR.bA(mp.a(((HttpURLConnection) this).url, e2, this.mContext));
                throw e2;
            }
        } while (this.la.it() < 2);
        RetryLogic.RetryErrorMessageFromServerSide dO = a.dO();
        IOException dN = a.dN();
        if (dO != null) {
            io.i(TAG, "Connection failed: " + dO.getReason());
            if (dO.equals(RetryLogic.RetryErrorMessageFromServerSide.ServerInternalError) || dO.equals(RetryLogic.RetryErrorMessageFromServerSide.InvalidJSON)) {
                return dvVar;
            }
        }
        if (dN == null) {
            return dvVar;
        }
        io.e(TAG, "All retries failed. Aborting request");
        URL url = dvVar.getURL();
        String str = mp.h(url) + ":AllRetriesFailed";
        io.a(TAG, null, str, str);
        throw dN;
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setAllowUserInteraction(boolean z) {
        this.kY.setAllowUserInteraction(z);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    public void setChunkedStreamingMode(int i) {
        this.kY.setChunkedStreamingMode(i);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setConnectTimeout(int i) {
        this.kY.setConnectTimeout(i);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setDefaultUseCaches(boolean z) {
        this.kY.setDefaultUseCaches(z);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setDoInput(boolean z) {
        this.kY.setDoInput(z);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setDoOutput(boolean z) {
        this.kY.setDoOutput(z);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(int i) {
        this.kY.setFixedLengthStreamingMode(i);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setIfModifiedSince(long j) {
        this.kY.setIfModifiedSince(j);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    public void setInstanceFollowRedirects(boolean z) {
        this.kY.setInstanceFollowRedirects(z);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setReadTimeout(int i) {
        this.kY.setReadTimeout(i);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    public void setRequestMethod(String str) throws ProtocolException {
        this.kY.setRequestMethod(str);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setRequestProperty(String str, String str2) {
        this.kY.setRequestProperty(str, str2);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public void setUseCaches(boolean z) {
        this.kY.setUseCaches(z);
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.URLConnection
    public String toString() {
        return this.kY.toString();
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    public boolean usingProxy() {
        return this.kY.usingProxy();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public dw(Cdo cdo, RetryLogic retryLogic, ej ejVar, Context context) {
        super(cdo.getURL());
        this.kY = cdo;
        this.kZ = retryLogic;
        this.la = new lt(kW, kX);
        this.bR = ejVar;
        this.mContext = context;
    }

    @Override // com.amazon.identity.auth.device.eq, java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(long j) {
        this.kY.setFixedLengthStreamingMode(j);
    }
}
