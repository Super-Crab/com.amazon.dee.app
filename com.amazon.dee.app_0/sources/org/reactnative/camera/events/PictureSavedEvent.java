package org.reactnative.camera.events;

import androidx.core.util.Pools;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import org.reactnative.camera.CameraViewManager;
/* loaded from: classes5.dex */
public class PictureSavedEvent extends Event<PictureSavedEvent> {
    private static final Pools.SynchronizedPool<PictureSavedEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(5);
    private WritableMap mResponse;

    private PictureSavedEvent() {
    }

    private void init(int i, WritableMap writableMap) {
        super.init(i);
        this.mResponse = writableMap;
    }

    public static PictureSavedEvent obtain(int i, WritableMap writableMap) {
        PictureSavedEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new PictureSavedEvent();
        }
        acquire.init(i, writableMap);
        return acquire;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), this.mResponse);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) (this.mResponse.mo6945getMap("data").getString("uri").hashCode() % 32767);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_PICTURE_SAVED.toString();
    }
}
