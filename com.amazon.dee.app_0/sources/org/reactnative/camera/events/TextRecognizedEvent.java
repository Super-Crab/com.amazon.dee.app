package org.reactnative.camera.events;

import androidx.core.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import org.reactnative.camera.CameraViewManager;
/* loaded from: classes5.dex */
public class TextRecognizedEvent extends Event<TextRecognizedEvent> {
    private static final Pools.SynchronizedPool<TextRecognizedEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private WritableArray mData;

    private TextRecognizedEvent() {
    }

    private WritableMap createEvent() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("type", "textBlock");
        createMap.putArray("textBlocks", this.mData);
        createMap.putInt("target", getViewTag());
        return createMap;
    }

    private void init(int i, WritableArray writableArray) {
        super.init(i);
        this.mData = writableArray;
    }

    public static TextRecognizedEvent obtain(int i, WritableArray writableArray) {
        TextRecognizedEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new TextRecognizedEvent();
        }
        acquire.init(i, writableArray);
        return acquire;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), createEvent());
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_TEXT_RECOGNIZED.toString();
    }
}
