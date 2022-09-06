package com.amazon.identity.auth.device.api;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface MAPFuture<T> {
    @FireOsSdk
    T get() throws MAPCallbackErrorException, InterruptedException, ExecutionException;

    @FireOsSdk
    T get(long j, TimeUnit timeUnit) throws MAPCallbackErrorException, InterruptedException, ExecutionException, TimeoutException;
}
