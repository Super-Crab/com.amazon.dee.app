package com.amazonaws.util;

import com.amazonaws.internal.SdkFilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.client.HttpClient;
/* loaded from: classes.dex */
class HttpClientWrappingInputStream extends SdkFilterInputStream {
    private final HttpClient client;

    public HttpClientWrappingInputStream(HttpClient httpClient, InputStream inputStream) {
        super(inputStream);
        this.client = httpClient;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            this.client.getConnectionManager().shutdown();
        }
    }
}
