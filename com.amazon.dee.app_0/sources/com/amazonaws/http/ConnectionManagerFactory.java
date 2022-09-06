package com.amazonaws.http;

import com.amazonaws.ClientConfiguration;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;
/* loaded from: classes13.dex */
class ConnectionManagerFactory {
    private static final int DEFAULT_HTTPS_PORT = 443;
    private static final int DEFAULT_HTTP_PORT = 80;

    ConnectionManagerFactory() {
    }

    public static ThreadSafeClientConnManager createThreadSafeClientConnManager(ClientConfiguration clientConfiguration, HttpParams httpParams) {
        ConnManagerParams.setMaxConnectionsPerRoute(httpParams, new ConnPerRouteBean(clientConfiguration.getMaxConnections()));
        ConnManagerParams.setMaxTotalConnections(httpParams, clientConfiguration.getMaxConnections());
        SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
        socketFactory.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", socketFactory, (int) DEFAULT_HTTPS_PORT));
        ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager(httpParams, schemeRegistry);
        if (clientConfiguration.useReaper()) {
            IdleConnectionReaper.registerConnectionManager(threadSafeClientConnManager);
        }
        return threadSafeClientConnManager;
    }
}
