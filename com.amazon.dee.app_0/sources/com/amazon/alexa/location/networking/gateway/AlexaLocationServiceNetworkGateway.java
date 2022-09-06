package com.amazon.alexa.location.networking.gateway;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.location.networking.LocationErrorCode;
import com.amazon.alexa.location.networking.LocationException;
import com.amazon.alexa.location.networking.LocationPermissionService;
import com.amazon.alexa.location.networking.als.models.ALSGeofence;
import com.amazon.alexa.location.networking.als.models.ALSTriggerEvent;
import com.amazon.alexa.location.networking.als.models.GeoFenceStatus;
import com.amazon.alexa.location.networking.als.networkModels.GetGeofenceByDeviceResponseBody;
import com.amazon.alexa.location.networking.als.networkModels.ReportGeofenceStatusRequestBody;
import com.amazon.alexa.location.networking.als.networkModels.TriggerGeofenceRequestBody;
import com.amazon.alexa.location.networking.utils.AppLifecycleUtil;
import com.amazon.alexa.location.networking.utils.MobilyticsUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.network.api.HttpClient;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/* loaded from: classes9.dex */
public class AlexaLocationServiceNetworkGateway {
    private static final String API_PATH_GET_GEOFENCES = "%s/v1/geofences/devices/~current";
    private static final String API_PATH_REPORT_SYNC_STATUS = "%s/v1/geofences/updateStatus";
    private static final String API_PATH_TRIGGER_GEOFENCE = "%s/v1/geofences/%s/trigger";
    private static final String PARAMETER_PERSON_ID = "personId=%s";
    private static final String QUERY_BEGIN = "?";
    private final Context context;
    private final LazyComponent<EnvironmentService> environmentService;
    private final Gson gson;
    private final OkHttpClient httpClient;
    private LocationPermissionService locationPermissionService;
    private final LazyComponent<Mobilytics> mobilytics;
    private final LazyComponent<PersonIdProvider> personIdProvider;
    private static final MediaType JSON = MediaType.parse(LocationPublisher.CONTENT_TYPE_JSON);
    private static final String COMPONENT_SYNC_GEOFENCE = MobilyticsUtil.getComponentName("sync_geofence");
    private static final String COMPONENT_TRIGGER_GEOFENCE = MobilyticsUtil.getComponentName("trigger_geofence");
    private static final String COMPONENT_REPORT_SYNC_STATUS = MobilyticsUtil.getComponentName("als_report_geofences_status");
    private static final String COMPONENT_PERMISSION = MobilyticsUtil.getComponentName("permission");
    private static final String TAG = AlexaLocationServiceNetworkGateway.class.getSimpleName();

    /* renamed from: com.amazon.alexa.location.networking.gateway.AlexaLocationServiceNetworkGateway$1  reason: invalid class name */
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

    public AlexaLocationServiceNetworkGateway(Context context, LocationPermissionService locationPermissionService) {
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        this.httpClient = ((HttpClient) componentRegistry.getLazy(HttpClient.class).mo10268get()).okHttpClientWithBearerAuth();
        this.personIdProvider = componentRegistry.getLazy(PersonIdProvider.class);
        this.environmentService = componentRegistry.getLazy(EnvironmentService.class);
        this.context = context;
        this.gson = new GsonBuilder().create();
        this.mobilytics = componentRegistry.getLazy(Mobilytics.class);
        this.locationPermissionService = locationPermissionService;
    }

    private void checkLocationPermission() {
        if (this.locationPermissionService.hasFullLocationPermission()) {
            String str = COMPONENT_PERMISSION;
            this.mobilytics.mo10268get().recordOccurrence("permission_always", true, str, str);
            return;
        }
        String str2 = COMPONENT_PERMISSION;
        this.mobilytics.mo10268get().recordOccurrence("permission_denied", true, str2, str2);
    }

