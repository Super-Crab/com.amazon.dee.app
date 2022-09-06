package com.amazon.alexa.accessoryservice.service.rxipc;

import com.amazon.alexa.accessory.protocol.Cbl;
import com.amazon.alexa.accessoryclient.common.query.response.CblInformationResponse;
import io.reactivex.rxjava3.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessoriesRequestHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/accessoryclient/common/query/response/CblInformationResponse;", "it", "Lcom/amazon/alexa/accessory/protocol/Cbl$CblInformation;", "kotlin.jvm.PlatformType", "apply"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class AccessoriesRequestHandler$requestCblInformation$requestCblInformationSingle$2<T, R> implements Function<T, R> {
    public static final AccessoriesRequestHandler$requestCblInformation$requestCblInformationSingle$2 INSTANCE = new AccessoriesRequestHandler$requestCblInformation$requestCblInformationSingle$2();

    AccessoriesRequestHandler$requestCblInformation$requestCblInformationSingle$2() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    @NotNull
    /* renamed from: apply */
    public final CblInformationResponse mo10358apply(Cbl.CblInformation it2) {
        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
        return new CblInformationResponse(it2);
    }
}
