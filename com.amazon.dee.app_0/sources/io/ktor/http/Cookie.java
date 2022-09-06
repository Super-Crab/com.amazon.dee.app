package io.ktor.http;

import com.amazon.alexa.routing.api.RouteParameter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.util.date.GMTDate;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Cookie.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b!\b\u0086\b\u0018\u00002\u00020\u0001By\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0011¢\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\u0017\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0011HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0006HÆ\u0003J\t\u0010'\u001a\u00020\bHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u000eHÆ\u0003J\t\u0010,\u001a\u00020\u000eHÆ\u0003J\u0081\u0001\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0011HÆ\u0001J\u0013\u0010.\u001a\u00020\u000e2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u00020\bHÖ\u0001J\t\u00101\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001f\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014¨\u00062"}, d2 = {"Lio/ktor/http/Cookie;", "", "name", "", "value", "encoding", "Lio/ktor/http/CookieEncoding;", "maxAge", "", "expires", "Lio/ktor/util/date/GMTDate;", "domain", RouteParameter.PATH, "secure", "", "httpOnly", "extensions", "", "(Ljava/lang/String;Ljava/lang/String;Lio/ktor/http/CookieEncoding;ILio/ktor/util/date/GMTDate;Ljava/lang/String;Ljava/lang/String;ZZLjava/util/Map;)V", "getDomain", "()Ljava/lang/String;", "getEncoding", "()Lio/ktor/http/CookieEncoding;", "getExpires", "()Lio/ktor/util/date/GMTDate;", "getExtensions", "()Ljava/util/Map;", "getHttpOnly", "()Z", "getMaxAge", "()I", "getName", "getPath", "getSecure", "getValue", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class Cookie {
    @Nullable
    private final String domain;
    @NotNull
    private final CookieEncoding encoding;
    @Nullable
    private final GMTDate expires;
    @NotNull
    private final Map<String, String> extensions;
    private final boolean httpOnly;
    private final int maxAge;
    @NotNull
    private final String name;
    @Nullable
    private final String path;
    private final boolean secure;
    @NotNull
    private final String value;

    public Cookie(@NotNull String name, @NotNull String value, @NotNull CookieEncoding encoding, int i, @Nullable GMTDate gMTDate, @Nullable String str, @Nullable String str2, boolean z, boolean z2, @NotNull Map<String, String> extensions) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(encoding, "encoding");
        Intrinsics.checkParameterIsNotNull(extensions, "extensions");
        this.name = name;
        this.value = value;
        this.encoding = encoding;
        this.maxAge = i;
        this.expires = gMTDate;
        this.domain = str;
        this.path = str2;
        this.secure = z;
        this.httpOnly = z2;
        this.extensions = extensions;
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final Map<String, String> component10() {
        return this.extensions;
    }

    @NotNull
    public final String component2() {
        return this.value;
    }

    @NotNull
    public final CookieEncoding component3() {
        return this.encoding;
    }

    public final int component4() {
        return this.maxAge;
    }

    @Nullable
    public final GMTDate component5() {
        return this.expires;
    }

    @Nullable
    public final String component6() {
        return this.domain;
    }

    @Nullable
    public final String component7() {
        return this.path;
    }

    public final boolean component8() {
        return this.secure;
    }

    public final boolean component9() {
        return this.httpOnly;
    }

    @NotNull
    public final Cookie copy(@NotNull String name, @NotNull String value, @NotNull CookieEncoding encoding, int i, @Nullable GMTDate gMTDate, @Nullable String str, @Nullable String str2, boolean z, boolean z2, @NotNull Map<String, String> extensions) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(encoding, "encoding");
        Intrinsics.checkParameterIsNotNull(extensions, "extensions");
        return new Cookie(name, value, encoding, i, gMTDate, str, str2, z, z2, extensions);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Cookie) {
                Cookie cookie = (Cookie) obj;
                if (Intrinsics.areEqual(this.name, cookie.name) && Intrinsics.areEqual(this.value, cookie.value) && Intrinsics.areEqual(this.encoding, cookie.encoding)) {
                    if ((this.maxAge == cookie.maxAge) && Intrinsics.areEqual(this.expires, cookie.expires) && Intrinsics.areEqual(this.domain, cookie.domain) && Intrinsics.areEqual(this.path, cookie.path)) {
                        if (this.secure == cookie.secure) {
                            if (!(this.httpOnly == cookie.httpOnly) || !Intrinsics.areEqual(this.extensions, cookie.extensions)) {
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final String getDomain() {
        return this.domain;
    }

    @NotNull
    public final CookieEncoding getEncoding() {
        return this.encoding;
    }

    @Nullable
    public final GMTDate getExpires() {
        return this.expires;
    }

    @NotNull
    public final Map<String, String> getExtensions() {
        return this.extensions;
    }

    public final boolean getHttpOnly() {
        return this.httpOnly;
    }

    public final int getMaxAge() {
        return this.maxAge;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final String getPath() {
        return this.path;
    }

    public final boolean getSecure() {
        return this.secure;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.value;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        CookieEncoding cookieEncoding = this.encoding;
        int hashCode3 = (((hashCode2 + (cookieEncoding != null ? cookieEncoding.hashCode() : 0)) * 31) + this.maxAge) * 31;
        GMTDate gMTDate = this.expires;
        int hashCode4 = (hashCode3 + (gMTDate != null ? gMTDate.hashCode() : 0)) * 31;
        String str3 = this.domain;
        int hashCode5 = (hashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.path;
        int hashCode6 = (hashCode5 + (str4 != null ? str4.hashCode() : 0)) * 31;
        boolean z = this.secure;
        if (z) {
            z = true;
        }
        int i2 = z ? 1 : 0;
        int i3 = z ? 1 : 0;
        int i4 = (hashCode6 + i2) * 31;
        boolean z2 = this.httpOnly;
        if (z2) {
            z2 = true;
        }
        int i5 = z2 ? 1 : 0;
        int i6 = z2 ? 1 : 0;
        int i7 = (i4 + i5) * 31;
        Map<String, String> map = this.extensions;
        if (map != null) {
            i = map.hashCode();
        }
        return i7 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cookie(name=");
        outline107.append(this.name);
        outline107.append(", value=");
        outline107.append(this.value);
        outline107.append(", encoding=");
        outline107.append(this.encoding);
        outline107.append(", maxAge=");
        outline107.append(this.maxAge);
        outline107.append(", expires=");
        outline107.append(this.expires);
        outline107.append(", domain=");
        outline107.append(this.domain);
        outline107.append(", path=");
        outline107.append(this.path);
        outline107.append(", secure=");
        outline107.append(this.secure);
        outline107.append(", httpOnly=");
        outline107.append(this.httpOnly);
        outline107.append(", extensions=");
        outline107.append(this.extensions);
        outline107.append(")");
        return outline107.toString();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ Cookie(java.lang.String r14, java.lang.String r15, io.ktor.http.CookieEncoding r16, int r17, io.ktor.util.date.GMTDate r18, java.lang.String r19, java.lang.String r20, boolean r21, boolean r22, java.util.Map r23, int r24, kotlin.jvm.internal.DefaultConstructorMarker r25) {
        /*
            r13 = this;
            r0 = r24
            r1 = r0 & 4
            if (r1 == 0) goto La
            io.ktor.http.CookieEncoding r1 = io.ktor.http.CookieEncoding.URI_ENCODING
            r5 = r1
            goto Lc
        La:
            r5 = r16
        Lc:
            r1 = r0 & 8
            r2 = 0
            if (r1 == 0) goto L13
            r6 = r2
            goto L15
        L13:
            r6 = r17
        L15:
            r1 = r0 & 16
            r3 = 0
            if (r1 == 0) goto L1c
            r7 = r3
            goto L1e
        L1c:
            r7 = r18
        L1e:
            r1 = r0 & 32
            if (r1 == 0) goto L24
            r8 = r3
            goto L26
        L24:
            r8 = r19
        L26:
            r1 = r0 & 64
            if (r1 == 0) goto L2c
            r9 = r3
            goto L2e
        L2c:
            r9 = r20
        L2e:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L34
            r10 = r2
            goto L36
        L34:
            r10 = r21
        L36:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L3c
            r11 = r2
            goto L3e
        L3c:
            r11 = r22
        L3e:
            r0 = r0 & 512(0x200, float:7.175E-43)
            if (r0 == 0) goto L48
            java.util.Map r0 = kotlin.collections.MapsKt.emptyMap()
            r12 = r0
            goto L4a
        L48:
            r12 = r23
        L4a:
            r2 = r13
            r3 = r14
            r4 = r15
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.Cookie.<init>(java.lang.String, java.lang.String, io.ktor.http.CookieEncoding, int, io.ktor.util.date.GMTDate, java.lang.String, java.lang.String, boolean, boolean, java.util.Map, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
