package com.amazonaws.services.s3.internal;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.http.HttpResponse;
import com.amazonaws.http.HttpResponseHandler;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.S3ResponseMetadata;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.util.DateUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes13.dex */
public abstract class AbstractS3ResponseHandler<T> implements HttpResponseHandler<AmazonWebServiceResponse<T>> {
    private static final Log log = LogFactory.getLog(S3MetadataResponseHandler.class);
    private static final Set<String> IGNORED_HEADERS = new HashSet();

    static {
        IGNORED_HEADERS.add("Date");
        IGNORED_HEADERS.add("Server");
        IGNORED_HEADERS.add(Headers.REQUEST_ID);
        IGNORED_HEADERS.add(Headers.EXTENDED_REQUEST_ID);
        IGNORED_HEADERS.add(Headers.CLOUD_FRONT_ID);
        IGNORED_HEADERS.add("Connection");
    }

    @Override // com.amazonaws.http.HttpResponseHandler
    public boolean needsConnectionLeftOpen() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AmazonWebServiceResponse<T> parseResponseMetadata(HttpResponse httpResponse) {
        AmazonWebServiceResponse<T> amazonWebServiceResponse = new AmazonWebServiceResponse<>();
        HashMap outline134 = GeneratedOutlineSupport1.outline134(ResponseMetadata.AWS_REQUEST_ID, httpResponse.getHeaders().get(Headers.REQUEST_ID), S3ResponseMetadata.HOST_ID, httpResponse.getHeaders().get(Headers.EXTENDED_REQUEST_ID));
        outline134.put(S3ResponseMetadata.CLOUD_FRONT_ID, httpResponse.getHeaders().get(Headers.CLOUD_FRONT_ID));
        amazonWebServiceResponse.setResponseMetadata(new S3ResponseMetadata(outline134));
        return amazonWebServiceResponse;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void populateObjectMetadata(HttpResponse httpResponse, ObjectMetadata objectMetadata) {
        for (Map.Entry<String, String> entry : httpResponse.getHeaders().entrySet()) {
            String key = entry.getKey();
            if (key.startsWith(Headers.S3_USER_METADATA_PREFIX)) {
                objectMetadata.addUserMetadata(key.substring(11), entry.getValue());
            } else if (IGNORED_HEADERS.contains(key)) {
                log.debug(String.format("%s is ignored.", key));
            } else if (key.equalsIgnoreCase("Last-Modified")) {
                try {
                    objectMetadata.setHeader(key, ServiceUtils.parseRfc822Date(entry.getValue()));
                } catch (Exception e) {
                    Log log2 = log;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to parse last modified date: ");
                    outline107.append(entry.getValue());
                    log2.warn(outline107.toString(), e);
                }
            } else if (key.equalsIgnoreCase("Content-Length")) {
                try {
                    objectMetadata.setHeader(key, Long.valueOf(Long.parseLong(entry.getValue())));
                } catch (NumberFormatException e2) {
                    Log log3 = log;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unable to parse content length: ");
                    outline1072.append(entry.getValue());
                    log3.warn(outline1072.toString(), e2);
                }
            } else if (key.equalsIgnoreCase("ETag")) {
                objectMetadata.setHeader(key, ServiceUtils.removeQuotes(entry.getValue()));
            } else if (key.equalsIgnoreCase("Expires")) {
                try {
                    objectMetadata.setHttpExpiresDate(DateUtils.parseRFC822Date(entry.getValue()));
                } catch (Exception e3) {
                    Log log4 = log;
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Unable to parse http expiration date: ");
                    outline1073.append(entry.getValue());
                    log4.warn(outline1073.toString(), e3);
                }
            } else if (key.equalsIgnoreCase(Headers.EXPIRATION)) {
                new ObjectExpirationHeaderHandler().handle((ObjectExpirationHeaderHandler) objectMetadata, httpResponse);
            } else if (key.equalsIgnoreCase(Headers.RESTORE)) {
                new ObjectRestoreHeaderHandler().handle((ObjectRestoreHeaderHandler) objectMetadata, httpResponse);
            } else if (key.equalsIgnoreCase(Headers.REQUESTER_CHARGED_HEADER)) {
                new S3RequesterChargedHeaderHandler().handle((S3RequesterChargedHeaderHandler) objectMetadata, httpResponse);
            } else if (key.equalsIgnoreCase(Headers.S3_PARTS_COUNT)) {
                try {
                    objectMetadata.setHeader(key, Integer.valueOf(Integer.parseInt(entry.getValue())));
                } catch (NumberFormatException e4) {
                    StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Unable to parse part count. Header x-amz-mp-parts-count has corrupted data");
                    outline1074.append(e4.getMessage());
                    throw new AmazonClientException(outline1074.toString(), e4);
                }
            } else {
                objectMetadata.setHeader(key, entry.getValue());
            }
        }
    }
}
