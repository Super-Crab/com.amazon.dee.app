package com.amazon.blueshift.bluefront.android.task;
/* loaded from: classes11.dex */
public class Progress<T> {
    private final Object mPayload;
    private final T mState;

    public Progress(T t) {
        this(t, null);
    }

    public Object getPayload() {
        return this.mPayload;
    }

    public T getState() {
        return this.mState;
    }

    public Progress(T t, Object obj) {
        this.mState = t;
        this.mPayload = obj;
    }
}
