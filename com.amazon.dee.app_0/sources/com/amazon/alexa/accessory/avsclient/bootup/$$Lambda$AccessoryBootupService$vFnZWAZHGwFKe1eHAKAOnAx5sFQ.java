package com.amazon.alexa.accessory.avsclient.bootup;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.bootup.-$$Lambda$AccessoryBootupService$vFnZWAZHGwFKe1eHAKAOnAx5sFQ  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AccessoryBootupService$vFnZWAZHGwFKe1eHAKAOnAx5sFQ implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoryBootupService$vFnZWAZHGwFKe1eHAKAOnAx5sFQ INSTANCE = new $$Lambda$AccessoryBootupService$vFnZWAZHGwFKe1eHAKAOnAx5sFQ();

    private /* synthetic */ $$Lambda$AccessoryBootupService$vFnZWAZHGwFKe1eHAKAOnAx5sFQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Throwable th = (Throwable) obj;
        Logger.e("AccessoryBootupServicefailed to get devicePoweredOn state");
    }
}
