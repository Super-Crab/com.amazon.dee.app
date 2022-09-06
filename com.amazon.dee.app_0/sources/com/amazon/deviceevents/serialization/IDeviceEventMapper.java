package com.amazon.deviceevents.serialization;

import com.amazon.deviceevents.exceptions.DeviceEventSerializationException;
import com.amazon.deviceevents.model.DeviceEvent;
/* loaded from: classes12.dex */
public interface IDeviceEventMapper {
    DeviceEvent deserialize(String str) throws DeviceEventSerializationException;

    String serialize(DeviceEvent deviceEvent) throws DeviceEventSerializationException;
}
