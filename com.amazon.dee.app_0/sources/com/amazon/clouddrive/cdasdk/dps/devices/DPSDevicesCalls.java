package com.amazon.clouddrive.cdasdk.dps.devices;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface DPSDevicesCalls {
    @NonNull
    Single<ListDevicesResponse> listDevices(@NonNull ListDevicesRequest listDevicesRequest);
}
