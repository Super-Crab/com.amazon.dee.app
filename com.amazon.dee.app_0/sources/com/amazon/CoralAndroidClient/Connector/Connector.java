package com.amazon.CoralAndroidClient.Connector;

import com.amazon.CoralAndroidClient.ClientBase.ClientRequest;
import com.amazon.CoralAndroidClient.ClientBase.ClientResponse;
import com.amazon.CoralAndroidClient.ClientBase.Unmarshaller;
import com.amazon.CoralAndroidClient.Exception.NativeException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes.dex */
public class Connector {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String CONTENT_CHARSET = "UTF-8";
    private static final String CONTENT_ENCODING = "Content-Encoding";
    private static final String CONTENT_ENCODING_VALUE = "amz-1.0";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json; charset=UTF-8";
    public static final int DEFAULT_MAX_REDIRECT = 10;
    public static final int DEFAULT_MAX_RETRY = 5;
    private static final String LOCATION = "Location";
    private static final Logger LOGGER = Logger.getLogger("Connector");
    private static final int RECEIVE_BUFFER_SIZE = 1024;
    private static final String X_AMZ_DATE = "X-Amz-Date";
    private static final String X_AMZ_TARGET = "X-Amz-Target";
    private HttpURLConnectionFactory mHttpURLConnectionFactory;
    private int mReadTimeout = 0;
    private int mConnectTimeout = 0;
    private int mMaxRetry = 5;
    private int mMaxRedirect = 10;
    private String mEndpoint = "";
    private URL mEndpointURL = null;
    private Proxy mProxy = null;
    private Set<TransmissionFilter> mTransmissionFilters = null;
    private ClientRequestEncoder mClientRequestEncoder = null;
    private SimpleDateFormat mAmzDateFormat = new SimpleDateFormat("EEE, d MMM yyyy k:m:s ZZZZ", Locale.getDefault());

    public static Connector create() throws NativeException {
        Connector connector = new Connector();
        connector.setMaxRedirect(10);
        connector.setMaxRetry(5);
        connector.setClientRequestEncoder(new ClientRequestJsonEncoder("UTF-8"));
        connector.setHttpURLConnectionFactory(new DefaultHttpURLConnectionFactory());
        return connector;
    }

    protected void afterSend(ClientRequest clientRequest, ClientResponse clientResponse) {
        Set<TransmissionFilter> set = this.mTransmissionFilters;
        if (set == null) {
            return;
        }
        for (TransmissionFilter transmissionFilter : set) {
            transmissionFilter.afterSend(clientRequest, clientResponse);
        }
    }

    protected void beforeSend(ClientRequest clientRequest) {
        Set<TransmissionFilter> set = this.mTransmissionFilters;
        if (set == null) {
            return;
        }
        for (TransmissionFilter transmissionFilter : set) {
            transmissionFilter.beforeSend(clientRequest);
        }
    }

    protected URL dealWithRedirect(HttpURLConnection httpURLConnection, int i) throws NativeException {
        URL url;
        String headerField = httpURLConnection.getHeaderField("Location");
        if (headerField != null) {
            try {
                if (headerField.startsWith("/")) {
                    url = new URL(this.mEndpointURL, headerField);
                } else {
                    url = new URL(headerField);
                }
                if (i == 301) {
                    this.mEndpointURL = url;
                    this.mEndpoint = url.toString();
                }
                return url;
            } catch (MalformedURLException e) {
                throw new NativeException(e);
            }
        }
        throw new NativeException(String.format("redirect without location, status code %d", Integer.valueOf(i)));
    }

