package com.amazon.alexa.fitness.metrics;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/TimeshiftMetrics;", "", "()V", MetricsOperation.UPLOAD, "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class TimeshiftMetrics {
    public static final TimeshiftMetrics INSTANCE = new TimeshiftMetrics();

    /* compiled from: MetricsConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/TimeshiftMetrics$Upload;", "", "()V", "Failure", "Lcom/amazon/alexa/fitness/metrics/UploadTimeshiftMetricComponent;", "getFailure", "()Lcom/amazon/alexa/fitness/metrics/UploadTimeshiftMetricComponent;", "PendingSessions", "getPendingSessions", "SessionEndUntilUploadDuration", "getSessionEndUntilUploadDuration", "Started", "getStarted", "Success", "getSuccess", "UploadSessionFailureDuration", "getUploadSessionFailureDuration", "UploadSessionSuccessDuration", "getUploadSessionSuccessDuration", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Upload {
        public static final Upload INSTANCE = new Upload();
        @NotNull
        private static final UploadTimeshiftMetricComponent Started = new UploadTimeshiftMetricComponent("uploadSessionStarted");
        @NotNull
        private static final UploadTimeshiftMetricComponent Success = new UploadTimeshiftMetricComponent("uploadSessionSuccess");
        @NotNull
        private static final UploadTimeshiftMetricComponent Failure = new UploadTimeshiftMetricComponent("uploadSessionFailure");
        @NotNull
        private static final UploadTimeshiftMetricComponent UploadSessionSuccessDuration = new UploadTimeshiftMetricComponent("uploadSessionSuccessDuration");
        @NotNull
        private static final UploadTimeshiftMetricComponent UploadSessionFailureDuration = new UploadTimeshiftMetricComponent("uploadSessionFailureDuration");
        @NotNull
        private static final UploadTimeshiftMetricComponent SessionEndUntilUploadDuration = new UploadTimeshiftMetricComponent("sessionEndUntilUploadDuration");
        @NotNull
        private static final UploadTimeshiftMetricComponent PendingSessions = new UploadTimeshiftMetricComponent("pendingSessions");

        private Upload() {
        }

        @NotNull
        public final UploadTimeshiftMetricComponent getFailure() {
            return Failure;
        }

        @NotNull
        public final UploadTimeshiftMetricComponent getPendingSessions() {
            return PendingSessions;
        }

        @NotNull
        public final UploadTimeshiftMetricComponent getSessionEndUntilUploadDuration() {
            return SessionEndUntilUploadDuration;
        }

        @NotNull
        public final UploadTimeshiftMetricComponent getStarted() {
            return Started;
        }

        @NotNull
        public final UploadTimeshiftMetricComponent getSuccess() {
            return Success;
        }

        @NotNull
        public final UploadTimeshiftMetricComponent getUploadSessionFailureDuration() {
            return UploadSessionFailureDuration;
        }

        @NotNull
        public final UploadTimeshiftMetricComponent getUploadSessionSuccessDuration() {
            return UploadSessionSuccessDuration;
        }
    }

    private TimeshiftMetrics() {
    }
}
