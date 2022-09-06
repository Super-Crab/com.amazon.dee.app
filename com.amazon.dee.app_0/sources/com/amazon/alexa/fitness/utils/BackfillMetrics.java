package com.amazon.alexa.fitness.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/utils/BackfillMetrics;", "", "()V", "Companion", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class BackfillMetrics {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final BackfillMetricsComponent Initiated = new BackfillMetricsComponent("initiated");
    @NotNull
    private static final BackfillMetricsComponent GetStatusSuccess = new BackfillMetricsComponent("getStatusSuccess");
    @NotNull
    private static final BackfillMetricsComponent GetStatusFailed = new BackfillMetricsComponent("getStatusFailed");
    @NotNull
    private static final BackfillMetricsComponent BackfillStatusSuccess = new BackfillMetricsComponent("backfillStatusSuccess");
    @NotNull
    private static final BackfillMetricsComponent BackfillStatusInProgress = new BackfillMetricsComponent("backfillStatusInProgress");
    @NotNull
    private static final BackfillMetricsComponent BackfillStatusUnavailable = new BackfillMetricsComponent("backfillStatusUnavailable");
    @NotNull
    private static final BackfillMetricsComponent BackfillStatusFailed = new BackfillMetricsComponent("backfillStatusFailed");
    @NotNull
    private static final BackfillMetricsComponent StatusCheckCountToGetSuccess = new BackfillMetricsComponent("statusCheckCountToGetSuccess");

    /* compiled from: MetricConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/fitness/utils/BackfillMetrics$Companion;", "", "()V", "BackfillStatusFailed", "Lcom/amazon/alexa/fitness/utils/BackfillMetricsComponent;", "getBackfillStatusFailed", "()Lcom/amazon/alexa/fitness/utils/BackfillMetricsComponent;", "BackfillStatusInProgress", "getBackfillStatusInProgress", "BackfillStatusSuccess", "getBackfillStatusSuccess", "BackfillStatusUnavailable", "getBackfillStatusUnavailable", "GetStatusFailed", "getGetStatusFailed", "GetStatusSuccess", "getGetStatusSuccess", "Initiated", "getInitiated", "StatusCheckCountToGetSuccess", "getStatusCheckCountToGetSuccess", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final BackfillMetricsComponent getBackfillStatusFailed() {
            return BackfillMetrics.BackfillStatusFailed;
        }

        @NotNull
        public final BackfillMetricsComponent getBackfillStatusInProgress() {
            return BackfillMetrics.BackfillStatusInProgress;
        }

        @NotNull
        public final BackfillMetricsComponent getBackfillStatusSuccess() {
            return BackfillMetrics.BackfillStatusSuccess;
        }

        @NotNull
        public final BackfillMetricsComponent getBackfillStatusUnavailable() {
            return BackfillMetrics.BackfillStatusUnavailable;
        }

        @NotNull
        public final BackfillMetricsComponent getGetStatusFailed() {
            return BackfillMetrics.GetStatusFailed;
        }

        @NotNull
        public final BackfillMetricsComponent getGetStatusSuccess() {
            return BackfillMetrics.GetStatusSuccess;
        }

        @NotNull
        public final BackfillMetricsComponent getInitiated() {
            return BackfillMetrics.Initiated;
        }

        @NotNull
        public final BackfillMetricsComponent getStatusCheckCountToGetSuccess() {
            return BackfillMetrics.StatusCheckCountToGetSuccess;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
