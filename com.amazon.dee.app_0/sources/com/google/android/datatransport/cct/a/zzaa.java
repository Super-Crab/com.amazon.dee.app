package com.google.android.datatransport.cct.a;

import android.util.SparseArray;
import com.amazon.alexa.redesign.view.carousel.ChipIconTitleViewHolder;
/* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
/* loaded from: classes2.dex */
public final class zzaa extends Enum<zzaa> {
    public static final zzaa zza = new zzaa(ChipIconTitleViewHolder.STATE_DEFAULT, 0, 0);
    public static final zzaa zzb = new zzaa("UNMETERED_ONLY", 1, 1);
    public static final zzaa zzc = new zzaa("UNMETERED_OR_DAILY", 2, 2);
    public static final zzaa zzd = new zzaa("FAST_IF_RADIO_AWAKE", 3, 3);
    public static final zzaa zze = new zzaa("NEVER", 4, 4);
    public static final zzaa zzf = new zzaa("UNRECOGNIZED", 5, -1);
    private static final SparseArray<zzaa> zzg;

    static {
        zzaa[] zzaaVarArr = {zza, zzb, zzc, zzd, zze, zzf};
        zzg = new SparseArray<>();
        zzg.put(0, zza);
        zzg.put(1, zzb);
        zzg.put(2, zzc);
        zzg.put(3, zzd);
        zzg.put(4, zze);
        zzg.put(-1, zzf);
    }

    private zzaa(String str, int i, int i2) {
    }
}
