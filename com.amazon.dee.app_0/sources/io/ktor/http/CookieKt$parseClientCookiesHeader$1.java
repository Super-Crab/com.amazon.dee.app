package io.ktor.http;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import org.jetbrains.annotations.NotNull;
/* compiled from: Cookie.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "it", "Lkotlin/text/MatchResult;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class CookieKt$parseClientCookiesHeader$1 extends Lambda implements Function1<MatchResult, Pair<? extends String, ? extends String>> {
    public static final CookieKt$parseClientCookiesHeader$1 INSTANCE = new CookieKt$parseClientCookiesHeader$1();

    CookieKt$parseClientCookiesHeader$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Pair<String, String> mo12165invoke(@NotNull MatchResult it2) {
        String str;
        String str2;
        Intrinsics.checkParameterIsNotNull(it2, "it");
        MatchGroup matchGroup = it2.getGroups().get(2);
        if (matchGroup == null || (str = matchGroup.getValue()) == null) {
            str = "";
        }
        MatchGroup matchGroup2 = it2.getGroups().get(4);
        if (matchGroup2 == null || (str2 = matchGroup2.getValue()) == null) {
            str2 = "";
        }
        return TuplesKt.to(str, str2);
    }
}
