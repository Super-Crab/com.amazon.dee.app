package com.amazon.alexa.accessory.notificationpublisher.servicerequest;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.storage.AsyncLocalStorage;
import com.amazon.alexa.accessory.notificationpublisher.storage.PutValueCallback;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.storage.StorageWrapper;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.security.SecureRandom;
import java.util.Locale;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/* loaded from: classes.dex */
public class UssSettingRequestSender {
    public static final MediaType JSON = MediaType.parse(LocationPublisher.CONTENT_TYPE_JSON);
    private static final String REMOVE_USS_OPERATION = "removeFromMap";
    private static final String TAG = "UssSettingRequestSender";
    private static final String UPDATE_USS_OPERATION = "putInMap";
    private final AsyncLocalStorage storage;

    public UssSettingRequestSender(AsyncLocalStorage asyncLocalStorage) {
        this.storage = asyncLocalStorage;
    }

    private String appendCsrfToCookiesIfNeeded(String str) {
        String csrfCookieValue = getCsrfCookieValue(str);
        SecureRandom secureRandom = new SecureRandom();
        if (Strings.isNullOrEmpty(csrfCookieValue)) {
            Log.d(TAG, "appendCsrfToCookiesIfNeeded - no csrf in original cookie, will generate one");
            String outline77 = GeneratedOutlineSupport1.outline77(str, "; ", HttpRequestConstants.CSRF, Config.Compare.EQUAL_TO, Integer.toString(secureRandom.nextInt()));
            GeneratedOutlineSupport1.outline165("appendCsrfToCookiesIfNeeded - new cookie is: ", outline77, TAG);
            return outline77;
        }
        return str;
    }

    private String buildUssAPIUrl(String str, String str2) {
        try {
            String obj = this.storage.getSync(SettingsStorageModule.DEVICE_ACCOUNT_ID_KEY, null).toString();
            String str3 = TAG;
            Log.d(str3, "buildUssAPIUrl - device account id is: " + obj);
            String webEndpoint = DependencyProvider.getEnvironmentService().getWebEndpoint();
            if (Strings.isNullOrEmpty(str2)) {
                return webEndpoint + String.format("/api/v1/devices/%s/settings/%s", obj, str);
            }
            return webEndpoint + String.format("/v2/devices/%s/settings/%s/%s", obj, str, str2);
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "buildUssAPIUrl failed. RxBlockingCallException: %s", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_buildUssAPIUrl", MetricsRecorder.customAttributesForException(e));
            return null;
        } catch (Exception e2) {
            String str4 = TAG;
            Log.e(str4, "buildUssAPIUrl - Exception: " + e2);
            return null;
        }
    }

    private String getCsrfCookieValue(String str) {
        if (str == null) {
            return null;
        }
        for (String str2 : str.split(";")) {
            String[] split = str2.split(Config.Compare.EQUAL_TO);
            if (split.length >= 2) {
                String trim = split[0].trim();
                String trim2 = split[1].trim();
                if (HttpRequestConstants.CSRF.equals(trim)) {
                    return trim2;
                }
            }
        }
        return null;
    }

