package com.amazon.alexa.fitness.view.workoutTab;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessViewHolders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/fitness/view/workoutTab/WorkoutItem;", "", "itemType", "Lcom/amazon/alexa/fitness/view/workoutTab/FitnessListType;", "(Lcom/amazon/alexa/fitness/view/workoutTab/FitnessListType;)V", "getItemType", "()Lcom/amazon/alexa/fitness/view/workoutTab/FitnessListType;", "setItemType", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public class WorkoutItem {
    @NotNull
    private FitnessListType itemType;

    public WorkoutItem(@NotNull FitnessListType itemType) {
        Intrinsics.checkParameterIsNotNull(itemType, "itemType");
        this.itemType = itemType;
    }

    @NotNull
    public final FitnessListType getItemType() {
        return this.itemType;
    }

    public final void setItemType(@NotNull FitnessListType fitnessListType) {
        Intrinsics.checkParameterIsNotNull(fitnessListType, "<set-?>");
        this.itemType = fitnessListType;
    }
}
