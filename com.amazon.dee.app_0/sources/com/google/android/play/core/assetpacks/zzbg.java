package com.google.android.play.core.assetpacks;

import com.google.android.play.core.assetpacks.model.AssetPackStatus;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzbg {
    public static boolean zza(@AssetPackStatus int i) {
        return i == 1 || i == 7 || i == 2 || i == 3;
    }

    public static boolean zzb(@AssetPackStatus int i) {
        return i == 2 || i == 7 || i == 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzc(@AssetPackStatus int i, @AssetPackStatus int i2) {
        if (i == 5) {
            if (i2 != 5) {
                return true;
            }
            i = 5;
        }
        int i3 = 6;
        if (i != 6) {
            i3 = i;
        } else if (i2 != 6 && i2 != 5) {
            return true;
        }
        if (i3 != 4 || i2 == 4) {
            if (i3 == 3 && (i2 == 2 || i2 == 7 || i2 == 1 || i2 == 8)) {
                return true;
            }
            if (i3 != 2) {
                return false;
            }
            return i2 == 1 || i2 == 8;
        }
        return true;
    }

    public static boolean zzd(@AssetPackStatus int i) {
        return i == 5 || i == 6 || i == 4;
    }
}
