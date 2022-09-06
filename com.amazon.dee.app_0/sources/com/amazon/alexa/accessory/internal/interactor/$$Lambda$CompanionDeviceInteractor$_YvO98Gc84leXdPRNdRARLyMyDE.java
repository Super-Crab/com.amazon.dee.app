package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$CompanionDeviceInteractor$_YvO98Gc84leXdPRNdRARLyMyDE  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$CompanionDeviceInteractor$_YvO98Gc84leXdPRNdRARLyMyDE implements Consumer {
    public static final /* synthetic */ $$Lambda$CompanionDeviceInteractor$_YvO98Gc84leXdPRNdRARLyMyDE INSTANCE = new $$Lambda$CompanionDeviceInteractor$_YvO98Gc84leXdPRNdRARLyMyDE();

    private /* synthetic */ $$Lambda$CompanionDeviceInteractor$_YvO98Gc84leXdPRNdRARLyMyDE() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("CompanionDeviceInteractor: Critical: Error in listenForNewCompanionDevices", (Throwable) obj);
    }
}
