package com.amazon.alexa.fitness.util;

import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.metrics.AggregatedMetricsConstants;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.metrics.PercentMetrics;
import com.amazon.alexa.fitness.sdk.MetricsAggregatorRecovery;
import com.amazon.alexa.fitness.utils.MetricComponent;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.connector.DefaultKinesisConnector;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ActivityLifecycleObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013J \u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u000e\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u0010J\b\u0010\u001f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/amazon/alexa/fitness/util/ActivityLifecycleObserver;", "", "metricsAggregatorRecovery", "Lcom/amazon/alexa/fitness/sdk/MetricsAggregatorRecovery;", "metricsAggregator", "Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;", "(Lcom/amazon/alexa/fitness/sdk/MetricsAggregatorRecovery;Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;)V", "isAppForegrounded", "", "isWorkoutActive", DefaultKinesisConnector.COMPONENT, "Lcom/amazon/alexa/mobilytics/Mobilytics;", "kotlin.jvm.PlatformType", "notificationCenter", "Lcom/amazon/alexa/protocols/lifecycle/MainActivityLifecycleObserverRegistrar;", "addObserver", "", "calculateLifecycleMetrics", "workoutDuration", "", "calculatePercent", "elapsedTime", "", "metricName", "Lcom/amazon/alexa/fitness/utils/MetricComponent;", "getLifeCycleObserver", "Lcom/amazon/alexa/protocols/lifecycle/MainActivityLifecycleObserver;", "onSessionStateChanged", "newState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "removeObserver", "updateLifecycleMetrics", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ActivityLifecycleObserver {
    private boolean isAppForegrounded;
    private boolean isWorkoutActive;
    private final MetricsAggregator metricsAggregator;
    private final MetricsAggregatorRecovery metricsAggregatorRecovery;
    private Mobilytics mobilytics;
    private final MainActivityLifecycleObserverRegistrar notificationCenter;

    @Inject
    public ActivityLifecycleObserver(@NotNull MetricsAggregatorRecovery metricsAggregatorRecovery, @NotNull MetricsAggregator metricsAggregator) {
        Intrinsics.checkParameterIsNotNull(metricsAggregatorRecovery, "metricsAggregatorRecovery");
        Intrinsics.checkParameterIsNotNull(metricsAggregator, "metricsAggregator");
        this.metricsAggregatorRecovery = metricsAggregatorRecovery;
        this.metricsAggregator = metricsAggregator;
        this.isAppForegrounded = true;
        this.mobilytics = (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
        this.notificationCenter = (MainActivityLifecycleObserverRegistrar) GeneratedOutlineSupport1.outline20(MainActivityLifecycleObserverRegistrar.class);
    }

    private final void calculatePercent(double d, long j, MetricComponent metricComponent) {
        if (j == 0) {
            return;
        }
        Mobilytics mobilytics = this.mobilytics;
        Intrinsics.checkExpressionValueIsNotNull(mobilytics, "mobilytics");
        MetricHelperKt.recordCounter(mobilytics, metricComponent, (long) ((d / j) * 100.0d));
    }

    private final MainActivityLifecycleObserver getLifeCycleObserver() {
        return new MainActivityLifecycleObserver() { // from class: com.amazon.alexa.fitness.util.ActivityLifecycleObserver$getLifeCycleObserver$1
            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityCreated() {
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityDestroy() {
                MetricsAggregatorRecovery metricsAggregatorRecovery;
                metricsAggregatorRecovery = ActivityLifecycleObserver.this.metricsAggregatorRecovery;
                metricsAggregatorRecovery.saveAggregatedMetrics();
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityPause() {
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityResume() {
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityStart() {
                ActivityLifecycleObserver.this.isAppForegrounded = true;
                ActivityLifecycleObserver.this.updateLifecycleMetrics();
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityStop() {
                ActivityLifecycleObserver.this.isAppForegrounded = false;
                ActivityLifecycleObserver.this.updateLifecycleMetrics();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLifecycleMetrics() {
        if (!this.isWorkoutActive) {
            this.metricsAggregator.pauseTimer(AggregatedMetricsConstants.Companion.getBACKGROUNDED_DURATION());
            this.metricsAggregator.pauseTimer(AggregatedMetricsConstants.Companion.getFOREGROUNDED_DURATION());
        } else if (this.isAppForegrounded) {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getFOREGROUNDED());
            this.metricsAggregator.startOrResumeTimer(AggregatedMetricsConstants.Companion.getFOREGROUNDED_DURATION());
            this.metricsAggregator.pauseTimer(AggregatedMetricsConstants.Companion.getBACKGROUNDED_DURATION());
        } else {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getBACKGROUNDED());
            this.metricsAggregator.startOrResumeTimer(AggregatedMetricsConstants.Companion.getBACKGROUNDED_DURATION());
            this.metricsAggregator.pauseTimer(AggregatedMetricsConstants.Companion.getFOREGROUNDED_DURATION());
        }
    }

    public final void addObserver() {
        this.notificationCenter.addObserver(getLifeCycleObserver());
    }

    public final void calculateLifecycleMetrics(long j) {
        PercentMetrics[] values;
        MobilyticsMetricsTimer mobilyticsMetricsTimer;
        for (PercentMetrics percentMetrics : PercentMetrics.values()) {
            if (this.metricsAggregator.getAggregatedTimerMetrics().get(percentMetrics.getIdentifiers().getFirst()) != null) {
                calculatePercent(mobilyticsMetricsTimer.getElapsedTime() / 1000.0d, j, percentMetrics.getIdentifiers().getSecond());
            }
        }
        MobilyticsMetricsTimer mobilyticsMetricsTimer2 = this.metricsAggregator.getAggregatedTimerMetrics().get(AggregatedMetricsConstants.Companion.getBACKGROUNDED_DURATION());
        long j2 = 0;
        long elapsedTime = mobilyticsMetricsTimer2 != null ? mobilyticsMetricsTimer2.getElapsedTime() : 0L;
        MobilyticsMetricsTimer mobilyticsMetricsTimer3 = this.metricsAggregator.getAggregatedTimerMetrics().get(AggregatedMetricsConstants.Companion.getFOREGROUNDED_DURATION());
        if (mobilyticsMetricsTimer3 != null) {
            j2 = mobilyticsMetricsTimer3.getElapsedTime();
        }
        long j3 = elapsedTime + j2;
        Mobilytics mobilytics = this.mobilytics;
        Intrinsics.checkExpressionValueIsNotNull(mobilytics, "mobilytics");
        MetricHelperKt.recordTimer(mobilytics, AggregatedMetricsConstants.Companion.getPROCESSING_DURATION(), j3);
        calculatePercent(j3 / 1000.0d, j, AggregatedMetricsConstants.Companion.getPROCESSING_PERCENTAGE());
    }

    public final void onSessionStateChanged(@NotNull FitnessSessionState newState) {
        Intrinsics.checkParameterIsNotNull(newState, "newState");
        this.isWorkoutActive = newState == FitnessSessionState.ACTIVE;
        updateLifecycleMetrics();
    }

    public final void removeObserver() {
        this.notificationCenter.removeObserver(getLifeCycleObserver());
    }
}
