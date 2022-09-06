package com.amazon.alexa.smarthomecameras.handlers;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.amazon.alexa.smarthomecameras.ptz.PtzGestureListener;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.gestures.listeners.OldScaleGestureDetector;
import com.amazon.ptz.gestures.listeners.PtzListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes10.dex */
public class LiveViewPtzListener extends PtzListener {
    private static final String TAG = LiveViewPtzListener.class.getSimpleName();
    private final PtzGestureListener listener;

    public LiveViewPtzListener(GestureHandler gestureHandler, GestureDetector gestureDetector, OldScaleGestureDetector oldScaleGestureDetector, Context context, PtzGestureListener ptzGestureListener) {
        super(gestureHandler, gestureDetector, oldScaleGestureDetector, context);
        this.listener = ptzGestureListener;
    }

    @Override // com.amazon.ptz.gestures.listeners.PtzListener, android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onTouch with event ");
        outline107.append(motionEvent.getAction());
        outline107.toString();
        boolean onTouch = super.onTouch(view, motionEvent);
        if (motionEvent.getAction() != 2) {
            this.listener.onTouch();
        }
        return onTouch;
    }
}
