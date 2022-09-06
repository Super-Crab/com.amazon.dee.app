package com.amazon.alexa.location.networking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.location.networking.als.ALSLocationNetworkService;
import com.amazon.alexa.location.networking.als.models.ALSGeofence;
import com.amazon.alexa.location.networking.als.models.ALSTriggerEvent;
import com.amazon.alexa.location.networking.als.models.GeoFenceStatus;
import com.amazon.alexa.location.networking.als.networkModels.CreateGeofenceRequestBody;
import com.amazon.alexa.location.networking.als.networkModels.CreateGeofenceResponseBody;
import com.amazon.alexa.location.networking.als.networkModels.GetGeofenceByDeviceResponseBody;
import com.amazon.alexa.location.networking.als.networkModels.ReportGeofenceStatusRequestBody;
import com.amazon.alexa.location.networking.als.networkModels.TriggerGeofenceRequestBody;
import com.amazon.alexa.location.networking.utils.MobilyticsUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import java.io.IOException;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/* loaded from: classes9.dex */
public class AlexaLocationNetworkService implements ALSLocationNetworkService {
    private static final String CREATE_GEOFENCE_URL = "%s/v1/geofences/";
    private static final String GET_GEOFENCE_BY_DEVICE_URL = "%s/v1/geofences/devices/~current";
    private static final String PERSON_ID_PARAM = "personId=%s";
    private static final String QUERY_PARAM = "?";
    private static final String REPORT_GEOFENCE_STATUS = "%s/v1/geofences/updateStatus";
    private static final String TRIGGER_GEOFENCE_URL = "%s/v1/geofences/%s/trigger";
    private static final String UPDATE_GEOFENCE_URL = "%s/v1/geofences/%s";
    private final Context context;
    private final LazyComponent<EnvironmentService> environmentService;
    private final LazyComponent<FeatureServiceV2> fsv2;
    private final Gson gson;
    private final OkHttpClient httpClient;
    private final LazyComponent<Mobilytics> mobilytics;
    private final LocationPermissionService permissionService;
    public static final MediaType JSON = MediaType.parse(LocationPublisher.CONTENT_TYPE_JSON);
    private static final String TAG = AlexaLocationNetworkService.class.getSimpleName();
    private static final String COMPONENT_TRIGGER_GEOFENCE = MobilyticsUtil.getComponentName("trigger_geofence");
    private static final String COMPONENT_NAME = MobilyticsUtil.getComponentName(TAG);

