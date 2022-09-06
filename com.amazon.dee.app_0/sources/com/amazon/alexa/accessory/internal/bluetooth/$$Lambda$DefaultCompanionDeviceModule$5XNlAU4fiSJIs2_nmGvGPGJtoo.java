package com.amazon.alexa.accessory.internal.bluetooth;

import android.util.Pair;
import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultCompanionDeviceModule$5XNlAU4fiSJIs2_nmGv-GPGJtoo  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DefaultCompanionDeviceModule$5XNlAU4fiSJIs2_nmGvGPGJtoo implements Predicate {
    public static final /* synthetic */ $$Lambda$DefaultCompanionDeviceModule$5XNlAU4fiSJIs2_nmGvGPGJtoo INSTANCE = new $$Lambda$DefaultCompanionDeviceModule$5XNlAU4fiSJIs2_nmGvGPGJtoo();

    private /* synthetic */ $$Lambda$DefaultCompanionDeviceModule$5XNlAU4fiSJIs2_nmGvGPGJtoo() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        boolean booleanValue;
        booleanValue = ((Boolean) ((Pair) obj).second).booleanValue();
        return booleanValue;
    }
}
