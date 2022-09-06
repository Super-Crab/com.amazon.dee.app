package amazon.communication.srr;

import amazon.communication.MissingCredentialsException;
import amazon.communication.RequestFailedException;
import amazon.communication.ResponseHandler;
import amazon.communication.TimeoutException;
import amazon.communication.authentication.RequestSigner;
import amazon.communication.authentication.SigningException;
import amazon.communication.connection.CompressionOption;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IRServiceEndpoint;
import amazon.communication.identity.IdentityResolver;
import amazon.communication.identity.ServiceIdentity;
import amazon.communication.identity.UrlEndpointIdentity;
import com.amazon.communication.HttpResponseDecompressor;
import com.amazon.communication.HttpResponseValidator;
import com.amazon.dp.logger.DPFormattedMessage;
import com.amazon.dp.logger.DPLogger;
import com.amazon.fireos.sdk.annotations.Extends;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.params.HttpConnectionParams;
@Extends(superClass = Object.class)
/* loaded from: classes.dex */
public class HttpClientSrrManager extends HttpClientSrrManagerBase implements SrrManager {
    private static final String COMPRESSION_ENCODING_VALUE = "gzip";
    private static final int DEFAULT_HTTP_CLIENT_TIMEOUT_IN_MILLISECONDS = 10000;
    private static final String ENCODING_HEADER_NAME = "Accept-Encoding";
    private static final DPLogger log = new DPLogger("TComm.HttpClientSrrManager");
    private final HttpClient mHttpClient;
    private IdentityResolver mIdentityResolver;
    private final RequestSigner mRequestSigner;

    @FireOsSdk
    @Deprecated
    public HttpClientSrrManager(HttpClient httpClient, RequestSigner requestSigner) {
        super(httpClient, requestSigner);
        this.mHttpClient = httpClient;
        this.mRequestSigner = requestSigner;
    }

    @Override // amazon.communication.srr.SrrManager
    @FireOsSdk
    @Deprecated
    public void makeRequestAsync(SrrRequest srrRequest, ResponseHandler responseHandler) {
        throw new UnsupportedOperationException();
    }

