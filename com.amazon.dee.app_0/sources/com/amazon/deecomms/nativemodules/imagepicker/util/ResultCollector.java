package com.amazon.deecomms.nativemodules.imagepicker.util;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes12.dex */
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

    public synchronized void notifyProblem(String str, String str2) {
        if (!isRequestValid()) {
            return;
        }
        GeneratedOutlineSupport.outline3("Unexpected error occurred", str2, LOG);
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
            this.mArrayResult = new WritableNativeArray();
        }
    }

    public synchronized void notifyProblem(String str, Throwable th) {
        if (!isRequestValid()) {
            return;
        }
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Unexpected error occurred.");
        outline1.append(th.getMessage());
        commsLogger.e(outline1.toString(), th);
        this.mPromise.reject(str, th);
        this.mResultSent = true;
    }
}
