package com.here.sdk.core.threading;

import com.here.NativeBase;
/* loaded from: classes3.dex */
class TaskHandleImpl extends NativeBase implements TaskHandle {
    protected TaskHandleImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.threading.TaskHandleImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                TaskHandleImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.core.threading.TaskHandle
    public native boolean cancel();

    @Override // com.here.sdk.core.threading.TaskHandle
    public native boolean isCancelled();

    @Override // com.here.sdk.core.threading.TaskHandle
    public native boolean isFinished();
}
