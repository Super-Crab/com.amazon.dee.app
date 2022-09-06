package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.internal.RequestPathGenerator;
import com.amazon.clouddrive.metrics.MetricListener;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class UploadFileOperation<Response> extends AbstractCloudDriveOperation<Response> {
    private JsonDeserializer<Response> mDeserializer;
    private String mMethod;
    private RequestPathGenerator.RequestPath mRequestPath;
    private final PostRequestWriter mRequestWriter;
    private ServiceExceptionProvider mServiceExceptionProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UploadFileOperation(OperationFactory operationFactory, ClientConfiguration clientConfiguration, AccountConfiguration accountConfiguration, SourceInfoGenerator sourceInfoGenerator, PostRequestWriter postRequestWriter, JsonDeserializer<Response> jsonDeserializer, ServiceExceptionProvider serviceExceptionProvider, RequestPathGenerator.RequestPath requestPath, String str, MetricListener metricListener, Class<?> cls, String str2) {
        super(operationFactory, clientConfiguration, accountConfiguration, sourceInfoGenerator, str, metricListener, cls);
        this.mRequestWriter = postRequestWriter;
        this.mDeserializer = jsonDeserializer;
        this.mServiceExceptionProvider = serviceExceptionProvider;
        this.mRequestPath = requestPath;
        this.mMethod = str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0072  */
    @Override // com.amazon.clouddrive.internal.AbstractCloudDriveOperation
    /* renamed from: retryCall */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final Response mo3157retryCall() throws com.amazon.clouddrive.exceptions.CloudDriveException, java.lang.InterruptedException {
        /*
            r5 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L4e com.amazon.clouddrive.exceptions.RetryException -> L51 java.io.IOException -> L5b java.io.InterruptedIOException -> L65
            com.amazon.clouddrive.internal.RequestPathGenerator$RequestPath r2 = r5.mRequestPath     // Catch: java.lang.Throwable -> L4e com.amazon.clouddrive.exceptions.RetryException -> L51 java.io.IOException -> L5b java.io.InterruptedIOException -> L65
            java.lang.String r2 = r2.getPath()     // Catch: java.lang.Throwable -> L4e com.amazon.clouddrive.exceptions.RetryException -> L51 java.io.IOException -> L5b java.io.InterruptedIOException -> L65
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L4e com.amazon.clouddrive.exceptions.RetryException -> L51 java.io.IOException -> L5b java.io.InterruptedIOException -> L65
            java.net.HttpURLConnection r1 = r5.setUpConnection(r1)     // Catch: java.lang.Throwable -> L4e com.amazon.clouddrive.exceptions.RetryException -> L51 java.io.IOException -> L5b java.io.InterruptedIOException -> L65
            java.lang.String r2 = r5.mMethod     // Catch: com.amazon.clouddrive.exceptions.RetryException -> L4a java.io.IOException -> L4c java.io.InterruptedIOException -> L66 java.lang.Throwable -> L6c
            com.amazon.clouddrive.internal.BodyConnectionHelper.setConnectionParameters(r1, r2)     // Catch: com.amazon.clouddrive.exceptions.RetryException -> L4a java.io.IOException -> L4c java.io.InterruptedIOException -> L66 java.lang.Throwable -> L6c
            com.amazon.clouddrive.internal.PostRequestWriter r2 = r5.mRequestWriter     // Catch: com.amazon.clouddrive.exceptions.RetryException -> L4a java.io.IOException -> L4c java.io.InterruptedIOException -> L66 java.lang.Throwable -> L6c
            r2.writeHeaders(r1)     // Catch: com.amazon.clouddrive.exceptions.RetryException -> L4a java.io.IOException -> L4c java.io.InterruptedIOException -> L66 java.lang.Throwable -> L6c
            com.amazon.clouddrive.internal.PostRequestWriter r2 = r5.mRequestWriter     // Catch: com.amazon.clouddrive.exceptions.RetryException -> L4a java.io.IOException -> L4c java.io.InterruptedIOException -> L66 java.lang.Throwable -> L6c
            com.amazon.clouddrive.internal.BodyConnectionHelper.addPostInput(r1, r2)     // Catch: com.amazon.clouddrive.exceptions.RetryException -> L4a java.io.IOException -> L4c java.io.InterruptedIOException -> L66 java.lang.Throwable -> L6c
            java.lang.String r2 = "uploadFileResponse"
            com.amazon.clouddrive.utils.Optional r2 = com.amazon.clouddrive.utils.Optional.of(r2)     // Catch: com.amazon.clouddrive.exceptions.RetryException -> L4a java.io.IOException -> L4c java.io.InterruptedIOException -> L66 java.lang.Throwable -> L6c
            com.amazon.clouddrive.internal.ServiceExceptionProvider r3 = r5.mServiceExceptionProvider     // Catch: com.amazon.clouddrive.exceptions.RetryException -> L4a java.io.IOException -> L4c java.io.InterruptedIOException -> L66 java.lang.Throwable -> L6c
            com.amazon.clouddrive.utils.Optional r3 = com.amazon.clouddrive.utils.Optional.fromNullable(r3)     // Catch: com.amazon.clouddrive.exceptions.RetryException -> L4a java.io.IOException -> L4c java.io.InterruptedIOException -> L66 java.lang.Throwable -> L6c
            r5.assertSuccessResponseCodeWithTimer(r1, r2, r3)     // Catch: com.amazon.clouddrive.exceptions.RetryException -> L4a java.io.IOException -> L4c java.io.InterruptedIOException -> L66 java.lang.Throwable -> L6c
            com.amazon.clouddrive.model.deserializer.JsonDeserializer<Response> r2 = r5.mDeserializer     // Catch: com.amazon.clouddrive.exceptions.RetryException -> L4a java.io.IOException -> L4c java.io.InterruptedIOException -> L66 java.lang.Throwable -> L6c
            if (r2 != 0) goto L39
            com.amazon.clouddrive.internal.utils.Closer.closeQuietly(r0)
            r1.disconnect()
            return r0
        L39:
            java.io.InputStream r0 = r1.getInputStream()     // Catch: com.amazon.clouddrive.exceptions.RetryException -> L4a java.io.IOException -> L4c java.io.InterruptedIOException -> L66 java.lang.Throwable -> L6c
            com.amazon.clouddrive.model.deserializer.JsonDeserializer<Response> r2 = r5.mDeserializer     // Catch: com.amazon.clouddrive.exceptions.RetryException -> L4a java.io.IOException -> L4c java.io.InterruptedIOException -> L66 java.lang.Throwable -> L6c
            java.lang.Object r2 = com.amazon.clouddrive.internal.CloudDriveObjectMapper.readStream(r0, r2)     // Catch: com.amazon.clouddrive.exceptions.RetryException -> L4a java.io.IOException -> L4c java.io.InterruptedIOException -> L66 java.lang.Throwable -> L6c
            com.amazon.clouddrive.internal.utils.Closer.closeQuietly(r0)
            r1.disconnect()
            return r2
        L4a:
            r2 = move-exception
            goto L53
        L4c:
            r2 = move-exception
            goto L5d
        L4e:
            r2 = move-exception
            r1 = r0
            goto L6d
        L51:
            r2 = move-exception
            r1 = r0
        L53:
            com.amazon.clouddrive.exceptions.RebuildRequestException r3 = new com.amazon.clouddrive.exceptions.RebuildRequestException     // Catch: java.lang.Throwable -> L6c
            java.lang.String r4 = "Request requires new OutputStream instance to continue."
            r3.<init>(r4, r2)     // Catch: java.lang.Throwable -> L6c
            throw r3     // Catch: java.lang.Throwable -> L6c
        L5b:
            r2 = move-exception
            r1 = r0
        L5d:
            com.amazon.clouddrive.exceptions.ActionRequiredException r3 = new com.amazon.clouddrive.exceptions.ActionRequiredException     // Catch: java.lang.Throwable -> L6c
            java.lang.String r4 = "Failure creating a connection"
            r3.<init>(r4, r2)     // Catch: java.lang.Throwable -> L6c
            throw r3     // Catch: java.lang.Throwable -> L6c
        L65:
            r1 = r0
        L66:
            java.lang.InterruptedException r2 = new java.lang.InterruptedException     // Catch: java.lang.Throwable -> L6c
            r2.<init>()     // Catch: java.lang.Throwable -> L6c
            throw r2     // Catch: java.lang.Throwable -> L6c
        L6c:
            r2 = move-exception
        L6d:
            com.amazon.clouddrive.internal.utils.Closer.closeQuietly(r0)
            if (r1 == 0) goto L75
            r1.disconnect()
        L75:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.clouddrive.internal.UploadFileOperation.mo3157retryCall():java.lang.Object");
    }
}
