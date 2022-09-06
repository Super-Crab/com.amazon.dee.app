package com.amazon.alexa.accessorykit.findmy;

import com.amazon.alexa.accessory.protocol.Device;
import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.findmy.-$$Lambda$DeviceFilterUtils$BxQ_Swb95eTDdhIuDYecdmb3ci8  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$DeviceFilterUtils$BxQ_Swb95eTDdhIuDYecdmb3ci8 implements Predicate {
    public static final /* synthetic */ $$Lambda$DeviceFilterUtils$BxQ_Swb95eTDdhIuDYecdmb3ci8 INSTANCE = new $$Lambda$DeviceFilterUtils$BxQ_Swb95eTDdhIuDYecdmb3ci8();

    private /* synthetic */ $$Lambda$DeviceFilterUtils$BxQ_Swb95eTDdhIuDYecdmb3ci8() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return DeviceFilterUtils.lambda$static$9((Device.DeviceInformation) obj);
    }
}
