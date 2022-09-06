package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceAccountInteractor$Dlr4XgaNeBFKkidjARDaHoZ4DBk  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DeviceAccountInteractor$Dlr4XgaNeBFKkidjARDaHoZ4DBk implements Consumer {
    public static final /* synthetic */ $$Lambda$DeviceAccountInteractor$Dlr4XgaNeBFKkidjARDaHoZ4DBk INSTANCE = new $$Lambda$DeviceAccountInteractor$Dlr4XgaNeBFKkidjARDaHoZ4DBk();

    private /* synthetic */ $$Lambda$DeviceAccountInteractor$Dlr4XgaNeBFKkidjARDaHoZ4DBk() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        List list = (List) obj;
        Logger.d("%s: Login observed", DeviceAccountInteractor.TAG);
    }
}
