package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionTransition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003*\b\u0012\u0004\u0012\u00020\u00050\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0001\u001a\n\u0010\u0007\u001a\u00020\b*\u00020\t\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"TAG", "", "firstEligibleFitnessAccessorySensorProvider", "Lcom/amazon/alexa/fitness/accessory/FitnessAccessorySensorProvider;", "", "Lcom/amazon/alexa/fitness/sdk/SensorProvider;", "sensorId", "toTransition", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionTransition;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SessionManagerKt {
    private static final String TAG = "SessionManager";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r3v3, types: [boolean] */
    @Nullable
    public static final FitnessAccessorySensorProvider firstEligibleFitnessAccessorySensorProvider(@NotNull List<? extends SensorProvider> firstEligibleFitnessAccessorySensorProvider, @Nullable String str) {
        Object next;
        Intrinsics.checkParameterIsNotNull(firstEligibleFitnessAccessorySensorProvider, "$this$firstEligibleFitnessAccessorySensorProvider");
        ArrayList arrayList = new ArrayList();
        for (Object obj : firstEligibleFitnessAccessorySensorProvider) {
            SensorProvider sensorProvider = (SensorProvider) obj;
            if (sensorProvider.getRequired() && (sensorProvider instanceof FitnessAccessorySensorProvider)) {
                arrayList.add(obj);
            }
        }
        Iterator it2 = arrayList.iterator();
        if (!it2.hasNext()) {
            next = null;
        } else {
            next = it2.next();
            if (it2.hasNext()) {
                ?? r1 = Intrinsics.areEqual(((SensorProvider) next).getSensorInUse(), str);
                do {
                    Object next2 = it2.next();
                    ?? areEqual = Intrinsics.areEqual(((SensorProvider) next2).getSensorInUse(), str);
                    boolean z = r1;
                    if (r1 < areEqual) {
                        next = next2;
                        z = areEqual == true ? 1 : 0;
                    }
                    r1 = z;
                } while (it2.hasNext());
            }
        }
        if (next != null) {
            return (FitnessAccessorySensorProvider) next;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider");
    }

    public static /* synthetic */ FitnessAccessorySensorProvider firstEligibleFitnessAccessorySensorProvider$default(List list, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return firstEligibleFitnessAccessorySensorProvider(list, str);
    }

    @NotNull
    public static final FitnessSessionTransition toTransition(@NotNull Command toTransition) {
        Intrinsics.checkParameterIsNotNull(toTransition, "$this$toTransition");
        if (toTransition instanceof Command.AttemptRecovery) {
            return FitnessSessionTransition.RECOVERY_COMMAND_RECEIVED;
        }
        if (toTransition instanceof Command.Start) {
            return FitnessSessionTransition.START_COMMAND_RECEIVED;
        }
        if (toTransition instanceof Command.Stop) {
            return FitnessSessionTransition.STOP_COMMAND_RECEIVED;
        }
        if (toTransition instanceof Command.Pause) {
            return FitnessSessionTransition.PAUSE_COMMAND_RECEIVED;
        }
        if (!(toTransition instanceof Command.Resume)) {
            throw new NoWhenBranchMatchedException();
        }
        return FitnessSessionTransition.RESUME_COMMAND_RECEIVED;
    }
}
