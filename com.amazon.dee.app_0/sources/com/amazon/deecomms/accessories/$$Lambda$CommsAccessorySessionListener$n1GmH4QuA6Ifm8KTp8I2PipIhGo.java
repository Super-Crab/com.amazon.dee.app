package com.amazon.deecomms.accessories;

import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.accessories.-$$Lambda$CommsAccessorySessionListener$n1GmH4QuA6Ifm8KTp8I2PipIhGo  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsAccessorySessionListener$n1GmH4QuA6Ifm8KTp8I2PipIhGo implements Consumer {
    public static final /* synthetic */ $$Lambda$CommsAccessorySessionListener$n1GmH4QuA6Ifm8KTp8I2PipIhGo INSTANCE = new $$Lambda$CommsAccessorySessionListener$n1GmH4QuA6Ifm8KTp8I2PipIhGo();

    private /* synthetic */ $$Lambda$CommsAccessorySessionListener$n1GmH4QuA6Ifm8KTp8I2PipIhGo() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        CommsAccessorySessionListener.LOG.e("CRITICAL error", (Throwable) obj);
    }
}
