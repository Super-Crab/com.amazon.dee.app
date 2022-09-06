package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceAccountInteractor$SessionListener$UrCRk3K4zFM2FGUDddWtPuIC2RA  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DeviceAccountInteractor$SessionListener$UrCRk3K4zFM2FGUDddWtPuIC2RA implements Consumer {
    public static final /* synthetic */ $$Lambda$DeviceAccountInteractor$SessionListener$UrCRk3K4zFM2FGUDddWtPuIC2RA INSTANCE = new $$Lambda$DeviceAccountInteractor$SessionListener$UrCRk3K4zFM2FGUDddWtPuIC2RA();

    private /* synthetic */ $$Lambda$DeviceAccountInteractor$SessionListener$UrCRk3K4zFM2FGUDddWtPuIC2RA() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        List list = (List) obj;
        Logger.d("%s: successfully handled 1p accessory registrations for device accounts", DeviceAccountInteractor.TAG);
    }
}
