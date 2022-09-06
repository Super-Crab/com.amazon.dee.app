package com.bugsnag.android;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bugsnag.android.JsonStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DefaultDelivery implements Delivery {
    private static final int HTTP_REQUEST_FAILED = 0;
    private final Connectivity connectivity;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultDelivery(Connectivity connectivity) {
        this.connectivity = connectivity;
    }

    @Override // com.bugsnag.android.Delivery
    public void deliver(@NonNull SessionTrackingPayload sessionTrackingPayload, @NonNull Configuration configuration) throws DeliveryFailureException {
        int deliver = deliver(configuration.getSessionEndpoint(), sessionTrackingPayload, configuration.getSessionApiHeaders());
        if (deliver != 202) {
            Logger.warn(GeneratedOutlineSupport1.outline49("Session API request failed with status ", deliver), null);
        } else {
            Logger.info("Completed session tracking request");
        }
    }

    @Override // com.bugsnag.android.Delivery
    public void deliver(@NonNull Report report, @NonNull Configuration configuration) throws DeliveryFailureException {
        int deliver = deliver(configuration.getEndpoint(), report, configuration.getErrorApiHeaders());
        if (deliver / 100 != 2) {
            Logger.warn(GeneratedOutlineSupport1.outline49("Error API request failed with status ", deliver), null);
        } else {
            Logger.info("Completed error API request");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int deliver(String str, JsonStream.Streamable streamable, Map<String, String> map) throws DeliveryFailureException {
        HttpURLConnection httpURLConnection;
        Connectivity connectivity = this.connectivity;
        HttpURLConnection httpURLConnection2 = null;
        JsonStream jsonStream = null;
        if (connectivity != null && !connectivity.hasNetworkConnection()) {
            throw new DeliveryFailureException("No network connection available", null);
        }
        try {
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            } catch (Throwable th) {
                th = th;
                httpURLConnection = null;
            }
        } catch (IOException e) {
            e = e;
        } catch (Exception e2) {
            e = e2;
        }
        try {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setChunkedStreamingMode(0);
            httpURLConnection.addRequestProperty("Content-Type", "application/json");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
            }
            try {
                JsonStream jsonStream2 = new JsonStream(new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), Charset.forName("UTF-8"))));
                try {
                    streamable.toStream(jsonStream2);
                    IOUtils.closeQuietly(jsonStream2);
                    int responseCode = httpURLConnection.getResponseCode();
                    IOUtils.close(httpURLConnection);
                    return responseCode;
                } catch (Throwable th2) {
                    th = th2;
                    jsonStream = jsonStream2;
                    IOUtils.closeQuietly(jsonStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e3) {
            e = e3;
            throw new DeliveryFailureException("IOException encountered in request", e);
        } catch (Exception e4) {
            e = e4;
            httpURLConnection2 = httpURLConnection;
            Logger.warn("Unexpected error delivering payload", e);
            IOUtils.close(httpURLConnection2);
            return 0;
        } catch (Throwable th4) {
            th = th4;
            IOUtils.close(httpURLConnection);
            throw th;
        }
    }
}
