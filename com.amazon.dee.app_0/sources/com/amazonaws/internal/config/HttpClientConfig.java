package com.amazonaws.internal.config;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class HttpClientConfig {
    private final String serviceName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpClientConfig(String str) {
        this.serviceName = str;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("serviceName: ");
        outline107.append(this.serviceName);
        return outline107.toString();
    }
}
