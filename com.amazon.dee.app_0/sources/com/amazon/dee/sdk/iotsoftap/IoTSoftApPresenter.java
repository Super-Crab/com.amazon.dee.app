package com.amazon.dee.sdk.iotsoftap;

import com.amazon.whisperjoin.softap.pojos.WifiConfiguration;
/* loaded from: classes12.dex */
public interface IoTSoftApPresenter {
    void connect(String str);

    void disconnect();

    void provisionDevice(WifiConfiguration wifiConfiguration);
}
