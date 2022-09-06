package com.amazon.alexa.location.networking.gateway;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import com.amazon.alexa.location.networking.LocationErrorCode;
import com.amazon.alexa.location.networking.LocationException;
import com.amazon.alexa.location.networking.alps.models.ISO8601TimeSupplier;
import com.amazon.alexa.location.networking.alps.models.ReportLocationsRequestBody;
import com.amazon.alexa.location.networking.alps.models.ReportLocationsRequestGenerator;
import com.amazon.alexa.location.networking.utils.MobilyticsUtil;
import com.amazon.alexa.location.networking.utils.MobilyticsUtilCommonStrings;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.network.api.HttpClient;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import java.io.IOException;
import java.util.TimeZone;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
/* loaded from: classes9.dex */
public class AlexaLocationPlatformServiceNetworkGateway {
    private static final String API_PATH_REPORT_LOCATION = "%s/v1/locations/report";
    private static final String TAG = "ALPSNetworkGateway";
    private final LazyComponent<EnvironmentService> environmentService;
    private final Gson gson;
    private final OkHttpClient httpClient;
    private final ISO8601TimeSupplier iso8601TimeSupplier;
    private final LazyComponent<Mobilytics> mobilytics;
    private static final MediaType JSON = MediaType.parse(LocationPublisher.CONTENT_TYPE_JSON);
    private static final String COMPONENT_ALPS_NETWORK_GATEWAY = MobilyticsUtil.getComponentName(MobilyticsUtilCommonStrings.ComponentSuffix.ALPS_NETWORK_GATEWAY);

    public AlexaLocationPlatformServiceNetworkGateway() {
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        this.httpClient = ((HttpClient) componentRegistry.getLazy(HttpClient.class).mo10268get()).okHttpClientWithBearerAuth();
        this.environmentService = componentRegistry.getLazy(EnvironmentService.class);
        this.gson = new GsonBuilder().create();
        this.mobilytics = componentRegistry.getLazy(Mobilytics.class);
        this.iso8601TimeSupplier = new ISO8601TimeSupplier("yyyy-MM-dd'T'HH:mm:ss'Z'", TimeZone.getTimeZone(Constants.UTC));
    }

    @Nullable
    @VisibleForTesting
    LocationException buildLocationExceptionFromResponse(@NonNull Response response) {
        if (response.isSuccessful()) {
            return null;
        }
        int code = response.code();
        if (code == 400) {
            LocationErrorCode locationErrorCode = LocationErrorCode.ALPS_400;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received HTTP 400 when calling ALPS backend: ");
            outline107.append(response.message());
            return new LocationException(locationErrorCode, outline107.toString());
        } else if (code == 401) {
            LocationErrorCode locationErrorCode2 = LocationErrorCode.ALPS_401;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Received HTTP 401 when calling ALPS backend: ");
            outline1072.append(response.message());
            return new LocationException(locationErrorCode2, outline1072.toString());
        } else if (code == 403) {
            LocationErrorCode locationErrorCode3 = LocationErrorCode.ALPS_403;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Received HTTP 403 when calling ALPS backend: ");
            outline1073.append(response.message());
            return new LocationException(locationErrorCode3, outline1073.toString());
        } else if (code == 404) {
            LocationErrorCode locationErrorCode4 = LocationErrorCode.ALPS_404;
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Received HTTP 404 when calling ALPS backend: ");
            outline1074.append(response.message());
            return new LocationException(locationErrorCode4, outline1074.toString());
        } else if (code == 500) {
            LocationErrorCode locationErrorCode5 = LocationErrorCode.ALPS_500;
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("Received HTTP 500 when calling ALPS backend: ");
            outline1075.append(response.message());
            return new LocationException(locationErrorCode5, outline1075.toString());
        } else if (code != 503) {
            LocationErrorCode locationErrorCode6 = LocationErrorCode.ALPS_NOT_RECOGNIZE;
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("Received unknown HTTP error when calling ALPS backend: ");
            outline1076.append(response.message());
            return new LocationException(locationErrorCode6, outline1076.toString());
        } else {
            LocationErrorCode locationErrorCode7 = LocationErrorCode.ALPS_503;
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("Received HTTP 503 when calling ALPS backend: ");
            outline1077.append(response.message());
            return new LocationException(locationErrorCode7, outline1077.toString());
        }
    }

