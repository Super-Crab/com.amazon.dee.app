package com.reactnativecommunity.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes3.dex */
class PageScrollStateChangedEvent extends Event<PageScrollStateChangedEvent> {
    public static final String EVENT_NAME = "topPageScrollStateChanged";
    private final String mPageScrollState;

    /* JADX INFO: Access modifiers changed from: protected */
    public PageScrollStateChangedEvent(int i, String str) {
        super(i);
        this.mPageScrollState = str;
    }

    private WritableMap serializeEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("pageScrollState", this.mPageScrollState);
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
