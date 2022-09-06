package io.ktor.client.features.observer;

import io.ktor.client.features.observer.ResponseObserver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: ResponseObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/client/features/observer/ResponseObserver$Config;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class ResponseObserverKt$ResponseObserver$1 extends Lambda implements Function1<ResponseObserver.Config, Unit> {
    final /* synthetic */ Function2 $block;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ResponseObserverKt$ResponseObserver$1(Function2 function2) {
        super(1);
        this.$block = function2;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(ResponseObserver.Config config) {
        invoke2(config);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull ResponseObserver.Config receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setResponseHandler$ktor_client_core(this.$block);
    }
}
