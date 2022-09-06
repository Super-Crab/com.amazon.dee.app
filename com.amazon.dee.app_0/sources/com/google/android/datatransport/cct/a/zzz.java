package com.google.android.datatransport.cct.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
/* loaded from: classes2.dex */
public final class zzz implements ObjectEncoder<zzn> {
    @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
    public void encode(@Nullable Object obj, @NonNull ObjectEncoderContext objectEncoderContext) throws EncodingException, IOException {
        zzn zznVar = (zzn) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        if (zznVar.zzb() != null) {
            objectEncoderContext2.mo8300add("mobileSubtype", zznVar.zzb().name());
        }
        if (zznVar.zzc() != null) {
            objectEncoderContext2.mo8300add("networkType", zznVar.zzc().name());
        }
    }
}
