package com.amazonaws.services.s3.internal;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazonaws.Request;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.util.StringUtils;
import com.sun.mail.imap.IMAPStore;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes13.dex */
public class RestUtils {
    private static final List<String> SIGNED_PARAMETERS = Arrays.asList("acl", "torrent", "logging", "location", "policy", "requestPayment", "versioning", "versions", "versionId", "notification", "uploadId", "uploads", "partNumber", "website", "delete", "lifecycle", "tagging", "cors", "restore", "replication", "accelerate", "inventory", "analytics", "metrics", ResponseHeaderOverrides.RESPONSE_HEADER_CACHE_CONTROL, ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_DISPOSITION, ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_ENCODING, ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_LANGUAGE, ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_TYPE, ResponseHeaderOverrides.RESPONSE_HEADER_EXPIRES);

    public static <T> String makeS3CanonicalString(String str, String str2, Request<T> request, String str3) {
        return makeS3CanonicalString(str, str2, request, str3, null);
    }

    public static <T> String makeS3CanonicalString(String str, String str2, Request<T> request, String str3, Collection<String> collection) {
        StringBuilder sb = new StringBuilder();
        sb.append(str + "\n");
        Map<String, String> headers = request.getHeaders();
        TreeMap treeMap = new TreeMap();
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key != null) {
                    String lowerCase = StringUtils.lowerCase(key);
                    if ("content-type".equals(lowerCase) || "content-md5".equals(lowerCase) || IMAPStore.ID_DATE.equals(lowerCase) || lowerCase.startsWith(Headers.AMAZON_PREFIX)) {
                        treeMap.put(lowerCase, value);
                    }
                }
            }
        }
        if (treeMap.containsKey(Headers.S3_ALTERNATE_DATE)) {
            treeMap.put(IMAPStore.ID_DATE, "");
        }
        if (str3 != null) {
            treeMap.put(IMAPStore.ID_DATE, str3);
        }
        if (!treeMap.containsKey("content-type")) {
            treeMap.put("content-type", "");
        }
        if (!treeMap.containsKey("content-md5")) {
            treeMap.put("content-md5", "");
        }
        for (Map.Entry<String, String> entry2 : request.getParameters().entrySet()) {
            if (entry2.getKey().startsWith(Headers.AMAZON_PREFIX)) {
                treeMap.put(entry2.getKey(), entry2.getValue());
            }
        }
        for (Map.Entry entry3 : treeMap.entrySet()) {
            String str4 = (String) entry3.getKey();
            String str5 = (String) entry3.getValue();
            if (str4.startsWith(Headers.AMAZON_PREFIX)) {
                sb.append(str4);
                sb.append(JsonReaderKt.COLON);
                if (str5 != null) {
                    sb.append(str5);
                }
            } else if (str5 != null) {
                sb.append(str5);
            }
            sb.append("\n");
        }
        sb.append(str2);
        String[] strArr = (String[]) request.getParameters().keySet().toArray(new String[request.getParameters().size()]);
        Arrays.sort(strArr);
        char c = com.amazon.deecomms.common.Constants.DEFAULT_IMAGE_CHAR;
        for (String str6 : strArr) {
            if (SIGNED_PARAMETERS.contains(str6) || (collection != null && collection.contains(str6))) {
                if (sb.length() == 0) {
                    sb.append(c);
                }
                sb.append(str6);
                String str7 = request.getParameters().get(str6);
                if (str7 != null) {
                    sb.append(Config.Compare.EQUAL_TO);
                    sb.append(str7);
                }
                c = Typography.amp;
            }
        }
        return sb.toString();
    }
}
