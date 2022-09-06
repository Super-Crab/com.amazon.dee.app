package com.amazon.communication;

import amazon.communication.RequestFailedException;
import amazon.communication.ResponseHandlerBase;
import amazon.communication.TimeoutException;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.dp.logger.DPLogger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes12.dex */
public class BlockingResponseHandler implements ResponseHandlerBase {
    private static final DPLogger log = new DPLogger("TComm.BlockingResponseHandler");
    private HttpResponse mResponse;
    private final int mTimeout;
    private final Lock mLock = new ReentrantLock();
    private final Condition mResponseAvailable = this.mLock.newCondition();
    private volatile RequestFailedException mException = null;

    public BlockingResponseHandler(int i) {
        this.mTimeout = i;
    }

    @Override // amazon.communication.ResponseHandlerBase
    public void onError(HttpRequestBase httpRequestBase, RequestFailedException requestFailedException) {
        this.mLock.lock();
        try {
            this.mException = requestFailedException;
            this.mResponseAvailable.signal();
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // amazon.communication.ResponseHandlerBase
    public void onResponse(EndpointIdentity endpointIdentity, HttpResponse httpResponse, int i) {
        this.mLock.lock();
        try {
            this.mResponse = httpResponse;
            if (this.mResponse != null) {
                this.mResponseAvailable.signal();
            }
        } finally {
            this.mLock.unlock();
        }
    }

    public HttpResponse waitForResponse() throws RequestFailedException, TimeoutException, InterruptedException {
        boolean z;
        this.mLock.lock();
        try {
            if (this.mResponse == null) {
                log.verbose("waitForResponse", "request sent... waiting...", "currentThread.getId", Long.valueOf(Thread.currentThread().getId()));
                if (!this.mResponseAvailable.await(this.mTimeout, TimeUnit.MILLISECONDS)) {
                    log.verbose("waitForResponse", "timed out waiting for condition", "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), "mTimeout", Integer.valueOf(this.mTimeout));
                    z = true;
                } else {
                    z = false;
                }
                if (this.mException != null) {
                    log.verbose("waitForResponse", "request failed", "currentThread.getId", Long.valueOf(Thread.currentThread().getId()), this.mException);
                    throw this.mException;
                } else if (z) {
                    throw new TimeoutException("Response timeout. " + this.mTimeout + " TimeLimit.");
                }
            }
            return this.mResponse;
        } finally {
            this.mLock.unlock();
        }
    }
}
