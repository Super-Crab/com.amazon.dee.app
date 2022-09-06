package com.google.android.play.core.assetpacks;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.util.HashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzco {
    private final Map zza = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized double zza(String str) {
        Double d = (Double) this.zza.get(str);
        if (d == null) {
            return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }
        return d.doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized double zzb(String str, zzdg zzdgVar) {
        double d;
        d = (((zzce) zzdgVar).zzf + 1.0d) / ((zzce) zzdgVar).zzg;
        this.zza.put(str, Double.valueOf(d));
        return d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzc(String str) {
        this.zza.put(str, Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR));
    }
}
