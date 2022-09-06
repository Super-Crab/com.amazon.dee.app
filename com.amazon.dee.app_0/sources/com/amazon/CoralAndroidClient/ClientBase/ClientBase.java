package com.amazon.CoralAndroidClient.ClientBase;

import com.amazon.CoralAndroidClient.Connector.Connector;
import com.amazon.CoralAndroidClient.Connector.HttpURLConnectionFactory;
import com.amazon.CoralAndroidClient.Connector.TransmissionFilter;
import com.amazon.CoralAndroidClient.Exception.ClientException;
import com.amazon.CoralAndroidClient.Exception.CoralException;
import com.amazon.CoralAndroidClient.Exception.NativeException;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes.dex */
public class ClientBase {
    private static final String DEFAULT_RES_PATH = "/";
    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static final Logger LOGGER = Logger.getLogger("ClientBase");
    private HttpURLConnectionFactory mHttpURLConnectionFactory;
    private Proxy mProxy;
    private Object mPropertyLock = new Object();
    private URL mEndpointURL = null;
    private int mRequestTimeout = 0;
    private int mMaxRetry = 5;
    private int mMaxRedirect = 10;
    private CopyOnWriteArraySet<TransmissionFilter> mTransmissionFilters = new CopyOnWriteArraySet<>();
    private ExecutorService mExecutor = Executors.newCachedThreadPool();

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public class RequestTask implements Callable<ClientOutput> {
        private Connector mConnector;
        public final ClientRequest mRequest;
        public final ResultHandler mResultHandler;
        public final Unmarshaller mUnmarshaller;

        public RequestTask(ClientRequest clientRequest, Unmarshaller unmarshaller, ResultHandler resultHandler) {
            this.mRequest = clientRequest;
            this.mUnmarshaller = unmarshaller;
            this.mResultHandler = resultHandler;
        }

        private void initConnector() throws NativeException {
            URL url;
            int i;
            Proxy proxy;
            int i2;
            int i3;
            HttpURLConnectionFactory httpURLConnectionFactory;
            synchronized (ClientBase.this.mPropertyLock) {
                url = ClientBase.this.mEndpointURL;
                i = ClientBase.this.mRequestTimeout;
                proxy = ClientBase.this.mProxy;
                i2 = ClientBase.this.mMaxRetry;
                i3 = ClientBase.this.mMaxRedirect;
                httpURLConnectionFactory = ClientBase.this.mHttpURLConnectionFactory;
            }
            this.mConnector = ClientBase.this.createConnector();
            this.mConnector.setEndpoint(url);
            this.mConnector.setConnectTimeout(i);
            this.mConnector.setReadTimeout(i);
            this.mConnector.setMaxRetry(i2);
            this.mConnector.setMaxRedirect(i3);
            this.mConnector.setProxy(proxy);
            this.mConnector.setTransmissionFilterSet(ClientBase.this.mTransmissionFilters);
            if (httpURLConnectionFactory != null) {
                this.mConnector.setHttpURLConnectionFactory(httpURLConnectionFactory);
            }
        }

        private void onException(ClientException clientException) {
            ResultHandler resultHandler = this.mResultHandler;
            if (resultHandler == null) {
                return;
            }
            resultHandler.onException(clientException);
        }

        private void onSuccess(ClientOutput clientOutput) {
            ResultHandler resultHandler = this.mResultHandler;
            if (resultHandler == null) {
                return;
            }
            resultHandler.onSuccess(clientOutput);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        /* renamed from: call */
        public ClientOutput mo287call() throws NativeException, CoralException {
            initConnector();
            try {
                ClientResponse send = this.mConnector.send(this.mRequest, this.mUnmarshaller);
                if (send == null) {
                    onSuccess(null);
                    return null;
                } else if (!send.isSuccessful()) {
                    CoralException exception = send.getException();
                    if (exception != null) {
                        onException(exception);
                        throw exception;
                    }
                    NativeException nativeException = new NativeException("Response contain empty exception");
                    onException(nativeException);
                    throw nativeException;
                } else {
                    ClientOutput output = send.getOutput();
                    onSuccess(output);
                    return output;
                }
            } catch (NativeException e) {
                onException(e);
                throw e;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Connector createConnector() throws NativeException {
        return Connector.create();
    }

    private Future<ClientOutput> invokeAsyncInternal(ClientInput clientInput, Marshaller marshaller, Unmarshaller unmarshaller, ResultHandler resultHandler) throws NativeException {
        if (marshaller != null) {
            ClientRequest marshal = marshaller.marshal(clientInput);
            if (marshal != null && !Helper.isStringNullOrEmpty(marshal.getOperationName())) {
                try {
                    return this.mExecutor.submit(new RequestTask(marshal, unmarshaller, resultHandler));
                } catch (RejectedExecutionException e) {
                    NativeException nativeException = new NativeException("submit task fail.", e);
                    if (resultHandler != null) {
                        resultHandler.onException(nativeException);
                    }
                    return null;
                }
            }
            throw new NativeException("invalid ClientRequest");
        }
        throw new NativeException("marshaller is null in invokeAsyncInternal");
    }

    public void addTransmissionFilter(TransmissionFilter transmissionFilter) {
        this.mTransmissionFilters.add(transmissionFilter);
    }

    public String getEndpoint() throws NativeException {
        URL url;
        synchronized (this.mPropertyLock) {
            url = this.mEndpointURL;
        }
        return url == null ? "" : url.toString();
    }

    public HttpURLConnectionFactory getHttpURLConnectionFactory() {
        HttpURLConnectionFactory httpURLConnectionFactory;
        synchronized (this.mPropertyLock) {
            httpURLConnectionFactory = this.mHttpURLConnectionFactory;
        }
        return httpURLConnectionFactory;
    }

    public int getMaxRetry() {
        return this.mMaxRetry;
    }

    public Proxy getProxy() {
        Proxy proxy;
        synchronized (this.mPropertyLock) {
            proxy = this.mProxy;
        }
        return proxy;
    }

    public int getRequestTimeout() {
        int i;
        synchronized (this.mPropertyLock) {
            i = this.mRequestTimeout;
        }
        return i;
    }

    public ClientOutput invoke(ClientInput clientInput, Marshaller marshaller, Unmarshaller unmarshaller, ResultHandler resultHandler) throws NativeException, CoralException {
        try {
            return invokeAsyncInternal(clientInput, marshaller, unmarshaller, resultHandler).get();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return null;
        } catch (CancellationException unused2) {
            return null;
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                if (!(cause instanceof NativeException)) {
                    if (cause instanceof CoralException) {
                        throw ((CoralException) cause);
                    }
                    throw new NativeException(cause);
                }
                throw ((NativeException) cause);
            }
            throw new NativeException(e);
        }
    }

    public void invokeAsync(ClientInput clientInput, Marshaller marshaller, Unmarshaller unmarshaller, ResultHandler resultHandler) {
        try {
            invokeAsyncInternal(clientInput, marshaller, unmarshaller, resultHandler);
        } catch (NativeException e) {
            LOGGER.log(Level.WARNING, "enqueue task failed.", (Throwable) e);
        }
    }

    public void removeTransmissionFilter(TransmissionFilter transmissionFilter) {
        this.mTransmissionFilters.remove(transmissionFilter);
    }

    public void setEndpoint(String str) throws NativeException {
        if (Helper.isStringNullOrEmpty(str)) {
            throw new NativeException(String.format("invalid endpoint: %s", str == null ? "(null)" : "(empty)"));
        }
        try {
            URL url = new URL(str);
            if (url.getProtocol() != null && url.getHost() != null) {
                String protocol = url.getProtocol();
                if (!protocol.equals("http") && !protocol.equals("https")) {
                    throw new NativeException(String.format("invalid endpoint %s, only HTTP and HTTPS are acceptable", str));
                }
                String str2 = "/";
                if (url.getPath() != null && url.getPath().length() != 0) {
                    str2 = url.getPath();
                }
                URL url2 = new URL(url.getProtocol(), url.getHost(), url.getPort(), str2);
                synchronized (this.mPropertyLock) {
                    this.mEndpointURL = url2;
                }
                return;
            }
            throw new NativeException(String.format("invalid endpoint %s, need protocol and host", str));
        } catch (MalformedURLException e) {
            throw new NativeException(String.format("invalid endpoint %s", str), e);
        }
    }

    public void setHttpURLConnectionFactory(HttpURLConnectionFactory httpURLConnectionFactory) {
        synchronized (this.mPropertyLock) {
            this.mHttpURLConnectionFactory = httpURLConnectionFactory;
        }
    }

    public void setMaxRetry(int i) {
        if (i >= 0) {
            this.mMaxRetry = i;
        }
    }

    public void setProxy(Proxy proxy) {
        synchronized (this.mPropertyLock) {
            this.mProxy = proxy;
        }
    }

    public void setRequestTimeout(int i) {
        if (i < 0) {
            i = 0;
        }
        synchronized (this.mPropertyLock) {
            this.mRequestTimeout = i;
        }
    }
}
