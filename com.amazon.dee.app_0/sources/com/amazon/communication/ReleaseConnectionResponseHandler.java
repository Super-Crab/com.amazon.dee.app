package com.amazon.communication;

import amazon.communication.RequestFailedException;
import amazon.communication.ResponseHandlerBase;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.dp.logger.DPLogger;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes12.dex */
public class ReleaseConnectionResponseHandler implements ResponseHandlerBase {
    private static final DPLogger log = new DPLogger("TComm.ReleaseConnectionResponseHandler");
    private amazon.communication.connection.IConnection mConnection;
    private ResponseHandlerBase mDecoratedResponseHandlerBase;

    public ReleaseConnectionResponseHandler(amazon.communication.connection.IConnection iConnection, ResponseHandlerBase responseHandlerBase) {
        if (iConnection != null) {
            if (responseHandlerBase != null) {
                this.mConnection = iConnection;
                this.mDecoratedResponseHandlerBase = responseHandlerBase;
                return;
            }
            throw new IllegalArgumentException("Decorated ResponseHandlerBase can not be null");
        }
        throw new IllegalArgumentException("Connection can not be null");
    }

    private synchronized void releaseConnection() {
        if (this.mConnection != null) {
            log.verbose("releaseConnection", "releasing", "mConnection", this.mConnection);
            this.mConnection.release();
            this.mConnection = null;
        } else {
            log.verbose("releaseConnection", "connection is already released", new Object[0]);
        }
    }

    @Override // amazon.communication.ResponseHandlerBase
    public void onError(HttpRequestBase httpRequestBase, RequestFailedException requestFailedException) {
        try {
            if (this.mDecoratedResponseHandlerBase != null) {
                this.mDecoratedResponseHandlerBase.onError(httpRequestBase, requestFailedException);
                return;
            }
            throw new IllegalStateException("onResponse/onError has already been invoked for the ResponseHandlerBase:" + this);
        } finally {
            releaseConnection();
            this.mDecoratedResponseHandlerBase = null;
        }
    }

    @Override // amazon.communication.ResponseHandlerBase
    public void onResponse(EndpointIdentity endpointIdentity, HttpResponse httpResponse, int i) {
        try {
            if (this.mDecoratedResponseHandlerBase != null) {
                this.mDecoratedResponseHandlerBase.onResponse(endpointIdentity, httpResponse, i);
                return;
            }
            throw new IllegalStateException("onResponse/onError has already been invoked for the ResponseHandlerBase:" + this);
        } finally {
            releaseConnection();
            this.mDecoratedResponseHandlerBase = null;
        }
    }
}
