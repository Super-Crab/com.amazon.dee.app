package com.amazon.alexa.accessory.registration.deviceaccount;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$Po8DLDHklbLiH6aHo3VNY0KlSsg  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FileSystemDeviceAccountSupplier$Po8DLDHklbLiH6aHo3VNY0KlSsg implements Consumer {
    public static final /* synthetic */ $$Lambda$FileSystemDeviceAccountSupplier$Po8DLDHklbLiH6aHo3VNY0KlSsg INSTANCE = new $$Lambda$FileSystemDeviceAccountSupplier$Po8DLDHklbLiH6aHo3VNY0KlSsg();

    private /* synthetic */ $$Lambda$FileSystemDeviceAccountSupplier$Po8DLDHklbLiH6aHo3VNY0KlSsg() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.d("%s: Device account queried & successfully persisted: %s", FileSystemDeviceAccountSupplier.TAG, ((DeviceAccount) obj).toJsonObject().toString());
    }
}
