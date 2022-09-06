package io.ktor.http;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.util.Base64Kt;
import io.ktor.util.KtorExperimentalAPI;
import io.ktor.util.date.GMTDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Cookie.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001a#\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0082\b\u001a#\u0010\r\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0082\b\u001a\u0019\u0010\u000e\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u000fH\u0082\b\u001a\u001b\u0010\u0010\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0082\b\u001a\u0018\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a\u0018\u0010\u0013\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a&\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00152\u0006\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\u0017\u001a\u00020\u000fH\u0007\u001a\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0006H\u0007\u001a\u0010\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0019H\u0007\u001a\u0010\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0019H\u0007\u001a\u0086\u0001\u0010\u001c\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010#\u001a\u00020\u000f2\b\b\u0002\u0010$\u001a\u00020\u000f2\u0016\b\u0002\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00152\b\b\u0002\u0010&\u001a\u00020\u000fH\u0007\u001a\f\u0010'\u001a\u00020\u0006*\u00020\u0006H\u0002\u001a\f\u0010(\u001a\u00020\u000f*\u00020\u0004H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"clientCookieHeaderPattern", "Lkotlin/text/Regex;", "cookieCharsShouldBeEscaped", "", "", "loweredPartNames", "", "cookiePart", "name", "value", "", "encoding", "Lio/ktor/http/CookieEncoding;", "cookiePartExt", "cookiePartFlag", "", "cookiePartUnencoded", "decodeCookieValue", "encodedValue", "encodeCookieValue", "parseClientCookiesHeader", "", "cookiesHeader", "skipEscaped", "parseServerSetCookieHeader", "Lio/ktor/http/Cookie;", "renderCookieHeader", "cookie", "renderSetCookieHeader", "maxAge", "", "expires", "Lio/ktor/util/date/GMTDate;", "domain", RouteParameter.PATH, "secure", "httpOnly", "extensions", "includeEncoding", "assertCookieName", "shouldEscapeInCookies", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class CookieKt {
    private static final Regex clientCookieHeaderPattern;
    private static final Set<Character> cookieCharsShouldBeEscaped;
    private static final Set<String> loweredPartNames;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[CookieEncoding.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[CookieEncoding.RAW.ordinal()] = 1;
            $EnumSwitchMapping$0[CookieEncoding.DQUOTES.ordinal()] = 2;
            $EnumSwitchMapping$0[CookieEncoding.BASE64_ENCODING.ordinal()] = 3;
            $EnumSwitchMapping$0[CookieEncoding.URI_ENCODING.ordinal()] = 4;
            $EnumSwitchMapping$1 = new int[CookieEncoding.values().length];
            $EnumSwitchMapping$1[CookieEncoding.RAW.ordinal()] = 1;
            $EnumSwitchMapping$1[CookieEncoding.DQUOTES.ordinal()] = 2;
            $EnumSwitchMapping$1[CookieEncoding.URI_ENCODING.ordinal()] = 3;
            $EnumSwitchMapping$1[CookieEncoding.BASE64_ENCODING.ordinal()] = 4;
        }
    }

    static {
        Set<String> of;
        Set<Character> of2;
        of = SetsKt__SetsKt.setOf((Object[]) new String[]{io.ktor.client.utils.CacheControl.MAX_AGE, "expires", "domain", RouteParameter.PATH, "secure", "httponly", "$x-enc"});
        loweredPartNames = of;
        clientCookieHeaderPattern = new Regex("(^|;)\\s*([^()<>@;:/\\\\\"\\[\\]\\?=\\{\\}\\s]+)\\s*(=\\s*(\"[^\"]*\"|[^;]*))?");
        of2 = SetsKt__SetsKt.setOf((Object[]) new Character[]{';', Character.valueOf(JsonReaderKt.COMMA), Character.valueOf(Chars.EQ), '\"'});
        cookieCharsShouldBeEscaped = of2;
    }

    private static final String assertCookieName(@NotNull String str) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                break;
            } else if (shouldEscapeInCookies(str.charAt(i))) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            return str;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Cookie name is not valid: ", str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String cookiePart(String str, Object obj, CookieEncoding cookieEncoding) {
        if (obj != null) {
            StringBuilder outline108 = GeneratedOutlineSupport1.outline108(str, Chars.EQ);
            outline108.append(encodeCookieValue(obj.toString(), cookieEncoding));
            return outline108.toString();
        }
        return "";
    }

    private static final String cookiePartExt(String str, String str2, CookieEncoding cookieEncoding) {
        if (str2 == null) {
            return str;
        }
        StringBuilder outline108 = GeneratedOutlineSupport1.outline108(str, Chars.EQ);
        outline108.append(encodeCookieValue(str2.toString(), cookieEncoding));
        return outline108.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String cookiePartFlag(String str, boolean z) {
        return z ? str : "";
    }

    private static final String cookiePartUnencoded(String str, Object obj) {
        if (obj != null) {
            return str + Chars.EQ + obj;
        }
        return "";
    }

    @KtorExperimentalAPI
    @NotNull
    public static final String decodeCookieValue(@NotNull String encodedValue, @NotNull CookieEncoding encoding) {
        CharSequence trimStart;
        boolean startsWith$default;
        CharSequence trimEnd;
        boolean endsWith$default;
        CharSequence trim;
        String removeSurrounding;
        Intrinsics.checkParameterIsNotNull(encodedValue, "encodedValue");
        Intrinsics.checkParameterIsNotNull(encoding, "encoding");
        int i = WhenMappings.$EnumSwitchMapping$1[encoding.ordinal()];
        if (i != 1 && i != 2) {
            if (i == 3) {
                return CodecsKt.decodeURLQueryComponent$default(encodedValue, 0, 0, true, null, 11, null);
            }
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            return Base64Kt.decodeBase64(encodedValue);
        }
        trimStart = StringsKt__StringsKt.trimStart((CharSequence) encodedValue);
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(trimStart.toString(), EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, false, 2, null);
        if (!startsWith$default) {
            return encodedValue;
        }
        trimEnd = StringsKt__StringsKt.trimEnd((CharSequence) encodedValue);
        endsWith$default = StringsKt__StringsJVMKt.endsWith$default(trimEnd.toString(), EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, false, 2, null);
        if (!endsWith$default) {
            return encodedValue;
        }
        trim = StringsKt__StringsKt.trim((CharSequence) encodedValue);
        removeSurrounding = StringsKt__StringsKt.removeSurrounding(trim.toString(), (CharSequence) EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
        return removeSurrounding;
    }

    @KtorExperimentalAPI
    @NotNull
    public static final String encodeCookieValue(@NotNull String value, @NotNull CookieEncoding encoding) {
        boolean contains$default;
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(encoding, "encoding");
        int i = WhenMappings.$EnumSwitchMapping$0[encoding.ordinal()];
        boolean z = false;
        if (i == 1) {
            int i2 = 0;
            while (true) {
                if (i2 >= value.length()) {
                    break;
                } else if (shouldEscapeInCookies(value.charAt(i2))) {
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                throw new IllegalArgumentException("The cookie value contains characters that couldn't be encoded in RAW format. Consider URL_ENCODING mode");
            }
            return value;
        } else if (i != 2) {
            if (i == 3) {
                return Base64Kt.encodeBase64(value);
            }
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            return CodecsKt.encodeURLQueryComponent$default(value, true, true, null, 4, null);
        } else {
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) value, '\"', false, 2, (Object) null);
            if (contains$default) {
                throw new IllegalArgumentException("The cookie value contains characters that couldn't be encoded in RAW format. Consider URL_ENCODING mode");
            }
            int i3 = 0;
            while (true) {
                if (i3 >= value.length()) {
                    break;
                } else if (shouldEscapeInCookies(value.charAt(i3))) {
                    z = true;
                    break;
                } else {
                    i3++;
                }
            }
            if (!z) {
                return value;
            }
            return '\"' + value + '\"';
        }
    }

    @KtorExperimentalAPI
    @NotNull
    public static final Map<String, String> parseClientCookiesHeader(@NotNull String cookiesHeader, boolean z) {
        Sequence map;
        Sequence filter;
        Sequence map2;
        Map<String, String> map3;
        Intrinsics.checkParameterIsNotNull(cookiesHeader, "cookiesHeader");
        map = SequencesKt___SequencesKt.map(Regex.findAll$default(clientCookieHeaderPattern, cookiesHeader, 0, 2, null), CookieKt$parseClientCookiesHeader$1.INSTANCE);
        filter = SequencesKt___SequencesKt.filter(map, new CookieKt$parseClientCookiesHeader$2(z));
        map2 = SequencesKt___SequencesKt.map(filter, CookieKt$parseClientCookiesHeader$3.INSTANCE);
        map3 = MapsKt__MapsKt.toMap(map2);
        return map3;
    }

    @KtorExperimentalAPI
    @NotNull
    public static /* synthetic */ Map parseClientCookiesHeader$default(String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return parseClientCookiesHeader(str, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0128 A[SYNTHETIC] */
    @io.ktor.util.KtorExperimentalAPI
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final io.ktor.http.Cookie parseServerSetCookieHeader(@org.jetbrains.annotations.NotNull java.lang.String r18) {
        /*
            Method dump skipped, instructions count: 328
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.CookieKt.parseServerSetCookieHeader(java.lang.String):io.ktor.http.Cookie");
    }

    @KtorExperimentalAPI
    @NotNull
    public static final String renderCookieHeader(@NotNull Cookie cookie) {
        Intrinsics.checkParameterIsNotNull(cookie, "cookie");
        return renderSetCookieHeader(cookie.getName(), cookie.getValue(), cookie.getEncoding(), cookie.getMaxAge(), cookie.getExpires(), cookie.getDomain(), cookie.getPath(), cookie.getSecure(), cookie.getHttpOnly(), cookie.getExtensions(), false);
    }

    @KtorExperimentalAPI
    @NotNull
    public static final String renderSetCookieHeader(@NotNull Cookie cookie) {
        Intrinsics.checkParameterIsNotNull(cookie, "cookie");
        return renderSetCookieHeader$default(cookie.getName(), cookie.getValue(), cookie.getEncoding(), cookie.getMaxAge(), cookie.getExpires(), cookie.getDomain(), cookie.getPath(), cookie.getSecure(), cookie.getHttpOnly(), cookie.getExtensions(), false, 1024, null);
    }

    @KtorExperimentalAPI
    @NotNull
    public static /* synthetic */ String renderSetCookieHeader$default(String str, String str2, CookieEncoding cookieEncoding, int i, GMTDate gMTDate, String str3, String str4, boolean z, boolean z2, Map map, boolean z3, int i2, Object obj) {
        CookieEncoding cookieEncoding2 = (i2 & 4) != 0 ? CookieEncoding.URI_ENCODING : cookieEncoding;
        boolean z4 = false;
        int i3 = (i2 & 8) != 0 ? 0 : i;
        String str5 = null;
        GMTDate gMTDate2 = (i2 & 16) != 0 ? null : gMTDate;
        String str6 = (i2 & 32) != 0 ? null : str3;
        if ((i2 & 64) == 0) {
            str5 = str4;
        }
        boolean z5 = (i2 & 128) != 0 ? false : z;
        if ((i2 & 256) == 0) {
            z4 = z2;
        }
        return renderSetCookieHeader(str, str2, cookieEncoding2, i3, gMTDate2, str6, str5, z5, z4, (i2 & 512) != 0 ? MapsKt__MapsKt.emptyMap() : map, (i2 & 1024) != 0 ? true : z3);
    }

    private static final boolean shouldEscapeInCookies(char c) {
        boolean isWhitespace;
        isWhitespace = CharsKt__CharJVMKt.isWhitespace(c);
        return isWhitespace || Intrinsics.compare((int) c, 32) < 0 || cookieCharsShouldBeEscaped.contains(Character.valueOf(c));
    }

    @KtorExperimentalAPI
    @NotNull
    public static final String renderSetCookieHeader(@NotNull String name, @NotNull String value, @NotNull CookieEncoding encoding, int i, @Nullable GMTDate gMTDate, @Nullable String str, @Nullable String str2, boolean z, boolean z2, @NotNull Map<String, String> extensions, boolean z3) {
        String str3;
        String str4;
        List listOf;
        List plus;
        List plus2;
        String joinToString$default;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(encoding, "encoding");
        Intrinsics.checkParameterIsNotNull(extensions, "extensions");
        String[] strArr = new String[7];
        StringBuilder outline108 = GeneratedOutlineSupport1.outline108(assertCookieName(name), Chars.EQ);
        outline108.append(encodeCookieValue(value.toString(), encoding));
        strArr[0] = outline108.toString();
        String str5 = null;
        Integer valueOf = i > 0 ? Integer.valueOf(i) : null;
        String str6 = "";
        strArr[1] = valueOf != null ? "Max-Age" + Chars.EQ + valueOf : str6;
        if (gMTDate != null) {
            str5 = DateUtilsKt.toHttpDate(gMTDate);
        }
        strArr[2] = str5 != null ? "Expires" + Chars.EQ + ((Object) str5) : str6;
        CookieEncoding cookieEncoding = CookieEncoding.RAW;
        if (str != null) {
            StringBuilder outline1082 = GeneratedOutlineSupport1.outline108(MAPCookie.KEY_DOMAIN, Chars.EQ);
            outline1082.append(encodeCookieValue(str.toString(), cookieEncoding));
            str3 = outline1082.toString();
        } else {
            str3 = str6;
        }
        strArr[3] = str3;
        CookieEncoding cookieEncoding2 = CookieEncoding.RAW;
        if (str2 != null) {
            StringBuilder outline1083 = GeneratedOutlineSupport1.outline108(MAPCookie.KEY_PATH, Chars.EQ);
            outline1083.append(encodeCookieValue(str2.toString(), cookieEncoding2));
            str4 = outline1083.toString();
        } else {
            str4 = str6;
        }
        strArr[4] = str4;
        strArr[5] = z ? MAPCookie.KEY_SECURE : str6;
        strArr[6] = z2 ? MAPCookie.KEY_HTTP_ONLY : str6;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) strArr);
        ArrayList arrayList = new ArrayList(extensions.size());
        for (Map.Entry<String, String> entry : extensions.entrySet()) {
            String assertCookieName = assertCookieName(entry.getKey());
            String value2 = entry.getValue();
            if (value2 != null) {
                StringBuilder outline1084 = GeneratedOutlineSupport1.outline108(assertCookieName, Chars.EQ);
                outline1084.append(encodeCookieValue(value2.toString(), encoding));
                assertCookieName = outline1084.toString();
            }
            arrayList.add(assertCookieName);
        }
        plus = CollectionsKt___CollectionsKt.plus((Collection) listOf, (Iterable) arrayList);
        if (z3) {
            str6 = "$x-enc";
            String name2 = encoding.name();
            CookieEncoding cookieEncoding3 = CookieEncoding.RAW;
            if (name2 != null) {
                StringBuilder outline1085 = GeneratedOutlineSupport1.outline108(str6, Chars.EQ);
                outline1085.append(encodeCookieValue(name2.toString(), cookieEncoding3));
                str6 = outline1085.toString();
            }
        }
        plus2 = CollectionsKt___CollectionsKt.plus((Collection<? extends Object>) ((Collection) plus), (Object) str6);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : plus2) {
            if (((String) obj).length() > 0) {
                arrayList2.add(obj);
            }
        }
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList2, "; ", null, null, 0, null, null, 62, null);
        return joinToString$default;
    }
}
