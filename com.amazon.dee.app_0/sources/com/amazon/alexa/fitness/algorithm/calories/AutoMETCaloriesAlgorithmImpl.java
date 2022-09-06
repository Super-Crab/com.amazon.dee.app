package com.amazon.alexa.fitness.algorithm.calories;

import com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmV2;
import com.amazon.alexa.fitness.algorithms.ActivityEvent;
import com.amazon.alexa.fitness.algorithms.ActivityType;
import com.amazon.alexa.fitness.api.fitnessSdk.UserProfile;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.util.GsonUtilsKt;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.lang.ref.WeakReference;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AutoMETCaloriesAlgorithmImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0016\u0010 \u001a\u00020\u000e2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001f0\tH\u0016J\b\u0010\"\u001a\u00020\u0006H\u0002J\u0018\u0010#\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020\u0010H\u0016J\u0016\u0010'\u001a\u00020\u000e2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002J\u0012\u0010(\u001a\u00020\u000e2\b\u0010)\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010*\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020\u000eH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R/\u0010\u0007\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\n0\t¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0010@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011¨\u0006."}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/calories/AutoMETCaloriesAlgorithmImpl;", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesAlgorithmV2;", "logger", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/logs/ILog;)V", "activityBuffer", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesActivityBuffer;", "activityBufferHandler", "Lkotlin/reflect/KFunction1;", "", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesActivityWindowSummary;", "Lkotlin/ParameterName;", "name", "summaries", "", "heightInCm", "", "Ljava/lang/Double;", "metProvider", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesMETProvider;", "state", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesAlgorithmState;", "value", "totalCaloriesBurned", "setTotalCaloriesBurned", "(D)V", "totalCaloriesChangedListener", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesAlgorithmV2$OnTotalCaloriesChangedListener;", "weightInKg", "addActivityEvent", "event", "Lcom/amazon/alexa/fitness/algorithms/ActivityEvent;", "addActivityEvents", DefaultDeliveryClient.EVENTS_DIRECTORY, "createActivityBuffer", "estimateStepLengthInCm", "activityType", "Lcom/amazon/alexa/fitness/algorithms/ActivityType;", "getTotalCaloriesBurned", "processBufferSummaries", "setOnTotalCaloriesChangedListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "start", "userProfile", "Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;", "stop", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AutoMETCaloriesAlgorithmImpl implements CaloriesAlgorithmV2 {
    private CaloriesActivityBuffer activityBuffer;
    private final KFunction<Unit> activityBufferHandler;
    private Double heightInCm;
    private final ILog logger;
    private final CaloriesMETProvider metProvider;
    private CaloriesAlgorithmState state;
    private double totalCaloriesBurned;
    private CaloriesAlgorithmV2.OnTotalCaloriesChangedListener totalCaloriesChangedListener;
    private Double weightInKg;

    @Inject
    public AutoMETCaloriesAlgorithmImpl(@NotNull ILog logger) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.logger = logger;
        this.state = CaloriesAlgorithmState.STOPPED;
        this.metProvider = new COPACaloriesMETProviderImpl();
        this.activityBufferHandler = new AutoMETCaloriesAlgorithmImpl$activityBufferHandler$1(this);
    }

    private final CaloriesActivityBuffer createActivityBuffer() {
        return new CaloriesActivityBuffer(15.0d, new WeakReference(this.activityBufferHandler), this.logger);
    }

    private final double estimateStepLengthInCm(double d, ActivityType activityType) {
        return d * (activityType == ActivityType.ACTIVITY_TYPE_RUN ? 0.7d : 0.45882352941176474d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processBufferSummaries(List<CaloriesActivityWindowSummary> list) {
        Double d = this.heightInCm;
        Double d2 = this.weightInKg;
        if (d != null && d2 != null) {
            for (CaloriesActivityWindowSummary caloriesActivityWindowSummary : list) {
                ILog iLog = this.logger;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Activity buffer summary received: ");
                outline107.append(GsonUtilsKt.toJson(caloriesActivityWindowSummary));
                outline107.append('.');
                ILog.DefaultImpls.debug$default(iLog, "AutoMETCaloriesAlgorithmImpl", outline107.toString(), null, 4, null);
                ActivityType activityClassification = caloriesActivityWindowSummary.getActivityClassification();
                double estimateStepLengthInCm = estimateStepLengthInCm(d.doubleValue(), activityClassification);
                final double durationMinutes = ((60.0d / caloriesActivityWindowSummary.getDurationMinutes()) * (caloriesActivityWindowSummary.getAccumulatedSteps() * estimateStepLengthInCm)) / 160934.4d;
                Double computeMETValue = this.metProvider.computeMETValue(new CaloriesWorkoutStatistics(durationMinutes) { // from class: com.amazon.alexa.fitness.algorithm.calories.AutoMETCaloriesAlgorithmImpl$processBufferSummaries$1$workoutStats$1
                    final /* synthetic */ double $cadenceInMPH;
                    private final double cadenceInMPH;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.$cadenceInMPH = durationMinutes;
                        this.cadenceInMPH = durationMinutes;
                    }

                    @Override // com.amazon.alexa.fitness.algorithm.calories.CaloriesWorkoutStatistics
                    public double getCadenceInMPH() {
                        return this.cadenceInMPH;
                    }
                });
                double doubleValue = computeMETValue != null ? computeMETValue.doubleValue() : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                double doubleValue2 = d2.doubleValue() * caloriesActivityWindowSummary.getDurationMinutes() * doubleValue * 0.0175d;
                setTotalCaloriesBurned(this.totalCaloriesBurned + doubleValue2);
                ILog iLog2 = this.logger;
                ILog.DefaultImpls.info$default(iLog2, "AutoMETCaloriesAlgorithmImpl", "Computed activityClassification=" + activityClassification + ", stepLengthInCm=" + estimateStepLengthInCm + ", cadenceInMph=" + durationMinutes + ", met=" + doubleValue + ", caloriesBurned=" + doubleValue2 + ", totalCaloriesBurned=" + this.totalCaloriesBurned, null, 4, null);
            }
            return;
        }
        ILog.DefaultImpls.error$default(this.logger, "AutoMETCaloriesAlgorithmImpl", "Activity buffer summary received while algorithm is not properly initialized.", null, 4, null);
    }

    private final void setTotalCaloriesBurned(double d) {
        this.totalCaloriesBurned = d;
        CaloriesAlgorithmV2.OnTotalCaloriesChangedListener onTotalCaloriesChangedListener = this.totalCaloriesChangedListener;
        if (onTotalCaloriesChangedListener != null) {
            onTotalCaloriesChangedListener.onTotalCaloriesChanged(this);
        }
    }

    @Override // com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmV2
    public void addActivityEvent(@NotNull ActivityEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        ILog iLog = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Activity event received (eventType=");
        outline107.append(event.eventType);
        outline107.append(").");
        ILog.DefaultImpls.info$default(iLog, "AutoMETCaloriesAlgorithmImpl", outline107.toString(), null, 4, null);
        if (this.state != CaloriesAlgorithmState.STARTED) {
            ILog.DefaultImpls.warn$default(this.logger, "AutoMETCaloriesAlgorithmImpl", "Attempt to add ActivityEvent when the algorithm is not started, ignoring event.", null, 4, null);
            return;
        }
        CaloriesActivityBuffer caloriesActivityBuffer = this.activityBuffer;
        if (caloriesActivityBuffer == null) {
            return;
        }
        caloriesActivityBuffer.addEvent(event);
    }

    @Override // com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmV2
    public void addActivityEvents(@NotNull List<? extends ActivityEvent> events) {
        Intrinsics.checkParameterIsNotNull(events, "events");
        ILog iLog = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bulk adding activity events (count=");
        outline107.append(events.size());
        outline107.append(").");
        ILog.DefaultImpls.info$default(iLog, "AutoMETCaloriesAlgorithmImpl", outline107.toString(), null, 4, null);
        for (ActivityEvent activityEvent : events) {
            addActivityEvent(activityEvent);
        }
    }

    @Override // com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmV2
    public double getTotalCaloriesBurned() {
        ILog iLog = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getTotalCaloriesBurned() -> ");
        outline107.append(this.totalCaloriesBurned);
        ILog.DefaultImpls.debug$default(iLog, "AutoMETCaloriesAlgorithmImpl", outline107.toString(), null, 4, null);
        double d = this.totalCaloriesBurned;
        return !Double.isInfinite(d) && !Double.isNaN(d) ? this.totalCaloriesBurned : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    @Override // com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmV2
    public void setOnTotalCaloriesChangedListener(@Nullable CaloriesAlgorithmV2.OnTotalCaloriesChangedListener onTotalCaloriesChangedListener) {
        this.totalCaloriesChangedListener = onTotalCaloriesChangedListener;
    }

    @Override // com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmV2
    public void start(@NotNull UserProfile userProfile) {
        Intrinsics.checkParameterIsNotNull(userProfile, "userProfile");
        ILog.DefaultImpls.info$default(this.logger, "AutoMETCaloriesAlgorithmImpl", "Algorithm starting", null, 4, null);
        ILog iLog = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Algorithm started with user profile: ");
        outline107.append(GsonUtilsKt.toJson(userProfile));
        ILog.DefaultImpls.debug$default(iLog, "AutoMETCaloriesAlgorithmImpl", outline107.toString(), null, 4, null);
        if (this.state != CaloriesAlgorithmState.STOPPED) {
            ILog.DefaultImpls.warn$default(this.logger, "AutoMETCaloriesAlgorithmImpl", "Received algorithm start when already in started state! Processing start; but, this scenario should not be happening.", null, 4, null);
        }
        this.weightInKg = userProfile.getWeightInKilograms();
        this.heightInCm = Double.valueOf(userProfile.getHeightInCentimetres());
        setTotalCaloriesBurned(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        this.activityBuffer = createActivityBuffer();
        this.state = CaloriesAlgorithmState.STARTED;
    }

    @Override // com.amazon.alexa.fitness.algorithm.calories.CaloriesAlgorithmV2
    public void stop() {
        ILog.DefaultImpls.info$default(this.logger, "AutoMETCaloriesAlgorithmImpl", "Algorithm stop requested", null, 4, null);
        CaloriesActivityBuffer caloriesActivityBuffer = this.activityBuffer;
        if (caloriesActivityBuffer != null) {
            caloriesActivityBuffer.flush();
        }
        this.state = CaloriesAlgorithmState.STOPPED;
        this.activityBuffer = null;
    }
}
