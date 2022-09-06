package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccount;
import com.amazon.alexa.accessoryclient.common.query.response.DeviceAccountResponse;
import io.reactivex.rxjava3.functions.Function;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientDeviceAccountSupplier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/accessory/registration/deviceaccount/DeviceAccount;", "it", "Lcom/amazon/alexa/accessoryclient/common/query/response/DeviceAccountResponse;", "kotlin.jvm.PlatformType", "apply"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class ClientDeviceAccountSupplier$fetchAndStoreDeviceAccount$1<T, R> implements Function<T, R> {
    public static final ClientDeviceAccountSupplier$fetchAndStoreDeviceAccount$1 INSTANCE = new ClientDeviceAccountSupplier$fetchAndStoreDeviceAccount$1();

    ClientDeviceAccountSupplier$fetchAndStoreDeviceAccount$1() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    @NotNull
    /* renamed from: apply */
    public final DeviceAccount mo10358apply(DeviceAccountResponse deviceAccountResponse) {
        return deviceAccountResponse.getDeviceAccount();
    }
}
