package com.amazonaws.http;

import com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.http.HttpResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
/* loaded from: classes13.dex */
public class ApacheHttpClient implements HttpClient {
    private final org.apache.http.client.HttpClient httpClient;
    private HttpParams params = null;

    public ApacheHttpClient(ClientConfiguration clientConfiguration) {
        this.httpClient = new HttpClientFactory().createHttpClient(clientConfiguration);
        this.httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        ((SSLSocketFactory) this.httpClient.getConnectionManager().getSchemeRegistry().getScheme("https").getSocketFactory()).setHostnameVerifier(SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    private HttpUriRequest createHttpRequest(HttpRequest httpRequest) {
        HttpPost httpHead;
        String method = httpRequest.getMethod();
        if ("POST".equals(method)) {
            httpHead = new HttpPost(httpRequest.getUri());
            if (httpRequest.getContent() != null) {
                httpHead.setEntity(new InputStreamEntity(httpRequest.getContent(), httpRequest.getContentLength()));
            }
        } else if ("GET".equals(method)) {
            httpHead = new HttpGet(httpRequest.getUri());
        } else if (SmartDeviceDataProvider.METHOD_HTTP_PUT.equals(method)) {
            httpHead = new HttpPut(httpRequest.getUri());
            if (httpRequest.getContent() != null) {
                httpHead.setEntity(new InputStreamEntity(httpRequest.getContent(), httpRequest.getContentLength()));
            }
        } else if (Constants.REQUEST_METHOD_DELETE.equals(method)) {
            httpHead = new HttpDelete(httpRequest.getUri());
        } else if ("HEAD".equals(method)) {
            httpHead = new HttpHead(httpRequest.getUri());
        } else {
            throw new UnsupportedOperationException(GeneratedOutlineSupport1.outline72("Unsupported method: ", method));
        }
        if (httpRequest.getHeaders() != null && !httpRequest.getHeaders().isEmpty()) {
            for (Map.Entry<String, String> entry : httpRequest.getHeaders().entrySet()) {
                String key = entry.getKey();
                if (!key.equals("Content-Length") && !key.equals("Host")) {
                    httpHead.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }
        if (this.params == null) {
            this.params = new BasicHttpParams();
            this.params.setParameter("http.protocol.handle-redirects", false);
        }
        httpHead.setParams(this.params);
        return httpHead;
    }

    @Override // com.amazonaws.http.HttpClient
    public HttpResponse execute(HttpRequest httpRequest) throws IOException {
        Header[] allHeaders;
        org.apache.http.HttpResponse execute = this.httpClient.execute(createHttpRequest(httpRequest));
        HttpResponse.Builder content = HttpResponse.builder().statusCode(execute.getStatusLine().getStatusCode()).statusText(execute.getStatusLine().getReasonPhrase()).content(execute.getEntity() != null ? execute.getEntity().getContent() : null);
        for (Header header : execute.getAllHeaders()) {
            content.header(header.getName(), header.getValue());
        }
        return content.build();
    }

    @Override // com.amazonaws.http.HttpClient
    public void shutdown() {
        this.httpClient.getConnectionManager().shutdown();
    }
}
