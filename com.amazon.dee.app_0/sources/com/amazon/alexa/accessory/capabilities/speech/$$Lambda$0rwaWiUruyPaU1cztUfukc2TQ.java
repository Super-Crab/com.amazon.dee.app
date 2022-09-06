package com.amazon.alexa.accessory.capabilities.speech;

import com.amazon.alexa.accessory.internal.util.MultiDeviceUtils;
import io.reactivex.rxjava3.functions.Function;
import java.util.Set;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$0rwaWiUruyPaU1-cztUfukc-2TQ  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$0rwaWiUruyPaU1cztUfukc2TQ implements Function {
    public static final /* synthetic */ $$Lambda$0rwaWiUruyPaU1cztUfukc2TQ INSTANCE = new $$Lambda$0rwaWiUruyPaU1cztUfukc2TQ();

    private /* synthetic */ $$Lambda$0rwaWiUruyPaU1cztUfukc2TQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return MultiDeviceUtils.extractLocaleFromFirmwareInformation((Set) obj);
    }
}
