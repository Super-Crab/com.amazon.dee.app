package org.reactnative.camera.events;

import androidx.core.util.Pools;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import org.reactnative.camera.CameraViewManager;
/* loaded from: classes5.dex */
public class TouchEvent extends Event<TouchEvent> {
    private static final Pools.SynchronizedPool<TouchEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private boolean mIsDoubleTap;
    private int mX;
    private int mY;

    private TouchEvent() {
    }

    private void init(int i, boolean z, int i2, int i3) {
        super.init(i);
        this.mX = i2;
        this.mY = i3;
        this.mIsDoubleTap = z;
    }

    public static TouchEvent obtain(int i, boolean z, int i2, int i3) {
        TouchEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new TouchEvent();
        }
        acquire.init(i, z, i2, i3);
        return acquire;
    }

    private WritableMap serializeEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("target", getViewTag());
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putInt(ReactProperties.HereMapMarker.X, this.mX);
        createMap2.putInt(ReactProperties.HereMapMarker.Y, this.mY);
        createMap.putBoolean("isDoubleTap", this.mIsDoubleTap);
        createMap.putMap("touchOrigin", createMap2);
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
        return CameraViewManager.Events.EVENT_ON_TOUCH.toString();
    }
}
