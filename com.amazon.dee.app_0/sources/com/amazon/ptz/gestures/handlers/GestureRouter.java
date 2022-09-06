package com.amazon.ptz.gestures.handlers;

import com.amazon.ptz.digital.DigitalPtzGestureHandler;
import com.amazon.ptz.gestures.Gesture;
import com.amazon.ptz.gestures.GestureType;
import com.amazon.ptz.physical.PhysicalPtzGestureHandler;
import com.amazon.ptz.util.LogTag;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.annotation.Nonnull;
import lombok.Generated;
/* loaded from: classes13.dex */
public class GestureRouter implements GestureHandler {
    private static final String TAG = LogTag.forClass(GestureRouter.class);
    @Nonnull
    private final DigitalPtzGestureHandler digitalPtzGestureHandler;
    @Nonnull
    private final PhysicalPtzGestureHandler physicalPtzGestureHandler;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public GestureRouter(@Nonnull DigitalPtzGestureHandler digitalPtzGestureHandler, @Nonnull PhysicalPtzGestureHandler physicalPtzGestureHandler) {
        if (digitalPtzGestureHandler != null) {
            if (physicalPtzGestureHandler == null) {
                throw new IllegalArgumentException("physicalPtzGestureHandler is null");
            }
            this.digitalPtzGestureHandler = digitalPtzGestureHandler;
            this.physicalPtzGestureHandler = physicalPtzGestureHandler;
            return;
        }
        throw new IllegalArgumentException("digitalPtzGestureHandler is null");
    }

    @Override // com.amazon.ptz.gestures.handlers.GestureHandler
    public boolean canHandle(Gesture gesture) {
        return true;
    }

    @Override // com.amazon.ptz.gestures.handlers.GestureHandler
    public void handle(Gesture gesture) {
        GestureType gestureType = gesture.getGestureType();
        String str = "Handling a gesture of type: " + gestureType;
        if (this.digitalPtzGestureHandler.canHandle(gesture)) {
            this.digitalPtzGestureHandler.handle(gesture);
        } else if (this.physicalPtzGestureHandler.canHandle(gesture)) {
            this.physicalPtzGestureHandler.handle(gesture);
        } else {
            String str2 = "Gesture of type " + gestureType + " could not be routed.";
        }
    }
}
