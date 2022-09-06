package com.amazon.whisperjoin.credentiallocker;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/* loaded from: classes13.dex */
public interface HttpUrlConnectionFactory {
    HttpURLConnection newConnection(URL url) throws IOException;
}
