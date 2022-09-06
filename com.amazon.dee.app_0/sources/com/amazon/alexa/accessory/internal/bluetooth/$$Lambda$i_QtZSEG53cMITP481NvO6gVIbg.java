package com.amazon.alexa.accessory.internal.bluetooth;

import com.amazon.alexa.accessory.Accessory;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$i_QtZSEG53cMITP481NvO6gVIbg  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$i_QtZSEG53cMITP481NvO6gVIbg implements Function {
    public static final /* synthetic */ $$Lambda$i_QtZSEG53cMITP481NvO6gVIbg INSTANCE = new $$Lambda$i_QtZSEG53cMITP481NvO6gVIbg();

    private /* synthetic */ $$Lambda$i_QtZSEG53cMITP481NvO6gVIbg() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return ((Accessory) obj).getAddress();
    }
}
