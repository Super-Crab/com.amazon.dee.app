package com.amazon.alexa.fitness.metrics;

import com.amazon.deecomms.common.Constants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/DatabaseMetrics;", "", "()V", "InitializationError", "Lcom/amazon/alexa/fitness/metrics/DatabaseMetricComponent;", "getInitializationError", "()Lcom/amazon/alexa/fitness/metrics/DatabaseMetricComponent;", "Sample", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DatabaseMetrics {
    public static final DatabaseMetrics INSTANCE = new DatabaseMetrics();
    @NotNull
    private static final DatabaseMetricComponent InitializationError = new DatabaseMetricComponent("initializationError");

    /* compiled from: MetricsConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/DatabaseMetrics$Sample;", "", "()V", "DeleteAll", "FetchAll", "Save", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Sample {
        public static final Sample INSTANCE = new Sample();

        /* compiled from: MetricsConstants.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/DatabaseMetrics$Sample$DeleteAll;", "", "()V", Constants.CALL_DURATION_KEY, "Lcom/amazon/alexa/fitness/metrics/DatabaseMetricComponent;", "getDuration", "()Lcom/amazon/alexa/fitness/metrics/DatabaseMetricComponent;", "Error", "getError", "Failure", "getFailure", "Success", "getSuccess", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes8.dex */
        public static final class DeleteAll {
            public static final DeleteAll INSTANCE = new DeleteAll();
            @NotNull
            private static final DatabaseMetricComponent Success = new DatabaseMetricComponent("deleteAllSamplesSuccess");
            @NotNull
            private static final DatabaseMetricComponent Failure = new DatabaseMetricComponent("deleteAllSamplesFailure");
            @NotNull
            private static final DatabaseMetricComponent Error = new DatabaseMetricComponent("deleteAllSamplesError");
            @NotNull
            private static final DatabaseMetricComponent Duration = new DatabaseMetricComponent("deleteAllSamplesDuration");

            private DeleteAll() {
            }

            @NotNull
            public final DatabaseMetricComponent getDuration() {
                return Duration;
            }

            @NotNull
            public final DatabaseMetricComponent getError() {
                return Error;
            }

            @NotNull
            public final DatabaseMetricComponent getFailure() {
                return Failure;
            }

            @NotNull
            public final DatabaseMetricComponent getSuccess() {
                return Success;
            }
        }

        /* compiled from: MetricsConstants.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/DatabaseMetrics$Sample$FetchAll;", "", "()V", Constants.CALL_DURATION_KEY, "Lcom/amazon/alexa/fitness/metrics/DatabaseMetricComponent;", "getDuration", "()Lcom/amazon/alexa/fitness/metrics/DatabaseMetricComponent;", "Error", "getError", "Failure", "getFailure", "Success", "getSuccess", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes8.dex */
        public static final class FetchAll {
            public static final FetchAll INSTANCE = new FetchAll();
            @NotNull
            private static final DatabaseMetricComponent Success = new DatabaseMetricComponent("fetchAllSamplesSuccess");
            @NotNull
            private static final DatabaseMetricComponent Failure = new DatabaseMetricComponent("fetchAllSamplesFailure");
            @NotNull
            private static final DatabaseMetricComponent Error = new DatabaseMetricComponent("fetchAllSamplesError");
            @NotNull
            private static final DatabaseMetricComponent Duration = new DatabaseMetricComponent("fetchAllSamplesDuration");

            private FetchAll() {
            }

            @NotNull
            public final DatabaseMetricComponent getDuration() {
                return Duration;
            }

            @NotNull
            public final DatabaseMetricComponent getError() {
                return Error;
            }

            @NotNull
            public final DatabaseMetricComponent getFailure() {
                return Failure;
            }

            @NotNull
            public final DatabaseMetricComponent getSuccess() {
                return Success;
            }
        }

        /* compiled from: MetricsConstants.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/DatabaseMetrics$Sample$Save;", "", "()V", "Error", "Lcom/amazon/alexa/fitness/metrics/DatabaseMetricComponent;", "getError", "()Lcom/amazon/alexa/fitness/metrics/DatabaseMetricComponent;", "Failure", "getFailure", "Success", "getSuccess", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes8.dex */
        public static final class Save {
            public static final Save INSTANCE = new Save();
            @NotNull
            private static final DatabaseMetricComponent Success = new DatabaseMetricComponent("saveSampleSuccess");
            @NotNull
            private static final DatabaseMetricComponent Failure = new DatabaseMetricComponent("saveSampleFailure");
            @NotNull
            private static final DatabaseMetricComponent Error = new DatabaseMetricComponent("saveSampleError");

            private Save() {
            }

            @NotNull
            public final DatabaseMetricComponent getError() {
                return Error;
            }

            @NotNull
            public final DatabaseMetricComponent getFailure() {
                return Failure;
            }

            @NotNull
            public final DatabaseMetricComponent getSuccess() {
                return Success;
            }
        }

        private Sample() {
        }
    }

    private DatabaseMetrics() {
    }

    @NotNull
    public final DatabaseMetricComponent getInitializationError() {
        return InitializationError;
    }
}
