package io.ktor.http.parsing;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: GrammarBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0005J\u0011\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0005H\u0086\u0004J\u0011\u0010\u0007\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0086\u0004J\u0013\u0010\u000b\u001a\u00020\f*\b\u0012\u0004\u0012\u00020\u00050\rH\u0086\u0002J\r\u0010\u000b\u001a\u00020\f*\u00020\u0005H\u0086\u0002J\r\u0010\u000b\u001a\u00020\f*\u00020\nH\u0086\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lio/ktor/http/parsing/GrammarBuilder;", "", "()V", "grammars", "", "Lio/ktor/http/parsing/Grammar;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "then", "grammar", "value", "", "unaryPlus", "", "Lkotlin/Function0;", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class GrammarBuilder {
    private final List<Grammar> grammars = new ArrayList();

    @NotNull
    public final Grammar build() {
        return this.grammars.size() == 1 ? (Grammar) CollectionsKt.first((List<? extends Object>) this.grammars) : new SequenceGrammar(this.grammars);
    }

    @NotNull
    public final GrammarBuilder then(@NotNull Grammar grammar) {
        Intrinsics.checkParameterIsNotNull(grammar, "grammar");
        this.grammars.add(grammar);
        return this;
    }

    public final void unaryPlus(@NotNull Function0<? extends Grammar> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        this.grammars.add(receiver$0.mo12560invoke());
    }

    @NotNull
    public final GrammarBuilder then(@NotNull String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.grammars.add(new StringGrammar(value));
        return this;
    }

    public final void unaryPlus(@NotNull Grammar receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        this.grammars.add(receiver$0);
    }

    public final void unaryPlus(@NotNull String receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        this.grammars.add(new StringGrammar(receiver$0));
    }
}
