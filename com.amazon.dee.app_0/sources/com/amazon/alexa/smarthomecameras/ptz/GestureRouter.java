package com.amazon.alexa.smarthomecameras.ptz;

import android.util.Log;
import com.amazon.alexa.smarthomecameras.util.CamerasLogger;
import com.amazon.ptz.digital.DigitalPtzGestureHandler;
import com.amazon.ptz.gestures.Gesture;
import com.amazon.ptz.gestures.GestureType;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
/* loaded from: classes10.dex */
public class GestureRouter implements GestureHandler {
    private static final String TAG = "GestureRouter";
    @Nonnull
    private final DigitalPtzGestureHandler digitalPtzGestureHandler;
    @Nonnull
    protected final PtzGestureListener listener;
    @Nonnull
    private final PhysicalPtzGestureHandler physicalPtzGestureHandler;

    public GestureRouter(DigitalPtzGestureHandler digitalPtzGestureHandler, PhysicalPtzGestureHandler physicalPtzGestureHandler, PtzGestureListener ptzGestureListener) {
        Preconditions.checkNotNull(digitalPtzGestureHandler, "dPTZGestureHandler cannot be null");
        Preconditions.checkNotNull(digitalPtzGestureHandler, "pPTZGestureHandler cannot be null");
        Preconditions.checkNotNull(ptzGestureListener, "PtzGestureListener cannot be null");
        this.digitalPtzGestureHandler = digitalPtzGestureHandler;
        this.physicalPtzGestureHandler = physicalPtzGestureHandler;
        this.listener = ptzGestureListener;
    }

    @Override // com.amazon.ptz.gestures.handlers.GestureHandler
    public boolean canHandle(Gesture gesture) {
        return true;
    }

    @Override // com.amazon.ptz.gestures.handlers.GestureHandler
    public void handle(Gesture gesture) {
        GestureType gestureType = gesture.getGestureType();
        String str = TAG;
        Log.i(str, "Handling a gesture of type: " + gestureType);
        if (this.digitalPtzGestureHandler.canHandle(gesture)) {
            this.digitalPtzGestureHandler.handle(gesture);
        } else if (this.physicalPtzGestureHandler.canHandle(gesture)) {
            CamerasLogger.logInfo(CamerasLogger.LVC_TAG, "Physical gesture stopped");
            this.physicalPtzGestureHandler.handle(gesture);
        } else {
            String str2 = TAG;
            Log.i(str2, "Gesture of type " + gestureType + " could not be routed.");
            return;
        }
        this.listener.onGestureChanged(gestureType);
    }
}
