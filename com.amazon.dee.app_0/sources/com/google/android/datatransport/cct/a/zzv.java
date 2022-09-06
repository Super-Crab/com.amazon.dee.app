package com.google.android.datatransport.cct.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.a.zzk;
import com.google.auto.value.AutoValue;
import java.util.List;
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
@AutoValue
/* loaded from: classes2.dex */
public abstract class zzv {

    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    @AutoValue.Builder
    /* loaded from: classes2.dex */
    public static abstract class zza {
        @NonNull
        abstract zza zza(int i);

        @NonNull
        public abstract zza zza(long j);

        @NonNull
        public abstract zza zza(@Nullable zzaa zzaaVar);

        @NonNull
        public abstract zza zza(@Nullable zzq zzqVar);

        @NonNull
        abstract zza zza(@Nullable String str);

        @NonNull
        public abstract zza zza(@Nullable List<zzt> list);

        @NonNull
        public abstract zzv zza();

        @NonNull
        public zza zzb(int i) {
            return zza(i);
        }

        @NonNull
        public abstract zza zzb(long j);

        @NonNull
        public zza zzb(@NonNull String str) {
            return zza(str);
        }
    }

    @NonNull
    public static zza zza() {
        return new zzk.zza().zza(Integer.MIN_VALUE);
    }
}
