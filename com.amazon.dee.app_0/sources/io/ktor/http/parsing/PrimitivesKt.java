package io.ktor.http.parsing;

import kotlin.Metadata;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.jetbrains.annotations.NotNull;
/* compiled from: Primitives.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\"\u0014\u0010\u0000\u001a\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003\"\u0014\u0010\u0006\u001a\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0003\"\u0014\u0010\b\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"\u0014\u0010\f\u001a\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0003\"\u0014\u0010\u000e\u001a\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0003\"\u0014\u0010\u0010\u001a\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0003¨\u0006\u0012"}, d2 = {"alpha", "Lio/ktor/http/parsing/Grammar;", "getAlpha", "()Lio/ktor/http/parsing/Grammar;", "alphaDigit", "getAlphaDigit", "alphas", "getAlphas", "digit", "Lio/ktor/http/parsing/RawGrammar;", "getDigit", "()Lio/ktor/http/parsing/RawGrammar;", "digits", "getDigits", "hex", "getHex", "lowAlpha", "getLowAlpha", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class PrimitivesKt {
    @NotNull
    public static final Grammar getAlpha() {
        return ParserDslKt.or(ParserDslKt.to('a', 'z'), ParserDslKt.to('A', Matrix.MATRIX_TYPE_ZERO));
    }

    @NotNull
    public static final Grammar getAlphaDigit() {
        return ParserDslKt.or(getAlpha(), getDigit());
    }

    @NotNull
    public static final Grammar getAlphas() {
        return ParserDslKt.atLeastOne(getAlpha());
    }

    @NotNull
    public static final RawGrammar getDigit() {
        return new RawGrammar("\\d");
    }

    @NotNull
    public static final Grammar getDigits() {
        return ParserDslKt.atLeastOne(getDigit());
    }

    @NotNull
    public static final Grammar getHex() {
        return ParserDslKt.or(ParserDslKt.or(getDigit(), ParserDslKt.to('A', 'F')), ParserDslKt.to('a', 'f'));
    }

    @NotNull
    public static final Grammar getLowAlpha() {
        return ParserDslKt.to('a', 'z');
    }
}
