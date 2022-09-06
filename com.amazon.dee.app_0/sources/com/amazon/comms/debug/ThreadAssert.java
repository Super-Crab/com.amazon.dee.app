package com.amazon.comms.debug;

import android.os.Looper;
/* loaded from: classes11.dex */
public final class ThreadAssert {
    private Looper mLooper;

    public ThreadAssert() {
        this.mLooper = Looper.myLooper();
    }

    public static void expectMainThread() {
        DebugAssert.expect(Looper.myLooper() == Looper.getMainLooper(), "Holy Violated Expectation, Batman! Not the main thread!");
    }

    public void expectSameThread() {
        DebugAssert.expect(Looper.myLooper() == this.mLooper, "Holy Violated Expectation, Batman! Not the thread I was constructed on!");
    }

    public ThreadAssert(Looper looper) {
        this.mLooper = looper;
    }
}
