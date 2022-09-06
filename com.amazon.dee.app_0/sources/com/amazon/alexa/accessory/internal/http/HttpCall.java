package com.amazon.alexa.accessory.internal.http;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.WrapperUtil;
import com.amazon.alexa.accessory.io.InputStreamSource;
import com.amazon.alexa.accessory.io.OutputStreamSink;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes.dex */
public class HttpCall {
    private static final String GZIP_ENCODING = "gzip";
    private static final String HTTPS_PROTOCOL = "https";
    private final HttpRequest request;
    private WrapperUtil.ParamedSupplier<URLConnection, URL, IOException> urlConnectionSupplier;

    /* loaded from: classes.dex */
    public static class HttpResult {
        public final byte[] response;
        public final int statuseCode;

        public HttpResult(byte[] bArr, int i) {
            this.response = bArr;
            this.statuseCode = i;
        }

        public String toString() {
            StringBuilder sb;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HttpResult: response: ");
            if (this.response.length > 2000) {
                sb = GeneratedOutlineSupport1.outline107("size: ");
                sb.append(this.response.length);
            } else {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("'");
                outline1072.append(new String(this.response));
                outline1072.append("'");
                sb = outline1072;
            }
            outline107.append(sb.toString());
            outline107.append(", statusCode: ");
            outline107.append(this.statuseCode);
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpCall(HttpRequest httpRequest) {
        Preconditions.notNull(httpRequest, "request");
        this.request = httpRequest;
        this.urlConnectionSupplier = $$Lambda$HttpCall$1bGQlxmzsIbnSzFTWmQQtNfJrZI.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ HttpResult lambda$executeCompletable$1(HttpResult httpResult) throws Throwable {
        int i = httpResult.statuseCode;
        if (i == 200 || i == 201 || i == 204) {
            return httpResult;
        }
        throw new IOException("Invalid httpResult " + httpResult);
    }

    private void setCustomSslSocketFactoryIfNecessary(URL url, HttpURLConnection httpURLConnection, HttpRequest httpRequest) {
        SSLSocketFactory sslSocketFactory;
        if (!"https".equals(url.getProtocol()) || (sslSocketFactory = httpRequest.getSslSocketFactory()) == null) {
            return;
        }
        ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(sslSocketFactory);
    }

    public HttpResult execute() throws IOException {
        InputStreamSource inputStreamSource;
        URL url = new URL(this.request.getUrl());
        HttpURLConnection httpURLConnection = (HttpURLConnection) this.urlConnectionSupplier.get(url);
        setCustomSslSocketFactoryIfNecessary(url, httpURLConnection, this.request);
        try {
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod(this.request.getMethod().name());
            for (Map.Entry<String, String> entry : this.request.getHeaders().entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            if (this.request.getMethod() == HttpMethod.POST && this.request.hasBody()) {
                httpURLConnection.setRequestProperty("Content-Type", this.request.getBody().getContentType());
                httpURLConnection.setDoOutput(true);
                OutputStreamSink outputStreamSink = new OutputStreamSink(new BufferedOutputStream(httpURLConnection.getOutputStream()));
                this.request.getBody().writeTo(outputStreamSink);
                outputStreamSink.flush();
                outputStreamSink.close();
            }
            int responseCode = httpURLConnection.getResponseCode();
            Logger.d("Response code from request: %d", Integer.valueOf(responseCode));
            if (responseCode != 200 && responseCode != 201 && responseCode != 204) {
                try {
                    InputStreamSource inputStreamSource2 = new InputStreamSource(new BufferedInputStream(httpURLConnection.getErrorStream()));
                    try {
                        byte[] sourceToByteArray = IOUtils.sourceToByteArray(inputStreamSource2);
                        new String(sourceToByteArray);
                        HttpResult httpResult = new HttpResult(sourceToByteArray, responseCode);
                        Logger.d("Invalid response in http client: %s", httpResult);
                        inputStreamSource2.close();
                        return httpResult;
                    } finally {
                    }
                } catch (Exception e) {
                    Logger.e("Failed to get error body from request", e);
                    throw new IOException("Failed to get error body from request: " + responseCode);
                }
            } else {
                try {
                    if (GZIP_ENCODING.equals(httpURLConnection.getContentEncoding())) {
                        inputStreamSource = new InputStreamSource(new GZIPInputStream(httpURLConnection.getInputStream()));
                    } else {
                        inputStreamSource = new InputStreamSource(new BufferedInputStream(httpURLConnection.getInputStream()));
                    }
                    return new HttpResult(IOUtils.sourceToByteArray(inputStreamSource), responseCode);
                } catch (Exception e2) {
                    Logger.e("Failed to read http response", e2);
                    throw new IOException("Failed to read http response", e2);
                }
            }
        } finally {
        }
        httpURLConnection.disconnect();
    }

    public Completable executeCompletable() {
        return executeSingle().map($$Lambda$HttpCall$9M9AcTzLuTYipWUnsTw2DjoXIGE.INSTANCE).ignoreElement();
    }

    public Single<HttpResult> executeSingle() {
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.accessory.internal.http.-$$Lambda$Ha6C530Y6pkgsjaVbRlKJLJlprc
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return HttpCall.this.execute();
            }
        }).subscribeOn(Schedulers.io());
    }

    @VisibleForTesting
    void setUrlConnectionSupplierForTest(WrapperUtil.ParamedSupplier<URLConnection, URL, IOException> paramedSupplier) {
        this.urlConnectionSupplier = paramedSupplier;
    }
}
