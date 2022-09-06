package com.amazonaws.http.impl.client;

import com.amazonaws.http.conn.ClientConnectionManagerFactory;
import com.amazonaws.http.protocol.SdkHttpRequestExecutor;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpRequestExecutor;
/* loaded from: classes13.dex */
public class SdkHttpClient extends DefaultHttpClient {
    public SdkHttpClient(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        super(ClientConnectionManagerFactory.wrap(clientConnectionManager), httpParams);
    }

    protected HttpRequestExecutor createRequestExecutor() {
        return new SdkHttpRequestExecutor();
    }
}
