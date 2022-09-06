package com.amazon.alexa.accessoryservice.service.rxipc;

import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceGroupListResponse;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessoriesRequestHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00060\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/accessoryclient/common/query/response/DeviceGroupListResponse;", "it", "", "Lcom/amazon/alexa/accessory/repositories/device/v2/DeviceGroup;", "kotlin.jvm.PlatformType", "", "apply"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class AccessoriesRequestHandler$queryDeviceGroups$queryDeviceGroupsObservable$1<T, R> implements Function<T, R> {
    public static final AccessoriesRequestHandler$queryDeviceGroups$queryDeviceGroupsObservable$1 INSTANCE = new AccessoriesRequestHandler$queryDeviceGroups$queryDeviceGroupsObservable$1();

    AccessoriesRequestHandler$queryDeviceGroups$queryDeviceGroupsObservable$1() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    @NotNull
    /* renamed from: apply */
    public final DeviceGroupListResponse mo10358apply(List<DeviceGroup> it2) {
        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
        return new DeviceGroupListResponse(it2);
    }
}
