package com.amazon.whisperjoin.softap.callbacks.errors;

import com.amazon.whisperjoin.softap.callbacks.DeviceConnectionStateChangedListener;
/* loaded from: classes13.dex */
public class UnexpectedDisconnectionFailureHandler implements DeviceConnectionStateChangedListener {
    private final ProvisioningErrorHandler provisioningErrorHandler;

    public UnexpectedDisconnectionFailureHandler(ProvisioningErrorHandler provisioningErrorHandler) {
        this.provisioningErrorHandler = provisioningErrorHandler;
    }

    @Override // com.amazon.whisperjoin.softap.callbacks.DeviceConnectionStateChangedListener
    public void onConnectionLost(Throwable th) {
        this.provisioningErrorHandler.handleConnectionError(th);
    }
}
