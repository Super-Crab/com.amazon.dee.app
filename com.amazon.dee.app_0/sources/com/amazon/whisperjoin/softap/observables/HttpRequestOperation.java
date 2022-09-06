package com.amazon.whisperjoin.softap.observables;

import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilder;
import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilderProvider;
import rx.Single;
/* loaded from: classes13.dex */
public abstract class HttpRequestOperation<T> {
    protected RequestBuilderProvider requestBuilderProvider;

    public HttpRequestOperation(RequestBuilderProvider requestBuilderProvider) {
        this.requestBuilderProvider = requestBuilderProvider;
    }

    public abstract Single<T> observe() throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    public RequestBuilder requestBuilder() {
        return this.requestBuilderProvider.getBuilder();
    }
}
