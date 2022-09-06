package com.amazon.alexa.fitness.metrics;

import com.amazon.deecomms.common.Constants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/AFITSMetrics;", "", "()V", MetricsOperation.UPLOAD, "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class AFITSMetrics {
    public static final AFITSMetrics INSTANCE = new AFITSMetrics();

    /* compiled from: MetricsConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/AFITSMetrics$Upload;", "", "()V", Constants.CALL_DURATION_KEY, "Lcom/amazon/alexa/fitness/metrics/UploadAFITSMetricComponent;", "getDuration", "()Lcom/amazon/alexa/fitness/metrics/UploadAFITSMetricComponent;", "ExpiredSessionPurged", "getExpiredSessionPurged", "Failure", "getFailure", "Started", "getStarted", "Success", "getSuccess", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Upload {
        public static final Upload INSTANCE = new Upload();
        @NotNull
        private static final UploadAFITSMetricComponent Started = new UploadAFITSMetricComponent("uploadSessionStarted");
        @NotNull
        private static final UploadAFITSMetricComponent Success = new UploadAFITSMetricComponent("uploadSessionSuccess");
        @NotNull
        private static final UploadAFITSMetricComponent Failure = new UploadAFITSMetricComponent("uploadSessionFailure");
        @NotNull
        private static final UploadAFITSMetricComponent Duration = new UploadAFITSMetricComponent("uploadSessionDuration");
        @NotNull
        private static final UploadAFITSMetricComponent ExpiredSessionPurged = new UploadAFITSMetricComponent("expiredSessionPurged");

        private Upload() {
        }

        @NotNull
        public final UploadAFITSMetricComponent getDuration() {
            return Duration;
        }

        @NotNull
        public final UploadAFITSMetricComponent getExpiredSessionPurged() {
            return ExpiredSessionPurged;
        }

        @NotNull
        public final UploadAFITSMetricComponent getFailure() {
            return Failure;
        }

        @NotNull
        public final UploadAFITSMetricComponent getStarted() {
            return Started;
        }

        @NotNull
        public final UploadAFITSMetricComponent getSuccess() {
            return Success;
        }
    }

    private AFITSMetrics() {
    }
}
