package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.MissingCredentialsException;
import amazon.communication.authentication.AccountRequestContext;
import amazon.communication.authentication.RequestSigner;
import amazon.communication.authentication.SigningException;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IRServiceEndpoint;
import amazon.communication.identity.IdentityResolver;
import amazon.communication.identity.ServiceIdentity;
import android.net.http.AndroidHttpClient;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.communication.socket.Measurements;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.ProtocolSocketBase;
import com.amazon.communication.socket.SocketUsageWriter;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseReason;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.utils.FailFast;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
/* loaded from: classes12.dex */
public class RawHttpSocket extends ProtocolSocketBase {
    private static final int CONNECTION_MAX_IDLE_TIME_MS = 5000;
    private static final String METRICS_SOURCE_NAME = "RawHttpSocket";
    private final BandwidthToolByteAccountant mByteAccountant;
    private CloseDetail mCloseDetail;
    private CloseReason mCloseReason;
    private final String mDirectedId;
    protected final URI mEndpointUri;
    private final HttpClient mHttpClient;
    private MetricEvent mMetricEvent;
    private final PeriodicMetricReporter mPeriodicMetricReporter;
    private final RequestSigner mRequestSigner;
    private final ResponseRouter mResponseRouter;
    protected ProtocolSocket.ProtocolSocketState mSocketState;
    private final SocketUsageWriter mSocketUsageWriter;
    protected final boolean mUseCompression;
    protected final boolean mUseSecureConnection;
    private final WorkExecutor mWorkExecutor;
    private static final DPLogger log = new DPLogger("TComm.RawHttpSocket");
    private static final CloseDetail NORMAL_CLOSE = new CloseDetail(1000, "Client-initiated close without detail");
    private static final HttpRequestResponseConverter sHttpConverter = PlainTextHttpRequestResponseConverter.getInstance();

    public RawHttpSocket(Set<ProtocolSocket.ProtocolSocketAttribute> set, IdentityResolver identityResolver, EndpointIdentity endpointIdentity, RequestSigner requestSigner, WorkExecutor workExecutor, HttpClient httpClient, ResponseRouter responseRouter, SocketUsageWriter socketUsageWriter, PeriodicMetricReporter periodicMetricReporter, BandwidthToolByteAccountant bandwidthToolByteAccountant, String str) {
        Set<ProtocolSocket.ProtocolSocketAttribute> copyOf;
        log.verbose(METRICS_SOURCE_NAME, "begin constructor", "endpoint", EndpointIdentity.logSafe(endpointIdentity), "responseRouter", responseRouter, "workExecutor", workExecutor);
        this.mIdentity = endpointIdentity;
        this.mWorkExecutor = workExecutor;
        this.mRequestSigner = requestSigner;
        this.mHttpClient = httpClient;
        this.mResponseRouter = responseRouter;
        this.mSocketUsageWriter = socketUsageWriter;
        if (set == null) {
            copyOf = ProtocolSocket.ProtocolSocketAttribute.EMPTY_ATTRIBUTES;
        } else {
            copyOf = EnumSet.copyOf((Collection) set);
        }
        FailFast.expectTrue(copyOf.contains(ProtocolSocket.ProtocolSocketAttribute.REQUEST_RESPONSE_ONLY));
        addSupportedAttributes(copyOf);
        removeSupportedAttributes(Collections.singleton(ProtocolSocket.ProtocolSocketAttribute.REUSABLE));
        this.mUseCompression = isAttributeSupported(ProtocolSocket.ProtocolSocketAttribute.COMPRESSED);
        this.mUseSecureConnection = isAttributeSupported(ProtocolSocket.ProtocolSocketAttribute.SECURE);
        this.mEndpointUri = URI.create(convertEndpointToHttpUri(endpointIdentity, identityResolver));
        this.mSocketState = ProtocolSocket.ProtocolSocketState.CONNECTED;
        this.mCloseDetail = null;
        this.mCloseReason = null;
        this.mSocketUsageWriter.writeTimestamp(Measurements.COUNT_SOCKETS_OPENED_TO_ENDPOINT, this.mIdentity, GlobalTimeSource.INSTANCE.currentTimeMillis());
        this.mPeriodicMetricReporter = periodicMetricReporter;
        this.mMetricEvent = periodicMetricReporter.createTrackedMetricEvent(TCommMetrics.PROGRAM_ID, METRICS_SOURCE_NAME);
        this.mMetricEvent.incrementCounter(TCommMetrics.COUNT_RAWHTTPSOCKET_CONNECTION_ESTABLISHED, 1.0d);
        this.mByteAccountant = bandwidthToolByteAccountant;
        this.mDirectedId = str;
    }

