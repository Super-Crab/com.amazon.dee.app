package com.amazon.alexa.accessory.repositories.device;

import io.reactivex.rxjava3.functions.Predicate;
import java.util.Set;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.repositories.device.-$$Lambda$MemoryDeviceRepository$_cU3-Jjq1gt_2Lf6818xqHr_GhA  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$MemoryDeviceRepository$_cU3Jjq1gt_2Lf6818xqHr_GhA implements Predicate {
    public static final /* synthetic */ $$Lambda$MemoryDeviceRepository$_cU3Jjq1gt_2Lf6818xqHr_GhA INSTANCE = new $$Lambda$MemoryDeviceRepository$_cU3Jjq1gt_2Lf6818xqHr_GhA();

    private /* synthetic */ $$Lambda$MemoryDeviceRepository$_cU3Jjq1gt_2Lf6818xqHr_GhA() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return MemoryDeviceRepository.lambda$queryDeviceInformation$0((Set) obj);
    }
}
