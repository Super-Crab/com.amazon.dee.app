package com.reactnativecommunity.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes3.dex */
class PageScrollEvent extends Event<PageScrollEvent> {
    public static final String EVENT_NAME = "topPageScroll";
    private final float mOffset;
    private final int mPosition;

    /* JADX INFO: Access modifiers changed from: protected */
    public PageScrollEvent(int i, int i2, float f) {
        super(i);
        this.mPosition = i2;
        this.mOffset = (Float.isInfinite(f) || Float.isNaN(f)) ? 0.0f : f;
    }

    private WritableMap serializeEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt(ViewProps.POSITION, this.mPosition);
        createMap.putDouble("offset", this.mOffset);
        return createMap;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }
}
