package com.amazon.alexa.enrollment.utils;

import android.util.Log;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.MarketplaceId;
/* loaded from: classes7.dex */
public final class VoiceEnrollmentMarketplaceUtils {
    private static final String TAG = "VoxEn_MarketplaceUtils";

    private VoiceEnrollmentMarketplaceUtils() {
    }

    public static boolean isBipa(IdentityService identityService, String str) {
        UserIdentity user;
        try {
            user = identityService.getUser(str);
        } catch (Exception e) {
            Log.e(TAG, "Unable to find if the customer is in bipa. Exception - ", e);
        }
        if (user == null) {
            Log.i(TAG, "customer is null, returning false for isBipa");
            return false;
        }
        MarketplaceId obfuscatedId = user.getMarketplace().getObfuscatedId();
        return MarketplaceId.CO_UK.equals(obfuscatedId) || MarketplaceId.FR.equals(obfuscatedId) || MarketplaceId.COM_BR_PROD.equals(obfuscatedId) || MarketplaceId.DE.equals(obfuscatedId) || MarketplaceId.ES_PROD.equals(obfuscatedId) || MarketplaceId.IT_PROD.equals(obfuscatedId) || MarketplaceId.NL_PROD.equals(obfuscatedId);
    }
}
