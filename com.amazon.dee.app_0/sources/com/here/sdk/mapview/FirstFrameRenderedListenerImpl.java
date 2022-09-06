package com.here.sdk.mapview;

import com.here.NativeBase;
/* loaded from: classes3.dex */
class FirstFrameRenderedListenerImpl extends NativeBase implements FirstFrameRenderedListener {
    protected FirstFrameRenderedListenerImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.FirstFrameRenderedListenerImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                FirstFrameRenderedListenerImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.mapview.FirstFrameRenderedListener
    public native void onFirstFrameRendered();
}
