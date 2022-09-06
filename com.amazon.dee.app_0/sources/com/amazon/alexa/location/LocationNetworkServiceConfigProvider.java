package com.amazon.alexa.location;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.marketplace.Marketplace;
import com.amazon.alexa.protocols.marketplace.MarketplaceService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class LocationNetworkServiceConfigProvider implements MainActivityLifecycleObserver {
    private static final String TAG = "LocationNetworkServiceConfigProvider";
    private final EnvironmentService environmentService;
    private final IdentityService identityService;
    private boolean isRunningBackground = true;
    private final MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar;
    private final MarketplaceService marketplaceService;
    private final LazyComponent<Mobilytics> mobilytics;
    private final PersonIdProvider personIdProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocationNetworkServiceConfigProvider() {
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        this.environmentService = (EnvironmentService) componentRegistry.get(EnvironmentService.class).get();
        this.marketplaceService = (MarketplaceService) componentRegistry.get(MarketplaceService.class).get();
        this.personIdProvider = (PersonIdProvider) componentRegistry.get(PersonIdProvider.class).get();
        this.identityService = (IdentityService) componentRegistry.getLazy(IdentityService.class).mo10268get();
        this.mobilytics = componentRegistry.getLazy(Mobilytics.class);
        this.mainActivityLifecycleObserverRegistrar = (MainActivityLifecycleObserverRegistrar) componentRegistry.get(MainActivityLifecycleObserverRegistrar.class).get();
        this.mainActivityLifecycleObserverRegistrar.addObserver(this);
    }

    private String getRunningStateMetricName(String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
        outline107.append(this.isRunningBackground ? "_background" : "_foreground");
        return outline107.toString();
    }

    @NonNull
    @VisibleForTesting
    Single<String> collectAuthToken() {
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.location.-$$Lambda$LocationNetworkServiceConfigProvider$N7a2JZ4l6YRj_ASKkiB28u6VPsI
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return LocationNetworkServiceConfigProvider.this.lambda$collectAuthToken$1$LocationNetworkServiceConfigProvider();
            }
        });
    }

    @NonNull
    @VisibleForTesting
    String collectBuildStage(String str) {
        String buildStage = this.environmentService.getBuildStage();
        if (buildStage == null) {
            this.mobilytics.mo10268get().recordOccurrence(getRunningStateMetricName("missing_build_stage"), true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            return "prod";
        }
        return buildStage;
    }

    @Nullable
    @VisibleForTesting
    String collectPersonId(String str) {
        String personId = this.personIdProvider.getPersonId();
        if (TextUtils.isEmpty(personId)) {
            this.mobilytics.mo10268get().recordOccurrence(getRunningStateMetricName("missing_person_id"), true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        }
        return personId;
    }

    @NonNull
    @VisibleForTesting
    String collectPreferredMarketplace(String str) {
        try {
            return this.marketplaceService.getEffectivePFM().toBlocking().single().getCountryCode().toString();
        } catch (NullPointerException | NoSuchElementException unused) {
            this.mobilytics.mo10268get().recordOccurrence(getRunningStateMetricName("missing_marketplace_code"), true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            return Marketplace.USA.getCountryCode().toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Single<LocationNetworkServiceConfig> getConfig(final String str) {
        return collectAuthToken().map(new Function() { // from class: com.amazon.alexa.location.-$$Lambda$LocationNetworkServiceConfigProvider$6ePAzVLfT7hnQSbyVF1Z-omhlHk
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return LocationNetworkServiceConfigProvider.this.lambda$getConfig$0$LocationNetworkServiceConfigProvider(str, (String) obj);
            }
        });
    }

    public /* synthetic */ String lambda$collectAuthToken$1$LocationNetworkServiceConfigProvider() throws Exception {
        return this.identityService.getAccessToken(TAG);
    }

    public /* synthetic */ LocationNetworkServiceConfig lambda$getConfig$0$LocationNetworkServiceConfigProvider(String str, String str2) throws Throwable {
        if (TextUtils.isEmpty(str2)) {
            this.mobilytics.mo10268get().recordOccurrence(getRunningStateMetricName("missing_access_token"), true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        }
        return new LocationNetworkServiceConfig(str2, collectBuildStage(str), collectPreferredMarketplace(str), collectPersonId(str));
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityCreated() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityDestroy() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityPause() {
        this.isRunningBackground = true;
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityResume() {
        this.isRunningBackground = false;
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStart() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStop() {
    }
}
