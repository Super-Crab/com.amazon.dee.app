package com.google.android.datatransport.cct.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.a.zzg;
import com.google.auto.value.AutoValue;
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
@AutoValue
/* loaded from: classes2.dex */
public abstract class zzq {

    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    @AutoValue.Builder
    /* loaded from: classes2.dex */
    public static abstract class zza {
        @NonNull
        public abstract zza zza(@Nullable com.google.android.datatransport.cct.a.zza zzaVar);

        @NonNull
        public abstract zza zza(@Nullable zzb zzbVar);

        @NonNull
        public abstract zzq zza();
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    /* loaded from: classes2.dex */
    public static final class zzb extends Enum<zzb> {
        public static final zzb zza = new zzb("UNKNOWN", 0, 0);
        public static final zzb zzb = new zzb("ANDROID", 1, 4);

        static {
            zzb[] zzbVarArr = {zza, zzb};
        }

        private zzb(String str, int i, int i2) {
        }
    }

    @NonNull
    public static zza zza() {
        return new zzg.zza();
    }
}
