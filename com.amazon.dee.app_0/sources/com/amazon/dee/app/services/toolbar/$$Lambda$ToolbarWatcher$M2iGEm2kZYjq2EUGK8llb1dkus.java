package com.amazon.dee.app.services.toolbar;

import com.amazon.dee.app.services.logging.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.services.toolbar.-$$Lambda$ToolbarWatcher$M2i-GEm2kZYjq2EUGK8llb1dkus  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$ToolbarWatcher$M2iGEm2kZYjq2EUGK8llb1dkus implements Consumer {
    public static final /* synthetic */ $$Lambda$ToolbarWatcher$M2iGEm2kZYjq2EUGK8llb1dkus INSTANCE = new $$Lambda$ToolbarWatcher$M2iGEm2kZYjq2EUGK8llb1dkus();

    private /* synthetic */ $$Lambda$ToolbarWatcher$M2iGEm2kZYjq2EUGK8llb1dkus() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.e(ToolbarWatcher.TAG, "Could not update route for toolbars: ", (Throwable) obj);
    }
}
