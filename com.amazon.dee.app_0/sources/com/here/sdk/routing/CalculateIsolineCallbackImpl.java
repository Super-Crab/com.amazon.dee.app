package com.here.sdk.routing;

import androidx.annotation.Nullable;
import com.here.NativeBase;
import java.util.List;
/* loaded from: classes3.dex */
class CalculateIsolineCallbackImpl extends NativeBase implements CalculateIsolineCallback {
    protected CalculateIsolineCallbackImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.routing.CalculateIsolineCallbackImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                CalculateIsolineCallbackImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.routing.CalculateIsolineCallback
    public native void onIsolineCalculated(@Nullable RoutingError routingError, @Nullable List<Isoline> list);
}
