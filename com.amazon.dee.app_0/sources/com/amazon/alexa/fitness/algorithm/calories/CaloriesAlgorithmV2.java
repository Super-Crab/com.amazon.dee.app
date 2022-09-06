package com.amazon.alexa.fitness.algorithm.calories;

import com.amazon.alexa.fitness.algorithms.ActivityEvent;
import com.amazon.alexa.fitness.api.fitnessSdk.UserProfile;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CaloriesAlgorithmV2.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\u0012J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH&J\b\u0010\t\u001a\u00020\nH&J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0003H&¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesAlgorithmV2;", "", "addActivityEvent", "", "event", "Lcom/amazon/alexa/fitness/algorithms/ActivityEvent;", "addActivityEvents", DefaultDeliveryClient.EVENTS_DIRECTORY, "", "getTotalCaloriesBurned", "", "setOnTotalCaloriesChangedListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesAlgorithmV2$OnTotalCaloriesChangedListener;", "start", "userProfile", "Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;", "stop", "OnTotalCaloriesChangedListener", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public interface CaloriesAlgorithmV2 {

    /* compiled from: CaloriesAlgorithmV2.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesAlgorithmV2$OnTotalCaloriesChangedListener;", "", "onTotalCaloriesChanged", "", "algorithm", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesAlgorithmV2;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public interface OnTotalCaloriesChangedListener {
        void onTotalCaloriesChanged(@NotNull CaloriesAlgorithmV2 caloriesAlgorithmV2);
    }

    void addActivityEvent(@NotNull ActivityEvent activityEvent);

    void addActivityEvents(@NotNull List<? extends ActivityEvent> list);

    double getTotalCaloriesBurned();

    void setOnTotalCaloriesChangedListener(@Nullable OnTotalCaloriesChangedListener onTotalCaloriesChangedListener);

    void start(@NotNull UserProfile userProfile);

    void stop();
}
