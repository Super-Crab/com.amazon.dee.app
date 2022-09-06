package amazon.communication.srr;

import amazon.communication.CommunicationManager;
import amazon.communication.ConnectionAcquisitionFailedException;
import amazon.communication.MissingCredentialsException;
import amazon.communication.RequestFailedException;
import amazon.communication.ResponseHandler;
import amazon.communication.TimeoutException;
import amazon.communication.authentication.RequestContext;
import amazon.communication.connection.Connection;
import amazon.communication.connection.ConnectionPolicyException;
import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IRServiceEndpoint;
import amazon.communication.identity.IdentityResolver;
import amazon.communication.identity.ServiceIdentity;
import amazon.communication.srr.SrrRequest;
import com.amazon.communication.BlockingResponseHandler;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.dp.logger.DPLogger;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.net.HttpURLConnection;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
@Deprecated
/* loaded from: classes.dex */
public class TCommSrrManager implements SrrManager, SingleRequestResponseManager {
    private static final DPLogger log = new DPLogger("TComm.SrrManager");
    @Deprecated
    private CommunicationManager mCommunicationManager;
    @Deprecated
    private final Policy mPolicy;

    @FireOsSdk
    @Deprecated
    public TCommSrrManager(CommunicationManager communicationManager, Policy policy) {
        if (communicationManager != null) {
            if (policy != null) {
                this.mCommunicationManager = communicationManager;
                this.mPolicy = policy;
                return;
            }
            throw new IllegalArgumentException("Policy cannot be null");
        }
        throw new IllegalArgumentException("CommunicationManager cannot be null");
    }

    private int getTimeoutFromSrrRequest(SrrRequest srrRequest) throws ConnectionAcquisitionFailedException {
        int timeout = srrRequest.getTimeout();
        if (timeout >= 0) {
            EndpointIdentity endpointIdentity = srrRequest.getEndpointIdentity();
            if (!(endpointIdentity instanceof ServiceIdentity)) {
                throw new IllegalArgumentException("Can only make SRR requests to service endpoints.");
            }
            if (timeout != 0) {
                return timeout;
            }
            IdentityResolver identityResolver = this.mCommunicationManager.getIdentityResolver();
            if (identityResolver != null) {
                IRServiceEndpoint resolveServiceEndpoint = identityResolver.resolveServiceEndpoint((ServiceIdentity) endpointIdentity);
                if (resolveServiceEndpoint != null) {
                    return resolveServiceEndpoint.getTimeout();
                }
                throw new ConnectionAcquisitionFailedException("Could not find desired endpoint in Identity Resolver");
            }
            throw new ConnectionAcquisitionFailedException("Could not contact Identity Resolver");
        }
        throw new IllegalArgumentException("Timeout must not be negative!");
    }

    private Policy overrideConnectionPolicy(SrrRequest srrRequest) throws IllegalAccessException, ConnectionPolicyException {
        Policy policy = this.mPolicy;
        if (policy.getCompressionOption().equals(srrRequest.getCompressionOption()) && policy.isWiFiNecessary() == srrRequest.isWiFiNecessary() && srrRequest.getRequestContext() == null) {
            return policy;
        }
        boolean isAnonymousCredentialsAllowed = policy.isAnonymousCredentialsAllowed();
        if (srrRequest.getRequestContext() != null) {
            isAnonymousCredentialsAllowed = isAnonymousCredentialsAllowed(srrRequest.getRequestContext());
        }
        return new Policy.Builder().setCompressionOption(srrRequest.getCompressionOption()).setPriority(policy.getPriority()).setIsLowLatencyNecessary(policy.isLowLatencyNecessary()).setIsRequestResponseOnly(policy.isRequestResponseOnly()).setIsClearText(policy.isClearText() && srrRequest.isClearTextAllowed()).setIsWiFiNecessary(srrRequest.isWiFiNecessary()).setIsAnonymousCredentialsAllowed(isAnonymousCredentialsAllowed).build();
    }

    @FireOsSdk
    @Deprecated
    protected boolean isAnonymousCredentialsAllowed(RequestContext requestContext) {
        return false;
    }

