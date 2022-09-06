package com.amazon.alexa.fitness.service.afits;

import android.util.Log;
import com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider;
import com.amazon.alexa.fitness.api.afits.AfitsClient;
import com.amazon.alexa.fitness.api.afits.AfitsTypesKt;
import com.amazon.alexa.fitness.api.afits.Altitude;
import com.amazon.alexa.fitness.api.afits.Coordinate;
import com.amazon.alexa.fitness.api.afits.DataSource;
import com.amazon.alexa.fitness.api.afits.FitnessSession;
import com.amazon.alexa.fitness.api.afits.Geolocation;
import com.amazon.alexa.fitness.api.afits.Heading;
import com.amazon.alexa.fitness.api.afits.LocationSample;
import com.amazon.alexa.fitness.api.afits.Speed;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.UserProfile;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.metrics.AFITSMetrics;
import com.amazon.alexa.fitness.model.biometric.ActivitySummary;
import com.amazon.alexa.fitness.orchestrator.FitnessSessionOrchestratorImplKt;
import com.amazon.alexa.fitness.repository.SessionSummaryCache;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.time.ISO8601;
import com.amazon.alexa.fitness.time.ISO8601Kt;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: WorkoutSessionUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B3\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\b\u0010\u0013\u001a\u00020\u0014H\u0002J)\u0010\u0015\u001a\u00020\u00142!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00140\u0017R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n \u0010*\u0004\u0018\u00010\u00120\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/alexa/fitness/service/afits/WorkoutSessionUploader;", "", "route", "", "Lcom/amazon/alexa/fitness/sdk/Sample$LocationSample;", "sensorProvider", "Lcom/amazon/alexa/fitness/accessory/FitnessAccessorySensorProvider;", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "activitySummary", "Lcom/amazon/alexa/fitness/model/biometric/ActivitySummary;", "sessionSummaryCache", "Lcom/amazon/alexa/fitness/repository/SessionSummaryCache;", "(Ljava/util/List;Lcom/amazon/alexa/fitness/accessory/FitnessAccessorySensorProvider;Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;Lcom/amazon/alexa/fitness/model/biometric/ActivitySummary;Lcom/amazon/alexa/fitness/repository/SessionSummaryCache;)V", "client", "Lcom/amazon/alexa/fitness/api/afits/AfitsClient;", "kotlin.jvm.PlatformType", "metrics", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "savePendingSessionUpload", "", "upload", "callback", "Lkotlin/Function1;", "Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "Lkotlin/ParameterName;", "name", "fitnessSession", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class WorkoutSessionUploader {
    private final ActivitySummary activitySummary;
    private final AfitsClient client;
    private final Mobilytics metrics;
    private final List<Sample.LocationSample> route;
    private final FitnessAccessorySensorProvider sensorProvider;
    private final Session session;
    private final SessionSummaryCache sessionSummaryCache;

    public WorkoutSessionUploader(@NotNull List<Sample.LocationSample> route, @NotNull FitnessAccessorySensorProvider sensorProvider, @NotNull Session session, @NotNull ActivitySummary activitySummary, @NotNull SessionSummaryCache sessionSummaryCache) {
        Intrinsics.checkParameterIsNotNull(route, "route");
        Intrinsics.checkParameterIsNotNull(sensorProvider, "sensorProvider");
        Intrinsics.checkParameterIsNotNull(session, "session");
        Intrinsics.checkParameterIsNotNull(activitySummary, "activitySummary");
        Intrinsics.checkParameterIsNotNull(sessionSummaryCache, "sessionSummaryCache");
        this.route = route;
        this.sensorProvider = sensorProvider;
        this.session = session;
        this.activitySummary = activitySummary;
        this.sessionSummaryCache = sessionSummaryCache;
        this.client = (AfitsClient) GeneratedOutlineSupport1.outline20(AfitsClient.class);
        this.metrics = (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void savePendingSessionUpload() {
        UserProfile userProfile = this.session.getUserProfile();
        if (userProfile != null) {
            this.sessionSummaryCache.savePendingSessionUpload(FitnessSessionOrchestratorImplKt.toSessionSummary(this.activitySummary, this.session, userProfile), null);
        }
    }

    public final void upload(@NotNull final Function1<? super FitnessSession, Unit> callback) {
        int collectionSizeOrDefault;
        String iso8601;
        String serializeDateTimeInLocalTimeZone$default;
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        String serializeDateTimeInLocalTimeZone$default2 = ISO8601.Companion.serializeDateTimeInLocalTimeZone$default(ISO8601.Companion, this.session.getCreatedAt(), null, false, 2, null);
        DateTime endTime = this.session.getEndTime();
        String str = (endTime == null || (serializeDateTimeInLocalTimeZone$default = ISO8601.Companion.serializeDateTimeInLocalTimeZone$default(ISO8601.Companion, endTime, null, false, 2, null)) == null) ? serializeDateTimeInLocalTimeZone$default2 : serializeDateTimeInLocalTimeZone$default;
        final String iso86012 = ISO8601Kt.toISO8601(this.session.getCreatedAt());
        DateTime endTime2 = this.session.getEndTime();
        final String str2 = (endTime2 == null || (iso8601 = ISO8601Kt.toISO8601(endTime2)) == null) ? iso86012 : iso8601;
        DataSource dataSource = this.session.getDataSource();
        List<Sample.LocationSample> list = this.route;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Sample.LocationSample locationSample : list) {
            arrayList.add(new LocationSample(ISO8601Kt.toISO8601(new DateTime(locationSample.getSampleData().getCollectedAt())), new Geolocation(new Coordinate(FitnessSessionOrchestratorImplKt.toLocationCoordinate(locationSample).getLatitude(), FitnessSessionOrchestratorImplKt.toLocationCoordinate(locationSample).getLongitude(), locationSample.getSampleData().getHorizontalAccuracy()), new Altitude(locationSample.getSampleData().getAltitude(), locationSample.getSampleData().getVerticalAccuracy()), AfitsTypesKt.orNull(new Heading(locationSample.getSampleData().getHeading(), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR)), new Speed(locationSample.getSampleData().getSpeed(), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR))));
            str2 = str2;
        }
        String uuid = this.session.getConfiguration().getSessionId().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "session.configuration.sessionId.toString()");
        final FitnessSession fitnessSession = new FitnessSession(uuid, serializeDateTimeInLocalTimeZone$default2, str, dataSource, WorkoutSessionUploaderKt.toAfitsActivitySummary(this.activitySummary), null, arrayList);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("uploading session with id ");
        outline107.append(this.session.getConfiguration().getSessionId());
        outline107.toString();
        Mobilytics metrics = this.metrics;
        Intrinsics.checkExpressionValueIsNotNull(metrics, "metrics");
        final MobilyticsMetricsTimer createTimer = MetricHelperKt.createTimer(metrics, AFITSMetrics.Upload.INSTANCE.getDuration());
        Mobilytics metrics2 = this.metrics;
        Intrinsics.checkExpressionValueIsNotNull(metrics2, "metrics");
        MetricHelperKt.recordCounter$default(metrics2, AFITSMetrics.Upload.INSTANCE.getStarted(), 0L, 2, null);
        this.client.createFitnessSession(fitnessSession, new CoralService.Callback<Response>() { // from class: com.amazon.alexa.fitness.service.afits.WorkoutSessionUploader$upload$1
            @Override // com.dee.app.http.CoralService.Callback
            public void onFailure(@Nullable CoralService.Call<Response> call, @Nullable CoralServiceException coralServiceException) {
                Mobilytics metrics3;
                if (coralServiceException != null) {
                    Log.e("AFX-SessionUploader", "failed to create fitness session on AFITS", coralServiceException);
                    metrics3 = WorkoutSessionUploader.this.metrics;
                    Intrinsics.checkExpressionValueIsNotNull(metrics3, "metrics");
                    MetricHelperKt.recordError(metrics3, AFITSMetrics.Upload.INSTANCE.getFailure(), coralServiceException);
                    WorkoutSessionUploader.this.savePendingSessionUpload();
                }
            }

            @Override // com.dee.app.http.CoralService.Callback
            public void onResult(@Nullable CoralService.Call<Response> call, @Nullable Response response) {
                Mobilytics metrics3;
                Mobilytics metrics4;
                Mobilytics mobilytics;
                if (response != null) {
                    int code = response.code();
                    if (200 <= code && 299 >= code) {
                        metrics4 = WorkoutSessionUploader.this.metrics;
                        Intrinsics.checkExpressionValueIsNotNull(metrics4, "metrics");
                        MetricHelperKt.recordCounter$default(metrics4, AFITSMetrics.Upload.INSTANCE.getSuccess(), 0L, 2, null);
                        mobilytics = WorkoutSessionUploader.this.metrics;
                        mobilytics.recordTimer(createTimer);
                        callback.mo12165invoke(new FitnessSession(fitnessSession.getId(), iso86012, str2, fitnessSession.getDataSource(), fitnessSession.getActivitySummary(), fitnessSession.getVersion(), fitnessSession.getRoute()));
                        return;
                    }
                    Log.e("AFX-SessionUploader", "Response for creating fitness session contains error " + response);
                    metrics3 = WorkoutSessionUploader.this.metrics;
                    Intrinsics.checkExpressionValueIsNotNull(metrics3, "metrics");
                    MetricHelperKt.recordError(metrics3, AFITSMetrics.Upload.INSTANCE.getFailure(), new Exception(String.valueOf(response.code())));
                    WorkoutSessionUploader.this.savePendingSessionUpload();
                }
            }
        });
    }
}
