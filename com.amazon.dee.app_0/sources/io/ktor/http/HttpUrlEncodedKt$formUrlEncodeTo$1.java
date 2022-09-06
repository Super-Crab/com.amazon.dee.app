package io.ktor.http;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpUrlEncoded.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lkotlin/Pair;", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpUrlEncodedKt$formUrlEncodeTo$1 extends Lambda implements Function1<Pair<? extends String, ? extends String>, CharSequence> {
    public static final HttpUrlEncodedKt$formUrlEncodeTo$1 INSTANCE = new HttpUrlEncodedKt$formUrlEncodeTo$1();

    HttpUrlEncodedKt$formUrlEncodeTo$1() {
        super(1);
    }

    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final CharSequence invoke2(@NotNull Pair<String, String> it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        String encodeURLParameter = CodecsKt.encodeURLParameter(it2.getFirst(), true);
        return it2.getSecond() == null ? encodeURLParameter : GeneratedOutlineSupport1.outline48(encodeURLParameter, Chars.EQ, CodecsKt.encodeURLParameter(String.valueOf(it2.getSecond()), true));
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ CharSequence mo12165invoke(Pair<? extends String, ? extends String> pair) {
        return invoke2((Pair<String, String>) pair);
    }
}
