package com.amazon.alexa.fitness.location;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.fitness.api.LocationService;
import com.amazon.alexa.fitness.api.LocationServiceListener;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LocationService.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000g\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u001a\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0017H\u0016J\b\u0010#\u001a\u00020\u000eH\u0002J\b\u0010$\u001a\u00020\u000eH\u0002J\u0010\u0010%\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0017H\u0016J\u0010\u0010&\u001a\u00020!2\u0006\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020!H\u0017J/\u0010)\u001a\u00020!2%\u0010*\u001a!\u0012\u0015\u0012\u0013\u0018\u00010,¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020!\u0018\u00010+H\u0017J\u0010\u00100\u001a\u00020!2\u0006\u0010'\u001a\u00020(H\u0016J\b\u00101\u001a\u00020!H\u0017J\b\u00102\u001a\u00020!H\u0016R\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00180\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0010R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u00063"}, d2 = {"Lcom/amazon/alexa/fitness/location/LocationServiceImpl;", "Lcom/amazon/alexa/fitness/api/LocationService;", "componentRegistry", "Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;", "context", "Landroid/content/Context;", "client", "Lcom/google/android/gms/location/FusedLocationProviderClient;", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/alexa/fitness/location/LocationServiceConfiguration;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/protocols/service/api/ComponentRegistry;Landroid/content/Context;Lcom/google/android/gms/location/FusedLocationProviderClient;Lcom/amazon/alexa/fitness/location/LocationServiceConfiguration;Lcom/amazon/alexa/fitness/logs/ILog;)V", "authorizationStatus", "", "getAuthorizationStatus", "()Z", "getClient", "()Lcom/google/android/gms/location/FusedLocationProviderClient;", "getConfiguration", "()Lcom/amazon/alexa/fitness/location/LocationServiceConfiguration;", "listeners", "", "Lcom/amazon/alexa/fitness/api/LocationServiceListener;", "Ljava/lang/ref/WeakReference;", "locationCallback", "com/amazon/alexa/fitness/location/LocationServiceImpl$locationCallback$1", "Lcom/amazon/alexa/fitness/location/LocationServiceImpl$locationCallback$1;", "locationStatus", "getLocationStatus", "getLog", "()Lcom/amazon/alexa/fitness/logs/ILog;", "addListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "isLocationEnabled", "isPermissionGranted", "removeListener", "requestLocationAccess", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "requestLocationOnce", "callback", "Lkotlin/Function1;", "Landroid/location/Location;", "Lkotlin/ParameterName;", "name", "location", "requestToEnableLocation", "startUpdatingLocation", "stopUpdatingLocation", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class LocationServiceImpl implements LocationService {
    @NotNull
    private final FusedLocationProviderClient client;
    @NotNull
    private final LocationServiceConfiguration configuration;
    private final Context context;
    private final Map<LocationServiceListener, WeakReference<LocationServiceListener>> listeners;
    private final LocationServiceImpl$locationCallback$1 locationCallback;
    @NotNull
    private final ILog log;

    /* JADX WARN: Type inference failed for: r2v1, types: [com.amazon.alexa.fitness.location.LocationServiceImpl$locationCallback$1] */
    @Inject
    public LocationServiceImpl(@NotNull ComponentRegistry componentRegistry, @NotNull Context context, @NotNull FusedLocationProviderClient client, @NotNull LocationServiceConfiguration configuration, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(componentRegistry, "componentRegistry");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(configuration, "configuration");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.context = context;
        this.client = client;
        this.configuration = configuration;
        this.log = log;
        Map<LocationServiceListener, WeakReference<LocationServiceListener>> synchronizedMap = Collections.synchronizedMap(new WeakHashMap());
        Intrinsics.checkExpressionValueIsNotNull(synchronizedMap, "Collections.synchronized…stener>\n        >()\n    )");
        this.listeners = synchronizedMap;
        componentRegistry.bindConcreteFactory(LocationService.class, new ComponentRegistry.ConcreteComponentFactory<LocationService>() { // from class: com.amazon.alexa.fitness.location.LocationServiceImpl.1
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            @NotNull
            /* renamed from: create */
            public final LocationService create2(Context context2) {
                return LocationServiceImpl.this;
            }
        });
        this.locationCallback = new LocationCallback() { // from class: com.amazon.alexa.fitness.location.LocationServiceImpl$locationCallback$1
            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationAvailability(@Nullable LocationAvailability locationAvailability) {
                Map map;
                ILog log2 = LocationServiceImpl.this.getLog();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("loc availability = ");
                outline107.append(locationAvailability != null ? Boolean.valueOf(locationAvailability.isLocationAvailable()) : null);
                ILog.DefaultImpls.info$default(log2, MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, outline107.toString(), null, 4, null);
                if (locationAvailability != null) {
                    map = LocationServiceImpl.this.listeners;
                    for (WeakReference weakReference : map.values()) {
                        LocationServiceListener locationServiceListener = (LocationServiceListener) weakReference.get();
                        if (locationServiceListener != null) {
                            locationServiceListener.onLocationStatusChanged(locationAvailability.isLocationAvailable());
                        }
                    }
                }
            }

            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationResult(@Nullable LocationResult locationResult) {
                Map map;
                if (locationResult != null) {
                    List<Location> locations = locationResult.getLocations();
                    Intrinsics.checkExpressionValueIsNotNull(locations, "it.locations");
                    for (Location location : locations) {
                        map = LocationServiceImpl.this.listeners;
                        for (WeakReference weakReference : map.values()) {
                            LocationServiceListener locationServiceListener = (LocationServiceListener) weakReference.get();
                            if (locationServiceListener != null) {
                                Intrinsics.checkExpressionValueIsNotNull(location, "location");
                                locationServiceListener.onLocationUpdated(location);
                            }
                        }
                    }
                }
            }
        };
    }

    private final boolean isLocationEnabled() {
        if (Build.VERSION.SDK_INT < 28) {
            return Settings.Secure.getInt(this.context.getContentResolver(), "location_mode", 0) != 0;
        }
        Object systemService = this.context.getSystemService("location");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.location.LocationManager");
        }
        return ((LocationManager) systemService).isLocationEnabled();
    }

    private final boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 29) {
            if (ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") == 0 && ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_BACKGROUND_LOCATION") == 0) {
                return true;
            }
        } else if (ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            return true;
        }
        return false;
    }

    @Override // com.amazon.alexa.fitness.api.LocationService
    public void addListener(@NotNull LocationServiceListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        synchronized (this.listeners) {
            this.listeners.put(listener, new WeakReference<>(listener));
            if (this.listeners.size() == 1) {
                startUpdatingLocation();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.alexa.fitness.api.LocationService
    public boolean getAuthorizationStatus() {
        return isPermissionGranted();
    }

    @NotNull
    public final FusedLocationProviderClient getClient() {
        return this.client;
    }

    @NotNull
    public final LocationServiceConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override // com.amazon.alexa.fitness.api.LocationService
    public boolean getLocationStatus() {
        return isLocationEnabled();
    }

    @NotNull
    public final ILog getLog() {
        return this.log;
    }

    @Override // com.amazon.alexa.fitness.api.LocationService
    public void removeListener(@NotNull LocationServiceListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        synchronized (this.listeners) {
            new WeakReference(listener);
            this.listeners.remove(listener);
            if (this.listeners.isEmpty()) {
                stopUpdatingLocation();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.alexa.fitness.api.LocationService
    public void requestLocationAccess(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        if (isPermissionGranted()) {
            ILog.DefaultImpls.info$default(this.log, MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, "loc is available, returning", null, 4, null);
        } else if (Build.VERSION.SDK_INT >= 29) {
            ILog.DefaultImpls.info$default(this.log, MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, "show location settings", null, 4, null);
            activity.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        } else {
            ILog.DefaultImpls.info$default(this.log, MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, "show application settings", null, 4, null);
            activity.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", "com.amazon.dee.app", null)));
        }
    }

    @Override // com.amazon.alexa.fitness.api.LocationService
    @SuppressLint({"MissingPermission"})
    public void requestLocationOnce() {
        requestLocationOnce(new LocationServiceImpl$requestLocationOnce$1(this));
    }

    @Override // com.amazon.alexa.fitness.api.LocationService
    public void requestToEnableLocation(@NotNull final Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        if (!isLocationEnabled()) {
            ILog.DefaultImpls.info$default(this.log, MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, "request to enable location", null, 4, null);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
            builder.addLocationRequest(new LocationRequest().setPriority(100)).setAlwaysShow(true);
            Task<LocationSettingsResponse> checkLocationSettings = LocationServices.getSettingsClient(activity).checkLocationSettings(builder.build());
            checkLocationSettings.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() { // from class: com.amazon.alexa.fitness.location.LocationServiceImpl$requestToEnableLocation$1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                    ILog.DefaultImpls.info$default(LocationServiceImpl.this.getLog(), MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, "location settings task succeeded", null, 4, null);
                }
            });
            checkLocationSettings.addOnFailureListener(new OnFailureListener() { // from class: com.amazon.alexa.fitness.location.LocationServiceImpl$requestToEnableLocation$2
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(@NotNull Exception it2) {
                    Intrinsics.checkParameterIsNotNull(it2, "it");
                    if (it2 instanceof ResolvableApiException) {
                        try {
                            ((ResolvableApiException) it2).startResolutionForResult(activity, 0);
                            return;
                        } catch (IntentSender.SendIntentException e) {
                            LocationServiceImpl.this.getLog().error(MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, "error resolving location request", e);
                            return;
                        }
                    }
                    LocationServiceImpl.this.getLog().error(MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, "error checking location", it2);
                }
            });
        }
    }

    @Override // com.amazon.alexa.fitness.api.LocationService
    @SuppressLint({"MissingPermission"})
    public void startUpdatingLocation() {
        LocationRequest create = LocationRequest.create();
        LocationRequest interval = create.setPriority(100).setInterval(this.configuration.getLocationUpdateIntervalInMillis());
        Intrinsics.checkExpressionValueIsNotNull(interval, "locationRequest\n        …onUpdateIntervalInMillis)");
        interval.setSmallestDisplacement(this.configuration.getDistanceFilterInMeters());
        try {
            this.client.requestLocationUpdates(create, this.locationCallback, Looper.getMainLooper()).addOnFailureListener(new OnFailureListener() { // from class: com.amazon.alexa.fitness.location.LocationServiceImpl$startUpdatingLocation$1
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(@NotNull Exception it2) {
                    Intrinsics.checkParameterIsNotNull(it2, "it");
                    LocationServiceImpl.this.getLog().error(MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, "error requesting location", it2);
                }
            });
        } catch (SecurityException e) {
            this.log.error(MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, "error requesting location", e);
        }
    }

    @Override // com.amazon.alexa.fitness.api.LocationService
    public void stopUpdatingLocation() {
        this.client.removeLocationUpdates(this.locationCallback);
    }

    @Override // com.amazon.alexa.fitness.api.LocationService
    @SuppressLint({"MissingPermission"})
    public void requestLocationOnce(@Nullable final Function1<? super Location, Unit> function1) {
        try {
            Task<Location> lastLocation = this.client.getLastLocation();
            lastLocation.addOnSuccessListener(new OnSuccessListener<Location>() { // from class: com.amazon.alexa.fitness.location.LocationServiceImpl$requestLocationOnce$2
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Location location) {
                    if (location != null) {
                        Function1 function12 = function1;
                        if ((function12 != null ? (Unit) function12.mo12165invoke(location) : null) != null) {
                            return;
                        }
                    }
                    ILog.DefaultImpls.error$default(LocationServiceImpl.this.getLog(), MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, "got null as last location", null, 4, null);
                    Function1 function13 = function1;
                    if (function13 != null) {
                        Unit unit = (Unit) function13.mo12165invoke(null);
                    }
                }
            });
            lastLocation.addOnFailureListener(new OnFailureListener() { // from class: com.amazon.alexa.fitness.location.LocationServiceImpl$requestLocationOnce$3
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(@NotNull Exception it2) {
                    Intrinsics.checkParameterIsNotNull(it2, "it");
                    LocationServiceImpl.this.getLog().error(MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, "error fetching location", it2);
                    Function1 function12 = function1;
                    if (function12 != null) {
                        Unit unit = (Unit) function12.mo12165invoke(null);
                    }
                }
            });
        } catch (SecurityException e) {
            this.log.error(MetricsUtil.LegacySubComponentName.LOCATION_SERVICE, "error fetching location", e);
            if (function1 == null) {
                return;
            }
            function1.mo12165invoke(null);
        }
    }
}
