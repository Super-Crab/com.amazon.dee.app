package com.amazon.deviceevents.serialization;

import com.amazon.deviceevents.com.google.gson.JsonIOException;
import com.amazon.deviceevents.com.google.gson.JsonSyntaxException;
import com.amazon.deviceevents.exceptions.DeviceEventSerializationException;
import com.amazon.deviceevents.model.DeviceEvent;
import com.amazon.deviceevents.utils.GsonSingleton;
import com.amazon.deviceevents.utils.Preconditions;
/* loaded from: classes12.dex */
final class DeviceEventMapper implements IDeviceEventMapper {
    private final IGson mGson;

    public DeviceEventMapper() {
        this(GsonSingleton.getInstance());
    }

    @Override // com.amazon.deviceevents.serialization.IDeviceEventMapper
    public DeviceEvent deserialize(String str) throws DeviceEventSerializationException {
        Preconditions.checkNotNull(str, "text");
        try {
            return (DeviceEvent) this.mGson.fromJson(str, DeviceEvent.class);
        } catch (JsonSyntaxException e) {
            throw new DeviceEventSerializationException(e.getMessage());
        }
    }

    @Override // com.amazon.deviceevents.serialization.IDeviceEventMapper
    public String serialize(DeviceEvent deviceEvent) throws DeviceEventSerializationException {
        Preconditions.checkNotNull(deviceEvent, "deviceEvent");
        try {
            return this.mGson.toJson(deviceEvent);
        } catch (JsonIOException e) {
            throw new DeviceEventSerializationException(e.getMessage());
        }
    }

    DeviceEventMapper(IGson iGson) {
        Preconditions.checkNotNull(iGson, "gson");
        this.mGson = iGson;
    }
}