    private String convertEndpointToHttpUri(EndpointIdentity endpointIdentity, IdentityResolver identityResolver) {
        String endpointIdentity2;
        log.verbose("convertEndpointToHttpUri", "converting", "endpoint", EndpointIdentity.logSafe(endpointIdentity));
        if (endpointIdentity instanceof ServiceIdentity) {
            IRServiceEndpoint resolveServiceEndpoint = identityResolver.resolveServiceEndpoint((ServiceIdentity) endpointIdentity);
            if (resolveServiceEndpoint != null) {
                endpointIdentity2 = resolveServiceEndpoint.toResolvedUri(this.mUseSecureConnection ? IRServiceEndpoint.Scheme.HTTPS : IRServiceEndpoint.Scheme.HTTP);
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid EndpointIdentity: Not a valid service- ");
                outline107.append(EndpointIdentity.logSafe(endpointIdentity));
                throw new IllegalArgumentException(outline107.toString());
            }
        } else {
            endpointIdentity2 = endpointIdentity.toString();
        }
        String str = this.mUseSecureConnection ? "s" : "";
        if (endpointIdentity2.startsWith("http" + str)) {
            return endpointIdentity2;
        }
        if (endpointIdentity2.startsWith("ws" + str)) {
            return GeneratedOutlineSupport1.outline55(endpointIdentity2, 2, GeneratedOutlineSupport1.outline107("http"));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline77("Endpoint does not have a ws", str, ":// or http", str, ":// address"));
    }

