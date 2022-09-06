package com.google.android.play.core.splitcompat;

import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzo implements com.google.android.play.core.splitinstall.zzq {
    final /* synthetic */ SplitCompat zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(SplitCompat splitCompat) {
        this.zza = splitCompat;
    }

    @Override // com.google.android.play.core.splitinstall.zzq
    public final Set zza() {
        Set zzf;
        zzf = this.zza.zzf();
        return zzf;
    }
}
