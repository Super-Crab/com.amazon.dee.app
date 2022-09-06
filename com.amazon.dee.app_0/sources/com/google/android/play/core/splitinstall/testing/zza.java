package com.google.android.play.core.splitinstall.testing;

import java.util.Map;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zza extends zzs {
    private Integer zza;
    private Map zzb;

    @Override // com.google.android.play.core.splitinstall.testing.zzs
    final zzs zza(int i) {
        this.zza = Integer.valueOf(i);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.splitinstall.testing.zzs
    public final zzs zzb(Map map) {
        if (map != null) {
            this.zzb = map;
            return this;
        }
        throw new NullPointerException("Null splitInstallErrorCodeByModule");
    }

    @Override // com.google.android.play.core.splitinstall.testing.zzs
    final zzt zzc() {
        Map map = this.zzb;
        if (map != null) {
            return new zzc(this.zza, map, null);
        }
        throw new IllegalStateException("Missing required properties: splitInstallErrorCodeByModule");
    }

    @Override // com.google.android.play.core.splitinstall.testing.zzs
    final Map zzd() {
        Map map = this.zzb;
        if (map != null) {
            return map;
        }
        throw new IllegalStateException("Property \"splitInstallErrorCodeByModule\" has not been set");
    }
}
