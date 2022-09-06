package com.amazon.whisperjoin.softap.observables;

import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilder;
import com.amazon.whisperjoin.softap.wifi.requests.RequestBuilderProvider;
/* loaded from: classes13.dex */
public abstract class SoftApRequestOperation<T> extends HttpRequestOperation<T> {
    private static final String SERVER_ADDRESS = "192.168.1.1";
    private static final String SERVER_SCHEME = "http";
    private static final String V2_API_VERSION_PREFIX = "v2";

    public SoftApRequestOperation(RequestBuilderProvider requestBuilderProvider) {
        super(requestBuilderProvider);
    }

    private String createRequestPath() {
        return String.format("/%s%s", getApiVersion(), getPath());
    }

    protected String getApiVersion() {
        return V2_API_VERSION_PREFIX;
    }

    protected abstract String getPath();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.softap.observables.HttpRequestOperation
    public RequestBuilder requestBuilder() {
        return super.requestBuilder().setScheme("http").setAuthority(SERVER_ADDRESS).setPath(createRequestPath());
    }
}
