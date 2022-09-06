package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.protocol.Device;
import io.reactivex.rxjava3.functions.Function;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientDeviceRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0001 \u0004*\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/accessory/protocol/Device$DeviceInformation;", "it", "", "kotlin.jvm.PlatformType", "apply"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class ClientDeviceRepository$queryDeviceInformation$1<T, R> implements Function<T, R> {
    public static final ClientDeviceRepository$queryDeviceInformation$1 INSTANCE = new ClientDeviceRepository$queryDeviceInformation$1();

    ClientDeviceRepository$queryDeviceInformation$1() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    @NotNull
    /* renamed from: apply */
    public final Device.DeviceInformation mo10358apply(Set<Device.DeviceInformation> set) {
        return set.iterator().next();
    }
}
