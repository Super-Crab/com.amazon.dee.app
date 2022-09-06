package com.amazon.alexa.identity;

import android.content.Context;
import android.util.Log;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.identity.api.UserIdentityRepository;
import com.amazon.alexa.identity.api.UserIdentityStorage;
import com.amazon.alexa.identity.api.UserProfile;
import com.amazon.alexa.identity.api.UserProfileManager;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.marketplace.api.MarketplaceService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import java.util.concurrent.ExecutionException;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
/* loaded from: classes9.dex */
public class DefaultUserIdentityRepository implements UserIdentityRepository {
    private static final String TAG = Utils.tag(DefaultUserIdentityRepository.class);
    private final Context context;
    private final CoralService coralService;
    private final EnvironmentService environmentService = (EnvironmentService) GeneratedOutlineSupport1.outline20(EnvironmentService.class);
    private final MAPAccountManager mapAccountManager;
    private final MarketplaceService marketplaceService;
    private final UserProfileManager profileManager;
    private final UserIdentityStorage storage;
    private final TokenManagement tokenManagement;
    private final UserIdentityMapper userMapper;

    public DefaultUserIdentityRepository(Context context, CoralService coralService, MarketplaceService marketplaceService, UserIdentityMapper userIdentityMapper, UserIdentityStorage userIdentityStorage, MAPAccountManager mAPAccountManager, TokenManagement tokenManagement, UserProfileManager userProfileManager) {
        this.context = context;
        this.coralService = coralService;
        this.marketplaceService = marketplaceService;
        this.userMapper = userIdentityMapper;
        this.storage = userIdentityStorage;
        this.mapAccountManager = mAPAccountManager;
        this.tokenManagement = tokenManagement;
        this.profileManager = userProfileManager;
    }

    private void addProfileData(UserIdentity.Builder builder) {
        UserIdentity cachedIdentity = getCachedIdentity();
        UserProfile userProfile = null;
        if (isProfileOobeEnabled(cachedIdentity)) {
            builder.withUserProfile(cachedIdentity == null ? null : cachedIdentity.getUserProfile());
        } else {
            builder.withUserProfile(this.profileManager.getCurrentProfile());
        }
        if (cachedIdentity != null) {
            userProfile = cachedIdentity.getDelegatedProfile();
        }
        builder.withDelegatedProfile(userProfile);
    }

