package com.swmansion.gesturehandler;
/* loaded from: classes3.dex */
public abstract class BaseGestureHandlerInteractionController implements GestureHandlerInteractionController {
    @Override // com.swmansion.gesturehandler.GestureHandlerInteractionController
    public boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return false;
    }

    @Override // com.swmansion.gesturehandler.GestureHandlerInteractionController
    public boolean shouldRequireHandlerToWaitForFailure(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return false;
    }

    @Override // com.swmansion.gesturehandler.GestureHandlerInteractionController
    public boolean shouldWaitForHandlerFailure(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return false;
    }
}
