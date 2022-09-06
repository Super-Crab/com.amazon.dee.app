package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.framework.RetryLogic;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class dy {
    public static HttpURLConnection a(URL url, RetryLogic retryLogic, ej ejVar, Context context) throws IOException {
        if (url != null) {
            if (retryLogic != null) {
                URLConnection c = ew.c(url);
                if (c instanceof HttpsURLConnection) {
                    return new dx(url, retryLogic, ejVar, context);
                }
                if (c instanceof HttpURLConnection) {
                    return new dw(url, retryLogic, ejVar, context);
                }
                throw new IllegalArgumentException("Url must be an Https or Http Url");
            }
            throw new IllegalArgumentException("Must Specify Retry Logic");
        }
        throw new IllegalArgumentException("Must Specify a URL");
    }
}
