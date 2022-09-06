package com.amazon.alexa.accessory.registration.deviceaccount;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$v2K87kody2qgoYeqUYzAMST5oTw  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FileSystemDeviceAccountSupplier$v2K87kody2qgoYeqUYzAMST5oTw implements Consumer {
    public static final /* synthetic */ $$Lambda$FileSystemDeviceAccountSupplier$v2K87kody2qgoYeqUYzAMST5oTw INSTANCE = new $$Lambda$FileSystemDeviceAccountSupplier$v2K87kody2qgoYeqUYzAMST5oTw();

    private /* synthetic */ $$Lambda$FileSystemDeviceAccountSupplier$v2K87kody2qgoYeqUYzAMST5oTw() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s: Failed to query or persist device account.", (Throwable) obj, FileSystemDeviceAccountSupplier.TAG);
    }
}
