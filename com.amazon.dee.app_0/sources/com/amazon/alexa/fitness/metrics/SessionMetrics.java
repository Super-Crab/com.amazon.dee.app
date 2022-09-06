package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.api.afx.FitnessMetrics;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionCommandSource;
import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.MetricsAggregatorRecovery;
import com.amazon.alexa.fitness.util.ConversionUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SessionMetrics.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nJ\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/SessionMetrics;", "", "metricsAggregator", "Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;", "metricsAggregatorRecovery", "Lcom/amazon/alexa/fitness/sdk/MetricsAggregatorRecovery;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;Lcom/amazon/alexa/fitness/sdk/MetricsAggregatorRecovery;Lcom/amazon/alexa/fitness/logs/ILog;)V", "getDistanceInFeet", "", "fitnessMetrics", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics$StepBased;", "parseCommandRecordMetric", "", "command", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "publishFitnessMetricsMetrics", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;", "duration", "recordPause", "source", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionCommandSource;", "recordResume", "recordStart", "recordStop", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SessionMetrics {
    private final ILog log;
    private final MetricsAggregator metricsAggregator;
    private final MetricsAggregatorRecovery metricsAggregatorRecovery;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Units.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;

        static {
            $EnumSwitchMapping$0[Units.Feet.ordinal()] = 1;
            $EnumSwitchMapping$0[Units.Miles.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[SessionCommandSource.values().length];
            $EnumSwitchMapping$1[SessionCommandSource.GUI.ordinal()] = 1;
            $EnumSwitchMapping$1[SessionCommandSource.VUI.ordinal()] = 2;
            $EnumSwitchMapping$1[SessionCommandSource.SENSOR.ordinal()] = 3;
            $EnumSwitchMapping$2 = new int[SessionCommandSource.values().length];
            $EnumSwitchMapping$2[SessionCommandSource.GUI.ordinal()] = 1;
            $EnumSwitchMapping$2[SessionCommandSource.VUI.ordinal()] = 2;
            $EnumSwitchMapping$2[SessionCommandSource.SENSOR.ordinal()] = 3;
            $EnumSwitchMapping$3 = new int[SessionCommandSource.values().length];
            $EnumSwitchMapping$3[SessionCommandSource.GUI.ordinal()] = 1;
            $EnumSwitchMapping$3[SessionCommandSource.VUI.ordinal()] = 2;
            $EnumSwitchMapping$3[SessionCommandSource.SENSOR.ordinal()] = 3;
            $EnumSwitchMapping$4 = new int[SessionCommandSource.values().length];
            $EnumSwitchMapping$4[SessionCommandSource.GUI.ordinal()] = 1;
            $EnumSwitchMapping$4[SessionCommandSource.VUI.ordinal()] = 2;
            $EnumSwitchMapping$4[SessionCommandSource.SENSOR.ordinal()] = 3;
        }
    }

    @Inject
    public SessionMetrics(@NotNull MetricsAggregator metricsAggregator, @NotNull MetricsAggregatorRecovery metricsAggregatorRecovery, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(metricsAggregator, "metricsAggregator");
        Intrinsics.checkParameterIsNotNull(metricsAggregatorRecovery, "metricsAggregatorRecovery");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.metricsAggregator = metricsAggregator;
        this.metricsAggregatorRecovery = metricsAggregatorRecovery;
        this.log = log;
    }

    private final long getDistanceInFeet(FitnessMetrics.StepBased stepBased) {
        double doubleValue;
        int i = WhenMappings.$EnumSwitchMapping$0[stepBased.getDistance().getSecond().ordinal()];
        if (i == 1) {
            doubleValue = stepBased.getDistance().getFirst().doubleValue();
        } else if (i != 2) {
            ILog iLog = this.log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unexpected units for distance - ");
            outline107.append(stepBased.getDistance().getSecond());
            ILog.DefaultImpls.error$default(iLog, "AFX-SessionMetrics", outline107.toString(), null, 4, null);
            return 0L;
        } else {
            doubleValue = ConversionUtils.Companion.convertMilesToFeet(stepBased.getDistance().getFirst().doubleValue());
        }
        return (long) doubleValue;
    }

    private final void recordPause(SessionCommandSource sessionCommandSource) {
        int i = WhenMappings.$EnumSwitchMapping$1[sessionCommandSource.ordinal()];
        if (i == 1) {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getGUI_PAUSE());
        } else if (i == 2) {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getVUI_PAUSE());
        } else if (i != 3) {
        } else {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getSENSOR_PAUSE());
        }
    }

    private final void recordResume(SessionCommandSource sessionCommandSource) {
        int i = WhenMappings.$EnumSwitchMapping$2[sessionCommandSource.ordinal()];
        if (i == 1) {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getGUI_RESUME());
        } else if (i == 2) {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getVUI_RESUME());
        } else if (i != 3) {
        } else {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getSENSOR_RESUME());
        }
    }

    private final void recordStart(SessionCommandSource sessionCommandSource) {
        this.metricsAggregator.startOrResumeTimer(AggregatedMetricsConstants.Companion.getSESSION_EXISTENCE_DURATION());
        int i = WhenMappings.$EnumSwitchMapping$3[sessionCommandSource.ordinal()];
        if (i == 1) {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getGUI_START());
        } else if (i != 2) {
        } else {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getVUI_START());
        }
    }

    private final void recordStop(SessionCommandSource sessionCommandSource) {
        int i = WhenMappings.$EnumSwitchMapping$4[sessionCommandSource.ordinal()];
        if (i == 1) {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getGUI_STOP());
        } else if (i == 2) {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getVUI_STOP());
        } else if (i != 3) {
        } else {
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getSENSOR_STOP());
        }
    }

    public final void parseCommandRecordMetric(@NotNull Command command) {
        Intrinsics.checkParameterIsNotNull(command, "command");
        if (command instanceof Command.Pause) {
            recordPause(((Command.Pause) command).getSource());
        } else if (command instanceof Command.Resume) {
            recordResume(((Command.Resume) command).getSource());
        } else if (command instanceof Command.Start) {
            recordStart(((Command.Start) command).getSource());
        } else if (command instanceof Command.Stop) {
            recordStop(((Command.Stop) command).getSource());
        } else if (!(command instanceof Command.AttemptRecovery)) {
        } else {
            this.metricsAggregatorRecovery.recoverAggregatedMetrics();
            this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getRECOVERY_COUNT());
        }
    }

    public final void publishFitnessMetricsMetrics(@NotNull FitnessMetrics fitnessMetrics, long j) {
        Intrinsics.checkParameterIsNotNull(fitnessMetrics, "fitnessMetrics");
        if (fitnessMetrics instanceof FitnessMetrics.StepBased) {
            FitnessMetrics.StepBased stepBased = (FitnessMetrics.StepBased) fitnessMetrics;
            Pair<Double, Units> calories = stepBased.getCalories();
            if (calories != null) {
                this.metricsAggregator.recordCounter(AggregatedMetricsConstants.Companion.getCALORIES(), (long) calories.getFirst().doubleValue());
            }
            this.metricsAggregator.recordCounter(AggregatedMetricsConstants.Companion.getDISTANCE_IN_FEET(), getDistanceInFeet(stepBased));
            this.metricsAggregator.recordTimer(AggregatedMetricsConstants.Companion.getSESSION_DURATION(), j);
            this.metricsAggregator.recordCounter(AggregatedMetricsConstants.Companion.getSPEED(), (long) stepBased.getSpeed().getFirst().doubleValue());
            this.metricsAggregator.recordCounter(AggregatedMetricsConstants.Companion.getSTEPS(), stepBased.getSteps().getFirst().intValue());
        }
    }
}
