package com.amazon.alexa.accessorykit;

import com.facebook.react.bridge.LifecycleEventListener;
/* loaded from: classes6.dex */
public class ActionOnHostDestroyListener implements LifecycleEventListener {
    private final Runnable action;

    public ActionOnHostDestroyListener(Runnable runnable) {
        this.action = runnable;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        this.action.run();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }
}
