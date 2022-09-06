package com.amazon.alexa.accessory.notificationpublisher.servicerequest;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
/* loaded from: classes.dex */
public final class GetFocusFilterTemplatesFromS3RequestSender {
    public static final String ANDROID_OS_TYPE = "ANDROID";
    public static final String ETAG = "etag";
    private static final String GET_FOCUS_FILTER_TEMPLATES_API = "/api/mobile/notifications/templates";
    public static final MediaType JSON = MediaType.parse(LocationPublisher.CONTENT_TYPE_JSON);
    private static final String OS_TYPE = "os=";
    private static final String QA_TEST_RELEASE_VERSION = "QA_TEST";
    private static final String RELEASE_VERSION = "version=";
    private static final String RELEASE_VERSION_VALUE = "V2";
    public static final int RETRY_TIMES_LIMIT = 2;
    private static final String TAG = "GetFocusFilterTemplatesFromS3RequestSender";
    public static final String TEMPLATE_KEY = "template";
    private static final String TEMPLATE_TYPE = "template=";

    /* loaded from: classes.dex */
    public enum TemplateType {
        PARSE(BaseTemplateProvider.PARSER_TEMPLATE_FILENAME),
        TRANSFORM(BaseTemplateProvider.TRANSFORMER_TEMPLATE_FILENAME),
        IGNORED(BaseTemplateProvider.IGNORED_NOTIFICATION_TEMPLATE_FILENAME);
        
        private final String fileName;

        TemplateType(String str) {
            this.fileName = str;
        }

        public String getFileName() {
            return this.fileName;
        }
    }

    private GetFocusFilterTemplatesFromS3RequestSender() {
    }

    private static String buildRequestUrl(String str, String str2, String str3) {
        String outline92 = GeneratedOutlineSupport1.outline92(GeneratedOutlineSupport1.outline116(str, GET_FOCUS_FILTER_TEMPLATES_API, WebConstants.UriConstants.QUESTIONMARK_KEY, OS_TYPE, str2), ";", TEMPLATE_TYPE, str3);
        String str4 = FeatureAccessChecker.hasFetchCloudTemplatesFromTestFolderAccess() ? QA_TEST_RELEASE_VERSION : RELEASE_VERSION_VALUE;
        String str5 = TAG;
        Log.i(str5, "buildRequestUrl - release version: " + str4);
        return !Strings.isNullOrEmpty(str4) ? GeneratedOutlineSupport1.outline76(outline92, ";", RELEASE_VERSION, str4) : outline92;
    }

