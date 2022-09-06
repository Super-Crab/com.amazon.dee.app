package com.amazon.commscore.commsidentity.implementation;

import androidx.core.util.Pair;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.commscore.api.identity.CommsCoreIdentity;
import com.amazon.commscore.api.identity.MarketplaceInfo;
import com.amazon.commscore.api.identity.Name;
import com.amazon.commscore.commsidentity.repo.model.AccountForDirectedId;
import com.amazon.commscore.commsidentity.repo.model.IdentityV2;
import com.amazon.commscore.commsidentity.repo.provider.CommsIdentityProvider;
import dagger.Lazy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Function;
import javax.inject.Inject;
import rx.Observable;
/* loaded from: classes12.dex */
public class CommsCoreIdentityViewModel {
    private static final String TAG = "com.amazon.commscore.commsidentity.implementation.CommsCoreIdentityViewModel";
    private CommsIdentityProvider commsIdentityProvider;
    private Lazy<IdentityService> identityServiceLazy;

    @Inject
    public CommsCoreIdentityViewModel(Lazy<IdentityService> lazy, CommsIdentityProvider commsIdentityProvider) {
        this.identityServiceLazy = lazy;
        this.commsIdentityProvider = commsIdentityProvider;
    }

    private CommsCoreIdentity parseAndCombineIdentityData(UserIdentity userIdentity, IdentityV2 identityV2, AccountForDirectedId accountForDirectedId) {
        CommsCoreIdentity commsCoreIdentity = new CommsCoreIdentity();
        commsCoreIdentity.setCommsId(identityV2.getCommsId());
        commsCoreIdentity.setDirectedId(userIdentity.getUserProfile().getDirectedId());
        commsCoreIdentity.setHomegroupId(identityV2.getHomeGroupId());
        commsCoreIdentity.setHashedCommsId(identityV2.getHashedCommsId());
        commsCoreIdentity.setName(parseName(identityV2.getName()));
        commsCoreIdentity.setPhoneCountryCode(accountForDirectedId.getPhoneCountryCode());
        commsCoreIdentity.setPhoneNumber(accountForDirectedId.getPhoneNumber());
        commsCoreIdentity.setEmail(userIdentity.getEmail());
        commsCoreIdentity.setProvisioningStatus(identityV2.getCommsProvisionStatus());
        commsCoreIdentity.setMarketplaceInfo(parseMarketplaceInfo(userIdentity.getMarketplace()));
        commsCoreIdentity.setCountryOfResidence(userIdentity.getCountryOfResidence());
        commsCoreIdentity.setPceId(identityV2.getPceId());
        commsCoreIdentity.setHomegroupPceId(identityV2.getHomeGroupPceId());
        commsCoreIdentity.setSpeakerProvisioned(accountForDirectedId.isSpeakerProvisioned());
        commsCoreIdentity.setPersonIdV2(accountForDirectedId.getPersonIdV2());
        commsCoreIdentity.setSignedInUser(accountForDirectedId.isSignedInUser());
        commsCoreIdentity.setCommsProvisioned(accountForDirectedId.isCommsProvisioned());
        commsCoreIdentity.setEnrolledInAlexa(accountForDirectedId.isEnrolledInAlexa());
        commsCoreIdentity.setProvisioningStatus(identityV2.getCommsProvisionStatus());
        return commsCoreIdentity;
    }

    private MarketplaceInfo parseMarketplaceInfo(Marketplace marketplace) {
        MarketplaceInfo marketplaceInfo = new MarketplaceInfo();
        if (marketplace != null) {
            String str = null;
            marketplaceInfo.setMarketPlaceCountryCode(marketplace.getCountryCode() != null ? marketplace.getCountryCode().toString() : null);
            marketplaceInfo.setMarketPlaceId(marketplace.getObfuscatedId() != null ? marketplace.getObfuscatedId().toString() : null);
            if (marketplace.getCountryCode() != null) {
                str = marketplace.getCountryCode().toString();
            }
            marketplaceInfo.setPfmCode(str);
        }
        return marketplaceInfo;
    }

    private Name parseName(com.amazon.commscore.commsidentity.repo.model.Name name) {
        Name name2 = new Name();
        if (name != null) {
            name2.setFirstName(name.getFirstName());
            name2.setLastName(name.getLastName());
            name2.setNickName(name.getNickName());
            name2.setPhoneticFirstName(name.getPhoneticFirstName());
            name2.setPhoneticLastName(name.getPhoneticLastName());
            name2.setPronunciationFirstName(name.getPronunciationFirstName());
            name2.setPronunciationLastName(name.getPronunciationLastName());
            name2.setSourceNickName(name.getSourceNickName());
        }
        return name2;
    }

    public Flowable<CommsCoreIdentity> getCommsCoreIdentity() {
        final UserIdentity user = this.identityServiceLazy.mo358get().getUser(TAG);
        if (user != null && user.getUserProfile() != null && user.getUserProfile().getDirectedId() != null) {
            String directedId = user.getUserProfile().getDirectedId();
            return this.commsIdentityProvider.getIdentityV2(directedId).zipWith(this.commsIdentityProvider.getAccount(directedId), $$Lambda$2JRu53QjpppxiYyMIXxupk78_lI.INSTANCE).map(new Function() { // from class: com.amazon.commscore.commsidentity.implementation.-$$Lambda$CommsCoreIdentityViewModel$hzXsVdkhO_04A0D7aWvsd5npDiA
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return CommsCoreIdentityViewModel.this.lambda$getCommsCoreIdentity$0$CommsCoreIdentityViewModel(user, (Pair) obj);
                }
            }).doOnError($$Lambda$CommsCoreIdentityViewModel$zpZI3EkHgfYIXue8xqTjEbi7BMM.INSTANCE);
        }
        return Flowable.empty();
    }

    public Observable<String[]> getCookiesForDirectedId(String str, String str2) {
        return this.identityServiceLazy.mo358get().getCookiesFromDirectedId(str, str2);
    }

    public /* synthetic */ CommsCoreIdentity lambda$getCommsCoreIdentity$0$CommsCoreIdentityViewModel(UserIdentity userIdentity, Pair pair) throws Throwable {
        IdentityV2 identityV2 = (IdentityV2) pair.first;
        AccountForDirectedId accountForDirectedId = (AccountForDirectedId) pair.second;
        if (identityV2 != null) {
            if (accountForDirectedId != null) {
                return parseAndCombineIdentityData(userIdentity, identityV2, accountForDirectedId);
            }
            throw new RuntimeException("Invalid Account");
        }
        throw new RuntimeException("Invalid identityV2");
    }

    public Completable refreshCommsIdentity() {
        UserIdentity user = this.identityServiceLazy.mo358get().getUser(TAG);
        if (user != null && user.getUserProfile() != null && user.getUserProfile().getDirectedId() != null) {
            return this.commsIdentityProvider.refreshCommsIdentity(user.getUserProfile().getDirectedId());
        }
        return Completable.error(new RuntimeException("DirectedId not available"));
    }
}
