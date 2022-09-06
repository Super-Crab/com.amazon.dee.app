package io.ktor.client.features.cookies;

import io.ktor.http.Cookie;
import io.ktor.http.URLUtilsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CookiesStorage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u0014\u0010\b\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0000\u001a\u0014\u0010\u000b\u001a\u00020\f*\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"addCookie", "", "Lio/ktor/client/features/cookies/CookiesStorage;", "urlString", "", "cookie", "Lio/ktor/http/Cookie;", "(Lio/ktor/client/features/cookies/CookiesStorage;Ljava/lang/String;Lio/ktor/http/Cookie;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fillDefaults", "requestUrl", "Lio/ktor/http/Url;", "matches", "", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class CookiesStorageKt {
    @Nullable
    public static final Object addCookie(@NotNull CookiesStorage cookiesStorage, @NotNull String str, @NotNull Cookie cookie, @NotNull Continuation<? super Unit> continuation) {
        return cookiesStorage.addCookie(URLUtilsKt.Url(str), cookie, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final io.ktor.http.Cookie fillDefaults(@org.jetbrains.annotations.NotNull io.ktor.http.Cookie r17, @org.jetbrains.annotations.NotNull io.ktor.http.Url r18) {
        /*
            java.lang.String r0 = "receiver$0"
            r1 = r17
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r0)
            java.lang.String r0 = "requestUrl"
            r14 = r18
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r0)
            java.lang.String r0 = r17.getPath()
            r15 = 1
            r13 = 0
            if (r0 == 0) goto L24
            r2 = 2
            r3 = 0
            java.lang.String r4 = "/"
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r4, r13, r2, r3)
            if (r0 == r15) goto L21
            goto L24
        L21:
            r16 = r13
            goto L3e
        L24:
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            java.lang.String r8 = r18.getEncodedPath()
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 959(0x3bf, float:1.344E-42)
            r0 = 0
            r1 = r17
            r16 = r13
            r13 = r0
            io.ktor.http.Cookie r0 = io.ktor.http.Cookie.copy$default(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r1 = r0
        L3e:
            java.lang.String r0 = r1.getDomain()
            if (r0 == 0) goto L4d
            boolean r0 = kotlin.text.StringsKt.isBlank(r0)
            if (r0 == 0) goto L4b
            goto L4d
        L4b:
            r15 = r16
        L4d:
            if (r15 == 0) goto L63
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = r18.getHost()
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 991(0x3df, float:1.389E-42)
            r13 = 0
            io.ktor.http.Cookie r1 = io.ktor.http.Cookie.copy$default(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
        L63:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.features.cookies.CookiesStorageKt.fillDefaults(io.ktor.http.Cookie, io.ktor.http.Url):io.ktor.http.Cookie");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x008a, code lost:
        if (r0 == false) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean matches(@org.jetbrains.annotations.NotNull io.ktor.http.Cookie r11, @org.jetbrains.annotations.NotNull io.ktor.http.Url r12) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            java.lang.String r0 = "requestUrl"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            java.lang.String r0 = r11.getDomain()
            if (r0 == 0) goto Lca
            java.lang.String r0 = r0.toLowerCase()
            java.lang.String r1 = "(this as java.lang.String).toLowerCase()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            r2 = 1
            char[] r3 = new char[r2]
            r4 = 46
            r5 = 0
            r3[r5] = r4
            java.lang.String r0 = kotlin.text.StringsKt.trimStart(r0, r3)
            if (r0 == 0) goto Lca
            r11.getPath()
            java.lang.String r3 = r11.getPath()
            if (r3 == 0) goto Lbe
            r6 = 0
            r7 = 2
            r8 = 47
            boolean r9 = kotlin.text.StringsKt.endsWith$default(r3, r8, r5, r7, r6)
            if (r9 == 0) goto L3b
            goto L4e
        L3b:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r9 = r11.getPath()
            r3.append(r9)
            r3.append(r8)
            java.lang.String r3 = r3.toString()
        L4e:
            java.lang.String r9 = r12.getHost()
            if (r9 == 0) goto Lb6
            java.lang.String r9 = r9.toLowerCase()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r1)
            java.lang.String r1 = r12.getEncodedPath()
            boolean r10 = kotlin.text.StringsKt.endsWith$default(r1, r8, r5, r7, r6)
            if (r10 == 0) goto L66
            goto L6a
        L66:
            java.lang.String r1 = com.android.tools.r8.GeneratedOutlineSupport1.outline47(r1, r8)
        L6a:
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r0)
            r8 = r8 ^ r2
            if (r8 == 0) goto L8d
            boolean r8 = io.ktor.http.IpParserKt.hostIsIp(r9)
            if (r8 != 0) goto L8c
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r4)
            r8.append(r0)
            java.lang.String r0 = r8.toString()
            boolean r0 = kotlin.text.StringsKt.endsWith$default(r9, r0, r5, r7, r6)
            if (r0 != 0) goto L8d
        L8c:
            return r5
        L8d:
            java.lang.String r0 = "/"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)
            r0 = r0 ^ r2
            if (r0 == 0) goto La4
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r3)
            r0 = r0 ^ r2
            if (r0 == 0) goto La4
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r1, r3, r5, r7, r6)
            if (r0 != 0) goto La4
            return r5
        La4:
            boolean r11 = r11.getSecure()
            if (r11 == 0) goto Lb5
            io.ktor.http.URLProtocol r11 = r12.getProtocol()
            boolean r11 = io.ktor.http.URLProtocolKt.isSecure(r11)
            if (r11 != 0) goto Lb5
            return r5
        Lb5:
            return r2
        Lb6:
            kotlin.TypeCastException r11 = new kotlin.TypeCastException
            java.lang.String r12 = "null cannot be cast to non-null type java.lang.String"
            r11.<init>(r12)
            throw r11
        Lbe:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "Path field should have the default value"
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        Lca:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "Domain field should have the default value"
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.features.cookies.CookiesStorageKt.matches(io.ktor.http.Cookie, io.ktor.http.Url):boolean");
    }
}
