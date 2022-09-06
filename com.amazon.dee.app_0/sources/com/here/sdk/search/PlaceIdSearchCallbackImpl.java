package com.here.sdk.search;

import androidx.annotation.Nullable;
import com.here.NativeBase;
/* loaded from: classes3.dex */
class PlaceIdSearchCallbackImpl extends NativeBase implements PlaceIdSearchCallback {
    protected PlaceIdSearchCallbackImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.search.PlaceIdSearchCallbackImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                PlaceIdSearchCallbackImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.search.PlaceIdSearchCallback
    public native void onPlaceIdSearchCompleted(@Nullable SearchError searchError, @Nullable Place place);
}
