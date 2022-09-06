package com.amazon.alexa.fitness.session;

import com.amazon.wellness.io.format.abs.BiometricDataPoint;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessDataHandlerImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"TAG", "", "hasActivityV1", "", "Lcom/amazon/wellness/io/format/abs/BiometricDataPoint;", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessDataHandlerImplKt {
    private static final String TAG = "FitnessDataHandler";

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasActivityV1(@NotNull BiometricDataPoint biometricDataPoint) {
        return biometricDataPoint.getValueCase() == BiometricDataPoint.ValueCase.ACTIVITY_V1;
    }
}
