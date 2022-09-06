package com.amazon.alexa.location;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.location.phase3.ActiveGeofences;
import com.amazon.alexa.location.phase3.ConfigurationManager;
import com.amazon.alexa.location.phase3.DwellTracker;
import com.amazon.alexa.location.phase3.LocationDataStoreService;
import com.amazon.alexa.location.phase3.Phase3LocationService;
import com.amazon.alexa.location.phase3.SensorController;
import com.amazon.alexa.location.phase3.evaluator.LocationEventEvaluator;
import com.amazon.alexa.location.phase3.evaluator.LocationEventQueue;
import com.amazon.alexa.location.phase3.evaluator.TriggerEventEvaluator;
import com.amazon.alexa.location.phase3.sensor.activity.ActivitySensor;
import com.amazon.alexa.location.phase3.sensor.alexageofence.AlexaGeofenceDetector;
import com.amazon.alexa.location.phase3.sensor.motiondetection.MotionDetector;
import com.amazon.alexa.location.phase3.sensor.nativelocation.NativeLocationSensor;
import com.amazon.alexa.location.phase3.sensor.osgeofence.OsGeofenceDetector;
import com.amazon.alexa.location.phase3.sensor.wifidetection.WifiChangeDetector;
import com.amazon.alexa.location.phase3.sensor.wifidetection.WifiDetectionSensor;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.location.utils.SystemClock;
import com.amazon.alexa.location.utils.WriteToFile;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.network.api.HttpClient;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import okhttp3.OkHttpClient;
import rx.Observable;
/* loaded from: classes9.dex */
public final class LocationServiceFactory {
    private static final String METRIC_COMPONENT_NAME = MobilyticsUtil.getComponentName("LocationServiceFactory");

    private LocationServiceFactory() {
    }

    @NonNull
    public static LocationService create(Context context, LocationProvider locationProvider, OkHttpClient okHttpClient, Cache<AppDataCacheEntry> cache) {
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        IdentityService identityService = (IdentityService) componentRegistry.get(IdentityService.class).get();
        PersonIdProvider personIdProvider = (PersonIdProvider) componentRegistry.get(PersonIdProvider.class).get();
        EventBus eventBus = (EventBus) componentRegistry.get(EventBus.class).get();
        MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar = (MainActivityLifecycleObserverRegistrar) componentRegistry.get(MainActivityLifecycleObserverRegistrar.class).get();
        LazyComponent lazy = componentRegistry.getLazy(Mobilytics.class);
        LazyComponent lazy2 = componentRegistry.getLazy(CrashReporter.class);
        LocationNetworkServiceConfigProvider locationNetworkServiceConfigProvider = new LocationNetworkServiceConfigProvider();
        Gson create = new GsonBuilder().create();
        AlexaLocationPermissionService alexaLocationPermissionService = new AlexaLocationPermissionService(context);
        GoogleApiService googleApiService = new GoogleApiService(context);
        AlexaLocationNetworkService alexaLocationNetworkService = new AlexaLocationNetworkService(((HttpClient) componentRegistry.getLazy(HttpClient.class).mo10268get()).okHttpClientWithBearerAuth(), create, lazy, alexaLocationPermissionService, context);
        EncryptedLocationDataService encryptedLocationDataService = new EncryptedLocationDataService(cache, create, lazy);
        LocationDataStoreService locationDataStoreService = new LocationDataStoreService(context, lazy);
        LocationManager locationManager = new LocationManager(alexaLocationNetworkService, encryptedLocationDataService, lazy, new GeofenceController(context, alexaLocationPermissionService, lazy, create), locationProvider, create, lazy2);
        Observable<UserIdentity> subject = new UserChangeSubjectFactory(eventBus, identityService).getSubject();
        DefaultLocationService defaultLocationService = new DefaultLocationService(identityService, personIdProvider, mainActivityLifecycleObserverRegistrar, lazy, locationManager, alexaLocationPermissionService, new GeofenceEventHandler(eventBus, subject, lazy, lazy2), locationDataStoreService, new LocationSettingsRecorder(lazy, locationDataStoreService, locationManager, alexaLocationPermissionService, lazy2, googleApiService), lazy2, locationNetworkServiceConfigProvider, subject, context);
        if (((FeatureServiceV2) componentRegistry.getLazy(FeatureServiceV2.class).mo10268get()).hasAccess("GEOFENCE_ANDROID_PHASE3", false)) {
            SystemClock systemClock = new SystemClock();
            SensorController sensorController = new SensorController(Arrays.asList(new NativeLocationSensor(context, lazy), new WifiDetectionSensor(context, lazy), new ActivitySensor(context, lazy)), lazy2);
            ActiveGeofences activeGeofences = new ActiveGeofences(lazy2, context);
            OsGeofenceDetector osGeofenceDetector = new OsGeofenceDetector(lazy, systemClock);
            AlexaGeofenceDetector alexaGeofenceDetector = new AlexaGeofenceDetector(locationDataStoreService, lazy2);
            WifiChangeDetector wifiChangeDetector = new WifiChangeDetector(locationDataStoreService);
            MotionDetector motionDetector = new MotionDetector(locationDataStoreService, lazy2, systemClock);
            LocationEventQueue locationEventQueue = new LocationEventQueue(locationDataStoreService);
            DwellTracker dwellTracker = new DwellTracker(locationDataStoreService, lazy2);
            LocationEventEvaluator locationEventEvaluator = new LocationEventEvaluator(locationEventQueue, new TriggerEventEvaluator(dwellTracker, activeGeofences, systemClock), lazy, systemClock, context);
            ConfigurationManager configurationManager = new ConfigurationManager(identityService, subject, mainActivityLifecycleObserverRegistrar, alexaLocationPermissionService, locationManager);
            WriteToFile.appendLogForDebugBuild(context, "Phase3LocationService created");
            return new Phase3LocationService(defaultLocationService, activeGeofences, locationEventEvaluator, configurationManager, sensorController, locationManager, osGeofenceDetector, lazy, alexaGeofenceDetector, wifiChangeDetector, motionDetector, dwellTracker, lazy2, context);
        }
        WriteToFile.appendLogForDebugBuild(context, "DefaultLocationService created");
        return defaultLocationService;
    }
}
