package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.internal.RequestPathGenerator;
import com.amazon.clouddrive.internal.utils.ThreadUtil;
import com.amazon.clouddrive.metrics.MetricEvent;
import com.amazon.clouddrive.metrics.MetricListener;
import com.amazon.clouddrive.model.GetFileProgressResponse;
import com.amazon.clouddrive.model.deserializer.GetFileProgressResponseDeserializer;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class GetFileProgressOperation<Response extends GetFileProgressResponse> extends AbstractCloudDriveOperation<Response> {
    private static final int MAX_RETRIES = 14;
    private static final String METRIC_ID = GetFileProgressOperation.class.getSimpleName() + "GetProgress";
    private static final long RETRY_INTERVAL = 3000;
    private final String mMethod;
    private final MetricListener mMetricListener;
    private final GetFileProgressResponseDeserializer<Response> mProgressDeserializer;
    private final RequestPathGenerator.RequestPath mRequestPath;
    private final RequestPropertyWriter mRequestPropertyWriter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GetFileProgressOperation(OperationFactory operationFactory, ClientConfiguration clientConfiguration, AccountConfiguration accountConfiguration, SourceInfoGenerator sourceInfoGenerator, String str, RequestPathGenerator.RequestPath requestPath, RequestPropertyWriter requestPropertyWriter, GetFileProgressResponseDeserializer<Response> getFileProgressResponseDeserializer, String str2, MetricListener metricListener, Class<?> cls) {
        super(operationFactory, clientConfiguration, accountConfiguration, sourceInfoGenerator, str2, metricListener, cls);
        this.mProgressDeserializer = getFileProgressResponseDeserializer;
        this.mMetricListener = metricListener;
        this.mMethod = str;
        this.mRequestPath = requestPath;
        this.mRequestPropertyWriter = requestPropertyWriter;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private Response getProgress(int r8) throws com.amazon.clouddrive.exceptions.CloudDriveException, java.lang.InterruptedException {
        /*
            r7 = this;
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 0
            java.net.URL r3 = new java.net.URL     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L60 java.io.InterruptedIOException -> L6a
            com.amazon.clouddrive.internal.RequestPathGenerator$RequestPath r4 = r7.mRequestPath     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L60 java.io.InterruptedIOException -> L6a
            java.lang.String r4 = r4.getPath()     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L60 java.io.InterruptedIOException -> L6a
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L60 java.io.InterruptedIOException -> L6a
            java.net.HttpURLConnection r3 = r7.setUpConnection(r3)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L60 java.io.InterruptedIOException -> L6a
            java.lang.String r4 = r7.mMethod     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L56 java.io.InterruptedIOException -> L5a
            r3.setRequestMethod(r4)     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L56 java.io.InterruptedIOException -> L5a
            com.amazon.clouddrive.internal.RequestPropertyWriter r4 = r7.mRequestPropertyWriter     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L56 java.io.InterruptedIOException -> L5a
            if (r4 == 0) goto L22
            com.amazon.clouddrive.internal.RequestPropertyWriter r4 = r7.mRequestPropertyWriter     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L56 java.io.InterruptedIOException -> L5a
            r4.writeHeaders(r3)     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L56 java.io.InterruptedIOException -> L5a
        L22:
            r3.connect()     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L56 java.io.InterruptedIOException -> L5a
            r7.assertSuccessResponseCode(r3)     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L56 java.io.InterruptedIOException -> L5a
            java.io.InputStream r4 = r3.getInputStream()     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L56 java.io.InterruptedIOException -> L5a
            if (r4 == 0) goto L4d
            int r5 = r3.getResponseCode()     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4b java.io.InterruptedIOException -> L5b
            r6 = 204(0xcc, float:2.86E-43)
            if (r5 != r6) goto L37
            goto L4d
        L37:
            r7.notifyMetricListener(r0, r8)     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4b java.io.InterruptedIOException -> L5b
            com.amazon.clouddrive.model.deserializer.GetFileProgressResponseDeserializer<Response extends com.amazon.clouddrive.model.GetFileProgressResponse> r8 = r7.mProgressDeserializer     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4b java.io.InterruptedIOException -> L5b
            java.lang.Object r8 = com.amazon.clouddrive.internal.CloudDriveObjectMapper.readStream(r4, r8)     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4b java.io.InterruptedIOException -> L5b
            com.amazon.clouddrive.model.GetFileProgressResponse r8 = (com.amazon.clouddrive.model.GetFileProgressResponse) r8     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4b java.io.InterruptedIOException -> L5b
            com.amazon.clouddrive.internal.utils.Closer.closeQuietly(r4)
            r3.disconnect()
            return r8
        L49:
            r8 = move-exception
            goto L73
        L4b:
            r8 = move-exception
            goto L58
        L4d:
            com.amazon.clouddrive.internal.utils.Closer.closeQuietly(r4)
            r3.disconnect()
            return r2
        L54:
            r8 = move-exception
            goto L74
        L56:
            r8 = move-exception
            r4 = r2
        L58:
            r2 = r3
            goto L62
        L5a:
            r4 = r2
        L5b:
            r2 = r3
            goto L6b
        L5d:
            r8 = move-exception
            r3 = r2
            goto L74
        L60:
            r8 = move-exception
            r4 = r2
        L62:
            com.amazon.clouddrive.exceptions.ActionRequiredException r0 = new com.amazon.clouddrive.exceptions.ActionRequiredException     // Catch: java.lang.Throwable -> L71
            java.lang.String r1 = "Failure in creating a connection"
            r0.<init>(r1, r8)     // Catch: java.lang.Throwable -> L71
            throw r0     // Catch: java.lang.Throwable -> L71
        L6a:
            r4 = r2
        L6b:
            java.lang.InterruptedException r8 = new java.lang.InterruptedException     // Catch: java.lang.Throwable -> L71
            r8.<init>()     // Catch: java.lang.Throwable -> L71
            throw r8     // Catch: java.lang.Throwable -> L71
        L71:
            r8 = move-exception
            r3 = r2
        L73:
            r2 = r4
        L74:
            com.amazon.clouddrive.internal.utils.Closer.closeQuietly(r2)
            if (r3 == 0) goto L7c
            r3.disconnect()
        L7c:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.clouddrive.internal.GetFileProgressOperation.getProgress(int):com.amazon.clouddrive.model.GetFileProgressResponse");
    }

    private void notifyMetricListener(long j, int i) {
        if (this.mMetricListener != null) {
            this.mMetricListener.onMetricEvent(new MetricEvent(METRIC_ID, System.currentTimeMillis() - j, i, null));
        }
    }

    @Override // com.amazon.clouddrive.internal.AbstractCloudDriveOperation
    /* renamed from: retryCall */
    public final Response mo3157retryCall() throws CloudDriveException, InterruptedException {
        for (int i = 0; i <= 14; i++) {
            Response progress = getProgress(i);
            if (progress != null) {
                return progress;
            }
            if (i < 14) {
                Thread.sleep(3000L);
                ThreadUtil.checkIfInterrupted();
            }
        }
        Response newResponseInstance = this.mProgressDeserializer.newResponseInstance();
        newResponseInstance.setIsProgressAvailable(false);
        return newResponseInstance;
    }
}
