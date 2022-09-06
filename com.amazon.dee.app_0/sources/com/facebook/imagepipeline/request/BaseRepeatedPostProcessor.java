package com.facebook.imagepipeline.request;

import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public abstract class BaseRepeatedPostProcessor extends BasePostprocessor implements RepeatedPostprocessor {
    @Nullable
    private RepeatedPostprocessorRunner mCallback;

    @Nullable
    private synchronized RepeatedPostprocessorRunner getCallback() {
        return this.mCallback;
    }

    @Override // com.facebook.imagepipeline.request.RepeatedPostprocessor
    public synchronized void setCallback(RepeatedPostprocessorRunner runner) {
        this.mCallback = runner;
    }

    public void update() {
        RepeatedPostprocessorRunner callback = getCallback();
        if (callback != null) {
            callback.update();
        }
    }
}
