package com.dee.app.data.reactnative.bridges;

import android.util.Log;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.dee.app.data.reactnative.bridges.-$$Lambda$ElementLocalStorageModule$VAKuDcAEXaxrYLs4Tf-ML0inuM4  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$ElementLocalStorageModule$VAKuDcAEXaxrYLs4TfML0inuM4 implements Action1 {
    public static final /* synthetic */ $$Lambda$ElementLocalStorageModule$VAKuDcAEXaxrYLs4TfML0inuM4 INSTANCE = new $$Lambda$ElementLocalStorageModule$VAKuDcAEXaxrYLs4TfML0inuM4();

    private /* synthetic */ $$Lambda$ElementLocalStorageModule$VAKuDcAEXaxrYLs4TfML0inuM4() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.e(ElementLocalStorageModule.LOG_TAG, "Async cache write error", (Throwable) obj);
    }
}
