package com.amazon.alexa.fitness.service.hds;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.fitness.api.fitnessSdk.ActivityType;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.configuration.HdsClientConfiguration;
import com.amazon.alexa.fitness.configuration.HdsClientConfigurationProvider;
import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.metrics.TimeshiftMetrics;
import com.amazon.alexa.fitness.metrics.UploadTimeshiftMetricComponent;
import com.amazon.alexa.fitness.model.biometric.ActivitySummary;
import com.amazon.alexa.fitness.model.biometric.FitnessSessionSummary;
import com.amazon.alexa.fitness.repository.SessionSummaryCache;
import com.amazon.alexa.fitness.service.hds.model.BatchQuantitySessionRecords;
import com.amazon.alexa.fitness.service.hds.model.DataSource;
import com.amazon.alexa.fitness.service.hds.model.DataSourceType;
import com.amazon.alexa.fitness.service.hds.model.GraphQLRequestBuilder;
import com.amazon.alexa.fitness.service.hds.model.GraphQLResponse;
import com.amazon.alexa.fitness.service.hds.model.QuantityRecordData;
import com.amazon.alexa.fitness.service.hds.model.QuantitySessionRecord;
import com.amazon.alexa.fitness.service.hds.model.Record;
import com.amazon.alexa.fitness.service.hds.model.RecordType;
import com.amazon.alexa.fitness.service.hds.model.RequestType;
import com.amazon.alexa.fitness.service.hds.model.Session;
import com.amazon.alexa.fitness.service.hds.model.SessionSummary;
import com.amazon.alexa.fitness.service.hds.model.SessionType;
import com.amazon.alexa.fitness.service.hds.model.Unit;
import com.amazon.alexa.fitness.time.ISO8601;
import com.amazon.alexa.fitness.util.ActivitySummaryUtils;
import com.amazon.alexa.fitness.util.ConversionUtils;
import com.amazon.alexa.fitness.util.ConversionUtilsKt;
import com.amazon.alexa.fitness.util.GsonUtils;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: HdsClientImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0007J\u001a\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u0018H\u0002J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016J\u0018\u0010#\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0007J\u0018\u0010$\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002R\u0014\u0010\u0011\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/HdsClientImpl;", "Lcom/amazon/alexa/fitness/service/hds/HdsClient;", "configurationProvider", "Lcom/amazon/alexa/fitness/configuration/HdsClientConfigurationProvider;", "hdsThreadHandler", "Lcom/amazon/alexa/fitness/service/hds/HdsThreadHandler;", "httpClient", "Lcom/amazon/alexa/fitness/service/hds/HttpClient;", "identityManager", "Lcom/amazon/alexa/fitness/identity/IdentityManager;", "metrics", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "sessionSummaryCache", "Lcom/amazon/alexa/fitness/repository/SessionSummaryCache;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/configuration/HdsClientConfigurationProvider;Lcom/amazon/alexa/fitness/service/hds/HdsThreadHandler;Lcom/amazon/alexa/fitness/service/hds/HttpClient;Lcom/amazon/alexa/fitness/identity/IdentityManager;Lcom/amazon/alexa/mobilytics/Mobilytics;Lcom/amazon/alexa/fitness/repository/SessionSummaryCache;Lcom/amazon/alexa/fitness/logs/ILog;)V", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/alexa/fitness/configuration/HdsClientConfiguration;", "getConfiguration", "()Lcom/amazon/alexa/fitness/configuration/HdsClientConfiguration;", "sendRequest", "Lcom/amazon/alexa/fitness/service/hds/model/GraphQLResponse;", "directedId", "", "queryOrMutationString", "sendRequestOnce", "requestString", "mapToken", "uploadSession", "", "sessionSummary", "Lcom/amazon/alexa/fitness/service/hds/model/SessionSummary;", "isRetry", "", "uploadSessionAndQuantityRecords", "uploadSessionAndQuantityRecordsWithoutMetrics", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class HdsClientImpl implements HdsClient {
    private final HdsClientConfigurationProvider configurationProvider;
    private final HdsThreadHandler hdsThreadHandler;
    private final HttpClient httpClient;
    private final IdentityManager identityManager;
    private final ILog log;
    private final Mobilytics metrics;
    private final SessionSummaryCache sessionSummaryCache;

    @Inject
    public HdsClientImpl(@NotNull HdsClientConfigurationProvider configurationProvider, @NotNull HdsThreadHandler hdsThreadHandler, @NotNull HttpClient httpClient, @NotNull IdentityManager identityManager, @NotNull Mobilytics metrics, @NotNull SessionSummaryCache sessionSummaryCache, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(configurationProvider, "configurationProvider");
        Intrinsics.checkParameterIsNotNull(hdsThreadHandler, "hdsThreadHandler");
        Intrinsics.checkParameterIsNotNull(httpClient, "httpClient");
        Intrinsics.checkParameterIsNotNull(identityManager, "identityManager");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(sessionSummaryCache, "sessionSummaryCache");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.configurationProvider = configurationProvider;
        this.hdsThreadHandler = hdsThreadHandler;
        this.httpClient = httpClient;
        this.identityManager = identityManager;
        this.metrics = metrics;
        this.sessionSummaryCache = sessionSummaryCache;
        this.log = log;
    }

    private final HdsClientConfiguration getConfiguration() {
        return this.configurationProvider.provideHdsClientConfiguration();
    }

    private final GraphQLResponse sendRequestOnce(String str, String str2) {
        boolean isBlank;
        GraphQLResponse graphQLResponse;
        ILog.DefaultImpls.debug$default(this.log, MetricsClass.HDS_CLIENT, GeneratedOutlineSupport1.outline72("Sending request to HDS: ", str), null, 4, null);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(FileUploadBase.CONTENT_TYPE, "application/json");
        linkedHashMap.put("x-api-key", getConfiguration().getTimeshiftKey());
        if (str2 != null) {
            String str3 = (String) linkedHashMap.put("Map", str2);
        }
        String postHttpRequest = this.httpClient.postHttpRequest(new HttpClientConfiguration(getConfiguration().getEndpoint(), getConfiguration().getConnectTimeout(), getConfiguration().getReadTimeout()), new HttpRequest(str, linkedHashMap));
        ILog.DefaultImpls.debug$default(this.log, MetricsClass.HDS_CLIENT, GeneratedOutlineSupport1.outline72("Received response from HDS: ", postHttpRequest), null, 4, null);
        if (postHttpRequest != null) {
            try {
                isBlank = StringsKt__StringsJVMKt.isBlank(postHttpRequest);
                if (!isBlank) {
                    Object fromJson = GsonUtils.Companion.getGson().fromJson(postHttpRequest, (Class<Object>) GraphQLResponse.class);
                    Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson(this, T::class.java)");
                    graphQLResponse = (GraphQLResponse) fromJson;
                } else {
                    throw new JsonParseException("Cannot parse blank JSON String.");
                }
            } catch (JsonParseException e) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline73("Failed to deserialize JSON String: '", postHttpRequest, Chars.QUOTE), e);
            }
        } else {
            graphQLResponse = null;
        }
        if (graphQLResponse != null) {
            if (graphQLResponse.isSuccessful()) {
                return graphQLResponse;
            }
            if (!graphQLResponse.isAuthSuccessful()) {
                ILog iLog = this.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received auth error: ");
                outline107.append(graphQLResponse.getMessage());
                ILog.DefaultImpls.warn$default(iLog, MetricsClass.HDS_CLIENT, outline107.toString(), null, 4, null);
                throw new HdsAuthException(graphQLResponse.getMessage());
            }
            throw new HdsServiceException(graphQLResponse.getErrors());
        }
        throw new HdsServiceException(null);
    }

    private final boolean uploadSessionAndQuantityRecordsWithoutMetrics(SessionSummary sessionSummary, boolean z) {
        Collection<ActivitySummary> arrayList;
        List listOfNotNull;
        EnumMap<ActivityType, ActivitySummary> activitySummaries;
        if (!z) {
            this.sessionSummaryCache.savePendingSessionUpload(sessionSummary, null);
        }
        ILog iLog = this.log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Preparing a session with session id ");
        outline107.append(sessionSummary.getSessionId());
        outline107.append(" to upload to HDS");
        ILog.DefaultImpls.debug$default(iLog, MetricsClass.HDS_CLIENT, outline107.toString(), null, 4, null);
        if (sessionSummary.getDeviceType() != null && !Intrinsics.areEqual(sessionSummary.getDeviceType(), "")) {
            DataSource dataSource = new DataSource(DataSourceType.ALEXA_FITNESS_HEADPHONES, sessionSummary.getDeviceType(), null, null);
            String uuid = sessionSummary.getSessionId().toString();
            Intrinsics.checkExpressionValueIsNotNull(uuid, "sessionSummary.sessionId.toString()");
            SessionType sessionType = SessionType.WORKOUT;
            String serializeDateTime = ISO8601.Companion.serializeDateTime(sessionSummary.getStartTime());
            ISO8601.Companion companion = ISO8601.Companion;
            DateTime endTime = sessionSummary.getEndTime();
            if (endTime == null) {
                Intrinsics.throwNpe();
            }
            try {
                Object convertToObject = sendRequest(sessionSummary.getUserIdentityDirectedId(), GraphQLRequestBuilder.Companion.buildCreateSessionMutation(new Session(uuid, sessionType, serializeDateTime, companion.serializeDateTime(endTime), dataSource, null))).convertToObject(RequestType.CREATE_SESSION, Session.class);
                if (convertToObject != null) {
                    Session session = (Session) convertToObject;
                    ILog iLog2 = this.log;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Created a session with session id ");
                    outline1072.append(session.getId());
                    outline1072.append(" and version ");
                    outline1072.append(session.getVersion());
                    outline1072.append(" in HDS");
                    ILog.DefaultImpls.debug$default(iLog2, MetricsClass.HDS_CLIENT, outline1072.toString(), null, 4, null);
                    ILog iLog3 = this.log;
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Preparing quantity records for session id ");
                    outline1073.append(sessionSummary.getSessionId());
                    outline1073.append(" to upload to HDS");
                    ILog.DefaultImpls.debug$default(iLog3, MetricsClass.HDS_CLIENT, outline1073.toString(), null, 4, null);
                    ActivitySummaryUtils.Companion companion2 = ActivitySummaryUtils.Companion;
                    FitnessSessionSummary fitnessSessionSummary = sessionSummary.getFitnessSessionSummary();
                    if (fitnessSessionSummary == null || (activitySummaries = fitnessSessionSummary.getActivitySummaries()) == null || (arrayList = activitySummaries.values()) == null) {
                        arrayList = new ArrayList<>();
                    }
                    ActivitySummary aggregateActivitySummaries = companion2.aggregateActivitySummaries(arrayList);
                    QuantitySessionRecord[] quantitySessionRecordArr = new QuantitySessionRecord[7];
                    quantitySessionRecordArr[0] = new QuantitySessionRecord(new Record(RecordType.DISTANCE), session, new QuantityRecordData((float) ConversionUtils.Companion.convertFeetToMeter(aggregateActivitySummaries.getDistanceInFeet()), Unit.DISTANCE_METERS), dataSource);
                    quantitySessionRecordArr[1] = new QuantitySessionRecord(new Record(RecordType.SPEED), session, new QuantityRecordData((float) ConversionUtils.Companion.convertMilesPerHourToMetersPerSecond(aggregateActivitySummaries.getSpeedInMph()), Unit.METER_SECOND), dataSource);
                    quantitySessionRecordArr[2] = new QuantitySessionRecord(new Record(RecordType.STEPS), session, new QuantityRecordData(aggregateActivitySummaries.getTotalSteps(), Unit.COUNT), dataSource);
                    quantitySessionRecordArr[3] = new QuantitySessionRecord(new Record(RecordType.PACE), session, new QuantityRecordData((float) ConversionUtils.Companion.convertMilesPerHourToMinutesPerKilometer(aggregateActivitySummaries.getSpeedInMph()), Unit.MINUTE_KILOMETER), dataSource);
                    quantitySessionRecordArr[4] = new QuantitySessionRecord(new Record(RecordType.CADENCE), session, new QuantityRecordData((float) aggregateActivitySummaries.getCadence(), Unit.COUNTS_MINUTE), dataSource);
                    quantitySessionRecordArr[5] = new QuantitySessionRecord(new Record(RecordType.WORKOUT_DURATION), session, new QuantityRecordData(ConversionUtilsKt.secondsToMilliseconds(aggregateActivitySummaries.getTimeInSeconds()), Unit.DURATION_MILLISECONDS), dataSource);
                    quantitySessionRecordArr[6] = sessionSummary.getShouldCaloriesBeReported() ? new QuantitySessionRecord(new Record(RecordType.CALORIES), session, new QuantityRecordData(aggregateActivitySummaries.getCalories(), Unit.ENERGY_KILOCALORIE), dataSource) : null;
                    listOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull((Object[]) quantitySessionRecordArr);
                    try {
                        sendRequest(sessionSummary.getUserIdentityDirectedId(), GraphQLRequestBuilder.Companion.buildBatchPutQuantitySessionRecordsMutation(new BatchQuantitySessionRecords(dataSource, listOfNotNull)));
                        ILog iLog4 = this.log;
                        ILog.DefaultImpls.debug$default(iLog4, MetricsClass.HDS_CLIENT, "Created quantity records for session id " + sessionSummary.getSessionId() + " in HDS", null, 4, null);
                        SessionSummaryCache sessionSummaryCache = this.sessionSummaryCache;
                        String uuid2 = sessionSummary.getSessionId().toString();
                        Intrinsics.checkExpressionValueIsNotNull(uuid2, "sessionSummary.sessionId.toString()");
                        sessionSummaryCache.deletePendingSessionUpload(uuid2);
                        return true;
                    } catch (Throwable th) {
                        this.sessionSummaryCache.savePendingSessionUpload(sessionSummary, th.getClass().getSimpleName());
                        this.log.error(MetricsClass.HDS_CLIENT, "error uploading session summary", th);
                        return false;
                    }
                }
                throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.service.hds.model.Session");
            } catch (Throwable th2) {
                this.sessionSummaryCache.savePendingSessionUpload(sessionSummary, th2.getClass().getSimpleName());
                this.log.error(MetricsClass.HDS_CLIENT, "error uploading session summary", th2);
                return false;
            }
        }
        ILog.DefaultImpls.error$default(this.log, MetricsClass.HDS_CLIENT, "error uploading session summary. session summary did not include a deviceType", null, 4, null);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0091 A[LOOP:0: B:16:0x002c->B:13:0x0091, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0093 A[SYNTHETIC] */
    @androidx.annotation.VisibleForTesting(otherwise = 2)
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.amazon.alexa.fitness.service.hds.model.GraphQLResponse sendRequest(@org.jetbrains.annotations.NotNull java.lang.String r20, @org.jetbrains.annotations.NotNull java.lang.String r21) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            r0 = r21
            java.lang.String r3 = "directedId"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r3)
            java.lang.String r3 = "queryOrMutationString"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r3)
            com.google.gson.JsonObject r3 = new com.google.gson.JsonObject
            r3.<init>()
            java.lang.String r4 = "query"
            r3.addProperty(r4, r0)
            java.lang.String r3 = r3.toString()
            java.lang.String r0 = "requestJson.toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r0)
            com.amazon.alexa.fitness.identity.IdentityManager r0 = r1.identityManager
            java.lang.String r0 = r0.getMapTokenByDirectedId(r2)
            r4 = 0
            r5 = r4
            r4 = r0
        L2c:
            com.amazon.alexa.fitness.service.hds.model.GraphQLResponse r0 = r1.sendRequestOnce(r3, r4)     // Catch: java.lang.Throwable -> L31 com.amazon.alexa.fitness.service.hds.HdsServiceException -> L34 com.amazon.alexa.fitness.service.hds.HdsAuthException -> L52
            return r0
        L31:
            r0 = move-exception
            r6 = r0
            goto L85
        L34:
            r0 = move-exception
            r6 = r0
            com.amazon.alexa.fitness.logs.ILog r7 = r1.log
            java.lang.String r0 = "Caught HdsServiceException: "
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            java.util.List r8 = r6.getErrors()
            r0.append(r8)
            java.lang.String r9 = r0.toString()
            r10 = 0
            r11 = 4
            r12 = 0
            java.lang.String r8 = "HdsClient"
            com.amazon.alexa.fitness.logs.ILog.DefaultImpls.warn$default(r7, r8, r9, r10, r11, r12)
            goto L85
        L52:
            r0 = move-exception
            r6 = r0
            com.amazon.alexa.fitness.logs.ILog r7 = r1.log
            java.lang.String r0 = "Received auth error: "
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            java.lang.String r4 = r6.getErrorMessage()
            r0.append(r4)
            java.lang.String r9 = r0.toString()
            r10 = 0
            r11 = 4
            r12 = 0
            java.lang.String r8 = "HdsClient"
            com.amazon.alexa.fitness.logs.ILog.DefaultImpls.warn$default(r7, r8, r9, r10, r11, r12)
            com.amazon.alexa.fitness.logs.ILog r13 = r1.log
            r16 = 0
            r17 = 4
            r18 = 0
            java.lang.String r14 = "HdsClient"
            java.lang.String r15 = "Refreshing MAP token."
            com.amazon.alexa.fitness.logs.ILog.DefaultImpls.debug$default(r13, r14, r15, r16, r17, r18)
            com.amazon.alexa.fitness.identity.IdentityManager r0 = r1.identityManager
            java.lang.String r0 = r0.getMapTokenByDirectedId(r2)
            r4 = r0
        L85:
            int r0 = r5 + 1
            com.amazon.alexa.fitness.configuration.HdsClientConfiguration r7 = r19.getConfiguration()
            int r7 = r7.getMaxRetries()
            if (r5 >= r7) goto L93
            r5 = r0
            goto L2c
        L93:
            com.amazon.alexa.fitness.logs.ILog r8 = r1.log
            java.lang.String r0 = "Unsuccessful response after "
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            com.amazon.alexa.fitness.configuration.HdsClientConfiguration r2 = r19.getConfiguration()
            int r2 = r2.getMaxRetries()
            r0.append(r2)
            java.lang.String r2 = " attempts"
            r0.append(r2)
            java.lang.String r10 = r0.toString()
            r11 = 0
            r12 = 4
            r13 = 0
            java.lang.String r9 = "HdsClient"
            com.amazon.alexa.fitness.logs.ILog.DefaultImpls.error$default(r8, r9, r10, r11, r12, r13)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.fitness.service.hds.HdsClientImpl.sendRequest(java.lang.String, java.lang.String):com.amazon.alexa.fitness.service.hds.model.GraphQLResponse");
    }

    @Override // com.amazon.alexa.fitness.service.hds.HdsClient
    public void uploadSession(@NotNull SessionSummary sessionSummary, boolean z) {
        Intrinsics.checkParameterIsNotNull(sessionSummary, "sessionSummary");
        MetricHelperKt.recordCounter$default(this.metrics, TimeshiftMetrics.Upload.INSTANCE.getStarted(), 0L, 2, null);
        this.hdsThreadHandler.postUpload(new HdsClientImpl$uploadSession$1(this, sessionSummary, z));
    }

    @VisibleForTesting(otherwise = 2)
    public final void uploadSessionAndQuantityRecords(@NotNull SessionSummary sessionSummary, boolean z) {
        Intrinsics.checkParameterIsNotNull(sessionSummary, "sessionSummary");
        MobilyticsMetricsTimer createTimer = MetricHelperKt.createTimer(this.metrics, TimeshiftMetrics.Upload.INSTANCE.getUploadSessionSuccessDuration());
        MobilyticsMetricsTimer createTimer2 = MetricHelperKt.createTimer(this.metrics, TimeshiftMetrics.Upload.INSTANCE.getUploadSessionFailureDuration());
        if (uploadSessionAndQuantityRecordsWithoutMetrics(sessionSummary, z)) {
            MetricHelperKt.recordCounter$default(this.metrics, TimeshiftMetrics.Upload.INSTANCE.getSuccess(), 0L, 2, null);
            this.metrics.recordTimer(createTimer);
            Mobilytics mobilytics = this.metrics;
            UploadTimeshiftMetricComponent sessionEndUntilUploadDuration = TimeshiftMetrics.Upload.INSTANCE.getSessionEndUntilUploadDuration();
            long epochMilli = DateTime.Companion.now().getEpochMilli();
            DateTime endTime = sessionSummary.getEndTime();
            MetricHelperKt.recordTimer(mobilytics, sessionEndUntilUploadDuration, epochMilli - (endTime != null ? endTime.getEpochMilli() : 0L));
            createTimer2.finishTimer();
            return;
        }
        MetricHelperKt.recordCounter$default(this.metrics, TimeshiftMetrics.Upload.INSTANCE.getFailure(), 0L, 2, null);
        this.metrics.recordTimer(createTimer2);
        createTimer.finishTimer();
    }
}
