package com.amazon.alexa.voice.ui;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.CountryCode;
import com.amazon.alexa.voice.ui.marketplace.MarketplaceAuthority;
/* loaded from: classes11.dex */
public final class DefaultMarketplaceAuthority implements MarketplaceAuthority {
    private static final String TAG = "DefaultMarketplaceAuthority";
    private final IdentityService identityService;

    public DefaultMarketplaceAuthority(IdentityService identityService) {
        this.identityService = identityService;
    }

    @Override // com.amazon.alexa.voice.ui.marketplace.MarketplaceAuthority
    public String getMarketplace() {
        UserIdentity user = this.identityService.getUser(TAG);
        if (user != null) {
            return user.getEffectiveMarketplace().getCountryCode().name();
        }
        return CountryCode.US.name();
    }
}
