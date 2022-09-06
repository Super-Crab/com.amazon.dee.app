package com.facebook.react.views.scroll;

import androidx.annotation.Nullable;
import androidx.core.util.Pools;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes2.dex */
public class ScrollEvent extends Event<ScrollEvent> {
    private static final Pools.SynchronizedPool<ScrollEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private int mContentHeight;
    private int mContentWidth;
    @Nullable
    private ScrollEventType mScrollEventType;
    private int mScrollViewHeight;
    private int mScrollViewWidth;
    private int mScrollX;
    private int mScrollY;
    private double mXVelocity;
    private double mYVelocity;

    private ScrollEvent() {
    }

    private void init(int i, ScrollEventType scrollEventType, int i2, int i3, float f, float f2, int i4, int i5, int i6, int i7) {
        super.init(i);
        this.mScrollEventType = scrollEventType;
        this.mScrollX = i2;
        this.mScrollY = i3;
        this.mXVelocity = f;
        this.mYVelocity = f2;
        this.mContentWidth = i4;
        this.mContentHeight = i5;
        this.mScrollViewWidth = i6;
        this.mScrollViewHeight = i7;
    }

    public static ScrollEvent obtain(int i, ScrollEventType scrollEventType, int i2, int i3, float f, float f2, int i4, int i5, int i6, int i7) {
        ScrollEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new ScrollEvent();
        }
        acquire.init(i, scrollEventType, i2, i3, f, f2, i4, i5, i6, i7);
        return acquire;
    }

    private WritableMap serializeEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble(ViewProps.TOP, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        createMap.putDouble(ViewProps.BOTTOM, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        createMap.putDouble("left", FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        createMap.putDouble("right", FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble(ReactProperties.HereMapMarker.X, PixelUtil.toDIPFromPixel(this.mScrollX));
        createMap2.putDouble(ReactProperties.HereMapMarker.Y, PixelUtil.toDIPFromPixel(this.mScrollY));
        WritableMap createMap3 = Arguments.createMap();
        createMap3.putDouble("width", PixelUtil.toDIPFromPixel(this.mContentWidth));
        createMap3.putDouble("height", PixelUtil.toDIPFromPixel(this.mContentHeight));
        WritableMap createMap4 = Arguments.createMap();
        createMap4.putDouble("width", PixelUtil.toDIPFromPixel(this.mScrollViewWidth));
        createMap4.putDouble("height", PixelUtil.toDIPFromPixel(this.mScrollViewHeight));
        WritableMap createMap5 = Arguments.createMap();
        createMap5.putDouble(ReactProperties.HereMapMarker.X, this.mXVelocity);
        createMap5.putDouble(ReactProperties.HereMapMarker.Y, this.mYVelocity);
        WritableMap createMap6 = Arguments.createMap();
        createMap6.putMap("contentInset", createMap);
        createMap6.putMap("contentOffset", createMap2);
        createMap6.putMap("contentSize", createMap3);
        createMap6.putMap("layoutMeasurement", createMap4);
        createMap6.putMap("velocity", createMap5);
        createMap6.putInt("target", getViewTag());
        createMap6.putBoolean("responderIgnoreScroll", true);
        return createMap6;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return this.mScrollEventType == ScrollEventType.SCROLL;
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
        return ScrollEventType.getJSEventName((ScrollEventType) Assertions.assertNotNull(this.mScrollEventType));
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void onDispose() {
        EVENTS_POOL.release(this);
    }
}
