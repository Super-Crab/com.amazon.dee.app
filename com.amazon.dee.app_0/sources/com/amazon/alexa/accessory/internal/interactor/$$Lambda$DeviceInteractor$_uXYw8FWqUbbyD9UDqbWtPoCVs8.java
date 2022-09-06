package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$_uXYw8FWqUbbyD9UDqbWtPoCVs8  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DeviceInteractor$_uXYw8FWqUbbyD9UDqbWtPoCVs8 implements Consumer {
    public static final /* synthetic */ $$Lambda$DeviceInteractor$_uXYw8FWqUbbyD9UDqbWtPoCVs8 INSTANCE = new $$Lambda$DeviceInteractor$_uXYw8FWqUbbyD9UDqbWtPoCVs8();

    private /* synthetic */ $$Lambda$DeviceInteractor$_uXYw8FWqUbbyD9UDqbWtPoCVs8() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("DeviceInteractor unable to remove unpaired devices from device supplier.", (Throwable) obj);
    }
}