    protected byte[] doReceive(HttpURLConnection httpURLConnection, int i) throws IOException {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            if (i == 200) {
                bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            } else {
                bufferedInputStream = new BufferedInputStream(httpURLConnection.getErrorStream());
            }
            bufferedInputStream2 = bufferedInputStream;
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            while (true) {
                int read = bufferedInputStream2.read(bArr);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                bufferedInputStream2.close();
            } catch (IOException e) {
                if (i == 200) {
                    LOGGER.log(Level.WARNING, "close getInputStream() with exception", (Throwable) e);
                } else {
                    LOGGER.log(Level.WARNING, "close getErrorStream() with exception", (Throwable) e);
                }
            }
            return byteArray;
        } catch (Throwable th) {
            if (bufferedInputStream2 != null) {
                try {
                    bufferedInputStream2.close();
                } catch (IOException e2) {
                    if (i == 200) {
                        LOGGER.log(Level.WARNING, "close getInputStream() with exception", (Throwable) e2);
                    } else {
                        LOGGER.log(Level.WARNING, "close getErrorStream() with exception", (Throwable) e2);
                    }
                }
            }
            throw th;
        }
    }

    protected void doSend(HttpURLConnection httpURLConnection, ClientRequest clientRequest, byte[] bArr) throws IOException {
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setFixedLengthStreamingMode(bArr.length);
        httpURLConnection.addRequestProperty("Content-Encoding", CONTENT_ENCODING_VALUE);
        httpURLConnection.addRequestProperty("Content-Type", CONTENT_TYPE_VALUE);
        httpURLConnection.addRequestProperty(X_AMZ_TARGET, clientRequest.getOperationName());
        Map<String, String> headers = clientRequest.getHeaders();
        if (headers == null || !headers.containsKey("X-Amz-Date")) {
            httpURLConnection.addRequestProperty("X-Amz-Date", this.mAmzDateFormat.format(new Date()));
        }
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                try {
                    httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
                } catch (NullPointerException e) {
                    LOGGER.log(Level.WARNING, "null additional header", (Throwable) e);
                }
            }
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(httpURLConnection.getOutputStream());
            try {
                bufferedOutputStream2.write(bArr);
                bufferedOutputStream2.flush();
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e2) {
                    LOGGER.log(Level.WARNING, "close getOutputStream() with exception", (Throwable) e2);
                }
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream = bufferedOutputStream2;
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e3) {
                        LOGGER.log(Level.WARNING, "close getOutputStream() with exception", (Throwable) e3);
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public ClientRequestEncoder getClientRequestEncoder() {
        return this.mClientRequestEncoder;
    }

    public int getConnectTimeout() {
        return this.mConnectTimeout;
    }

    public URL getEndpoint() {
        return this.mEndpointURL;
    }

    protected HttpURLConnection getHttpURLConnection(URL url, Proxy proxy) throws NativeException {
        HttpURLConnectionFactory httpURLConnectionFactory = this.mHttpURLConnectionFactory;
        if (httpURLConnectionFactory != null) {
            HttpURLConnection create = httpURLConnectionFactory.create(url, proxy);
            create.setInstanceFollowRedirects(false);
            create.setReadTimeout(this.mReadTimeout);
            create.setConnectTimeout(this.mConnectTimeout);
            return create;
        }
        throw new NativeException("HttpURLConnectionFactory is null.");
    }

    public HttpURLConnectionFactory getHttpURLConnectionFactory() {
        return this.mHttpURLConnectionFactory;
    }

    public int getMaxRedirect() {
        return this.mMaxRedirect;
    }

    public int getMaxRetry() {
        return this.mMaxRetry;
    }

    public Proxy getProxy() {
        return this.mProxy;
    }

    public int getReadTimeout() {
        return this.mReadTimeout;
    }

    public Set<TransmissionFilter> getTransmissionFilterSet() {
        return this.mTransmissionFilters;
    }

    public ClientResponse send(ClientRequest clientRequest, Unmarshaller unmarshaller) throws NativeException {
        String str;
        if (this.mEndpointURL != null && (str = this.mEndpoint) != null && str.length() != 0) {
            beforeSend(clientRequest);
            ClientRequestEncoder clientRequestEncoder = this.mClientRequestEncoder;
            if (clientRequestEncoder != null) {
                byte[] encode = clientRequestEncoder.encode(clientRequest);
                URL url = this.mEndpointURL;
                int i = this.mMaxRetry;
                int i2 = this.mMaxRedirect;
                ClientResponse clientResponse = null;
                HttpURLConnection httpURLConnection = null;
                while (true) {
                    boolean z = false;
                    if (i < 0) {
                        throw new NativeException(String.format("exceed max retry times %d", Integer.valueOf(this.mMaxRetry)));
                    }
                    if (i2 >= 0) {
                        try {
                            try {
                                httpURLConnection = getHttpURLConnection(url, this.mProxy);
                                doSend(httpURLConnection, clientRequest, encode);
                                int responseCode = httpURLConnection.getResponseCode();
                                if (responseCode != 301 && responseCode != 302) {
                                    if (responseCode != 400 && responseCode != 200) {
                                        throw new NativeException(String.format("receive invalid HTTP response code %d", Integer.valueOf(responseCode)));
                                    }
                                    String translateResponseDataToString = translateResponseDataToString(httpURLConnection, doReceive(httpURLConnection, responseCode));
                                    if (responseCode == 200) {
                                        z = true;
                                    }
                                    httpURLConnection.disconnect();
                                    if (translateResponseDataToString == null) {
                                        translateResponseDataToString = "";
                                    }
                                    if (unmarshaller != null) {
                                        if (z) {
                                            clientResponse = new ClientResponse(unmarshaller.UnmarshalOutput(translateResponseDataToString));
                                        } else {
                                            clientResponse = new ClientResponse(unmarshaller.UnmarshalException(translateResponseDataToString));
                                        }
                                    }
                                    afterSend(clientRequest, clientResponse);
                                    return clientResponse;
                                }
                                i2--;
                                url = dealWithRedirect(httpURLConnection, responseCode);
                            } catch (UnsupportedEncodingException e) {
                                throw new NativeException(e);
                            } catch (IOException e2) {
                                i--;
                                if (i < 0) {
                                    throw new NativeException(e2);
                                }
                                if (httpURLConnection != null) {
                                }
                            }
                            httpURLConnection.disconnect();
                        } catch (Throwable th) {
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            throw th;
                        }
                    } else {
                        throw new NativeException(String.format("exceed max redirect times %d", Integer.valueOf(this.mMaxRedirect)));
                    }
                }
            } else {
                throw new NativeException("ClientRequestEncoder is null.");
            }
        } else {
            throw new NativeException("empty endpoint");
        }
    }

    public void setClientRequestEncoder(ClientRequestEncoder clientRequestEncoder) {
        this.mClientRequestEncoder = clientRequestEncoder;
    }

    public void setConnectTimeout(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mConnectTimeout = i;
    }

    public void setEndpoint(URL url) {
        this.mEndpointURL = url;
        if (url != null) {
            this.mEndpoint = url.toString();
        } else {
            this.mEndpoint = "";
        }
    }

    public void setHttpURLConnectionFactory(HttpURLConnectionFactory httpURLConnectionFactory) {
        this.mHttpURLConnectionFactory = httpURLConnectionFactory;
    }

    public void setMaxRedirect(int i) {
        if (i < 0) {
            i = 10;
        }
        this.mMaxRedirect = i;
    }

    public void setMaxRetry(int i) {
        if (i < 0) {
            i = 5;
        }
        this.mMaxRetry = i;
    }

    public void setProxy(Proxy proxy) {
        this.mProxy = proxy;
    }

    public void setReadTimeout(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mReadTimeout = i;
    }

    public void setTransmissionFilterSet(Set<TransmissionFilter> set) {
        this.mTransmissionFilters = set;
    }

    protected String translateResponseDataToString(HttpURLConnection httpURLConnection, byte[] bArr) throws UnsupportedEncodingException {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        String str = "UTF-8";
        String headerField = httpURLConnection.getHeaderField("Content-Type");
        if (headerField != null && headerField.length() != 0) {
            if (headerField.startsWith("application/json")) {
                int indexOf = headerField.indexOf("charset=");
                if (indexOf != -1) {
                    str = headerField.substring(indexOf + 8);
                }
                return new String(bArr, str);
            }
            throw new UnsupportedEncodingException(String.format("only support application/json, content-type is %s", headerField));
        }
        return new String(bArr, str);
    }
}
