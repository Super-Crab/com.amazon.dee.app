package com.amazon.alexa.accessory.registration;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$ZmVYOwaCnDmY1WmqlmICLKTEC14  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FileSystemRegistrationSupplier$ZmVYOwaCnDmY1WmqlmICLKTEC14 implements Consumer {
    public static final /* synthetic */ $$Lambda$FileSystemRegistrationSupplier$ZmVYOwaCnDmY1WmqlmICLKTEC14 INSTANCE = new $$Lambda$FileSystemRegistrationSupplier$ZmVYOwaCnDmY1WmqlmICLKTEC14();

    private /* synthetic */ $$Lambda$FileSystemRegistrationSupplier$ZmVYOwaCnDmY1WmqlmICLKTEC14() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.d("Successful silent registration persisted \n%s", ((DeviceRegistration) obj).toJsonObject().toString(4));
    }
}
