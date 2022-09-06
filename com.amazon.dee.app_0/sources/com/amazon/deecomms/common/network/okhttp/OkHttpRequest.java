package com.amazon.deecomms.common.network.okhttp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
/* loaded from: classes12.dex */
public class OkHttpRequest implements IHttpClient.Request {
    private static final String COOKIE_AT_ACB = "at-acb";
    private static final String COOKIE_AT_MAIN = "at-main";
    private static final String COOKIE_AT_TACB = "at-tacb";
    private static final String COOKIE_UBID_ACB = "ubid-acb";
    private static final String COOKIE_UBID_MAIN = "ubid-main";
    private static final String COOKIE_UBID_TACB = "ubid-tacb";
    private static final int[] DEFAULT_RETRYABLE_ERRORS = {HttpServletResponse.SC_UNAUTHORIZED};
    private IHttpClient.AuthHeaderProvider authHeaderProvider;
    private boolean authenticated;
    private RequestBody body;
    private String directedId;
    private OkHttpClient httpClient;
    private IHttpClient.JSONConverter jsonConverter;
    private String method;
    private String operationMetricNameRoot;
    private HttpUrl.Builder urlBuilder;
    private CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, OkHttpRequest.class);
    private int attemptCount = 1;
    private final Set<Integer> retryableErrors = new HashSet();
    private final Map<String, Object> metricsMetadata = new HashMap();
    private boolean validateMapCookiesPresence = false;
    protected Request.Builder builder = new Request.Builder();
    private final String requestId = UUID.randomUUID().toString();

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes12.dex */
    public class MissingCookiesCall extends OkHttpCall {
        MissingCookiesCall(Call call, OkHttpRequest okHttpRequest, IHttpClient.JSONConverter jSONConverter, Map<String, Object> map) {
            super(call, okHttpRequest, jSONConverter, map);
        }

        private ServiceException buildException() {
            return new ServiceException("CommsUser API call is missing map cookies");
        }

        private void recordMetric(@NonNull String str, @NonNull String str2, double d, @NonNull Map<String, Object> map) {
            CounterMetric generateOperational = CounterMetric.generateOperational(str + str2);
            generateOperational.getMetadata().putAll(map);
            MetricsHelper.recordCounterMetric(generateOperational, Double.valueOf(d));
        }

        private void recordMetrics() {
            HashMap outline133 = GeneratedOutlineSupport1.outline133("source", Constants.MISSING_COOKIES);
            outline133.put("requestId", getRequestId());
            outline133.put("statusCode", Integer.valueOf((int) Constants.STATUS_CODE_MISSING_COOKIES));
            recordMetric(OkHttpRequest.this.operationMetricNameRoot, MetricKeys.DEBUG_SFX, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, outline133);
        }

        @Override // com.amazon.deecomms.common.network.okhttp.OkHttpCall, com.amazon.deecomms.common.network.IHttpClient.Call
        public void enqueue(IHttpClient.Callback callback) {
            recordMetrics();
            if (callback != null) {
                callback.onFailure(this, buildException());
            }
        }

        @Override // com.amazon.deecomms.common.network.okhttp.OkHttpCall, com.amazon.deecomms.common.network.IHttpClient.Call
        /* renamed from: execute  reason: collision with other method in class */
        public OkHttpResponse mo3640execute() throws ServiceException {
            recordMetrics();
            throw buildException();
        }
    }

    public OkHttpRequest(String str, IHttpClient.JSONConverter jSONConverter, IHttpClient.AuthHeaderProvider authHeaderProvider, OkHttpClient okHttpClient) {
        this.urlBuilder = HttpUrl.parse(str).newBuilder();
        this.jsonConverter = jSONConverter;
        this.authHeaderProvider = authHeaderProvider;
        this.httpClient = okHttpClient;
        setRetryableErrors(DEFAULT_RETRYABLE_ERRORS);
    }

    private static boolean expectedMapCookiesPresent(@Nullable Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return false;
        }
        String str = map.get("Cookie");
        if (Strings.isNullOrEmpty(str)) {
            return false;
        }
        String[] split = str.split(";");
        if (split.length == 0) {
            return false;
        }
        boolean z = false;
        boolean z2 = false;
        for (String str2 : split) {
            if (str2.startsWith(COOKIE_UBID_MAIN) || str2.startsWith(COOKIE_UBID_ACB) || str2.startsWith(COOKIE_UBID_TACB)) {
                z = true;
            }
            if (str2.startsWith(COOKIE_AT_MAIN) || str2.startsWith(COOKIE_AT_ACB) || str2.startsWith(COOKIE_AT_TACB)) {
                z2 = true;
            }
        }
        return z && z2;
    }

    private IHttpClient.Call prepareCall(Request.Builder builder) {
        IHttpClient.Call okHttpCall;
        boolean z = false;
        if (this.authenticated) {
            if (getAttemptCount() > 1) {
                z = true;
            }
            z = addDefaultHeadersToRequest(z);
        }
        Request build = builder.url(this.urlBuilder.build()).build();
        this.method = build.method();
        this.body = build.body();
        if (this.authenticated && !z) {
            okHttpCall = new MissingCookiesCall(this.httpClient.newCall(build), this, this.jsonConverter, this.metricsMetadata);
        } else {
            okHttpCall = new OkHttpCall(this.httpClient.newCall(build), this, this.jsonConverter, this.metricsMetadata);
        }
        okHttpCall.setOperationMetricNameRoot(this.operationMetricNameRoot);
        return okHttpCall;
    }

    private IHttpClient.Call prepareDeleteCall(RequestBody requestBody) {
        return prepareCall(this.builder.delete(requestBody));
    }

    private IHttpClient.Call preparePatchCall(RequestBody requestBody) {
        return prepareCall(this.builder.patch(requestBody));
    }

    private IHttpClient.Call preparePostCall(RequestBody requestBody) {
        return prepareCall(this.builder.post(requestBody));
    }

    private IHttpClient.Call preparePutCall(RequestBody requestBody) {
        return prepareCall(this.builder.put(requestBody));
    }

    protected boolean addDefaultHeadersToRequest(boolean z) {
        Map<String, String> authHeaders = this.authHeaderProvider.getAuthHeaders(this.directedId, z);
        if (this.validateMapCookiesPresence && !expectedMapCookiesPresent(authHeaders)) {
            if (!z) {
                this.LOG.w("Missing or invalid authentication cookies, attempting forced refresh...");
                return addDefaultHeadersToRequest(true);
            }
            this.LOG.e("Missing or invalid authentication cookies");
            return false;
        }
        if (authHeaders != null) {
            for (Map.Entry<String, String> entry : authHeaders.entrySet()) {
                addHeader(entry.getKey(), entry.getValue());
            }
        }
        return true;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Request addHeader(String str, String str2) {
        if (str != null && str2 != null) {
            this.builder.removeHeader(str);
            this.builder.addHeader(str, str2);
            return this;
        }
        this.LOG.e(String.format("value or key is null when adding header. key = %s, value = %s", str, str2));
        return this;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Request addMetricMetadata(@NonNull String str, @Nullable Object obj) {
        if (obj != null) {
            this.metricsMetadata.put(str, obj);
        }
        return this;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Request addQueryParameter(String str, String str2) {
        if (str != null && str2 != null) {
            this.urlBuilder.addQueryParameter(str, str2);
            return this;
        }
        this.LOG.e(String.format("value or key is null when adding query param.key = %s, value = %s", str, str2));
        return this;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Request addRequestIdToHeader() {
        return addHeader("X-Amzn-RequestId", getRequestId());
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Request authenticated() {
        this.authenticated = true;
        this.validateMapCookiesPresence = true;
        this.directedId = null;
        return this;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Request authenticatedAsCurrentCommsUser() {
        return authenticated(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getDirectedId("authenticatedAsCurrentCommsUser", false));
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Request authenticatedWithoutMAP() {
        this.authenticated = true;
        this.validateMapCookiesPresence = false;
        this.directedId = null;
        return this;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call delete() {
        return prepareCall(this.builder.delete());
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call get() {
        return prepareCall(this.builder.get());
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public int getAttemptCount() {
        return this.attemptCount;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public String getRequestId() {
        return this.requestId;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public boolean isRetryPermitted(@Nullable IHttpClient.Response response) {
        return response != null && this.retryableErrors.contains(Integer.valueOf(response.code())) && getAttemptCount() < 2;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call patch() {
        return preparePatchCall(RequestBody.create((MediaType) null, new byte[0]));
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call patchJson(@Nullable Object obj) {
        return preparePatchCall(RequestBody.create(MediaType.parse("application/json"), this.jsonConverter.toJson(obj)));
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call post(String str, @Nullable Object obj) {
        return preparePostCall(RequestBody.create(MediaType.parse(str), this.jsonConverter.toJson(obj)));
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call postJson(@Nullable Object obj) {
        return preparePostCall(RequestBody.create(MediaType.parse("application/json"), this.jsonConverter.toJson(obj)));
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call put(String str, @Nullable Object obj) {
        return preparePutCall(RequestBody.create(MediaType.parse(str), this.jsonConverter.toJson(obj)));
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call putJson(@Nullable Object obj) {
        return put("application/json", obj);
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call retryCall() {
        this.attemptCount++;
        addRequestIdToHeader();
        this.LOG.d(String.format("Retrying request '%s': requestId=%s", this.operationMetricNameRoot, this.requestId));
        return prepareCall(this.builder.method(this.method, this.body));
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public void setOperationMetricNameRoot(String str) {
        this.operationMetricNameRoot = str;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Request setRetryableErrors(int... iArr) {
        this.retryableErrors.clear();
        if (iArr != null) {
            for (int i : iArr) {
                this.retryableErrors.add(Integer.valueOf(i));
            }
        }
        return this;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call delete(@Nullable String str) {
        return delete(str, new byte[0]);
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call delete(@Nullable String str, @Nullable byte[] bArr) {
        return prepareDeleteCall(RequestBody.create(MediaType.parse(str), bArr));
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call patch(String str, byte[] bArr) {
        return preparePatchCall(RequestBody.create(MediaType.parse(str), bArr));
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Request authenticated(String str) {
        this.authenticated = true;
        this.validateMapCookiesPresence = true;
        this.directedId = str;
        return this;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call post() {
        return preparePostCall(RequestBody.create((MediaType) null, new byte[0]));
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call post(String str) {
        return post(str, new byte[0]);
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call post(String str, File file) {
        return preparePostCall(RequestBody.create(MediaType.parse(str), file));
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Request
    public IHttpClient.Call post(String str, byte[] bArr) {
        return preparePostCall(RequestBody.create(MediaType.parse(str), bArr));
    }
}
