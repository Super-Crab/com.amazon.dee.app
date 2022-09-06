package com.amazon.alexa;

import com.amazon.alexa.api.AlexaCardExtras;
import com.amazon.alexa.client.core.marketplace.MarketplaceAuthority;
import dagger.Module;
import dagger.Provides;
/* compiled from: CardModule.java */
@Module
/* loaded from: classes.dex */
public class xfG {
    @Provides
    public AlexaCardExtras zZm(MarketplaceAuthority marketplaceAuthority, MBE mbe) {
        return AlexaCardExtras.builder().setLocale(mbe.Qle()).setMarketplace(marketplaceAuthority.getMarketplace().name()).build();
    }
}
