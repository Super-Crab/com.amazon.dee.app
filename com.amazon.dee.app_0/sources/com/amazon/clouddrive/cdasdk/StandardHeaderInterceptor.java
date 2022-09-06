package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.util.SystemUtil;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;
/* loaded from: classes11.dex */
public class StandardHeaderInterceptor implements Interceptor {
    @NonNull
    public static final String APP_ID_HEADER = "x-amz-clouddrive-appid";
    @NonNull
    public static final String USER_AGENT_HEADER = "User-Agent";
    @NonNull
    private final String applicationIdBase64;
    @NonNull
    private final ClientConfig clientConfig;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StandardHeaderInterceptor(@NonNull ClientConfig clientConfig, @NonNull SystemUtil systemUtil) {
        this.clientConfig = clientConfig;
        this.applicationIdBase64 = systemUtil.getBase64(clientConfig.getApplicationId(), 3);
    }

    @Override // okhttp3.Interceptor
    @NonNull
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().header("User-Agent", this.clientConfig.getUserAgent()).header(APP_ID_HEADER, this.applicationIdBase64).build());
    }
}
