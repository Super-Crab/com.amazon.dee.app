package com.amazon.whisperjoin.softap.callbacks;
/* loaded from: classes13.dex */
public interface OnDeviceDetailsFetchedCallback<T> {
    void onDeviceDetailsFetched(T t);

    void unableToFetchDeviceDetails(Throwable th);
}
