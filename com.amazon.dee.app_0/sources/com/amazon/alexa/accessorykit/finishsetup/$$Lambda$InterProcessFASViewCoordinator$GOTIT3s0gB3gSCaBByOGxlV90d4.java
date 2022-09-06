package com.amazon.alexa.accessorykit.finishsetup;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.finishsetup.-$$Lambda$InterProcessFASViewCoordinator$GOTIT3s0gB3gSCaBByOGxlV90d4  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$InterProcessFASViewCoordinator$GOTIT3s0gB3gSCaBByOGxlV90d4 implements Consumer {
    public static final /* synthetic */ $$Lambda$InterProcessFASViewCoordinator$GOTIT3s0gB3gSCaBByOGxlV90d4 INSTANCE = new $$Lambda$InterProcessFASViewCoordinator$GOTIT3s0gB3gSCaBByOGxlV90d4();

    private /* synthetic */ $$Lambda$InterProcessFASViewCoordinator$GOTIT3s0gB3gSCaBByOGxlV90d4() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Unable to observe newly setup devices.", (Throwable) obj);
    }
}
