package com.amazon.alexa.accessory.notificationpublisher.servicerequest;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.io.IOException;
import java.util.Locale;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/* loaded from: classes.dex */
public final class AnnounceWithContentRequestSender {
    private static final String ANNOUNCE_WITH_CONTENT_SERVICE_API = "/api/mobile/notifications";
    private static final String AVS_SERVICE_API = "/v2/directives";
    public static final MediaType JSON = MediaType.parse(LocationPublisher.CONTENT_TYPE_JSON);
    private static final int RETRY_TIMES_LIMIT = 2;
    private static final String TAG = "AnnounceWithContentRequestSender";

    private AnnounceWithContentRequestSender() {
    }

    public static void createAndSendAnnounceWithContentRequest(@NonNull AnnounceWithContentRequestBody announceWithContentRequestBody, @NonNull AnnounceWithContentResponseHandler announceWithContentResponseHandler) {
        createAndSendAnnounceWithContentRequest(announceWithContentRequestBody, announceWithContentResponseHandler, 2, "None");
    }

    public static void establishConnectionWithAVS(@NonNull final AnnounceWithContentRequestBody announceWithContentRequestBody, @NonNull final AnnounceWithContentResponseHandler announceWithContentResponseHandler, final int i, final String str) {
        Request.Builder builder = new Request.Builder();
        Request.Builder url = builder.url(DependencyProvider.getAvsProperties().getAveEndpoint() + AVS_SERVICE_API);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(HttpRequestConstants.BEARER_TOKEN_PREFIX);
        outline107.append(DependencyProvider.getAccessToken(TAG));
        DependencyProvider.getHttpClient().newCall(url.addHeader("x-amz-access-token", outline107.toString()).get().build()).enqueue(new Callback() { // from class: com.amazon.alexa.accessory.notificationpublisher.servicerequest.AnnounceWithContentRequestSender.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Log.e(AnnounceWithContentRequestSender.TAG, "establishConnectionWithAVS - onFailure.", iOException);
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.ESTABLISH_AVS_CONNECTION_EXCEPTION);
                if (i >= 1) {
                    MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_retry_avs_call_failed");
                    AnnounceWithContentRequestSender.createAndSendAnnounceWithContentRequest(announceWithContentRequestBody, announceWithContentResponseHandler, i - 1, MetricsConstants.RETRY_REASON_AVS_CALL_FAILED);
                    return;
                }
                announceWithContentResponseHandler.handleAnnounceWithContentResponse(false, announceWithContentRequestBody);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                String str2 = AnnounceWithContentRequestSender.TAG;
                Log.d(str2, "establishConnectionWithAVS - onResponse " + response);
                MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(MetricsConstants.ESTABLISH_AVS_CONNECTION);
                outline1072.append(response.code());
                metricsRecorder.recordCounter(outline1072.toString());
                response.close();
                AnnounceWithContentRequestSender.sendAnnounceWithContentRequest(announceWithContentRequestBody, announceWithContentResponseHandler, i, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean responseIsSuccessful(Response response) {
        if (response == null) {
            return false;
        }
        int code = response.code();
        MetricsRecorder.getInstance().recordCounter(GeneratedOutlineSupport1.outline49("FocusFilter_createMobileNotification_http_", code));
        if (code == 201) {
            return true;
        }
        String str = TAG;
        Log.w(str, "request failed with response." + response);
        MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_error_http");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendAnnounceWithContentRequest(@NonNull final AnnounceWithContentRequestBody announceWithContentRequestBody, @NonNull final AnnounceWithContentResponseHandler announceWithContentResponseHandler, final int i, final String str) {
        String webEndpoint = DependencyProvider.getEnvironmentService().getWebEndpoint();
        String outline72 = GeneratedOutlineSupport1.outline72(webEndpoint, ANNOUNCE_WITH_CONTENT_SERVICE_API);
        String cookie = DependencyProvider.getCookie(webEndpoint);
        String accessToken = DependencyProvider.getAccessToken(TAG);
        if (i < 2) {
            MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_retry");
            try {
                cookie = CookieHelper.getRefreshedCookies(cookie);
                String str2 = TAG;
                Log.d(str2, "sendAnnounceWithContentRequest - this is a retry request, refreshed cookie: " + cookie);
            } catch (RxBlockingCallException e) {
                Log.e(TAG, "getRefreshedCookies failed when sendAnnounceWithContentRequest. RxBlockingCallException:", e);
                MetricsRecorder.getInstance().recordCounterWithDebounce("FocusFilter_rx_blocking_call_exception_sendAnnounceWithContentRequest", MetricsRecorder.customAttributesForException(e));
            }
        }
        if (CookieHelper.isExpiredCookie(cookie)) {
            Log.d(TAG, "cookie expired");
            if (i < 1) {
                return;
            }
            MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_retry_expired_cookie");
            createAndSendAnnounceWithContentRequest(announceWithContentRequestBody, announceWithContentResponseHandler, i - 1, MetricsConstants.RETRY_REASON_EXPIRED_COOKIE);
            return;
        }
        Request build = new Request.Builder().url(outline72).addHeader("Content-Type", HttpRequestConstants.JSON_CONTENT_TYPE_VALUE).addHeader("x-amz-access-token", accessToken).addHeader("Cookie", cookie).post(RequestBody.create(JSON, announceWithContentRequestBody.toString())).build();
        GeneratedOutlineSupport1.outline166("RequestUrl - ", outline72, TAG);
        String str3 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("isCookieEmpty? ");
        outline107.append(Strings.isNullOrEmpty(cookie));
        outline107.append(" isTokenEmpty? ");
        outline107.append(Strings.isNullOrEmpty(accessToken));
        Log.i(str3, outline107.toString());
        String str4 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Request header debug info: ");
        outline1072.append(build.headers());
        Log.d(str4, outline1072.toString());
        if (i < 2) {
            MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_retry_called");
        }
        DependencyProvider.getHttpClient().newCall(build).enqueue(new Callback() { // from class: com.amazon.alexa.accessory.notificationpublisher.servicerequest.AnnounceWithContentRequestSender.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Log.e(AnnounceWithContentRequestSender.TAG, "onFailure.", iOException);
                if (i >= 1) {
                    MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_retry_exception");
                    AnnounceWithContentRequestSender.createAndSendAnnounceWithContentRequest(announceWithContentRequestBody, announceWithContentResponseHandler, i - 1, MetricsConstants.RETRY_REASON_EXCEPTION);
                    return;
                }
                announceWithContentResponseHandler.handleAnnounceWithContentResponse(false, announceWithContentRequestBody);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                String str5 = AnnounceWithContentRequestSender.TAG;
                Log.d(str5, "onResponse " + response);
                boolean responseIsSuccessful = AnnounceWithContentRequestSender.responseIsSuccessful(response);
                int code = response.code();
                response.close();
                String str6 = AnnounceWithContentRequestSender.TAG;
                Log.i(str6, "onResponse success: " + responseIsSuccessful + " code: " + code);
                if ((code == 408 || code == 200) && i >= 1) {
                    MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
                    metricsRecorder.recordCounter("FocusFilter_createMobileNotification_retry_http_" + code);
                    AnnounceWithContentRequestSender.createAndSendAnnounceWithContentRequest(announceWithContentRequestBody, announceWithContentResponseHandler, i - 1, "_http_" + code);
                    return;
                }
                if (i < 2 && code == 201) {
                    MetricsRecorder metricsRecorder2 = MetricsRecorder.getInstance();
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("FocusFilter_createMobileNotification_retry");
                    outline1073.append(str);
                    outline1073.append(MetricsConstants.SUCCESS_SUFFIX);
                    metricsRecorder2.recordCounter(outline1073.toString());
                }
                announceWithContentResponseHandler.handleAnnounceWithContentResponse(responseIsSuccessful, announceWithContentRequestBody);
            }
        });
    }

    public static void createAndSendAnnounceWithContentRequest(@NonNull AnnounceWithContentRequestBody announceWithContentRequestBody, @NonNull AnnounceWithContentResponseHandler announceWithContentResponseHandler, int i, String str) {
        Log.i(TAG, String.format(Locale.US, "createAndSendAnnounceWithContentRequest - request body without sensitive info: %s", announceWithContentRequestBody.toLoggableString()));
        try {
            if (FeatureAccessChecker.hasRemoveWakeupCallFeatureAccess()) {
                Log.i(TAG, "createAndSendAnnounceWithContentRequest - user has weblab access to skip wake up call.");
                sendAnnounceWithContentRequest(announceWithContentRequestBody, announceWithContentResponseHandler, i, str);
            } else {
                establishConnectionWithAVS(announceWithContentRequestBody, announceWithContentResponseHandler, i, str);
            }
        } catch (Exception e) {
            Log.e(TAG, "createAndSendAnnounceWithContentRequest - error.", e);
            announceWithContentResponseHandler.handleAnnounceWithContentResponse(false, announceWithContentRequestBody);
        }
    }
}
