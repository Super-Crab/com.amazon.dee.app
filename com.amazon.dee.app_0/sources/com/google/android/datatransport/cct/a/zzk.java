package com.google.android.datatransport.cct.a;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.datatransport.cct.a.zzv;
import java.util.List;
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
/* loaded from: classes2.dex */
final class zzk extends zzv {
    private final long zza;
    private final long zzb;
    private final zzq zzc;
    private final int zzd;
    private final String zze;
    private final List<zzt> zzf;
    private final zzaa zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    /* loaded from: classes2.dex */
    public static final class zza extends zzv.zza {
        private Long zza;
        private Long zzb;
        private zzq zzc;
        private Integer zzd;
        private String zze;
        private List<zzt> zzf;
        private zzaa zzg;

        @Override // com.google.android.datatransport.cct.a.zzv.zza
        public zzv.zza zza(long j) {
            this.zza = Long.valueOf(j);
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzv.zza
        public zzv.zza zzb(long j) {
            this.zzb = Long.valueOf(j);
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzv.zza
        public zzv.zza zza(@Nullable zzq zzqVar) {
            this.zzc = zzqVar;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.datatransport.cct.a.zzv.zza
        public zzv.zza zza(int i) {
            this.zzd = Integer.valueOf(i);
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzv.zza
        zzv.zza zza(@Nullable String str) {
            this.zze = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzv.zza
        public zzv.zza zza(@Nullable List<zzt> list) {
            this.zzf = list;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzv.zza
        public zzv.zza zza(@Nullable zzaa zzaaVar) {
            this.zzg = zzaaVar;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzv.zza
        public zzv zza() {
            String str = "";
            if (this.zza == null) {
                str = GeneratedOutlineSupport1.outline72(str, " requestTimeMs");
            }
            if (this.zzb == null) {
                str = GeneratedOutlineSupport1.outline72(str, " requestUptimeMs");
            }
            if (this.zzd == null) {
                str = GeneratedOutlineSupport1.outline72(str, " logSource");
            }
            if (str.isEmpty()) {
                return new zzk(this.zza.longValue(), this.zzb.longValue(), this.zzc, this.zzd.intValue(), this.zze, this.zzf, this.zzg, null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }
    }

    /* synthetic */ zzk(long j, long j2, zzq zzqVar, int i, String str, List list, zzaa zzaaVar, zzj zzjVar) {
        this.zza = j;
        this.zzb = j2;
        this.zzc = zzqVar;
        this.zzd = i;
        this.zze = str;
        this.zzf = list;
        this.zzg = zzaaVar;
    }

    public boolean equals(Object obj) {
        zzq zzqVar;
        String str;
        List<zzt> list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzv)) {
            return false;
        }
        zzk zzkVar = (zzk) obj;
        if (this.zza == zzkVar.zza && this.zzb == zzkVar.zzb && ((zzqVar = this.zzc) != null ? zzqVar.equals(zzkVar.zzc) : zzkVar.zzc == null) && this.zzd == zzkVar.zzd && ((str = this.zze) != null ? str.equals(zzkVar.zze) : zzkVar.zze == null) && ((list = this.zzf) != null ? list.equals(zzkVar.zzf) : zzkVar.zzf == null)) {
            zzaa zzaaVar = this.zzg;
            if (zzaaVar == null) {
                if (zzkVar.zzg == null) {
                    return true;
                }
            } else if (zzaaVar.equals(zzkVar.zzg)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.zza;
        long j2 = this.zzb;
        int i = (((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003;
        zzq zzqVar = this.zzc;
        int i2 = 0;
        int hashCode = (((i ^ (zzqVar == null ? 0 : zzqVar.hashCode())) * 1000003) ^ this.zzd) * 1000003;
        String str = this.zze;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        List<zzt> list = this.zzf;
        int hashCode3 = (hashCode2 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        zzaa zzaaVar = this.zzg;
        if (zzaaVar != null) {
            i2 = zzaaVar.hashCode();
        }
        return hashCode3 ^ i2;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LogRequest{requestTimeMs=");
        outline107.append(this.zza);
        outline107.append(", requestUptimeMs=");
        outline107.append(this.zzb);
        outline107.append(", clientInfo=");
        outline107.append(this.zzc);
        outline107.append(", logSource=");
        outline107.append(this.zzd);
        outline107.append(", logSourceName=");
        outline107.append(this.zze);
        outline107.append(", logEvents=");
        outline107.append(this.zzf);
        outline107.append(", qosTier=");
        outline107.append(this.zzg);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    @Nullable
    public zzq zzb() {
        return this.zzc;
    }

    @Nullable
    public List<zzt> zzc() {
        return this.zzf;
    }

    public int zzd() {
        return this.zzd;
    }

    @Nullable
    public String zze() {
        return this.zze;
    }

    public long zzf() {
        return this.zza;
    }

    public long zzg() {
        return this.zzb;
    }
}
