package com.amazon.tarazed.arcus;

import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.webrtc.VideoParameters;
import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.DateTimeConstants;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: ArcusHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \"2\u00020\u0001:\u0001\"B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\r\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\rJ\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0011J\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0017J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u0011J\u0006\u0010\u0019\u001a\u00020\u0016J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000fJ\u0006\u0010 \u001a\u00020\u001eJ\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/amazon/tarazed/arcus/ArcusHelper;", "", "workManager", "Landroidx/work/WorkManager;", "remoteConfigurationManager", "Lcom/amazonaws/mobileconnectors/remoteconfiguration/RemoteConfigurationManager;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedLogger;", "metrics", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Landroidx/work/WorkManager;Lcom/amazonaws/mobileconnectors/remoteconfiguration/RemoteConfigurationManager;Lcom/amazon/tarazed/core/logging/TarazedLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "getArcusConfig", "Lorg/json/JSONObject;", "getArcusConfig$TarazedAndroidLibrary_release", "getBoolean", "", "key", "", "(Ljava/lang/String;)Ljava/lang/Boolean;", "getHelpContentUrlForMarketplace", WebConstants.WebviewConstants.MARKETPLACE_ID, "getInt", "", "(Ljava/lang/String;)Ljava/lang/Integer;", "getNotificationHostnameForMarketplace", "getPrimerTimeOutSec", "getString", "getVideoParameters", "Lcom/amazon/tarazed/core/webrtc/VideoParameters;", "scheduleArcusSync", "", "replaceExistingJob", "scheduleImmediateArcusSync", "schedulePeriodicArcusSync", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ArcusHelper {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String JOB_NAME_IMMEDIATE = "ImmediateArcusSyncJob";
    @NotNull
    public static final String JOB_NAME_PERIODIC = "ArcusSyncJob";
    public static final int MAX_WAIT_SEC = 10;
    @NotNull
    public static final String METRIC_INITIAL_ARCUS_SYNC_SCHEDULED = "InitialArcusSyncScheduled";
    @NotNull
    public static final String METRIC_RETRIEVE_SYNC_REQUESTS_TIMEOUT = "RetrieveArcusSyncRequestsTimeout";
    @NotNull
    public static final String METRIC_RETRIEVE_SYNC_REQUESTS_UNKNOWN_EXCEPTION = "RetrieveArcusSyncRequestsUnknownException";
    private static final String TAG = "ArcusHelper";
    private final TarazedLogger logger;
    private final TarazedMetricsHelper metrics;
    private final RemoteConfigurationManager remoteConfigurationManager;
    private final WorkManager workManager;

    /* compiled from: ArcusHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/tarazed/arcus/ArcusHelper$Companion;", "", "()V", "JOB_NAME_IMMEDIATE", "", "JOB_NAME_PERIODIC", "MAX_WAIT_SEC", "", "METRIC_INITIAL_ARCUS_SYNC_SCHEDULED", "METRIC_RETRIEVE_SYNC_REQUESTS_TIMEOUT", "METRIC_RETRIEVE_SYNC_REQUESTS_UNKNOWN_EXCEPTION", "TAG", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ArcusHelper(@NotNull WorkManager workManager, @NotNull RemoteConfigurationManager remoteConfigurationManager, @NotNull TarazedLogger logger, @NotNull TarazedMetricsHelper metrics) {
        Intrinsics.checkParameterIsNotNull(workManager, "workManager");
        Intrinsics.checkParameterIsNotNull(remoteConfigurationManager, "remoteConfigurationManager");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.workManager = workManager;
        this.remoteConfigurationManager = remoteConfigurationManager;
        this.logger = logger;
        this.metrics = metrics;
    }

    @NotNull
    public final JSONObject getArcusConfig$TarazedAndroidLibrary_release() {
        Configuration openConfiguration = this.remoteConfigurationManager.openConfiguration();
        Intrinsics.checkExpressionValueIsNotNull(openConfiguration, "remoteConfigurationManager.openConfiguration()");
        JSONObject asJsonObject = openConfiguration.getAsJsonObject();
        Intrinsics.checkExpressionValueIsNotNull(asJsonObject, "remoteConfigurationManag…figuration().asJsonObject");
        return asJsonObject;
    }

    @Nullable
    public final Boolean getBoolean(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        try {
            boolean z = getArcusConfig$TarazedAndroidLibrary_release().getBoolean(key);
            TarazedLogger tarazedLogger = this.logger;
            tarazedLogger.i(TAG, "getBoolean received key: " + key + " and is returning boolean result: " + z);
            return Boolean.valueOf(z);
        } catch (JSONException e) {
            this.logger.e(TAG, "Exception checking locally cached Arcus configuration for boolean", e);
            return null;
        }
    }

    @NotNull
    public final String getHelpContentUrlForMarketplace(@Nullable String str) {
        if (str == null) {
            return "https://www.amazon.com/gp/help/customer/display.html?ie=UTF8&nodeId=GPXURFBMZKPVCQET";
        }
        try {
            String string = getArcusConfig$TarazedAndroidLibrary_release().getJSONObject("helpContentUrl").getString(str);
            TarazedLogger tarazedLogger = this.logger;
            tarazedLogger.i(TAG, "getHelpContentUrlForMarketplace received key: " + str + " and is returning result: " + string);
            return string != null ? string : "https://www.amazon.com/gp/help/customer/display.html?ie=UTF8&nodeId=GPXURFBMZKPVCQET";
        } catch (JSONException e) {
            this.logger.e(TAG, "Exception checking locally cached Arcus configuration for MSS help content URL.", e);
            return "https://www.amazon.com/gp/help/customer/display.html?ie=UTF8&nodeId=GPXURFBMZKPVCQET";
        }
    }

    @Nullable
    public final Integer getInt(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        try {
            int i = getArcusConfig$TarazedAndroidLibrary_release().getInt(key);
            TarazedLogger tarazedLogger = this.logger;
            tarazedLogger.i(TAG, "getInt received key: " + key + " and is returning int result: " + i);
            return Integer.valueOf(i);
        } catch (JSONException e) {
            this.logger.e(TAG, "Exception checking locally cached Arcus configuration for int", e);
            return null;
        }
    }

    @Nullable
    public final String getNotificationHostnameForMarketplace(@NotNull String marketplaceId) {
        Intrinsics.checkParameterIsNotNull(marketplaceId, "marketplaceId");
        try {
            String string = getArcusConfig$TarazedAndroidLibrary_release().getJSONObject("notificationHostnameByMarketplace").getString(marketplaceId);
            TarazedLogger tarazedLogger = this.logger;
            tarazedLogger.i(TAG, "getHostnameByMarketplace received key: " + marketplaceId + " and is returning result: " + string);
            return string;
        } catch (JSONException e) {
            this.logger.e(TAG, "Exception checking locally cached Arcus configuration for notification hostname.", e);
            return null;
        }
    }

    public final int getPrimerTimeOutSec() {
        Integer num = getInt("primerTimeOutSec");
        if (num != null) {
            return num.intValue();
        }
        return 90;
    }

    @Nullable
    public final String getString(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        try {
            String string = getArcusConfig$TarazedAndroidLibrary_release().getString(key);
            TarazedLogger tarazedLogger = this.logger;
            tarazedLogger.i(TAG, "getString received key: " + key + " and is returning string result: " + string);
            return string;
        } catch (JSONException e) {
            this.logger.e(TAG, "Exception checking locally cached Arcus configuration for string", e);
            return null;
        }
    }

    @NotNull
    public final VideoParameters getVideoParameters() {
        Boolean bool = getBoolean("isHardwareVideoEncodingEnabled");
        boolean booleanValue = bool != null ? bool.booleanValue() : false;
        Integer num = getInt("maxVideoDimension");
        int intValue = num != null ? num.intValue() : 960;
        Integer num2 = getInt("minVideoDimension");
        int intValue2 = num2 != null ? num2.intValue() : 720;
        Integer num3 = getInt("maxVideoBitrateKbps");
        int intValue3 = num3 != null ? num3.intValue() : 1200;
        Integer num4 = getInt("videoFramerate");
        return new VideoParameters(booleanValue, intValue, intValue2, intValue3, num4 != null ? num4.intValue() : 60);
    }

    public final void scheduleArcusSync(boolean z) {
        ExistingWorkPolicy existingWorkPolicy;
        if (!z) {
            try {
                List<WorkInfo> periodicWorkInfo = this.workManager.getWorkInfosForUniqueWork(JOB_NAME_PERIODIC).get(10, TimeUnit.SECONDS);
                Intrinsics.checkExpressionValueIsNotNull(periodicWorkInfo, "periodicWorkInfo");
                if (!periodicWorkInfo.isEmpty()) {
                    this.logger.i(TAG, "Periodic arcus sync has already been scheduled, nothing to do.");
                    return;
                }
            } catch (Exception e) {
                if ((e instanceof ExecutionException) || (e instanceof InterruptedException) || (e instanceof TimeoutException)) {
                    this.metrics.addCount(TAG, METRIC_RETRIEVE_SYNC_REQUESTS_TIMEOUT, 1.0d);
                    TarazedLogger tarazedLogger = this.logger;
                    tarazedLogger.w(TAG, "Exception trying to query periodic Arcus sync job, scheduling new request. " + e);
                } else {
                    this.metrics.addCount(TAG, METRIC_RETRIEVE_SYNC_REQUESTS_UNKNOWN_EXCEPTION, 1.0d);
                    throw e;
                }
            }
        }
        if (z) {
            this.workManager.cancelUniqueWork(JOB_NAME_PERIODIC);
        }
        Integer num = getInt("arcusSyncIntervalSec");
        int intValue = num != null ? num.intValue() : DateTimeConstants.SECONDS_PER_DAY;
        if (!z) {
            intValue = ThreadLocalRandom.current().nextInt(0, intValue + 1);
        }
        Constraints build = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Constraints.Builder()\n  …TED)\n            .build()");
        OneTimeWorkRequest build2 = new OneTimeWorkRequest.Builder(ArcusPeriodicSchedulerWorker.class).setInitialDelay(intValue, TimeUnit.SECONDS).setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 10000L, TimeUnit.MILLISECONDS).setConstraints(build).build();
        Intrinsics.checkExpressionValueIsNotNull(build2, "OneTimeWorkRequest.Build…nts)\n            .build()");
        OneTimeWorkRequest oneTimeWorkRequest = build2;
        if (z) {
            existingWorkPolicy = ExistingWorkPolicy.REPLACE;
        } else {
            existingWorkPolicy = ExistingWorkPolicy.KEEP;
        }
        this.workManager.enqueueUniqueWork(JOB_NAME_IMMEDIATE, existingWorkPolicy, oneTimeWorkRequest);
        this.metrics.addCount(TAG, METRIC_INITIAL_ARCUS_SYNC_SCHEDULED, 1.0d);
        TarazedLogger tarazedLogger2 = this.logger;
        tarazedLogger2.i(TAG, "Arcus sync scheduled to be run in " + intValue + " seconds unless one has already been enqueued, will replace existing: " + z);
    }

    public final void scheduleImmediateArcusSync() {
        Constraints build = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Constraints.Builder()\n  …\n                .build()");
        OneTimeWorkRequest build2 = new OneTimeWorkRequest.Builder(ArcusSyncWorker.class).setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 10000L, TimeUnit.MILLISECONDS).setConstraints(build).build();
        Intrinsics.checkExpressionValueIsNotNull(build2, "OneTimeWorkRequest.Build…\n                .build()");
        this.workManager.enqueueUniqueWork(JOB_NAME_IMMEDIATE, ExistingWorkPolicy.REPLACE, build2);
        this.logger.i(TAG, "Scheduled an immediate one-time Arcus sync");
    }

    public final void schedulePeriodicArcusSync(boolean z) {
        ExistingPeriodicWorkPolicy existingPeriodicWorkPolicy;
        Constraints build = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Constraints.Builder()\n  …TED)\n            .build()");
        Integer num = getInt("arcusSyncIntervalSec");
        PeriodicWorkRequest build2 = new PeriodicWorkRequest.Builder(ArcusSyncWorker.class, num != null ? num.intValue() : DateTimeConstants.SECONDS_PER_DAY, TimeUnit.SECONDS).setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 10000L, TimeUnit.MILLISECONDS).setConstraints(build).build();
        Intrinsics.checkExpressionValueIsNotNull(build2, "PeriodicWorkRequest.Buil…nts)\n            .build()");
        PeriodicWorkRequest periodicWorkRequest = build2;
        if (z) {
            existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.REPLACE;
        } else {
            existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP;
        }
        this.workManager.enqueueUniquePeriodicWork(JOB_NAME_PERIODIC, existingPeriodicWorkPolicy, periodicWorkRequest);
        TarazedLogger tarazedLogger = this.logger;
        tarazedLogger.i(TAG, "Scheduled a periodic Arcus sync, replaceExistingJob: " + z);
    }
}
