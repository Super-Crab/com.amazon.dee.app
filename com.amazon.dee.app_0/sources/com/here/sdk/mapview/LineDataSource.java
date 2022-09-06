package com.here.sdk.mapview;

import com.here.NativeBase;
/* loaded from: classes3.dex */
class LineDataSource extends NativeBase {
    protected LineDataSource(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.LineDataSource.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                LineDataSource.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);
}