    private void recordExceptionMetrics(LocationException locationException, String str) {
        int ordinal = locationException.getErrorCode().ordinal();
        if (ordinal == 4) {
            this.mobilytics.mo10268get().recordOccurrence("fetch_error_timeout", true, str, str);
        } else if (ordinal == 5) {
            this.mobilytics.mo10268get().recordOccurrence("fetch_error_not_recognized", true, str, str);
        } else if (ordinal != 6) {
            switch (ordinal) {
                case 14:
                    this.mobilytics.mo10268get().recordOccurrence("fetch_error_400", true, str, str);
                    return;
                case 15:
                    this.mobilytics.mo10268get().recordOccurrence("fetch_error_401", true, str, str);
                    return;
                case 16:
                    this.mobilytics.mo10268get().recordOccurrence("fetch_error_403", true, str, str);
                    return;
                case 17:
                    this.mobilytics.mo10268get().recordOccurrence("fetch_error_404", true, str, str);
                    return;
                case 18:
                    this.mobilytics.mo10268get().recordOccurrence("fetch_error_500", true, str, str);
                    return;
                case 19:
                    this.mobilytics.mo10268get().recordOccurrence("fetch_error_503", true, str, str);
                    return;
                default:
                    return;
            }
        } else {
            this.mobilytics.mo10268get().recordOccurrence("fetch_error_payload", true, str, str);
        }
    }

    @Nullable
    @VisibleForTesting
    LocationException buildLocationExceptionFromResponse(@NonNull Response response) {
        if (response.isSuccessful()) {
            return null;
        }
        int code = response.code();
        if (code == 400) {
            LocationErrorCode locationErrorCode = LocationErrorCode.ALS_400;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received HTTP 400 when calling ALS backend: ");
            outline107.append(response.message());
            return new LocationException(locationErrorCode, outline107.toString());
        } else if (code == 401) {
            LocationErrorCode locationErrorCode2 = LocationErrorCode.ALS_401;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Received HTTP 401 when calling ALS backend: ");
            outline1072.append(response.message());
            return new LocationException(locationErrorCode2, outline1072.toString());
        } else if (code == 403) {
            LocationErrorCode locationErrorCode3 = LocationErrorCode.ALS_403;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Received HTTP 403 when calling ALS backend: ");
            outline1073.append(response.message());
            return new LocationException(locationErrorCode3, outline1073.toString());
        } else if (code == 404) {
            LocationErrorCode locationErrorCode4 = LocationErrorCode.ALS_404;
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Received HTTP 404 when calling ALS backend: ");
            outline1074.append(response.message());
            return new LocationException(locationErrorCode4, outline1074.toString());
        } else if (code == 500) {
            LocationErrorCode locationErrorCode5 = LocationErrorCode.ALS_500;
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("Received HTTP 500 when calling ALS backend: ");
            outline1075.append(response.message());
            return new LocationException(locationErrorCode5, outline1075.toString());
        } else if (code != 503) {
            LocationErrorCode locationErrorCode6 = LocationErrorCode.ALS_NOT_RECOGNIZE;
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("Received unknown HTTP error when calling ALS backend: ");
            outline1076.append(response.message());
            return new LocationException(locationErrorCode6, outline1076.toString());
        } else {
            LocationErrorCode locationErrorCode7 = LocationErrorCode.ALS_503;
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("Received HTTP 503 when calling ALS backend: ");
            outline1077.append(response.message());
            return new LocationException(locationErrorCode7, outline1077.toString());
        }
    }

    @VisibleForTesting
    String getEndpoint() {
        return this.environmentService.mo10268get().getApiGatewayEndpoint();
    }

