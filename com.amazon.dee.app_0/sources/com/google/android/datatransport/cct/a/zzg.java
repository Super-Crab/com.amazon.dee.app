package com.google.android.datatransport.cct.a;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.datatransport.cct.a.zzq;
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
/* loaded from: classes2.dex */
final class zzg extends zzq {
    private final zzq.zzb zza;
    private final com.google.android.datatransport.cct.a.zza zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    /* loaded from: classes2.dex */
    public static final class zza extends zzq.zza {
        private zzq.zzb zza;
        private com.google.android.datatransport.cct.a.zza zzb;

        @Override // com.google.android.datatransport.cct.a.zzq.zza
        public zzq.zza zza(@Nullable zzq.zzb zzbVar) {
            this.zza = zzbVar;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzq.zza
        public zzq.zza zza(@Nullable com.google.android.datatransport.cct.a.zza zzaVar) {
            this.zzb = zzaVar;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzq.zza
        public zzq zza() {
            return new zzg(this.zza, this.zzb, null);
        }
    }

    /* synthetic */ zzg(zzq.zzb zzbVar, com.google.android.datatransport.cct.a.zza zzaVar, zzf zzfVar) {
        this.zza = zzbVar;
        this.zzb = zzaVar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzq)) {
            return false;
        }
        zzq.zzb zzbVar = this.zza;
        if (zzbVar != null ? zzbVar.equals(((zzg) obj).zza) : ((zzg) obj).zza == null) {
            com.google.android.datatransport.cct.a.zza zzaVar = this.zzb;
            if (zzaVar == null) {
                if (((zzg) obj).zzb == null) {
                    return true;
                }
            } else if (zzaVar.equals(((zzg) obj).zzb)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        zzq.zzb zzbVar = this.zza;
        int i = 0;
        int hashCode = ((zzbVar == null ? 0 : zzbVar.hashCode()) ^ 1000003) * 1000003;
        com.google.android.datatransport.cct.a.zza zzaVar = this.zzb;
        if (zzaVar != null) {
            i = zzaVar.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ClientInfo{clientType=");
        outline107.append(this.zza);
        outline107.append(", androidClientInfo=");
        outline107.append(this.zzb);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    @Nullable
    public com.google.android.datatransport.cct.a.zza zzb() {
        return this.zzb;
    }

    @Nullable
    public zzq.zzb zzc() {
        return this.zza;
    }
}
