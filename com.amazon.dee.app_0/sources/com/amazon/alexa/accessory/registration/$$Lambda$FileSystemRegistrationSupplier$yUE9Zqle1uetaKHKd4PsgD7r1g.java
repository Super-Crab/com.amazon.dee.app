package com.amazon.alexa.accessory.registration;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$yUE9Zqle1-uetaKHKd4PsgD7r1g  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FileSystemRegistrationSupplier$yUE9Zqle1uetaKHKd4PsgD7r1g implements Consumer {
    public static final /* synthetic */ $$Lambda$FileSystemRegistrationSupplier$yUE9Zqle1uetaKHKd4PsgD7r1g INSTANCE = new $$Lambda$FileSystemRegistrationSupplier$yUE9Zqle1uetaKHKd4PsgD7r1g();

    private /* synthetic */ $$Lambda$FileSystemRegistrationSupplier$yUE9Zqle1uetaKHKd4PsgD7r1g() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Failed async registration", (Throwable) obj);
    }
}
