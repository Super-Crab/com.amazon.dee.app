package com.amazon.alexa.comms.mediaInsights.service.factories;

import java.io.IOException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class HttpURLConnectionFactory {
    public HttpsURLConnection create(@NonNull String str) throws IOException {
        if (str != null) {
            return (HttpsURLConnection) new URL(str).openConnection();
        }
        throw new IllegalArgumentException("httpsEndpoint is null");
    }
}
