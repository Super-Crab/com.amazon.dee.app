package com.here.sdk.search;

import androidx.annotation.Nullable;
import com.here.NativeBase;
import java.util.List;
/* loaded from: classes3.dex */
class SuggestCallbackImpl extends NativeBase implements SuggestCallback {
    protected SuggestCallbackImpl(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.search.SuggestCallbackImpl.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                SuggestCallbackImpl.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @Override // com.here.sdk.search.SuggestCallback
    public native void onSuggestCompleted(@Nullable SearchError searchError, @Nullable List<Suggestion> list);
}
