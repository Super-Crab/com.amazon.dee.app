package com.amazon.alexa.fitness.view.workoutTab;

import com.amazon.alexa.fitness.utils.FormatUtilKt;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessViewHolders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\u001a\u0015\u0010\u0002\u001a\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"TAG", "", "getValidCalories", "totalCalories", "", "(Ljava/lang/Double;)Ljava/lang/String;", "AlexaMobileAndroidFitnessUI_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessViewHoldersKt {
    private static final String TAG = "AFX-ListViewHolder";

    @NotNull
    public static final String getValidCalories(@Nullable Double d) {
        return FormatUtilKt.getFormattedCalories(d != null ? (int) d.doubleValue() : -1);
    }
}
