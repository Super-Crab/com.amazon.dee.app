package com.google.android.gms.internal.vision;
/* JADX WARN: Init of enum zzqe can be incorrect */
/* JADX WARN: Init of enum zzqf can be incorrect */
/* JADX WARN: Init of enum zzqg can be incorrect */
/* JADX WARN: Init of enum zzqh can be incorrect */
/* loaded from: classes2.dex */
public enum zzft {
    DOUBLE(zzfy.DOUBLE, 1),
    FLOAT(zzfy.FLOAT, 5),
    INT64(zzfy.LONG, 0),
    UINT64(zzfy.LONG, 0),
    INT32(zzfy.INT, 0),
    FIXED64(zzfy.LONG, 1),
    FIXED32(zzfy.INT, 5),
    BOOL(zzfy.BOOLEAN, 0),
    STRING(r1, 2) { // from class: com.google.android.gms.internal.vision.zzfu
    },
    GROUP(r1, 3) { // from class: com.google.android.gms.internal.vision.zzfv
    },
    MESSAGE(r1, 2) { // from class: com.google.android.gms.internal.vision.zzfw
    },
    BYTES(r1, 2) { // from class: com.google.android.gms.internal.vision.zzfx
    },
    UINT32(zzfy.INT, 0),
    ENUM(zzfy.ENUM, 0),
    SFIXED32(zzfy.INT, 5),
    SFIXED64(zzfy.LONG, 1),
    SINT32(zzfy.INT, 0),
    SINT64(zzfy.LONG, 0);
    
    private final zzfy zzqo;
    private final int zzqp;

    static {
        final zzfy zzfyVar = zzfy.STRING;
        final zzfy zzfyVar2 = zzfy.MESSAGE;
        final zzfy zzfyVar3 = zzfy.MESSAGE;
        final zzfy zzfyVar4 = zzfy.BYTE_STRING;
    }

    zzft(zzfy zzfyVar, int i) {
        this.zzqo = zzfyVar;
        this.zzqp = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* synthetic */ zzft(zzfy zzfyVar, int i, zzfs zzfsVar) {
        this(zzfyVar, i);
    }

    public final zzfy zzed() {
        return this.zzqo;
    }

    public final int zzee() {
        return this.zzqp;
    }
}
