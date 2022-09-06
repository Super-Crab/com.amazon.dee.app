package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.internal.RequestPathGenerator;
import com.amazon.clouddrive.metrics.MetricListener;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class CloudDriveBodyOperation<Response> extends AbstractCloudDriveOperation<Response> {
    private JsonDeserializer<Response> mDeserializer;
    private final String mMethod;
    private RequestPathGenerator.RequestPath mRequestPath;
    private final PostRequestWriter mRequestWriter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CloudDriveBodyOperation(OperationFactory operationFactory, ClientConfiguration clientConfiguration, AccountConfiguration accountConfiguration, SourceInfoGenerator sourceInfoGenerator, PostRequestWriter postRequestWriter, JsonDeserializer<Response> jsonDeserializer, RequestPathGenerator.RequestPath requestPath, String str, String str2, MetricListener metricListener, Class<?> cls) {
        super(operationFactory, clientConfiguration, accountConfiguration, sourceInfoGenerator, str2, metricListener, cls);
        this.mRequestWriter = postRequestWriter;
        this.mDeserializer = jsonDeserializer;
        this.mMethod = str;
        this.mRequestPath = requestPath;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005a  */
    @Override // com.amazon.clouddrive.internal.AbstractCloudDriveOperation
    /* renamed from: retryCall */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Response mo3157retryCall() throws com.amazon.clouddrive.exceptions.CloudDriveException, java.lang.InterruptedException {
        /*
            r5 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43 java.io.InterruptedIOException -> L4d
            com.amazon.clouddrive.internal.RequestPathGenerator$RequestPath r2 = r5.mRequestPath     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43 java.io.InterruptedIOException -> L4d
            java.lang.String r2 = r2.getPath()     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43 java.io.InterruptedIOException -> L4d
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43 java.io.InterruptedIOException -> L4d
            java.net.HttpURLConnection r1 = r5.setUpConnection(r1)     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L43 java.io.InterruptedIOException -> L4d
            java.lang.String r2 = r5.mMethod     // Catch: java.io.IOException -> L3e java.io.InterruptedIOException -> L4e java.lang.Throwable -> L54
            com.amazon.clouddrive.internal.BodyConnectionHelper.setConnectionParameters(r1, r2)     // Catch: java.io.IOException -> L3e java.io.InterruptedIOException -> L4e java.lang.Throwable -> L54
            com.amazon.clouddrive.internal.PostRequestWriter r2 = r5.mRequestWriter     // Catch: java.io.IOException -> L3e java.io.InterruptedIOException -> L4e java.lang.Throwable -> L54
            r2.writeHeaders(r1)     // Catch: java.io.IOException -> L3e java.io.InterruptedIOException -> L4e java.lang.Throwable -> L54
            com.amazon.clouddrive.internal.PostRequestWriter r2 = r5.mRequestWriter     // Catch: java.io.IOException -> L3e java.io.InterruptedIOException -> L4e java.lang.Throwable -> L54
            com.amazon.clouddrive.internal.BodyConnectionHelper.addPostInput(r1, r2)     // Catch: java.io.IOException -> L3e java.io.InterruptedIOException -> L4e java.lang.Throwable -> L54
            r5.assertSuccessResponseCode(r1)     // Catch: java.io.IOException -> L3e java.io.InterruptedIOException -> L4e java.lang.Throwable -> L54
            com.amazon.clouddrive.model.deserializer.JsonDeserializer<Response> r2 = r5.mDeserializer     // Catch: java.io.IOException -> L3e java.io.InterruptedIOException -> L4e java.lang.Throwable -> L54
            if (r2 != 0) goto L2d
            com.amazon.clouddrive.internal.utils.Closer.closeQuietly(r0)
            r1.disconnect()
            return r0
        L2d:
            java.io.InputStream r0 = r1.getInputStream()     // Catch: java.io.IOException -> L3e java.io.InterruptedIOException -> L4e java.lang.Throwable -> L54
            com.amazon.clouddrive.model.deserializer.JsonDeserializer<Response> r2 = r5.mDeserializer     // Catch: java.io.IOException -> L3e java.io.InterruptedIOException -> L4e java.lang.Throwable -> L54
            java.lang.Object r2 = com.amazon.clouddrive.internal.CloudDriveObjectMapper.readStream(r0, r2)     // Catch: java.io.IOException -> L3e java.io.InterruptedIOException -> L4e java.lang.Throwable -> L54
            com.amazon.clouddrive.internal.utils.Closer.closeQuietly(r0)
            r1.disconnect()
            return r2
        L3e:
            r2 = move-exception
            goto L45
        L40:
            r2 = move-exception
            r1 = r0
            goto L55
        L43:
            r2 = move-exception
            r1 = r0
        L45:
            com.amazon.clouddrive.exceptions.ActionRequiredException r3 = new com.amazon.clouddrive.exceptions.ActionRequiredException     // Catch: java.lang.Throwable -> L54
            java.lang.String r4 = "Failure creating a connection"
            r3.<init>(r4, r2)     // Catch: java.lang.Throwable -> L54
            throw r3     // Catch: java.lang.Throwable -> L54
        L4d:
            r1 = r0
        L4e:
            java.lang.InterruptedException r2 = new java.lang.InterruptedException     // Catch: java.lang.Throwable -> L54
            r2.<init>()     // Catch: java.lang.Throwable -> L54
            throw r2     // Catch: java.lang.Throwable -> L54
        L54:
            r2 = move-exception
        L55:
            com.amazon.clouddrive.internal.utils.Closer.closeQuietly(r0)
            if (r1 == 0) goto L5d
            r1.disconnect()
        L5d:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.clouddrive.internal.CloudDriveBodyOperation.mo3157retryCall():java.lang.Object");
    }
}
