package com.amazon.whisperjoin.softap.callbacks;
/* loaded from: classes13.dex */
public interface DeviceConnectionEstablishedCallback<T> {
    void onConnectionEstablished(T t);

    void unableToEstablishConnection(Throwable th);
}
