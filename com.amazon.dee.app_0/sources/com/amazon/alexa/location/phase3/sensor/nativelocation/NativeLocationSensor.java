package com.amazon.alexa.location.phase3.sensor.nativelocation;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.location.phase3.SensorController;
import com.amazon.alexa.location.phase3.sensor.AbstractSensor;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/* loaded from: classes9.dex */
public class NativeLocationSensor extends AbstractSensor {
    private static final String SENSOR_NAME = "native_location_sensor";
    private final FusedLocationProviderClient nativeLocationClient;
    private final PendingIntent nativeLocationPendingIntent;
    private LocationRequest nativeLocationRequest;
    @VisibleForTesting
    static final String ACTION_NATIVE_LOCATION_REPORT = "com.amazon.alexa.location.ACTION_NATIVE_LOCATION_REPORT";
    private static final Set<String> TARGET_ACTIONS = Sets.newHashSet(ACTION_NATIVE_LOCATION_REPORT);

    public NativeLocationSensor(@NonNull Context context, @NonNull LazyComponent<Mobilytics> lazyComponent) {
        this(lazyComponent, LocationServices.getFusedLocationProviderClient(context), buildPendingIntent(context));
    }

    private static PendingIntent buildPendingIntent(@NonNull Context context) {
        Intent intent = new Intent(ACTION_NATIVE_LOCATION_REPORT);
        intent.setClassName(context, "com.amazon.alexa.location.phase3.sensor.SensorIntentService");
        int i = Build.VERSION.SDK_INT;
        return PendingIntent.getForegroundService(context, 0, intent, 134217728);
    }

    @Override // com.amazon.alexa.location.phase3.sensor.AbstractSensor
    @NonNull
    public Set<String> getCorrespondingIntentActions() {
        return TARGET_ACTIONS;
    }

    @Override // com.amazon.alexa.location.phase3.sensor.AbstractSensor
    @NonNull
    protected String getSensorName() {
        return SENSOR_NAME;
    }

    @Override // com.amazon.alexa.location.phase3.sensor.AbstractSensor
    protected List<NativeLocationInput> processInternal(@NonNull Intent intent) {
        ArrayList arrayList = new ArrayList();
        LocationResult extractResult = LocationResult.extractResult(intent);
        if (extractResult != null) {
            for (Location location : extractResult.getLocations()) {
                arrayList.add(new NativeLocationInput(location));
            }
        }
        return arrayList;
    }

    @Override // com.amazon.alexa.location.phase3.sensor.AbstractSensor
    @SuppressLint({"MissingPermission"})
    protected void startInternal(SensorController.SensorState sensorState) {
        this.nativeLocationRequest = LocationRequest.create();
        if (SensorController.SensorState.NORMAL.equals(sensorState)) {
            this.nativeLocationRequest.setPriority(102).setInterval(300000L);
        } else {
            this.nativeLocationRequest.setPriority(100).setInterval(10000L);
        }
        this.nativeLocationClient.requestLocationUpdates(this.nativeLocationRequest, this.nativeLocationPendingIntent);
    }

    @Override // com.amazon.alexa.location.phase3.sensor.AbstractSensor
    protected void stopInternal() {
        this.nativeLocationClient.removeLocationUpdates(this.nativeLocationPendingIntent);
    }

    @VisibleForTesting
    NativeLocationSensor(@NonNull LazyComponent<Mobilytics> lazyComponent, @NonNull FusedLocationProviderClient fusedLocationProviderClient, @NonNull PendingIntent pendingIntent) {
        super(lazyComponent);
        this.nativeLocationClient = fusedLocationProviderClient;
        this.nativeLocationPendingIntent = pendingIntent;
    }
}
