package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceConfigurationResponse;
import io.reactivex.rxjava3.functions.Function;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientDeviceRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/accessory/protocol/Device$DeviceConfiguration;", "it", "Lcom/amazon/alexa/accessoryclient/common/query/response/DeviceConfigurationResponse;", "kotlin.jvm.PlatformType", "apply"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class ClientDeviceRepository$queryDeviceConfiguration$1<T, R> implements Function<T, R> {
    public static final ClientDeviceRepository$queryDeviceConfiguration$1 INSTANCE = new ClientDeviceRepository$queryDeviceConfiguration$1();

    ClientDeviceRepository$queryDeviceConfiguration$1() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    @NotNull
    /* renamed from: apply */
    public final Device.DeviceConfiguration mo10358apply(DeviceConfigurationResponse deviceConfigurationResponse) {
        return deviceConfigurationResponse.getDeviceConfiguration();
    }
}
