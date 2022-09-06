package io.ktor.http.parsing;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ParserDsl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lio/ktor/http/parsing/NamedGrammar;", "Lio/ktor/http/parsing/Grammar;", "name", "", "grammar", "(Ljava/lang/String;Lio/ktor/http/parsing/Grammar;)V", "getGrammar", "()Lio/ktor/http/parsing/Grammar;", "getName", "()Ljava/lang/String;", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class NamedGrammar extends Grammar {
    @NotNull
    private final Grammar grammar;
    @NotNull
    private final String name;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NamedGrammar(@NotNull String name, @NotNull Grammar grammar) {
        super(null);
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(grammar, "grammar");
        this.name = name;
        this.grammar = grammar;
    }

    @NotNull
    public final Grammar getGrammar() {
        return this.grammar;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }
}
