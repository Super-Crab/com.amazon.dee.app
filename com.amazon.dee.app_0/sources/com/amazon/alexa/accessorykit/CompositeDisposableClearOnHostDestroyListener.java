package com.amazon.alexa.accessorykit;

import com.facebook.react.bridge.LifecycleEventListener;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
/* loaded from: classes6.dex */
public final class CompositeDisposableClearOnHostDestroyListener implements LifecycleEventListener {
    private final CompositeDisposable compositeDisposable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompositeDisposableClearOnHostDestroyListener(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        this.compositeDisposable.clear();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }
}
