package com.google.android.play.core.assetpacks;

import java.io.File;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzcf {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("ExtractChunkTaskHandler");
    private final byte[] zzb = new byte[8192];
    private final zzbh zzc;
    private final com.google.android.play.core.internal.zzco zzd;
    private final com.google.android.play.core.internal.zzco zze;
    private final zzco zzf;
    private final zzeb zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcf(zzbh zzbhVar, com.google.android.play.core.internal.zzco zzcoVar, com.google.android.play.core.internal.zzco zzcoVar2, zzco zzcoVar3, zzeb zzebVar) {
        this.zzc = zzbhVar;
        this.zzd = zzcoVar;
        this.zze = zzcoVar2;
        this.zzf = zzcoVar3;
        this.zzg = zzebVar;
    }

    private final File zzb(zzce zzceVar) {
        File zzp = this.zzc.zzp(zzceVar.zzl, zzceVar.zza, zzceVar.zzb, zzceVar.zzd);
        if (!zzp.exists()) {
            zzp.mkdirs();
        }
        return zzp;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:11|(2:13|(12:15|(1:(1:(2:19|(1:21)(2:85|86))(2:87|88))(2:89|(10:91|(7:24|(4:25|(2:29|(1:38)(4:33|(1:35)|36|37))|39|(1:41)(1:65))|43|44|(1:46)|47|(2:49|(1:51)(2:52|(1:54)(3:55|(2:57|(1:59)(2:61|62))(1:64)|60))))|66|67|(2:79|80)|69|70|71|72|(2:74|75)(1:76))(2:92|93)))(2:94|(4:96|(4:97|(1:99)|100|(1:103)(1:111))|106|(3:108|109|110))(2:112|113))|22|(0)|66|67|(0)|69|70|71|72|(0)(0))(2:114|115))|116|(0)|66|67|(0)|69|70|71|72|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x02fa, code lost:
        com.google.android.play.core.assetpacks.zzcf.zza.zze("Could not close file for chunk %s of slice %s of pack %s.", java.lang.Integer.valueOf(r23.zzf), r23.zzd, r23.zzl);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x029e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:133:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0186 A[Catch: all -> 0x0292, TryCatch #2 {all -> 0x0292, blocks: (B:54:0x0186, B:55:0x018f, B:57:0x0199, B:59:0x019f, B:61:0x01a5, B:63:0x01ab, B:65:0x01cf, B:66:0x01db, B:67:0x01df, B:68:0x01e6, B:70:0x01ec, B:72:0x01f2, B:74:0x01f8, B:75:0x0208, B:77:0x020e, B:79:0x0214, B:80:0x0227, B:82:0x022d, B:83:0x023c, B:85:0x0242, B:91:0x0283, B:88:0x026a, B:89:0x0271, B:90:0x0272, B:47:0x0151, B:48:0x0156, B:49:0x0160, B:50:0x0161, B:51:0x0181), top: B:122:0x0043 }] */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.google.android.play.core.assetpacks.zzbh] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2, types: [java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zza(com.google.android.play.core.assetpacks.zzce r23) {
        /*
            Method dump skipped, instructions count: 900
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.zzcf.zza(com.google.android.play.core.assetpacks.zzce):void");
    }
}
