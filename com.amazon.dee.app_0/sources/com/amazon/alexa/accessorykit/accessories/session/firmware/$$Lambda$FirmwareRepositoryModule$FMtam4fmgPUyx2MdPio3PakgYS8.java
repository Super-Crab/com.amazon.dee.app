package com.amazon.alexa.accessorykit.accessories.session.firmware;

import com.amazon.alexa.accessory.repositories.firmware.FirmwareUpdateStatus;
import io.reactivex.rxjava3.functions.BiPredicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.accessories.session.firmware.-$$Lambda$FirmwareRepositoryModule$FMtam4fmgPUyx2MdPio3PakgYS8  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FirmwareRepositoryModule$FMtam4fmgPUyx2MdPio3PakgYS8 implements BiPredicate {
    public static final /* synthetic */ $$Lambda$FirmwareRepositoryModule$FMtam4fmgPUyx2MdPio3PakgYS8 INSTANCE = new $$Lambda$FirmwareRepositoryModule$FMtam4fmgPUyx2MdPio3PakgYS8();

    private /* synthetic */ $$Lambda$FirmwareRepositoryModule$FMtam4fmgPUyx2MdPio3PakgYS8() {
    }

    @Override // io.reactivex.rxjava3.functions.BiPredicate
    public final boolean test(Object obj, Object obj2) {
        return FirmwareRepositoryModule.lambda$queryUpdateStatus$0((FirmwareUpdateStatus) obj, (FirmwareUpdateStatus) obj2);
    }
}
