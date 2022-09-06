package com.google.android.play.core.splitcompat;

import androidx.annotation.NonNull;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzb extends zzs {
    private final File zza;
    private final String zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(File file, String str) {
        if (file != null) {
            this.zza = file;
            if (str != null) {
                this.zzb = str;
                return;
            }
            throw new NullPointerException("Null splitId");
        }
        throw new NullPointerException("Null splitFile");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzs) {
            zzs zzsVar = (zzs) obj;
            if (this.zza.equals(zzsVar.zza()) && this.zzb.equals(zzsVar.zzb())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        String obj = this.zza.toString();
        String str = this.zzb;
        StringBuilder sb = new StringBuilder(str.length() + obj.length() + 35);
        GeneratedOutlineSupport1.outline181(sb, "SplitFileInfo{splitFile=", obj, ", splitId=", str);
        sb.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.splitcompat.zzs
    @NonNull
    public final File zza() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.splitcompat.zzs
    @NonNull
    public final String zzb() {
        return this.zzb;
    }
}
