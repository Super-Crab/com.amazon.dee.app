package com.amazon.alexa;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Set;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
/* compiled from: NoGeolocationPermissionsChecker.kt */
/* loaded from: classes.dex */
public final class rGJ implements Dtt {
    public final Set<String> zQM;
    public final String zZm = Reflection.getOrCreateKotlinClass(rGJ.class).getSimpleName();
    public final String BIo = "android.permission.ACCESS_BACKGROUND_LOCATION";

    public rGJ() {
        Set<String> of;
        of = SetsKt__SetsKt.setOf((Object[]) new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", this.BIo});
        this.zQM = of;
    }

    @Override // com.amazon.alexa.Dtt
    public int zZm(@NotNull Context context, @NotNull String permission) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(permission, "permission");
        if (this.zQM.contains(permission)) {
            GeneratedOutlineSupport1.outline164("Denying request for a location permission: ", permission, this.zZm);
            return -1;
        }
        return ContextCompat.checkSelfPermission(context, permission);
    }
}
