package com.amazon.alexa.devicesetup.sdk.whisperjoin.zerotouch;

import android.content.Context;
import com.amazon.alexa.devicesetup.sdk.whisperjoin.FFSConfigurationProvider;
/* loaded from: classes7.dex */
public final class BackgroundProvisioningServiceFactory {
    private BackgroundProvisioningServiceFactory() {
    }

    public static BackgroundProvisioningService getBackgroundProvisioning(Context context, FFSConfigurationProvider fFSConfigurationProvider) {
        return new BackgroundProvisioningService(context, fFSConfigurationProvider);
    }
}
