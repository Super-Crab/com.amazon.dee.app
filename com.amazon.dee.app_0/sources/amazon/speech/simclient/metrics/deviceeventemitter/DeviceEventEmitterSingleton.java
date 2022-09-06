package amazon.speech.simclient.metrics.deviceeventemitter;

import com.amazon.deviceevents.emitter.IDeviceEventEmitter;
/* loaded from: classes.dex */
public final class DeviceEventEmitterSingleton {
    private static final IDeviceEventEmitter DEVICE_EVENT_EMITTER = new AndroidDeviceEventEmitter();

    public static IDeviceEventEmitter getDeviceEventEmitter() {
        return DEVICE_EVENT_EMITTER;
    }
}
