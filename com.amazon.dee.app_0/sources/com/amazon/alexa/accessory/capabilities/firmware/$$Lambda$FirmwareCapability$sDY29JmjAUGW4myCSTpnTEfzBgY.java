package com.amazon.alexa.accessory.capabilities.firmware;

import com.amazon.alexa.accessory.repositories.firmware.FirmwareMetadata;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.concurrent.Callable;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$sDY29JmjAUGW4myCSTpnTEfzBgY  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$FirmwareCapability$sDY29JmjAUGW4myCSTpnTEfzBgY implements Function {
    public static final /* synthetic */ $$Lambda$FirmwareCapability$sDY29JmjAUGW4myCSTpnTEfzBgY INSTANCE = new $$Lambda$FirmwareCapability$sDY29JmjAUGW4myCSTpnTEfzBgY();

    private /* synthetic */ $$Lambda$FirmwareCapability$sDY29JmjAUGW4myCSTpnTEfzBgY() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        SingleSource subscribeOn;
        subscribeOn = Single.fromCallable(new Callable() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$cOgeF6MCTqOZZDZiDwqGQ2AV7X8
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return FirmwareCapability.lambda$null$13(FirmwareMetadata.this);
            }
        }).subscribeOn(Schedulers.io());
        return subscribeOn;
    }
}