    @VisibleForTesting
    String getEndpoint() {
        return this.environmentService.mo10268get().getApiGatewayEndpoint();
    }

    public /* synthetic */ void lambda$reportLocationRxSingle$1$AlexaLocationPlatformServiceNetworkGateway(Throwable th) throws Throwable {
        MobilyticsUtil.recordExceptionMetrics(this.mobilytics, (LocationException) th, MobilyticsUtilCommonStrings.MetricsID.REPORT_LOCATION_FAILURE_PREFIX, COMPONENT_ALPS_NETWORK_GATEWAY);
    }

    public /* synthetic */ void lambda$reportLocationRxSingle$2$AlexaLocationPlatformServiceNetworkGateway() throws Throwable {
        String str = COMPONENT_ALPS_NETWORK_GATEWAY;
        this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtilCommonStrings.MetricsID.REPORT_LOCATION_COMPLETE, true, str, str);
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
            throw new LocationException(LocationErrorCode.ALPS_IO_EXCEPTION, "Received IO exception when making POST request to ALPS backend", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: reportLocation */
    public void lambda$reportLocationRxSingle$0$AlexaLocationPlatformServiceNetworkGateway(double d, double d2, double d3, @NonNull String str, ISO8601TimeSupplier iSO8601TimeSupplier) throws LocationException {
        String format = String.format(API_PATH_REPORT_LOCATION, getEndpoint());
        try {
            ReportLocationsRequestBody generateReportLocationsRequest = ReportLocationsRequestGenerator.generateReportLocationsRequest(d, d2, d3, str, iSO8601TimeSupplier);
            if (generateReportLocationsRequest != null) {
                makePostRequest(format, generateReportLocationsRequest.toJsonObject().toString());
                return;
            }
            throw new LocationException(LocationErrorCode.GENERIC_ERROR, "[ERROR] Cannot build request to report location.");
        } catch (DeviceInformationException e) {
            throw new LocationException(LocationErrorCode.ALPS_DEVICE_INFO_ERROR, "Error in generating report locations request", e);
        } catch (JSONException e2) {
            throw new LocationException(LocationErrorCode.ALPS_PAYLOAD_ERROR, "Error in generating json for report locations request", e2);
        }
    }

    @Deprecated
    public Completable reportLocationRxSingle(double d, double d2, double d3) {
        return reportLocationRxSingle(d, d2, d3, "CONTEXTUAL_EVENT_UPDATE", this.iso8601TimeSupplier);
    }

    public Completable reportLocationRxSingle(double d, double d2, double d3, @NonNull String str) {
        return reportLocationRxSingle(d, d2, d3, str, this.iso8601TimeSupplier);
    }

    @VisibleForTesting
    Completable reportLocationRxSingle(final double d, final double d2, final double d3, @NonNull final String str, final ISO8601TimeSupplier iSO8601TimeSupplier) {
        return Completable.fromAction(new Action() { // from class: com.amazon.alexa.location.networking.gateway.-$$Lambda$AlexaLocationPlatformServiceNetworkGateway$_bEpdBLVngkahC69aQP96mXW2Uk
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AlexaLocationPlatformServiceNetworkGateway.this.lambda$reportLocationRxSingle$0$AlexaLocationPlatformServiceNetworkGateway(d, d2, d3, str, iSO8601TimeSupplier);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.location.networking.gateway.-$$Lambda$AlexaLocationPlatformServiceNetworkGateway$g_rYGXw6Htq0sfRX-RponCo__RE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaLocationPlatformServiceNetworkGateway.this.lambda$reportLocationRxSingle$1$AlexaLocationPlatformServiceNetworkGateway((Throwable) obj);
            }
        }).doOnComplete(new Action() { // from class: com.amazon.alexa.location.networking.gateway.-$$Lambda$AlexaLocationPlatformServiceNetworkGateway$6pZr2LO-qQtJ7Cld-F_86WDdx1E
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AlexaLocationPlatformServiceNetworkGateway.this.lambda$reportLocationRxSingle$2$AlexaLocationPlatformServiceNetworkGateway();
            }
        });
    }
}