    private void getDeviceAccountIdAndSendRequest(final String str, final boolean z, final Object obj, final UssSettingResponseHandler ussSettingResponseHandler) {
        DevicePreferencesRequestSender.getDevicePreferencesRequest(new DevicePreferencesResponseHandler() { // from class: com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.1
            @Override // com.amazon.alexa.accessory.notificationpublisher.servicerequest.DevicePreferencesResponseHandler
            public void handleGetDevicePreferencesResponse(boolean z2, Response response) {
                Log.i(UssSettingRequestSender.TAG, String.format("handleGetDevicePreferencesResponse - success: %s", Boolean.valueOf(z2)));
                String str2 = UssSettingRequestSender.TAG;
                Log.d(str2, "handleGetDevicePreferencesResponse - response " + response);
                try {
                    try {
                        if (!z2 || response == null) {
                            Log.w(UssSettingRequestSender.TAG, "handleGetDevicePreferencesResponse - GET device preferences failed");
                        } else {
                            String string = response.body().string();
                            String str3 = UssSettingRequestSender.TAG;
                            Log.d(str3, "handleGetDevicePreferencesResponse - value string: " + string);
                            UssSettingRequestSender.this.storage.put(SettingsStorageModule.DEVICE_ACCOUNT_ID_KEY, StorageWrapper.getDeviceAccountId(string), null, new PutValueCallback() { // from class: com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.1.1
                                @Override // com.amazon.alexa.accessory.notificationpublisher.storage.PutValueCallback
                                public void onComplete(Object obj2) {
                                    Log.d(UssSettingRequestSender.TAG, "handleGetDevicePreferencesResponse - successfully put device account id into local storage");
                                    AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                    if (z) {
                                        UssSettingRequestSender.this.getUssSettingsRequest(str, ussSettingResponseHandler);
                                    } else {
                                        UssSettingRequestSender.this.setUssSettingsRequest(str, obj);
                                    }
                                }

                                @Override // com.amazon.alexa.accessory.notificationpublisher.storage.PutValueCallback
                                public void onError(Throwable th) {
                                    String str4 = UssSettingRequestSender.TAG;
                                    Log.e(str4, "handleGetDevicePreferencesResponse - Failed to store device account id: " + th);
                                }
                            });
                        }
                        if (response == null) {
                            return;
                        }
                    } catch (Exception e) {
                        String str4 = UssSettingRequestSender.TAG;
                        Log.e(str4, "handleGetDevicePreferencesResponse: Exception - " + e);
                        if (response == null) {
                            return;
                        }
                    }
                    response.close();
                } catch (Throwable th) {
                    if (response != null) {
                        response.close();
                    }
                    throw th;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void getUssSettingsRequest(@androidx.annotation.NonNull final java.lang.String r6, @androidx.annotation.NonNull final com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingResponseHandler r7) {
        /*
            r5 = this;
            java.lang.String r0 = com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule.getDWCSSettingKeyFromLocalSettingKey(r6)     // Catch: java.lang.Exception -> L20 java.lang.IllegalArgumentException -> L32 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L34
            java.lang.String r1 = ""
            java.lang.String r0 = r5.buildUssAPIUrl(r0, r1)     // Catch: java.lang.Exception -> L20 java.lang.IllegalArgumentException -> L32 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L34
            java.lang.String r1 = com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.TAG     // Catch: java.lang.Exception -> L20 java.lang.IllegalArgumentException -> L32 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L34
            java.lang.String r2 = "getUssSettingsRequest - request url: %s"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L20 java.lang.IllegalArgumentException -> L32 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L34
            r4 = 0
            r3[r4] = r0     // Catch: java.lang.Exception -> L20 java.lang.IllegalArgumentException -> L32 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L34
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(r1, r2, r3)     // Catch: java.lang.Exception -> L20 java.lang.IllegalArgumentException -> L32 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L34
            com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender$5 r1 = new com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender$5     // Catch: java.lang.Exception -> L20 java.lang.IllegalArgumentException -> L32 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L34
            r1.<init>()     // Catch: java.lang.Exception -> L20 java.lang.IllegalArgumentException -> L32 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L34
            r5.makeGetRequest(r0, r1)     // Catch: java.lang.Exception -> L20 java.lang.IllegalArgumentException -> L32 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L34
            goto L65
        L20:
            r6 = move-exception
            java.lang.String r7 = com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.TAG
            java.lang.String r0 = "getUssSettingsRequest - failed with error."
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.e(r7, r0, r6)
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r6 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.lang.String r7 = "FocusFilter_settings_get_exception_client"
            r6.recordCounter(r7)
            goto L65
        L32:
            r6 = move-exception
            goto L35
        L34:
            r6 = move-exception
        L35:
            java.lang.String r7 = com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.TAG
            java.lang.String r0 = "getUssSettingsRequest - failed with Exception."
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.e(r7, r0, r6)
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r7 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.lang.String r0 = "FocusFilter_settings_get_exception_client_"
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            java.lang.String r1 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.truncateExceptionMessage(r6)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.recordCounter(r0)
            boolean r7 = r6 instanceof com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException
            if (r7 == 0) goto L65
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r7 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.util.Map r6 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.customAttributesForException(r6)
            java.lang.String r0 = "FocusFilter_rx_blocking_call_exception_getUssSettingsRequest"
            r7.recordCounter(r0, r6)
        L65:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.getUssSettingsRequest(java.lang.String, com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingResponseHandler):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setUssSettingsRequest(final java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            java.lang.String r6 = r6.toString()
            java.lang.String r0 = com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule.getDWCSSettingKeyFromLocalSettingKey(r5)
            java.lang.String r1 = ""
            java.lang.String r0 = r4.buildUssAPIUrl(r0, r1)
            java.lang.String r1 = com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.TAG
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            r2[r3] = r0
            java.lang.String r3 = "setUssSettingsRequest - request url: %s"
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(r1, r3, r2)
            com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender$2 r1 = new com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender$2     // Catch: java.lang.Exception -> L25 java.lang.IllegalArgumentException -> L37 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L39
            r1.<init>()     // Catch: java.lang.Exception -> L25 java.lang.IllegalArgumentException -> L37 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L39
            r4.makePutRequest(r0, r6, r1)     // Catch: java.lang.Exception -> L25 java.lang.IllegalArgumentException -> L37 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L39
            goto L6b
        L25:
            r5 = move-exception
            java.lang.String r6 = com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.TAG
            java.lang.String r0 = "Failed to setUssSettingsRequest. "
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.e(r6, r0, r5)
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r5 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.lang.String r6 = "FocusFilter_settings_put_exception_client"
            r5.recordCounter(r6)
            goto L6b
        L37:
            r5 = move-exception
            goto L3a
        L39:
            r5 = move-exception
        L3a:
            java.lang.String r6 = com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.TAG
            java.lang.String r0 = "setUssSettingsRequest - failed with Exception."
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.e(r6, r0, r5)
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r6 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.lang.String r0 = "FocusFilter_settings_put_exception_client_"
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            java.lang.String r1 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.truncateExceptionMessage(r5)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r6.recordCounter(r0)
            boolean r6 = r5 instanceof com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException
            if (r6 == 0) goto L6b
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r6 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.util.Map r5 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.customAttributesForException(r5)
            java.lang.String r0 = "FocusFilter_rx_blocking_call_exception_setUssSettingsRequest"
            r6.recordCounter(r0, r5)
        L6b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.setUssSettingsRequest(java.lang.String, java.lang.Object):void");
    }

    public void checkDeviceAccountIdAndGetUssSettingsRequest(@NonNull String str, @NonNull UssSettingResponseHandler ussSettingResponseHandler) {
        try {
            Object sync = this.storage.getSync(SettingsStorageModule.DEVICE_ACCOUNT_ID_KEY, null);
            if (sync != null && !Strings.isNullOrEmpty(sync.toString())) {
                getUssSettingsRequest(str, ussSettingResponseHandler);
            } else {
                getDeviceAccountIdAndSendRequest(str, true, null, ussSettingResponseHandler);
            }
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "checkDeviceAccountIdAndGetUssSettingsRequest failed with RxBlockingCallException", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_checkDeviceAccountIdAndGetUssSettingsRequest", MetricsRecorder.customAttributesForException(e));
        } catch (Exception e2) {
            String str2 = TAG;
            Log.e(str2, "checkDeviceAccountIdAndGetUssSettingsRequest Failed with Exception: " + e2);
        }
    }

    public void checkDeviceAccountIdAndSetUssSettingsRequest(@NonNull String str, @NonNull Object obj) {
        try {
            Object sync = this.storage.getSync(SettingsStorageModule.DEVICE_ACCOUNT_ID_KEY, null);
            if (sync != null && !Strings.isNullOrEmpty(sync.toString())) {
                setUssSettingsRequest(str, obj);
            } else {
                getDeviceAccountIdAndSendRequest(str, false, obj, null);
            }
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "checkDeviceAccountIdAndSetUssSettingsRequest failed with RxBlockingCallException", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_checkDeviceAccountIdAndSetUssSettingsRequest", MetricsRecorder.customAttributesForException(e));
        } catch (Exception e2) {
            String str2 = TAG;
            Log.e(str2, "checkDeviceAccountIdAndSetUssSettingsRequest Failed with Exception: " + e2);
        }
    }

    void makeGetRequest(@NonNull String str, @NonNull Callback callback) throws IllegalArgumentException, RxBlockingCallException {
        OkHttpClient httpClient = DependencyProvider.getHttpClient();
        GeneratedOutlineSupport1.outline165("url: ", str, TAG);
        String validCookie = CookieHelper.getValidCookie();
        Request.Builder addHeader = new Request.Builder().url(str).addHeader("Content-Type", HttpRequestConstants.JSON_CONTENT_TYPE_VALUE);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(HttpRequestConstants.BEARER_TOKEN_PREFIX);
        outline107.append(DependencyProvider.getAccessToken(TAG));
        Request build = addHeader.addHeader("x-amz-access-token", outline107.toString()).addHeader("Cookie", validCookie).get().build();
        String str2 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("request: ");
        outline1072.append(build.headers().toString());
        outline1072.append(" ");
        outline1072.append(build);
        Log.d(str2, outline1072.toString());
        httpClient.newCall(build).enqueue(callback);
    }

    void makePutRequest(@NonNull String str, @NonNull String str2, @NonNull Callback callback) throws IllegalArgumentException, RxBlockingCallException {
        Log.i(TAG, "makePutRequest");
        OkHttpClient httpClient = DependencyProvider.getHttpClient();
        Log.d(TAG, String.format(Locale.US, "makePutRequest - URL: %s, Body: %s", str, str2));
        String appendCsrfToCookiesIfNeeded = appendCsrfToCookiesIfNeeded(CookieHelper.getValidCookie());
        String csrfCookieValue = getCsrfCookieValue(appendCsrfToCookiesIfNeeded);
        RequestBody create = RequestBody.create(JSON, str2);
        Request.Builder addHeader = new Request.Builder().url(str).addHeader(HttpRequestConstants.CSRF, csrfCookieValue).addHeader("Content-Type", HttpRequestConstants.JSON_CONTENT_TYPE_VALUE);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(HttpRequestConstants.BEARER_TOKEN_PREFIX);
        outline107.append(DependencyProvider.getAccessToken(TAG));
        Request build = addHeader.addHeader("x-amz-access-token", outline107.toString()).addHeader("Accept", "application/json").addHeader("Cookie", appendCsrfToCookiesIfNeeded).addHeader("Accept-Language", HttpRequestConstants.DEFAULT_ACCEPT_LANGUAGE_VALUE).put(create).build();
        String str3 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("makePutRequest - Request: ");
        outline1072.append(build.headers().toString());
        outline1072.append(" ");
        outline1072.append(build);
        Log.d(str3, outline1072.toString());
        httpClient.newCall(build).enqueue(callback);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void putInMapUssSettingsRequest(java.lang.String r3, java.lang.Object r4) {
        /*
            r2 = this;
            java.lang.String r4 = r4.toString()
            java.lang.String r0 = "putInMap"
            java.lang.String r3 = r2.buildUssAPIUrl(r3, r0)
            com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender$3 r0 = new com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender$3     // Catch: java.lang.Exception -> L14 java.lang.IllegalArgumentException -> L27 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L29
            r0.<init>()     // Catch: java.lang.Exception -> L14 java.lang.IllegalArgumentException -> L27 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L29
            r2.makePutRequest(r3, r4, r0)     // Catch: java.lang.Exception -> L14 java.lang.IllegalArgumentException -> L27 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L29
            goto L5b
        L14:
            r3 = move-exception
            java.lang.String r4 = com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.TAG
            java.lang.String r0 = "putInMapUssSettingsRequest - error."
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.e(r4, r0, r3)
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r3 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.lang.String r4 = "FocusFilter_settings_put_exception_client"
            r3.recordCounter(r4)
            goto L5b
        L27:
            r3 = move-exception
            goto L2a
        L29:
            r3 = move-exception
        L2a:
            java.lang.String r4 = com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.TAG
            java.lang.String r0 = "putInMapUssSettingsRequest - failed with Exception."
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.e(r4, r0, r3)
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r4 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.lang.String r0 = "FocusFilter_settings_put_exception_client_"
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            java.lang.String r1 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.truncateExceptionMessage(r3)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4.recordCounter(r0)
            boolean r4 = r3 instanceof com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException
            if (r4 == 0) goto L5b
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r4 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.util.Map r3 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.customAttributesForException(r3)
            java.lang.String r0 = "FocusFilter_rx_blocking_call_exception_putInMapUssSettingsRequest"
            r4.recordCounter(r0, r3)
        L5b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.putInMapUssSettingsRequest(java.lang.String, java.lang.Object):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void removeUssSettingsRequest(java.lang.String r3, java.lang.Object r4) {
        /*
            r2 = this;
            java.lang.String r4 = r4.toString()
            java.lang.String r0 = "removeFromMap"
            java.lang.String r3 = r2.buildUssAPIUrl(r3, r0)
            com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender$4 r0 = new com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender$4     // Catch: java.lang.Exception -> L14 java.lang.IllegalArgumentException -> L27 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L29
            r0.<init>()     // Catch: java.lang.Exception -> L14 java.lang.IllegalArgumentException -> L27 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L29
            r2.makePutRequest(r3, r4, r0)     // Catch: java.lang.Exception -> L14 java.lang.IllegalArgumentException -> L27 com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException -> L29
            goto L5b
        L14:
            r3 = move-exception
            java.lang.String r4 = com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.TAG
            java.lang.String r0 = "removeUssSettingsRequest - error."
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.e(r4, r0, r3)
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r3 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.lang.String r4 = "FocusFilter_settings_put_exception_client"
            r3.recordCounter(r4)
            goto L5b
        L27:
            r3 = move-exception
            goto L2a
        L29:
            r3 = move-exception
        L2a:
            java.lang.String r4 = com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.TAG
            java.lang.String r0 = "removeUssSettingsRequest - failed with Exception."
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.e(r4, r0, r3)
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r4 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.lang.String r0 = "FocusFilter_settings_put_exception_client_"
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            java.lang.String r1 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.truncateExceptionMessage(r3)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4.recordCounter(r0)
            boolean r4 = r3 instanceof com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException
            if (r4 == 0) goto L5b
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r4 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.util.Map r3 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.customAttributesForException(r3)
            java.lang.String r0 = "FocusFilter_rx_blocking_call_exception_removeUssSettingsRequest"
            r4.recordCounter(r0, r3)
        L5b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender.removeUssSettingsRequest(java.lang.String, java.lang.Object):void");
    }
}
