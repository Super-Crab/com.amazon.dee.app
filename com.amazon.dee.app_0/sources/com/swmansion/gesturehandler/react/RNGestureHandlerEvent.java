package com.swmansion.gesturehandler.react;

import androidx.annotation.Nullable;
import androidx.core.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.swmansion.gesturehandler.GestureHandler;
/* loaded from: classes3.dex */
public class RNGestureHandlerEvent extends Event<RNGestureHandlerEvent> {
    private static final Pools.SynchronizedPool<RNGestureHandlerEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(7);
    public static final String EVENT_NAME = "onGestureHandlerEvent";
    private static final int TOUCH_EVENTS_POOL_SIZE = 7;
    private short mCoalescingKey;
    private WritableMap mExtraData;

    private RNGestureHandlerEvent() {
    }

    private void init(GestureHandler gestureHandler, @Nullable RNGestureHandlerEventDataExtractor rNGestureHandlerEventDataExtractor) {
        super.init(gestureHandler.getView().getId());
        this.mExtraData = Arguments.createMap();
        if (rNGestureHandlerEventDataExtractor != null) {
            rNGestureHandlerEventDataExtractor.extractEventData(gestureHandler, this.mExtraData);
        }
        this.mExtraData.putInt("handlerTag", gestureHandler.getTag());
        this.mExtraData.putInt("state", gestureHandler.getState());
        this.mCoalescingKey = gestureHandler.getEventCoalescingKey();
    }

    public static RNGestureHandlerEvent obtain(GestureHandler gestureHandler, @Nullable RNGestureHandlerEventDataExtractor rNGestureHandlerEventDataExtractor) {
        RNGestureHandlerEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new RNGestureHandlerEvent();
        }
        acquire.init(gestureHandler, rNGestureHandlerEventDataExtractor);
        return acquire;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return true;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), EVENT_NAME, this.mExtraData);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return this.mCoalescingKey;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void onDispose() {
        this.mExtraData = null;
        EVENTS_POOL.release(this);
    }
}
