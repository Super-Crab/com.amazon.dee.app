package com.amazon.alexa.fitness.api.afits;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0001¨\u0006\u0002"}, d2 = {"orNull", "Lcom/amazon/alexa/fitness/api/afits/Heading;", "AlexaMobileAndroidFitnessAPI_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class AfitsTypesKt {
    @Nullable
    public static final Heading orNull(@NotNull Heading orNull) {
        Intrinsics.checkParameterIsNotNull(orNull, "$this$orNull");
        if (orNull.getDirectionInDegrees() > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            return orNull;
        }
        return null;
    }
}
