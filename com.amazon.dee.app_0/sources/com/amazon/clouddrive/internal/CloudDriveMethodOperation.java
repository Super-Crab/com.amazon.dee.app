package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.internal.RequestPathGenerator;
import com.amazon.clouddrive.metrics.MetricListener;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import okhttp3.MediaType;
import okhttp3.RequestBody;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class CloudDriveMethodOperation<Response> extends AbstractCloudDriveOperation<Response> {
    static final String DELETE_METHOD = "DELETE";
    private static final RequestBody EMPTY_BODY = RequestBody.create((MediaType) null, new byte[0]);
    static final String GET_METHOD = "GET";
    static final String PATCH_METHOD = "PATCH";
    static final String POST_METHOD = "POST";
    static final String PUT_METHOD = "PUT";
    private final JsonDeserializer<Response> mDeserializer;
    private boolean mIsHeadRequest;
    private final String mMethod;
    private final RequestPathGenerator.RequestPath mRequestPath;
    private final RequestPropertyWriter mRequestPropertyWriter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CloudDriveMethodOperation(OperationFactory operationFactory, ClientConfiguration clientConfiguration, AccountConfiguration accountConfiguration, SourceInfoGenerator sourceInfoGenerator, String str, RequestPathGenerator.RequestPath requestPath, JsonDeserializer<Response> jsonDeserializer, String str2, MetricListener metricListener, Class<?> cls) {
        this(operationFactory, clientConfiguration, accountConfiguration, sourceInfoGenerator, str, requestPath, null, jsonDeserializer, str2, metricListener, cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void isHeadRequest(boolean z) {
        this.mIsHeadRequest = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00aa  */
    @Override // com.amazon.clouddrive.internal.AbstractCloudDriveOperation
    /* renamed from: retryCall */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final Response mo3157retryCall() throws com.amazon.clouddrive.exceptions.CloudDriveException, java.lang.InterruptedException {
        /*
            r6 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            com.amazon.clouddrive.internal.RequestPathGenerator$RequestPath r2 = r6.mRequestPath     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            java.lang.String r2 = r2.getPath()     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            okhttp3.Request$Builder r1 = r6.setUpRequest(r1)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            java.lang.String r2 = r6.mMethod     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            boolean r2 = okhttp3.internal.http.HttpMethod.requiresRequestBody(r2)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            if (r2 == 0) goto L20
            java.lang.String r2 = r6.mMethod     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            okhttp3.RequestBody r3 = com.amazon.clouddrive.internal.CloudDriveMethodOperation.EMPTY_BODY     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            r1.method(r2, r3)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            goto L25
        L20:
            java.lang.String r2 = r6.mMethod     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            r1.method(r2, r0)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
        L25:
            boolean r2 = r6.mIsHeadRequest     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            if (r2 == 0) goto L30
            java.lang.String r2 = "Accept-Encoding"
            java.lang.String r3 = ""
            r1.header(r2, r3)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
        L30:
            com.amazon.clouddrive.internal.RequestPropertyWriter r2 = r6.mRequestPropertyWriter     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            if (r2 == 0) goto L39
            com.amazon.clouddrive.internal.RequestPropertyWriter r2 = r6.mRequestPropertyWriter     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            r2.writeHeaders(r1)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
        L39:
            okhttp3.OkHttpClient r2 = r6.mClient     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            okhttp3.Request r1 = r1.build()     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            okhttp3.Call r1 = r2.newCall(r1)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            okhttp3.Response r1 = r1.execute()     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a java.io.InterruptedIOException -> L99
            r6.assertSuccessResponseCode(r1)     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7d java.io.InterruptedIOException -> L83
            com.amazon.clouddrive.model.deserializer.JsonDeserializer<Response> r2 = r6.mDeserializer     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7d java.io.InterruptedIOException -> L83
            if (r2 != 0) goto L57
            com.amazon.clouddrive.internal.utils.Closer.closeQuietly(r0)
            if (r1 == 0) goto L56
            r1.close()
        L56:
            return r0
        L57:
            com.amazon.clouddrive.model.deserializer.JsonDeserializer<Response> r2 = r6.mDeserializer     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7d java.io.InterruptedIOException -> L83
            boolean r2 = r2 instanceof com.amazon.clouddrive.internal.ResponsePropertyReader     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7d java.io.InterruptedIOException -> L83
            if (r2 == 0) goto L64
            com.amazon.clouddrive.model.deserializer.JsonDeserializer<Response> r2 = r6.mDeserializer     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7d java.io.InterruptedIOException -> L83
            com.amazon.clouddrive.internal.ResponsePropertyReader r2 = (com.amazon.clouddrive.internal.ResponsePropertyReader) r2     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7d java.io.InterruptedIOException -> L83
            r2.readHeaders(r1)     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7d java.io.InterruptedIOException -> L83
        L64:
            okhttp3.ResponseBody r2 = r1.body()     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7d java.io.InterruptedIOException -> L83
            java.io.InputStream r0 = r2.byteStream()     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7d java.io.InterruptedIOException -> L83
            com.amazon.clouddrive.model.deserializer.JsonDeserializer<Response> r2 = r6.mDeserializer     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7d java.io.InterruptedIOException -> L83
            java.lang.Object r2 = com.amazon.clouddrive.internal.CloudDriveObjectMapper.readStream(r0, r2)     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7d java.io.InterruptedIOException -> L83
            com.amazon.clouddrive.internal.utils.Closer.closeQuietly(r0)
            r1.close()
            return r2
        L79:
            r2 = move-exception
            r5 = r2
            r2 = r1
            goto La4
        L7d:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r1
            r1 = r5
            goto L8c
        L83:
            r5 = r1
            r1 = r0
            r0 = r5
            goto L9a
        L87:
            r1 = move-exception
            r2 = r0
            goto La5
        L8a:
            r1 = move-exception
            r2 = r0
        L8c:
            com.amazon.clouddrive.exceptions.ActionRequiredException r3 = new com.amazon.clouddrive.exceptions.ActionRequiredException     // Catch: java.lang.Throwable -> L94
            java.lang.String r4 = "Failure in creating a connection"
            r3.<init>(r4, r1)     // Catch: java.lang.Throwable -> L94
            throw r3     // Catch: java.lang.Throwable -> L94
        L94:
            r1 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto La5
        L99:
            r1 = r0
        L9a:
            java.lang.InterruptedException r2 = new java.lang.InterruptedException     // Catch: java.lang.Throwable -> La0
            r2.<init>()     // Catch: java.lang.Throwable -> La0
            throw r2     // Catch: java.lang.Throwable -> La0
        La0:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r1
        La4:
            r1 = r5
        La5:
            com.amazon.clouddrive.internal.utils.Closer.closeQuietly(r0)
            if (r2 == 0) goto Lad
            r2.close()
        Lad:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.clouddrive.internal.CloudDriveMethodOperation.mo3157retryCall():java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CloudDriveMethodOperation(OperationFactory operationFactory, ClientConfiguration clientConfiguration, AccountConfiguration accountConfiguration, SourceInfoGenerator sourceInfoGenerator, String str, RequestPathGenerator.RequestPath requestPath, RequestPropertyWriter requestPropertyWriter, JsonDeserializer<Response> jsonDeserializer, String str2, MetricListener metricListener, Class<?> cls) {
        super(operationFactory, clientConfiguration, accountConfiguration, sourceInfoGenerator, str2, metricListener, cls);
        this.mIsHeadRequest = false;
        this.mMethod = str;
        this.mRequestPath = requestPath;
        this.mRequestPropertyWriter = requestPropertyWriter;
        this.mDeserializer = jsonDeserializer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CloudDriveMethodOperation(OperationFactory operationFactory, ClientConfiguration clientConfiguration, AccountConfiguration accountConfiguration, SourceInfoGenerator sourceInfoGenerator, String str, RequestPathGenerator.RequestPath requestPath, RequestPropertyWriter requestPropertyWriter, JsonDeserializer<Response> jsonDeserializer, String str2, MetricListener metricListener, String str3, Class<?> cls) {
        super(operationFactory, clientConfiguration, accountConfiguration, sourceInfoGenerator, str2, metricListener, str3, cls);
        this.mIsHeadRequest = false;
        this.mMethod = str;
        this.mRequestPath = requestPath;
        this.mRequestPropertyWriter = requestPropertyWriter;
        this.mDeserializer = jsonDeserializer;
    }
}
