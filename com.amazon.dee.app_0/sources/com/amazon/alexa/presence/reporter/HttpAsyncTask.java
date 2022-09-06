package com.amazon.alexa.presence.reporter;

import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import com.amazon.alexa.location.utils.EndpointsUtil;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.identity.IdentityHelper;
import com.amazon.alexa.presence.identity.PresenceAccessTokenProvider;
import com.amazon.alexa.presence.models.ResolveBeaconsRequest;
import com.amazon.alexa.presence.retry.PresenceRetryStrategy;
import com.amazon.alexa.presence.retry.RetriableException;
import com.amazon.alexa.presence.utils.MetricsUtil;
import com.amazonaws.services.s3.Headers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsServiceV2;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.UUID;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/* loaded from: classes9.dex */
public class HttpAsyncTask extends AsyncTask<String, Void, Void> {
    private static final String AUTHORIZATION_VALUE_FORMAT = "Bearer %s";
    private static final int UNAUTHORIZED_ERROR_CODE = 401;
    private final MetricsServiceV2 metricsServiceV2;
    private final OkHttpClient okHttpClient;
    private final PresenceAccessTokenProvider presenceAccessTokenProvider;
    private final PresenceRetryStrategy presenceRetryStrategy;
    private static final String TAG = Presence.tag();
    private static final MediaType JSON = MediaType.parse(LocationPublisher.CONTENT_TYPE_JSON);
    private static final String RESOLVE_BEACONS_URL = "%s/beacons/receiver/v1/resolutions";
    private static final String ENDPOINT = String.format(RESOLVE_BEACONS_URL, EndpointsUtil.ENDPOINT_NA_PROD);
    private static final Gson GSON = new Gson();

    public HttpAsyncTask(OkHttpClient okHttpClient, PresenceAccessTokenProvider presenceAccessTokenProvider, PresenceRetryStrategy presenceRetryStrategy, MetricsServiceV2 metricsServiceV2) {
        this.okHttpClient = okHttpClient;
        this.presenceAccessTokenProvider = presenceAccessTokenProvider;
        this.presenceRetryStrategy = presenceRetryStrategy;
        this.metricsServiceV2 = metricsServiceV2;
    }

