package com.google.android.datatransport.cct.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
/* loaded from: classes2.dex */
public final class zzr implements ObjectEncoder<zzg> {
    @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
    public void encode(@Nullable Object obj, @NonNull ObjectEncoderContext objectEncoderContext) throws EncodingException, IOException {
        zzg zzgVar = (zzg) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        if (zzgVar.zzc() != null) {
            objectEncoderContext2.mo8300add("clientType", zzgVar.zzc().name());
        }
        if (zzgVar.zzb() != null) {
            objectEncoderContext2.mo8300add("androidClientInfo", zzgVar.zzb());
        }
    }
}
