package com.amazonaws.http;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Request;
import com.amazonaws.util.HttpUtils;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.animated.InterpolationAnimatedNode;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class HttpRequestFactory {
    private static final String DEFAULT_ENCODING = "UTF-8";

    private void configureHeaders(Map<String, String> map, Request<?> request, ExecutionContext executionContext, ClientConfiguration clientConfiguration) {
        URI endpoint = request.getEndpoint();
        String host = endpoint.getHost();
        if (HttpUtils.isUsingNonDefaultPort(endpoint)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(host, ":");
            outline113.append(endpoint.getPort());
            host = outline113.toString();
        }
        map.put("Host", host);
        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        if (map.get("Content-Type") == null || map.get("Content-Type").isEmpty()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("application/x-www-form-urlencoded; charset=");
            outline107.append(StringUtils.lowerCase("UTF-8"));
            map.put("Content-Type", outline107.toString());
        }
        if (executionContext == null || executionContext.getContextUserAgent() == null) {
            return;
        }
        map.put("User-Agent", createUserAgentString(clientConfiguration, executionContext.getContextUserAgent()));
    }

    private String createUserAgentString(ClientConfiguration clientConfiguration, String str) {
        if (clientConfiguration.getUserAgent().contains(str)) {
            return clientConfiguration.getUserAgent();
        }
        return clientConfiguration.getUserAgent() + " " + str;
    }

    public HttpRequest createHttpRequest(Request<?> request, ClientConfiguration clientConfiguration, ExecutionContext executionContext) {
        boolean z = true;
        String appendUri = HttpUtils.appendUri(request.getEndpoint().toString(), request.getResourcePath(), true);
        String encodeParameters = HttpUtils.encodeParameters(request);
        HttpMethodName httpMethod = request.getHttpMethod();
        boolean z2 = request.getContent() != null;
        if ((httpMethod == HttpMethodName.POST) && !z2) {
            z = false;
        }
        if (encodeParameters != null && z) {
            appendUri = GeneratedOutlineSupport1.outline75(appendUri, WebConstants.UriConstants.QUESTIONMARK_KEY, encodeParameters);
        }
        HashMap hashMap = new HashMap();
        configureHeaders(hashMap, request, executionContext, clientConfiguration);
        InputStream content = request.getContent();
        if (httpMethod == HttpMethodName.PATCH) {
            httpMethod = HttpMethodName.POST;
            hashMap.put("X-HTTP-Method-Override", HttpMethodName.PATCH.toString());
        }
        if (httpMethod == HttpMethodName.POST && request.getContent() == null && encodeParameters != null) {
            byte[] bytes = encodeParameters.getBytes(StringUtils.UTF8);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            hashMap.put("Content-Length", String.valueOf(bytes.length));
            content = byteArrayInputStream;
        }
        if (clientConfiguration.isEnableGzip() && hashMap.get("Accept-Encoding") == null) {
            hashMap.put("Accept-Encoding", "gzip");
        } else {
            hashMap.put("Accept-Encoding", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
        }
        HttpRequest httpRequest = new HttpRequest(httpMethod.toString(), URI.create(appendUri), hashMap, content);
        httpRequest.setStreaming(request.isStreaming());
        return httpRequest;
    }
}
