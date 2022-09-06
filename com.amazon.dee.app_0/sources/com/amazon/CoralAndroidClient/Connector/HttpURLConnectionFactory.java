package com.amazon.CoralAndroidClient.Connector;

import com.amazon.CoralAndroidClient.Exception.NativeException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
/* loaded from: classes.dex */
public interface HttpURLConnectionFactory {
    HttpURLConnection create(URL url) throws NativeException;

    HttpURLConnection create(URL url, Proxy proxy) throws NativeException;
}
