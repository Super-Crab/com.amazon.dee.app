package io.ktor.http.parsing;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: ParserDsl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/parsing/Grammar;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class ParserDslKt$maybe$1 extends Lambda implements Function0<Grammar> {
    final /* synthetic */ Function1 $block;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ParserDslKt$maybe$1(Function1 function1) {
        super(0);
        this.$block = function1;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke */
    public final Grammar mo12560invoke() {
        GrammarBuilder grammarBuilder = new GrammarBuilder();
        this.$block.mo12165invoke(grammarBuilder);
        return ParserDslKt.maybe(grammarBuilder.build());
    }
}
