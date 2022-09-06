package com.amazon.dee.app.services.coral;

import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralServiceException;
import com.dee.app.http.HttpCoralService;
import okhttp3.Response;
/* loaded from: classes12.dex */
public final class HttpCoralDefaultInterceptor implements HttpCoralService.ResponseInterceptor, HttpCoralService.RequestInterceptor {
    private static final String TAG = Log.tag(HttpCoralDefaultInterceptor.class);

    @Override // com.dee.app.http.HttpCoralService.RequestInterceptor
    public void intercept(HttpCoralService.HttpRequest httpRequest) {
        Object[] objArr = {httpRequest.getMethod(), httpRequest.getUrl()};
    }

    @Override // com.dee.app.http.HttpCoralService.ResponseInterceptor
    public <T> T intercept(HttpCoralService.ResponseInterceptor.Chain<T> chain) throws CoralServiceException {
        Response response = chain.response();
        if (response.networkResponse() == null) {
            Object[] objArr = {response.request().url(), response.headers()};
        } else {
            Object[] objArr2 = {response.request().url(), Long.valueOf(response.receivedResponseAtMillis() - response.sentRequestAtMillis()), response.headers()};
        }
        if (response.isSuccessful()) {
            return chain.proceed(response);
        }
        response.close();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected code ");
        outline107.append(response.code());
        throw new CoralServiceException(outline107.toString(), response.code());
    }
}
