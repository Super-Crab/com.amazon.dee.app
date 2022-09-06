package com.amazon.alexa.identity;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.identity.api.Mapper;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.protocols.features.FeatureConstraints;
/* loaded from: classes9.dex */
public class UserIdentityMapper implements Mapper<UserIdentityDTO, UserIdentity> {
    private static final String TAG = Utils.tag(UserIdentityMapper.class);
    private final FeatureConstraints constraints;

    public UserIdentityMapper(@NonNull FeatureConstraints featureConstraints) {
        this.constraints = featureConstraints;
    }

    @Override // com.amazon.alexa.identity.api.Mapper
    public UserIdentity map(@NonNull UserIdentityDTO userIdentityDTO) {
        Marketplace marketplace = null;
        UserIdentity.Builder withCountryOfResidence = UserIdentity.builder().withId(userIdentityDTO.id).withName(userIdentityDTO.fullName).withEmail(userIdentityDTO.email).withOriginalMarketplace(Marketplace.findMarketplaceById(userIdentityDTO.marketPlaceId, null)).withMarketplace(Marketplace.findMarketplaceById(userIdentityDTO.marketPlaceId, Marketplace.USA)).withCountryOfResidence(userIdentityDTO.countryOfResidence);
        if (!TextUtils.isEmpty(userIdentityDTO.effectiveMarketPlaceId)) {
            marketplace = Marketplace.findMarketplaceById(userIdentityDTO.effectiveMarketPlaceId, Marketplace.USA);
        }
        UserIdentity build = withCountryOfResidence.withEffectiveMarketplace(marketplace).hasActiveDevices(userIdentityDTO.hasActiveDopplers.booleanValue()).hasAcceptedEula(userIdentityDTO.eulaAcceptance.booleanValue()).build();
        String[] strArr = userIdentityDTO.features;
        int length = strArr != null ? strArr.length : 0;
        String str = TAG;
        Log.i(str, "IdentityV2 : Updated user with " + length + " features");
        return UserIdentity.from(build).withFeatures(this.constraints.apply(build, userIdentityDTO.features)).build();
    }
}
