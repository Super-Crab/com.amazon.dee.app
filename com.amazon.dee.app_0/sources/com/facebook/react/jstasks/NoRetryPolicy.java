package com.facebook.react.jstasks;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public class NoRetryPolicy implements HeadlessJsTaskRetryPolicy {
    public static final NoRetryPolicy INSTANCE = new NoRetryPolicy();

    private NoRetryPolicy() {
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public boolean canRetry() {
        return false;
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public HeadlessJsTaskRetryPolicy copy() {
        return this;
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public int getDelay() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Should not retrieve delay as canRetry is: ");
        outline107.append(canRetry());
        throw new IllegalStateException(outline107.toString());
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public HeadlessJsTaskRetryPolicy update() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Should not update as canRetry is: ");
        outline107.append(canRetry());
        throw new IllegalStateException(outline107.toString());
    }
}