    @Override // amazon.communication.srr.SrrManager
    @FireOsSdk
    @Deprecated
    public HttpResponse makeRequestSync(SrrRequest srrRequest) throws MissingCredentialsException, RequestFailedException, TimeoutException {
        HttpHost httpHost;
        boolean z;
        if (srrRequest != null) {
            int timeout = srrRequest.getTimeout();
            if (timeout >= 0) {
                if (timeout == 0) {
                    timeout = 10000;
                }
                EndpointIdentity endpointIdentity = srrRequest.getEndpointIdentity();
                try {
                    if (endpointIdentity instanceof UrlEndpointIdentity) {
                        URI uri = new URI(endpointIdentity.toString());
                        httpHost = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
                    } else if (!(endpointIdentity instanceof ServiceIdentity) || this.mIdentityResolver == null) {
                        httpHost = null;
                    } else {
                        IRServiceEndpoint resolveServiceEndpoint = this.mIdentityResolver.resolveServiceEndpoint((ServiceIdentity) endpointIdentity);
                        if (resolveServiceEndpoint != null) {
                            if (srrRequest.isClearTextAllowed() && resolveServiceEndpoint.getClearTextConnection() == IRServiceEndpoint.ClearTextConnection.ALLOWED) {
                                httpHost = new HttpHost(resolveServiceEndpoint.getHostname(), resolveServiceEndpoint.getPort().intValue(), IRServiceEndpoint.Scheme.HTTP.toString());
                            } else {
                                httpHost = new HttpHost(resolveServiceEndpoint.getHostname(), resolveServiceEndpoint.getSecurePort().intValue(), IRServiceEndpoint.Scheme.HTTPS.toString());
                            }
                        } else {
                            throw new IllegalArgumentException("irEndpoint is null");
                        }
                    }
                    if (httpHost != null) {
                        HttpRequestBase request = srrRequest.getRequest();
                        if (srrRequest.getCompressionOption() == CompressionOption.REQUIRED || srrRequest.getCompressionOption() == CompressionOption.ALLOWED) {
                            request.addHeader("Accept-Encoding", COMPRESSION_ENCODING_VALUE);
                            z = true;
                        } else {
                            z = false;
                        }
                        try {
                            request.setURI(URIUtils.rewriteURI(request.getURI(), httpHost));
                            this.mRequestSigner.signRequest(request, srrRequest.getRequestContext());
                            try {
                                HttpConnectionParams.setConnectionTimeout(this.mHttpClient.getParams(), timeout);
                                HttpConnectionParams.setSoTimeout(this.mHttpClient.getParams(), timeout);
                                HttpResponse execute = this.mHttpClient.execute(request);
                                HttpResponseValidator.validateContentLength(execute);
                                if (HttpResponseDecompressor.decompressResponseIfNecessary(execute) && !z) {
                                    log.warn("makeRequestSync", "HttpResponse was compressed but request does not expect compression", new Object[0]);
                                }
                                return execute;
                            } catch (SocketTimeoutException e) {
                                DPFormattedMessage dPFormattedMessage = new DPFormattedMessage("makeRequestSync", "Timed out making the request.", "destination", EndpointIdentity.logSafe(endpointIdentity), e);
                                log.error(dPFormattedMessage);
                                throw new TimeoutException(dPFormattedMessage.toString(), e);
                            } catch (ConnectTimeoutException e2) {
                                DPFormattedMessage dPFormattedMessage2 = new DPFormattedMessage("makeRequestSync", "Timed out connecting for the request.", "destination", EndpointIdentity.logSafe(endpointIdentity), e2);
                                log.error(dPFormattedMessage2);
                                throw new TimeoutException(dPFormattedMessage2.toString(), e2);
                            } catch (IOException e3) {
                                DPFormattedMessage dPFormattedMessage3 = new DPFormattedMessage("makeRequestSync", "Failed to execute the request.", "destination", EndpointIdentity.logSafe(endpointIdentity), e3);
                                log.error(dPFormattedMessage3);
                                throw new RequestFailedException(dPFormattedMessage3.toString(), e3);
                            }
                        } catch (SigningException e4) {
                            DPFormattedMessage dPFormattedMessage4 = new DPFormattedMessage("makeRequestSync", "Failed to sign request.", "destination", EndpointIdentity.logSafe(endpointIdentity), e4);
                            log.error(dPFormattedMessage4);
                            throw new RequestFailedException(dPFormattedMessage4.toString(), e4);
                        } catch (URISyntaxException e5) {
                            DPFormattedMessage dPFormattedMessage5 = new DPFormattedMessage("makeRequestSync", "Failed to parse the URI for the request.", "destination", EndpointIdentity.logSafe(endpointIdentity), e5);
                            log.error(dPFormattedMessage5);
                            throw new RequestFailedException(dPFormattedMessage5.toString(), e5);
                        }
                    }
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HttpClientSrrManager only supports UriEndpointIdentity, or ServiceIdentity if IdentityResolver is set. identity: ");
                    outline107.append(EndpointIdentity.logSafe(endpointIdentity));
                    throw new IllegalArgumentException(outline107.toString());
                } catch (URISyntaxException e6) {
                    throw new IllegalArgumentException("Improperly formatted URL", e6);
                }
            }
            throw new IllegalArgumentException("The SrrRequest must have a non-negative timeout");
        }
        throw new IllegalArgumentException("The SrrRequest cannot be null");
    }

    @FireOsSdk
    @Deprecated
    public HttpURLConnection makeRequestSyncDiscovery(SrrRequest srrRequest) throws MissingCredentialsException, RequestFailedException, TimeoutException {
        throw new UnsupportedOperationException();
    }

    @Override // amazon.communication.srr.HttpClientSrrManagerBase
    @FireOsSdk
    @Deprecated
    public void setIdentityResolver(IdentityResolver identityResolver) {
        super.setIdentityResolver(identityResolver);
        this.mIdentityResolver = identityResolver;
    }

    @FireOsSdk
    @Deprecated
    public HttpClientSrrManager(RequestSigner requestSigner) {
        super(requestSigner);
        this.mRequestSigner = requestSigner;
        this.mHttpClient = null;
    }
}
