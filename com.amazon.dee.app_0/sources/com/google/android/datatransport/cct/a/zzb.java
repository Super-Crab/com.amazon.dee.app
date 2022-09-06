package com.google.android.datatransport.cct.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
/* loaded from: classes2.dex */
public final class zzb implements ObjectEncoder<zzd> {
    @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
    public void encode(@Nullable Object obj, @NonNull ObjectEncoderContext objectEncoderContext) throws EncodingException, IOException {
        zzd zzdVar = (zzd) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        if (zzdVar.zzi() != Integer.MIN_VALUE) {
            objectEncoderContext2.mo8298add("sdkVersion", zzdVar.zzi());
        }
        if (zzdVar.zzf() != null) {
            objectEncoderContext2.mo8300add("model", zzdVar.zzf());
        }
        if (zzdVar.zzd() != null) {
            objectEncoderContext2.mo8300add(MetricsConfiguration.HARDWARE, zzdVar.zzd());
        }
        if (zzdVar.zzb() != null) {
            objectEncoderContext2.mo8300add("device", zzdVar.zzb());
        }
        if (zzdVar.zzh() != null) {
            objectEncoderContext2.mo8300add(AMPDInformationProvider.DEVICE_PRODUCT_KEY, zzdVar.zzh());
        }
        if (zzdVar.zzg() != null) {
            objectEncoderContext2.mo8300add("osBuild", zzdVar.zzg());
        }
        if (zzdVar.zze() != null) {
            objectEncoderContext2.mo8300add("manufacturer", zzdVar.zze());
        }
        if (zzdVar.zzc() != null) {
            objectEncoderContext2.mo8300add("fingerprint", zzdVar.zzc());
        }
    }
}
