package com.amazon.alexa.sharing.media.picker;

import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.api.exceptions.BridgeError;
import com.amazon.comms.log.CommsLogger;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes10.dex */
public class ResultCollector {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ResultCollector.class);
    private WritableArray mArrayResult;
    private boolean mIsMultiple;
    private Promise mPromise;
    private boolean mResultSent;
    private int mWaitCount;
    private AtomicInteger mWaitCounter;

    private synchronized boolean isRequestValid() {
        if (this.mResultSent) {
            LOG.w("Skip result, already sent");
            return false;
        } else if (this.mPromise != null) {
            return true;
        } else {
            LOG.w("Promise is null");
            return false;
        }
    }

    synchronized WritableArray createWritableArray() {
        return new WritableNativeArray();
    }

    public WritableArray getArrayResult() {
        return this.mArrayResult;
    }

    public Promise getPromise() {
        return this.mPromise;
    }

    public int getWaitCount() {
        return this.mWaitCount;
    }

    public AtomicInteger getWaitCounter() {
        return this.mWaitCounter;
    }

    public boolean isResultSent() {
        return this.mResultSent;
    }

    public synchronized void notifyProblem(String str, String str2) {
        if (!isRequestValid()) {
            return;
        }
        CommsLogger commsLogger = LOG;
        commsLogger.e("Unexpected error occurred" + str2);
        this.mPromise.reject(str, str2);
        this.mResultSent = true;
    }

    public synchronized void notifySuccess(WritableMap writableMap) {
        if (!isRequestValid()) {
            return;
        }
        if (this.mIsMultiple) {
            this.mArrayResult.pushMap(writableMap);
            if (this.mWaitCounter.addAndGet(1) == this.mWaitCount) {
                this.mPromise.resolve(this.mArrayResult);
                this.mResultSent = true;
            }
        } else {
            this.mPromise.resolve(writableMap);
            this.mResultSent = true;
        }
    }

    public synchronized void setWaitCount(int i) {
        this.mWaitCount = i;
        this.mWaitCounter = new AtomicInteger(0);
    }

    public synchronized void setup(Promise promise, boolean z) {
        this.mPromise = promise;
        this.mIsMultiple = z;
        this.mResultSent = false;
        this.mWaitCount = 0;
        this.mWaitCounter = new AtomicInteger(0);
        if (z) {
            this.mArrayResult = createWritableArray();
        }
    }

    public synchronized void notifyProblem(BridgeError bridgeError) {
        if (!isRequestValid()) {
            return;
        }
        CommsLogger commsLogger = LOG;
        commsLogger.e("Unexpected error occurred" + bridgeError.message());
        this.mPromise.reject(bridgeError.code(), bridgeError.message());
        this.mResultSent = true;
    }

    public synchronized void notifyProblem(String str, Throwable th) {
        if (!isRequestValid()) {
            return;
        }
        CommsLogger commsLogger = LOG;
        commsLogger.e("Unexpected error occurred." + th.getMessage(), th);
        this.mPromise.reject(str, th);
        this.mResultSent = true;
    }
}