    public static void getFocusFilterTemplatesFromS3Request(@NonNull final String str, @NonNull final TemplateType templateType, final String str2, @NonNull final GetFocusFilterTemplatesFromS3ResponseHandler getFocusFilterTemplatesFromS3ResponseHandler, final int i) {
        try {
            String str3 = TAG;
            Log.d(str3, "getFocusFilterTemplatesFromS3Request - templateType: " + templateType.name());
            String webEndpoint = DependencyProvider.getEnvironmentService().getWebEndpoint();
            String buildRequestUrl = buildRequestUrl(webEndpoint, str, templateType.name());
            String cookie = DependencyProvider.getCookie(webEndpoint);
            String accessToken = DependencyProvider.getAccessToken(TAG);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.GET_TEMPLATE_FROM_CLOUD_REQUEST_START);
            if (i < 2) {
                try {
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.GET_TEMPLATE_FROM_CLOUD_RETRY);
                    cookie = CookieHelper.getRefreshedCookies(cookie);
                    String str4 = TAG;
                    Log.d(str4, "getFocusFilterTemplatesFromS3Request - this is a retry request, refreshed cookie: " + cookie);
                } catch (RxBlockingCallException e) {
                    Log.e(TAG, "getRefreshedCookies failed when getFocusFilterTemplatesFromS3Request. RxBlockingCallException:", e);
                    MetricsRecorder.getInstance().recordCounterWithDebounce("FocusFilter_rx_blocking_call_exception_getFocusFilterTemplatesFromS3Request", MetricsRecorder.customAttributesForException(e));
                }
            }
            if (CookieHelper.isExpiredCookie(cookie)) {
                Log.d(TAG, "cookie expired");
                if (i < 1) {
                    return;
                }
                MetricsRecorder.getInstance().recordCounter("FocusFilter_getTemplateFromCloud_retry_expired_cookie");
                getFocusFilterTemplatesFromS3Request(str, templateType, str2, getFocusFilterTemplatesFromS3ResponseHandler, i - 1);
                return;
            }
            Request.Builder addHeader = new Request.Builder().url(buildRequestUrl).addHeader("Content-Type", HttpRequestConstants.JSON_CONTENT_TYPE_VALUE).addHeader("x-amz-access-token", accessToken).addHeader("Cookie", cookie);
            if (!Strings.isNullOrEmpty(str2)) {
                addHeader.addHeader("etag", str2);
            }
            Request build = addHeader.get().build();
            String str5 = TAG;
            Log.i(str5, "RequestUrl - " + buildRequestUrl);
            String str6 = TAG;
            Log.i(str6, "isCookieEmpty? " + Strings.isNullOrEmpty(cookie) + " isTokenEmpty? " + Strings.isNullOrEmpty(accessToken));
            String str7 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("eTag - ");
            sb.append(str2);
            Log.i(str7, sb.toString());
            String str8 = TAG;
            Log.d(str8, "Request header debug info: " + build.headers());
            DependencyProvider.getHttpClient().newCall(build).enqueue(new Callback() { // from class: com.amazon.alexa.accessory.notificationpublisher.servicerequest.GetFocusFilterTemplatesFromS3RequestSender.1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    String str9 = GetFocusFilterTemplatesFromS3RequestSender.TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onFailure.");
                    outline107.append(iOException.getMessage());
                    Log.e(str9, outline107.toString(), iOException);
                    if (i >= 1) {
                        MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_retry_exception");
                        GetFocusFilterTemplatesFromS3RequestSender.getFocusFilterTemplatesFromS3Request(str, templateType, str2, getFocusFilterTemplatesFromS3ResponseHandler, i - 1);
                        return;
                    }
                    getFocusFilterTemplatesFromS3ResponseHandler.handleGetFocusFilterTemplatesFromS3Response(false, null, null, str, templateType);
                }

                /* JADX WARN: Can't wrap try/catch for region: R(16:1|(3:2|3|4)|(11:9|(1:11)(1:40)|12|13|(1:35)|(1:18)(1:34)|(1:23)|25|(1:30)|31|32)|42|(0)(0)|12|13|(1:15)|35|(0)(0)|(2:21|23)|25|(2:27|30)|31|32|(1:(0))) */
                /* JADX WARN: Code restructure failed: missing block: B:32:0x00ca, code lost:
                    if (r1 >= 1) goto L39;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:33:0x00cc, code lost:
                    com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance().recordCounter("FocusFilter_createMobileNotification_retry_exception");
                    com.amazon.alexa.accessory.notificationpublisher.servicerequest.GetFocusFilterTemplatesFromS3RequestSender.getFocusFilterTemplatesFromS3Request(r2, r3, r2, r5, r1 - 1);
                 */
                /* JADX WARN: Removed duplicated region for block: B:12:0x0088  */
                /* JADX WARN: Removed duplicated region for block: B:13:0x008a A[Catch: all -> 0x00c5, Exception -> 0x00c7, TRY_LEAVE, TryCatch #0 {all -> 0x00c5, blocks: (B:3:0x0061, B:6:0x0078, B:14:0x008e, B:16:0x0094, B:24:0x00ab, B:26:0x00af, B:21:0x00a2, B:31:0x00c8, B:33:0x00cc, B:13:0x008a), top: B:45:0x0061 }] */
                /* JADX WARN: Removed duplicated region for block: B:20:0x00a1  */
                /* JADX WARN: Removed duplicated region for block: B:21:0x00a2 A[Catch: all -> 0x00c5, Exception -> 0x00c8, TryCatch #0 {all -> 0x00c5, blocks: (B:3:0x0061, B:6:0x0078, B:14:0x008e, B:16:0x0094, B:24:0x00ab, B:26:0x00af, B:21:0x00a2, B:31:0x00c8, B:33:0x00cc, B:13:0x008a), top: B:45:0x0061 }] */
                /* JADX WARN: Removed duplicated region for block: B:36:0x00eb  */
                @Override // okhttp3.Callback
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onResponse(okhttp3.Call r10, okhttp3.Response r11) {
                    /*
                        Method dump skipped, instructions count: 264
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.servicerequest.GetFocusFilterTemplatesFromS3RequestSender.AnonymousClass1.onResponse(okhttp3.Call, okhttp3.Response):void");
                }
            });
        } catch (Exception e2) {
            Log.e(TAG, "getFocusFilterTemplatesFromS3Request - error.", e2);
            getFocusFilterTemplatesFromS3ResponseHandler.handleGetFocusFilterTemplatesFromS3Response(false, null, null, str, templateType);
        }
    }

    public static String getLocalStorageEtagKey(String str) {
        return String.format("%s_%s", str, "etag");
    }
}
