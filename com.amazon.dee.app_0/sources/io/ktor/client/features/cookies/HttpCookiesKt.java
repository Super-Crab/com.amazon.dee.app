package io.ktor.client.features.cookies;

import com.amazon.identity.auth.map.device.AccountManagerConstants;
import com.dee.app.metrics.MetricsConstants;
import io.ktor.http.CodecsKt;
import io.ktor.http.Cookie;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpCookies.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002\u001a#\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\b\u001a#\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\u00052\u0006\u0010\t\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\u001d\u0010\u000b\u001a\u0004\u0018\u00010\u0004*\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\f\u001a\u00020\u0001H\u0086\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"renderClientCookies", "", AccountManagerConstants.GetCookiesParams.COOKIES, "", "Lio/ktor/http/Cookie;", "Lio/ktor/client/HttpClient;", "url", "Lio/ktor/http/Url;", "(Lio/ktor/client/HttpClient;Lio/ktor/http/Url;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "urlString", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", MetricsConstants.Method.CACHE_GET, "name", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpCookiesKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object cookies(@org.jetbrains.annotations.NotNull io.ktor.client.HttpClient r4, @org.jetbrains.annotations.NotNull io.ktor.http.Url r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<io.ktor.http.Cookie>> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.client.features.cookies.HttpCookiesKt$cookies$1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.client.features.cookies.HttpCookiesKt$cookies$1 r0 = (io.ktor.client.features.cookies.HttpCookiesKt$cookies$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.features.cookies.HttpCookiesKt$cookies$1 r0 = new io.ktor.client.features.cookies.HttpCookiesKt$cookies$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3f
            if (r2 != r3) goto L37
            java.lang.Object r4 = r0.L$1
            io.ktor.http.Url r4 = (io.ktor.http.Url) r4
            java.lang.Object r4 = r0.L$0
            io.ktor.client.HttpClient r4 = (io.ktor.client.HttpClient) r4
            boolean r4 = r6 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L32
            goto L5a
        L32:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r4 = r6.exception
            throw r4
        L37:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3f:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L64
            io.ktor.client.features.cookies.HttpCookies$Companion r6 = io.ktor.client.features.cookies.HttpCookies.Companion
            java.lang.Object r6 = io.ktor.client.features.HttpClientFeatureKt.feature(r4, r6)
            io.ktor.client.features.cookies.HttpCookies r6 = (io.ktor.client.features.cookies.HttpCookies) r6
            if (r6 == 0) goto L5f
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r6.get(r5, r0)
            if (r6 != r1) goto L5a
            return r1
        L5a:
            java.util.List r6 = (java.util.List) r6
            if (r6 == 0) goto L5f
            goto L63
        L5f:
            java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()
        L63:
            return r6
        L64:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r4 = r6.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.features.cookies.HttpCookiesKt.cookies(io.ktor.client.HttpClient, io.ktor.http.Url, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static final Cookie get(@NotNull List<Cookie> receiver$0, @NotNull String name) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Iterator<T> it2 = receiver$0.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            if (Intrinsics.areEqual(((Cookie) obj).getName(), name)) {
                break;
            }
        }
        return (Cookie) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String renderClientCookies(List<Cookie> list) {
        StringBuilder sb = new StringBuilder();
        for (Cookie cookie : list) {
            sb.append(cookie.getName());
            sb.append(Chars.EQ);
            sb.append(CodecsKt.encodeURLParameter$default(cookie.getValue(), false, 1, null));
            sb.append(';');
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object cookies(@org.jetbrains.annotations.NotNull io.ktor.client.HttpClient r4, @org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<io.ktor.http.Cookie>> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.client.features.cookies.HttpCookiesKt$cookies$2
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.client.features.cookies.HttpCookiesKt$cookies$2 r0 = (io.ktor.client.features.cookies.HttpCookiesKt$cookies$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.features.cookies.HttpCookiesKt$cookies$2 r0 = new io.ktor.client.features.cookies.HttpCookiesKt$cookies$2
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3f
            if (r2 != r3) goto L37
            java.lang.Object r4 = r0.L$1
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r4 = r0.L$0
            io.ktor.client.HttpClient r4 = (io.ktor.client.HttpClient) r4
            boolean r4 = r6 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L32
            goto L5e
        L32:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r4 = r6.exception
            throw r4
        L37:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3f:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L68
            io.ktor.client.features.cookies.HttpCookies$Companion r6 = io.ktor.client.features.cookies.HttpCookies.Companion
            java.lang.Object r6 = io.ktor.client.features.HttpClientFeatureKt.feature(r4, r6)
            io.ktor.client.features.cookies.HttpCookies r6 = (io.ktor.client.features.cookies.HttpCookies) r6
            if (r6 == 0) goto L63
            io.ktor.http.Url r2 = io.ktor.http.URLUtilsKt.Url(r5)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r6.get(r2, r0)
            if (r6 != r1) goto L5e
            return r1
        L5e:
            java.util.List r6 = (java.util.List) r6
            if (r6 == 0) goto L63
            goto L67
        L63:
            java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()
        L67:
            return r6
        L68:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r4 = r6.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.features.cookies.HttpCookiesKt.cookies(io.ktor.client.HttpClient, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
