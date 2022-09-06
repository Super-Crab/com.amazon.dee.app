package com.amazon.whisperjoin.softap.callbacks.errors;
/* loaded from: classes13.dex */
public interface ProvisioningErrorHandler {
    void handleConnectionError(Throwable th);

    void handleIOError(Throwable th);
}