    /* renamed from: com.amazon.alexa.location.networking.AlexaLocationNetworkService$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$location$networking$LocationErrorCode = new int[LocationErrorCode.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$location$networking$LocationErrorCode[LocationErrorCode.ALS_400.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$location$networking$LocationErrorCode[LocationErrorCode.ALS_401.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$location$networking$LocationErrorCode[LocationErrorCode.ALS_403.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$location$networking$LocationErrorCode[LocationErrorCode.ALS_404.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$location$networking$LocationErrorCode[LocationErrorCode.ALS_500.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$location$networking$LocationErrorCode[LocationErrorCode.ALS_503.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$location$networking$LocationErrorCode[LocationErrorCode.ALS_NOT_RECOGNIZE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$location$networking$LocationErrorCode[LocationErrorCode.ALS_PAYLOAD_ERROR.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$location$networking$LocationErrorCode[LocationErrorCode.ALS_TIMEOUT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public AlexaLocationNetworkService(OkHttpClient okHttpClient, Gson gson, LazyComponent<Mobilytics> lazyComponent, LocationPermissionService locationPermissionService, Context context) {
        this.httpClient = okHttpClient;
        this.gson = gson;
        this.mobilytics = lazyComponent;
        this.permissionService = locationPermissionService;
        this.context = context;
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        this.environmentService = componentRegistry.getLazy(EnvironmentService.class);
        this.fsv2 = componentRegistry.getLazy(FeatureServiceV2.class);
    }

    private void handleFailureStatusCode(int i) throws LocationException {
        if (i != 400) {
            if (i == 401) {
                throw new LocationException(LocationErrorCode.ALS_401, "[ERROR] ALS call returns 401");
            }
            if (i == 403) {
                throw new LocationException(LocationErrorCode.ALS_403, "[ERROR] ALS call returns 403");
            }
            if (i == 404) {
                throw new LocationException(LocationErrorCode.ALS_404, "[ERROR] ALS call returns 404");
            }
            if (i == 500) {
                throw new LocationException(LocationErrorCode.ALS_500, "[ERROR] ALS call returns 500");
            }
            if (i != 503) {
                throw new LocationException(LocationErrorCode.ALS_NOT_RECOGNIZE, "[ERROR] Not recognized ALS error");
            }
            throw new LocationException(LocationErrorCode.ALS_503, "[ERROR] ALS call returns 503");
        }
        throw new LocationException(LocationErrorCode.ALS_400, "[ERROR] ALS call returns 400");
    }

    private boolean isGeofenceDataValid(@Nullable ALSGeofence aLSGeofence) {
        return (aLSGeofence == null || aLSGeofence.getId() == null || aLSGeofence.getCircularRegion() == null) ? false : true;
    }

    private boolean isGeofenceListValid(@Nullable List<ALSGeofence> list) {
        if (list == null) {
            return false;
        }
        boolean z = true;
        for (ALSGeofence aLSGeofence : list) {
            if (!z || !isGeofenceDataValid(aLSGeofence)) {
                z = false;
                continue;
            } else {
                z = true;
                continue;
            }
            if (!z) {
                break;
            }
        }
        return z;
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void recordDetailedMetrics(LocationException locationException, String str) {
        int ordinal = locationException.getErrorCode().ordinal();
        if (ordinal == 4) {
            MobilyticsUtil.recordFailure(this.mobilytics, "fetch_error_timeout", str, String.format("[ERROR] %s times out.", str));
        } else if (ordinal == 5) {
            MobilyticsUtil.recordFailure(this.mobilytics, "fetch_error_not_recognized", str, String.format("[ERROR] %s not recognized error.", str));
        } else if (ordinal != 6) {
            switch (ordinal) {
                case 14:
                    MobilyticsUtil.recordFailure(this.mobilytics, "fetch_error_400", str, String.format("[ERROR] %s 400 error.", str));
                    break;
                case 15:
                    MobilyticsUtil.recordFailure(this.mobilytics, "fetch_error_401", str, String.format("[ERROR] %s 401 error.", str));
                    break;
                case 16:
                    MobilyticsUtil.recordFailure(this.mobilytics, "fetch_error_403", str, String.format("[ERROR] %s 403 error.", str));
                    break;
                case 17:
                    MobilyticsUtil.recordFailure(this.mobilytics, "fetch_error_404", str, String.format("[ERROR] %s 404 error.", str));
                    break;
                case 18:
                    MobilyticsUtil.recordFailure(this.mobilytics, "fetch_error_500", str, String.format("[ERROR] %s 500 error.", str));
                    break;
                case 19:
                    MobilyticsUtil.recordFailure(this.mobilytics, "fetch_error_503", str, String.format("[ERROR] %s 503 error.", str));
                    break;
            }
        } else {
            MobilyticsUtil.recordFailure(this.mobilytics, "fetch_error_payload", str, String.format("[ERROR] %s has wrong payload.", str));
        }
        MobilyticsUtil.recordFailure(this.mobilytics, "fetch_als", str, String.format("[ERROR] %s failure", str));
    }

    @Override // com.amazon.alexa.location.networking.als.ALSLocationNetworkService
    @NonNull
    public Single<ALSGeofence> createGeofence(@NonNull String str, @NonNull String str2, final double d, final double d2, final double d3) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.location.networking.-$$Lambda$AlexaLocationNetworkService$G-pfAIQgTMTtqK-U3GO3M4uaBSk
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                AlexaLocationNetworkService.this.lambda$createGeofence$0$AlexaLocationNetworkService(d, d2, d3, singleEmitter);
            }
        });
    }

    @VisibleForTesting
    String getEndpoint() {
        return this.environmentService.mo10268get().getApiGatewayEndpoint();
    }

    @Override // com.amazon.alexa.location.networking.als.ALSLocationNetworkService
    @NonNull
    public Single<List<ALSGeofence>> getGeofencesByDevice(@NonNull String str, @NonNull String str2, @Nullable final String str3) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.location.networking.-$$Lambda$AlexaLocationNetworkService$b2vywitpxd9L_Xk5ixK0YOcarQ8
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                AlexaLocationNetworkService.this.lambda$getGeofencesByDevice$3$AlexaLocationNetworkService(str3, singleEmitter);
            }
        });
    }

    public /* synthetic */ void lambda$createGeofence$0$AlexaLocationNetworkService(double d, double d2, double d3, SingleEmitter singleEmitter) throws Throwable {
        Response response = null;
        try {
            try {
                try {
                } catch (LocationException e) {
                    recordDetailedMetrics(e, "als_create_geofence");
                    singleEmitter.onError(e);
                    if (0 == 0) {
                        return;
                    }
                }
            } catch (JsonSyntaxException e2) {
                LocationException locationException = new LocationException(LocationErrorCode.ALS_PAYLOAD_ERROR, "[ERROR] ALS_CREATE_GEOFENCE response fails to parse.", e2);
                recordDetailedMetrics(locationException, "als_create_geofence");
                singleEmitter.onError(locationException);
                if (0 == 0) {
                    return;
                }
            } catch (IOException e3) {
                LocationException locationException2 = new LocationException(LocationErrorCode.ALS_TIMEOUT, "[ERROR] ALS_CREATE_GEOFENCE times out.", e3);
                recordDetailedMetrics(locationException2, "als_create_geofence");
                singleEmitter.onError(locationException2);
                if (0 == 0) {
                    return;
                }
            }
            if (this.permissionService.hasFullLocationPermission()) {
                String format = String.format(CREATE_GEOFENCE_URL, getEndpoint());
                String json = this.gson.toJson(new CreateGeofenceRequestBody(d, d2, d3));
                MobilyticsMetricsTimer createTimer = MobilyticsUtil.createTimer(this.mobilytics, "fetch_als", "als_create_geofence");
                response = makePostRequest(format, json);
                if (createTimer != null) {
                    createTimer.finishTimer();
                    this.mobilytics.mo10268get().recordTimer(createTimer);
                }
                if (!response.isSuccessful()) {
                    String.format("[ERROR] ALS_CREATE_GEOFENCE failure: %s , %s", Integer.valueOf(response.code()), response.message());
                    handleFailureStatusCode(response.code());
                }
                MobilyticsUtil.recordSuccess(this.mobilytics, "fetch_als", "als_create_geofence");
                CreateGeofenceResponseBody createGeofenceResponseBody = (CreateGeofenceResponseBody) this.gson.fromJson(response.body().string(), (Class<Object>) CreateGeofenceResponseBody.class);
                if (createGeofenceResponseBody != null && isGeofenceDataValid(createGeofenceResponseBody.getFence())) {
                    ALSGeofence fence = createGeofenceResponseBody.getFence();
                    this.gson.toJson(fence);
                    singleEmitter.onSuccess(fence);
                    response.body().close();
                    return;
                }
                throw new LocationException(LocationErrorCode.ALS_PAYLOAD_ERROR, "[ERROR] ALS_CREATE_GEOFENCE response is missing required data.");
            }
            throw new LocationException(LocationErrorCode.PERMISSION_ERROR, "[ERROR] Location permission is not granted.");
        } catch (Throwable th) {
            if (0 != 0) {
                response.body().close();
            }
            throw th;
        }
    }

