package com.google.android.play.core.splitinstall.testing;

import androidx.annotation.Nullable;
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public abstract class zzt {
    public static final zzt zza = zzc().zze();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzs zzc() {
        zza zzaVar = new zza();
        zzaVar.zzb(new HashMap());
        return zzaVar;
    }

    @Nullable
    @SplitInstallErrorCode
    public abstract Integer zza();

    public abstract Map zzb();
}
