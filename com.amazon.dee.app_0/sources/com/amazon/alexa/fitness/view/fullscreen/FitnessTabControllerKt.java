package com.amazon.alexa.fitness.view.fullscreen;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.alexa.fitness.utils.Fonts;
import com.google.android.material.tabs.TabLayout;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessTabController.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"TAG", "", "addTab", "", "Lcom/google/android/material/tabs/TabLayout;", "tab", "Lcom/google/android/material/tabs/TabLayout$Tab;", "AlexaMobileAndroidFitnessUI_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessTabControllerKt {
    private static final String TAG = "AFX-TabController";

    public static final void addTab(@NotNull TabLayout addTab, @NotNull TabLayout.Tab tab) {
        Intrinsics.checkParameterIsNotNull(addTab, "$this$addTab");
        Intrinsics.checkParameterIsNotNull(tab, "tab");
        addTab.addTab(tab);
        View childAt = addTab.getChildAt(0);
        if (childAt != null) {
            View childAt2 = ((ViewGroup) childAt).getChildAt(tab.getPosition());
            if (childAt2 != null) {
                View childAt3 = ((ViewGroup) childAt2).getChildAt(1);
                Fonts fonts = Fonts.EMBER_REGULAR;
                if (childAt3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
                }
                fonts.apply((TextView) childAt3);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
    }
}
