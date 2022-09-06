package com.google.android.play.core.assetpacks;

import java.util.List;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzao extends zzal {
    final /* synthetic */ zzaw zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzao(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar) {
        super(zzawVar, zziVar);
        this.zzc = zzawVar;
    }

    @Override // com.google.android.play.core.assetpacks.zzal, com.google.android.play.core.internal.zzw
    public final void zzg(List list) {
        super.zzg(list);
        this.zza.zze(zzaw.zzw(this.zzc, list));
    }
}
