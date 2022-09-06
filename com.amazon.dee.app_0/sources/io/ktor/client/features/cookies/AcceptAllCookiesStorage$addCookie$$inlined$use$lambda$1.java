package io.ktor.client.features.cookies;

import io.ktor.http.Cookie;
import io.ktor.http.Url;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: AcceptAllCookiesStorage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/http/Cookie;", "invoke", "io/ktor/client/features/cookies/AcceptAllCookiesStorage$addCookie$2$2"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class AcceptAllCookiesStorage$addCookie$$inlined$use$lambda$1 extends Lambda implements Function1<Cookie, Boolean> {
    final /* synthetic */ Cookie $cookie$inlined;
    final /* synthetic */ Url $requestUrl$inlined;
    final /* synthetic */ AcceptAllCookiesStorage this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AcceptAllCookiesStorage$addCookie$$inlined$use$lambda$1(AcceptAllCookiesStorage acceptAllCookiesStorage, Cookie cookie, Url url) {
        super(1);
        this.this$0 = acceptAllCookiesStorage;
        this.$cookie$inlined = cookie;
        this.$requestUrl$inlined = url;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(Cookie cookie) {
        return Boolean.valueOf(invoke2(cookie));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(@NotNull Cookie it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        return Intrinsics.areEqual(it2.getName(), this.$cookie$inlined.getName()) && CookiesStorageKt.matches(it2, this.$requestUrl$inlined);
    }
}
