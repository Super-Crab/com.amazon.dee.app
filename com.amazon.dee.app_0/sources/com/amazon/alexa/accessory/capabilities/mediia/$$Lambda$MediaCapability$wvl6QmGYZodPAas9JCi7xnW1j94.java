package com.amazon.alexa.accessory.capabilities.mediia;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.mediia.-$$Lambda$MediaCapability$wvl6QmGYZodPAas9JCi7xnW1j94  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$MediaCapability$wvl6QmGYZodPAas9JCi7xnW1j94 implements Consumer {
    public static final /* synthetic */ $$Lambda$MediaCapability$wvl6QmGYZodPAas9JCi7xnW1j94 INSTANCE = new $$Lambda$MediaCapability$wvl6QmGYZodPAas9JCi7xnW1j94();

    private /* synthetic */ $$Lambda$MediaCapability$wvl6QmGYZodPAas9JCi7xnW1j94() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Failed to handle playback event:", (Throwable) obj);
    }
}
