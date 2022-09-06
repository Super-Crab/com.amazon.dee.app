package com.amazon.alexa.mode.util;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes9.dex */
public class IdentityServiceHelper {
    private static final String TAG = "IdentityServiceHelper";
    private IdentityService identityService;

    public IdentityServiceHelper() {
        if (this.identityService == null) {
            this.identityService = (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);
        }
    }

    public Marketplace getUserMarketPlace() {
        UserIdentity user;
        IdentityService identityService = this.identityService;
        if (identityService != null && (user = identityService.getUser(TAG)) != null) {
            return user.getMarketplace();
        }
        return Marketplace.USA;
    }
}
