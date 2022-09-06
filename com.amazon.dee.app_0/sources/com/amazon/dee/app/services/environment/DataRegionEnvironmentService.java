package com.amazon.dee.app.services.environment;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.identity.api.UserIdentityStorage;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.marketplace.api.MarketplaceEndpoints;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.marketplace.MarketplaceConfiguration;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import dagger.Lazy;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Map;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
/* loaded from: classes12.dex */
public class DataRegionEnvironmentService implements EnvironmentService {
    private static final String DEFAULT_AWS_REGION = "us-east-1";
    public static final String ENDPOINTS_CHANGED = "ENDPOINTS_CHANGED";
    private static final String HTTPS_PREFIX = "https://";
    private static final String TAG = "DataRegionEnvironmentService";
    private Context context;
    @VisibleForTesting
    Lazy<CoralService> coralService;
    @VisibleForTesting
    Endpoints endpoints;
    @VisibleForTesting
    PersistentEndpointsStorage endpointsStorage;
    @VisibleForTesting
    EventBus eventBus;
    @VisibleForTesting
    boolean haveEndpointsBeenRetrieved;
    @VisibleForTesting
    Lazy<IdentityService> identityService;
    private Lazy<Mobilytics> mobilytics;

    public DataRegionEnvironmentService(@NonNull Context context, @NonNull EventBus eventBus, @NonNull UserIdentityStorage userIdentityStorage, @NonNull PersistentEndpointsStorage persistentEndpointsStorage, @NonNull Lazy<CoralService> lazy, @NonNull Lazy<IdentityService> lazy2, @NonNull Lazy<Mobilytics> lazy3) {
        this.coralService = lazy;
        this.identityService = lazy2;
        this.context = context;
        this.eventBus = eventBus;
        this.endpointsStorage = persistentEndpointsStorage;
        this.mobilytics = lazy3;
        Log.i(TAG, "Constructing endpoints from cache");
        Endpoints endpoints = persistentEndpointsStorage.get();
        boolean z = false;
        updateEndpoints(endpoints, false);
        UserIdentity userIdentity = userIdentityStorage.get();
        if (userIdentity != null && userIdentity.hasFeature("ALEXA_CORE_DATA_REGION_ENDPOINTS_ANDROID")) {
            z = true;
        }
        this.haveEndpointsBeenRetrieved = z;
        eventBus.getNewSubscriber().subscribeFilter(new EventTypeMessageFilter("ui:loading:started"), new MessageHandler() { // from class: com.amazon.dee.app.services.environment.-$$Lambda$DataRegionEnvironmentService$X_1XzJ9CiWygWfUzXLecP_QOvTI
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DataRegionEnvironmentService.this.lambda$new$0$DataRegionEnvironmentService(message);
            }
        });
        eventBus.getNewSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.services.environment.-$$Lambda$DataRegionEnvironmentService$v8kR3pFeGA1sPph8AJOZe0oAo3I
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DataRegionEnvironmentService.this.lambda$new$1$DataRegionEnvironmentService(message);
            }
        });
    }

    @NonNull
    private String getAwsRegion() {
        String awsRegion = this.endpoints.getAwsRegion();
        return awsRegion == null ? "us-east-1" : awsRegion;
    }

    private void initializeEndpointsFromDevice() {
        Marketplace findMarketplaceByName;
        Log.i(TAG, "Initializing endpoints from device heuristics");
        MarketplaceConfiguration marketplaceConfiguration = MarketplaceConfiguration.USA;
        Context context = this.context;
        if (context != null && (findMarketplaceByName = Marketplace.findMarketplaceByName(context.getResources().getConfiguration().locale.getCountry(), null)) != null) {
            marketplaceConfiguration = MarketplaceConfiguration.forMarketplace(findMarketplaceByName);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(HTTPS_PREFIX);
        outline107.append(marketplaceConfiguration.getApiGatewayHost());
        String sb = outline107.toString();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(HTTPS_PREFIX);
        outline1072.append(marketplaceConfiguration.getCoralHost());
        String sb2 = outline1072.toString();
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107(HTTPS_PREFIX);
        outline1073.append(marketplaceConfiguration.getWebHost());
        String sb3 = outline1073.toString();
        String retailHost = marketplaceConfiguration.getRetailHost();
        String outline72 = GeneratedOutlineSupport1.outline72("https://www.", retailHost);
        String skillsStoreEndpoint = marketplaceConfiguration.getSkillsStoreEndpoint();
        Log.i(TAG, "Setting endpoints from device");
        updateEndpoints(new Endpoints(sb, sb2, sb3, retailHost, outline72, "us-east-1", skillsStoreEndpoint), false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$handleColdStart$3(Throwable th) {
        String str = TAG;
        Log.i(str, "Error fetching endpoints from server: " + th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$handleIdentityChanged$5(Throwable th) {
        String str = TAG;
        Log.i(str, "Error fetching endpoints from server: " + th);
    }

    private void updateEndpoints(@Nullable Endpoints endpoints, boolean z) {
        String str = TAG;
        Log.i(str, "Updating endpoints from: " + endpoints + ", Caching: " + z);
        if (endpoints != null) {
            if (!endpoints.equals(this.endpoints)) {
                this.endpoints = endpoints;
                publishEndpointsChangedEvent();
            }
            if (!z) {
                return;
            }
            this.endpointsStorage.put(endpoints);
            return;
        }
        resetValues();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public MarketplaceEndpoints forMarketplace(@NonNull Marketplace marketplace) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @Nullable
    @SuppressFBWarnings({"NP_METHOD_RETURN_RELAXING_ANNOTATION"})
    public String getApiGatewayEndpoint() {
        return this.endpoints.getAlexaApiUrl();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @Nullable
    @SuppressFBWarnings({"NP_METHOD_RETURN_RELAXING_ANNOTATION"})
    public String getApiGatewayHost() {
        String alexaApiUrl = this.endpoints.getAlexaApiUrl();
        if (alexaApiUrl == null) {
            return null;
        }
        return alexaApiUrl.substring(8);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getAuthWebAssociationHandle() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getAuthWebHost() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public String getBuildFlavor() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public String getBuildStage() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @Nullable
    @SuppressFBWarnings({"NP_METHOD_RETURN_RELAXING_ANNOTATION"})
    public String getCoralEndpoint() {
        return this.endpoints.getWebsiteApiUrl();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @Nullable
    @SuppressFBWarnings({"NP_METHOD_RETURN_RELAXING_ANNOTATION"})
    public String getCoralHost() {
        String websiteApiUrl = this.endpoints.getWebsiteApiUrl();
        if (websiteApiUrl == null) {
            return null;
        }
        return websiteApiUrl.substring(8);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public String getCountryCode() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public DeviceInformation getDeviceInformation() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @NonNull
    @VisibleForTesting
    Observable<Endpoints> getEndpointsFromServer() {
        Log.i(TAG, "Updating endpoints from /api/endpoints");
        this.mobilytics.mo358get().recordOccurrence(AlexaMetricsConstants.MetricEvents.ENDPOINTS_REQUEST_ATTEMPT, true, "Application", TAG, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        return this.coralService.mo358get().request("/api/endpoints").get().as(Endpoints.class).toObservable().observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).first().doOnNext(new Action1() { // from class: com.amazon.dee.app.services.environment.-$$Lambda$DataRegionEnvironmentService$KblxpHmOaCxZImycqCZ9M5XMPyw
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DataRegionEnvironmentService.this.lambda$getEndpointsFromServer$6$DataRegionEnvironmentService((Endpoints) obj);
            }
        }).doOnError(new Action1() { // from class: com.amazon.dee.app.services.environment.-$$Lambda$DataRegionEnvironmentService$XvWI7tYikMkc2E3xSyjyuHHF1yg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DataRegionEnvironmentService.this.lambda$getEndpointsFromServer$7$DataRegionEnvironmentService((Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public Marketplace getMarketplace() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @Nullable
    public <T> T getMobilyticsEndpoint(@NonNull Map<String, T> map) {
        String awsRegion = getAwsRegion();
        if (awsRegion != null && map.containsKey(awsRegion)) {
            String str = "Using mobilytics endpoint for dataRegion " + awsRegion;
            return map.get(awsRegion);
        }
        T t = map.get("us-east-1");
        Log.i(TAG, "Using default endpoint for mobilytics");
        return t;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getPrefetchUrl() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @Nullable
    @SuppressFBWarnings({"NP_METHOD_RETURN_RELAXING_ANNOTATION"})
    public String getRetailEndpoint() {
        return this.endpoints.getRetailUrl();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @Nullable
    @SuppressFBWarnings({"NP_METHOD_RETURN_RELAXING_ANNOTATION"})
    public String getRetailHost() {
        return this.endpoints.getRetailDomain();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @Nullable
    @SuppressFBWarnings({"NP_METHOD_RETURN_RELAXING_ANNOTATION"})
    public String getSkillsStoreEndpoint() {
        return this.endpoints.getSkillsStoreUrl();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public String getVersionName() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @Nullable
    @SuppressFBWarnings({"NP_METHOD_RETURN_RELAXING_ANNOTATION"})
    public String getWebEndpoint() {
        return this.endpoints.getWebsiteUrl();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @Nullable
    @SuppressFBWarnings({"NP_METHOD_RETURN_RELAXING_ANNOTATION"})
    public String getWebHost() {
        String websiteUrl = this.endpoints.getWebsiteUrl();
        if (websiteUrl == null) {
            return null;
        }
        return websiteUrl.substring(8);
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    @NonNull
    public String getWebIndex() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @VisibleForTesting
    void handleColdStart() {
        Log.i(TAG, "Handling cold start");
        UserIdentity user = this.identityService.mo358get().getUser(TAG);
        if (user == null || !user.hasFeature("ALEXA_CORE_DATA_REGION_ENDPOINTS_ANDROID")) {
            return;
        }
        Log.i(TAG, "Cold start, updating endpoints from server");
        getEndpointsFromServer().subscribe(new Action1() { // from class: com.amazon.dee.app.services.environment.-$$Lambda$DataRegionEnvironmentService$FNhwd03awuqA53bfc-WJUaXsE3g
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DataRegionEnvironmentService.this.lambda$handleColdStart$2$DataRegionEnvironmentService((Endpoints) obj);
            }
        }, $$Lambda$DataRegionEnvironmentService$oshvUo6nI3Nb4fMzG8d1SShbeho.INSTANCE);
    }

    @VisibleForTesting
    synchronized void handleIdentityChanged() {
        Log.i(TAG, "Handling IDENTITY_CHANGED event");
        UserIdentity user = this.identityService.mo358get().getUser(TAG);
        boolean z = user != null && user.hasFeature("ALEXA_CORE_DATA_REGION_ENDPOINTS_ANDROID");
        if (!this.haveEndpointsBeenRetrieved && z) {
            Log.i(TAG, "Data Region Endpoints feature became enabled, updating endpoints from server");
            getEndpointsFromServer().subscribe(new Action1() { // from class: com.amazon.dee.app.services.environment.-$$Lambda$DataRegionEnvironmentService$8LkF4ysz_sRf0PECIUbUxxhzCyc
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    DataRegionEnvironmentService.this.lambda$handleIdentityChanged$4$DataRegionEnvironmentService((Endpoints) obj);
                }
            }, $$Lambda$DataRegionEnvironmentService$TVtxxqPse1Re5qk9NkdIhxFME48.INSTANCE);
        } else if (this.haveEndpointsBeenRetrieved && !z) {
            Log.i(TAG, "Data Region Endpoints feature became disabled, resetting endpoints");
            resetValues();
        }
        this.haveEndpointsBeenRetrieved = z;
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isDebugBuild() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isDevelopmentFabric() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isKnownUrl(EnvironmentService.KnownUrlType knownUrlType, String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isOutsideWebAppUrl(String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isProductionFabric() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWebAppUrl(String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWebSigninUrl(String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWebTivUrl(Uri uri) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWebWarmSeatUrl(String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWithinAlexaWebViewNonIndex(String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWithinExternalUIWebView(String str, String str2) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWithinHostList(String str, String[] strArr) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean isWithinWebAppUrl(String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    public /* synthetic */ void lambda$getEndpointsFromServer$6$DataRegionEnvironmentService(Endpoints endpoints) {
        Log.i(TAG, "Succeeded getting endpoints from /api/endpoints");
        MobilyticsOperationalEvent createOperationalEvent = this.mobilytics.mo358get().createOperationalEvent(AlexaMetricsConstants.MetricEvents.AuthError.ENDPOINTS_UPDATE, OperationalEventType.DIAGNOSTIC, AlexaMetricsConstants.MetricsComponents.CORAL_SERVICE, TAG, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        if (endpoints != null) {
            createOperationalEvent.setSourceContext(endpoints.toString());
        }
        this.mobilytics.mo358get().recordOperationalEvent(createOperationalEvent);
        this.mobilytics.mo358get().recordOccurrence(AlexaMetricsConstants.MetricEvents.ENDPOINTS_REQUEST_SUCCESS_RATE, true, "Application", TAG, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
    }

    public /* synthetic */ void lambda$getEndpointsFromServer$7$DataRegionEnvironmentService(Throwable th) {
        String str = TAG;
        Log.i(str, "Failed getting endpoints from /api/endpoints: " + th);
        this.mobilytics.mo358get().recordOccurrence(AlexaMetricsConstants.MetricEvents.ENDPOINTS_REQUEST_SUCCESS_RATE, false, "Application", TAG, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        if (th instanceof CoralServiceException) {
            Mobilytics mo358get = this.mobilytics.mo358get();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ERROR_");
            outline107.append(((CoralServiceException) th).getStatusCode());
            mo358get.recordCriticalEvent(AlexaMetricsConstants.MetricEvents.ENDPOINTS_REQUEST_FAILURE, AlexaMetricsConstants.MetricEvents.ENDPOINTS_REQUEST_FAILURE, outline107.toString(), TAG, th, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        }
        Mobilytics mo358get2 = this.mobilytics.mo358get();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ERROR_");
        outline1072.append(th.getClass().getCanonicalName());
        mo358get2.recordCriticalEvent(AlexaMetricsConstants.MetricEvents.ENDPOINTS_CONSTRUCTION_FAILURE, AlexaMetricsConstants.MetricEvents.ENDPOINTS_CONSTRUCTION_FAILURE, outline1072.toString(), TAG, th, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
    }

    public /* synthetic */ void lambda$handleColdStart$2$DataRegionEnvironmentService(Endpoints endpoints) {
        updateEndpoints(endpoints, true);
    }

    public /* synthetic */ void lambda$handleIdentityChanged$4$DataRegionEnvironmentService(Endpoints endpoints) {
        updateEndpoints(endpoints, true);
    }

    public /* synthetic */ void lambda$new$0$DataRegionEnvironmentService(Message message) {
        handleColdStart();
    }

    public /* synthetic */ void lambda$new$1$DataRegionEnvironmentService(Message message) {
        handleIdentityChanged();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void overrideHost(String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @VisibleForTesting
    void publishEndpointsChangedEvent() {
        Log.i(TAG, "Publishing ENDPOINTS_CHANGED event");
        this.eventBus.publish(new Message.Builder().setEventType("ENDPOINTS_CHANGED").setPayload(TextUtils.join(",", new String[]{getCoralEndpoint(), getWebEndpoint(), getRetailEndpoint()})).build());
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void resetValues() {
        Log.i(TAG, "Resetting endpoints, clearing Endpoints storage");
        this.endpointsStorage.clear();
        initializeEndpointsFromDevice();
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void setAuthWebAssociationHandle(@NonNull String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void setAuthWebHost(@NonNull String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void setCoralHost(@NonNull String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void setMarketplace(@NonNull Marketplace marketplace) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public void setWebHost(@NonNull String str) {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }

    @Override // com.amazon.alexa.protocols.environment.EnvironmentService
    public boolean shouldOverrideHosts() {
        throw new UnsupportedOperationException("This operation is currently unsupported.");
    }
}
