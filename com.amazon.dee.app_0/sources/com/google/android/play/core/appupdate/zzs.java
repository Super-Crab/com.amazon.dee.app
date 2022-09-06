package com.google.android.play.core.appupdate;

import android.content.Context;
import java.io.File;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzs {
    private final Context zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzs(Context context) {
        this.zza = context;
    }

    private static long zzb(File file) {
        if (!file.isDirectory()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        long j = 0;
        if (listFiles != null) {
            for (File file2 : listFiles) {
                j += zzb(file2);
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long zza() {
        return zzb(new File(this.zza.getFilesDir(), "assetpacks"));
    }
}