    private Observable.Transformer<UserIdentity, UserIdentity> getAccessToken() {
        return new Observable.Transformer() { // from class: com.amazon.alexa.identity.-$$Lambda$DefaultUserIdentityRepository$OI6zEBuYlZeP6NgVMiSSHy5AKVA
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return DefaultUserIdentityRepository.this.lambda$getAccessToken$3$DefaultUserIdentityRepository((Observable) obj);
            }
        };
    }

    @Override // com.amazon.alexa.identity.api.UserIdentityRepository
    public void clear() {
        this.storage.clear();
    }

    @Override // com.amazon.alexa.identity.api.UserIdentityRepository
    public Observable<UserIdentity> get(UserIdentityRepository.FetchOptions fetchOptions) {
        UserIdentity cachedIdentity;
        if (fetchOptions != UserIdentityRepository.FetchOptions.FromServer && (cachedIdentity = getCachedIdentity()) != null) {
            UserProfile currentProfile = this.profileManager.getCurrentProfile();
            UserProfile userProfile = cachedIdentity.getUserProfile();
            if ((userProfile == null && currentProfile != null) || (userProfile != null && !userProfile.equals(currentProfile))) {
                if (isProfileOobeEnabled(cachedIdentity)) {
                    Log.i(TAG, "IdentityV2 : Profile OOBE is Enabled. Not updating the Cached Profile.");
                } else {
                    Log.i(TAG, "IdentityV2 : Cached Profile does not match the Current profile. Updating.");
                    cachedIdentity = UserIdentity.from(cachedIdentity).withUserProfile(currentProfile).build();
                    this.storage.put(cachedIdentity);
                }
            }
            return Observable.just(cachedIdentity);
        } else if (fetchOptions == UserIdentityRepository.FetchOptions.FromCache) {
            return Observable.just(null);
        } else {
            Observable subscribeOn = this.coralService.request(String.format("/api/users/me?platform=android&version=%s", "")).get().as(UserIdentityDTO.class).toObservable().observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
            final UserIdentityMapper userIdentityMapper = this.userMapper;
            userIdentityMapper.getClass();
            return subscribeOn.map(new Func1() { // from class: com.amazon.alexa.identity.-$$Lambda$IHGyRxfhcvPFhpE1xXdSIIYUdbY
                @Override // rx.functions.Func1
                /* renamed from: call */
                public final Object mo13102call(Object obj) {
                    return UserIdentityMapper.this.map((UserIdentityDTO) obj);
                }
            }).switchMap(new Func1() { // from class: com.amazon.alexa.identity.-$$Lambda$DefaultUserIdentityRepository$12_88t6b__0xpqGEsYhSVVvxkns
                @Override // rx.functions.Func1
                /* renamed from: call */
                public final Object mo13102call(Object obj) {
                    return DefaultUserIdentityRepository.this.lambda$get$1$DefaultUserIdentityRepository((UserIdentity) obj);
                }
            }).compose(getAccessToken());
        }
    }

    @Override // com.amazon.alexa.identity.api.UserIdentityRepository
    public UserIdentity getCachedIdentity() {
        UserIdentity userIdentity = this.storage.get();
        if (userIdentity == null) {
            Log.w(TAG, "User is null in the cached storage; returning null as the cached identiy.");
            return null;
        }
        String account = this.mapAccountManager.getAccount();
        if (account != null && account.equals(userIdentity.getDirectedId())) {
            return userIdentity;
        }
        Log.w(TAG, "IdentityV2 : The directed IDs don't match; clearing the cache and returning null as the cached identity", new Throwable());
        Log.i(TAG, String.format("getCachedIdentity: currentDirectedId = %s, user.getDirectedId() = %s", account, userIdentity.getDirectedId()));
        this.storage.clear();
        return null;
    }

    @Override // com.amazon.alexa.identity.api.UserIdentityRepository
    public String getCachedIdentityId() {
        String id = this.storage.getId();
        if (id == null) {
            Log.w(TAG, "IdentityV2 : User id is null in the cached storage; returning null as the id.");
            return null;
        }
        return id;
    }

    boolean isProfileOobeEnabled(UserIdentity userIdentity) {
        try {
            if (this.environmentService.getDeviceInformation().isFireOS() || userIdentity.getFeatures() == null) {
                return false;
            }
            return userIdentity.getFeatures().contains("ALEXA_PROFILE_OOBE_DECOUPLING_ANDROID");
        } catch (Exception e) {
            Log.w(TAG, "IdentityV2 : Could not evaluate the feature availability for Profile OOBE.", e);
            return false;
        }
    }

    public /* synthetic */ Observable lambda$get$1$DefaultUserIdentityRepository(UserIdentity userIdentity) {
        final UserIdentity.Builder withDirectedId = UserIdentity.from(userIdentity).withDirectedId(this.mapAccountManager.getAccount());
        if (userIdentity.getEffectiveMarketplace() == null) {
            return this.marketplaceService.getEffectivePFM().switchMap(new Func1() { // from class: com.amazon.alexa.identity.-$$Lambda$DefaultUserIdentityRepository$EZP1zE4sjlMVeX-hT6l6B3SZrbc
                @Override // rx.functions.Func1
                /* renamed from: call */
                public final Object mo13102call(Object obj) {
                    return DefaultUserIdentityRepository.this.lambda$null$0$DefaultUserIdentityRepository(withDirectedId, (Marketplace) obj);
                }
            });
        }
        addProfileData(withDirectedId);
        UserIdentity build = withDirectedId.build();
        this.storage.put(build);
        return Observable.just(build);
    }

    public /* synthetic */ Observable lambda$getAccessToken$3$DefaultUserIdentityRepository(Observable observable) {
        return observable.flatMap(new Func1() { // from class: com.amazon.alexa.identity.-$$Lambda$DefaultUserIdentityRepository$khFjU16uVSFfmT0V_il9usPWpLI
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return DefaultUserIdentityRepository.this.lambda$null$2$DefaultUserIdentityRepository((UserIdentity) obj);
            }
        });
    }

    public /* synthetic */ Observable lambda$null$0$DefaultUserIdentityRepository(UserIdentity.Builder builder, Marketplace marketplace) {
        builder.withEffectiveMarketplace(marketplace);
        addProfileData(builder);
        UserIdentity build = builder.build();
        this.storage.put(build);
        return Observable.just(build);
    }

    public /* synthetic */ Observable lambda$null$2$DefaultUserIdentityRepository(UserIdentity userIdentity) {
        try {
            String string = this.tokenManagement.getToken(userIdentity.getDirectedId(), TokenKeys.getAccessTokenKeyForPackage(this.context.getPackageName()), null, null).get().getString("value_key");
            if (string == null) {
                return Observable.error(new IllegalStateException("Access token is not available through a token management."));
            }
            return Observable.just(UserIdentity.from(userIdentity).withAccessToken(string).build());
        } catch (MAPCallbackErrorException | InterruptedException | ExecutionException e) {
            return Observable.error(e);
        }
    }

    @Override // com.amazon.alexa.identity.api.UserIdentityRepository
    public void save(UserIdentity userIdentity) {
        this.storage.put(userIdentity);
    }
}
