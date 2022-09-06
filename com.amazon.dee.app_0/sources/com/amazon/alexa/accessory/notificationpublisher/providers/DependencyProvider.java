package com.amazon.alexa.accessory.notificationpublisher.providers;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.CookieManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationServiceCommunicator;
import com.amazon.alexa.accessory.notificationpublisher.storage.AsyncLocalStorage;
import com.amazon.alexa.accessory.notificationpublisher.storage.NativeLocalStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.utils.AvsProperties;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsCapabilityAgent;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import java.util.HashMap;
import okhttp3.OkHttpClient;
/* loaded from: classes.dex */
public final class DependencyProvider {
    private static final String TAG = "DependencyProvider";
    private static AvsProperties avsProperties;
    private static Accessories clientAccessories;
    private static Context context;
    private static Lazy<DeviceInformation> deviceInformation;
    private static Lazy<EnvironmentService> environmentService;
    private static Lazy<EventBus> eventBus;
    private static ExternalNotificationsCapabilityAgent externalNotificationsCapabilityAgent;
    private static Lazy<FeatureServiceV2> featureServiceV2;
    private static Lazy<OkHttpClient> httpClient;
    private static Lazy<IdentityService> identityService;
    private static Lazy<MetricsService> metricsService;
    private static Lazy<NativeLocalStorageModule> nativeLocalStorageModule;
    private static NotificationServiceCommunicator notificationServiceCommunicator;

    private DependencyProvider() {
    }

    public static void create(@NonNull Context context2, @NonNull final Lazy<Cache<AppDataCacheEntry>> lazy, @NonNull Lazy<OkHttpClient> lazy2) {
        context = context2;
        deviceInformation = new Lazy<DeviceInformation>() { // from class: com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // dagger.Lazy
            /* renamed from: get */
            public DeviceInformation mo358get() {
                return (DeviceInformation) GeneratedOutlineSupport1.outline20(DeviceInformation.class);
            }
        };
        environmentService = new Lazy<EnvironmentService>() { // from class: com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // dagger.Lazy
            /* renamed from: get */
            public EnvironmentService mo358get() {
                return (EnvironmentService) GeneratedOutlineSupport1.outline20(EnvironmentService.class);
            }
        };
        eventBus = new Lazy<EventBus>() { // from class: com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // dagger.Lazy
            /* renamed from: get */
            public EventBus mo358get() {
                return (EventBus) GeneratedOutlineSupport1.outline20(EventBus.class);
            }
        };
        featureServiceV2 = new Lazy<FeatureServiceV2>() { // from class: com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // dagger.Lazy
            /* renamed from: get */
            public FeatureServiceV2 mo358get() {
                return (FeatureServiceV2) GeneratedOutlineSupport1.outline20(FeatureServiceV2.class);
            }
        };
        identityService = new Lazy<IdentityService>() { // from class: com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // dagger.Lazy
            /* renamed from: get */
            public IdentityService mo358get() {
                return (IdentityService) GeneratedOutlineSupport1.outline20(IdentityService.class);
            }
        };
        metricsService = new Lazy<MetricsService>() { // from class: com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // dagger.Lazy
            /* renamed from: get */
            public MetricsService mo358get() {
                return (MetricsService) GeneratedOutlineSupport1.outline20(MetricsService.class);
            }
        };
        nativeLocalStorageModule = new Lazy<NativeLocalStorageModule>() { // from class: com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // dagger.Lazy
            /* renamed from: get */
            public NativeLocalStorageModule mo358get() {
                return new NativeLocalStorageModule((Cache) Lazy.this.mo358get());
            }
        };
        httpClient = lazy2;
        avsProperties = new AvsProperties(context2);
    }

    public static AlexaServicesConnection createAlexaServiceConnection() {
        return new AlexaServicesConnection(getContext());
    }

    public static AsyncLocalStorage createAsyncLocalStorage(String str) {
        return new AsyncLocalStorage(getNativeLocalStorageModule(), str);
    }

    public static String getAccessToken(String str) {
        return getIdentityService().getAccessToken(str);
    }

    public static AvsProperties getAvsProperties() {
        return avsProperties;
    }

    public static Accessories getClientAccessories() {
        return clientAccessories;
    }

    public static Context getContext() {
        return context;
    }

    public static String getCookie(String str) {
        CookieManager cookieManager = getCookieManager();
        return cookieManager == null ? "" : cookieManager.getCookie(str);
    }

    public static CookieManager getCookieManager() {
        try {
            return CookieManager.getInstance();
        } catch (Exception e) {
            HashMap hashMap = new HashMap();
            hashMap.put(MetricsConstants.CUSTOM_VALUES_KEY, MetricsRecorder.customAttributesForException(e, 512));
            MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FocusFilter_cookieManager_exception_");
            outline107.append(e.getClass().getSimpleName());
            metricsRecorder.recordCounter(outline107.toString(), hashMap);
            Log.e(TAG, "Caught Exception when call CookieManager.getInstance().", e);
            return null;
        }
    }

    public static DeviceInformation getDeviceInformation() {
        return deviceInformation.mo358get();
    }

    public static String getDeviceType() throws DeviceInformationException {
        return getDeviceInformation().getDeviceType();
    }

    public static EnvironmentService getEnvironmentService() {
        return environmentService.mo358get();
    }

    public static EventBus getEventBus() {
        return eventBus.mo358get();
    }

    @Nullable
    public static ExternalNotificationsCapabilityAgent getExternalNotificationsCapabilityAgent() {
        return externalNotificationsCapabilityAgent;
    }

    public static FeatureServiceV2 getFeatureServiceV2() {
        return featureServiceV2.mo358get();
    }

    public static OkHttpClient getHttpClient() {
        return httpClient.mo358get();
    }

    public static IdentityService getIdentityService() {
        return identityService.mo358get();
    }

    public static Handler getMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    public static MetricsService getMetricsService() {
        return metricsService.mo358get();
    }

    public static NativeLocalStorageModule getNativeLocalStorageModule() {
        return nativeLocalStorageModule.mo358get();
    }

    public static NotificationServiceCommunicator getNotificationServiceCommunicator() {
        return notificationServiceCommunicator;
    }

    public static String getSerialNumber() throws DeviceInformationException {
        return getDeviceInformation().getDeviceSerialNumber();
    }

    public static void setAccessoriesClient(@NonNull Context context2) {
        clientAccessories = Accessories.Shared.INSTANCE.get(context2);
    }

    public static void setExternalNotificationsCapabilityAgent(ExternalNotificationsCapabilityAgent externalNotificationsCapabilityAgent2) {
        externalNotificationsCapabilityAgent = externalNotificationsCapabilityAgent2;
    }

    public static void setNotificationServiceCommunicator(NotificationServiceCommunicator notificationServiceCommunicator2) {
        notificationServiceCommunicator = notificationServiceCommunicator2;
    }
}
