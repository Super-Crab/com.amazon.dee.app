package com.amazon.dee.sdk.iotsoftap;

import com.amazon.dee.sdk.iotsoftap.impl.SmartHomeIoTSoftApPresenterImpl;
import com.facebook.react.bridge.ReactApplicationContext;
/* loaded from: classes12.dex */
public final class IoTSoftApPresenterFactory {
    private IoTSoftApPresenterFactory() {
    }

    public static IoTSoftApPresenter getIoTSoftApPresenter(ReactApplicationContext reactApplicationContext, IoTSoftApConfigurationProvider ioTSoftApConfigurationProvider, IoTSoftApResponseCallback ioTSoftApResponseCallback) {
        if (reactApplicationContext.getCurrentActivity() != null) {
            return new SmartHomeIoTSoftApPresenterImpl(reactApplicationContext.getCurrentActivity(), ioTSoftApConfigurationProvider, ioTSoftApResponseCallback);
        }
        return null;
    }
}
