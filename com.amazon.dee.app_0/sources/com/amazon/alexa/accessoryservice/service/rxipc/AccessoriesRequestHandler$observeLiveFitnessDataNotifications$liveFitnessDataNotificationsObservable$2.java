package com.amazon.alexa.accessoryservice.service.rxipc;

import com.amazon.alexa.accessory.protocol.Fitness;
import com.amazon.alexa.accessoryclient.common.query.response.LiveFitnessDataResponse;
import io.reactivex.rxjava3.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessoriesRequestHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/accessoryclient/common/query/response/LiveFitnessDataResponse;", "it", "Lcom/amazon/alexa/accessory/protocol/Fitness$LiveFitnessData;", "kotlin.jvm.PlatformType", "apply"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class AccessoriesRequestHandler$observeLiveFitnessDataNotifications$liveFitnessDataNotificationsObservable$2<T, R> implements Function<T, R> {
    public static final AccessoriesRequestHandler$observeLiveFitnessDataNotifications$liveFitnessDataNotificationsObservable$2 INSTANCE = new AccessoriesRequestHandler$observeLiveFitnessDataNotifications$liveFitnessDataNotificationsObservable$2();

    AccessoriesRequestHandler$observeLiveFitnessDataNotifications$liveFitnessDataNotificationsObservable$2() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    @NotNull
    /* renamed from: apply */
    public final LiveFitnessDataResponse mo10358apply(Fitness.LiveFitnessData it2) {
        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
        return new LiveFitnessDataResponse(it2);
    }
}