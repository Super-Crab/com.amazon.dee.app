package com.amazon.alexa.device.setup.echo.softap.wifi;

import android.content.Context;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.service.api.LazyComponent;
/* loaded from: classes6.dex */
public final class SoftAPWifiManagerFactory {
    private SoftAPWifiManagerFactory() {
    }

    public static SoftAPWifiManager getSoftAPWifiManager(Context context, LazyComponent<IdentityService> lazyComponent) {
        return new SoftAPWifiManager(context, lazyComponent);
    }
}
