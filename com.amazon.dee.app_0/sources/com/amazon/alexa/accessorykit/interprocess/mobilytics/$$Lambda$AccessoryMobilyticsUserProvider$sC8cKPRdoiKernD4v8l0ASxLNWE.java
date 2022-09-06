package com.amazon.alexa.accessorykit.interprocess.mobilytics;

import com.amazon.alexa.marketplace.api.Marketplace;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.interprocess.mobilytics.-$$Lambda$AccessoryMobilyticsUserProvider$sC8cKPRdoiKernD4v8l0ASxLNWE  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryMobilyticsUserProvider$sC8cKPRdoiKernD4v8l0ASxLNWE implements Function {
    public static final /* synthetic */ $$Lambda$AccessoryMobilyticsUserProvider$sC8cKPRdoiKernD4v8l0ASxLNWE INSTANCE = new $$Lambda$AccessoryMobilyticsUserProvider$sC8cKPRdoiKernD4v8l0ASxLNWE();

    private /* synthetic */ $$Lambda$AccessoryMobilyticsUserProvider$sC8cKPRdoiKernD4v8l0ASxLNWE() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        String marketplaceId;
        marketplaceId = ((Marketplace) obj).getObfuscatedId().toString();
        return marketplaceId;
    }
}
