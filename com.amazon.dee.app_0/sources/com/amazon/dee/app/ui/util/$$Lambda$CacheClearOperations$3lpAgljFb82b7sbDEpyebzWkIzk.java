package com.amazon.dee.app.ui.util;

import com.amazon.dee.app.services.logging.Log;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.ui.util.-$$Lambda$CacheClearOperations$3lpAgljFb82b7sbDEpyebzWkIzk  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CacheClearOperations$3lpAgljFb82b7sbDEpyebzWkIzk implements Action1 {
    public static final /* synthetic */ $$Lambda$CacheClearOperations$3lpAgljFb82b7sbDEpyebzWkIzk INSTANCE = new $$Lambda$CacheClearOperations$3lpAgljFb82b7sbDEpyebzWkIzk();

    private /* synthetic */ $$Lambda$CacheClearOperations$3lpAgljFb82b7sbDEpyebzWkIzk() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.e(CacheClearOperations.LOG_TAG, "Failed to clear cache on logout.", obj);
    }
}
