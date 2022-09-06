package com.amazon.alexa.smarthomecameras.ptz;

import com.amazon.ptz.gestures.GestureType;
/* loaded from: classes10.dex */
public interface PtzGestureListener {
    void onGestureChanged(GestureType gestureType);

    void onTouch();
}