    public List<ALSGeofence> getGeofencesByDevice() throws LocationException {
        String format = String.format(API_PATH_GET_GEOFENCES, getEndpoint());
        String personId = this.personIdProvider.mo10268get().getPersonId();
        if (!TextUtils.isEmpty(personId)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(format, "?");
            outline113.append(String.format(PARAMETER_PERSON_ID, personId));
            format = outline113.toString();
        } else {
            String runningStateMetricName = AppLifecycleUtil.getRunningStateMetricName("missing_person_id", AppLifecycleUtil.isAppRunningForeground(this.context));
            String str = COMPONENT_SYNC_GEOFENCE;
            this.mobilytics.mo10268get().recordOccurrence(runningStateMetricName, true, str, str);
        }
        try {
            MobilyticsMetricsTimer createTimer = this.mobilytics.mo10268get().createTimer("fetch_als", COMPONENT_SYNC_GEOFENCE, COMPONENT_SYNC_GEOFENCE);
            String makeGetRequest = makeGetRequest(format);
            if (createTimer != null) {
                createTimer.finishTimer();
                this.mobilytics.mo10268get().recordTimer(createTimer);
            }
            GetGeofenceByDeviceResponseBody getGeofenceByDeviceResponseBody = (GetGeofenceByDeviceResponseBody) this.gson.fromJson(makeGetRequest, (Class<Object>) GetGeofenceByDeviceResponseBody.class);
            if (getGeofenceByDeviceResponseBody != null) {
                List<ALSGeofence> geofences = getGeofenceByDeviceResponseBody.getGeofences();
                if (geofences != null) {
                    for (ALSGeofence aLSGeofence : geofences) {
                        if (aLSGeofence == null || aLSGeofence.getId() == null || aLSGeofence.getCircularRegion() == null) {
                            throw new LocationException(LocationErrorCode.ALS_PAYLOAD_ERROR, "");
                        }
                    }
                    return geofences;
                }
                throw new LocationException(LocationErrorCode.ALS_PAYLOAD_ERROR, "");
            }
            throw new LocationException(LocationErrorCode.ALS_PAYLOAD_ERROR, "");
        } catch (JsonSyntaxException unused) {
            throw new LocationException(LocationErrorCode.ALS_PAYLOAD_ERROR, "");
        }
    }

