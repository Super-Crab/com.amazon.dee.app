package io.ktor.client.utils;

import io.ktor.http.HeadersBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: headers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/http/HeadersBuilder;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class HeadersKt$buildHeaders$1 extends Lambda implements Function1<HeadersBuilder, Unit> {
    public static final HeadersKt$buildHeaders$1 INSTANCE = new HeadersKt$buildHeaders$1();

    HeadersKt$buildHeaders$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(HeadersBuilder headersBuilder) {
        invoke2(headersBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull HeadersBuilder receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
    }
}
