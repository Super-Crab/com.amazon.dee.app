package com.amazon.alexa.accessorykit.findmy.reporter;

import android.util.Pair;
import com.amazon.alexa.accessory.BuildStageProvider;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.avsclient.utils.ISO8601TimeSupplier;
import com.amazon.alexa.accessory.internal.http.HttpCall;
import com.amazon.alexa.accessory.internal.http.HttpMethod;
import com.amazon.alexa.accessory.internal.http.HttpRequest;
import com.amazon.alexa.accessory.internal.http.HttpStatusUnsuccessfulException;
import com.amazon.alexa.accessory.internal.http.JsonHttpBody;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.MetricsUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessorykit.endpoint.AlexaEndpointProvider;
import com.amazon.alexa.accessorykit.findmy.AccessoryLocationReporter;
import com.amazon.alexa.accessorykit.findmy.FindMyAccessoryInformation;
import com.amazon.alexa.accessorykit.findmy.LocationInformation;
import com.amazon.alexa.accessorykit.metrics.MetricsConstants;
import com.amazon.alexa.accessorykit.utils.AccessTokenUtils;
import com.amazon.alexa.marketplace.api.MarketplaceService;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.IOException;
import java.util.Collections;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class DefaultAccessoryLocationReporter implements AccessoryLocationReporter {
    private static final String ACCESS_TOKEN_KEY = "x-amz-access-token";
    private static final String TAG = "DefaultAccessoryLocationReporter";
    private final AlexaEndpointProvider alexaEndpointProvider;
    private final BuildStageProvider buildStageProvider;
    private final ISO8601TimeSupplier iso8601TimeSupplier;
    private final MarketplaceService marketplaceService;
    private final UserSupplier userSupplier;

    public DefaultAccessoryLocationReporter(UserSupplier userSupplier, MarketplaceService marketplaceService, BuildStageProvider buildStageProvider, AlexaEndpointProvider alexaEndpointProvider) {
        Preconditions.notNull(userSupplier, "userSupplier");
        Preconditions.notNull(marketplaceService, "marketplaceService");
        Preconditions.notNull(buildStageProvider, "buildStageProvider");
        Preconditions.notNull(alexaEndpointProvider, "alexaEndpointProvider");
        this.userSupplier = userSupplier;
        this.marketplaceService = marketplaceService;
        this.buildStageProvider = buildStageProvider;
        this.alexaEndpointProvider = alexaEndpointProvider;
        this.iso8601TimeSupplier = new ISO8601TimeSupplier("yyyy-MM-dd'T'HH:mm:ss'Z'");
    }

    private Single<Pair<String, String>> getAccessTokenAndUrl(final String str) {
        return AccessTokenUtils.getAccessToken(this.userSupplier).doOnError(new Consumer() { // from class: com.amazon.alexa.accessorykit.findmy.reporter.-$$Lambda$DefaultAccessoryLocationReporter$qdrbsv8D6rjfyE8IMhr5cYKIqmE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                DefaultAccessoryLocationReporter.recordReportLocationsMetric(MetricsConstants.FindMy.REPORT_LOCATION_PREPARED_REQUEST, false, str);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessorykit.findmy.reporter.-$$Lambda$DefaultAccessoryLocationReporter$6Qn8bmPG8m3BK9_c2RC3YFJ2Oxs
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultAccessoryLocationReporter.this.lambda$getAccessTokenAndUrl$1$DefaultAccessoryLocationReporter((String) obj);
            }
        });
    }

    private ReportLocationsRequest getReportLocationsRequest(LocationInformation locationInformation, FindMyAccessoryInformation findMyAccessoryInformation) {
        return ReportLocationsRequest.create(Collections.singletonList(TrackableDevicesLocation.builder().setGeolocation(Geolocation.builder().setCoordinate(Coordinate.builder().setLatitudeInDegrees(locationInformation.getLatitudeInDegrees()).setLongitudeInDegrees(locationInformation.getLongitudeInDegrees()).setAccuracyInMeters(locationInformation.getAccuracyInMeters()).build()).setAltitude(Altitude.builder().setAltitudeInMeters(locationInformation.getAltitudeInMeters()).setAccuracyInMeters(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR).build()).setHeading(Heading.builder().setDirectionInDegrees(locationInformation.getBearingInDegrees()).setAccuracyInDegrees(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR).build()).setSpeed(Speed.builder().setSpeedInMetersPerSecond(locationInformation.getSpeedInMetersPerSecond()).setAccuracyInMetersPerSecond(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR).build()).build()).setTrackableDevices(Collections.singletonList(TrackableDevice.builder().setDeviceInfo(DeviceInfo.builder().setDeviceSerialNumber(findMyAccessoryInformation.getDeviceSerialNumber()).setDeviceType(findMyAccessoryInformation.getDeviceType()).build()).setEstimatedProximityDistance("UNKNOWN").setCause(findMyAccessoryInformation.getEventCause().getDescription()).build())).setTimestamp(this.iso8601TimeSupplier.getTime(locationInformation.getTime())).build()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ReportLocationsResponse lambda$null$2(FindMyAccessoryInformation findMyAccessoryInformation, String str, HttpCall.HttpResult httpResult) throws Throwable {
        Logger.d("%s: response for reportLocations %s", TAG, httpResult);
        recordReportLocationsStatusCode(httpResult.statuseCode, findMyAccessoryInformation.getDeviceType());
        int i = httpResult.statuseCode;
        if (i == 201) {
            byte[] bArr = httpResult.response;
            boolean z = (bArr == null || bArr.length == 0) ? false : true;
            recordReportLocationsMetric(MetricsConstants.FindMy.REPORT_LOCATION_HAS_DATA, z, str);
            if (z) {
                try {
                    ReportLocationsResponse mo1239create = ReportLocationsResponse.FACTORY.mo1239create(new JSONObject(new String(httpResult.response)));
                    recordReportLocationsMetric(MetricsConstants.FindMy.REPORT_LOCATION_PARSE_SUCCESS, true, str);
                    recordReportLocationsMetric(MetricsConstants.FindMy.REPORT_LOCATION_SUCCESS, true, str);
                    return mo1239create;
                } catch (JSONException e) {
                    recordReportLocationsMetric(MetricsConstants.FindMy.REPORT_LOCATION_PARSE_SUCCESS, false, str);
                    throw e;
                }
            }
            throw new IOException("DefaultAccessoryLocationReporter: HttpResult response is either null or empty");
        }
        throw new HttpStatusUnsuccessfulException(i);
    }

    private void recordMetricForThrowable(Throwable th, String str) {
        GeneratedOutlineSupport1.outline171(MetricsUtils.createMetricNameFromThrowable(MetricsConstants.FindMy.REPORT_LOCATION_EXCEPTION_PREFIX, th), str, true, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void recordReportLocationsMetric(String str, boolean z, String str2) {
        GeneratedOutlineSupport1.outline171(str, str2, z, null);
    }

    private static void recordReportLocationsStatusCode(int i, String str) {
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.FindMy.REPORT_LOCATION_STATUS_CODE, GeneratedOutlineSupport1.outline74(str, ":", i), true, null);
    }

    public /* synthetic */ Pair lambda$getAccessTokenAndUrl$1$DefaultAccessoryLocationReporter(String str) throws Throwable {
        return new Pair(str, this.alexaEndpointProvider.getReportLocationsEndpoint());
    }

    public /* synthetic */ void lambda$null$3$DefaultAccessoryLocationReporter(String str, Throwable th) throws Throwable {
        recordMetricForThrowable(th, str);
        recordReportLocationsMetric(MetricsConstants.FindMy.REPORT_LOCATION_SUCCESS, false, str);
    }

    public /* synthetic */ SingleSource lambda$reportAccessoryLocation$4$DefaultAccessoryLocationReporter(LocationInformation locationInformation, final FindMyAccessoryInformation findMyAccessoryInformation, final String str, Pair pair) throws Throwable {
        String str2 = (String) pair.first;
        String str3 = (String) pair.second;
        ReportLocationsRequest reportLocationsRequest = getReportLocationsRequest(locationInformation, findMyAccessoryInformation);
        if (Logger.shouldLog(Logger.Level.DEBUG)) {
            Logger.d("%s: Sending reportLocations Request: %s", TAG, reportLocationsRequest.toJsonObject().toString(4));
        }
        recordReportLocationsMetric(MetricsConstants.FindMy.REPORT_LOCATION_PREPARED_REQUEST, true, str);
        return HttpRequest.createBuilder().method(HttpMethod.POST).url(str3).header("x-amz-access-token", str2).body(new JsonHttpBody(reportLocationsRequest)).build().newCall().executeSingle().map(new Function() { // from class: com.amazon.alexa.accessorykit.findmy.reporter.-$$Lambda$DefaultAccessoryLocationReporter$vWZvUwleRcM8VOywMFr6yBBFTlQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultAccessoryLocationReporter.lambda$null$2(FindMyAccessoryInformation.this, str, (HttpCall.HttpResult) obj);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessorykit.findmy.reporter.-$$Lambda$DefaultAccessoryLocationReporter$rAy7fMj3yFZFL7X9-tArLtGRcQI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultAccessoryLocationReporter.this.lambda$null$3$DefaultAccessoryLocationReporter(str, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.accessorykit.findmy.AccessoryLocationReporter
    public Single<ReportLocationsResponse> reportAccessoryLocation(final LocationInformation locationInformation, final FindMyAccessoryInformation findMyAccessoryInformation) {
        Preconditions.notNull(locationInformation, "locationInformation");
        Preconditions.notNull(findMyAccessoryInformation, "accessoryInformation");
        final String deviceType = findMyAccessoryInformation.getDeviceType();
        return getAccessTokenAndUrl(deviceType).flatMap(new Function() { // from class: com.amazon.alexa.accessorykit.findmy.reporter.-$$Lambda$DefaultAccessoryLocationReporter$4hivOT9qVa7XRkE1qzTWDa2WdT8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultAccessoryLocationReporter.this.lambda$reportAccessoryLocation$4$DefaultAccessoryLocationReporter(locationInformation, findMyAccessoryInformation, deviceType, (Pair) obj);
            }
        }).subscribeOn(Schedulers.io());
    }
}
