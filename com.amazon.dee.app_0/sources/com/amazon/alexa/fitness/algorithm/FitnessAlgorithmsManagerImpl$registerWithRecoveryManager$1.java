package com.amazon.alexa.fitness.algorithm;

import com.amazon.alexa.fitness.api.fitnessSdk.WorkoutType;
import com.amazon.alexa.fitness.util.SerializationUtilsKt;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FitnessAlgorithmsManagerImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0012\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FitnessAlgorithmsManagerImpl$registerWithRecoveryManager$1 extends Lambda implements Function0<byte[]> {
    final /* synthetic */ FitnessAlgorithmsManagerImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessAlgorithmsManagerImpl$registerWithRecoveryManager$1(FitnessAlgorithmsManagerImpl fitnessAlgorithmsManagerImpl) {
        super(0);
        this.this$0 = fitnessAlgorithmsManagerImpl;
    }

    @Override // kotlin.jvm.functions.Function0
    @Nullable
    /* renamed from: invoke  reason: collision with other method in class */
    public final byte[] mo12560invoke() {
        WorkoutType workoutType;
        workoutType = this.this$0.activeWorkoutType;
        if (workoutType != null) {
            Map<String, FitnessAlgorithm> activeAlgorithms = this.this$0.getActiveAlgorithms();
            ArrayList arrayList = new ArrayList(activeAlgorithms.size());
            for (Map.Entry<String, FitnessAlgorithm> entry : activeAlgorithms.entrySet()) {
                arrayList.add(entry.getKey());
            }
            return SerializationUtilsKt.serialize(new FitnessAlgorithmRecoverableState(workoutType, arrayList));
        }
        return null;
    }
}
