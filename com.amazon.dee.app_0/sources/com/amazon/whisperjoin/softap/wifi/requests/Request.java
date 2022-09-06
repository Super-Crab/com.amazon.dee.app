package com.amazon.whisperjoin.softap.wifi.requests;

import android.util.Log;
import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
/* loaded from: classes13.dex */
public class Request {
    private static final String TAG = "Request";
    private final String contentType;
    private final byte[] requestBody;
    private final RequestBuilder.RequestMethod requestMethod;
    private final URL requestUrl;
    private int responseCode = -1;
    private String responseString;
    private final int timeoutMs;

    /* renamed from: com.amazon.whisperjoin.softap.wifi.requests.Request$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$softap$wifi$requests$RequestBuilder$RequestMethod = new int[RequestBuilder.RequestMethod.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$softap$wifi$requests$RequestBuilder$RequestMethod[RequestBuilder.RequestMethod.POST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$softap$wifi$requests$RequestBuilder$RequestMethod[RequestBuilder.RequestMethod.GET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request(URL url, String str, int i, RequestBuilder.RequestMethod requestMethod, byte[] bArr) throws IOException {
        this.requestUrl = url;
        this.contentType = str;
        this.timeoutMs = i;
        this.requestMethod = requestMethod;
        this.requestBody = bArr;
    }

    private void addHeaders(HttpUriRequest httpUriRequest) {
        String str = this.contentType;
        if (str != null) {
            httpUriRequest.setHeader(FileUploadBase.CONTENT_TYPE, str);
        }
    }

    private void checkResponse(HttpResponse httpResponse) throws IOException {
        this.responseCode = httpResponse.getStatusLine().getStatusCode();
        this.responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        httpResponse.getEntity().consumeContent();
        if (isSuccessful()) {
            return;
        }
        RequestException requestException = new RequestException(this.responseCode);
        Log.e(TAG, String.format("Request failed: %s", this.responseString), requestException);
        throw requestException;
    }

    private HttpUriRequest createGetRequest() throws IOException {
        return new HttpGet(createURI());
    }

    private HttpUriRequest createPostRequest() throws IOException {
        HttpPost httpPost = new HttpPost(createURI());
        byte[] bArr = this.requestBody;
        if (bArr != null) {
            httpPost.setEntity(new ByteArrayEntity(bArr));
        }
        return httpPost;
    }

    private HttpUriRequest createRequest() throws IOException {
        int ordinal = this.requestMethod.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return createPostRequest();
            }
            throw new UnsupportedOperationException(String.format("Request method %s not supported.", this.requestMethod.name()));
        }
        return createGetRequest();
    }

    private URI createURI() throws IOException {
        try {
            return this.requestUrl.toURI();
        } catch (URISyntaxException e) {
            throw new RequestException("Error occurred while creating the URI", e);
        }
    }

    private HttpResponse executeRequest(HttpUriRequest httpUriRequest) throws IOException {
        try {
            BasicHttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, this.timeoutMs);
            return new DefaultHttpClient(basicHttpParams).execute(httpUriRequest);
        } catch (ConnectTimeoutException e) {
            throw new RequestTimeoutException("The request timed out", e);
        } catch (IOException e2) {
            throw new RequestException("Error occurred while executing the request", e2);
        }
    }

    private boolean isSuccessful() {
        return this.responseCode == 200;
    }

    public String getResponseString() {
        return this.responseString;
    }

    public void makeRequest() throws IOException {
        HttpUriRequest createRequest = createRequest();
        addHeaders(createRequest);
        checkResponse(executeRequest(createRequest));
    }
}
