package com.amazon.alexa.accessory.internal.bluetooth;

import android.os.ParcelUuid;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
/* loaded from: classes.dex */
public interface SdpRepository {

    /* loaded from: classes.dex */
    public interface FetchListener {
        void onError(PeripheralDevice peripheralDevice, Throwable th);

        void onSuccess(PeripheralDevice peripheralDevice, SdpRecords sdpRecords);
    }

    void cancelFetch(FetchListener fetchListener);

    void fetch(PeripheralDevice peripheralDevice, FetchListener fetchListener);

    SdpRecords getLocal(PeripheralDevice peripheralDevice);

    void getOrFetch(PeripheralDevice peripheralDevice, FetchListener fetchListener, ParcelUuid parcelUuid);
}
