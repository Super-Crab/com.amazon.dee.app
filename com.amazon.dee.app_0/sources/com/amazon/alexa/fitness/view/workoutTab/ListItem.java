package com.amazon.alexa.fitness.view.workoutTab;

import com.amazon.alexa.fitness.api.afits.FitnessSession;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessViewHolders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/fitness/view/workoutTab/ListItem;", "Lcom/amazon/alexa/fitness/view/workoutTab/WorkoutItem;", "fitnessSession", "Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "(Lcom/amazon/alexa/fitness/api/afits/FitnessSession;)V", "getFitnessSession", "()Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "setFitnessSession", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ListItem extends WorkoutItem {
    @NotNull
    private FitnessSession fitnessSession;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ListItem(@NotNull FitnessSession fitnessSession) {
        super(FitnessListType.LIST_ITEM);
        Intrinsics.checkParameterIsNotNull(fitnessSession, "fitnessSession");
        this.fitnessSession = fitnessSession;
    }

    @NotNull
    public final FitnessSession getFitnessSession() {
        return this.fitnessSession;
    }

    public final void setFitnessSession(@NotNull FitnessSession fitnessSession) {
        Intrinsics.checkParameterIsNotNull(fitnessSession, "<set-?>");
        this.fitnessSession = fitnessSession;
    }
}
