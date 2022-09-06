package com.swmansion.gesturehandler;
/* loaded from: classes3.dex */
public interface GestureHandlerInteractionController {
    boolean shouldHandlerBeCancelledBy(GestureHandler gestureHandler, GestureHandler gestureHandler2);

    boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler, GestureHandler gestureHandler2);

    boolean shouldRequireHandlerToWaitForFailure(GestureHandler gestureHandler, GestureHandler gestureHandler2);

    boolean shouldWaitForHandlerFailure(GestureHandler gestureHandler, GestureHandler gestureHandler2);
}
