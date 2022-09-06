package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.Nullable;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes6.dex */
public abstract class AlexaApiCallbacks {
    private final AtomicBoolean callbackMethodCalled;
    private final String id;
    @Nullable
    private final TimeUnit timeoutUnit;
    @Nullable
    private final Long timeoutValue;

    /* JADX INFO: Access modifiers changed from: protected */
    public AlexaApiCallbacks() {
        this(null, null);
    }

    protected AlexaApiCallbacks(@Nullable Long l, @Nullable TimeUnit timeUnit) {
        this.timeoutValue = l;
        this.timeoutUnit = timeUnit;
        this.id = UUID.randomUUID().toString();
        this.callbackMethodCalled = new AtomicBoolean(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void doOnFailure(ApiCallFailure apiCallFailure) {
        if (this.callbackMethodCalled.compareAndSet(false, true)) {
            onFailure(apiCallFailure);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void doOnSuccess() {
        if (this.callbackMethodCalled.compareAndSet(false, true)) {
            onSuccess();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getId() {
        return this.id;
    }

    @Nullable
    public TimeUnit getTimeoutUnit() {
        return this.timeoutUnit;
    }

    @Nullable
    public Long getTimeoutValue() {
        return this.timeoutValue;
    }

    public void onFailure(ApiCallFailure apiCallFailure) {
    }

    public void onSuccess() {
    }
}
