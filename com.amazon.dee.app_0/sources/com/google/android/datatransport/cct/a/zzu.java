package com.google.android.datatransport.cct.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
/* loaded from: classes2.dex */
public final class zzu implements ObjectEncoder<zzi> {
    @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
    public void encode(@Nullable Object obj, @NonNull ObjectEncoderContext objectEncoderContext) throws EncodingException, IOException {
        zzi zziVar = (zzi) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.mo8299add("eventTimeMs", zziVar.zza()).mo8299add("eventUptimeMs", zziVar.zzb()).mo8299add("timezoneOffsetSeconds", zziVar.zzc());
        if (zziVar.zzf() != null) {
            objectEncoderContext2.mo8300add("sourceExtension", zziVar.zzf());
        }
        if (zziVar.zzg() != null) {
            objectEncoderContext2.mo8300add("sourceExtensionJsonProto3", zziVar.zzg());
        }
        if (zziVar.zzd() != Integer.MIN_VALUE) {
            objectEncoderContext2.mo8298add("eventCode", zziVar.zzd());
        }
        if (zziVar.zze() != null) {
            objectEncoderContext2.mo8300add("networkConnectionInfo", zziVar.zze());
        }
    }
}
