package com.amazon.dee.app.services.network.interceptor;

import androidx.annotation.NonNull;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes12.dex */
public class NetworkLatencyInterceptor implements Interceptor {
    private static final String TAG = Log.tag(NetworkLatencyInterceptor.class);

    @Override // okhttp3.Interceptor
    @NonNull
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Log.i(TAG, "Sending request (method: %s, url: %s)", request.method(), request.url().toString());
        Response proceed = chain.proceed(request);
        if (proceed.networkResponse() == null) {
            Log.i(TAG, "Received response for %s from the cache%n%s", proceed.request().url(), proceed.headers());
        } else {
            long sentRequestAtMillis = proceed.sentRequestAtMillis();
            Log.i(TAG, "Received response for %s in %d ms%n%s", proceed.request().url(), Long.valueOf(proceed.receivedResponseAtMillis() - sentRequestAtMillis), proceed.headers());
        }
        if (proceed.isSuccessful()) {
            return proceed;
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected code ");
        outline107.append(proceed.code());
        Log.i(str, outline107.toString());
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unexpected code ");
        outline1072.append(proceed.code());
        throw new IOException(outline1072.toString());
    }
}
