package com.amazon.deecomms.rx;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import rx.Subscriber;
/* loaded from: classes12.dex */
public abstract class SimpleSubscriber<T> extends Subscriber<T> {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SimpleSubscriber.class);
    private String errorMsg;

    public SimpleSubscriber() {
        this.errorMsg = "";
    }

    public abstract void onCall(T t);

    @Override // rx.Observer
    public void onCompleted() {
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        LOG.e(this.errorMsg, th);
    }

    @Override // rx.Observer
    public void onNext(T t) {
        if (!isUnsubscribed()) {
            onCall(t);
        }
    }

    public SimpleSubscriber(String str) {
        this.errorMsg = str;
    }
}
