package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzer {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("VerifySliceTaskHandler");
    private final zzbh zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzer(zzbh zzbhVar) {
        this.zzb = zzbhVar;
    }

    private final void zzb(zzeq zzeqVar, File file) {
        try {
            File zzo = this.zzb.zzo(zzeqVar.zzl, zzeqVar.zza, zzeqVar.zzb, zzeqVar.zzc);
            if (zzo.exists()) {
                try {
                    if (zzdq.zza(zzep.zza(file, zzo)).equals(zzeqVar.zzd)) {
                        zza.zzd("Verification of slice %s of pack %s successful.", zzeqVar.zzc, zzeqVar.zzl);
                        return;
                    }
                    throw new zzck(String.format("Verification failed for slice %s.", zzeqVar.zzc), zzeqVar.zzk);
                } catch (IOException e) {
                    throw new zzck(String.format("Could not digest file during verification for slice %s.", zzeqVar.zzc), e, zzeqVar.zzk);
                } catch (NoSuchAlgorithmException e2) {
                    throw new zzck("SHA256 algorithm not supported.", e2, zzeqVar.zzk);
                }
            }
            throw new zzck(String.format("Cannot find metadata files for slice %s.", zzeqVar.zzc), zzeqVar.zzk);
        } catch (IOException e3) {
            throw new zzck(String.format("Could not reconstruct slice archive during verification for slice %s.", zzeqVar.zzc), e3, zzeqVar.zzk);
        }
    }

    public final void zza(zzeq zzeqVar) {
        File zzp = this.zzb.zzp(zzeqVar.zzl, zzeqVar.zza, zzeqVar.zzb, zzeqVar.zzc);
        if (zzp.exists()) {
            zzb(zzeqVar, zzp);
            File zzq = this.zzb.zzq(zzeqVar.zzl, zzeqVar.zza, zzeqVar.zzb, zzeqVar.zzc);
            if (!zzq.exists()) {
                zzq.mkdirs();
            }
            if (!zzp.renameTo(zzq)) {
                throw new zzck(String.format("Failed to move slice %s after verification.", zzeqVar.zzc), zzeqVar.zzk);
            }
            return;
        }
        throw new zzck(String.format("Cannot find unverified files for slice %s.", zzeqVar.zzc), zzeqVar.zzk);
    }
}
