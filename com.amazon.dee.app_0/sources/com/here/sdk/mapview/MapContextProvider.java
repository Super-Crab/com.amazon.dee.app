package com.here.sdk.mapview;

import androidx.annotation.Nullable;
import com.here.NativeBase;
/* loaded from: classes3.dex */
class MapContextProvider extends NativeBase {
    protected MapContextProvider(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapContextProvider.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapContextProvider.disposeNativeHandle(j2);
            }
        });
    }

    static native void destroy();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static native MapContext getInstance();
}
