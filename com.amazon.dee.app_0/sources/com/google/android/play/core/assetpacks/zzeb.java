package com.google.android.play.core.assetpacks;

import androidx.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzeb {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("PackMetadataManager");
    private final zzbh zzb;
    private final zzed zzc;
    private final com.google.android.play.core.common.zza zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeb(zzbh zzbhVar, zzed zzedVar, com.google.android.play.core.common.zza zzaVar) {
        this.zzb = zzbhVar;
        this.zzc = zzedVar;
        this.zzd = zzaVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zza(String str) {
        if (this.zzd.zza("assetOnlyUpdates") && this.zzb.zzG(str)) {
            int zza2 = this.zzc.zza();
            zzbh zzbhVar = this.zzb;
            File zzk = zzbhVar.zzk(str, zza2, zzbhVar.zzc(str));
            try {
                if (!zzk.exists()) {
                    return String.valueOf(zza2);
                }
                FileInputStream fileInputStream = new FileInputStream(zzk);
                Properties properties = new Properties();
                properties.load(fileInputStream);
                fileInputStream.close();
                String property = properties.getProperty("moduleVersionTag");
                return property == null ? String.valueOf(zza2) : property;
            } catch (IOException unused) {
                zza.zzb("Failed to read pack version tag for pack %s", str);
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(String str, int i, long j, @Nullable String str2) throws IOException {
        if (str2 == null || str2.isEmpty()) {
            str2 = String.valueOf(i);
        }
        Properties properties = new Properties();
        properties.put("moduleVersionTag", str2);
        File zzk = this.zzb.zzk(str, i, j);
        zzk.getParentFile().mkdirs();
        zzk.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(zzk);
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }
}
