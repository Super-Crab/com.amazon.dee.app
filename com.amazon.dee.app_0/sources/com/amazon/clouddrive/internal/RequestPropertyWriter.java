package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import java.net.HttpURLConnection;
import okhttp3.Request;
/* loaded from: classes11.dex */
interface RequestPropertyWriter {
    public static final String CONTENT_RANGE_FORMAT = "bytes %d-%d/%d";
    public static final String DEFAULT_MIME_TYPE = "application/octet-stream";
    public static final String PROPERTY_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String PROPERTY_CONTENT_LENGTH = "Content-Length";
    public static final String PROPERTY_CONTENT_MD5 = "Content-MD5";
    public static final String PROPERTY_CONTENT_RANGE = "Content-Range";
    public static final String PROPERTY_CONTENT_TYPE = "Content-Type";

    @Deprecated
    void writeHeaders(HttpURLConnection httpURLConnection) throws CloudDriveException, InterruptedException;

    void writeHeaders(Request.Builder builder) throws CloudDriveException, InterruptedException;
}
