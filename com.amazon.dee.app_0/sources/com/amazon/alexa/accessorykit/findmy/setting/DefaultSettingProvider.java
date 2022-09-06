package com.amazon.alexa.accessorykit.findmy.setting;

import android.webkit.CookieManager;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.http.HttpCall;
import com.amazon.alexa.accessory.internal.http.HttpMethod;
import com.amazon.alexa.accessory.internal.http.HttpRequest;
import com.amazon.alexa.accessory.internal.http.HttpStatusUnsuccessfulException;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.MetricsUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessorykit.endpoint.AlexaEndpointProvider;
import com.amazon.alexa.accessorykit.metrics.MetricsConstants;
import com.amazon.alexa.accessorykit.utils.AccessTokenUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.IOException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class DefaultSettingProvider implements SettingProvider {
    private static final String ACCEPT_ENCODING_KEY = "Accept-Encoding";
    private static final String ACCESS_TOKEN_KEY = "x-amz-access-token";
    private static final String CONTENT_TYPE = "application/json";
    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String COOKIE_KEY = "Cookie";
    private static final String GZIP_ENCODING = "gzip";
    private static final String TAG = "DefaultSettingProvider";
    private final AlexaEndpointProvider alexaEndpointProvider;
    private final UserSupplier userSupplier;

    public DefaultSettingProvider(UserSupplier userSupplier, AlexaEndpointProvider alexaEndpointProvider) {
        Preconditions.notNull(userSupplier, "userSupplier");
        Preconditions.notNull(alexaEndpointProvider, "alexaEndpointProvider");
        this.userSupplier = userSupplier;
        this.alexaEndpointProvider = alexaEndpointProvider;
    }

    private String getCookie(String str) {
        String cookie = CookieManager.getInstance().getCookie(str);
        return cookie != null ? cookie : "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SettingResponse lambda$null$1(String str, HttpCall.HttpResult httpResult) throws Throwable {
        Logger.d("%s: response for querySettings %s", TAG, httpResult);
        recordQuerySettingStatusCode(httpResult.statuseCode, str);
        int i = httpResult.statuseCode;
        if (i == 200) {
            byte[] bArr = httpResult.response;
            boolean z = (bArr == null || bArr.length == 0) ? false : true;
            recordQuerySettingMetric(MetricsConstants.FindMy.QUERY_SETTING_HAS_DATA, z, str);
            if (z) {
                try {
                    SettingResponse mo1239create = SettingResponse.FACTORY.mo1239create(new JSONObject(new String(httpResult.response)));
                    recordQuerySettingMetric(MetricsConstants.FindMy.QUERY_SETTING_PARSE_SUCCESS, true, str);
                    recordQuerySettingMetric(MetricsConstants.FindMy.QUERY_SETTING_SUCCESS, true, str);
                    return mo1239create;
                } catch (Exception e) {
                    recordQuerySettingMetric(MetricsConstants.FindMy.QUERY_SETTING_PARSE_SUCCESS, false, str);
                    throw e;
                }
            }
            throw new IOException("DefaultSettingProvider: HttpResult response is either null or empty");
        }
        throw new HttpStatusUnsuccessfulException(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void recordQuerySettingMetric(String str, boolean z, String str2) {
        GeneratedOutlineSupport1.outline171(str, str2, z, null);
    }

    private static void recordQuerySettingStatusCode(int i, String str) {
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.FindMy.QUERY_SETTING_STATUS_CODE, GeneratedOutlineSupport1.outline74(str, ":", i), true, null);
    }

    public /* synthetic */ void lambda$null$2$DefaultSettingProvider(String str, Throwable th) throws Throwable {
        recordMetricForThrowable(th, str);
        recordQuerySettingMetric(MetricsConstants.FindMy.QUERY_SETTING_SUCCESS, false, str);
    }

    public /* synthetic */ SingleSource lambda$querySetting$3$DefaultSettingProvider(SettingRequest settingRequest, final String str, String str2) throws Throwable {
        String locationEnablementApiEndpoint = this.alexaEndpointProvider.getLocationEnablementApiEndpoint(settingRequest.getAccountId());
        String cookie = getCookie(locationEnablementApiEndpoint);
        recordQuerySettingMetric(MetricsConstants.FindMy.QUERY_SETTING_PREPARED_REQUEST, true, str);
        HttpRequest build = HttpRequest.createBuilder().method(HttpMethod.GET).url(locationEnablementApiEndpoint).header("Content-Type", "application/json").header("Accept-Encoding", GZIP_ENCODING).header("x-amz-access-token", str2).header("Cookie", cookie).build();
        Logger.d("%s: Sending USS settings request with url: %s", TAG, locationEnablementApiEndpoint);
        return build.newCall().executeSingle().map(new Function() { // from class: com.amazon.alexa.accessorykit.findmy.setting.-$$Lambda$DefaultSettingProvider$IprAYLsAdqt9ZHjldHt-Qt_c0WM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultSettingProvider.lambda$null$1(str, (HttpCall.HttpResult) obj);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessorykit.findmy.setting.-$$Lambda$DefaultSettingProvider$37y6gwroATC9ep9IpTFeOC-mqjM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultSettingProvider.this.lambda$null$2$DefaultSettingProvider(str, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.accessorykit.findmy.setting.SettingProvider
    public Single<SettingResponse> querySetting(final SettingRequest settingRequest) {
        Preconditions.notNull(settingRequest, "settingRequest");
        final String deviceType = settingRequest.getDeviceType();
        return AccessTokenUtils.getAccessToken(this.userSupplier).doOnError(new Consumer() { // from class: com.amazon.alexa.accessorykit.findmy.setting.-$$Lambda$DefaultSettingProvider$DHwBYG3ZFOb-hIw5mh1RJ71Qdng
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                DefaultSettingProvider.recordQuerySettingMetric(MetricsConstants.FindMy.QUERY_SETTING_PREPARED_REQUEST, false, deviceType);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessorykit.findmy.setting.-$$Lambda$DefaultSettingProvider$blLCwZI6CYLM9dRfV6u4bLsgsPM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultSettingProvider.this.lambda$querySetting$3$DefaultSettingProvider(settingRequest, deviceType, (String) obj);
            }
        }).subscribeOn(Schedulers.io());
    }

    @VisibleForTesting
    void recordMetricForThrowable(Throwable th, String str) {
        GeneratedOutlineSupport1.outline171(MetricsUtils.createMetricNameFromThrowable(MetricsConstants.FindMy.QUERY_SETTING_EXCEPTION_PREFIX, th), str, true, null);
    }
}
