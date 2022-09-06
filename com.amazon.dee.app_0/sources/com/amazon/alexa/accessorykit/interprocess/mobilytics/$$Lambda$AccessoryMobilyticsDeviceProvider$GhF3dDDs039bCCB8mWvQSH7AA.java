package com.amazon.alexa.accessorykit.interprocess.mobilytics;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.interprocess.mobilytics.-$$Lambda$AccessoryMobilyticsDeviceProvider$GhF3dDD-s-039bCCB8mWvQSH7AA  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryMobilyticsDeviceProvider$GhF3dDDs039bCCB8mWvQSH7AA implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoryMobilyticsDeviceProvider$GhF3dDDs039bCCB8mWvQSH7AA INSTANCE = new $$Lambda$AccessoryMobilyticsDeviceProvider$GhF3dDDs039bCCB8mWvQSH7AA();

    private /* synthetic */ $$Lambda$AccessoryMobilyticsDeviceProvider$GhF3dDDs039bCCB8mWvQSH7AA() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s Unexpected error in queryMobityticsDevice", (Throwable) obj, AccessoryMobilyticsDeviceProvider.TAG);
    }
}
