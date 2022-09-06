package com.amazon.alexa.accessory.repositories.device.v2;

import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.repositories.device.DeviceFeatures;
import com.amazon.alexa.accessory.repositories.device.DeviceRepository;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.Set;
/* loaded from: classes6.dex */
public interface DeviceRepositoryV2 extends DeviceRepository {
    Single<DeviceFeatures> queryDeviceFeatures();

    Observable<Set<Device.DeviceInformation>> queryDeviceInformationSet();

    Single<Common.ErrorCode> requestUpdateDeviceInformation(String str, int i);
}
