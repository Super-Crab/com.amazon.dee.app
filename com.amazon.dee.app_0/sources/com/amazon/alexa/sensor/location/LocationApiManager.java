package com.amazon.alexa.sensor.location;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.sensor.SensorUtils;
import com.amazon.alexa.sensor.api.location.LocationConfiguration;
import com.amazon.alexa.sensor.api.location.LocationConfigurationRequest;
import com.amazon.alexa.sensor.api.location.LocationEventHandler;
import com.amazon.alexa.sensor.api.location.LocationSensorAccess;
import com.amazon.alexa.sensor.api.metrics.SensorAccessMetricsRecorder;
import com.amazon.alexa.sensor.api.metrics.events.CounterEvent;
import com.amazon.alexa.sensor.location.Metrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class LocationApiManager {
    private static final String ACTION_LOCATION_REPORT = "com.amazon.alexa.sensor.ACTION_LOCATION_REPORT";
    private static final String EXTRA_SUBSCRIBERS = "com.amazon.alexa.sensor.EXTRA_SUBSCRIBERS";
    @VisibleForTesting
    static final LocationRequest SPARSE_LOCATION_REQUEST = LocationRequest.create().setPriority(102).setFastestInterval(60000).setInterval(3600000).setSmallestDisplacement(500.0f);
    private static final String TAG = "LocationApiManager";
    @VisibleForTesting
    LocationCallback allLocationCallback;
    @VisibleForTesting
    LocationRequest allLocationRequest;
    @VisibleForTesting
    final ArrayList<String> allLocationSubscribers;
    @VisibleForTesting
    Context context;
    @VisibleForTesting
    FusedLocationProviderClient fusedLocationProviderClient;
    @VisibleForTesting
    boolean isForcingForeground;
    @NonNull
    @VisibleForTesting
    LocationConfiguration locationConfiguration;
    @VisibleForTesting
    Looper looper;
    private final LazyComponent<SensorAccessMetricsRecorder> sensorAccessMetricsRecorder;
    @VisibleForTesting
    LocationCallback sparseLocationCallback;
    @VisibleForTesting
    final ArrayList<String> sparseLocationSubscribers;

    /* loaded from: classes10.dex */
    private class LocationApiCallback extends LocationCallback {
        private LocationApiCallback() {
        }

        @Override // com.google.android.gms.location.LocationCallback
        public void onLocationAvailability(LocationAvailability locationAvailability) {
            ((SensorAccessMetricsRecorder) LocationApiManager.this.sensorAccessMetricsRecorder.mo10268get()).recordOccurrence(Metrics.Events.LOCATION_AVAILABILITIES_RECEIVED, Metrics.fullComponentName(Metrics.SubComponents.LOCATION_UPDATES), Metrics.SubComponents.LOCATION_UPDATES, locationAvailability != null);
        }

        @Override // com.google.android.gms.location.LocationCallback
        public void onLocationResult(LocationResult locationResult) {
            LocationSensorAccess locationSensorAccess = (LocationSensorAccess) ComponentRegistry.getInstance().getLazy(LocationSensorAccess.class).mo10268get();
            if (!(locationSensorAccess instanceof LocationSensorAccessor)) {
                ((SensorAccessMetricsRecorder) LocationApiManager.this.sensorAccessMetricsRecorder.mo10268get()).recordEvent(Metrics.Events.INCOMPATIBLE_ACCESSOR, Metrics.fullComponentName(Metrics.SubComponents.LOCATION_UPDATES), Metrics.SubComponents.LOCATION_UPDATES);
                return;
            }
            LocationSensorAccessor locationSensorAccessor = (LocationSensorAccessor) locationSensorAccess;
            ((SensorAccessMetricsRecorder) LocationApiManager.this.sensorAccessMetricsRecorder.mo10268get()).recordOccurrence(Metrics.Events.LOCATION_RESULTS_RECEIVED, Metrics.fullComponentName(Metrics.SubComponents.LOCATION_UPDATES), Metrics.SubComponents.LOCATION_UPDATES, locationResult != null);
            if (locationResult == null) {
                return;
            }
            CounterEvent counterEvent = new CounterEvent(Metrics.Events.LOCATIONS_RECEIVED, Metrics.fullComponentName(Metrics.SubComponents.LOCATION_UPDATES), Metrics.SubComponents.LOCATION_UPDATES);
            counterEvent.setValue(locationResult.getLocations().size());
            ((SensorAccessMetricsRecorder) LocationApiManager.this.sensorAccessMetricsRecorder.mo10268get()).recordCounter(counterEvent);
            for (Location location : locationResult.getLocations()) {
                locationSensorAccessor.didReceiveLocation(location);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocationApiManager(@NonNull Context context, @NonNull LazyComponent<SensorAccessMetricsRecorder> lazyComponent) {
        this(context, lazyComponent, LocationServices.getFusedLocationProviderClient(context), SensorUtils.getLooper(TAG));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocationApiAuthorization authorization() {
        boolean z = true;
        boolean z2 = ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") == 0;
        if (!z2 && ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            z = false;
        }
        return new LocationApiAuthorization(z2, z);
    }

    void clearLocationRequests() {
        stopForegroundService();
        this.sparseLocationSubscribers.clear();
        this.allLocationSubscribers.clear();
        this.fusedLocationProviderClient.removeLocationUpdates(this.sparseLocationCallback);
        this.fusedLocationProviderClient.removeLocationUpdates(this.allLocationCallback);
        this.allLocationRequest = null;
        this.locationConfiguration = new LocationConfiguration(false, false, 100);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public LocationConfiguration configuration() {
        return this.locationConfiguration;
    }

    @VisibleForTesting
    void getLastKnownLocation(final LocationEventHandler locationEventHandler) {
        if (!authorization().isLocationAccessAuthorized) {
            Log.e(TAG, "App doesn't have permissions to access location");
            locationEventHandler.didReceiveLocation(null);
            this.sensorAccessMetricsRecorder.mo10268get().recordEvent(Metrics.Events.MISSING_LOCATION_PERMISSION, Metrics.fullComponentName(Metrics.SubComponents.SINGLE_LOCATION), Metrics.SubComponents.SINGLE_LOCATION);
            return;
        }
        this.fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener() { // from class: com.amazon.alexa.sensor.location.-$$Lambda$LocationApiManager$Ts3S2BPUOfv6GGPToN_Qo7VvvVU
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                LocationApiManager.this.lambda$getLastKnownLocation$0$LocationApiManager(locationEventHandler, task);
            }
        });
    }

    public /* synthetic */ void lambda$getLastKnownLocation$0$LocationApiManager(LocationEventHandler locationEventHandler, Task task) {
        if (task.isSuccessful()) {
            Location location = (Location) task.getResult();
            if (location == null) {
                Log.e(TAG, "API called but didn't receive any location data");
                this.sensorAccessMetricsRecorder.mo10268get().recordEvent(Metrics.Events.NO_LOCATION_RETURNED, Metrics.fullComponentName(Metrics.SubComponents.SINGLE_LOCATION), Metrics.SubComponents.SINGLE_LOCATION);
            }
            locationEventHandler.didReceiveLocation(location);
            return;
        }
        Log.e(TAG, "API call was marked as unsuccessful", task.getException());
        this.sensorAccessMetricsRecorder.mo10268get().recordEvent(Metrics.Events.PLATFORM_API_CALL_FAILED, Metrics.fullComponentName(Metrics.SubComponents.SINGLE_LOCATION), Metrics.SubComponents.SINGLE_LOCATION);
        locationEventHandler.didReceiveLocation(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void location(LocationEventHandler locationEventHandler) {
        getLastKnownLocation(locationEventHandler);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setConfiguration(@Nullable Collection<Subscription> collection) {
        if (collection != null && !collection.isEmpty()) {
            if (!authorization().isLocationAccessAuthorized) {
                Log.e(TAG, "App doesn't have permissions to access location");
                clearLocationRequests();
                this.sensorAccessMetricsRecorder.mo10268get().recordEvent(Metrics.Events.MISSING_LOCATION_PERMISSION, Metrics.fullComponentName(Metrics.SubComponents.CONFIGURATION_CHANGES), Metrics.SubComponents.CONFIGURATION_CHANGES);
                return;
            }
            this.sparseLocationSubscribers.clear();
            this.allLocationSubscribers.clear();
            this.allLocationRequest = LocationRequest.create().setPriority(102).setFastestInterval(10000L).setInterval(3600000L).setSmallestDisplacement(500.0f);
            Iterator<Subscription> it2 = collection.iterator();
            int i = 100;
            int i2 = 100;
            boolean z = false;
            while (true) {
                boolean z2 = true;
                if (!it2.hasNext()) {
                    break;
                }
                Subscription next = it2.next();
                if (next.configuration.mode == 1) {
                    this.sparseLocationSubscribers.add(next.featureId);
                } else {
                    this.allLocationSubscribers.add(next.featureId);
                    LocationConfigurationRequest locationConfigurationRequest = next.configuration;
                    if (locationConfigurationRequest.locationAccuracy == 0) {
                        this.allLocationRequest.setPriority(100);
                        i2 = 0;
                    }
                    if (locationConfigurationRequest.minimumDeliveryTimeInterval < this.allLocationRequest.getInterval()) {
                        this.allLocationRequest.setInterval(locationConfigurationRequest.minimumDeliveryTimeInterval);
                    }
                    if (locationConfigurationRequest.minimumDeliveryDistance < this.allLocationRequest.getSmallestDisplacement()) {
                        this.allLocationRequest.setSmallestDisplacement(locationConfigurationRequest.minimumDeliveryDistance);
                    }
                }
                if (next.configuration.appStateRequirement != 2) {
                    z2 = false;
                }
                z |= z2;
            }
            if (z) {
                startForegroundService();
            } else {
                stopForegroundService();
            }
            boolean z3 = !this.sparseLocationSubscribers.isEmpty();
            boolean z4 = !this.allLocationSubscribers.isEmpty();
            if (!this.allLocationSubscribers.isEmpty()) {
                i = i2;
            }
            this.locationConfiguration = new LocationConfiguration(z3, z4, i);
            if (this.locationConfiguration.isCoarseLocationOn) {
                this.fusedLocationProviderClient.requestLocationUpdates(SPARSE_LOCATION_REQUEST, this.sparseLocationCallback, this.looper).addOnFailureListener($$Lambda$LocationApiManager$dVGYKOHOwgR6nM83Gi8PzET_608.INSTANCE);
            } else {
                this.fusedLocationProviderClient.removeLocationUpdates(this.sparseLocationCallback);
            }
            if (this.locationConfiguration.isFineLocationOn) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("New interval: ");
                outline107.append(this.allLocationRequest.getInterval());
                outline107.toString();
                this.fusedLocationProviderClient.requestLocationUpdates(this.allLocationRequest, this.allLocationCallback, this.looper).addOnFailureListener($$Lambda$LocationApiManager$XmyIUPOaft7fzA7tLBtUCD3LUeM.INSTANCE);
            } else {
                this.fusedLocationProviderClient.removeLocationUpdates(this.allLocationCallback);
                this.allLocationRequest = null;
            }
            this.sensorAccessMetricsRecorder.mo10268get().recordEvent(Metrics.Events.LOCATION_TRACKING_STARTED, Metrics.fullComponentName(Metrics.SubComponents.CONFIGURATION_CHANGES), Metrics.SubComponents.CONFIGURATION_CHANGES);
            this.sensorAccessMetricsRecorder.mo10268get().recordOccurrence(Metrics.Events.LOCATION_TRACKING_ALL_CHANGES, Metrics.fullComponentName(Metrics.SubComponents.CONFIGURATION_CHANGES), Metrics.SubComponents.CONFIGURATION_CHANGES, this.locationConfiguration.isFineLocationOn);
            return;
        }
        clearLocationRequests();
        this.sensorAccessMetricsRecorder.mo10268get().recordEvent(Metrics.Events.LOCATION_TRACKING_STOPPED, Metrics.fullComponentName(Metrics.SubComponents.CONFIGURATION_CHANGES), Metrics.SubComponents.CONFIGURATION_CHANGES);
    }

    @VisibleForTesting
    void startForegroundService() {
        if (this.isForcingForeground) {
            Log.i(TAG, "Already running forced-foreground tracking.");
        } else if (!((FeatureServiceV2) ComponentRegistry.getInstance().getLazy(FeatureServiceV2.class).mo10268get()).hasAccess("LOCATION_ANDROID_FORCE_FOREGROUND_TRACKING", false)) {
            Log.i(TAG, String.format("Feature %s is disabled; ignoring force-foreground requests", "LOCATION_ANDROID_FORCE_FOREGROUND_TRACKING"));
        } else {
            LocationSensorService.startSensorService(this.context, true);
            this.isForcingForeground = true;
        }
    }

    @VisibleForTesting
    void stopForegroundService() {
        if (!this.isForcingForeground) {
            Log.i(TAG, "No need to stopForegroundService()  since we're not running forced-foreground tracking.");
            return;
        }
        Context context = this.context;
        context.stopService(new Intent(context, LocationSensorService.class));
        this.isForcingForeground = false;
    }

    @VisibleForTesting
    LocationApiManager(@NonNull Context context, @NonNull LazyComponent<SensorAccessMetricsRecorder> lazyComponent, @NonNull FusedLocationProviderClient fusedLocationProviderClient, @NonNull Looper looper) {
        this.allLocationRequest = null;
        this.allLocationCallback = new LocationApiCallback();
        this.sparseLocationCallback = new LocationApiCallback();
        this.sparseLocationSubscribers = new ArrayList<>();
        this.allLocationSubscribers = new ArrayList<>();
        this.locationConfiguration = new LocationConfiguration(false, false, 100);
        this.isForcingForeground = false;
        this.context = context;
        this.sensorAccessMetricsRecorder = lazyComponent;
        this.fusedLocationProviderClient = fusedLocationProviderClient;
        this.looper = looper;
    }
}