    public /* synthetic */ void lambda$getGeofencesByDevice$3$AlexaLocationNetworkService(String str, SingleEmitter singleEmitter) throws Throwable {
        GetGeofenceByDeviceResponseBody getGeofenceByDeviceResponseBody;
        Response response = null;
        try {
            try {
                try {
                    try {
                        String format = String.format(GET_GEOFENCE_BY_DEVICE_URL, getEndpoint());
                        if (!TextUtils.isEmpty(str)) {
                            format = format + "?" + String.format(PERSON_ID_PARAM, str);
                        }
                        MobilyticsMetricsTimer createTimer = MobilyticsUtil.createTimer(this.mobilytics, "fetch_als", "als_fetch_geofences");
                        response = makeGetRequest(format);
                        if (createTimer != null) {
                            createTimer.finishTimer();
                            this.mobilytics.mo10268get().recordTimer(createTimer);
                        }
                        if (!response.isSuccessful()) {
                            String.format("[ERROR] ALS_FETCH_GEOFENCE failure: %s , %s", Integer.valueOf(response.code()), response.message());
                            handleFailureStatusCode(response.code());
                        }
                        MobilyticsUtil.recordSuccess(this.mobilytics, "fetch_als", "als_fetch_geofences");
                        getGeofenceByDeviceResponseBody = (GetGeofenceByDeviceResponseBody) this.gson.fromJson(response.body().string(), (Class<Object>) GetGeofenceByDeviceResponseBody.class);
                    } catch (IOException e) {
                        LocationException locationException = new LocationException(LocationErrorCode.ALS_TIMEOUT, "[ERROR] ALS_FETCH_GEOFENCE times out.", e);
                        recordDetailedMetrics(locationException, "als_fetch_geofences");
                        singleEmitter.onError(locationException);
                        if (0 == 0) {
                            return;
                        }
                    }
                } catch (LocationException e2) {
                    recordDetailedMetrics(e2, "als_fetch_geofences");
                    singleEmitter.onError(e2);
                    if (0 == 0) {
                        return;
                    }
                }
            } catch (JsonSyntaxException e3) {
                LocationException locationException2 = new LocationException(LocationErrorCode.ALS_PAYLOAD_ERROR, "[ERROR] ALS_FETCH_GEOFENCE response fails to parse.", e3);
                recordDetailedMetrics(locationException2, "als_fetch_geofences");
                singleEmitter.onError(locationException2);
                if (0 == 0) {
                    return;
                }
            }
            if (getGeofenceByDeviceResponseBody != null && isGeofenceListValid(getGeofenceByDeviceResponseBody.getGeofences())) {
                List<ALSGeofence> geofences = getGeofenceByDeviceResponseBody.getGeofences();
                this.gson.toJson(geofences);
                singleEmitter.onSuccess(geofences);
                response.body().close();
                return;
            }
            throw new LocationException(LocationErrorCode.ALS_PAYLOAD_ERROR, "[ERROR] ALS_FETCH_GEOFENCE response is missing required data.");
        } catch (Throwable th) {
            if (0 != 0) {
                response.body().close();
            }
            throw th;
        }
    }

