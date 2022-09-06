package com.here.sdk.search;

import androidx.annotation.Nullable;
import com.here.NativeBase;
import java.util.List;
/* loaded from: classes3.dex */
class SearchCallbackImpl extends NativeBase implements SearchCallback {
    protected SearchCallbackImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.search.SearchCallbackImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                SearchCallbackImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.search.SearchCallback
    public native void onSearchCompleted(@Nullable SearchError searchError, @Nullable List<Place> list);
}
