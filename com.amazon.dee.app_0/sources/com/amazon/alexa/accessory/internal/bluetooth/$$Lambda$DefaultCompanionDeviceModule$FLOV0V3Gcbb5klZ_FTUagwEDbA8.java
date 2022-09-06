package com.amazon.alexa.accessory.internal.bluetooth;

import android.util.Pair;
import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultCompanionDeviceModule$FLOV0V3Gcbb5klZ_FTUagwEDbA8  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DefaultCompanionDeviceModule$FLOV0V3Gcbb5klZ_FTUagwEDbA8 implements Predicate {
    public static final /* synthetic */ $$Lambda$DefaultCompanionDeviceModule$FLOV0V3Gcbb5klZ_FTUagwEDbA8 INSTANCE = new $$Lambda$DefaultCompanionDeviceModule$FLOV0V3Gcbb5klZ_FTUagwEDbA8();

    private /* synthetic */ $$Lambda$DefaultCompanionDeviceModule$FLOV0V3Gcbb5klZ_FTUagwEDbA8() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return DefaultCompanionDeviceModule.lambda$queryRemovedCompanionDevices$6((Pair) obj);
    }
}
