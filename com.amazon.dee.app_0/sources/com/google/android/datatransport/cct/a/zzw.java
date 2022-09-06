package com.google.android.datatransport.cct.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
/* loaded from: classes2.dex */
public final class zzw implements ObjectEncoder<zzk> {
    @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
    public void encode(@Nullable Object obj, @NonNull ObjectEncoderContext objectEncoderContext) throws EncodingException, IOException {
        zzk zzkVar = (zzk) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.mo8299add("requestTimeMs", zzkVar.zzf()).mo8299add("requestUptimeMs", zzkVar.zzg());
        if (zzkVar.zzb() != null) {
            objectEncoderContext2.mo8300add("clientInfo", zzkVar.zzb());
        }
        if (zzkVar.zze() != null) {
            objectEncoderContext2.mo8300add("logSourceName", zzkVar.zze());
        } else if (zzkVar.zzd() != Integer.MIN_VALUE) {
            objectEncoderContext2.mo8298add("logSource", zzkVar.zzd());
        } else {
            throw new EncodingException("Log request must have either LogSourceName or LogSource");
        }
        if (!zzkVar.zzc().isEmpty()) {
            objectEncoderContext2.mo8300add("logEvent", zzkVar.zzc());
        }
    }
}
