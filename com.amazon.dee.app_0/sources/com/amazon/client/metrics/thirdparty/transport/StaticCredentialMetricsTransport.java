package com.amazon.client.metrics.thirdparty.transport;

import android.content.Context;
import android.util.Log;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/* loaded from: classes11.dex */
public class StaticCredentialMetricsTransport extends AbstractHTTPMetricsTransport {
    public static final String STATIC_CREDENTIAL_TOKEN_HEADER = "x-credential-token";

    public StaticCredentialMetricsTransport(Context context, MetricsConfiguration metricsConfiguration, DeviceUtil deviceUtil, MetricsTransport metricsTransport) {
        super(context, metricsConfiguration, deviceUtil, metricsTransport);
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.AbstractHTTPMetricsTransport
    protected HttpURLConnection openConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.AbstractHTTPMetricsTransport
    protected URL resolveEndpoint() {
        try {
            return new URL(this.mMetricsConfiguration.getHttpConfiguration().getStaticCredentialUrlEndpoint() + "/metricsBatch");
        } catch (MalformedURLException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Malformed URL supplied: ");
            outline107.append(this.mMetricsConfiguration.getHttpConfiguration().getStaticCredentialUrlEndpoint());
            throw new IllegalArgumentException(outline107.toString(), e);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.MetricsTransport
    public void shutdown() {
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.AbstractHTTPMetricsTransport
    protected boolean signRequest(HttpURLConnection httpURLConnection) {
        String fetchStaticCredential = this.mDeviceUtil.fetchStaticCredential();
        if (fetchStaticCredential != null && !fetchStaticCredential.isEmpty()) {
            httpURLConnection.addRequestProperty(STATIC_CREDENTIAL_TOKEN_HEADER, fetchStaticCredential);
            return true;
        }
        Log.e(this.TAG, "Static token cannot be null or empty");
        return false;
    }
}
