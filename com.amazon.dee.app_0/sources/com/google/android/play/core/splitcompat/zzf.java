package com.google.android.play.core.splitcompat;

import java.io.File;
import java.io.IOException;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzf implements zzk {
    final /* synthetic */ zzg zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(zzg zzgVar) {
        this.zza = zzgVar;
    }

    @Override // com.google.android.play.core.splitcompat.zzk
    public final void zza(zzl zzlVar, File file, boolean z) throws IOException {
        this.zza.zzb.add(file);
        if (!z) {
            this.zza.zzc.set(false);
        }
    }
}
