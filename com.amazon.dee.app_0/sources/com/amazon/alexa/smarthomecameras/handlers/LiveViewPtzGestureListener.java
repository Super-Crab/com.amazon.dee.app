package com.amazon.alexa.smarthomecameras.handlers;

import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.ptz.PtzEventListener;
import com.amazon.alexa.smarthomecameras.ptz.PtzGestureListener;
import com.amazon.ptz.gestures.GestureType;
/* loaded from: classes10.dex */
public class LiveViewPtzGestureListener implements PtzGestureListener {
    private static final String TAG = "LiveViewPtzGestureListener";
    private final DevicePayload devicePayload;
    private GestureType lastGestureType;
    private final PtzEventListener listener;

    /* renamed from: com.amazon.alexa.smarthomecameras.handlers.LiveViewPtzGestureListener$1  reason: invalid class name */
    /* loaded from: classes10.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ptz$gestures$GestureType = new int[GestureType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$ptz$gestures$GestureType[GestureType.ZOOM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ptz$gestures$GestureType[GestureType.PAN_TILT_DRAG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ptz$gestures$GestureType[GestureType.ZOOM_TOGGLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public LiveViewPtzGestureListener(PtzEventListener ptzEventListener, DevicePayload devicePayload) {
        this.listener = ptzEventListener;
        this.devicePayload = devicePayload;
    }

    @Override // com.amazon.alexa.smarthomecameras.ptz.PtzGestureListener
    public synchronized void onGestureChanged(GestureType gestureType) {
        String str = "onGestureChanged" + gestureType;
        this.lastGestureType = gestureType;
    }

    @Override // com.amazon.alexa.smarthomecameras.ptz.PtzGestureListener
    public synchronized void onTouch() {
        String str = "onTouch with last gesture: " + this.lastGestureType;
        if (this.lastGestureType == null) {
            return;
        }
        int ordinal = this.lastGestureType.ordinal();
        if (ordinal == 1) {
            this.listener.onPan(this.devicePayload.getManufacturerName());
        } else if (ordinal == 2) {
            this.listener.onPinchZoom(this.devicePayload.getManufacturerName());
        } else if (ordinal == 3) {
            this.listener.onDoubleTapZoom(this.devicePayload.getManufacturerName());
        }
        this.lastGestureType = null;
    }
}
