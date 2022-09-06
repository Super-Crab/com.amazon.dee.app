package com.google.android.datatransport.cct.a;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.datatransport.cct.a.zzy;
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
/* loaded from: classes2.dex */
final class zzn extends zzy {
    private final zzy.zzc zza;
    private final zzy.zzb zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    /* loaded from: classes2.dex */
    public static final class zza extends zzy.zza {
        private zzy.zzc zza;
        private zzy.zzb zzb;

        @Override // com.google.android.datatransport.cct.a.zzy.zza
        public zzy.zza zza(@Nullable zzy.zzc zzcVar) {
            this.zza = zzcVar;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzy.zza
        public zzy.zza zza(@Nullable zzy.zzb zzbVar) {
            this.zzb = zzbVar;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzy.zza
        public zzy zza() {
            return new zzn(this.zza, this.zzb, null);
        }
    }

    /* synthetic */ zzn(zzy.zzc zzcVar, zzy.zzb zzbVar, zzm zzmVar) {
        this.zza = zzcVar;
        this.zzb = zzbVar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzy)) {
            return false;
        }
        zzy.zzc zzcVar = this.zza;
        if (zzcVar != null ? zzcVar.equals(((zzn) obj).zza) : ((zzn) obj).zza == null) {
            zzy.zzb zzbVar = this.zzb;
            if (zzbVar == null) {
                if (((zzn) obj).zzb == null) {
                    return true;
                }
            } else if (zzbVar.equals(((zzn) obj).zzb)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        zzy.zzc zzcVar = this.zza;
        int i = 0;
        int hashCode = ((zzcVar == null ? 0 : zzcVar.hashCode()) ^ 1000003) * 1000003;
        zzy.zzb zzbVar = this.zzb;
        if (zzbVar != null) {
            i = zzbVar.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NetworkConnectionInfo{networkType=");
        outline107.append(this.zza);
        outline107.append(", mobileSubtype=");
        outline107.append(this.zzb);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    @Nullable
    public zzy.zzb zzb() {
        return this.zzb;
    }

    @Nullable
    public zzy.zzc zzc() {
        return this.zza;
    }
}