    protected void addTimeoutHttpParamsFromHeaders(HttpRequestBase httpRequestBase) {
        BasicHttpParams params = httpRequestBase.getParams();
        if (params == null) {
            params = new BasicHttpParams();
        }
        Header firstHeader = httpRequestBase.getFirstHeader(HttpRequestResponseConverterBase.CONNECTION_TIMEOUT_MS_HEADER);
        if (firstHeader != null) {
            try {
                int parseInt = Integer.parseInt(firstHeader.getValue());
                if (parseInt >= 0) {
                    HttpConnectionParams.setConnectionTimeout(params, parseInt);
                } else {
                    log.warn("addTimeoutHttpParamsFromHeaders", "negative connection timeout", "connectionTimeout", Integer.valueOf(parseInt));
                }
            } catch (NumberFormatException e) {
                log.error("addTimeoutHttpParamsFromHeaders", "Error reading value from x-amz-connection-timeout-msheader, not setting connection timeout", e);
            }
        }
        Header firstHeader2 = httpRequestBase.getFirstHeader(HttpRequestResponseConverterBase.SOCKET_TIMEOUT_MS_HEADER);
        if (firstHeader2 != null) {
            try {
                int parseInt2 = Integer.parseInt(firstHeader2.getValue());
                if (parseInt2 >= 0) {
                    HttpConnectionParams.setSoTimeout(params, parseInt2);
                } else {
                    log.warn("addTimeoutHttpParamsFromHeaders", "negative socket timeout", "socketTimeout", Integer.valueOf(parseInt2));
                }
            } catch (NumberFormatException e2) {
                log.error("addTimeoutHttpParamsFromHeaders", "Error reading value from x-amz-socket-timeout-msheader, not setting socket timeout", e2);
            }
        }
        httpRequestBase.setParams(params);
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public void close() {
        close(NORMAL_CLOSE);
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public CloseDetail closeDetail() {
        return this.mCloseDetail;
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public CloseReason closeReason() {
        return this.mCloseReason;
    }

    protected void makeRequestAndRouteResponse(HttpRequestBase httpRequestBase, int i) throws IOException, ProtocolException {
        log.verbose("call", "making asynchronous request", "request", httpRequestBase);
        try {
            HttpResponse execute = this.mHttpClient.execute(httpRequestBase);
            log.verbose("call", "got response", "response", execute);
            HttpResponseValidator.validateContentLength(execute);
            if (HttpResponseDecompressor.decompressResponseIfNecessary(execute) && !this.mUseCompression) {
                log.warn("call", "HttpResponse was compressed but this socket does not expect compression", new Object[0]);
            }
            try {
                Message convertResponseToMessage = sHttpConverter.convertResponseToMessage(execute);
                log.verbose("call", "converted response to message", new Object[0]);
                routeResponseMessage(convertResponseToMessage, i);
            } catch (ProtocolException e) {
                DPLogger dPLogger = log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProtocolException while converting response to message: ");
                outline107.append(e.getMessage());
                dPLogger.verbose("call", outline107.toString(), new Object[0]);
                throw e;
            }
        } catch (ClientProtocolException e2) {
            DPLogger dPLogger2 = log;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ClientProtocolException while executing request: ");
            outline1072.append(e2.getMessage());
            dPLogger2.verbose("call", outline1072.toString(), new Object[0]);
            throw e2;
        } catch (IOException e3) {
            log.verbose("call", GeneratedOutlineSupport1.outline37(e3, GeneratedOutlineSupport1.outline107("IOException while executing request: ")), new Object[0]);
            throw e3;
        }
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public int release() {
        int release = super.release();
        if (release == 0) {
            close(NORMAL_CLOSE);
        }
        return release;
    }

    protected void routeResponseMessage(Message message, int i) {
        this.mResponseRouter.routeResponse(this.mIdentity, message, i);
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void sendMessage(Message message, String str, int i) throws IOException, MissingCredentialsException {
        log.verbose("sendMessage", "begin", "messageType", str, "channel", Integer.valueOf(i));
        if (str.equals(ProtocolHandler.REQUEST_MESSAGE_TYPE)) {
            try {
                HttpRequestBase convertMessageToRequest = sHttpConverter.convertMessageToRequest(message);
                if (this.mUseCompression) {
                    AndroidHttpClient.modifyRequestToAcceptGzipResponse(convertMessageToRequest);
                }
                addTimeoutHttpParamsFromHeaders(convertMessageToRequest);
                sendOverHttpClient(convertMessageToRequest, i);
                return;
            } catch (ProtocolException e) {
                throw new IOException(e);
            }
        }
        throw new IllegalArgumentException("RawHttpSocket should only receive RQS messages");
    }

    protected void sendOverHttpClient(final HttpRequestBase httpRequestBase, final int i) throws IOException, MissingCredentialsException {
        log.verbose("sendOverStandardHttpClient", "sending", "request.getURI", httpRequestBase.getURI());
        URI uri = httpRequestBase.getURI();
        String host = uri.getHost();
        String host2 = this.mEndpointUri.getHost();
        if (host != null && !host.equals("") && !host.equals(host2)) {
            log.error("sendOverStandardHttpClient", "Unexpected host", "endpointHost", host2, " requestHost", host);
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline76("Received request for a different endpoint than this socket is connected to. requestHost:", host, ", endpointHost:", host2));
        }
        try {
            URI uri2 = new URI(this.mEndpointUri.getScheme(), uri.getUserInfo(), this.mEndpointUri.getHost(), this.mEndpointUri.getPort(), uri.getPath(), uri.getQuery(), uri.getFragment());
            String aSCIIString = uri2.toASCIIString();
            URI create = URI.create(aSCIIString);
            log.verbose("sendOverStandardHttpClient", "After combining endpoint URI and http request URI", "httpUri", uri2, "escapedHttpUri", aSCIIString, "finalUri", create);
            httpRequestBase.setURI(create);
            AccountRequestContext accountRequestContext = isAttributeSupported(ProtocolSocket.ProtocolSocketAttribute.ANONYMOUS) ? AccountRequestContext.EMPTY_ACCOUNT : null;
            String str = this.mDirectedId;
            if (str != null) {
                accountRequestContext = new AccountRequestContext(str);
            }
            try {
                this.mRequestSigner.signRequest(httpRequestBase, accountRequestContext);
                Callable<Void> callable = new Callable<Void>() { // from class: com.amazon.communication.RawHttpSocket.2
                    @Override // java.util.concurrent.Callable
                    public Void call() throws Exception {
                        RawHttpSocket.this.makeRequestAndRouteResponse(httpRequestBase, i);
                        return null;
                    }
                };
                log.verbose("sendOverStandardHttpClient", "Submitting work to WorkExecutor", new Object[0]);
                this.mWorkExecutor.enqueueWork(this, callable);
            } catch (SigningException e) {
                throw new IOException(e);
            }
        } catch (URISyntaxException e2) {
            log.error("sendOverStandardHttpClient", "URISyntaxException thrown", e2);
            throw new IllegalArgumentException("Received request for a URI that could not be parsed. requestUri:" + uri + ", mEndpointUri:" + this.mEndpointUri);
        }
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public ProtocolSocket.ProtocolSocketState socketState() {
        return this.mSocketState;
    }

    public String toString() {
        return formatProtocolSocketString(METRICS_SOURCE_NAME, this.mEndpointUri, this.mSocketState.toString());
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public void verifyTuningResult(ByteBufferBackedMessage byteBufferBackedMessage) {
        throw new UnsupportedOperationException("RawHttpSocket doesn't need tuning");
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void close(CloseDetail closeDetail) {
        this.mSocketState = ProtocolSocket.ProtocolSocketState.DISCONNECTED;
        this.mCloseDetail = closeDetail;
        this.mCloseReason = CloseReason.CLOSE_CALLER;
        this.mWorkExecutor.enqueueWorkAfter(this, new Callable<Void>() { // from class: com.amazon.communication.RawHttpSocket.1
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                RawHttpSocket.this.mHttpClient.getConnectionManager().closeIdleConnections(5000L, TimeUnit.MILLISECONDS);
                return null;
            }
        }, 6000L);
        this.mSocketUsageWriter.writeTimestamp(Measurements.COUNT_SOCKETS_CLOSED_TO_ENDPOINT, this.mIdentity, GlobalTimeSource.INSTANCE.currentTimeMillis());
        this.mMetricEvent.incrementCounter(TCommMetrics.COUNT_RAWHTTPSOCKET_CLOSED, 1.0d);
        notifyStateChanged();
    }
}
