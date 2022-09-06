package com.amazon.alexa.drive.util;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.identity.api.UserProfile;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public final class IdentityUtils {
    private IdentityUtils() {
    }

    public static String getAccessToken() {
        IdentityService identityService = (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);
        if (identityService != null) {
            return identityService.getAccessToken(IdentityUtils.class.getSimpleName());
        }
        return null;
    }

    public static String getPersonIdFromUserIdentity(UserIdentity userIdentity) {
        UserProfile userProfile = userIdentity.getUserProfile();
        return userProfile != null ? userProfile.getDirectedId() : userIdentity.getDirectedId();
    }

    public static UserIdentity getUserFromIdentityService() {
        IdentityService identityService = (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);
        if (identityService != null) {
            return identityService.getUser(IdentityUtils.class.getSimpleName());
        }
        return null;
    }

    public static Marketplace getUserMarketPlace() {
        UserIdentity userFromIdentityService = getUserFromIdentityService();
        if (userFromIdentityService != null) {
            return userFromIdentityService.getMarketplace();
        }
        return Marketplace.USA;
    }
}
