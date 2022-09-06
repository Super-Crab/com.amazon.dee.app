package com.google.android.play.core.assetpacks;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzbq extends zzet {
    private final String zza;
    private final long zzb;
    private final int zzc;
    private final boolean zzd;
    private final boolean zze;
    private final byte[] zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbq(@Nullable String str, long j, int i, boolean z, boolean z2, @Nullable byte[] bArr) {
        this.zza = str;
        this.zzb = j;
        this.zzc = i;
        this.zzd = z;
        this.zze = z2;
        this.zzf = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzet) {
            zzet zzetVar = (zzet) obj;
            String str = this.zza;
            if (str != null ? str.equals(zzetVar.zzc()) : zzetVar.zzc() == null) {
                if (this.zzb == zzetVar.zzb() && this.zzc == zzetVar.zza() && this.zzd == zzetVar.zze() && this.zze == zzetVar.zzd()) {
                    if (Arrays.equals(this.zzf, zzetVar instanceof zzbq ? ((zzbq) zzetVar).zzf : zzetVar.zzf())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.zza;
        int hashCode = str == null ? 0 : str.hashCode();
        long j = this.zzb;
        int i = 1237;
        int i2 = (((((((hashCode ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.zzc) * 1000003) ^ (true != this.zzd ? 1237 : 1231)) * 1000003;
        if (true == this.zze) {
            i = 1231;
        }
        return ((i2 ^ i) * 1000003) ^ Arrays.hashCode(this.zzf);
    }

    public final String toString() {
        String str = this.zza;
        long j = this.zzb;
        int i = this.zzc;
        boolean z = this.zzd;
        boolean z2 = this.zze;
        String arrays = Arrays.toString(this.zzf);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 126 + String.valueOf(arrays).length());
        GeneratedOutlineSupport1.outline180(sb, "ZipEntry{name=", str, ", size=");
        sb.append(j);
        sb.append(", compressionMethod=");
        sb.append(i);
        sb.append(", isPartial=");
        sb.append(z);
        sb.append(", isEndOfArchive=");
        sb.append(z2);
        return GeneratedOutlineSupport1.outline92(sb, ", headerBytes=", arrays, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzet
    public final int zza() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzet
    public final long zzb() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzet
    @Nullable
    public final String zzc() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzet
    public final boolean zzd() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzet
    public final boolean zze() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.zzet
    @Nullable
    public final byte[] zzf() {
        return this.zzf;
    }
}
