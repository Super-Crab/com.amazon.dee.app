package com.amazon.alexa.accessory.notificationpublisher.servicerequest;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
/* loaded from: classes.dex */
public final class DevicePreferencesRequestSender {
    private static final String TAG = "DevicePreferencesRequestSender";

    private DevicePreferencesRequestSender() {
    }

    private static String buildPreferenceAPIUrl() {
        return GeneratedOutlineSupport1.outline72(DependencyProvider.getEnvironmentService().getWebEndpoint(), "/api/device-preferences?cached=false");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void getDevicePreferencesRequest(@androidx.annotation.NonNull final com.amazon.alexa.accessory.notificationpublisher.servicerequest.DevicePreferencesResponseHandler r3) {
        /*
            java.lang.String r0 = buildPreferenceAPIUrl()     // Catch: java.lang.Exception -> Ld java.lang.IllegalArgumentException -> L1f com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L21
            com.amazon.alexa.accessory.notificationpublisher.servicerequest.DevicePreferencesRequestSender$1 r1 = new com.amazon.alexa.accessory.notificationpublisher.servicerequest.DevicePreferencesRequestSender$1     // Catch: java.lang.Exception -> Ld java.lang.IllegalArgumentException -> L1f com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L21
            r1.<init>()     // Catch: java.lang.Exception -> Ld java.lang.IllegalArgumentException -> L1f com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L21
            makeGetRequest(r0, r1)     // Catch: java.lang.Exception -> Ld java.lang.IllegalArgumentException -> L1f com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L21
            goto L52
        Ld:
            r3 = move-exception
            java.lang.String r0 = com.amazon.alexa.accessory.notificationpublisher.servicerequest.DevicePreferencesRequestSender.TAG
            java.lang.String r1 = "getDevicePreferencesRequest - failed with error."
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.e(r0, r1, r3)
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r3 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.lang.String r0 = "FocusFilter_preferences_get_exception_client"
            r3.recordCounter(r0)
            goto L52
        L1f:
            r3 = move-exception
            goto L22
        L21:
            r3 = move-exception
        L22:
            java.lang.String r0 = com.amazon.alexa.accessory.notificationpublisher.servicerequest.DevicePreferencesRequestSender.TAG
            java.lang.String r1 = "getDevicePreferencesRequest - failed with Exception"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.e(r0, r1, r3)
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r0 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.lang.String r1 = "FocusFilter_preferences_get_exception_client_"
            java.lang.StringBuilder r1 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r1)
            java.lang.String r2 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.truncateExceptionMessage(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.recordCounter(r1)
            boolean r0 = r3 instanceof com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException
            if (r0 == 0) goto L52
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r0 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.util.Map r3 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.customAttributesForException(r3)
            java.lang.String r1 = "FocusFilter_rx_blocking_call_exception_getDevicePreferencesRequest"
            r0.recordCounter(r1, r3)
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.servicerequest.DevicePreferencesRequestSender.getDevicePreferencesRequest(com.amazon.alexa.accessory.notificationpublisher.servicerequest.DevicePreferencesResponseHandler):void");
    }

    static void makeGetRequest(@NonNull String str, @NonNull Callback callback) throws IllegalArgumentException, RxBlockingCallException {
        OkHttpClient httpClient = DependencyProvider.getHttpClient();
        GeneratedOutlineSupport1.outline166("makeGetRequest - url: ", str, TAG);
        String validCookie = CookieHelper.getValidCookie();
        Request.Builder addHeader = new Request.Builder().url(str).addHeader("Content-Type", HttpRequestConstants.JSON_CONTENT_TYPE_VALUE);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(HttpRequestConstants.BEARER_TOKEN_PREFIX);
        outline107.append(DependencyProvider.getAccessToken(TAG));
        Request build = addHeader.addHeader("x-amz-access-token", outline107.toString()).addHeader("Cookie", validCookie).get().build();
        String str2 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("makeGetRequest - request: ");
        outline1072.append(build.headers().toString());
        outline1072.append(" ");
        outline1072.append(build);
        Log.d(str2, outline1072.toString());
        httpClient.newCall(build).enqueue(callback);
    }
}
