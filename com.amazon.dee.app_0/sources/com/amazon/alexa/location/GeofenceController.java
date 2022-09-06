package com.amazon.alexa.location;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.location.models.ALSGeofence;
import com.amazon.alexa.location.utils.Converter;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import java.util.List;
/* loaded from: classes9.dex */
public class GeofenceController {
    private static final String COMPONENT_REGISTER_GEOFENCE = MobilyticsUtil.getComponentName("register_geofence");
    public static final String GEOFENCE_TRIGGER_ACTION = "com.amazon.alexa.location.ACTION_GEOFENCE_TRIGGER";
    public static final String GEOFENCE_TRIGGER_INTENT_SERVICE = "com.amazon.alexa.location.GeofenceTriggerIntentService";
    public static final String GEOFENCE_TRIGGER_RECEIVER = "com.amazon.alexa.location.GeofenceTriggerReceiver";
    static final String TAG = "GeofenceController";
    private final LazyComponent<FeatureServiceV2> featureServiceV2;
    private GeofencingClient geofencingClient;
    private PendingIntent geofencingPendingIntent;
    private Gson gson;
    private final LazyComponent<Mobilytics> mobilytics;
    private LocationPermissionService permissionService;
    private SettingsClient settingClient;

    public GeofenceController(Context context, LocationPermissionService locationPermissionService, LazyComponent<Mobilytics> lazyComponent, Gson gson) {
        this(context, locationPermissionService, lazyComponent, gson, LocationServices.getGeofencingClient(context), LocationServices.getSettingsClient(context));
    }

    private void createPendingIntent(Context context) {
        Intent intent = new Intent(GEOFENCE_TRIGGER_ACTION);
        if (this.featureServiceV2.mo10268get().hasAccess("GEOFENCE_ANDROID_DROP_TRIGGER_RECEIVER", false)) {
            intent.setClassName(context, GEOFENCE_TRIGGER_INTENT_SERVICE);
            int i = Build.VERSION.SDK_INT;
            this.geofencingPendingIntent = PendingIntent.getForegroundService(context, 0, intent, 134217728);
            return;
        }
        intent.setClassName(context, GEOFENCE_TRIGGER_RECEIVER);
        this.geofencingPendingIntent = PendingIntent.getBroadcast(context, 0, intent, 134217728);
    }

