package com.amazon.dee.app.services.toolbar;

import com.amazon.dee.app.services.logging.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.services.toolbar.-$$Lambda$ToolbarWatcher$pjS-DBZhprgvl8iAT1pasEzGGHw  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$ToolbarWatcher$pjSDBZhprgvl8iAT1pasEzGGHw implements Consumer {
    public static final /* synthetic */ $$Lambda$ToolbarWatcher$pjSDBZhprgvl8iAT1pasEzGGHw INSTANCE = new $$Lambda$ToolbarWatcher$pjSDBZhprgvl8iAT1pasEzGGHw();

    private /* synthetic */ $$Lambda$ToolbarWatcher$pjSDBZhprgvl8iAT1pasEzGGHw() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.e(ToolbarWatcher.TAG, "Could not update toolbar: ", (Throwable) obj);
    }
}
