package com.amazon.communication.utils;

import com.amazon.alexa.biloba.utils.WebConstants;
import java.net.URI;
/* loaded from: classes12.dex */
public class HttpUtils {
    public static String getRawFullUri(URI uri) {
        String rawPath;
        if (uri.getQuery() != null) {
            rawPath = uri.getRawPath() + WebConstants.UriConstants.QUESTIONMARK_KEY + uri.getRawQuery();
        } else {
            rawPath = uri.getRawPath();
        }
        return rawPath.length() == 0 ? "/" : rawPath;
    }
}
