package com.amazonaws.util;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public class HttpUtils {
    private static final Pattern DECODED_CHARACTERS_PATTERN;
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final Pattern ENCODED_CHARACTERS_PATTERN = Pattern.compile(Pattern.quote("+") + AccessoryMetricsConstants.DELIMITER + Pattern.quote("*") + AccessoryMetricsConstants.DELIMITER + Pattern.quote("%7E") + AccessoryMetricsConstants.DELIMITER + Pattern.quote("%2F"));
    private static final int HTTP_STATUS_OK = 200;
    private static final int PORT_HTTP = 80;
    private static final int PORT_HTTPS = 443;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(Pattern.quote("%2A"));
        sb.append(AccessoryMetricsConstants.DELIMITER);
        sb.append(Pattern.quote("%2B"));
        sb.append(AccessoryMetricsConstants.DELIMITER);
        DECODED_CHARACTERS_PATTERN = Pattern.compile(sb.toString());
    }

    public static String appendUri(String str, String str2) {
        return appendUri(str, str2, false);
    }

    public static String encodeParameters(Request<?> request) {
        if (request.getParameters().isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        try {
            for (Map.Entry<String, String> entry : request.getParameters().entrySet()) {
                String encode = URLEncoder.encode(entry.getKey(), "UTF-8");
                String value = entry.getValue();
                String encode2 = value == null ? "" : URLEncoder.encode(value, "UTF-8");
                if (!z) {
                    sb.append(WebConstants.UriConstants.AMPERSAND_KEY);
                } else {
                    z = false;
                }
                sb.append(encode);
                sb.append(Config.Compare.EQUAL_TO);
                sb.append(encode2);
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static InputStream fetchFile(URI uri, ClientConfiguration clientConfiguration) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) uri.toURL().openConnection();
        httpURLConnection.setConnectTimeout(getConnectionTimeout(clientConfiguration));
        httpURLConnection.setReadTimeout(getSocketTimeout(clientConfiguration));
        httpURLConnection.addRequestProperty("User-Agent", getUserAgent(clientConfiguration));
        if (httpURLConnection.getResponseCode() != 200) {
            InputStream errorStream = httpURLConnection.getErrorStream();
            if (errorStream != null) {
                errorStream.close();
            }
            httpURLConnection.disconnect();
            throw new IOException("Error fetching file from " + uri + RealTimeTextConstants.COLON_SPACE + httpURLConnection.getResponseMessage());
        }
        return httpURLConnection.getInputStream();
    }

    static int getConnectionTimeout(ClientConfiguration clientConfiguration) {
        if (clientConfiguration != null) {
            return clientConfiguration.getConnectionTimeout();
        }
        return 15000;
    }

    static int getSocketTimeout(ClientConfiguration clientConfiguration) {
        if (clientConfiguration != null) {
            return clientConfiguration.getSocketTimeout();
        }
        return 15000;
    }

    static String getUserAgent(ClientConfiguration clientConfiguration) {
        String userAgent = clientConfiguration != null ? clientConfiguration.getUserAgent() : null;
        if (userAgent == null) {
            return ClientConfiguration.DEFAULT_USER_AGENT;
        }
        if (ClientConfiguration.DEFAULT_USER_AGENT.equals(userAgent)) {
            return userAgent;
        }
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(userAgent, ", ");
        outline113.append(ClientConfiguration.DEFAULT_USER_AGENT);
        return outline113.toString();
    }

    public static boolean isUsingNonDefaultPort(URI uri) {
        String lowerCase = StringUtils.lowerCase(uri.getScheme());
        int port = uri.getPort();
        if (port <= 0) {
            return false;
        }
        if ("http".equals(lowerCase) && port == 80) {
            return false;
        }
        return !"https".equals(lowerCase) || port != PORT_HTTPS;
    }

    public static String urlDecode(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String urlEncode(String str, boolean z) {
        if (str == null) {
            return "";
        }
        try {
            String encode = URLEncoder.encode(str, "UTF-8");
            Matcher matcher = ENCODED_CHARACTERS_PATTERN.matcher(encode);
            StringBuffer stringBuffer = new StringBuffer(encode.length());
            while (matcher.find()) {
                String group = matcher.group(0);
                if ("+".equals(group)) {
                    group = "%20";
                } else if ("*".equals(group)) {
                    group = "%2A";
                } else if ("%7E".equals(group)) {
                    group = "~";
                } else if (z && "%2F".equals(group)) {
                    group = "/";
                }
                matcher.appendReplacement(stringBuffer, group);
            }
            matcher.appendTail(stringBuffer);
            return stringBuffer.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean usePayloadForQueryParameters(Request<?> request) {
        return HttpMethodName.POST.equals(request.getHttpMethod()) && (request.getContent() == null);
    }

    public static String appendUri(String str, String str2, boolean z) {
        if (str2 == null || str2.length() <= 0) {
            return !str.endsWith("/") ? GeneratedOutlineSupport1.outline72(str, "/") : str;
        }
        if (str2.startsWith("/")) {
            if (str.endsWith("/")) {
                str = GeneratedOutlineSupport1.outline51(str, 1, 0);
            }
        } else if (!str.endsWith("/")) {
            str = GeneratedOutlineSupport1.outline72(str, "/");
        }
        String urlEncode = urlEncode(str2, true);
        if (z) {
            urlEncode = urlEncode.replace("//", "/%2F");
        }
        return GeneratedOutlineSupport1.outline72(str, urlEncode);
    }
}
