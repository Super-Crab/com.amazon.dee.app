package com.google.android.play.core.splitinstall.testing;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
import java.util.Map;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzc extends zzt {
    private final Integer zzb;
    private final Map zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzc(Integer num, Map map, zzb zzbVar) {
        this.zzb = num;
        this.zzc = map;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzt) {
            zzt zztVar = (zzt) obj;
            Integer num = this.zzb;
            if (num != null ? num.equals(zztVar.zza()) : zztVar.zza() == null) {
                if (this.zzc.equals(zztVar.zzb())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        Integer num = this.zzb;
        return (((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003) ^ this.zzc.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzb);
        String valueOf2 = String.valueOf(this.zzc);
        StringBuilder sb = new StringBuilder(valueOf.length() + 81 + valueOf2.length());
        GeneratedOutlineSupport1.outline181(sb, "LocalTestingConfig{defaultSplitInstallErrorCode=", valueOf, ", splitInstallErrorCodeByModule=", valueOf2);
        sb.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return sb.toString();
    }

    @Override // com.google.android.play.core.splitinstall.testing.zzt
    @Nullable
    @SplitInstallErrorCode
    public final Integer zza() {
        return this.zzb;
    }

    @Override // com.google.android.play.core.splitinstall.testing.zzt
    public final Map zzb() {
        return this.zzc;
    }
}
