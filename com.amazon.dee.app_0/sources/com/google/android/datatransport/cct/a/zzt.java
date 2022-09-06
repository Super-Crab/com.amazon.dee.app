package com.google.android.datatransport.cct.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.a.zzi;
import com.google.auto.value.AutoValue;
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
@AutoValue
/* loaded from: classes2.dex */
public abstract class zzt {

    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    @AutoValue.Builder
    /* loaded from: classes2.dex */
    public static abstract class zza {
        @NonNull
        public abstract zza zza(int i);

        @NonNull
        public abstract zza zza(long j);

        @NonNull
        public abstract zza zza(@Nullable zzy zzyVar);

        @NonNull
        abstract zza zza(@Nullable String str);

        @NonNull
        abstract zza zza(@Nullable byte[] bArr);

        @NonNull
        public abstract zzt zza();

        @NonNull
        public abstract zza zzb(long j);

        @NonNull
        public abstract zza zzc(long j);
    }

    @NonNull
    public static zza zza(@NonNull String str) {
        return new zzi.zza().zza(Integer.MIN_VALUE).zza(str);
    }

    public abstract long zza();

    public abstract long zzb();

    public abstract long zzc();

    @NonNull
    public static zza zza(@NonNull byte[] bArr) {
        return new zzi.zza().zza(Integer.MIN_VALUE).zza(bArr);
    }
}
