package com.amazon.client.metrics.thirdparty.configuration;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.http.auth.AuthScheme;
/* loaded from: classes11.dex */
public enum HttpRequestSignerType {
    DCP("Dcp"),
    DCP_OAUTH("DcpOAuth"),
    OAUTH(AuthScheme.OAuth);
    
    private final String mName;

    HttpRequestSignerType(String str) {
        this.mName = str;
    }

    public static HttpRequestSignerType fromString(String str) throws MetricsConfigurationException {
        HttpRequestSignerType[] values;
        for (HttpRequestSignerType httpRequestSignerType : values()) {
            if (httpRequestSignerType.getName().equalsIgnoreCase(str)) {
                return httpRequestSignerType;
            }
        }
        throw new MetricsConfigurationException(GeneratedOutlineSupport1.outline72(str, " is not a valid RequestSignerType"));
    }

    public String getName() {
        return this.mName;
    }
}
