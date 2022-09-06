package com.amazon.alexa.accessory.capabilities.transport;

import com.amazon.alexa.accessory.repositories.Producer;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$25dTK8BRpWxdQZXVVmIUFgH5OIU  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$25dTK8BRpWxdQZXVVmIUFgH5OIU implements Consumer {
    private final /* synthetic */ Producer.Result f$0;

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        this.f$0.completeWithError((Throwable) obj);
    }
}
