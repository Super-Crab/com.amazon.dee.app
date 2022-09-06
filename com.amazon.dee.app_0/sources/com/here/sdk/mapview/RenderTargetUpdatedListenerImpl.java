package com.here.sdk.mapview;

import com.here.NativeBase;
/* loaded from: classes3.dex */
class RenderTargetUpdatedListenerImpl extends NativeBase implements RenderTargetUpdatedListener {
    protected RenderTargetUpdatedListenerImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.RenderTargetUpdatedListenerImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                RenderTargetUpdatedListenerImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.mapview.RenderTargetUpdatedListener
    public native void onRenderTargetUpdated();
}
