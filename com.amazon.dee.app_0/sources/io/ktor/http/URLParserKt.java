package io.ktor.http;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.util.CharsetKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: URLParser.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a \u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0002\u001a$\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0002\u001a\u001c\u0010\f\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0002\u001a\f\u0010\r\u001a\u00020\u000e*\u00020\u0007H\u0002\u001a\u0012\u0010\u000f\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0014\u0010\u0010\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0011"}, d2 = {"count", "", "urlString", "", "startIndex", "endIndex", "char", "", "findScheme", "fillHost", "", "Lio/ktor/http/URLBuilder;", "indexOfColonInHostPort", "isLetter", "", "takeFrom", "takeFromUnsafe", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class URLParserKt {
    private static final int count(String str, int i, int i2, char c) {
        int i3 = 0;
        while (true) {
            int i4 = i + i3;
            if (i4 >= i2 || str.charAt(i4) != c) {
                break;
            }
            i3++;
        }
        return i3;
    }

    private static final void fillHost(@NotNull URLBuilder uRLBuilder, String str, int i, int i2) {
        Integer valueOf = Integer.valueOf(indexOfColonInHostPort(str, i, i2));
        if (!(valueOf.intValue() > 0)) {
            valueOf = null;
        }
        int intValue = valueOf != null ? valueOf.intValue() : i2;
        if (str != null) {
            String substring = str.substring(i, intValue);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            uRLBuilder.setHost(substring);
            int i3 = intValue + 1;
            if (i3 < i2) {
                String substring2 = str.substring(i3, i2);
                Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                uRLBuilder.setPort(Integer.parseInt(substring2));
                return;
            }
            uRLBuilder.setPort(0);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private static final int findScheme(String str, int i, int i2) {
        while (i < i2) {
            char charAt = str.charAt(i);
            if (charAt == ':') {
                return i;
            }
            if (!isLetter(charAt)) {
                return -1;
            }
            i++;
        }
        return -1;
    }

    private static final int indexOfColonInHostPort(@NotNull String str, int i, int i2) {
        boolean z = false;
        while (i < i2) {
            char charAt = str.charAt(i);
            if (charAt != ':') {
                if (charAt == '[') {
                    z = true;
                } else if (charAt == ']') {
                    z = false;
                }
            } else if (!z) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static final boolean isLetter(char c) {
        char lowerCase = Character.toLowerCase(c);
        return 'a' <= lowerCase && 'z' >= lowerCase;
    }

    @NotNull
    public static final URLBuilder takeFrom(@NotNull URLBuilder receiver$0, @NotNull String urlString) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(urlString, "urlString");
        try {
            return takeFromUnsafe(receiver$0, urlString);
        } catch (Throwable th) {
            throw new URLParserException(urlString, th);
        }
    }

    @NotNull
    public static final URLBuilder takeFromUnsafe(@NotNull URLBuilder receiver$0, @NotNull String urlString) {
        int indexOfAny$default;
        int indexOf$default;
        int lastIndexOf$default;
        int indexOfAny$default2;
        int intValue;
        boolean isWhitespace;
        boolean isWhitespace2;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(urlString, "urlString");
        int length = urlString.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            }
            isWhitespace2 = CharsKt__CharJVMKt.isWhitespace(urlString.charAt(i));
            if (!isWhitespace2) {
                break;
            }
            i++;
        }
        int length2 = urlString.length() - 1;
        while (true) {
            if (length2 < 0) {
                length2 = -1;
                break;
            }
            isWhitespace = CharsKt__CharJVMKt.isWhitespace(urlString.charAt(length2));
            if (!isWhitespace) {
                break;
            }
            length2--;
        }
        int i2 = length2 + 1;
        int findScheme = findScheme(urlString, i, i2);
        if (findScheme > 0) {
            String substring = urlString.substring(i, i + findScheme);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            receiver$0.setProtocol(URLProtocol.Companion.createOrDefault(substring));
            i += findScheme + 1;
        }
        int count = count(urlString, i, i2, '/');
        int i3 = i + count;
        if (count >= 2) {
            while (true) {
                indexOfAny$default2 = StringsKt__StringsKt.indexOfAny$default((CharSequence) urlString, CharsetKt.toCharArray("@/\\?#"), i3, false, 4, (Object) null);
                Integer valueOf = Integer.valueOf(indexOfAny$default2);
                if (!(valueOf.intValue() > 0)) {
                    valueOf = null;
                }
                intValue = valueOf != null ? valueOf.intValue() : i2;
                if (intValue >= i2 || urlString.charAt(intValue) != '@') {
                    break;
                }
                int indexOfColonInHostPort = indexOfColonInHostPort(urlString, i3, intValue);
                if (indexOfColonInHostPort != -1) {
                    String substring2 = urlString.substring(i3, indexOfColonInHostPort);
                    Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    receiver$0.setUser(substring2);
                    String substring3 = urlString.substring(indexOfColonInHostPort + 1, intValue);
                    Intrinsics.checkExpressionValueIsNotNull(substring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    receiver$0.setPassword(substring3);
                } else {
                    String substring4 = urlString.substring(i3, intValue);
                    Intrinsics.checkExpressionValueIsNotNull(substring4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    receiver$0.setUser(substring4);
                }
                i3 = intValue + 1;
            }
            fillHost(receiver$0, urlString, i3, intValue);
            i3 = intValue;
        }
        String str = "/";
        if (i3 >= i2) {
            receiver$0.setEncodedPath(str);
            return receiver$0;
        }
        if (count == 0) {
            lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) receiver$0.getEncodedPath(), '/', 0, false, 6, (Object) null);
            if (lastIndexOf$default == receiver$0.getEncodedPath().length() - 1) {
                str = receiver$0.getEncodedPath();
            } else if (lastIndexOf$default != -1) {
                String encodedPath = receiver$0.getEncodedPath();
                int i4 = lastIndexOf$default + 1;
                if (encodedPath == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                str = encodedPath.substring(0, i4);
                Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            }
        } else {
            str = "";
        }
        receiver$0.setEncodedPath(str);
        indexOfAny$default = StringsKt__StringsKt.indexOfAny$default((CharSequence) urlString, CharsetKt.toCharArray("?#"), i3, false, 4, (Object) null);
        Integer valueOf2 = Integer.valueOf(indexOfAny$default);
        if (!(valueOf2.intValue() > 0)) {
            valueOf2 = null;
        }
        int intValue2 = valueOf2 != null ? valueOf2.intValue() : i2;
        String substring5 = urlString.substring(i3, intValue2);
        Intrinsics.checkExpressionValueIsNotNull(substring5, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(receiver$0.getEncodedPath());
        outline107.append(CodecsKt.encodeURLPath(substring5));
        receiver$0.setEncodedPath(outline107.toString());
        if (intValue2 < i2 && urlString.charAt(intValue2) == '?') {
            int i5 = intValue2 + 1;
            if (i5 == i2) {
                receiver$0.setTrailingQuery(true);
                return receiver$0;
            }
            indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) urlString, '#', i5, false, 4, (Object) null);
            Integer valueOf3 = Integer.valueOf(indexOf$default);
            if (!(valueOf3.intValue() > 0)) {
                valueOf3 = null;
            }
            intValue2 = valueOf3 != null ? valueOf3.intValue() : i2;
            String substring6 = urlString.substring(i5, intValue2);
            Intrinsics.checkExpressionValueIsNotNull(substring6, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            QueryKt.parseQueryString$default(substring6, 0, 0, 6, null).forEach(new URLParserKt$takeFromUnsafe$1(receiver$0));
        }
        if (intValue2 < i2 && urlString.charAt(intValue2) == '#') {
            String substring7 = urlString.substring(intValue2 + 1, i2);
            Intrinsics.checkExpressionValueIsNotNull(substring7, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            receiver$0.setFragment(substring7);
        }
        return receiver$0;
    }
}
