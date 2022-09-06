package com.amazon.alexa.accessorykit.finishsetup;

import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.finishsetup.-$$Lambda$InterProcessFASViewCoordinator$9fSB7_NEkNVrbABCXLZ3jAKeCOA  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$InterProcessFASViewCoordinator$9fSB7_NEkNVrbABCXLZ3jAKeCOA implements Predicate {
    public static final /* synthetic */ $$Lambda$InterProcessFASViewCoordinator$9fSB7_NEkNVrbABCXLZ3jAKeCOA INSTANCE = new $$Lambda$InterProcessFASViewCoordinator$9fSB7_NEkNVrbABCXLZ3jAKeCOA();

    private /* synthetic */ $$Lambda$InterProcessFASViewCoordinator$9fSB7_NEkNVrbABCXLZ3jAKeCOA() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return InterProcessFASViewCoordinator.lambda$observeNewlySetupDevices$4((DeviceGroup) obj);
    }
}
