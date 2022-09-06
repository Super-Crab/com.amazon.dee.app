package io.ktor.http.auth;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.http.auth.HttpAuthHeader;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.StringsKt___StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpAuthHeader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u0006\u001a\u0014\u0010\n\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a\f\u0010\r\u001a\u00020\u0006*\u00020\u0006H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"authSchemePattern", "Lkotlin/text/Regex;", "escapeRegex", "parameterPattern", "token68Pattern", "valuePatternPart", "", "parseAuthorizationHeader", "Lio/ktor/http/auth/HttpAuthHeader;", "headerValue", "substringAfterMatch", "result", "Lkotlin/text/MatchResult;", "unescapeIfQuoted", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpAuthHeaderKt {
    private static final Regex escapeRegex;
    private static final Regex parameterPattern;
    private static final String valuePatternPart = "(\"((\\\\.)|[^\\\\\"])*\")|[^\\s,]*";
    private static final Regex token68Pattern = new Regex("[a-zA-Z0-9\\-._~+/]+=*");
    private static final Regex authSchemePattern = new Regex("\\S+");

    static {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\\s*,?\\s*(");
        outline107.append(token68Pattern);
        outline107.append(")\\s*=\\s*((\"((\\\\.)|[^\\\\\"])*\")|[^\\s,]*)\\s*,?\\s*");
        parameterPattern = new Regex(outline107.toString());
        escapeRegex = new Regex("\\\\.");
    }

    @Nullable
    public static final HttpAuthHeader parseAuthorizationHeader(@NotNull String headerValue) {
        CharSequence trimStart;
        boolean isBlank;
        Intrinsics.checkParameterIsNotNull(headerValue, "headerValue");
        MatchResult find$default = Regex.find$default(authSchemePattern, headerValue, 0, 2, null);
        if (find$default != null) {
            String value = find$default.getValue();
            String substringAfterMatch = substringAfterMatch(headerValue, find$default);
            if (substringAfterMatch != null) {
                trimStart = StringsKt__StringsKt.trimStart((CharSequence) substringAfterMatch);
                String obj = trimStart.toString();
                MatchResult find$default2 = Regex.find$default(token68Pattern, obj, 0, 2, null);
                if (find$default2 != null) {
                    isBlank = StringsKt__StringsJVMKt.isBlank(substringAfterMatch(obj, find$default2));
                    if (isBlank) {
                        return new HttpAuthHeader.Single(value, find$default2.getValue());
                    }
                }
                Sequence<MatchResult> findAll$default = Regex.findAll$default(parameterPattern, obj, 0, 2, null);
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (MatchResult matchResult : findAll$default) {
                    MatchGroup matchGroup = matchResult.getGroups().get(1);
                    if (matchGroup == null) {
                        Intrinsics.throwNpe();
                    }
                    String value2 = matchGroup.getValue();
                    MatchGroup matchGroup2 = matchResult.getGroups().get(2);
                    if (matchGroup2 == null) {
                        Intrinsics.throwNpe();
                    }
                    linkedHashMap.put(value2, unescapeIfQuoted(matchGroup2.getValue()));
                }
                return new HttpAuthHeader.Parameterized(value, linkedHashMap, (HeaderValueEncoding) null, 4, (DefaultConstructorMarker) null);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return null;
    }

    private static final String substringAfterMatch(@NotNull String str, MatchResult matchResult) {
        String drop;
        drop = StringsKt___StringsKt.drop(str, matchResult.getRange().mo11372getEndInclusive().intValue() + (!matchResult.getRange().isEmpty()));
        return drop;
    }

    private static final String unescapeIfQuoted(@NotNull String str) {
        boolean startsWith$default;
        boolean endsWith$default;
        String removeSurrounding;
        startsWith$default = StringsKt__StringsKt.startsWith$default((CharSequence) str, '\"', false, 2, (Object) null);
        if (startsWith$default) {
            endsWith$default = StringsKt__StringsKt.endsWith$default((CharSequence) str, '\"', false, 2, (Object) null);
            if (!endsWith$default) {
                return str;
            }
            removeSurrounding = StringsKt__StringsKt.removeSurrounding(str, (CharSequence) EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            return escapeRegex.replace(removeSurrounding, HttpAuthHeaderKt$unescapeIfQuoted$1.INSTANCE);
        }
        return str;
    }
}
