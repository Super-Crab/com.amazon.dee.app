package com.amazon.CoralAndroidClient.Connector;

import com.amazon.CoralAndroidClient.Exception.NativeException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
/* loaded from: classes.dex */
public class DefaultHttpURLConnectionFactory implements HttpURLConnectionFactory {
    @Override // com.amazon.CoralAndroidClient.Connector.HttpURLConnectionFactory
    public HttpURLConnection create(URL url) throws NativeException {
        return create(url, null);
    }

    @Override // com.amazon.CoralAndroidClient.Connector.HttpURLConnectionFactory
    public HttpURLConnection create(URL url, Proxy proxy) throws NativeException {
        URLConnection openConnection;
        if (url != null) {
            try {
                if (proxy != null) {
                    openConnection = url.openConnection(proxy);
                } else {
                    openConnection = url.openConnection();
                }
                if (openConnection instanceof HttpURLConnection) {
                    return (HttpURLConnection) openConnection;
                }
                throw new NativeException(String.format("invalid endpoint %s, only HTTP and HTTPS is acceptable", url.toString()));
            } catch (IOException e) {
                throw new NativeException(e);
            }
        }
        throw new NativeException("url is null");
    }
}
