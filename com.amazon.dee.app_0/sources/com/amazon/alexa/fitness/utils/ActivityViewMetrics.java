package com.amazon.alexa.fitness.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/utils/ActivityViewMetrics;", "", "()V", "Companion", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ActivityViewMetrics {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final MetricComponent DONE_BUTTON = new MetricComponent(MetricName.DONE, "activityView", "activityView");
    @NotNull
    private static final MetricComponent START_BUTTON = new MetricComponent("start", "activityView", "activityView");
    @NotNull
    private static final MetricComponent PAUSE_BUTTON = new MetricComponent("pause", "activityView", "activityView");
    @NotNull
    private static final MetricComponent RESUME_BUTTON = new MetricComponent("resume", "activityView", "activityView");
    @NotNull
    private static final MetricComponent STOP_BUTTON = new MetricComponent("stop", "activityView", "activityView");
    @NotNull
    private static final MetricComponent VIEW = new MetricComponent("view", "activityView", "activityView");

    /* compiled from: MetricConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/fitness/utils/ActivityViewMetrics$Companion;", "", "()V", "DONE_BUTTON", "Lcom/amazon/alexa/fitness/utils/MetricComponent;", "getDONE_BUTTON", "()Lcom/amazon/alexa/fitness/utils/MetricComponent;", "PAUSE_BUTTON", "getPAUSE_BUTTON", "RESUME_BUTTON", "getRESUME_BUTTON", "START_BUTTON", "getSTART_BUTTON", "STOP_BUTTON", "getSTOP_BUTTON", "VIEW", "getVIEW", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final MetricComponent getDONE_BUTTON() {
            return ActivityViewMetrics.DONE_BUTTON;
        }

        @NotNull
        public final MetricComponent getPAUSE_BUTTON() {
            return ActivityViewMetrics.PAUSE_BUTTON;
        }

        @NotNull
        public final MetricComponent getRESUME_BUTTON() {
            return ActivityViewMetrics.RESUME_BUTTON;
        }

        @NotNull
        public final MetricComponent getSTART_BUTTON() {
            return ActivityViewMetrics.START_BUTTON;
        }

        @NotNull
        public final MetricComponent getSTOP_BUTTON() {
            return ActivityViewMetrics.STOP_BUTTON;
        }

        @NotNull
        public final MetricComponent getVIEW() {
            return ActivityViewMetrics.VIEW;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
