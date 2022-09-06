package com.amazon.alexa.accessory.engagement;

import com.amazon.alexa.accessory.internal.util.MultiDeviceUtils;
import io.reactivex.rxjava3.functions.Function;
import java.util.Set;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.engagement.-$$Lambda$kTr_FAzIzBku4_pbIPWuzYj2wAQ  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$kTr_FAzIzBku4_pbIPWuzYj2wAQ implements Function {
    public static final /* synthetic */ $$Lambda$kTr_FAzIzBku4_pbIPWuzYj2wAQ INSTANCE = new $$Lambda$kTr_FAzIzBku4_pbIPWuzYj2wAQ();

    private /* synthetic */ $$Lambda$kTr_FAzIzBku4_pbIPWuzYj2wAQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return MultiDeviceUtils.getFirmwareInformationWithLowestVersion((Set) obj);
    }
}
