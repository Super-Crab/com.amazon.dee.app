package com.amazon.alexa.mobilytics.integration.ama;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.CommsProfile;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
/* loaded from: classes9.dex */
public class MobilyticsUserImpl implements MobilyticsUser {
    private final FeatureServiceV2 featureServiceV2;
    private final UserIdentity userIdentity;

    /* renamed from: com.amazon.alexa.mobilytics.integration.ama.MobilyticsUserImpl$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$mobilytics$identity$MobilyticsUser$Attribute = new int[MobilyticsUser.Attribute.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$mobilytics$identity$MobilyticsUser$Attribute[MobilyticsUser.Attribute.HASHED_COMMS_ID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mobilytics$identity$MobilyticsUser$Attribute[MobilyticsUser.Attribute.HOUSEHOLD_ID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public MobilyticsUserImpl(@NonNull UserIdentity userIdentity, @NonNull FeatureServiceV2 featureServiceV2) {
        this.userIdentity = userIdentity;
        this.featureServiceV2 = featureServiceV2;
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    @Nullable
    public String attribute(MobilyticsUser.Attribute attribute) {
        CommsProfile commsProfile;
        UserIdentity userIdentity = this.userIdentity;
        if (userIdentity == null || userIdentity.getUserProfile() == null || (commsProfile = this.userIdentity.getUserProfile().getCommsProfile()) == null) {
            return "Unknown";
        }
        int ordinal = attribute.ordinal();
        if (ordinal == 0) {
            return commsProfile.getHashedCommsId();
        }
        return ordinal != 1 ? "Unknown" : commsProfile.getHouseholdId();
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    @NonNull
    public String countryOfResidence() {
        UserIdentity userIdentity = this.userIdentity;
        return (userIdentity == null || TextUtils.isEmpty(userIdentity.getCountryOfResidence())) ? "Unknown" : this.userIdentity.getCountryOfResidence();
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    @NonNull
    public String directedId() {
        UserIdentity userIdentity = this.userIdentity;
        return (userIdentity == null || TextUtils.isEmpty(userIdentity.getDirectedId())) ? "Unknown" : this.userIdentity.getDirectedId();
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    public boolean hasFeature(@NonNull String str) {
        FeatureServiceV2 featureServiceV2 = this.featureServiceV2;
        if (featureServiceV2 == null) {
            return false;
        }
        return featureServiceV2.hasAccess(str, false);
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    @NonNull
    public String marketplaceId() {
        UserIdentity userIdentity = this.userIdentity;
        return (userIdentity == null || userIdentity.getMarketplace() == null || this.userIdentity.getMarketplace().getObfuscatedId() == null) ? "Unknown" : this.userIdentity.getMarketplace().getObfuscatedId().toString();
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    @NonNull
    public String personId() {
        UserIdentity userIdentity = this.userIdentity;
        return (userIdentity == null || userIdentity.getUserProfile() == null || TextUtils.isEmpty(this.userIdentity.getUserProfile().getDirectedId())) ? "Unknown" : this.userIdentity.getUserProfile().getDirectedId();
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    @NonNull
    public String personIdv2() {
        UserIdentity userIdentity = this.userIdentity;
        return (userIdentity == null || userIdentity.getUserProfile() == null || TextUtils.isEmpty(this.userIdentity.getUserProfile().getPersonId())) ? "Unknown" : this.userIdentity.getUserProfile().getPersonId();
    }
}
