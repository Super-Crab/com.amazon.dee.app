package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.registration.DeviceRegistration;
import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceAccountInteractor$ICFSU3tq4cGqDuT2_7VGe2kWaV4  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DeviceAccountInteractor$ICFSU3tq4cGqDuT2_7VGe2kWaV4 implements Predicate {
    public static final /* synthetic */ $$Lambda$DeviceAccountInteractor$ICFSU3tq4cGqDuT2_7VGe2kWaV4 INSTANCE = new $$Lambda$DeviceAccountInteractor$ICFSU3tq4cGqDuT2_7VGe2kWaV4();

    private /* synthetic */ $$Lambda$DeviceAccountInteractor$ICFSU3tq4cGqDuT2_7VGe2kWaV4() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return DeviceAccountInteractor.lambda$handle1PAccessoryRegistrations$1((DeviceRegistration) obj);
    }
}
