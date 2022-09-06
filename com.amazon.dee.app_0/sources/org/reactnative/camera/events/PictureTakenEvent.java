package org.reactnative.camera.events;

import androidx.core.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import org.reactnative.camera.CameraViewManager;
/* loaded from: classes5.dex */
public class PictureTakenEvent extends Event<PictureTakenEvent> {
    private static final Pools.SynchronizedPool<PictureTakenEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);

    private PictureTakenEvent() {
    }

    public static PictureTakenEvent obtain(int i) {
        PictureTakenEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new PictureTakenEvent();
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
        return CameraViewManager.Events.EVENT_ON_PICTURE_TAKEN.toString();
    }
}
