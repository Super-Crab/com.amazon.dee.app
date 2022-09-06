package com.here.sdk.core.threading;

import com.here.NativeBase;
/* loaded from: classes3.dex */
class RunnableImpl extends NativeBase implements Runnable {
    protected RunnableImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.threading.RunnableImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                RunnableImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.core.threading.Runnable
    public native void run();
}
