package com.amazon.clouddrive.cdasdk.aps.interceptor;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import com.amazon.clouddrive.cdasdk.aps.common.DevicePlatform;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes11.dex */
public class APSInterceptor implements Interceptor {
    public static final String APP_VERSION_HEADER = "x-amzn-clouddrive-app-version";
    public static final String DEVICE_TYPE_HEADER = "x-amzn-clouddrive-device-type";
    @NonNull
    private final ClientConfig clientConfig;

    public APSInterceptor(@NonNull ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    @Override // okhttp3.Interceptor
    @NonNull
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        DevicePlatform apsDevicePlatform = this.clientConfig.getApsDevicePlatform();
        String appVersionName = this.clientConfig.getAppVersionName();
        Request.Builder newBuilder = chain.request().newBuilder();
        if (apsDevicePlatform != null) {
            newBuilder = newBuilder.header(DEVICE_TYPE_HEADER, apsDevicePlatform.name());
        }
        if (appVersionName != null && !appVersionName.isEmpty()) {
            newBuilder = newBuilder.header(APP_VERSION_HEADER, appVersionName);
        }
        return chain.proceed(newBuilder.build());
    }
}
