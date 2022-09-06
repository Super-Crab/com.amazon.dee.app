package com.amazon.deviceevents.model;

import com.amazon.deviceevents.com.google.gson.annotations.SerializedName;
import com.amazon.deviceevents.utils.GsonSingleton;
import com.amazon.deviceevents.utils.Preconditions;
/* loaded from: classes12.dex */
public final class DeviceEvent {
    @SerializedName("event")
    private Object mEvent;
    @SerializedName("eventType")
    private DeviceEventType mEventType;

    public DeviceEvent(DeviceEventType deviceEventType, Object obj) {
        Preconditions.checkNotNull(deviceEventType, "deviceEventType");
        Preconditions.checkNotNull(obj, "event");
        this.mEventType = deviceEventType;
        this.mEvent = obj;
    }

    public Object getEvent() {
        return this.mEvent;
    }

    public DeviceEventType getEventType() {
        return this.mEventType;
    }

    public <T> T getEvent(Class<T> cls) {
        return (T) GsonSingleton.castObjectTo(getEvent(), cls);
    }

    private DeviceEvent() {
    }
}
