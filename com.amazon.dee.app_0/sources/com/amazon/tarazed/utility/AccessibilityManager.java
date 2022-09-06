package com.amazon.tarazed.utility;

import android.content.Context;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessibilityManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/utility/AccessibilityManager;", "", "isEnabled", "", "context", "Landroid/content/Context;", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface AccessibilityManager {

    /* compiled from: AccessibilityManager.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class DefaultImpls {
        public static boolean isEnabled(AccessibilityManager accessibilityManager, @NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Object systemService = context.getSystemService("accessibility");
            if (systemService != null) {
                return ((android.view.accessibility.AccessibilityManager) systemService).isEnabled();
            }
            throw new TypeCastException("null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        }
    }

    boolean isEnabled(@NotNull Context context);
}
