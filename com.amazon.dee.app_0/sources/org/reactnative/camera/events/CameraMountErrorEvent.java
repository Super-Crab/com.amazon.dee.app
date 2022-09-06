package org.reactnative.camera.events;

import androidx.core.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import org.reactnative.camera.CameraViewManager;
/* loaded from: classes5.dex */
public class CameraMountErrorEvent extends Event<CameraMountErrorEvent> {
    private static final Pools.SynchronizedPool<CameraMountErrorEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private String mError;

    private CameraMountErrorEvent() {
    }

    private void init(int i, String str) {
        super.init(i);
        this.mError = str;
    }

    public static CameraMountErrorEvent obtain(int i, String str) {
        CameraMountErrorEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new CameraMountErrorEvent();
        }
        acquire.init(i, str);
        return acquire;
    }

    private WritableMap serializeEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("message", this.mError);
        return createMap;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) 0;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_MOUNT_ERROR.toString();
    }
}
