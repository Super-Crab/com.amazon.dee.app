package com.amazon.alexa.accessory.repositories.device;

import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface DeviceRepository {
    Observable<Device.DeviceConfiguration> queryDeviceConfiguration();

    @Deprecated
    Observable<Device.DeviceInformation> queryDeviceInformation();

    Single<Common.ErrorCode> requestCompleteSetup(boolean z);

    Single<Common.ErrorCode> requestOverrideAssistant(boolean z);

    Single<Common.ErrorCode> requestStartSetup();

    @Deprecated
    Single<Common.ErrorCode> requestUpdateDeviceInformation(String str);
}
