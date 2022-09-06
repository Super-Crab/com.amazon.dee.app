package io.ktor.http.auth;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.MatchResult;
import kotlin.text.StringsKt___StringsKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpAuthHeader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lkotlin/text/MatchResult;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpAuthHeaderKt$unescapeIfQuoted$1 extends Lambda implements Function1<MatchResult, String> {
    public static final HttpAuthHeaderKt$unescapeIfQuoted$1 INSTANCE = new HttpAuthHeaderKt$unescapeIfQuoted$1();

    HttpAuthHeaderKt$unescapeIfQuoted$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final String mo12165invoke(@NotNull MatchResult it2) {
        String takeLast;
        Intrinsics.checkParameterIsNotNull(it2, "it");
        takeLast = StringsKt___StringsKt.takeLast(it2.getValue(), 1);
        return takeLast;
    }
}
