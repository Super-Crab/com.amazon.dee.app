package com.amazon.alexa.network.api;

import okhttp3.OkHttpClient;
/* loaded from: classes9.dex */
public interface HttpClient {
    OkHttpClient httpClient();

    OkHttpClient okHttpClientWithBearerAuth();
}
