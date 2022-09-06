package org.apache.thrift.orig.transport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.util.EntityUtils;
/* loaded from: classes4.dex */
public class THttpClient extends TTransport {
    private final HttpClient client;
    private final HttpHost host;
    private URL url_;
    private final ByteArrayOutputStream requestBuffer_ = new ByteArrayOutputStream();
    private InputStream inputStream_ = null;
    private int connectTimeout_ = 0;
    private int readTimeout_ = 0;
    private Map<String, String> customHeaders_ = null;

    public THttpClient(String str) throws TTransportException {
        this.url_ = null;
        try {
            this.url_ = new URL(str);
            this.client = null;
            this.host = null;
        } catch (IOException e) {
            throw new TTransportException(e);
        }
    }

    private void flushUsingHttpClient() throws TTransportException {
        HttpPost httpPost;
        int read;
        if (this.client != null) {
            byte[] byteArray = this.requestBuffer_.toByteArray();
            this.requestBuffer_.reset();
            InputStream inputStream = null;
            try {
                try {
                    httpPost = new HttpPost(this.url_.getFile());
                } catch (IOException e) {
                    e = e;
                    httpPost = null;
                }
                try {
                    httpPost.setHeader("Content-Type", "application/x-thrift");
                    httpPost.setHeader("Accept", "application/x-thrift");
                    httpPost.setHeader("User-Agent", "Java/THttpClient/HC");
                    if (this.customHeaders_ != null) {
                        for (Map.Entry<String, String> entry : this.customHeaders_.entrySet()) {
                            httpPost.setHeader(entry.getKey(), entry.getValue());
                        }
                    }
                    httpPost.setEntity(new ByteArrayEntity(byteArray));
                    HttpResponse execute = this.client.execute(this.host, httpPost);
                    int statusCode = execute.getStatusLine().getStatusCode();
                    InputStream content = execute.getEntity().getContent();
                    if (statusCode == 200) {
                        byte[] bArr = new byte[1024];
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        do {
                            read = content.read(bArr);
                            if (read > 0) {
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                        } while (-1 != read);
                        try {
                            EntityUtils.consume(execute.getEntity());
                        } catch (IOException unused) {
                        }
                        this.inputStream_ = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                        try {
                            content.close();
                            return;
                        } catch (IOException e2) {
                            throw new TTransportException(e2);
                        }
                    }
                    throw new TTransportException("HTTP Response code: " + statusCode);
                } catch (IOException e3) {
                    e = e3;
                    if (httpPost != null) {
                        httpPost.abort();
                    }
                    throw new TTransportException(e);
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        throw new TTransportException(e4);
                    }
                }
                throw th;
            }
        }
        throw new TTransportException("Null HttpClient, aborting.");
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void close() {
        InputStream inputStream = this.inputStream_;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
            this.inputStream_ = null;
        }
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void flush() throws TTransportException {
        if (this.client != null) {
            flushUsingHttpClient();
            return;
        }
        byte[] byteArray = this.requestBuffer_.toByteArray();
        this.requestBuffer_.reset();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) this.url_.openConnection();
            if (this.connectTimeout_ > 0) {
                httpURLConnection.setConnectTimeout(this.connectTimeout_);
            }
            if (this.readTimeout_ > 0) {
                httpURLConnection.setReadTimeout(this.readTimeout_);
            }
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-thrift");
            httpURLConnection.setRequestProperty("Accept", "application/x-thrift");
            httpURLConnection.setRequestProperty("User-Agent", "Java/THttpClient");
            if (this.customHeaders_ != null) {
                for (Map.Entry<String, String> entry : this.customHeaders_.entrySet()) {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();
            httpURLConnection.getOutputStream().write(byteArray);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                this.inputStream_ = httpURLConnection.getInputStream();
                return;
            }
            throw new TTransportException("HTTP Response code: " + responseCode);
        } catch (IOException e) {
            throw new TTransportException(e);
        }
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public boolean isOpen() {
        return true;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void open() {
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public int read(byte[] bArr, int i, int i2) throws TTransportException {
        InputStream inputStream = this.inputStream_;
        if (inputStream != null) {
            try {
                int read = inputStream.read(bArr, i, i2);
                if (read != -1) {
                    return read;
                }
                throw new TTransportException("No more data available.");
            } catch (IOException e) {
                throw new TTransportException(e);
            }
        }
        throw new TTransportException("Response buffer is empty, no request.");
    }

    public void setConnectTimeout(int i) {
        this.connectTimeout_ = i;
        HttpClient httpClient = this.client;
        if (httpClient != null) {
            httpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(this.connectTimeout_));
        }
    }

    public void setCustomHeader(String str, String str2) {
        if (this.customHeaders_ == null) {
            this.customHeaders_ = new HashMap();
        }
        this.customHeaders_.put(str, str2);
    }

    public void setCustomHeaders(Map<String, String> map) {
        this.customHeaders_ = map;
    }

    public void setReadTimeout(int i) {
        this.readTimeout_ = i;
        HttpClient httpClient = this.client;
        if (httpClient != null) {
            httpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(this.readTimeout_));
        }
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void write(byte[] bArr, int i, int i2) {
        this.requestBuffer_.write(bArr, i, i2);
    }

    /* loaded from: classes4.dex */
    public static class Factory extends TTransportFactory {
        private final HttpClient client;
        private final String url;

        public Factory(String str) {
            this.url = str;
            this.client = null;
        }

        @Override // org.apache.thrift.orig.transport.TTransportFactory
        public TTransport getTransport(TTransport tTransport) {
            try {
                if (this.client != null) {
                    return new THttpClient(this.url, this.client);
                }
                return new THttpClient(this.url);
            } catch (TTransportException unused) {
                return null;
            }
        }

        public Factory(String str, HttpClient httpClient) {
            this.url = str;
            this.client = httpClient;
        }
    }

    public THttpClient(String str, HttpClient httpClient) throws TTransportException {
        this.url_ = null;
        try {
            this.url_ = new URL(str);
            this.client = httpClient;
            this.host = new HttpHost(this.url_.getHost(), -1 == this.url_.getPort() ? this.url_.getDefaultPort() : this.url_.getPort(), this.url_.getProtocol());
        } catch (IOException e) {
            throw new TTransportException(e);
        }
    }
}
