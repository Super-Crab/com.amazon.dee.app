package com.google.android.datatransport.cct.a;

import androidx.annotation.NonNull;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
/* loaded from: classes2.dex */
public final class zze extends zzo {
    private final List<zzv> zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zze(List<zzv> list) {
        if (list != null) {
            this.zza = list;
            return;
        }
        throw new NullPointerException("Null logRequests");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzo)) {
            return false;
        }
        return this.zza.equals(((zzo) obj).zza());
    }

    public int hashCode() {
        return this.zza.hashCode() ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline95(GeneratedOutlineSupport1.outline107("BatchedLogRequest{logRequests="), this.zza, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    @Override // com.google.android.datatransport.cct.a.zzo
    @NonNull
    public List<zzv> zza() {
        return this.zza;
    }
}
