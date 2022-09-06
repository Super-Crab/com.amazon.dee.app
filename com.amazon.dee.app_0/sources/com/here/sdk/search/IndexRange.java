package com.here.sdk.search;

import com.here.NativeBase;
/* loaded from: classes3.dex */
public final class IndexRange extends NativeBase {
    protected IndexRange(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.search.IndexRange.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                IndexRange.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    public native int getEnd();

    public native int getStart();
}
