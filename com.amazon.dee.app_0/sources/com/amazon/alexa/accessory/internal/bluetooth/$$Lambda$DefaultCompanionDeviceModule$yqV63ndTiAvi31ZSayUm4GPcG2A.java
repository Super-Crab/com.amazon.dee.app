package com.amazon.alexa.accessory.internal.bluetooth;

import android.os.Build;
import android.util.Pair;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultCompanionDeviceModule$yqV63ndTiAvi31ZSayUm4GPcG2A  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DefaultCompanionDeviceModule$yqV63ndTiAvi31ZSayUm4GPcG2A implements Function {
    public static final /* synthetic */ $$Lambda$DefaultCompanionDeviceModule$yqV63ndTiAvi31ZSayUm4GPcG2A INSTANCE = new $$Lambda$DefaultCompanionDeviceModule$yqV63ndTiAvi31ZSayUm4GPcG2A();

    private /* synthetic */ $$Lambda$DefaultCompanionDeviceModule$yqV63ndTiAvi31ZSayUm4GPcG2A() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.CompanionDevice.COMPANION_DEVICE_ASSOCIATION_REQUESTED, String.valueOf(Build.VERSION.SDK_INT), ((Boolean) ((Pair) obj).second).booleanValue(), null);
    }
}
