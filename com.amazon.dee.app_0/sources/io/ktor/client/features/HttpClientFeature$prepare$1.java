package io.ktor.client.features;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpClientFeature.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002 \u0001*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u0002H\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "TConfig", "", "TFeature", "invoke", "(Ljava/lang/Object;)V"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class HttpClientFeature$prepare$1 extends Lambda implements Function1<TConfig, Unit> {
    public static final HttpClientFeature$prepare$1 INSTANCE = new HttpClientFeature$prepare$1();

    HttpClientFeature$prepare$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Object obj) {
        mo12165invoke((HttpClientFeature$prepare$1) obj);
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void mo12165invoke(@NotNull TConfig receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
    }
}
