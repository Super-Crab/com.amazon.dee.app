package com.amazon.alexa.wakeword.speakerverification.enrollment;

import androidx.annotation.Nullable;
import com.amazon.alexa.auth.AccessToken;
import com.amazon.alexa.auth.TokenProvider;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.marketplace.api.Marketplace;
/* loaded from: classes11.dex */
public class IdentityServiceTokenProvider implements TokenProvider {
    private static final String TAG = "IdentityServiceTokenProvider";
    private final IdentityService mIdentityService;

    public IdentityServiceTokenProvider(@Nullable IdentityService identityService) {
        this.mIdentityService = identityService;
    }

    public Marketplace getMarketPlace() {
        IdentityService identityService = this.mIdentityService;
        if (identityService != null) {
            return identityService.getUser(TAG).getMarketplace();
        }
        return Marketplace.USA;
    }

    @Override // com.amazon.alexa.auth.TokenProvider
    @Nullable
    public AccessToken getToken() {
        IdentityService identityService = this.mIdentityService;
        if (identityService != null) {
            return AccessToken.create(identityService.getAccessToken(TAG));
        }
        return null;
    }
}