    private String getExceptionMetricName(String str, @Nullable Exception exc) {
        if (exc instanceof ApiException) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
            outline107.append(GeofenceStatusCodes.getStatusCodeString(((ApiException) exc).getStatusCode()));
            return outline107.toString();
        } else if (exc != null) {
            return GeneratedOutlineSupport1.outline72(str, "EXCEPTION_UNKNOWN");
        } else {
            return GeneratedOutlineSupport1.outline72(str, "EXCEPTION_NULL");
        }
    }

    private GeofencingRequest getGeofencingRequest(List<Geofence> list) {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(0);
        builder.addGeofences(list);
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$4(SingleEmitter singleEmitter, Task task) {
        if (task.isSuccessful()) {
            singleEmitter.onSuccess(((LocationSettingsResponse) task.getResult()).getLocationSettingsStates());
            return;
        }
        Exception exception = task.getException();
        if (exception != null) {
            exception.printStackTrace();
        }
        singleEmitter.onError(new LocationException(LocationErrorCode.GENERIC_ERROR, exception.toString(), exception));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Single<List<ALSGeofence>> addGeofences(@NonNull final List<ALSGeofence> list) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.location.-$$Lambda$GeofenceController$6vFZzdtAbBErRkIZ5YAed4M6gVo
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                GeofenceController.this.lambda$addGeofences$1$GeofenceController(list, singleEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Single<LocationSettingsStates> getLocationSettings() {
        LocationRequest create = LocationRequest.create();
        create.setPriority(105);
        final LocationSettingsRequest build = new LocationSettingsRequest.Builder().addLocationRequest(create).build();
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.location.-$$Lambda$GeofenceController$ueGkKVT8lr1WrglQ8OexE0lvBKI
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                GeofenceController.this.lambda$getLocationSettings$5$GeofenceController(build, singleEmitter);
            }
        });
    }

    public /* synthetic */ void lambda$addGeofences$1$GeofenceController(final List list, final SingleEmitter singleEmitter) throws Throwable {
        if (!this.permissionService.hasFullLocationPermission()) {
            String locationErrorMetricNameByReason = MobilyticsUtil.getLocationErrorMetricNameByReason(MobilyticsUtil.MetricsID.GEOFENCES_ADDED_FAILURE, LocationErrorCode.PERMISSION_ERROR);
            String str = COMPONENT_REGISTER_GEOFENCE;
            this.mobilytics.mo10268get().recordOccurrence(locationErrorMetricNameByReason, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            singleEmitter.onError(new GeoFenceException(LocationErrorCode.PERMISSION_ERROR, "[ERROR] Location permission is not granted.", list));
        } else if (list.size() > 20) {
            GeoFenceException geoFenceException = new GeoFenceException(LocationErrorCode.BEYOND_20_LIMIT, "[ERROR] Fail to create geofence. Reached geofence limit 20.", list);
            String locationErrorMetricNameByReason2 = MobilyticsUtil.getLocationErrorMetricNameByReason(MobilyticsUtil.MetricsID.GEOFENCES_ADDED_FAILURE, LocationErrorCode.BEYOND_20_LIMIT);
            String str2 = COMPONENT_REGISTER_GEOFENCE;
            this.mobilytics.mo10268get().recordOccurrence(locationErrorMetricNameByReason2, true, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            MetricsUtil.recordFailure(this.mobilytics, MetricsUtil.MetricsId.EXCEEDS_MAX_GEOFENCES, "", "[ERROR] Fail to create geofence. Reached geofence limit 20.");
            singleEmitter.onError(geoFenceException);
        } else if (list.isEmpty()) {
            singleEmitter.onSuccess(list);
        } else {
            this.geofencingClient.addGeofences(getGeofencingRequest(Converter.convertToGeofences(list)), this.geofencingPendingIntent).addOnCompleteListener(new OnCompleteListener() { // from class: com.amazon.alexa.location.-$$Lambda$GeofenceController$FVa5141YCC9gJJPl2gWe0xjqghk
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    GeofenceController.this.lambda$null$0$GeofenceController(list, singleEmitter, task);
                }
            });
        }
    }

    public /* synthetic */ void lambda$getLocationSettings$5$GeofenceController(LocationSettingsRequest locationSettingsRequest, final SingleEmitter singleEmitter) throws Throwable {
        this.settingClient.checkLocationSettings(locationSettingsRequest).addOnCompleteListener(new OnCompleteListener() { // from class: com.amazon.alexa.location.-$$Lambda$GeofenceController$8zbWmcQD8av26ckBdofp3FOsZ2o
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                GeofenceController.lambda$null$4(SingleEmitter.this, task);
            }
        });
    }

    public /* synthetic */ void lambda$null$0$GeofenceController(List list, SingleEmitter singleEmitter, Task task) {
        if (task.isSuccessful()) {
            this.gson.toJson(list);
            String str = COMPONENT_REGISTER_GEOFENCE;
            this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.GEOFENCES_ADDED_SUCCESS, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            MetricsUtil.recordSuccess(this.mobilytics, MetricsUtil.MetricsId.GEOFENCES_REGISTERED_TO_OS, "");
            singleEmitter.onSuccess(list);
            return;
        }
        Exception exception = task.getException();
        String exceptionMetricName = getExceptionMetricName(MobilyticsUtil.MetricsID.GEOFENCES_ADDED_FAILURE, exception);
        String str2 = COMPONENT_REGISTER_GEOFENCE;
        this.mobilytics.mo10268get().recordOccurrence(exceptionMetricName, true, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        MetricsUtil.recordFailure(this.mobilytics, MetricsUtil.MetricsId.GEOFENCES_REGISTERED_TO_OS, "", "[ERROR] Geofences fail to add to OS.");
        singleEmitter.onError(new GeoFenceException(LocationErrorCode.GENERIC_ERROR, "[ERROR] Geofences fail to add to OS.", list, exception));
    }

    public /* synthetic */ void lambda$null$2$GeofenceController(List list, SingleEmitter singleEmitter, Task task) {
        if (task.isSuccessful()) {
            this.gson.toJson(list);
            String str = COMPONENT_REGISTER_GEOFENCE;
            this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.GEOFENCES_REMOVED_SUCCESS, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            singleEmitter.onSuccess(list);
            return;
        }
        String exceptionMetricName = getExceptionMetricName(MobilyticsUtil.MetricsID.GEOFENCES_REMOVED_FAILURE, task.getException());
        String str2 = COMPONENT_REGISTER_GEOFENCE;
        this.mobilytics.mo10268get().recordOccurrence(exceptionMetricName, true, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        singleEmitter.onError(new GeoFenceException(LocationErrorCode.GENERIC_ERROR, "[ERROR] Geofences fail to remove from OS.", list));
    }

    public /* synthetic */ void lambda$removeGeofences$3$GeofenceController(final List list, final SingleEmitter singleEmitter) throws Throwable {
        if (!this.permissionService.hasFullLocationPermission()) {
            String locationErrorMetricNameByReason = MobilyticsUtil.getLocationErrorMetricNameByReason(MobilyticsUtil.MetricsID.GEOFENCES_REMOVED_FAILURE, LocationErrorCode.PERMISSION_ERROR);
            String str = COMPONENT_REGISTER_GEOFENCE;
            this.mobilytics.mo10268get().recordOccurrence(locationErrorMetricNameByReason, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            singleEmitter.onError(new GeoFenceException(LocationErrorCode.PERMISSION_ERROR, "[ERROR] Location permission is not granted.", list));
        } else if (list.isEmpty()) {
            singleEmitter.onSuccess(list);
        } else {
            this.geofencingClient.removeGeofences(Converter.extractGeofenceIds(list)).addOnCompleteListener(new OnCompleteListener() { // from class: com.amazon.alexa.location.-$$Lambda$GeofenceController$kXvOx5frmeIi2XDpDm_iI9D2j9k
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    GeofenceController.this.lambda$null$2$GeofenceController(list, singleEmitter, task);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Single<List<ALSGeofence>> removeGeofences(final List<ALSGeofence> list) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.location.-$$Lambda$GeofenceController$MHypGgaViJS6clXfKamsBzyvgYc
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                GeofenceController.this.lambda$removeGeofences$3$GeofenceController(list, singleEmitter);
            }
        });
    }

    @VisibleForTesting
    GeofenceController(Context context, LocationPermissionService locationPermissionService, LazyComponent<Mobilytics> lazyComponent, Gson gson, GeofencingClient geofencingClient, SettingsClient settingsClient) {
        this.geofencingClient = geofencingClient;
        this.settingClient = settingsClient;
        this.featureServiceV2 = ComponentRegistry.getInstance().getLazy(FeatureServiceV2.class);
        createPendingIntent(context);
        this.permissionService = locationPermissionService;
        this.mobilytics = lazyComponent;
        this.gson = gson;
    }
}
