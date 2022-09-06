package io.ktor.client;

import io.ktor.client.features.DefaultTransformKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/client/HttpClient;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpClient$1$1 extends Lambda implements Function1<HttpClient, Unit> {
    public static final HttpClient$1$1 INSTANCE = new HttpClient$1$1();

    HttpClient$1$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(HttpClient httpClient) {
        invoke2(httpClient);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull HttpClient receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        DefaultTransformKt.defaultTransformers(receiver$0);
    }
}
