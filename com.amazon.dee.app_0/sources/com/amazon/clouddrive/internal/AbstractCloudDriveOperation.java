package com.amazon.clouddrive.internal;

import android.util.Base64;
import com.amazon.clouddrive.cdasdk.StandardHeaderInterceptor;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.NoRetryException;
import com.amazon.clouddrive.internal.utils.Closer;
import com.amazon.clouddrive.metrics.MetricEvent;
import com.amazon.clouddrive.metrics.MetricListener;
import com.amazon.clouddrive.model.GetAccountEndpointRequest;
import com.amazon.clouddrive.model.SetupSourceRequest;
import com.amazon.clouddrive.utils.Optional;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public abstract class AbstractCloudDriveOperation<Response> implements CloudDriveOperation<Response> {
    private static final AtomicBoolean SKIP_SETUP_SOURCE = new AtomicBoolean(false);
    private final AccountConfiguration mAccountConfiguration;
    protected final OkHttpClient mClient;
    private final ClientConfiguration mConfiguration;
    private final String mMetricId;
    private final MetricListener mMetricListener;
    private final String mMetricSessionId;
    private final OperationFactory mOperationFactory;
    private final Class<?> mRequestClass;
    private final SourceInfoGenerator mSourceInfoGenerator;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractCloudDriveOperation(OperationFactory operationFactory, ClientConfiguration clientConfiguration, AccountConfiguration accountConfiguration, SourceInfoGenerator sourceInfoGenerator, String str, MetricListener metricListener, Class<?> cls) {
        this.mOperationFactory = operationFactory;
        this.mConfiguration = clientConfiguration;
        this.mAccountConfiguration = accountConfiguration;
        this.mSourceInfoGenerator = sourceInfoGenerator;
        this.mMetricId = str;
        this.mMetricListener = metricListener;
        this.mMetricSessionId = null;
        this.mRequestClass = cls;
        this.mClient = clientConfiguration.getHttpClient();
    }

    @Deprecated
    private void initializeApplicationId(HttpURLConnection httpURLConnection) {
        String applicationId = this.mConfiguration.getApplicationId();
        if (applicationId != null) {
            httpURLConnection.addRequestProperty(StandardHeaderInterceptor.APP_ID_HEADER, Base64.encodeToString(applicationId.getBytes(), 3));
        }
    }

    @Deprecated
    private void initializeMetricSessionIdInfo(HttpURLConnection httpURLConnection) {
        String str = this.mMetricSessionId;
        if (str == null || str.length() == 0) {
            return;
        }
        httpURLConnection.addRequestProperty("x-amz-clouddrive-session-id", this.mMetricSessionId);
    }

    @Deprecated
    private void initializeSourceInfo(HttpURLConnection httpURLConnection) {
        if (!shouldUseSourceInfo(this.mRequestClass)) {
            return;
        }
        try {
            BasicSourceInfo createSourceInfo = this.mSourceInfoGenerator.createSourceInfo();
            if (createSourceInfo == null || createSourceInfo.getSourceId() == null) {
                return;
            }
            httpURLConnection.addRequestProperty("x-amzn-clouddrive-source", createSourceInfo.getSourceId());
        } catch (CloudDriveException unused) {
            SKIP_SETUP_SOURCE.set(true);
        } catch (InterruptedException unused2) {
            SKIP_SETUP_SOURCE.set(true);
        }
    }

    private void notifyMetricListener(long j, int i, CloudDriveException cloudDriveException) {
        if (this.mMetricListener != null) {
            this.mMetricListener.onMetricEvent(new MetricEvent(this.mMetricId, System.currentTimeMillis() - j, i, cloudDriveException));
        }
    }

    private boolean shouldUseSourceInfo(Class cls) {
        return !SKIP_SETUP_SOURCE.get() && !SetupSourceRequest.class.isAssignableFrom(cls) && cls != GetAccountEndpointRequest.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public final void assertSuccessResponseCode(HttpURLConnection httpURLConnection) throws CloudDriveException, InterruptedIOException {
        assertSuccessResponseCodeWithTimer(httpURLConnection, Optional.absent());
    }

    @Deprecated
    protected final void assertSuccessResponseCodeWithTimer(HttpURLConnection httpURLConnection, Optional<String> optional) throws CloudDriveException, InterruptedIOException {
        assertSuccessResponseCodeWithTimer(httpURLConnection, optional, Optional.absent());
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0049 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.amazon.clouddrive.internal.CloudDriveOperation, java.util.concurrent.Callable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final Response call() throws com.amazon.clouddrive.exceptions.CloudDriveException, java.lang.InterruptedException {
        /*
            r9 = this;
            com.amazon.clouddrive.internal.OperationFactory r0 = r9.mOperationFactory
            long r0 = r0.getOperationDelay()
            r2 = 0
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 <= 0) goto Lf
            java.lang.Thread.sleep(r0)
        Lf:
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 0
            r3 = 1
            r4 = r2
            r5 = r3
        L17:
            java.lang.Object r6 = r9.mo3157retryCall()     // Catch: com.amazon.clouddrive.exceptions.CloudDriveException -> L25 com.amazon.clouddrive.exceptions.NoRetryException -> L27
            java.lang.Class<?> r7 = r9.mRequestClass     // Catch: com.amazon.clouddrive.exceptions.CloudDriveException -> L25 com.amazon.clouddrive.exceptions.NoRetryException -> L27
            com.amazon.clouddrive.internal.BackoffWaitTime.addSuccess(r7)     // Catch: com.amazon.clouddrive.exceptions.CloudDriveException -> L25 com.amazon.clouddrive.exceptions.NoRetryException -> L27
            r7 = 0
            r9.notifyMetricListener(r0, r4, r7)     // Catch: com.amazon.clouddrive.exceptions.CloudDriveException -> L25 com.amazon.clouddrive.exceptions.NoRetryException -> L27
            return r6
        L25:
            r6 = move-exception
            goto L2a
        L27:
            r5 = move-exception
            r6 = r5
            r5 = r2
        L2a:
            if (r5 == 0) goto L47
            com.amazon.clouddrive.configuration.ClientConfiguration r7 = r9.mConfiguration
            int r7 = r7.getMaxErrorRetry()
            int r7 = r7 - r3
            if (r4 >= r7) goto L47
            java.lang.String r7 = "Failure occurred. Retrying request "
            java.lang.StringBuilder r7 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r7)
            java.lang.Class<?> r8 = r9.mRequestClass
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            com.amazon.clouddrive.internal.AmazonCloudDriveLog.w(r7, r6)
        L47:
            if (r5 == 0) goto L58
            java.lang.Class<?> r7 = r9.mRequestClass     // Catch: java.lang.InterruptedException -> L53
            long r7 = com.amazon.clouddrive.internal.BackoffWaitTime.getNextWaitTime(r4, r7)     // Catch: java.lang.InterruptedException -> L53
            java.lang.Thread.sleep(r7)     // Catch: java.lang.InterruptedException -> L53
            goto L58
        L53:
            r2 = move-exception
            r9.notifyMetricListener(r0, r4, r6)
            throw r2
        L58:
            int r7 = r4 + 1
            com.amazon.clouddrive.configuration.ClientConfiguration r8 = r9.mConfiguration
            int r8 = r8.getMaxErrorRetry()
            if (r4 >= r8) goto L66
            if (r5 == 0) goto L66
            r4 = r7
            goto L17
        L66:
            int r7 = r7 - r3
            r9.notifyMetricListener(r0, r7, r6)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.clouddrive.internal.AbstractCloudDriveOperation.call():java.lang.Object");
    }

    @Deprecated
    protected String createUserAgentString() {
        return this.mConfiguration.getUserAgent() + " " + Build.SDK_AGENT;
    }

    /* renamed from: retryCall */
    protected abstract Response mo3157retryCall() throws CloudDriveException, InterruptedException;

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public final HttpURLConnection setUpConnection(URL url) throws IOException, CloudDriveException, InterruptedException {
        HttpURLConnection createHttpURLConnection = this.mAccountConfiguration.getAuthenticatedURLConnectionFactory().createHttpURLConnection(url);
        createHttpURLConnection.setReadTimeout(this.mConfiguration.getReadTimeOutMillis());
        createHttpURLConnection.setConnectTimeout(this.mConfiguration.getConnectionTimeOutMillis());
        createHttpURLConnection.addRequestProperty("user-agent", createUserAgentString());
        initializeSourceInfo(createHttpURLConnection);
        initializeApplicationId(createHttpURLConnection);
        initializeMetricSessionIdInfo(createHttpURLConnection);
        return createHttpURLConnection;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Request.Builder setUpRequest(URL url) {
        Request.Builder url2 = new Request.Builder().url(url);
        initializeSourceInfo(url2);
        initializeApplicationId(url2);
        initializeMetricSessionIdInfo(url2);
        return url2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void assertSuccessResponseCode(Response response) throws CloudDriveException {
        assertSuccessResponseCodeWithTimer(response, Optional.absent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public final void assertSuccessResponseCodeWithTimer(HttpURLConnection httpURLConnection, Optional<String> optional, Optional<ServiceExceptionProvider> optional2) throws CloudDriveException, InterruptedIOException {
        long currentTimeMillis = System.currentTimeMillis();
        long j = -1;
        InputStream inputStream = null;
        try {
            try {
                try {
                    int responseCode = httpURLConnection.getResponseCode();
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (responseCode >= 200 && responseCode < 300) {
                        Closer.closeQuietly(null);
                        if (!optional.isPresent() || this.mMetricListener == null) {
                            return;
                        }
                        if (currentTimeMillis2 <= 0) {
                            currentTimeMillis2 = System.currentTimeMillis();
                        }
                        this.mMetricListener.onMetricEvent(new MetricEvent(optional.get(), currentTimeMillis2 - currentTimeMillis, 0, null));
                        return;
                    }
                    inputStream = httpURLConnection.getErrorStream();
                    if (optional2.isPresent()) {
                        throw optional2.get().getCloudDriveException(inputStream, responseCode);
                    }
                    throw ErrorDeserializer.getCloudDriveException(inputStream, responseCode);
                } catch (IOException e) {
                    throw new NoRetryException("Failed to read response code from the connection.", e);
                }
            } catch (InterruptedIOException e2) {
                throw e2;
            }
        } catch (Throwable th) {
            Closer.closeQuietly(inputStream);
            if (optional.isPresent() && this.mMetricListener != null) {
                if (-1 <= 0) {
                    j = System.currentTimeMillis();
                }
                this.mMetricListener.onMetricEvent(new MetricEvent(optional.get(), j - currentTimeMillis, 0, null));
            }
            throw th;
        }
    }

    private void initializeMetricSessionIdInfo(Request.Builder builder) {
        String str = this.mMetricSessionId;
        if (str == null || str.length() == 0) {
            return;
        }
        builder.header("x-amz-clouddrive-session-id", this.mMetricSessionId);
    }

    private void initializeApplicationId(Request.Builder builder) {
        String applicationId = this.mConfiguration.getApplicationId();
        if (applicationId != null) {
            builder.header(StandardHeaderInterceptor.APP_ID_HEADER, Base64.encodeToString(applicationId.getBytes(), 3));
        }
    }

    private void initializeSourceInfo(Request.Builder builder) {
        if (!shouldUseSourceInfo(this.mRequestClass)) {
            return;
        }
        try {
            BasicSourceInfo createSourceInfo = this.mSourceInfoGenerator.createSourceInfo();
            if (createSourceInfo == null || createSourceInfo.getSourceId() == null) {
                return;
            }
            builder.header("x-amzn-clouddrive-source", createSourceInfo.getSourceId());
        } catch (CloudDriveException unused) {
            SKIP_SETUP_SOURCE.set(true);
        } catch (InterruptedException unused2) {
            SKIP_SETUP_SOURCE.set(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractCloudDriveOperation(OperationFactory operationFactory, ClientConfiguration clientConfiguration, AccountConfiguration accountConfiguration, SourceInfoGenerator sourceInfoGenerator, String str, MetricListener metricListener, String str2, Class<?> cls) {
        this.mOperationFactory = operationFactory;
        this.mConfiguration = clientConfiguration;
        this.mAccountConfiguration = accountConfiguration;
        this.mSourceInfoGenerator = sourceInfoGenerator;
        this.mMetricId = str;
        this.mMetricListener = metricListener;
        this.mMetricSessionId = str2;
        this.mRequestClass = cls;
        this.mClient = clientConfiguration.getHttpClient();
    }

    protected final void assertSuccessResponseCodeWithTimer(Response response, Optional<String> optional) throws CloudDriveException {
        long currentTimeMillis = System.currentTimeMillis();
        long j = -1;
        InputStream inputStream = null;
        try {
            int code = response.code();
            long currentTimeMillis2 = System.currentTimeMillis();
            if (code >= 200 && code < 300) {
                Closer.closeQuietly(null);
                if (!optional.isPresent() || this.mMetricListener == null) {
                    return;
                }
                if (currentTimeMillis2 <= 0) {
                    currentTimeMillis2 = System.currentTimeMillis();
                }
                this.mMetricListener.onMetricEvent(new MetricEvent(optional.get(), currentTimeMillis2 - currentTimeMillis, 0, null));
                return;
            }
            inputStream = response.body().byteStream();
            throw ErrorDeserializer.getCloudDriveException(inputStream, code);
        } catch (Throwable th) {
            Closer.closeQuietly(inputStream);
            if (optional.isPresent() && this.mMetricListener != null) {
                if (-1 <= 0) {
                    j = System.currentTimeMillis();
                }
                this.mMetricListener.onMetricEvent(new MetricEvent(optional.get(), j - currentTimeMillis, 0, null));
            }
            throw th;
        }
    }
}
