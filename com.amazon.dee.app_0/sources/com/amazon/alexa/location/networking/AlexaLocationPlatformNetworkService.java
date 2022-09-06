package com.amazon.alexa.location.networking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.location.networking.alps.ALPSLocationNetworkService;
import com.amazon.alexa.location.networking.alps.models.ISO8601TimeSupplier;
import com.amazon.alexa.location.networking.alps.models.ReportLocationsRequestBody;
import com.amazon.alexa.location.networking.alps.models.ReportLocationsRequestGenerator;
import com.amazon.alexa.location.networking.utils.MobilyticsUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import java.io.IOException;
import java.util.TimeZone;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
/* loaded from: classes9.dex */
public class AlexaLocationPlatformNetworkService implements ALPSLocationNetworkService {
    private static final String ALPS_REPORT_LOCATION = "%s/v1/locations/report";
    private final Context context;
    private final LazyComponent<EnvironmentService> environmentService;
    private final LazyComponent<FeatureServiceV2> fsv2;
    private final Gson gson;
    private final OkHttpClient httpClient;
    private final ISO8601TimeSupplier iso8601TimeSupplier;
    private final LazyComponent<Mobilytics> mobilytics;
    private final LocationPermissionService permissionService;
    public static final MediaType JSON = MediaType.parse(LocationPublisher.CONTENT_TYPE_JSON);
    private static final String TAG = AlexaLocationPlatformNetworkService.class.getSimpleName();
    private static final String COMPONENT_NAME = MobilyticsUtil.getComponentName(TAG);

    public AlexaLocationPlatformNetworkService(OkHttpClient okHttpClient, Gson gson, LazyComponent<Mobilytics> lazyComponent, LocationPermissionService locationPermissionService, Context context) {
        this.httpClient = okHttpClient;
        this.gson = gson;
        this.mobilytics = lazyComponent;
        this.permissionService = locationPermissionService;
        this.context = context;
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        this.environmentService = componentRegistry.getLazy(EnvironmentService.class);
        this.fsv2 = componentRegistry.getLazy(FeatureServiceV2.class);
        this.iso8601TimeSupplier = new ISO8601TimeSupplier("yyyy-MM-dd'T'HH:mm:ss'Z'", TimeZone.getTimeZone(Constants.UTC));
    }

    private void handleFailureStatusCode(int i) throws LocationException {
        if (i != 400) {
            if (i == 401) {
                throw new LocationException(LocationErrorCode.ALPS_401, "[ERROR] ALPS call returns 401");
            }
            if (i == 403) {
                throw new LocationException(LocationErrorCode.ALPS_403, "[ERROR] ALPS call returns 403");
            }
            if (i == 404) {
                throw new LocationException(LocationErrorCode.ALPS_404, "[ERROR] ALPS call returns 404");
            }
            if (i == 500) {
                throw new LocationException(LocationErrorCode.ALPS_500, "[ERROR] ALPS call returns 500");
            }
            if (i != 503) {
                throw new LocationException(LocationErrorCode.ALPS_NOT_RECOGNIZE, "[ERROR] Not recognized ALPS error");
            }
            throw new LocationException(LocationErrorCode.ALPS_503, "[ERROR] ALPS call returns 503");
        }
        throw new LocationException(LocationErrorCode.ALPS_400, "[ERROR] ALPS call returns 400");
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @VisibleForTesting
    String getEndpoint() {
        return this.environmentService.mo10268get().getApiGatewayEndpoint();
    }

    public /* synthetic */ void lambda$reportLocation$0$AlexaLocationPlatformNetworkService(double d, double d2, double d3, String str, ISO8601TimeSupplier iSO8601TimeSupplier, ObservableEmitter observableEmitter) throws Throwable {
        String format;
        ReportLocationsRequestBody generateReportLocationsRequest;
        Response response = null;
        try {
            try {
                try {
                    try {
                        format = String.format(ALPS_REPORT_LOCATION, getEndpoint());
                        generateReportLocationsRequest = ReportLocationsRequestGenerator.generateReportLocationsRequest(d, d2, d3, str, iSO8601TimeSupplier);
                    } catch (IOException e) {
                        observableEmitter.onError(new LocationException(LocationErrorCode.ALPS_IO_EXCEPTION, "[ERROR] ALPS_REPORT_LOCATION IO exception.", e));
                        if (0 == 0) {
                            return;
                        }
                    }
                } catch (DeviceInformationException e2) {
                    observableEmitter.onError(new LocationException(LocationErrorCode.ALPS_DEVICE_INFO_ERROR, "Error in generating report locations request", e2));
                    if (0 == 0) {
                        return;
                    }
                }
            } catch (LocationException e3) {
                observableEmitter.onError(e3);
                if (0 == 0) {
                    return;
                }
            } catch (JSONException e4) {
                observableEmitter.onError(new LocationException(LocationErrorCode.ALPS_PAYLOAD_ERROR, "Error in generating json for report locations request", e4));
                if (0 == 0) {
                    return;
                }
            }
            if (generateReportLocationsRequest != null) {
                response = makePostRequest(format, generateReportLocationsRequest.toJsonObject().toString());
                if (!response.isSuccessful()) {
                    String.format("[ERROR] ALPS_REPORT_LOCATION failure: %s , %s", Integer.valueOf(response.code()), response.message());
                    handleFailureStatusCode(response.code());
                }
                if (!TextUtils.isEmpty(response.message()) && 204 != response.code()) {
                    throw new LocationException(LocationErrorCode.GENERIC_ERROR, "[ERROR] ALPS_REPORT_LOCATION response has error.");
                }
                observableEmitter.onComplete();
                response.body().close();
                return;
            }
            throw new LocationException(LocationErrorCode.GENERIC_ERROR, "[ERROR] Cannot build request to report location.");
        } catch (Throwable th) {
            if (0 != 0) {
                response.body().close();
            }
            throw th;
        }
    }

    @VisibleForTesting
    Response makePostRequest(@NonNull String str, @NonNull String str2) throws IOException {
        String str3 = "url: " + str;
        String str4 = "body: " + str2;
        return this.httpClient.newCall(new Request.Builder().url(str).post(RequestBody.create(str2, JSON)).build()).execute();
    }

    @Override // com.amazon.alexa.location.networking.alps.ALPSLocationNetworkService
    @NonNull
    @Deprecated
    public Completable reportLocation(double d, double d2, double d3) {
        return reportLocation(d, d2, d3, "CONTEXTUAL_EVENT_UPDATE", this.iso8601TimeSupplier);
    }

    @Override // com.amazon.alexa.location.networking.alps.ALPSLocationNetworkService
    @NonNull
    public Completable reportLocation(double d, double d2, double d3, @NonNull String str) {
        return reportLocation(d, d2, d3, str, this.iso8601TimeSupplier);
    }

    @NonNull
    @VisibleForTesting
    Completable reportLocation(final double d, final double d2, final double d3, @NonNull final String str, final ISO8601TimeSupplier iSO8601TimeSupplier) {
        return Completable.fromObservable(Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.location.networking.-$$Lambda$AlexaLocationPlatformNetworkService$I14KHUbjPHUUPYDlvk1CtMBzDJ4
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                AlexaLocationPlatformNetworkService.this.lambda$reportLocation$0$AlexaLocationPlatformNetworkService(d, d2, d3, str, iSO8601TimeSupplier, observableEmitter);
            }
        }));
    }
}
