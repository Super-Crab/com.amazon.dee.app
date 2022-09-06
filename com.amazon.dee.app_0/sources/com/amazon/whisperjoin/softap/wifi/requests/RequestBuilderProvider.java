package com.amazon.whisperjoin.softap.wifi.requests;

import android.net.Uri;
/* loaded from: classes13.dex */
public interface RequestBuilderProvider {

    /* loaded from: classes13.dex */
    public static class DefaultRequestBuilderProvider implements RequestBuilderProvider {
        @Override // com.amazon.whisperjoin.softap.wifi.requests.RequestBuilderProvider
        public RequestBuilder getBuilder() {
            return new RequestBuilderImpl(new Uri.Builder());
        }
    }

    RequestBuilder getBuilder();
}
