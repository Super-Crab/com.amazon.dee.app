package com.google.android.datatransport.cct.a;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
/* loaded from: classes2.dex */
public final class zzl extends zzx {
    private final long zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(long j) {
        this.zza = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof zzx) && this.zza == ((zzx) obj).zza();
    }

    public int hashCode() {
        long j = this.zza;
        return ((int) (j ^ (j >>> 32))) ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline87(GeneratedOutlineSupport1.outline107("LogResponse{nextRequestWaitMillis="), this.zza, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    @Override // com.google.android.datatransport.cct.a.zzx
    public long zza() {
        return this.zza;
    }
}
