package com.google.android.play.core.internal;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzcq implements zzcs, zzco {
    private static final Object zza = new Object();
    private volatile zzcs zzb;
    private volatile Object zzc = zza;

    private zzcq(zzcs zzcsVar) {
        this.zzb = zzcsVar;
    }

    public static zzco zzb(zzcs zzcsVar) {
        if (zzcsVar instanceof zzco) {
            return (zzco) zzcsVar;
        }
        if (zzcsVar != null) {
            return new zzcq(zzcsVar);
        }
        throw null;
    }

    public static zzcs zzc(zzcs zzcsVar) {
        if (zzcsVar != null) {
            return zzcsVar instanceof zzcq ? zzcsVar : new zzcq(zzcsVar);
        }
        throw null;
    }

    @Override // com.google.android.play.core.internal.zzcs
    public final Object zza() {
        Object obj = this.zzc;
        if (obj == zza) {
            synchronized (this) {
                obj = this.zzc;
                if (obj == zza) {
                    obj = this.zzb.zza();
                    Object obj2 = this.zzc;
                    if (obj2 != zza && obj2 != obj) {
                        String valueOf = String.valueOf(obj2);
                        String valueOf2 = String.valueOf(obj);
                        StringBuilder sb = new StringBuilder(valueOf.length() + 118 + valueOf2.length());
                        sb.append("Scoped provider was invoked recursively returning different results: ");
                        sb.append(valueOf);
                        sb.append(" & ");
                        sb.append(valueOf2);
                        sb.append(". This is likely due to a circular dependency.");
                        throw new IllegalStateException(sb.toString());
                    }
                    this.zzc = obj;
                    this.zzb = null;
                }
            }
        }
        return obj;
    }
}
