package com.amazon.alexa.accessory.registration.deviceaccount;

import android.webkit.CookieManager;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.internal.EndpointProvider;
import com.amazon.alexa.accessory.internal.http.HttpCall;
import com.amazon.alexa.accessory.internal.http.HttpMethod;
import com.amazon.alexa.accessory.internal.http.HttpRequest;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class DefaultDeviceAccountExecutor implements DeviceAccountExecutor {
    private static final String ACCEPT_ENCODING_KEY = "Accept-Encoding";
    private static final String ACCESS_TOKEN_KEY = "x-amz-access-token";
    private static final String CONTENT_TYPE = "application/json";
    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String COOKIE_KEY = "Cookie";
    private static final String GZIP_ENCODING = "gzip";
    private static final String TAG = "DefaultDeviceAccountExecutor";
    private final EndpointProvider endpointProvider;

    public DefaultDeviceAccountExecutor(EndpointProvider endpointProvider) {
        Preconditions.notNull(endpointProvider, "endpointProvider");
        this.endpointProvider = endpointProvider;
    }

    private String getCookie(String str) {
        String cookie = CookieManager.getInstance().getCookie(str);
        return cookie != null ? cookie : "";
    }

    private String getUrlForRequest(DeviceAccountRequest deviceAccountRequest) {
        if (deviceAccountRequest.getClusterDeviceType() != null) {
            return this.endpointProvider.getDeviceAccountApiEndpoint(deviceAccountRequest.getClusterDeviceType(), deviceAccountRequest.getClusterDsn());
        }
        return this.endpointProvider.getDeviceAccountApiEndpoint(deviceAccountRequest.getDeviceType(), deviceAccountRequest.getDeviceSerialNumber());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DeviceAccount lambda$null$0(DeviceAccountRequest deviceAccountRequest, HttpCall.HttpResult httpResult) throws Throwable {
        Logger.d("%s: Received response for getDeviceAccount " + httpResult, TAG);
        recordDeviceAccountStatusCode(httpResult.statuseCode, deviceAccountRequest.getDeviceType());
        if (httpResult.statuseCode == 200) {
            byte[] bArr = httpResult.response;
            boolean z = (bArr == null || bArr.length == 0) ? false : true;
            recordDeviceAccountMetric(MetricsConstants.DeviceAccount.DEVICE_ACCOUNT_HAS_DATA, z, deviceAccountRequest.getDeviceType());
            if (z) {
                try {
                    DeviceAccountResponse mo1239create = DeviceAccountResponse.FACTORY.mo1239create(new JSONObject(new String(httpResult.response)));
                    recordDeviceAccountMetric(MetricsConstants.DeviceAccount.DEVICE_ACCOUNT_PARSE_SUCCESS, true, deviceAccountRequest.getDeviceType());
                    DeviceAccount deviceAccount = new DeviceAccount(deviceAccountRequest, mo1239create);
                    recordDeviceAccountMetric(MetricsConstants.DeviceAccount.DEVICE_ACCOUNT_SUCCESS, true, deviceAccountRequest.getDeviceType());
                    return deviceAccount;
                } catch (JSONException e) {
                    recordDeviceAccountMetric(MetricsConstants.DeviceAccount.DEVICE_ACCOUNT_PARSE_SUCCESS, false, deviceAccountRequest.getDeviceType());
                    throw new IOException("DefaultDeviceAccountExecutor: Error parsing GetDeviceAccount response", e);
                }
            }
            throw new IOException("DefaultDeviceAccountExecutor: HttpResult response is either null or empty");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DefaultDeviceAccountExecutor: GetDeviceAccount API call failed, status code=");
        outline107.append(httpResult.statuseCode);
        throw new IOException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void recordDeviceAccountMetric(String str, boolean z, String str2) {
        GeneratedOutlineSupport1.outline171(str, str2, z, null);
    }

    private static void recordDeviceAccountStatusCode(int i, String str) {
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.DeviceAccount.DEVICE_ACCOUNT_STATUS_CODE, GeneratedOutlineSupport1.outline74(str, ":", i), true, null);
    }

    @Override // com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountExecutor
    public Single<DeviceAccount> fetchDeviceAccount(final DeviceAccountRequest deviceAccountRequest, final User user) {
        Preconditions.notNull(deviceAccountRequest, "deviceAccountRequest");
        Preconditions.notNull(user, "user");
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$DefaultDeviceAccountExecutor$Tw6SG7k453IyYDJ3YMSExu_ACLU
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return DefaultDeviceAccountExecutor.this.lambda$fetchDeviceAccount$1$DefaultDeviceAccountExecutor(user, deviceAccountRequest);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$DefaultDeviceAccountExecutor$n9_b5UZb24S1cDquStfYDbj1kPk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                DefaultDeviceAccountExecutor.recordDeviceAccountMetric(MetricsConstants.DeviceAccount.DEVICE_ACCOUNT_SUCCESS, false, DeviceAccountRequest.this.getDeviceType());
            }
        }).subscribeOn(Schedulers.io());
    }

    public /* synthetic */ SingleSource lambda$fetchDeviceAccount$1$DefaultDeviceAccountExecutor(User user, final DeviceAccountRequest deviceAccountRequest) throws Throwable {
        if (user.getAccessToken() == null) {
            recordDeviceAccountMetric(MetricsConstants.DeviceAccount.DEVICE_ACCOUNT_PREPARED_REQUEST, false, deviceAccountRequest.getDeviceType());
            return Single.error(new IOException("No access token available for user " + user));
        }
        recordDeviceAccountMetric(MetricsConstants.DeviceAccount.DEVICE_ACCOUNT_PREPARED_REQUEST, true, deviceAccountRequest.getDeviceType());
        String urlForRequest = getUrlForRequest(deviceAccountRequest);
        Logger.d("%s: fetchDeviceAccount url: %s", TAG, urlForRequest);
        HttpRequest build = HttpRequest.createBuilder().method(HttpMethod.GET).url(urlForRequest).header("Content-Type", "application/json").header("Accept-Encoding", GZIP_ENCODING).header("x-amz-access-token", user.getAccessToken()).header("Cookie", getCookie(urlForRequest)).build();
        Logger.d("%s: Sending deviceAccount request: %s with url: %s", TAG, deviceAccountRequest.toJsonObject().toString(), urlForRequest);
        return build.newCall().executeSingle().map(new Function() { // from class: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$DefaultDeviceAccountExecutor$sqg3qhU9nA6D4POcQxWKaDmaoN8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultDeviceAccountExecutor.lambda$null$0(DeviceAccountRequest.this, (HttpCall.HttpResult) obj);
            }
        });
    }
}
