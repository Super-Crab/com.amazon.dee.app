package com.amazon.alexa.fitness.view.startTab;

import android.content.Context;
import android.content.res.Resources;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CustomStopButtonView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\b\u001a\u00020\t*\u00020\n\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"MAX_ANGLE", "", "MAX_PROGRESS", "", "PADDING", "START_ANGLE", "TAG", "", "isDarkThemeOn", "", "Landroid/content/Context;", "AlexaMobileAndroidFitnessUI_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class CustomStopButtonViewKt {
    private static final float MAX_ANGLE = 360.0f;
    public static final int MAX_PROGRESS = 100;
    private static final float PADDING = 20.0f;
    private static final float START_ANGLE = -90.0f;
    private static final String TAG = "AFX-CustomStopButtonView";

    public static final boolean isDarkThemeOn(@NotNull Context isDarkThemeOn) {
        Intrinsics.checkParameterIsNotNull(isDarkThemeOn, "$this$isDarkThemeOn");
        Resources resources = isDarkThemeOn.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return (resources.getConfiguration().uiMode & 48) == 32;
    }
}
