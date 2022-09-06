package com.amazon.deecomms.common.network.okhttp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func0;
/* loaded from: classes12.dex */
public class OkHttpCall implements IHttpClient.Call {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, OkHttpCall.class);
    private Call call;
    private IHttpClient.JSONConverter jsonConverter;
    private final Map<String, Object> metricsMeta = new HashMap();
    private String operationMetricNameRoot;
    private final IHttpClient.Request request;

    public OkHttpCall(Call call, IHttpClient.Request request, IHttpClient.JSONConverter jSONConverter, @NonNull Map<String, Object> map) {
        this.call = call;
        this.request = request;
        this.jsonConverter = jSONConverter;
        this.metricsMeta.putAll(map);
        this.metricsMeta.put("requestId", request.getRequestId());
        this.metricsMeta.put("network", Utils.getNetworkType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void printRequestIDAndClientID(Response response) {
        if (response != null) {
            Headers headers = response.request().headers();
            if (headers != null) {
                CommsLogger commsLogger = LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1(" RequestID - ");
                outline1.append(headers.get("X-Amzn-RequestId"));
                commsLogger.i(outline1.toString());
                CommsLogger commsLogger2 = LOG;
                StringBuilder outline12 = GeneratedOutlineSupport.outline1(" ClientID - ");
                outline12.append(headers.get(Constants.ACMS_HEADER_CLIENT_ID));
                commsLogger2.i(outline12.toString());
                return;
            }
            LOG.e(" The response header is null. Unable to retrieve requestID/clientID");
            return;
        }
        LOG.e(" The response is null. Unable to retrieve requestID/clientID");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <R> void recordCallCompleted(@NonNull R r) {
        if (r instanceof OkHttpResponse) {
            OkHttpResponse okHttpResponse = (OkHttpResponse) r;
            if (this.request.isRetryPermitted(okHttpResponse)) {
                LOG.e("recordCallComplete: will retry");
                this.metricsMeta.put("statusCode", Integer.valueOf(okHttpResponse.code()));
                MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, GeneratedOutlineSupport1.outline91(new StringBuilder(), this.operationMetricNameRoot, MetricKeys.RETRY_SFX), 1.0d, this.metricsMeta);
                return;
            }
        }
        MetricsHelper.recordApiCallCompleted(CommsMetric.MetricType.Operational, this.operationMetricNameRoot, r, this.metricsMeta);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ServiceException serviceExceptionFromResponse(Response response) {
        Headers headers = response.request().headers();
        long receivedResponseAtMillis = response.receivedResponseAtMillis() - response.sentRequestAtMillis();
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Unexpected code ");
        outline1.append(response.code());
        return new ServiceException(outline1.toString(), response.code(), headers.get("X-Amzn-RequestId"), headers.get(Constants.ACMS_HEADER_CLIENT_ID), Long.valueOf(receivedResponseAtMillis));
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Call
    public void cancel() {
        this.call.cancel();
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Call
    public void enqueue(final IHttpClient.Callback callback) {
        this.call.enqueue(new Callback() { // from class: com.amazon.deecomms.common.network.okhttp.OkHttpCall.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                OkHttpCall.this.recordCallCompleted(iOException);
                IHttpClient.Callback callback2 = callback;
                if (callback2 == null) {
                    OkHttpCall.LOG.e("Callback is null in OkHttpCall onFailure, exception: ", iOException);
                } else {
                    callback2.onFailure(OkHttpCall.this, new ServiceException(iOException));
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                OkHttpCall okHttpCall = OkHttpCall.this;
                OkHttpResponse okHttpResponse = new OkHttpResponse(okHttpCall, response, okHttpCall.jsonConverter);
                OkHttpCall.this.recordCallCompleted(okHttpResponse);
                if (!response.isSuccessful()) {
                    OkHttpCall.LOG.i("Response is unsuccessful");
                    CommsLogger commsLogger = OkHttpCall.LOG;
                    StringBuilder outline1 = GeneratedOutlineSupport.outline1("Msg: ");
                    outline1.append(response.message());
                    outline1.append(" body: ");
                    outline1.append(response.body() != null ? response.body().string() : null);
                    commsLogger.d(outline1.toString());
                    OkHttpCall.this.printRequestIDAndClientID(response);
                    if (OkHttpCall.this.request.isRetryPermitted(okHttpResponse)) {
                        OkHttpCall.this.request.retryCall().enqueue(callback);
                    } else {
                        IHttpClient.Callback callback2 = callback;
                        if (callback2 != null) {
                            callback2.onFailure(OkHttpCall.this, OkHttpCall.serviceExceptionFromResponse(response));
                        } else {
                            OkHttpCall.LOG.i("Callback is null in OkHttpCall onResponse, returning");
                        }
                    }
                    response.close();
                    return;
                }
                IHttpClient.Callback callback3 = callback;
                if (callback3 == null) {
                    OkHttpCall.LOG.i("Callback is null in OkHttpCall onResponse, returning");
                    response.close();
                    return;
                }
                callback3.onResult(OkHttpCall.this, okHttpResponse);
            }
        });
    }

    Map<String, Object> getMetricsMetadata() {
        return this.metricsMeta;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Call
    public String getOperationMetricNameRoot() {
        return this.operationMetricNameRoot;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Call
    @Nullable
    public String getRequestId() {
        IHttpClient.Request request = this.request;
        if (request != null) {
            return request.getRequestId();
        }
        return null;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Call
    public boolean isCanceled() {
        return this.call.isCanceled();
    }

    public /* synthetic */ Observable lambda$toObservable$0$OkHttpCall() {
        try {
            Response execute = this.call.execute();
            recordCallCompleted(new OkHttpResponse(this, execute, this.jsonConverter));
            if (!execute.isSuccessful()) {
                return Observable.error(serviceExceptionFromResponse(execute));
            }
            return Observable.just(new OkHttpResponse(this, execute, this.jsonConverter));
        } catch (IOException e) {
            recordCallCompleted(e);
            return Observable.error(new ServiceException(e));
        }
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Call
    public void setOperationMetricNameRoot(String str) {
        this.operationMetricNameRoot = str;
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Call
    public Observable<IHttpClient.Response> toObservable() {
        return Observable.defer(new Func0() { // from class: com.amazon.deecomms.common.network.okhttp.-$$Lambda$OkHttpCall$KBWfZ7Gda5STvJx4YJNVXNnAA3k
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            /* renamed from: call */
            public final Object mo13098call() {
                return OkHttpCall.this.lambda$toObservable$0$OkHttpCall();
            }
        }).doOnUnsubscribe(new Action0() { // from class: com.amazon.deecomms.common.network.okhttp.-$$Lambda$AgqodPq6qtC-ZGFntM230P4ZVyo
            @Override // rx.functions.Action0
            public final void call() {
                OkHttpCall.this.cancel();
            }
        });
    }

    @Override // com.amazon.deecomms.common.network.IHttpClient.Call
    /* renamed from: execute  reason: collision with other method in class */
    public OkHttpResponse mo3640execute() throws ServiceException {
        Response execute;
        Response response = null;
        String str = null;
        try {
            execute = this.call.execute();
        } catch (IOException e) {
            e = e;
        }
        try {
            OkHttpResponse okHttpResponse = new OkHttpResponse(this, execute, this.jsonConverter);
            recordCallCompleted(okHttpResponse);
            if (execute.isSuccessful()) {
                return okHttpResponse;
            }
            LOG.i("Response is unsuccessful");
            CommsLogger commsLogger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("Msg: ");
            sb.append(execute.message());
            sb.append(" body: ");
            if (execute.body() != null) {
                str = execute.body().string();
            }
            sb.append(str);
            commsLogger.d(sb.toString());
            printRequestIDAndClientID(execute);
            if (this.request.isRetryPermitted(okHttpResponse)) {
                IHttpClient.Response mo3640execute = this.request.retryCall().mo3640execute();
                if (mo3640execute instanceof OkHttpResponse) {
                    return (OkHttpResponse) mo3640execute;
                }
                throw serviceExceptionFromResponse(execute);
            }
            throw serviceExceptionFromResponse(execute);
        } catch (IOException e2) {
            e = e2;
            response = execute;
            recordCallCompleted(e);
            printRequestIDAndClientID(response);
            throw new ServiceException(e);
        }
    }
}
