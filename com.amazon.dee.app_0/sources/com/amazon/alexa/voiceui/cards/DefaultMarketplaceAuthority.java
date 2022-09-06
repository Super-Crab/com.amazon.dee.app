package com.amazon.alexa.voiceui.cards;

import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.marketplace.MarketplaceAuthority;
import javax.inject.Singleton;
/* JADX INFO: Access modifiers changed from: package-private */
@Singleton
/* loaded from: classes11.dex */
public class DefaultMarketplaceAuthority implements MarketplaceAuthority {
    private String marketplace;

    @Override // com.amazon.alexa.voice.ui.marketplace.MarketplaceAuthority
    @Nullable
    public String getMarketplace() {
        return this.marketplace;
    }

    public void setMarketplace(String str) {
        this.marketplace = str;
    }
}
