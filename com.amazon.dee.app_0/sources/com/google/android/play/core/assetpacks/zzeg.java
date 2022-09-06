package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzeg {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("PatchSliceTaskHandler");
    private final zzbh zzb;
    private final com.google.android.play.core.internal.zzco zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeg(zzbh zzbhVar, com.google.android.play.core.internal.zzco zzcoVar) {
        this.zzb = zzbhVar;
        this.zzc = zzcoVar;
    }

    public final void zza(zzef zzefVar) {
        File zzh = this.zzb.zzh(zzefVar.zzl, zzefVar.zza, zzefVar.zzb);
        File file = new File(this.zzb.zzi(zzefVar.zzl, zzefVar.zza, zzefVar.zzb), zzefVar.zzf);
        try {
            InputStream inputStream = zzefVar.zzh;
            if (zzefVar.zze == 2) {
                inputStream = new GZIPInputStream(inputStream, 8192);
            }
            zzbk zzbkVar = new zzbk(zzh, file);
            File zzp = this.zzb.zzp(zzefVar.zzl, zzefVar.zzc, zzefVar.zzd, zzefVar.zzf);
            if (!zzp.exists()) {
                zzp.mkdirs();
            }
            zzen zzenVar = new zzen(this.zzb, zzefVar.zzl, zzefVar.zzc, zzefVar.zzd, zzefVar.zzf);
            com.google.android.play.core.internal.zzcl.zza(zzbkVar, inputStream, new zzcn(zzp, zzenVar), zzefVar.zzg);
            zzenVar.zzi(0);
            inputStream.close();
            zza.zzd("Patching and extraction finished for slice %s of pack %s.", zzefVar.zzf, zzefVar.zzl);
            ((zzy) this.zzc.zza()).zzg(zzefVar.zzk, zzefVar.zzl, zzefVar.zzf, 0);
            try {
                zzefVar.zzh.close();
            } catch (IOException unused) {
                zza.zze("Could not close file for slice %s of pack %s.", zzefVar.zzf, zzefVar.zzl);
            }
        } catch (IOException e) {
            zza.zzb("IOException during patching %s.", e.getMessage());
            throw new zzck(String.format("Error patching slice %s of pack %s.", zzefVar.zzf, zzefVar.zzl), e, zzefVar.zzk);
        }
    }
}
