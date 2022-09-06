package org.reactnative.camera.events;

import androidx.core.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import org.reactnative.camera.CameraViewManager;
import org.reactnative.facedetector.RNFaceDetector;
/* loaded from: classes5.dex */
public class FaceDetectionErrorEvent extends Event<FaceDetectionErrorEvent> {
    private static final Pools.SynchronizedPool<FaceDetectionErrorEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private RNFaceDetector mFaceDetector;

    private FaceDetectionErrorEvent() {
    }

    private void init(int i, RNFaceDetector rNFaceDetector) {
        super.init(i);
        this.mFaceDetector = rNFaceDetector;
    }

    public static FaceDetectionErrorEvent obtain(int i, RNFaceDetector rNFaceDetector) {
        FaceDetectionErrorEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new FaceDetectionErrorEvent();
        }
        acquire.init(i, rNFaceDetector);
        return acquire;
    }

    private WritableMap serializeEventData() {
        WritableMap createMap = Arguments.createMap();
        RNFaceDetector rNFaceDetector = this.mFaceDetector;
        createMap.putBoolean("isOperational", rNFaceDetector != null && rNFaceDetector.isOperational());
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
        return CameraViewManager.Events.EVENT_ON_FACE_DETECTION_ERROR.toString();
    }
}
