package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzp {
    private final Context zza;

    public zzp(Context context) {
        this.zza = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static String zzb(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                return bundle.getString("local_testing_dir");
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Context zza() {
        return this.zza;
    }
}
