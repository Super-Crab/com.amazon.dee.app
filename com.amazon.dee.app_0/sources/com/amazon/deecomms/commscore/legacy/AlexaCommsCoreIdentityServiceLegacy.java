package com.amazon.deecomms.commscore.legacy;

import androidx.annotation.Nullable;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.identity.CommsCoreIdentity;
import com.amazon.commscore.api.identity.MarketplaceInfo;
import com.amazon.commscore.api.identity.Name;
import com.amazon.deecomms.api.CommsIdentity;
import com.amazon.deecomms.api.CommsIdentityManager;
/* loaded from: classes12.dex */
public class AlexaCommsCoreIdentityServiceLegacy implements AlexaCommsCoreIdentityService {
    CommsIdentityManager commsIdentityManager;

    public AlexaCommsCoreIdentityServiceLegacy(CommsIdentityManager commsIdentityManager) {
        this.commsIdentityManager = commsIdentityManager;
    }

    private CommsCoreIdentity convertLegacyIdentityToCommsCoreIdentity(CommsIdentity commsIdentity) {
        CommsCoreIdentity commsCoreIdentity = new CommsCoreIdentity();
        commsCoreIdentity.setCommsId(commsIdentity.getCommsId());
        commsCoreIdentity.setName(convertLegacyNametoCommsCoreName(commsIdentity));
        commsCoreIdentity.setDirectedId(commsIdentity.getDirectedId());
        commsCoreIdentity.setHashedCommsId(commsIdentity.getHashedCommsId());
        commsCoreIdentity.setPhoneNumber(commsIdentity.getPhoneNumber());
        commsCoreIdentity.setHomegroupId(commsIdentity.getHomeGroupId());
        commsCoreIdentity.setEmail(commsIdentity.getEmail());
        commsCoreIdentity.setMarketplaceInfo(convertLegacyMarketplaceToCommsCoreMarketplace(commsIdentity.getUserPFMInfo()));
        commsCoreIdentity.setCountryOfResidence(commsIdentity.getCountryOfResidence());
        commsCoreIdentity.setProvisioningStatus(commsIdentity.getProvisioningStatus().name());
        return commsCoreIdentity;
    }

    private MarketplaceInfo convertLegacyMarketplaceToCommsCoreMarketplace(com.amazon.deecomms.api.MarketplaceInfo marketplaceInfo) {
        MarketplaceInfo marketplaceInfo2 = new MarketplaceInfo();
        marketplaceInfo2.setPfmCode(marketplaceInfo.getPfmCode());
        marketplaceInfo2.setMarketPlaceId(marketplaceInfo.getMarketplaceId());
        marketplaceInfo2.setMarketPlaceCountryCode(marketplaceInfo.getMarketplaceIdCode());
        return marketplaceInfo2;
    }

    private Name convertLegacyNametoCommsCoreName(CommsIdentity commsIdentity) {
        Name name = new Name();
        name.setFirstName(commsIdentity.getFirstName());
        name.setLastName(commsIdentity.getLastName());
        name.setPhoneticFirstName(commsIdentity.getPhoneticFirstName());
        name.setPhoneticLastName(commsIdentity.getPhoneticLastName());
        return name;
    }

    @Override // com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService
    @Nullable
    public CommsCoreIdentity getIdentity() {
        return convertLegacyIdentityToCommsCoreIdentity(this.commsIdentityManager.getCurrentCommsIdentity());
    }
}
