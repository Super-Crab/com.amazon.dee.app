package com.amazon.dee.app.services.marketplace;

import com.amazon.alexa.marketplace.api.Marketplace;
import rx.functions.Func1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.services.marketplace.-$$Lambda$PreferredMarketplaceService$9l2illCPhonpLdnPtPMP30j9QIA  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$PreferredMarketplaceService$9l2illCPhonpLdnPtPMP30j9QIA implements Func1 {
    public static final /* synthetic */ $$Lambda$PreferredMarketplaceService$9l2illCPhonpLdnPtPMP30j9QIA INSTANCE = new $$Lambda$PreferredMarketplaceService$9l2illCPhonpLdnPtPMP30j9QIA();

    private /* synthetic */ $$Lambda$PreferredMarketplaceService$9l2illCPhonpLdnPtPMP30j9QIA() {
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public final Object mo13102call(Object obj) {
        Marketplace findMarketplaceById;
        findMarketplaceById = Marketplace.findMarketplaceById((String) obj, Marketplace.USA);
        return findMarketplaceById;
    }
}
