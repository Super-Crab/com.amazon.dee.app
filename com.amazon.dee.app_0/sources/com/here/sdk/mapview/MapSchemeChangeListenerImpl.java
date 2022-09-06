package com.here.sdk.mapview;

import androidx.annotation.NonNull;
import com.here.NativeBase;
/* loaded from: classes3.dex */
class MapSchemeChangeListenerImpl extends NativeBase implements MapSchemeChangeListener {
    protected MapSchemeChangeListenerImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.mapview.MapSchemeChangeListenerImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                MapSchemeChangeListenerImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.mapview.MapSchemeChangeListener
    public native void beforeMapConfigChange(@NonNull String str);
}
