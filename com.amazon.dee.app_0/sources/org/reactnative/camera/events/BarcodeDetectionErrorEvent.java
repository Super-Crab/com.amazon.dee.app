package org.reactnative.camera.events;

import androidx.core.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import org.reactnative.barcodedetector.RNBarcodeDetector;
import org.reactnative.camera.CameraViewManager;
/* loaded from: classes5.dex */
public class BarcodeDetectionErrorEvent extends Event<BarcodeDetectionErrorEvent> {
    private static final Pools.SynchronizedPool<BarcodeDetectionErrorEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private RNBarcodeDetector mBarcodeDetector;

    private BarcodeDetectionErrorEvent() {
    }

    private void init(int i, RNBarcodeDetector rNBarcodeDetector) {
        super.init(i);
        this.mBarcodeDetector = rNBarcodeDetector;
    }

    public static BarcodeDetectionErrorEvent obtain(int i, RNBarcodeDetector rNBarcodeDetector) {
        BarcodeDetectionErrorEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new BarcodeDetectionErrorEvent();
        }
        acquire.init(i, rNBarcodeDetector);
        return acquire;
    }

    private WritableMap serializeEventData() {
        WritableMap createMap = Arguments.createMap();
        RNBarcodeDetector rNBarcodeDetector = this.mBarcodeDetector;
        createMap.putBoolean("isOperational", rNBarcodeDetector != null && rNBarcodeDetector.isOperational());
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
        return CameraViewManager.Events.EVENT_ON_BARCODE_DETECTION_ERROR.toString();
    }
}
