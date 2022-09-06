package com.amazon.alexa.fitness.service.hds;

import dagger.internal.Factory;
/* loaded from: classes8.dex */
public final class HttpClientImpl_Factory implements Factory<HttpClientImpl> {
    private static final HttpClientImpl_Factory INSTANCE = new HttpClientImpl_Factory();

    public static HttpClientImpl_Factory create() {
        return INSTANCE;
    }

    public static HttpClientImpl newHttpClientImpl() {
        return new HttpClientImpl();
    }

    public static HttpClientImpl provideInstance() {
        return new HttpClientImpl();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HttpClientImpl mo10268get() {
        return provideInstance();
    }
}
