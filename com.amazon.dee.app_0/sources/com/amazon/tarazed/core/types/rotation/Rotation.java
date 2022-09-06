package com.amazon.tarazed.core.types.rotation;

import kotlin.Metadata;
/* compiled from: Rotation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/tarazed/core/types/rotation/Rotation;", "", "degrees", "", "(Ljava/lang/String;II)V", "getDegrees", "()I", "ROTATION_0", "ROTATION_90", "ROTATION_180", "ROTATION_270", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public enum Rotation {
    ROTATION_0(0),
    ROTATION_90(90),
    ROTATION_180(180),
    ROTATION_270(270);
    
    private final int degrees;

    Rotation(int i) {
        this.degrees = i;
    }

    public final int getDegrees() {
        return this.degrees;
    }
}
