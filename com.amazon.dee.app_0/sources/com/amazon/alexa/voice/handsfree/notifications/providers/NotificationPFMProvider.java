package com.amazon.alexa.voice.handsfree.notifications.providers;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.api.PFMProvider;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.CountryCode;
import com.amazon.alexa.voice.handsfree.features.IdentityServiceProvider;
/* loaded from: classes11.dex */
public class NotificationPFMProvider implements PFMProvider {
    private final IdentityService mIdentityService;

    public NotificationPFMProvider() {
        this(new IdentityServiceProvider().provideIdentityService());
    }

    @Override // com.amazon.alexa.handsfree.notification.api.PFMProvider
    public boolean isIndianCustomers() {
        UserIdentity user;
        IdentityService identityService = this.mIdentityService;
        return (identityService == null || (user = identityService.getUser(NotificationPFMProvider.class.getSimpleName())) == null || user.getEffectiveMarketplace().getCountryCode() != CountryCode.IN) ? false : true;
    }

    @VisibleForTesting
    NotificationPFMProvider(@Nullable IdentityService identityService) {
        this.mIdentityService = identityService;
    }
}
