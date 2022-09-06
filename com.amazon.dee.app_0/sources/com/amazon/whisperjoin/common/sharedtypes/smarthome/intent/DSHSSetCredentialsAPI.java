package com.amazon.whisperjoin.common.sharedtypes.smarthome.intent;

import com.amazon.whisperjoin.common.sharedtypes.smarthome.data.BluetoothFFSEntry;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.data.ZigbeeFFSEntry;
/* loaded from: classes13.dex */
public interface DSHSSetCredentialsAPI {
    void setCredentials(ZigbeeFFSEntry zigbeeFFSEntry);

    void setCredentials(ZigbeeFFSEntry zigbeeFFSEntry, BluetoothFFSEntry bluetoothFFSEntry);

    void setCredentialsV2(String str);
}