    private Response makePostRequest(String str, String str2, String str3) throws IOException {
        String str4 = "url: " + str2;
        String str5 = "body: " + str3;
        String valueOf = String.valueOf(UUID.randomUUID());
        GeneratedOutlineSupport1.outline163("request id: ", valueOf, TAG);
        return this.okHttpClient.newCall(new Request.Builder().header("Authorization", String.format(AUTHORIZATION_VALUE_FORMAT, str)).header(Headers.REQUEST_ID, valueOf).url(str2).post(RequestBody.create(JSON, str3)).build()).execute();
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0061, code lost:
        if (r4 == null) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private okhttp3.Response reportToService(java.lang.String r11) {
        /*
            r10 = this;
            java.lang.String r0 = "resolve_io_exception"
            java.lang.String r1 = "IO Exception Encountered"
            java.lang.String r2 = "resolve"
            java.lang.String r3 = "resolve_beacons"
            r4 = 0
            com.amazon.alexa.presence.identity.PresenceAccessTokenProvider r5 = r10.presenceAccessTokenProvider     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.lang.String r5 = r5.getAccessToken()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.lang.String r8 = com.amazon.alexa.presence.reporter.HttpAsyncTask.ENDPOINT     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            okhttp3.Response r4 = r10.makePostRequest(r5, r8, r11)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            long r8 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            com.dee.app.metrics.MetricsServiceV2 r11 = r10.metricsServiceV2     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            long r8 = r8 - r6
            com.amazon.alexa.presence.utils.MetricsUtil.recordTime(r11, r2, r3, r8)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.lang.String r11 = com.amazon.alexa.presence.reporter.HttpAsyncTask.TAG     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            r5.<init>()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.lang.String r6 = "Response "
            r5.append(r6)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            r5.append(r6)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            android.util.Log.i(r11, r5)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            com.dee.app.metrics.MetricsServiceV2 r11 = r10.metricsServiceV2     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            com.amazon.alexa.presence.utils.MetricsUtil.recordSuccess(r11, r2, r3)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            com.dee.app.metrics.MetricsServiceV2 r11 = r10.metricsServiceV2     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            com.amazon.alexa.presence.utils.MetricsUtil.recordCount(r11, r2, r3)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
        L47:
            okhttp3.ResponseBody r11 = r4.body()
            r11.close()
            goto L64
        L4f:
            r11 = move-exception
            goto L65
        L51:
            r11 = move-exception
            java.lang.String r2 = com.amazon.alexa.presence.reporter.HttpAsyncTask.TAG     // Catch: java.lang.Throwable -> L4f
            android.util.Log.e(r2, r1, r11)     // Catch: java.lang.Throwable -> L4f
            com.dee.app.metrics.MetricsServiceV2 r11 = r10.metricsServiceV2     // Catch: java.lang.Throwable -> L4f
            com.amazon.alexa.presence.utils.MetricsUtil.recordFailure(r11, r0, r3, r1)     // Catch: java.lang.Throwable -> L4f
            com.dee.app.metrics.MetricsServiceV2 r11 = r10.metricsServiceV2     // Catch: java.lang.Throwable -> L4f
            com.amazon.alexa.presence.utils.MetricsUtil.recordCount(r11, r0, r3)     // Catch: java.lang.Throwable -> L4f
            if (r4 == 0) goto L64
            goto L47
        L64:
            return r4
        L65:
            if (r4 == 0) goto L6e
            okhttp3.ResponseBody r0 = r4.body()
            r0.close()
        L6e:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.presence.reporter.HttpAsyncTask.reportToService(java.lang.String):okhttp3.Response");
    }

    boolean didRefreshAuthToken() {
        return new IdentityHelper().requestRefreshAuthToken();
    }

    @VisibleForTesting
    Runnable getRunnableInstance(final String str) {
        return new Runnable() { // from class: com.amazon.alexa.presence.reporter.-$$Lambda$HttpAsyncTask$X8feWXD9j_QuUKv4_ScKmmu4uHk
            @Override // java.lang.Runnable
            public final void run() {
                HttpAsyncTask.this.lambda$getRunnableInstance$0$HttpAsyncTask(str);
            }
        };
    }

    public /* synthetic */ void lambda$getRunnableInstance$0$HttpAsyncTask(String str) {
        Response reportToService = reportToService(str);
        if (reportToService == null || reportToService.code() == 401) {
            if (!didRefreshAuthToken()) {
                Log.w(TAG, "Could not refresh auth token in Core. Not retrying");
                return;
            }
            throw new RetriableException();
        }
    }

    public void sendRequest(ResolveBeaconsRequest resolveBeaconsRequest) {
        String json = GSON.toJson(resolveBeaconsRequest);
        GeneratedOutlineSupport1.outline158("JSON string", json);
        execute(json);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(String... strArr) {
        try {
            String str = strArr[0];
            MetricsUtil.recordZeroCount(this.metricsServiceV2, MetricsUtil.MetricsId.RESOLVE, MetricsUtil.Method.RESOLVE_BEACONS);
            MetricsUtil.recordZeroCount(this.metricsServiceV2, MetricsUtil.MetricsId.RESOLVE_IO_EXCEPTION, MetricsUtil.Method.RESOLVE_BEACONS);
            MetricsUtil.recordZeroCount(this.metricsServiceV2, MetricsUtil.MetricsId.RESOLVE_FAIL_AFTER_RETRIES, MetricsUtil.Method.RESOLVE_BEACONS);
            if (!this.presenceRetryStrategy.apply(getRunnableInstance(str))) {
                Log.e(TAG, "Resolve Beacons failed after retries.");
                MetricsUtil.recordFailure(this.metricsServiceV2, MetricsUtil.MetricsId.RESOLVE_FAIL_AFTER_RETRIES, MetricsUtil.Method.RESOLVE_BEACONS, "Resolve Beacons failed after retries.");
            }
            return null;
        } catch (Throwable th) {
            try {
                Log.e(TAG, "Unhandled error while resolving beacons.", th);
            } catch (Throwable unused) {
            }
            return null;
        }
    }
}
