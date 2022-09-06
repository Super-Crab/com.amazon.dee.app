package com.facebook.react.bridge;
/* loaded from: classes2.dex */
interface ReactCallback {
    void decrementPendingJSCalls();

    void incrementPendingJSCalls();

    void onBatchComplete();
}
