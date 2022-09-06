package com.dee.app.data.reactnative;

import android.util.Log;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.dee.app.data.reactnative.-$$Lambda$DefaultElementsDataService$YWAyjWyahlNYjWVb-QBkOqFKLxs  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$DefaultElementsDataService$YWAyjWyahlNYjWVbQBkOqFKLxs implements Action1 {
    public static final /* synthetic */ $$Lambda$DefaultElementsDataService$YWAyjWyahlNYjWVbQBkOqFKLxs INSTANCE = new $$Lambda$DefaultElementsDataService$YWAyjWyahlNYjWVbQBkOqFKLxs();

    private /* synthetic */ $$Lambda$DefaultElementsDataService$YWAyjWyahlNYjWVbQBkOqFKLxs() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.e(DefaultElementsDataService.LOG_TAG, "Internal cache request exception occurred.", (Throwable) obj);
    }
}
