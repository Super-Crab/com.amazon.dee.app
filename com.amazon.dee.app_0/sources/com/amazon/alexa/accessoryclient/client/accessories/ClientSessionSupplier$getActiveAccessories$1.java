package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessoryclient.common.query.response.AccessoryListResponse;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientSessionSupplier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u000e\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "Lcom/amazon/alexa/accessory/Accessory;", "it", "Lcom/amazon/alexa/accessoryclient/common/query/response/AccessoryListResponse;", "kotlin.jvm.PlatformType", "apply"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class ClientSessionSupplier$getActiveAccessories$1<T, R> implements Function<T, R> {
    public static final ClientSessionSupplier$getActiveAccessories$1 INSTANCE = new ClientSessionSupplier$getActiveAccessories$1();

    ClientSessionSupplier$getActiveAccessories$1() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    @NotNull
    /* renamed from: apply */
    public final List<Accessory> mo10358apply(AccessoryListResponse accessoryListResponse) {
        return accessoryListResponse.getAccessoryList();
    }
}
