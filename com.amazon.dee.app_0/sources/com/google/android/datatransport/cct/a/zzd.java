package com.google.android.datatransport.cct.a;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.datatransport.cct.a.zza;
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
/* loaded from: classes2.dex */
final class zzd extends com.google.android.datatransport.cct.a.zza {
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    /* loaded from: classes2.dex */
    public static final class zza extends zza.AbstractC0089zza {
        private Integer zza;
        private String zzb;
        private String zzc;
        private String zzd;
        private String zze;
        private String zzf;
        private String zzg;
        private String zzh;

        @Override // com.google.android.datatransport.cct.a.zza.AbstractC0089zza
        public zza.AbstractC0089zza zza(int i) {
            this.zza = Integer.valueOf(i);
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zza.AbstractC0089zza
        public zza.AbstractC0089zza zzb(@Nullable String str) {
            this.zzh = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zza.AbstractC0089zza
        public zza.AbstractC0089zza zzc(@Nullable String str) {
            this.zzc = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zza.AbstractC0089zza
        public zza.AbstractC0089zza zzd(@Nullable String str) {
            this.zzg = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zza.AbstractC0089zza
        public zza.AbstractC0089zza zze(@Nullable String str) {
            this.zzb = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zza.AbstractC0089zza
        public zza.AbstractC0089zza zzf(@Nullable String str) {
            this.zzf = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zza.AbstractC0089zza
        public zza.AbstractC0089zza zzg(@Nullable String str) {
            this.zze = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zza.AbstractC0089zza
        public zza.AbstractC0089zza zza(@Nullable String str) {
            this.zzd = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zza.AbstractC0089zza
        public com.google.android.datatransport.cct.a.zza zza() {
            String str = "";
            if (this.zza == null) {
                str = GeneratedOutlineSupport1.outline72(str, " sdkVersion");
            }
            if (str.isEmpty()) {
                return new zzd(this.zza.intValue(), this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }
    }

    /* synthetic */ zzd(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, zzc zzcVar) {
        this.zza = i;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = str4;
        this.zzf = str5;
        this.zzg = str6;
        this.zzh = str7;
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof com.google.android.datatransport.cct.a.zza)) {
            return false;
        }
        zzd zzdVar = (zzd) obj;
        if (this.zza == zzdVar.zza && ((str = this.zzb) != null ? str.equals(zzdVar.zzb) : zzdVar.zzb == null) && ((str2 = this.zzc) != null ? str2.equals(zzdVar.zzc) : zzdVar.zzc == null) && ((str3 = this.zzd) != null ? str3.equals(zzdVar.zzd) : zzdVar.zzd == null) && ((str4 = this.zze) != null ? str4.equals(zzdVar.zze) : zzdVar.zze == null) && ((str5 = this.zzf) != null ? str5.equals(zzdVar.zzf) : zzdVar.zzf == null) && ((str6 = this.zzg) != null ? str6.equals(zzdVar.zzg) : zzdVar.zzg == null)) {
            String str7 = this.zzh;
            if (str7 == null) {
                if (zzdVar.zzh == null) {
                    return true;
                }
            } else if (str7.equals(zzdVar.zzh)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = (this.zza ^ 1000003) * 1000003;
        String str = this.zzb;
        int i2 = 0;
        int hashCode = (i ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.zzc;
        int hashCode2 = (hashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.zzd;
        int hashCode3 = (hashCode2 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.zze;
        int hashCode4 = (hashCode3 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        String str5 = this.zzf;
        int hashCode5 = (hashCode4 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        String str6 = this.zzg;
        int hashCode6 = (hashCode5 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        String str7 = this.zzh;
        if (str7 != null) {
            i2 = str7.hashCode();
        }
        return hashCode6 ^ i2;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AndroidClientInfo{sdkVersion=");
        outline107.append(this.zza);
        outline107.append(", model=");
        outline107.append(this.zzb);
        outline107.append(", hardware=");
        outline107.append(this.zzc);
        outline107.append(", device=");
        outline107.append(this.zzd);
        outline107.append(", product=");
        outline107.append(this.zze);
        outline107.append(", osBuild=");
        outline107.append(this.zzf);
        outline107.append(", manufacturer=");
        outline107.append(this.zzg);
        outline107.append(", fingerprint=");
        return GeneratedOutlineSupport1.outline91(outline107, this.zzh, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    @Nullable
    public String zzb() {
        return this.zzd;
    }

    @Nullable
    public String zzc() {
        return this.zzh;
    }

    @Nullable
    public String zzd() {
        return this.zzc;
    }

    @Nullable
    public String zze() {
        return this.zzg;
    }

    @Nullable
    public String zzf() {
        return this.zzb;
    }

    @Nullable
    public String zzg() {
        return this.zzf;
    }

    @Nullable
    public String zzh() {
        return this.zze;
    }

    public int zzi() {
        return this.zza;
    }
}
