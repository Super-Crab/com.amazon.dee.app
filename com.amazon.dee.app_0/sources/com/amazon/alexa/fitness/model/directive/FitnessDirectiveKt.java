package com.amazon.alexa.fitness.model.directive;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessDirective.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0002¨\u0006\u0003"}, d2 = {"asFitnessDirective", "Lcom/amazon/alexa/fitness/model/directive/FitnessDirective;", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessDirectiveKt {
    @Nullable
    public static final FitnessDirective asFitnessDirective(@Nullable String str) {
        FitnessDirective[] values;
        if (str != null) {
            for (FitnessDirective fitnessDirective : FitnessDirective.values()) {
                if (Intrinsics.areEqual(fitnessDirective.getValue(), str)) {
                    return fitnessDirective;
                }
            }
            return null;
        }
        return null;
    }
}
