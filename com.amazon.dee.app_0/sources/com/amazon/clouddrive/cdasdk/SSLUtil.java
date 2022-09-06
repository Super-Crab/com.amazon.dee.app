package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.util.Logger;
import java.util.Collections;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.internal.tls.OkHostnameVerifier;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class SSLUtil {
    private static final String TAG = "SSLUtil";
    @NonNull
    private final Logger logger;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SSLUtil(@NonNull Logger logger) {
        this.logger = logger;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initSafeSSL(@NonNull OkHttpClient.Builder builder) {
        builder.connectionSpecs(Collections.singletonList(ConnectionSpec.MODERN_TLS)).hostnameVerifier(OkHostnameVerifier.INSTANCE).protocols(Collections.singletonList(Protocol.HTTP_1_1));
    }
}
