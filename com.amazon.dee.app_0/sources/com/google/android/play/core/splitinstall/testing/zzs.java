package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
import java.util.Collections;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public abstract class zzs {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract zzs zza(@SplitInstallErrorCode int i);

    abstract zzs zzb(Map map);

    abstract zzt zzc();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Map zzd();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzt zze() {
        zzb(Collections.unmodifiableMap(zzd()));
        return zzc();
    }
}