    public Single<List<ALSGeofence>> getGeofencesByDeviceRxSingle() {
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.location.networking.gateway.-$$Lambda$tceluobWut35EgYTQ5imUes_vbA
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return AlexaLocationServiceNetworkGateway.this.getGeofencesByDevice();
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.location.networking.gateway.-$$Lambda$AlexaLocationServiceNetworkGateway$KgOSyMQWS_SK3LTjWhV4W-DdxDs
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaLocationServiceNetworkGateway.this.lambda$getGeofencesByDeviceRxSingle$0$AlexaLocationServiceNetworkGateway((Throwable) obj);
            }
        }).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.location.networking.gateway.-$$Lambda$AlexaLocationServiceNetworkGateway$ChRh6-ISoFDyIim5fO8KSzGhlnM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaLocationServiceNetworkGateway.this.lambda$getGeofencesByDeviceRxSingle$1$AlexaLocationServiceNetworkGateway((List) obj);
            }
        });
    }

    public /* synthetic */ void lambda$getGeofencesByDeviceRxSingle$0$AlexaLocationServiceNetworkGateway(Throwable th) throws Throwable {
        if (th instanceof LocationException) {
            recordExceptionMetrics((LocationException) th, COMPONENT_SYNC_GEOFENCE);
        }
    }

    public /* synthetic */ void lambda$getGeofencesByDeviceRxSingle$1$AlexaLocationServiceNetworkGateway(List list) throws Throwable {
        if (list.size() > 0) {
            checkLocationPermission();
        }
        String str = COMPONENT_SYNC_GEOFENCE;
        this.mobilytics.mo10268get().recordOccurrence("fetch_successful", true, str, str);
    }

    public /* synthetic */ String lambda$triggerGeofenceRxSingle$2$AlexaLocationServiceNetworkGateway(String str, ALSTriggerEvent aLSTriggerEvent, long j) throws Exception {
        triggerGeofence(str, aLSTriggerEvent, j);
        return str;
    }

    @VisibleForTesting
    String makeGetRequest(@NonNull String str) throws LocationException {
        try {
            Response execute = this.httpClient.newCall(new Request.Builder().url(str).build()).execute();
            if (!execute.isSuccessful()) {
                LocationException buildLocationExceptionFromResponse = buildLocationExceptionFromResponse(execute);
                if (buildLocationExceptionFromResponse != null) {
                    throw buildLocationExceptionFromResponse;
                }
            } else {
                this.mobilytics.mo10268get().recordOccurrence("fetch_als", true, COMPONENT_SYNC_GEOFENCE, COMPONENT_SYNC_GEOFENCE);
            }
            String string = execute.body().string();
            execute.close();
            return string;
        } catch (IOException e) {
            throw new LocationException(LocationErrorCode.ALS_TIMEOUT, "Received IO exception when making GET request to ALS backend", e);
        }
    }

    @VisibleForTesting
    String makePostRequest(@NonNull String str, @NonNull String str2) throws LocationException {
        LocationException buildLocationExceptionFromResponse;
        try {
            Response execute = this.httpClient.newCall(new Request.Builder().url(str).post(RequestBody.create(JSON, str2)).build()).execute();
            if (!execute.isSuccessful() && (buildLocationExceptionFromResponse = buildLocationExceptionFromResponse(execute)) != null) {
                throw buildLocationExceptionFromResponse;
            }
            String string = execute.body().string();
            execute.close();
            return string;
        } catch (IOException e) {
            throw new LocationException(LocationErrorCode.ALS_TIMEOUT, "Received IO exception when making POST request to ALS backend", e);
        }
    }

    /* renamed from: reportSyncStatus */
    public void lambda$reportSyncStatusRxSingle$3$AlexaLocationServiceNetworkGateway(@NonNull List<GeoFenceStatus> list) throws LocationException {
        String personId = this.personIdProvider.mo10268get().getPersonId();
        String str = COMPONENT_REPORT_SYNC_STATUS;
        MobilyticsMetricsTimer createTimer = this.mobilytics.mo10268get().createTimer("fetch_als", str, str);
        try {
            makePostRequest(String.format(API_PATH_REPORT_SYNC_STATUS, getEndpoint()), this.gson.toJson(new ReportGeofenceStatusRequestBody(personId, list)));
            String str2 = COMPONENT_REPORT_SYNC_STATUS;
            this.mobilytics.mo10268get().recordOccurrence("fetch_als", true, str2, str2);
            if (createTimer == null) {
                return;
            }
            createTimer.finishTimer();
            this.mobilytics.mo10268get().recordTimer(createTimer);
        } catch (LocationException e) {
            if (createTimer != null) {
                createTimer.finishTimer();
                this.mobilytics.mo10268get().recordTimer(createTimer);
            }
            recordExceptionMetrics(e, COMPONENT_REPORT_SYNC_STATUS);
            throw e;
        }
    }

    public Completable reportSyncStatusRxSingle(@NonNull final List<GeoFenceStatus> list) {
        return Completable.fromAction(new Action() { // from class: com.amazon.alexa.location.networking.gateway.-$$Lambda$AlexaLocationServiceNetworkGateway$3iY9VexIRR3STex_NU4xXRkl37s
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AlexaLocationServiceNetworkGateway.this.lambda$reportSyncStatusRxSingle$3$AlexaLocationServiceNetworkGateway(list);
            }
        });
    }

    public void triggerGeofence(@NonNull String str, @NonNull ALSTriggerEvent aLSTriggerEvent, long j) throws LocationException {
        makePostRequest(String.format(API_PATH_TRIGGER_GEOFENCE, getEndpoint(), str), this.gson.toJson(new TriggerGeofenceRequestBody(this.personIdProvider.mo10268get().getPersonId(), aLSTriggerEvent, j)));
    }

    public Single<String> triggerGeofenceRxSingle(@NonNull final String str, @NonNull final ALSTriggerEvent aLSTriggerEvent, final long j) {
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.location.networking.gateway.-$$Lambda$AlexaLocationServiceNetworkGateway$ulTxkYT7CSjTYs0ETHgBJCtygZ8
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return AlexaLocationServiceNetworkGateway.this.lambda$triggerGeofenceRxSingle$2$AlexaLocationServiceNetworkGateway(str, aLSTriggerEvent, j);
            }
        });
    }
}
