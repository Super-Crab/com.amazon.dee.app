package io.ktor.client.features.cookies;

import io.ktor.http.Cookie;
import io.ktor.util.date.GMTDate;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: AcceptAllCookiesStorage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "cookie", "Lio/ktor/http/Cookie;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class AcceptAllCookiesStorage$cleanup$1 extends Lambda implements Function1<Cookie, Boolean> {
    final /* synthetic */ long $timestamp;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AcceptAllCookiesStorage$cleanup$1(long j) {
        super(1);
        this.$timestamp = j;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(Cookie cookie) {
        return Boolean.valueOf(invoke2(cookie));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(@NotNull Cookie cookie) {
        Intrinsics.checkParameterIsNotNull(cookie, "cookie");
        GMTDate expires = cookie.getExpires();
        return expires != null && expires.getTimestamp() < this.$timestamp;
    }
}
