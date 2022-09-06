package io.ktor.client.features.cookies;

import com.amazon.identity.auth.map.device.AccountManagerConstants;
import io.ktor.client.HttpClient;
import io.ktor.http.Url;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HttpCookies.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006H\u0086@ø\u0001\u0000"}, d2 = {AccountManagerConstants.GetCookiesParams.COOKIES, "", "Lio/ktor/client/HttpClient;", "url", "Lio/ktor/http/Url;", "continuation", "Lkotlin/coroutines/Continuation;", "", "Lio/ktor/http/Cookie;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.features.cookies.HttpCookiesKt", f = "HttpCookies.kt", i = {0, 0}, l = {81}, m = AccountManagerConstants.GetCookiesParams.COOKIES, n = {"$receiver", "url"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class HttpCookiesKt$cookies$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpCookiesKt$cookies$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return HttpCookiesKt.cookies((HttpClient) null, (Url) null, this);
    }
}
