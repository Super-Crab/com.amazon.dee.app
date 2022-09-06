package com.amazonaws.util;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.Request;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
/* loaded from: classes.dex */
public class RuntimeHttpUtils {
    private static final String COMMA = ", ";
    private static final String SPACE = " ";

    public static URL convertRequestToUrl(Request<?> request, boolean z, boolean z2) {
        String resourcePath;
        if (z2) {
            resourcePath = HttpUtils.urlEncode(request.getResourcePath(), true);
        } else {
            resourcePath = request.getResourcePath();
        }
        if (z && resourcePath.startsWith("/")) {
            resourcePath = resourcePath.substring(1);
        }
        String replaceAll = ("/" + resourcePath).replaceAll("(?<=/)/", "%2F");
        StringBuilder sb = new StringBuilder(request.getEndpoint().toString());
        sb.append(replaceAll);
        StringBuilder sb2 = new StringBuilder();
        for (Map.Entry<String, String> entry : request.getParameters().entrySet()) {
            sb2.append(sb2.length() > 0 ? WebConstants.UriConstants.AMPERSAND_KEY : WebConstants.UriConstants.QUESTIONMARK_KEY);
            sb2.append(HttpUtils.urlEncode(entry.getKey(), false));
            sb2.append(Config.Compare.EQUAL_TO);
            sb2.append(HttpUtils.urlEncode(entry.getValue(), false));
        }
        sb.append(sb2.toString());
        try {
            return new URL(sb.toString());
        } catch (MalformedURLException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to convert request to well formed URL: ");
            outline107.append(e.getMessage());
            throw new AmazonClientException(outline107.toString(), e);
        }
    }

    public static URI toUri(String str, ClientConfiguration clientConfiguration) {
        if (clientConfiguration != null) {
            return toUri(str, clientConfiguration.getProtocol());
        }
        throw new IllegalArgumentException("ClientConfiguration cannot be null");
    }

    public static URI toUri(String str, Protocol protocol) {
        if (str != null) {
            if (!str.contains("://")) {
                str = protocol.toString() + "://" + str;
            }
            try {
                return new URI(str);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException(e);
            }
        }
        throw new IllegalArgumentException("endpoint cannot be null");
    }
}