    @FireOsSdk
    @Deprecated
    public HttpResponse makeRequest(HttpRequestBase httpRequestBase, EndpointIdentity endpointIdentity, int i) throws TimeoutException, RequestFailedException, MissingCredentialsException, ConnectionAcquisitionFailedException {
        try {
            return makeRequestSync(new SrrRequest.Builder().mo6setRequest(httpRequestBase).mo3setEndpointIdentity(endpointIdentity).mo8setTimeout(i).mo1build());
        } catch (IllegalAccessException e) {
            throw new RequestFailedException(e);
        }
    }

    @Override // amazon.communication.srr.SrrManager
    @FireOsSdk
    @Deprecated
    public void makeRequestAsync(SrrRequest srrRequest, ResponseHandler responseHandler) throws RequestFailedException, MissingCredentialsException {
        HttpRequestBase request = srrRequest.getRequest();
        EndpointIdentity endpointIdentity = srrRequest.getEndpointIdentity();
        try {
            int timeoutFromSrrRequest = getTimeoutFromSrrRequest(srrRequest);
            if (timeoutFromSrrRequest < 0) {
                throw new IllegalArgumentException("Timeout value should be nonnegative integer");
            }
            if (responseHandler != null) {
                try {
                    log.verbose("makeRequestAsync", "acquire connection", "currentThread.getId", Long.valueOf(Thread.currentThread().getId()));
                    SrrConnectionListener srrConnectionListener = new SrrConnectionListener(request, responseHandler, timeoutFromSrrRequest, null);
                    try {
                        Connection acquireConnection = this.mCommunicationManager.acquireConnection(endpointIdentity, overrideConnectionPolicy(srrRequest), srrConnectionListener);
                        if (acquireConnection == null || acquireConnection.getConnectionState() != 2) {
                            return;
                        }
                        srrConnectionListener.onOpened(acquireConnection);
                    } catch (ConnectionAcquisitionFailedException e) {
                        e = e;
                        throw new RequestFailedException("Connection cannot be established.", e);
                    } catch (ConnectionPolicyException e2) {
                        e = e2;
                        throw new RequestFailedException(e);
                    } catch (IllegalAccessException e3) {
                        e = e3;
                        throw new RequestFailedException(e);
                    } catch (IllegalArgumentException e4) {
                    }
                } catch (ConnectionAcquisitionFailedException e5) {
                    e = e5;
                } catch (ConnectionPolicyException e6) {
                    e = e6;
                } catch (IllegalAccessException e7) {
                    e = e7;
                } catch (IllegalArgumentException e8) {
                    throw e8;
                }
            } else {
                throw new IllegalArgumentException("ResponseHandler cannot be null");
            }
        } catch (ConnectionAcquisitionFailedException e9) {
            log.verbose("makeRequestAsync", ADMRegistrationConstants.CALL_EXCEPTION, "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), e9);
            throw new RequestFailedException("Connection cannot be established.", e9);
        }
    }

    @Override // amazon.communication.srr.SrrManager
    @FireOsSdk
    @Deprecated
    public HttpResponse makeRequestSync(SrrRequest srrRequest) throws TimeoutException, RequestFailedException, MissingCredentialsException {
        try {
            BlockingResponseHandler blockingResponseHandler = new BlockingResponseHandler(getTimeoutFromSrrRequest(srrRequest));
            makeRequestAsync(srrRequest, new BlockingResponseHandlerDelegate(blockingResponseHandler));
            return blockingResponseHandler.waitForResponse();
        } catch (ConnectionAcquisitionFailedException e) {
            log.verbose("makeRequestSync", ADMRegistrationConstants.CALL_EXCEPTION, "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), e);
            throw new RequestFailedException("Connection cannot be established.", e);
        } catch (IllegalArgumentException e2) {
            log.verbose("makeRequestSync", "user Exception", "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), e2);
            throw e2;
        } catch (InterruptedException e3) {
            log.verbose("makeRequestSync", ADMRegistrationConstants.CALL_EXCEPTION, "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), e3);
            throw new RequestFailedException("InterruptedException when making http request.", e3);
        }
    }

    @FireOsSdk
    @Deprecated
    public HttpURLConnection makeRequestSyncDiscovery(SrrRequest srrRequest) throws TimeoutException, RequestFailedException, MissingCredentialsException {
        return null;
    }

    @FireOsSdk
    @Deprecated
    public void setCommunicationManager(CommunicationManager communicationManager) {
        this.mCommunicationManager = communicationManager;
    }
}
