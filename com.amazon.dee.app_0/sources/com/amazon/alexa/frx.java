package com.amazon.alexa;

import android.content.Context;
import androidx.core.content.ContextCompat;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DefaultPermissionsChecker.kt */
/* loaded from: classes.dex */
public final class frx implements Dtt {
    @Override // com.amazon.alexa.Dtt
    public int zZm(@NotNull Context context, @NotNull String permission) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(permission, "permission");
        return ContextCompat.checkSelfPermission(context, permission);
    }
}
