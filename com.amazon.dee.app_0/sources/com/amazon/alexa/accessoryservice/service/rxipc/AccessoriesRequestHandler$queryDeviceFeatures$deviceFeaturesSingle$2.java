package com.amazon.alexa.accessoryservice.service.rxipc;

import com.amazon.alexa.accessory.repositories.device.DeviceFeatures;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceFeaturesResponse;
import io.reactivex.rxjava3.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessoriesRequestHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/accessoryclient/common/query/response/DeviceFeaturesResponse;", "it", "Lcom/amazon/alexa/accessory/repositories/device/DeviceFeatures;", "kotlin.jvm.PlatformType", "apply"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class AccessoriesRequestHandler$queryDeviceFeatures$deviceFeaturesSingle$2<T, R> implements Function<T, R> {
    public static final AccessoriesRequestHandler$queryDeviceFeatures$deviceFeaturesSingle$2 INSTANCE = new AccessoriesRequestHandler$queryDeviceFeatures$deviceFeaturesSingle$2();

    AccessoriesRequestHandler$queryDeviceFeatures$deviceFeaturesSingle$2() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    @NotNull
    /* renamed from: apply */
    public final DeviceFeaturesResponse mo10358apply(DeviceFeatures it2) {
        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
        return new DeviceFeaturesResponse(it2);
    }
}