    public /* synthetic */ void lambda$reportGeofenceStatus$4$AlexaLocationNetworkService(String str, List list, ObservableEmitter observableEmitter) throws Throwable {
        Response response = null;
        try {
            try {
                String format = String.format(REPORT_GEOFENCE_STATUS, getEndpoint());
                MobilyticsMetricsTimer createTimer = MobilyticsUtil.createTimer(this.mobilytics, "fetch_als", "als_report_geofences_status");
                response = makePostRequest(format, this.gson.toJson(new ReportGeofenceStatusRequestBody(str, list)));
                if (createTimer != null) {
                    createTimer.finishTimer();
                    this.mobilytics.mo10268get().recordTimer(createTimer);
                }
                if (!response.isSuccessful()) {
                    String.format("[ERROR] ALS_REPORT_GEOFENCES_STATUS failure: %s , %s", Integer.valueOf(response.code()), response.message());
                    handleFailureStatusCode(response.code());
                }
                MobilyticsUtil.recordSuccess(this.mobilytics, "fetch_als", "als_report_geofences_status");
                if (!TextUtils.isEmpty(response.message()) && 204 != response.code()) {
                    throw new LocationException(LocationErrorCode.GENERIC_ERROR, "[ERROR] ALS_REPORT_GEOFENCES_STATUS response has error.");
                }
                observableEmitter.onComplete();
            } catch (LocationException e) {
                recordDetailedMetrics(e, "als_report_geofences_status");
                observableEmitter.onError(e);
                if (0 == 0) {
                    return;
                }
            } catch (IOException e2) {
                LocationException locationException = new LocationException(LocationErrorCode.ALS_TIMEOUT, "[ERROR] ALS_FETCH_GEOFENCE times out.", e2);
                recordDetailedMetrics(locationException, "als_report_geofences_status");
                observableEmitter.onError(locationException);
                if (0 == 0) {
                    return;
                }
            }
            response.body().close();
        } catch (Throwable th) {
            if (0 != 0) {
                response.body().close();
            }
            throw th;
        }
    }

