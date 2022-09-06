package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$BluetoothBondObserver$RvOWPK9apizcxF29bsDzNfT6HMI  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DeviceInteractor$BluetoothBondObserver$RvOWPK9apizcxF29bsDzNfT6HMI implements Consumer {
    public static final /* synthetic */ $$Lambda$DeviceInteractor$BluetoothBondObserver$RvOWPK9apizcxF29bsDzNfT6HMI INSTANCE = new $$Lambda$DeviceInteractor$BluetoothBondObserver$RvOWPK9apizcxF29bsDzNfT6HMI();

    private /* synthetic */ $$Lambda$DeviceInteractor$BluetoothBondObserver$RvOWPK9apizcxF29bsDzNfT6HMI() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.d("DeviceInteractor bluetooth bond was removed but no device was removed from database", (Throwable) obj);
    }
}
