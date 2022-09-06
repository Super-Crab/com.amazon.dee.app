package com.google.android.play.core.assetpacks;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzbl extends AssetLocation {
    private final String zza;
    private final long zzb;
    private final long zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbl(String str, long j, long j2) {
        if (str != null) {
            this.zza = str;
            this.zzb = j;
            this.zzc = j2;
            return;
        }
        throw new NullPointerException("Null path");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetLocation) {
            AssetLocation assetLocation = (AssetLocation) obj;
            if (this.zza.equals(assetLocation.path()) && this.zzb == assetLocation.offset() && this.zzc == assetLocation.size()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.zza.hashCode();
        long j = this.zzb;
        long j2 = this.zzc;
        return ((((hashCode ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2));
    }

    @Override // com.google.android.play.core.assetpacks.AssetLocation
    public final long offset() {
        return this.zzb;
    }

    @Override // com.google.android.play.core.assetpacks.AssetLocation
    public final String path() {
        return this.zza;
    }

    @Override // com.google.android.play.core.assetpacks.AssetLocation
    public final long size() {
        return this.zzc;
    }

    public final String toString() {
        String str = this.zza;
        long j = this.zzb;
        long j2 = this.zzc;
        StringBuilder sb = new StringBuilder(str.length() + 76);
        GeneratedOutlineSupport1.outline180(sb, "AssetLocation{path=", str, ", offset=");
        sb.append(j);
        sb.append(", size=");
        sb.append(j2);
        sb.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return sb.toString();
    }
}
