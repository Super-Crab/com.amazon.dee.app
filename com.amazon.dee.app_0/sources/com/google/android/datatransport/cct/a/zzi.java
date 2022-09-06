package com.google.android.datatransport.cct.a;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.datatransport.cct.a.zzt;
import java.util.Arrays;
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
/* loaded from: classes2.dex */
final class zzi extends zzt {
    private final long zza;
    private final int zzb;
    private final long zzc;
    private final byte[] zzd;
    private final String zze;
    private final long zzf;
    private final zzy zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    /* loaded from: classes2.dex */
    public static final class zza extends zzt.zza {
        private Long zza;
        private Integer zzb;
        private Long zzc;
        private byte[] zzd;
        private String zze;
        private Long zzf;
        private zzy zzg;

        @Override // com.google.android.datatransport.cct.a.zzt.zza
        public zzt.zza zza(long j) {
            this.zza = Long.valueOf(j);
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzt.zza
        public zzt.zza zzb(long j) {
            this.zzc = Long.valueOf(j);
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzt.zza
        public zzt.zza zzc(long j) {
            this.zzf = Long.valueOf(j);
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzt.zza
        public zzt.zza zza(int i) {
            this.zzb = Integer.valueOf(i);
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzt.zza
        zzt.zza zza(@Nullable byte[] bArr) {
            this.zzd = bArr;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzt.zza
        zzt.zza zza(@Nullable String str) {
            this.zze = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzt.zza
        public zzt.zza zza(@Nullable zzy zzyVar) {
            this.zzg = zzyVar;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzt.zza
        public zzt zza() {
            String str = "";
            if (this.zza == null) {
                str = GeneratedOutlineSupport1.outline72(str, " eventTimeMs");
            }
            if (this.zzb == null) {
                str = GeneratedOutlineSupport1.outline72(str, " eventCode");
            }
            if (this.zzc == null) {
                str = GeneratedOutlineSupport1.outline72(str, " eventUptimeMs");
            }
            if (this.zzf == null) {
                str = GeneratedOutlineSupport1.outline72(str, " timezoneOffsetSeconds");
            }
            if (str.isEmpty()) {
                return new zzi(this.zza.longValue(), this.zzb.intValue(), this.zzc.longValue(), this.zzd, this.zze, this.zzf.longValue(), this.zzg, null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }
    }

    /* synthetic */ zzi(long j, int i, long j2, byte[] bArr, String str, long j3, zzy zzyVar, zzh zzhVar) {
        this.zza = j;
        this.zzb = i;
        this.zzc = j2;
        this.zzd = bArr;
        this.zze = str;
        this.zzf = j3;
        this.zzg = zzyVar;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzt)) {
            return false;
        }
        zzt zztVar = (zzt) obj;
        if (this.zza == zztVar.zza()) {
            zzi zziVar = (zzi) zztVar;
            if (this.zzb == zziVar.zzb && this.zzc == zztVar.zzb()) {
                if (Arrays.equals(this.zzd, zztVar instanceof zzi ? zziVar.zzd : zziVar.zzd) && ((str = this.zze) != null ? str.equals(zziVar.zze) : zziVar.zze == null) && this.zzf == zztVar.zzc()) {
                    zzy zzyVar = this.zzg;
                    if (zzyVar == null) {
                        if (zziVar.zzg == null) {
                            return true;
                        }
                    } else if (zzyVar.equals(zziVar.zzg)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.zza;
        long j2 = this.zzc;
        int hashCode = (((((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.zzb) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ Arrays.hashCode(this.zzd)) * 1000003;
        String str = this.zze;
        int i = 0;
        int hashCode2 = str == null ? 0 : str.hashCode();
        long j3 = this.zzf;
        int i2 = (((hashCode ^ hashCode2) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003;
        zzy zzyVar = this.zzg;
        if (zzyVar != null) {
            i = zzyVar.hashCode();
        }
        return i2 ^ i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LogEvent{eventTimeMs=");
        outline107.append(this.zza);
        outline107.append(", eventCode=");
        outline107.append(this.zzb);
        outline107.append(", eventUptimeMs=");
        outline107.append(this.zzc);
        outline107.append(", sourceExtension=");
        outline107.append(Arrays.toString(this.zzd));
        outline107.append(", sourceExtensionJsonProto3=");
        outline107.append(this.zze);
        outline107.append(", timezoneOffsetSeconds=");
        outline107.append(this.zzf);
        outline107.append(", networkConnectionInfo=");
        outline107.append(this.zzg);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    @Override // com.google.android.datatransport.cct.a.zzt
    public long zza() {
        return this.zza;
    }

    @Override // com.google.android.datatransport.cct.a.zzt
    public long zzb() {
        return this.zzc;
    }

    @Override // com.google.android.datatransport.cct.a.zzt
    public long zzc() {
        return this.zzf;
    }

    public int zzd() {
        return this.zzb;
    }

    @Nullable
    public zzy zze() {
        return this.zzg;
    }

    @Nullable
    public byte[] zzf() {
        return this.zzd;
    }

    @Nullable
    public String zzg() {
        return this.zze;
    }
}
