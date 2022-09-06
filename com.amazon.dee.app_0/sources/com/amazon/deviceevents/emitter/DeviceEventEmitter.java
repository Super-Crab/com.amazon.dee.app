package com.amazon.deviceevents.emitter;

import com.amazon.deviceevents.model.DeviceEvent;
import com.amazon.deviceevents.model.DeviceEventType;
import com.amazon.deviceevents.model.event.UplMetric;
import com.amazon.deviceevents.utils.Preconditions;
/* loaded from: classes12.dex */
public abstract class DeviceEventEmitter implements IDeviceEventEmitter {
    public static final String TAG = "DeviceEventEmitter";

    protected abstract void emit(DeviceEvent deviceEvent);

    @Override // com.amazon.deviceevents.emitter.IDeviceEventEmitter
    public void emitUplMetric(UplMetric uplMetric) {
        Preconditions.checkNotNull(uplMetric, "metric");
        emit(new DeviceEvent(DeviceEventType.UPL_METRIC, uplMetric));
    }
}
