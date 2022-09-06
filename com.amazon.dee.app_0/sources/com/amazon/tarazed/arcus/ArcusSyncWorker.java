package com.amazon.tarazed.arcus;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notification.client.model.NotificationPlatform;
import com.amazon.tarazed.core.registry.TarazedComponentRegistry;
import com.amazon.tarazed.core.registry.component.AccountMetadataProvider;
import com.amazon.tarazed.core.type.Account;
import com.amazon.tarazed.dagger.injectors.LibraryInjector;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ArcusSyncWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0000\u0018\u0000 ,2\u00020\u0001:\u0002+,B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010%\u001a\u00020&H\u0016J\u0013\u0010'\u001a\u00020(H\u0081@ø\u0001\u0000¢\u0006\u0004\b)\u0010*R\u001e\u0010\u0007\u001a\u00020\b8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\u000e8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u00148\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\u001a8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u001f\u001a\u00020 8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, d2 = {"Lcom/amazon/tarazed/arcus/ArcusSyncWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "arcusHelper", "Lcom/amazon/tarazed/arcus/ArcusHelper;", "getArcusHelper$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/arcus/ArcusHelper;", "setArcusHelper$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/arcus/ArcusHelper;)V", "deviceInfoUtility", "Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid;", "getDeviceInfoUtility$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid;", "setDeviceInfoUtility$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid;)V", "logger", "Lcom/amazon/tarazed/core/logging/TarazedLogger;", "getLogger$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/logging/TarazedLogger;", "setLogger$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/core/logging/TarazedLogger;)V", "metrics", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "getMetrics$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "setMetrics$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "remoteConfigurationManager", "Lcom/amazonaws/mobileconnectors/remoteconfiguration/RemoteConfigurationManager;", "getRemoteConfigurationManager$TarazedAndroidLibrary_release", "()Lcom/amazonaws/mobileconnectors/remoteconfiguration/RemoteConfigurationManager;", "setRemoteConfigurationManager$TarazedAndroidLibrary_release", "(Lcom/amazonaws/mobileconnectors/remoteconfiguration/RemoteConfigurationManager;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "syncConfiguration", "", "syncConfiguration$TarazedAndroidLibrary_release", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ArcusCallback", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ArcusSyncWorker extends Worker {
    @NotNull
    public static final String ATTRIBUTE_DEVICE_TYPE = "deviceType";
    @NotNull
    public static final String ATTRIBUTE_IS_FIREOS = "isFireOs";
    @Deprecated
    public static final Companion Companion = new Companion(null);
    public static final int MAX_SYNC_ATTEMPTS = 10;
    public static final long MAX_SYNC_DURATION_MS = 10000;
    @NotNull
    public static final String METRIC_ARCUS_SYNC_ATTEMPTED = "ArcusSyncAttempted";
    @NotNull
    public static final String METRIC_ARCUS_SYNC_FAILED = "ArcusSyncFailed";
    @NotNull
    public static final String METRIC_ARCUS_SYNC_SUCCESSFUL = "ArcusSyncSuccessful";
    @NotNull
    public static final String METRIC_ARCUS_SYNC_THROTTLED = "ArcusSyncThrottled";
    @NotNull
    public static final String METRIC_ARCUS_SYNC_UNKNOWN_EXCEPTION = "ArcusSyncUnknownException";
    @NotNull
    public static final String METRIC_MAX_ATTEMPTS_EXCEEDED = "ArcusSyncMaxAttemptsExceeded";
    private static final String TAG = "ArcusSyncWorker";
    @Inject
    @NotNull
    public ArcusHelper arcusHelper;
    @Inject
    @NotNull
    public DeviceInfoUtilityAndroid deviceInfoUtility;
    @Inject
    @NotNull
    public TarazedLogger logger;
    @Inject
    @NotNull
    public TarazedMetricsHelper metrics;
    @Inject
    @NotNull
    public RemoteConfigurationManager remoteConfigurationManager;

    /* compiled from: ArcusSyncWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0014\u0010\u000b\u001a\u00020\u00072\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/tarazed/arcus/ArcusSyncWorker$ArcusCallback;", "Lcom/amazonaws/mobileconnectors/remoteconfiguration/ConfigurationSyncCallback;", "continuation", "Lkotlin/coroutines/Continuation;", "", "(Lcom/amazon/tarazed/arcus/ArcusSyncWorker;Lkotlin/coroutines/Continuation;)V", "onConfigurationModified", "", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazonaws/mobileconnectors/remoteconfiguration/Configuration;", "onConfigurationUnmodified", "onFailure", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onThrottle", "l", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private final class ArcusCallback implements ConfigurationSyncCallback {
        private final Continuation<Integer> continuation;
        final /* synthetic */ ArcusSyncWorker this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public ArcusCallback(@NotNull ArcusSyncWorker arcusSyncWorker, Continuation<? super Integer> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "continuation");
            this.this$0 = arcusSyncWorker;
            this.continuation = continuation;
        }

        @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
        public void onConfigurationModified(@NotNull Configuration configuration) {
            Intrinsics.checkParameterIsNotNull(configuration, "configuration");
            TarazedLogger logger$TarazedAndroidLibrary_release = this.this$0.getLogger$TarazedAndroidLibrary_release();
            Companion unused = ArcusSyncWorker.Companion;
            logger$TarazedAndroidLibrary_release.d(ArcusSyncWorker.TAG, "Arcus sync onConfigurationModified called.");
            TarazedLogger logger$TarazedAndroidLibrary_release2 = this.this$0.getLogger$TarazedAndroidLibrary_release();
            Companion unused2 = ArcusSyncWorker.Companion;
            logger$TarazedAndroidLibrary_release2.d(ArcusSyncWorker.TAG, "New Arcus configuration: " + configuration.getAsJsonString());
            TarazedMetricsHelper metrics$TarazedAndroidLibrary_release = this.this$0.getMetrics$TarazedAndroidLibrary_release();
            Companion unused3 = ArcusSyncWorker.Companion;
            Companion unused4 = ArcusSyncWorker.Companion;
            metrics$TarazedAndroidLibrary_release.addCount(ArcusSyncWorker.TAG, ArcusSyncWorker.METRIC_ARCUS_SYNC_SUCCESSFUL, 1.0d);
            if (this.this$0.getDeviceInfoUtility$TarazedAndroidLibrary_release().is1PDevice()) {
                TarazedLogger logger$TarazedAndroidLibrary_release3 = this.this$0.getLogger$TarazedAndroidLibrary_release();
                Companion unused5 = ArcusSyncWorker.Companion;
                logger$TarazedAndroidLibrary_release3.d(ArcusSyncWorker.TAG, "Next Arcus sync scheduled for 1P.");
                this.this$0.getArcusHelper$TarazedAndroidLibrary_release().scheduleArcusSync(true);
            }
            Continuation<Integer> continuation = this.continuation;
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m10378constructorimpl(0));
        }

        @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
        public void onConfigurationUnmodified(@NotNull Configuration configuration) {
            Intrinsics.checkParameterIsNotNull(configuration, "configuration");
            TarazedLogger logger$TarazedAndroidLibrary_release = this.this$0.getLogger$TarazedAndroidLibrary_release();
            Companion unused = ArcusSyncWorker.Companion;
            logger$TarazedAndroidLibrary_release.d(ArcusSyncWorker.TAG, "Arcus sync onConfigurationUnmodified called.");
            TarazedMetricsHelper metrics$TarazedAndroidLibrary_release = this.this$0.getMetrics$TarazedAndroidLibrary_release();
            Companion unused2 = ArcusSyncWorker.Companion;
            Companion unused3 = ArcusSyncWorker.Companion;
            metrics$TarazedAndroidLibrary_release.addCount(ArcusSyncWorker.TAG, ArcusSyncWorker.METRIC_ARCUS_SYNC_SUCCESSFUL, 1.0d);
            Continuation<Integer> continuation = this.continuation;
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m10378constructorimpl(0));
        }

        @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
        public void onFailure(@NotNull Exception e) {
            Intrinsics.checkParameterIsNotNull(e, "e");
            TarazedLogger logger$TarazedAndroidLibrary_release = this.this$0.getLogger$TarazedAndroidLibrary_release();
            Companion unused = ArcusSyncWorker.Companion;
            logger$TarazedAndroidLibrary_release.e(ArcusSyncWorker.TAG, "Arcus sync on failure called with exception.", e);
            TarazedMetricsHelper metrics$TarazedAndroidLibrary_release = this.this$0.getMetrics$TarazedAndroidLibrary_release();
            Companion unused2 = ArcusSyncWorker.Companion;
            Companion unused3 = ArcusSyncWorker.Companion;
            metrics$TarazedAndroidLibrary_release.addCount(ArcusSyncWorker.TAG, ArcusSyncWorker.METRIC_ARCUS_SYNC_FAILED, 1.0d);
            Continuation<Integer> continuation = this.continuation;
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m10378constructorimpl(1));
        }

        @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
        public void onThrottle(long j) {
            TarazedLogger logger$TarazedAndroidLibrary_release = this.this$0.getLogger$TarazedAndroidLibrary_release();
            Companion unused = ArcusSyncWorker.Companion;
            logger$TarazedAndroidLibrary_release.w(ArcusSyncWorker.TAG, "Arcus sync onThrottle called.");
            TarazedMetricsHelper metrics$TarazedAndroidLibrary_release = this.this$0.getMetrics$TarazedAndroidLibrary_release();
            Companion unused2 = ArcusSyncWorker.Companion;
            Companion unused3 = ArcusSyncWorker.Companion;
            metrics$TarazedAndroidLibrary_release.addCount(ArcusSyncWorker.TAG, ArcusSyncWorker.METRIC_ARCUS_SYNC_THROTTLED, 1.0d);
            Continuation<Integer> continuation = this.continuation;
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m10378constructorimpl(1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ArcusSyncWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\b\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/tarazed/arcus/ArcusSyncWorker$Companion;", "", "()V", "ATTRIBUTE_DEVICE_TYPE", "", "ATTRIBUTE_IS_FIREOS", "MAX_SYNC_ATTEMPTS", "", "MAX_SYNC_DURATION_MS", "", "METRIC_ARCUS_SYNC_ATTEMPTED", "METRIC_ARCUS_SYNC_FAILED", "METRIC_ARCUS_SYNC_SUCCESSFUL", "METRIC_ARCUS_SYNC_THROTTLED", "METRIC_ARCUS_SYNC_UNKNOWN_EXCEPTION", "METRIC_MAX_ATTEMPTS_EXCEEDED", "TAG", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArcusSyncWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(workerParams, "workerParams");
        LibraryInjector.getComponent().inject(this);
    }

    @Override // androidx.work.Worker
    @NotNull
    public ListenableWorker.Result doWork() {
        Object runBlocking$default;
        Account accountMetadata;
        TarazedLogger tarazedLogger = this.logger;
        if (tarazedLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        tarazedLogger.i(TAG, "Executing Arcus sync.");
        if (getRunAttemptCount() > 10) {
            TarazedMetricsHelper tarazedMetricsHelper = this.metrics;
            if (tarazedMetricsHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            tarazedMetricsHelper.addCountHighPriority(TAG, METRIC_MAX_ATTEMPTS_EXCEEDED, 1.0d);
            TarazedLogger tarazedLogger2 = this.logger;
            if (tarazedLogger2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            tarazedLogger2.w(TAG, "Arcus sync job has exceeded max attempts, dropping the request.");
            ListenableWorker.Result failure = ListenableWorker.Result.failure();
            Intrinsics.checkExpressionValueIsNotNull(failure, "Result.failure()");
            return failure;
        }
        try {
            RemoteConfigurationManager remoteConfigurationManager = this.remoteConfigurationManager;
            if (remoteConfigurationManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("remoteConfigurationManager");
            }
            Attributes openAttributes = remoteConfigurationManager.openAttributes();
            DeviceInfoUtilityAndroid deviceInfoUtilityAndroid = this.deviceInfoUtility;
            if (deviceInfoUtilityAndroid == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceInfoUtility");
            }
            if (deviceInfoUtilityAndroid.getNotificationPlatform() == NotificationPlatform.ADM) {
                openAttributes.addAttribute(ATTRIBUTE_IS_FIREOS, (Boolean) true);
            }
            AccountMetadataProvider accountMetadataProvider = (AccountMetadataProvider) TarazedComponentRegistry.INSTANCE.getComponent(AccountMetadataProvider.class);
            String deviceType = (accountMetadataProvider == null || (accountMetadata = accountMetadataProvider.getAccountMetadata()) == null) ? null : accountMetadata.getDeviceType();
            if (deviceType != null) {
                openAttributes.addAttribute("deviceType", deviceType);
                TarazedLogger tarazedLogger3 = this.logger;
                if (tarazedLogger3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("logger");
                }
                tarazedLogger3.d(TAG, "Syncing with Arcus.");
                runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new ArcusSyncWorker$doWork$1(this, null), 1, null);
                int intValue = ((Number) runBlocking$default).intValue();
                if (intValue == 0) {
                    TarazedLogger tarazedLogger4 = this.logger;
                    if (tarazedLogger4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("logger");
                    }
                    tarazedLogger4.i(TAG, "Arcus sync - succeeded.");
                    ListenableWorker.Result success = ListenableWorker.Result.success();
                    Intrinsics.checkExpressionValueIsNotNull(success, "Result.success()");
                    return success;
                } else if (intValue != 1) {
                    TarazedLogger tarazedLogger5 = this.logger;
                    if (tarazedLogger5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("logger");
                    }
                    tarazedLogger5.w(TAG, "Arcus sync - unhandled result code, dropping request.");
                    ListenableWorker.Result failure2 = ListenableWorker.Result.failure();
                    Intrinsics.checkExpressionValueIsNotNull(failure2, "Result.failure()");
                    return failure2;
                } else {
                    TarazedLogger tarazedLogger6 = this.logger;
                    if (tarazedLogger6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("logger");
                    }
                    tarazedLogger6.i(TAG, "Arcus sync - failed, will retry.");
                    ListenableWorker.Result retry = ListenableWorker.Result.retry();
                    Intrinsics.checkExpressionValueIsNotNull(retry, "Result.retry()");
                    return retry;
                }
            }
            TarazedLogger tarazedLogger7 = this.logger;
            if (tarazedLogger7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            tarazedLogger7.w(TAG, "DeviceType has not yet been saved, reschedule Arcus sync.");
            ListenableWorker.Result retry2 = ListenableWorker.Result.retry();
            Intrinsics.checkExpressionValueIsNotNull(retry2, "Result.retry()");
            return retry2;
        } catch (Exception e) {
            TarazedLogger tarazedLogger8 = this.logger;
            if (tarazedLogger8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            tarazedLogger8.e(TAG, "Exception occurred trying to issue Arcus sync.", e);
            TarazedMetricsHelper tarazedMetricsHelper2 = this.metrics;
            if (tarazedMetricsHelper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            tarazedMetricsHelper2.addCount(TAG, METRIC_ARCUS_SYNC_UNKNOWN_EXCEPTION, 1.0d);
            ListenableWorker.Result retry3 = ListenableWorker.Result.retry();
            Intrinsics.checkExpressionValueIsNotNull(retry3, "Result.retry()");
            return retry3;
        }
    }

    @NotNull
    public final ArcusHelper getArcusHelper$TarazedAndroidLibrary_release() {
        ArcusHelper arcusHelper = this.arcusHelper;
        if (arcusHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("arcusHelper");
        }
        return arcusHelper;
    }

    @NotNull
    public final DeviceInfoUtilityAndroid getDeviceInfoUtility$TarazedAndroidLibrary_release() {
        DeviceInfoUtilityAndroid deviceInfoUtilityAndroid = this.deviceInfoUtility;
        if (deviceInfoUtilityAndroid == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceInfoUtility");
        }
        return deviceInfoUtilityAndroid;
    }

    @NotNull
    public final TarazedLogger getLogger$TarazedAndroidLibrary_release() {
        TarazedLogger tarazedLogger = this.logger;
        if (tarazedLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return tarazedLogger;
    }

    @NotNull
    public final TarazedMetricsHelper getMetrics$TarazedAndroidLibrary_release() {
        TarazedMetricsHelper tarazedMetricsHelper = this.metrics;
        if (tarazedMetricsHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return tarazedMetricsHelper;
    }

    @NotNull
    public final RemoteConfigurationManager getRemoteConfigurationManager$TarazedAndroidLibrary_release() {
        RemoteConfigurationManager remoteConfigurationManager = this.remoteConfigurationManager;
        if (remoteConfigurationManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("remoteConfigurationManager");
        }
        return remoteConfigurationManager;
    }

    public final void setArcusHelper$TarazedAndroidLibrary_release(@NotNull ArcusHelper arcusHelper) {
        Intrinsics.checkParameterIsNotNull(arcusHelper, "<set-?>");
        this.arcusHelper = arcusHelper;
    }

    public final void setDeviceInfoUtility$TarazedAndroidLibrary_release(@NotNull DeviceInfoUtilityAndroid deviceInfoUtilityAndroid) {
        Intrinsics.checkParameterIsNotNull(deviceInfoUtilityAndroid, "<set-?>");
        this.deviceInfoUtility = deviceInfoUtilityAndroid;
    }

    public final void setLogger$TarazedAndroidLibrary_release(@NotNull TarazedLogger tarazedLogger) {
        Intrinsics.checkParameterIsNotNull(tarazedLogger, "<set-?>");
        this.logger = tarazedLogger;
    }

    public final void setMetrics$TarazedAndroidLibrary_release(@NotNull TarazedMetricsHelper tarazedMetricsHelper) {
        Intrinsics.checkParameterIsNotNull(tarazedMetricsHelper, "<set-?>");
        this.metrics = tarazedMetricsHelper;
    }

    public final void setRemoteConfigurationManager$TarazedAndroidLibrary_release(@NotNull RemoteConfigurationManager remoteConfigurationManager) {
        Intrinsics.checkParameterIsNotNull(remoteConfigurationManager, "<set-?>");
        this.remoteConfigurationManager = remoteConfigurationManager;
    }

    @VisibleForTesting
    @Nullable
    public final Object syncConfiguration$TarazedAndroidLibrary_release(@NotNull Continuation<? super Integer> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        SafeContinuation safeContinuation = new SafeContinuation(intercepted);
        TarazedMetricsHelper tarazedMetricsHelper = this.metrics;
        if (tarazedMetricsHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        tarazedMetricsHelper.addCountHighPriority(TAG, METRIC_ARCUS_SYNC_ATTEMPTED, 1.0d);
        RemoteConfigurationManager remoteConfigurationManager = this.remoteConfigurationManager;
        if (remoteConfigurationManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("remoteConfigurationManager");
        }
        remoteConfigurationManager.sync(new ArcusCallback(this, safeContinuation));
        Object orThrow = safeContinuation.getOrThrow();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (orThrow == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }
}
