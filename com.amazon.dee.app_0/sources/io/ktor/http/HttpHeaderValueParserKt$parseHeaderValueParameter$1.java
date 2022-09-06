package io.ktor.http;

import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpHeaderValueParser.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\n¢\u0006\u0002\b\b"}, d2 = {"addParam", "", "text", "", "start", "", "end", "value", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpHeaderValueParserKt$parseHeaderValueParameter$1 extends Lambda implements Function4<String, Integer, Integer, String, Unit> {
    final /* synthetic */ Lazy $parameters;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpHeaderValueParserKt$parseHeaderValueParameter$1(Lazy lazy) {
        super(4);
        this.$parameters = lazy;
    }

    @Override // kotlin.jvm.functions.Function4
    public /* bridge */ /* synthetic */ Unit invoke(String str, Integer num, Integer num2, String str2) {
        invoke(str, num.intValue(), num2.intValue(), str2);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull String text, int i, int i2, @NotNull String value) {
        String subtrim;
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(value, "value");
        subtrim = HttpHeaderValueParserKt.subtrim(text, i, i2);
        if (subtrim.length() == 0) {
            return;
        }
        ((ArrayList) this.$parameters.getValue()).add(new HeaderValueParam(subtrim, value));
    }
}
