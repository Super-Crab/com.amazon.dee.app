package com.google.android.gms.internal.vision;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
/* loaded from: classes2.dex */
public enum zzcz {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR)),
    BOOLEAN(Boolean.TYPE, Boolean.class, false),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzbo.class, zzbo.class, zzbo.zzgt),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);
    
    private final Class<?> zzmc;
    private final Class<?> zzmd;
    private final Object zzme;

    zzcz(Class cls, Class cls2, Object obj) {
        this.zzmc = cls;
        this.zzmd = cls2;
        this.zzme = obj;
    }

    public final Class<?> zzch() {
        return this.zzmd;
    }
}
