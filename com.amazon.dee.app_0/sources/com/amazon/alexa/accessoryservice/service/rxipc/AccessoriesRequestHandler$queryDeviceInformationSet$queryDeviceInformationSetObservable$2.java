package com.amazon.alexa.accessoryservice.service.rxipc;

import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceInformationSetResponse;
import io.reactivex.rxjava3.functions.Function;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessoriesRequestHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00060\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/accessoryclient/common/query/response/DeviceInformationSetResponse;", "it", "", "Lcom/amazon/alexa/accessory/protocol/Device$DeviceInformation;", "kotlin.jvm.PlatformType", "", "apply"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class AccessoriesRequestHandler$queryDeviceInformationSet$queryDeviceInformationSetObservable$2<T, R> implements Function<T, R> {
    public static final AccessoriesRequestHandler$queryDeviceInformationSet$queryDeviceInformationSetObservable$2 INSTANCE = new AccessoriesRequestHandler$queryDeviceInformationSet$queryDeviceInformationSetObservable$2();

    AccessoriesRequestHandler$queryDeviceInformationSet$queryDeviceInformationSetObservable$2() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    @NotNull
    /* renamed from: apply */
    public final DeviceInformationSetResponse mo10358apply(Set<Device.DeviceInformation> it2) {
        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
        return new DeviceInformationSetResponse(it2);
    }
}
