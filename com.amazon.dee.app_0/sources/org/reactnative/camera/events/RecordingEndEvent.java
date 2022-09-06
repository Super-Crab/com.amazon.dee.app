package org.reactnative.camera.events;

import androidx.core.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import org.reactnative.camera.CameraViewManager;
/* loaded from: classes5.dex */
public class RecordingEndEvent extends Event<RecordingEndEvent> {
    private static final Pools.SynchronizedPool<RecordingEndEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);

    private RecordingEndEvent() {
    }

    public static RecordingEndEvent obtain(int i) {
        RecordingEndEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new RecordingEndEvent();
        }
        acquire.init(i);
        return acquire;
    }

    private WritableMap serializeEventData() {
        return Arguments.createMap();
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
        return CameraViewManager.Events.EVENT_ON_RECORDING_END.toString();
    }
}
