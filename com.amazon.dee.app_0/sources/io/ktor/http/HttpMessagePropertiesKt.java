package io.ktor.http;

import com.amazon.identity.auth.map.device.AccountManagerConstants;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpMessageProperties.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u001a\u0012\u0010\u0000\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002*\u00020\u0003\u001a\u0012\u0010\u0000\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002*\u00020\u0004\u001a\u001f\u0010\u0000\u001a\u0004\u0018\u00010\u0005*\u00020\u00042\n\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002H\u0007¢\u0006\u0002\u0010\u0006\u001a\u0011\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u0003¢\u0006\u0002\u0010\t\u001a\u0011\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u0004¢\u0006\u0002\u0010\n\u001a\u0014\u0010\u0007\u001a\u00020\u0005*\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a\f\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u0003\u001a\f\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u0004\u001a\u0012\u0010\r\u001a\u00020\u0005*\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000e\u001a\u0010\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011*\u00020\u0004\u001a\f\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\u00020\u0003\u001a\f\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\u00020\u0004\u001a\u0012\u0010\u0015\u001a\u00020\u0005*\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0014\u001a\u0012\u0010\u0017\u001a\u00020\u0005*\u00020\u00042\u0006\u0010\u0018\u001a\u00020\f\u001a\u0010\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011*\u00020\u0003\u001a\u0012\u0010\u001a\u001a\u00020\u0005*\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0014\u001a\u0012\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0011*\u00020\u0003\u001a\u0012\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0011*\u00020\u0004¨\u0006\u001d"}, d2 = {HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lkotlinx/io/charsets/Charset;", "Lio/ktor/http/HttpMessage;", "Lio/ktor/http/HttpMessageBuilder;", "", "(Lio/ktor/http/HttpMessageBuilder;Ljava/nio/charset/Charset;)Lkotlin/Unit;", "contentLength", "", "(Lio/ktor/http/HttpMessage;)Ljava/lang/Long;", "(Lio/ktor/http/HttpMessageBuilder;)Ljava/lang/Long;", "length", "", "contentType", "Lio/ktor/http/ContentType;", "type", AccountManagerConstants.GetCookiesParams.COOKIES, "", "Lio/ktor/http/Cookie;", "etag", "", "ifNoneMatch", "value", "maxAge", BizMetricsConstants.DURATION_METADATA_NAME, "setCookie", "userAgent", "content", "vary", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpMessagePropertiesKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use content with particular content type and charset instead")
    @Nullable
    public static final Unit charset(@NotNull HttpMessageBuilder receiver$0, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        ContentType contentType = contentType(receiver$0);
        if (contentType != null) {
            contentType(receiver$0, ContentTypesKt.withCharset(contentType, charset));
            return Unit.INSTANCE;
        }
        return null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Content-Length is controlled by underlying engine. Don't specify it explicitly.")
    public static final void contentLength(@NotNull HttpMessageBuilder receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.getHeaders().set(HttpHeaders.INSTANCE.getContentLength(), String.valueOf(i));
    }

    public static final void contentType(@NotNull HttpMessageBuilder receiver$0, @NotNull ContentType type) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(type, "type");
        receiver$0.getHeaders().set(HttpHeaders.INSTANCE.getContentType(), type.toString());
    }

    @NotNull
    public static final List<Cookie> cookies(@NotNull HttpMessageBuilder receiver$0) {
        List<Cookie> emptyList;
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        List<String> all = receiver$0.getHeaders().getAll(HttpHeaders.INSTANCE.getSetCookie());
        if (all == null) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(all, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (String str : all) {
            arrayList.add(CookieKt.parseServerSetCookieHeader(str));
        }
        return arrayList;
    }

    @Nullable
    public static final String etag(@NotNull HttpMessageBuilder receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getHeaders().get(HttpHeaders.INSTANCE.getETag());
    }

    public static final void ifNoneMatch(@NotNull HttpMessageBuilder receiver$0, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(value, "value");
        receiver$0.getHeaders().set(HttpHeaders.INSTANCE.getIfNoneMatch(), value);
    }

    public static final void maxAge(@NotNull HttpMessageBuilder receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        HeadersBuilder headers = receiver$0.getHeaders();
        String cacheControl = HttpHeaders.INSTANCE.getCacheControl();
        headers.append(cacheControl, "max-age:" + i);
    }

    @NotNull
    public static final List<Cookie> setCookie(@NotNull HttpMessage receiver$0) {
        List<Cookie> emptyList;
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        List<String> all = receiver$0.getHeaders().getAll(HttpHeaders.INSTANCE.getSetCookie());
        if (all == null) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(all, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (String str : all) {
            arrayList.add(CookieKt.parseServerSetCookieHeader(str));
        }
        return arrayList;
    }

    public static final void userAgent(@NotNull HttpMessageBuilder receiver$0, @NotNull String content) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(content, "content");
        receiver$0.getHeaders().set(HttpHeaders.INSTANCE.getUserAgent(), content);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0015, code lost:
        r7 = kotlin.text.StringsKt__StringsKt.split$default((java.lang.CharSequence) r1, new java.lang.String[]{","}, false, 0, 6, (java.lang.Object) null);
     */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.util.List<java.lang.String> vary(@org.jetbrains.annotations.NotNull io.ktor.http.HttpMessageBuilder r7) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            io.ktor.http.HeadersBuilder r7 = r7.getHeaders()
            io.ktor.http.HttpHeaders r0 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r0 = r0.getVary()
            java.lang.String r1 = r7.get(r0)
            if (r1 == 0) goto L56
            java.lang.String r7 = ","
            java.lang.String[] r2 = new java.lang.String[]{r7}
            r3 = 0
            r4 = 0
            r5 = 6
            r6 = 0
            java.util.List r7 = kotlin.text.StringsKt.split$default(r1, r2, r3, r4, r5, r6)
            if (r7 == 0) goto L56
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r7, r1)
            r0.<init>(r1)
            java.util.Iterator r7 = r7.iterator()
        L34:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L57
            java.lang.Object r1 = r7.next()
            java.lang.String r1 = (java.lang.String) r1
            if (r1 == 0) goto L4e
            java.lang.CharSequence r1 = kotlin.text.StringsKt.trim(r1)
            java.lang.String r1 = r1.toString()
            r0.add(r1)
            goto L34
        L4e:
            kotlin.TypeCastException r7 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.CharSequence"
            r7.<init>(r0)
            throw r7
        L56:
            r0 = 0
        L57:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.HttpMessagePropertiesKt.vary(io.ktor.http.HttpMessageBuilder):java.util.List");
    }

    @Nullable
    public static final Charset charset(@NotNull HttpMessageBuilder receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ContentType contentType = contentType(receiver$0);
        if (contentType != null) {
            return ContentTypesKt.charset(contentType);
        }
        return null;
    }

    @Nullable
    public static final Long contentLength(@NotNull HttpMessageBuilder receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        String str = receiver$0.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
        if (str != null) {
            return Long.valueOf(Long.parseLong(str));
        }
        return null;
    }

    @Nullable
    public static final ContentType contentType(@NotNull HttpMessageBuilder receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        String str = receiver$0.getHeaders().get(HttpHeaders.INSTANCE.getContentType());
        if (str != null) {
            return ContentType.Companion.parse(str);
        }
        return null;
    }

    @Nullable
    public static final String etag(@NotNull HttpMessage receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getHeaders().get(HttpHeaders.INSTANCE.getETag());
    }

    @Nullable
    public static final Charset charset(@NotNull HttpMessage receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ContentType contentType = contentType(receiver$0);
        if (contentType != null) {
            return ContentTypesKt.charset(contentType);
        }
        return null;
    }

    @Nullable
    public static final Long contentLength(@NotNull HttpMessage receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        String str = receiver$0.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
        if (str != null) {
            return Long.valueOf(Long.parseLong(str));
        }
        return null;
    }

    @Nullable
    public static final ContentType contentType(@NotNull HttpMessage receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        String str = receiver$0.getHeaders().get(HttpHeaders.INSTANCE.getContentType());
        if (str != null) {
            return ContentType.Companion.parse(str);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0015, code lost:
        r7 = kotlin.text.StringsKt__StringsKt.split$default((java.lang.CharSequence) r1, new java.lang.String[]{","}, false, 0, 6, (java.lang.Object) null);
     */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.util.List<java.lang.String> vary(@org.jetbrains.annotations.NotNull io.ktor.http.HttpMessage r7) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            io.ktor.http.Headers r7 = r7.getHeaders()
            io.ktor.http.HttpHeaders r0 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r0 = r0.getVary()
            java.lang.String r1 = r7.get(r0)
            if (r1 == 0) goto L56
            java.lang.String r7 = ","
            java.lang.String[] r2 = new java.lang.String[]{r7}
            r3 = 0
            r4 = 0
            r5 = 6
            r6 = 0
            java.util.List r7 = kotlin.text.StringsKt.split$default(r1, r2, r3, r4, r5, r6)
            if (r7 == 0) goto L56
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r7, r1)
            r0.<init>(r1)
            java.util.Iterator r7 = r7.iterator()
        L34:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L57
            java.lang.Object r1 = r7.next()
            java.lang.String r1 = (java.lang.String) r1
            if (r1 == 0) goto L4e
            java.lang.CharSequence r1 = kotlin.text.StringsKt.trim(r1)
            java.lang.String r1 = r1.toString()
            r0.add(r1)
            goto L34
        L4e:
            kotlin.TypeCastException r7 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.CharSequence"
            r7.<init>(r0)
            throw r7
        L56:
            r0 = 0
        L57:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.HttpMessagePropertiesKt.vary(io.ktor.http.HttpMessage):java.util.List");
    }
}
