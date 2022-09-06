package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Action;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$p3XQ5DgkMwQqx6wboXiTeEKPxEI  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DeviceInteractor$p3XQ5DgkMwQqx6wboXiTeEKPxEI implements Action {
    public static final /* synthetic */ $$Lambda$DeviceInteractor$p3XQ5DgkMwQqx6wboXiTeEKPxEI INSTANCE = new $$Lambda$DeviceInteractor$p3XQ5DgkMwQqx6wboXiTeEKPxEI();

    private /* synthetic */ $$Lambda$DeviceInteractor$p3XQ5DgkMwQqx6wboXiTeEKPxEI() {
    }

    @Override // io.reactivex.rxjava3.functions.Action
    public final void run() {
        Logger.e("DeviceInteractor completed observing linked devices. Device information for subsequently linked devices will not be updated.");
    }
}
