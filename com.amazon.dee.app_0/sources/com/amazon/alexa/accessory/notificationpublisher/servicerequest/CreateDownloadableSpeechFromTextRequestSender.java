package com.amazon.alexa.accessory.notificationpublisher.servicerequest;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/* loaded from: classes.dex */
public final class CreateDownloadableSpeechFromTextRequestSender {
    public static final MediaType JSON = MediaType.parse(LocationPublisher.CONTENT_TYPE_JSON);
    private static final int RETRY_TIMES_LIMIT = 2;
    private static final String TAG = "CreateDownloadableSpeechFromTextRequestSender";
    private static final String TTS_SERVICE_API = "/api/mobile/notifications/tts";

    private CreateDownloadableSpeechFromTextRequestSender() {
    }

    public static void createDownloadableSpeechFromTextRequest(@NonNull CreateDownloadableSpeechFromTextRequestBody createDownloadableSpeechFromTextRequestBody, @NonNull CreateDownloadableSpeechFromTextResponseHandler createDownloadableSpeechFromTextResponseHandler, @NonNull Map<String, Object> map) {
        createDownloadableSpeechFromTextRequest(createDownloadableSpeechFromTextRequestBody, createDownloadableSpeechFromTextResponseHandler, 2, "None", map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean responseIsSuccessful(Response response, @NonNull Map<String, Object> map) {
        if (response == null) {
            return false;
        }
        int code = response.code();
        MetricsRecorder.getInstance().recordCounter(GeneratedOutlineSupport1.outline49("FocusFilter_createMobileNotification_http_", code), map);
        if (code == 201) {
            return true;
        }
        String str = TAG;
        Log.w(str, "request failed with response." + response);
        MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_error_http", map);
        return false;
    }

    @VisibleForTesting
    static void sendDownloadableSpeechFromTextRequest(@NonNull final CreateDownloadableSpeechFromTextRequestBody createDownloadableSpeechFromTextRequestBody, @NonNull final CreateDownloadableSpeechFromTextResponseHandler createDownloadableSpeechFromTextResponseHandler, final int i, final String str, @NonNull final Map<String, Object> map) {
        String webEndpoint = DependencyProvider.getEnvironmentService().getWebEndpoint();
        String outline72 = GeneratedOutlineSupport1.outline72(webEndpoint, TTS_SERVICE_API);
        String cookie = DependencyProvider.getCookie(webEndpoint);
        String accessToken = DependencyProvider.getAccessToken(TAG);
        if (i < 2) {
            MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_retry", map);
            try {
                cookie = CookieHelper.getRefreshedCookies(cookie);
                String str2 = TAG;
                Log.d(str2, "sendDownloadableSpeechFromTextRequest - this is a retry request, refreshed cookie: " + cookie);
            } catch (RxBlockingCallException e) {
                Log.e(TAG, "getRefreshedCookies failed when sendDownloadableSpeechFromTextRequest. RxBlockingCallException:", e);
                MetricsRecorder.getInstance().recordCounterWithDebounce("FocusFilter_rx_blocking_call_exception_sendDownloadableSpeechFromTextRequest", MetricsRecorder.customAttributesForException(e));
            }
        }
        if (CookieHelper.isExpiredCookie(cookie)) {
            Log.d(TAG, "cookie expired");
            if (i < 1) {
                return;
            }
            MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_retry_expired_cookie", map);
            createDownloadableSpeechFromTextRequest(createDownloadableSpeechFromTextRequestBody, createDownloadableSpeechFromTextResponseHandler, i - 1, MetricsConstants.RETRY_REASON_EXPIRED_COOKIE, map);
            return;
        }
        Request build = new Request.Builder().url(outline72).addHeader("Content-Type", HttpRequestConstants.JSON_CONTENT_TYPE_VALUE).addHeader("x-amz-access-token", accessToken).addHeader("Cookie", cookie).post(RequestBody.create(JSON, createDownloadableSpeechFromTextRequestBody.toString())).build();
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
            MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_retry_called", map);
        }
        DependencyProvider.getHttpClient().newCall(build).enqueue(new Callback() { // from class: com.amazon.alexa.accessory.notificationpublisher.servicerequest.CreateDownloadableSpeechFromTextRequestSender.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Log.e(CreateDownloadableSpeechFromTextRequestSender.TAG, "onFailure.", iOException);
                if (i >= 1) {
                    MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_retry_exception", map);
                    CreateDownloadableSpeechFromTextRequestSender.createDownloadableSpeechFromTextRequest(createDownloadableSpeechFromTextRequestBody, createDownloadableSpeechFromTextResponseHandler, i - 1, MetricsConstants.RETRY_REASON_EXCEPTION, map);
                    return;
                }
                createDownloadableSpeechFromTextResponseHandler.handleDownloadableSpeechFromTextResponse(false, createDownloadableSpeechFromTextRequestBody, map);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                String str5 = CreateDownloadableSpeechFromTextRequestSender.TAG;
                Log.d(str5, "onResponse " + response);
                boolean responseIsSuccessful = CreateDownloadableSpeechFromTextRequestSender.responseIsSuccessful(response, map);
                int code = response.code();
                response.close();
                String str6 = CreateDownloadableSpeechFromTextRequestSender.TAG;
                Log.i(str6, "onResponse success: " + responseIsSuccessful + " code: " + code);
                if ((code == 408 || code == 200) && i >= 1) {
                    MetricsRecorder.getInstance().recordCounter(GeneratedOutlineSupport1.outline49("FocusFilter_createMobileNotification_retry_http_", code), map);
                    CreateDownloadableSpeechFromTextRequestSender.createDownloadableSpeechFromTextRequest(createDownloadableSpeechFromTextRequestBody, createDownloadableSpeechFromTextResponseHandler, i - 1, GeneratedOutlineSupport1.outline49("_http_", code), map);
                    return;
                }
                if (i < 2 && code == 201) {
                    MetricsRecorder.getInstance().recordCounter(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("FocusFilter_createMobileNotification_retry"), str, MetricsConstants.SUCCESS_SUFFIX), map);
                }
                createDownloadableSpeechFromTextResponseHandler.handleDownloadableSpeechFromTextResponse(responseIsSuccessful, createDownloadableSpeechFromTextRequestBody, map);
            }
        });
    }

    public static void createDownloadableSpeechFromTextRequest(@NonNull CreateDownloadableSpeechFromTextRequestBody createDownloadableSpeechFromTextRequestBody, @NonNull CreateDownloadableSpeechFromTextResponseHandler createDownloadableSpeechFromTextResponseHandler, int i, String str, @NonNull Map<String, Object> map) {
        Log.i(TAG, String.format(Locale.US, "createDownloadableSpeechFromTextRequest - request body without sensitive info: %s", createDownloadableSpeechFromTextRequestBody.toLoggableString()));
        try {
            sendDownloadableSpeechFromTextRequest(createDownloadableSpeechFromTextRequestBody, createDownloadableSpeechFromTextResponseHandler, i, str, map);
        } catch (Exception e) {
            Log.e(TAG, "createDownloadableSpeechFromTextRequest - error.", e);
            createDownloadableSpeechFromTextResponseHandler.handleDownloadableSpeechFromTextResponse(false, createDownloadableSpeechFromTextRequestBody, map);
        }
    }
}
