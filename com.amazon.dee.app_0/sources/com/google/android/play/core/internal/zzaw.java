package com.google.android.play.core.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.play.core.splitcompat.SplitCompat;
import java.util.List;
import java.util.concurrent.Executor;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzaw implements com.google.android.play.core.splitinstall.zzh {
    private final Context zza;
    private final com.google.android.play.core.splitcompat.zze zzb;
    private final zzay zzc;
    private final Executor zzd;
    private final com.google.android.play.core.splitcompat.zzr zze;

    public zzaw(Context context, Executor executor, zzay zzayVar, com.google.android.play.core.splitcompat.zze zzeVar, com.google.android.play.core.splitcompat.zzr zzrVar, byte[] bArr) {
        this.zza = context;
        this.zzb = zzeVar;
        this.zzc = zzayVar;
        this.zzd = executor;
        this.zze = zzrVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzb(zzaw zzawVar, List list, com.google.android.play.core.splitinstall.zzf zzfVar) {
        Integer zze = zzawVar.zze(list);
        if (zze == null) {
            return;
        }
        if (zze.intValue() == 0) {
            zzfVar.zzc();
        } else {
            zzfVar.zzb(zze.intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzc(zzaw zzawVar, com.google.android.play.core.splitinstall.zzf zzfVar) {
        try {
            if (!SplitCompat.zzd(zzce.zza(zzawVar.zza))) {
                Log.e("SplitCompat", "Emulating splits failed.");
                zzfVar.zzb(-12);
                return;
            }
            Log.i("SplitCompat", "Splits installed.");
            zzfVar.zza();
        } catch (Exception e) {
            Log.e("SplitCompat", "Error emulating splits.", e);
            zzfVar.zzb(-12);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0125 A[Catch: Exception -> 0x0129, TRY_LEAVE, TryCatch #8 {Exception -> 0x0129, blocks: (B:3:0x0004, B:69:0x0125, B:5:0x0017, B:12:0x0025, B:13:0x002e, B:15:0x0034, B:17:0x005e, B:22:0x0071, B:24:0x007d, B:33:0x009c, B:40:0x00a9, B:20:0x006b, B:41:0x00aa, B:42:0x00af, B:43:0x00b9, B:45:0x00c1, B:47:0x00c9, B:48:0x00d7, B:50:0x00db, B:52:0x00ec, B:63:0x0115, B:54:0x00f4, B:55:0x00fa, B:57:0x0101, B:59:0x0108, B:61:0x010f), top: B:84:0x0004 }] */
    @androidx.annotation.Nullable
    @com.google.android.play.core.splitinstall.model.SplitInstallErrorCode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.Integer zze(java.util.List r14) {
        /*
            Method dump skipped, instructions count: 308
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.internal.zzaw.zze(java.util.List):java.lang.Integer");
    }

    @Override // com.google.android.play.core.splitinstall.zzh
    public final void zzd(List list, com.google.android.play.core.splitinstall.zzf zzfVar) {
        if (SplitCompat.zze()) {
            this.zzd.execute(new zzav(this, list, zzfVar));
            return;
        }
        throw new IllegalStateException("Ingestion should only be called in SplitCompat mode.");
    }
}
