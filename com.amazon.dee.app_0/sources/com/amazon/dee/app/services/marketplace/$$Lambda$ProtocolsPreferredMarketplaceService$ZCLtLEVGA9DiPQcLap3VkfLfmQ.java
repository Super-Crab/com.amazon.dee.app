package com.amazon.dee.app.services.marketplace;

import com.amazon.alexa.protocols.marketplace.Marketplace;
import rx.functions.Func1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.services.marketplace.-$$Lambda$ProtocolsPreferredMarketplaceService$ZCLtLEVGA9-DiPQcLap3VkfLfmQ  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$ProtocolsPreferredMarketplaceService$ZCLtLEVGA9DiPQcLap3VkfLfmQ implements Func1 {
    public static final /* synthetic */ $$Lambda$ProtocolsPreferredMarketplaceService$ZCLtLEVGA9DiPQcLap3VkfLfmQ INSTANCE = new $$Lambda$ProtocolsPreferredMarketplaceService$ZCLtLEVGA9DiPQcLap3VkfLfmQ();

    private /* synthetic */ $$Lambda$ProtocolsPreferredMarketplaceService$ZCLtLEVGA9DiPQcLap3VkfLfmQ() {
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public final Object mo13102call(Object obj) {
        Marketplace findMarketplaceById;
        findMarketplaceById = Marketplace.findMarketplaceById((String) obj, Marketplace.USA);
        return findMarketplaceById;
    }
}
