package com.google.android.play.core.splitcompat;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzi implements zzk {
    final /* synthetic */ Set zza;
    final /* synthetic */ zzs zzb;
    final /* synthetic */ ZipFile zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzi(zzm zzmVar, Set set, zzs zzsVar, ZipFile zipFile) {
        this.zza = set;
        this.zzb = zzsVar;
        this.zzc = zipFile;
    }

    @Override // com.google.android.play.core.splitcompat.zzk
    public final void zza(zzl zzlVar, File file, boolean z) throws IOException {
        this.zza.add(file);
        if (!z) {
            Log.i("SplitCompat", String.format("NativeLibraryExtractor: split '%s' has native library '%s' that does not exist; extracting from '%s!%s' to '%s'", this.zzb.zzb(), zzlVar.zza, this.zzb.zza().getAbsolutePath(), zzlVar.zzb.getName(), file.getAbsolutePath()));
            ZipFile zipFile = this.zzc;
            ZipEntry zipEntry = zzlVar.zzb;
            byte[] bArr = new byte[4096];
            if (file.exists()) {
                file.delete();
            }
            InputStream inputStream = zipFile.getInputStream(zipEntry);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                zze.zzm(file);
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read > 0) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.close();
                        inputStream.close();
                        return;
                    }
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        }
    }
}