    public /* synthetic */ void lambda$triggerGeofence$2$AlexaLocationNetworkService(String str, String str2, ALSTriggerEvent aLSTriggerEvent, long j, SingleEmitter singleEmitter) throws Throwable {
        MobilyticsMetricsTimer createTimer = MobilyticsUtil.createTimer(this.mobilytics, "fetch_als", "als_trigger_geofence");
        Response response = null;
        try {
            try {
                try {
                    response = makePostRequest(String.format(TRIGGER_GEOFENCE_URL, getEndpoint(), str), this.gson.toJson(new TriggerGeofenceRequestBody(str2, aLSTriggerEvent, j)));
                    if (createTimer != null) {
                        createTimer.finishTimer();
                        this.mobilytics.mo10268get().recordTimer(createTimer);
                    }
                    if (!response.isSuccessful()) {
                        if (createTimer != null) {
                            this.mobilytics.mo10268get().recordTimer(MobilyticsUtil.createTimerWithNewName(this.mobilytics, createTimer, "trigger_geofence_location_exception_delay", COMPONENT_TRIGGER_GEOFENCE));
                        }
                        String.format("[ERROR] ALS_TRIGGER_GEOFENCE failure: %s , %s", Integer.valueOf(response.code()), response.message());
                        handleFailureStatusCode(response.code());
                    }
                    MobilyticsUtil.recordSuccess(this.mobilytics, "fetch_als", "als_trigger_geofence");
                    String str3 = "[SUCCESS] ALS_TRIGGER_GEOFENCE success. fence_id: " + str;
                    singleEmitter.onSuccess(str);
                } catch (IOException e) {
                    if (createTimer != null) {
                        createTimer.finishTimer();
                        MobilyticsUtil.recordTime(this.mobilytics, "geofence_trigger_io_exception_delay", "als_fetch_geofences", createTimer.getElapsedTime());
                    }
                    Log.e(TAG, "[ERROR] ALS_TRIGGER_GEOFENCE times out.", e);
                    LocationException locationException = new LocationException(LocationErrorCode.ALS_TIMEOUT, "[ERROR] ALS_TRIGGER_GEOFENCE times out.", e);
                    this.mobilytics.mo10268get().recordOccurrence(String.format("trigger_io_exception_%s", e.getClass().getSimpleName()), true, COMPONENT_TRIGGER_GEOFENCE, COMPONENT_TRIGGER_GEOFENCE, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
                    MobilyticsUtil.recordFailure(this.mobilytics, "geofence_trigger_io_exception", "als_fetch_geofences", "Trigger geofence io exception");
                    if (isNetworkAvailable()) {
                        MobilyticsUtil.recordSuccess(this.mobilytics, "geofence_trigger_network_available", "als_fetch_geofences");
                    } else {
                        MobilyticsUtil.recordFailure(this.mobilytics, "geofence_trigger_network_available", "als_fetch_geofences", "Network not available at io exception");
                    }
                    recordDetailedMetrics(locationException, "als_trigger_geofence");
                    singleEmitter.onError(locationException);
                    if (response == null) {
                        return;
                    }
                }
            } catch (LocationException e2) {
                recordDetailedMetrics(e2, "als_trigger_geofence");
                singleEmitter.onError(e2);
                if (response == null) {
                    return;
                }
            }
            response.body().close();
        } catch (Throwable th) {
            if (response != null) {
                response.body().close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void lambda$updateGeofence$1$AlexaLocationNetworkService(java.lang.String r19, double r20, double r22, double r24, io.reactivex.rxjava3.core.SingleEmitter r26) throws java.lang.Throwable {
        /*
            r18 = this;
            r1 = r18
            r2 = r26
            java.lang.String r0 = "fetch_als"
            java.lang.String r3 = "als_update_geofence"
            com.amazon.alexa.location.networking.LocationPermissionService r5 = r1.permissionService     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            boolean r5 = r5.hasFullLocationPermission()     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            if (r5 == 0) goto L94
            java.lang.String r5 = "%s/v1/geofences/%s"
            r6 = 2
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            java.lang.String r8 = r18.getEndpoint()     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            r9 = 0
            r7[r9] = r8     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            r8 = 1
            r7[r8] = r19     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            java.lang.String r5 = java.lang.String.format(r5, r7)     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            com.google.gson.Gson r7 = r1.gson     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            com.amazon.alexa.location.networking.als.networkModels.UpdateGeofenceRequestBody r15 = new com.amazon.alexa.location.networking.als.networkModels.UpdateGeofenceRequestBody     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            r10 = r15
            r11 = r20
            r13 = r22
            r4 = r15
            r15 = r24
            r10.<init>(r11, r13, r15)     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            java.lang.String r4 = r7.toJson(r4)     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            com.amazon.alexa.protocols.service.api.LazyComponent<com.amazon.alexa.mobilytics.Mobilytics> r7 = r1.mobilytics     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer r7 = com.amazon.alexa.location.networking.utils.MobilyticsUtil.createTimer(r7, r0, r3)     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            okhttp3.Response r4 = r1.makePutRequest(r5, r4)     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            if (r7 == 0) goto L50
            r7.finishTimer()     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            com.amazon.alexa.protocols.service.api.LazyComponent<com.amazon.alexa.mobilytics.Mobilytics> r5 = r1.mobilytics     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            java.lang.Object r5 = r5.mo10268get()     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            com.amazon.alexa.mobilytics.Mobilytics r5 = (com.amazon.alexa.mobilytics.Mobilytics) r5     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            r5.recordTimer(r7)     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
        L50:
            boolean r5 = r4.isSuccessful()     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            if (r5 != 0) goto L74
            java.lang.String r5 = "[ERROR] ALS_UPDATE_GEOFENCE failure: %s , %s"
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            int r7 = r4.code()     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            r6[r9] = r7     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            java.lang.String r7 = r4.message()     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            r6[r8] = r7     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            java.lang.String.format(r5, r6)     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            int r5 = r4.code()     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            r1.handleFailureStatusCode(r5)     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
        L74:
            com.amazon.alexa.protocols.service.api.LazyComponent<com.amazon.alexa.mobilytics.Mobilytics> r5 = r1.mobilytics     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            com.amazon.alexa.location.networking.utils.MobilyticsUtil.recordSuccess(r5, r0, r3)     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            com.amazon.alexa.location.networking.als.models.ALSGeofence r0 = new com.amazon.alexa.location.networking.als.models.ALSGeofence     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            r10 = r0
            r11 = r19
            r12 = r20
            r14 = r22
            r16 = r24
            r10.<init>(r11, r12, r14, r16)     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            com.google.gson.Gson r5 = r1.gson     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            r5.toJson(r0)     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            r2.onSuccess(r0)     // Catch: com.amazon.alexa.location.networking.LocationException -> L90 java.io.IOException -> L92 java.lang.Throwable -> Lb3
            goto Lab
        L90:
            r0 = move-exception
            goto La3
        L92:
            r0 = move-exception
            goto Lb7
        L94:
            com.amazon.alexa.location.networking.LocationException r0 = new com.amazon.alexa.location.networking.LocationException     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            com.amazon.alexa.location.networking.LocationErrorCode r4 = com.amazon.alexa.location.networking.LocationErrorCode.PERMISSION_ERROR     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            java.lang.String r5 = "[ERROR] Location permission is not granted."
            r0.<init>(r4, r5)     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
            throw r0     // Catch: java.lang.Throwable -> L9e com.amazon.alexa.location.networking.LocationException -> La1 java.io.IOException -> Lb5
        L9e:
            r0 = move-exception
            r4 = 0
            goto Lca
        La1:
            r0 = move-exception
            r4 = 0
        La3:
            r1.recordDetailedMetrics(r0, r3)     // Catch: java.lang.Throwable -> Lb3
            r2.onError(r0)     // Catch: java.lang.Throwable -> Lb3
            if (r4 == 0) goto Lc9
        Lab:
            okhttp3.ResponseBody r0 = r4.body()
            r0.close()
            goto Lc9
        Lb3:
            r0 = move-exception
            goto Lca
        Lb5:
            r0 = move-exception
            r4 = 0
        Lb7:
            com.amazon.alexa.location.networking.LocationException r5 = new com.amazon.alexa.location.networking.LocationException     // Catch: java.lang.Throwable -> Lb3
            com.amazon.alexa.location.networking.LocationErrorCode r6 = com.amazon.alexa.location.networking.LocationErrorCode.ALS_TIMEOUT     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r7 = "[ERROR] ALS_UPDATE_GEOFENCE times out."
            r5.<init>(r6, r7, r0)     // Catch: java.lang.Throwable -> Lb3
            r1.recordDetailedMetrics(r5, r3)     // Catch: java.lang.Throwable -> Lb3
            r2.onError(r5)     // Catch: java.lang.Throwable -> Lb3
            if (r4 == 0) goto Lc9
            goto Lab
        Lc9:
            return
        Lca:
            if (r4 == 0) goto Ld3
            okhttp3.ResponseBody r2 = r4.body()
            r2.close()
        Ld3:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.location.networking.AlexaLocationNetworkService.lambda$updateGeofence$1$AlexaLocationNetworkService(java.lang.String, double, double, double, io.reactivex.rxjava3.core.SingleEmitter):void");
    }

    @VisibleForTesting
    Response makeGetRequest(@NonNull String str) throws IOException {
        String str2 = "url: " + str;
        return this.httpClient.newCall(new Request.Builder().url(str).build()).execute();
    }

    @VisibleForTesting
    Response makePostRequest(@NonNull String str, @NonNull String str2) throws IOException {
        String str3 = "url: " + str;
        String str4 = "body: " + str2;
        return this.httpClient.newCall(new Request.Builder().url(str).post(RequestBody.create(str2, JSON)).build()).execute();
    }

    @VisibleForTesting
    Response makePutRequest(@NonNull String str, @NonNull String str2) throws IOException {
        String str3 = "url: " + str;
        String str4 = "body: " + str2;
        return this.httpClient.newCall(new Request.Builder().url(str).put(RequestBody.create(str2, JSON)).build()).execute();
    }

    @Override // com.amazon.alexa.location.networking.als.ALSLocationNetworkService
    @NonNull
    public Completable reportGeofenceStatus(@NonNull String str, @NonNull String str2, @NonNull final String str3, @NonNull final List<GeoFenceStatus> list) {
        return Completable.fromObservable(Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.location.networking.-$$Lambda$AlexaLocationNetworkService$ELho2O0kVevnAbRPBxH2Jb14Ipc
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                AlexaLocationNetworkService.this.lambda$reportGeofenceStatus$4$AlexaLocationNetworkService(str3, list, observableEmitter);
            }
        }));
    }

    @Override // com.amazon.alexa.location.networking.als.ALSLocationNetworkService
    public Single<String> triggerGeofence(@NonNull String str, @NonNull String str2, @NonNull final String str3, @NonNull final String str4, final ALSTriggerEvent aLSTriggerEvent, final long j) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.location.networking.-$$Lambda$AlexaLocationNetworkService$-A4qrmu5ebJym0srUzzxu9Z03ds
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                AlexaLocationNetworkService.this.lambda$triggerGeofence$2$AlexaLocationNetworkService(str3, str4, aLSTriggerEvent, j, singleEmitter);
            }
        });
    }

    @Override // com.amazon.alexa.location.networking.als.ALSLocationNetworkService
    public Single<ALSGeofence> updateGeofence(@NonNull String str, @NonNull String str2, @NonNull final String str3, final double d, final double d2, final double d3) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.location.networking.-$$Lambda$AlexaLocationNetworkService$RvaFHfKR_kZWb0MgikzXoa3VxRg
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                AlexaLocationNetworkService.this.lambda$updateGeofence$1$AlexaLocationNetworkService(str3, d, d2, d3, singleEmitter);
            }
        });
    }
}
