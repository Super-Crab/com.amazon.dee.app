package com.amazon.client.metrics.thirdparty.transport;

import android.content.Context;
import android.util.Log;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/* loaded from: classes11.dex */
public class ProvidedOAuthMetricsTransport extends AbstractHTTPMetricsTransport {
    public static final String OAUTH_TOKEN_HEADER = "x-amz-access-token";
    private final OAuthHelper mOAuthHelper;

    public ProvidedOAuthMetricsTransport(Context context, MetricsConfiguration metricsConfiguration, DeviceUtil deviceUtil, OAuthHelper oAuthHelper, MetricsTransport metricsTransport) {
        super(context, metricsConfiguration, deviceUtil, metricsTransport);
        this.mOAuthHelper = oAuthHelper;
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.AbstractHTTPMetricsTransport
    protected HttpURLConnection openConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.MetricsTransport
    public void shutdown() {
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.AbstractHTTPMetricsTransport
    protected boolean signRequest(HttpURLConnection httpURLConnection) {
        try {
            String accessToken = this.mOAuthHelper.getAccessToken();
            if (accessToken != null && !accessToken.isEmpty()) {
                httpURLConnection.addRequestProperty("x-amz-access-token", accessToken);
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.e(this.TAG, "Exception getting OAuth token", e);
            return false;
        }
    }
}
