package io.ktor.http;

import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.util.KtorExperimentalAPI;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.CharRange;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.Typography;
import kotlinx.io.charsets.EncodingKt;
import kotlinx.io.core.ByteReadPacket;
import kotlinx.io.core.IoBuffer;
import kotlinx.io.core.internal.UnsafeKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.jetbrains.annotations.NotNull;
/* compiled from: Codecs.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0000\n\u0002\u0010 \n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0002\u001a8\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015H\u0002\u001a0\u0010\u0016\u001a\u00020\f*\u00020\f2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015H\u0002\u001a.\u0010\u0017\u001a\u00020\f*\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\f\b\u0002\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015H\u0007\u001a6\u0010\u0018\u001a\u00020\f*\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\f\b\u0002\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015\u001a\n\u0010\u0019\u001a\u00020\f*\u00020\f\u001a\u0014\u0010\u001a\u001a\u00020\f*\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\u0012\u001a\n\u0010\u001c\u001a\u00020\f*\u00020\f\u001a,\u0010\u001d\u001a\u00020\f*\u00020\f2\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u00122\f\b\u0002\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015\u001a \u0010\u001f\u001a\u00020 *\u00020!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 0#H\u0002\u001a\f\u0010$\u001a\u00020\f*\u00020\u0004H\u0002\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"HEX_ALPHABET", "", "", "OAUTH_SYMBOLS", "", "URL_ALPHABET", "URL_PROTOCOL_PART", "VALID_PATH_PART", "charToHexDigit", "", "c2", "decodeImpl", "", "", "start", "end", "prefixEnd", "plusIsSpace", "", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lkotlinx/io/charsets/Charset;", "decodeScan", "decodeURLPart", "decodeURLQueryComponent", "encodeOAuth", "encodeURLParameter", "spaceToPlus", "encodeURLPath", "encodeURLQueryComponent", "encodeFull", "forEach", "", "Lkotlinx/io/core/ByteReadPacket;", "block", "Lkotlin/Function1;", "percentEncode", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class CodecsKt {
    private static final List<Character> HEX_ALPHABET;
    private static final List<Byte> OAUTH_SYMBOLS;
    private static final List<Byte> URL_ALPHABET;
    private static final List<Byte> URL_PROTOCOL_PART;
    private static final List<Character> VALID_PATH_PART;

    static {
        List plus;
        List<Character> plus2;
        int collectionSizeOrDefault;
        List plus3;
        List<Character> plus4;
        List<Character> listOf;
        int collectionSizeOrDefault2;
        List<Character> listOf2;
        List<Character> listOf3;
        int collectionSizeOrDefault3;
        plus = CollectionsKt___CollectionsKt.plus((Iterable) new CharRange('a', 'z'), (Iterable) new CharRange('A', Matrix.MATRIX_TYPE_ZERO));
        plus2 = CollectionsKt___CollectionsKt.plus((Collection) plus, (Iterable) new CharRange('0', '9'));
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(plus2, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Character ch : plus2) {
            arrayList.add(Byte.valueOf((byte) ch.charValue()));
        }
        URL_ALPHABET = arrayList;
        plus3 = CollectionsKt___CollectionsKt.plus((Iterable) new CharRange('a', 'f'), (Iterable) new CharRange('A', 'F'));
        plus4 = CollectionsKt___CollectionsKt.plus((Collection) plus3, (Iterable) new CharRange('0', '9'));
        HEX_ALPHABET = plus4;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Character[]{Character.valueOf(JsonReaderKt.COLON), '/', Character.valueOf(Constants.DEFAULT_IMAGE_CHAR), '#', Character.valueOf(JsonReaderKt.BEGIN_LIST), Character.valueOf(JsonReaderKt.END_LIST), '@', '!', '$', Character.valueOf(Typography.amp), Character.valueOf(Chars.QUOTE), '(', ')', '*', Character.valueOf(JsonReaderKt.COMMA), ';', Character.valueOf(Chars.EQ), '-', '.', '_', '~', '+'});
        collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
        for (Character ch2 : listOf) {
            arrayList2.add(Byte.valueOf((byte) ch2.charValue()));
        }
        URL_PROTOCOL_PART = arrayList2;
        listOf2 = CollectionsKt__CollectionsKt.listOf((Object[]) new Character[]{Character.valueOf(JsonReaderKt.COLON), '@', '!', '$', Character.valueOf(Typography.amp), Character.valueOf(Chars.QUOTE), '(', ')', '*', '+', Character.valueOf(JsonReaderKt.COMMA), ';', Character.valueOf(Chars.EQ), '-', '.', '_', '~'});
        VALID_PATH_PART = listOf2;
        listOf3 = CollectionsKt__CollectionsKt.listOf((Object[]) new Character[]{'-', '.', '_', '~'});
        collectionSizeOrDefault3 = CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf3, 10);
        ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault3);
        for (Character ch3 : listOf3) {
            arrayList3.add(Byte.valueOf((byte) ch3.charValue()));
        }
        OAUTH_SYMBOLS = arrayList3;
    }

    private static final int charToHexDigit(char c) {
        if ('0' <= c && '9' >= c) {
            return c - '0';
        }
        char c2 = 'A';
        if ('A' > c || 'F' < c) {
            c2 = 'a';
            if ('a' > c || 'f' < c) {
                return -1;
            }
        }
        return (c - c2) + 10;
    }

    private static final String decodeImpl(@NotNull CharSequence charSequence, int i, int i2, int i3, boolean z, Charset charset) {
        int i4 = i2 - i;
        if (i4 > 255) {
            i4 /= 3;
        }
        StringBuilder sb = new StringBuilder(i4);
        if (i3 > i) {
            sb.append(charSequence, i, i3);
        }
        byte[] bArr = null;
        while (i3 < i2) {
            char charAt = charSequence.charAt(i3);
            if (z && charAt == '+') {
                sb.append(Chars.SPACE);
            } else if (charAt == '%') {
                if (bArr == null) {
                    bArr = new byte[(i2 - i3) / 3];
                }
                int i5 = 0;
                while (i3 < i2 && charSequence.charAt(i3) == '%') {
                    int i6 = i3 + 2;
                    if (i6 < i2) {
                        int i7 = i3 + 1;
                        int charToHexDigit = charToHexDigit(charSequence.charAt(i7));
                        int charToHexDigit2 = charToHexDigit(charSequence.charAt(i6));
                        if (charToHexDigit != -1 && charToHexDigit2 != -1) {
                            bArr[i5] = (byte) ((charToHexDigit * 16) + charToHexDigit2);
                            i3 += 3;
                            i5++;
                        } else {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Wrong HEX escape: %");
                            outline107.append(charSequence.charAt(i7));
                            outline107.append(charSequence.charAt(i6));
                            outline107.append(", in ");
                            outline107.append(charSequence);
                            outline107.append(", at ");
                            outline107.append(i3);
                            throw new URLDecodeException(outline107.toString());
                        }
                    } else {
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Incomplete trailing HEX escape: ");
                        outline1072.append(charSequence.subSequence(i3, charSequence.length()).toString());
                        outline1072.append(", in ");
                        outline1072.append(charSequence);
                        outline1072.append(" at ");
                        outline1072.append(i3);
                        throw new URLDecodeException(outline1072.toString());
                    }
                }
                sb.append(new String(bArr, 0, i5, charset));
            } else {
                sb.append(charAt);
            }
            i3++;
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
        return sb2;
    }

    private static final String decodeScan(@NotNull String str, int i, int i2, boolean z, Charset charset) {
        for (int i3 = i; i3 < i2; i3++) {
            char charAt = str.charAt(i3);
            if (charAt == '%' || (z && charAt == '+')) {
                return decodeImpl(str, i, i2, i3, z, charset);
            }
        }
        if (i == 0 && i2 == str.length()) {
            return str;
        }
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String substring = str.substring(i, i2);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @KtorExperimentalAPI
    @NotNull
    public static final String decodeURLPart(@NotNull String receiver$0, int i, int i2, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return decodeScan(receiver$0, i, i2, false, charset);
    }

    @KtorExperimentalAPI
    @NotNull
    public static /* synthetic */ String decodeURLPart$default(String str, int i, int i2, Charset charset, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            charset = Charsets.UTF_8;
        }
        return decodeURLPart(str, i, i2, charset);
    }

    @NotNull
    public static final String decodeURLQueryComponent(@NotNull String receiver$0, int i, int i2, boolean z, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return decodeScan(receiver$0, i, i2, z, charset);
    }

    @NotNull
    public static /* synthetic */ String decodeURLQueryComponent$default(String str, int i, int i2, boolean z, Charset charset, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            charset = Charsets.UTF_8;
        }
        return decodeURLQueryComponent(str, i, i2, z, charset);
    }

    @NotNull
    public static final String encodeOAuth(@NotNull String receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return encodeURLParameter$default(receiver$0, false, 1, null);
    }

    @NotNull
    public static final String encodeURLParameter(@NotNull String receiver$0, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        StringBuilder sb = new StringBuilder();
        CharsetEncoder newEncoder = Charsets.UTF_8.newEncoder();
        Intrinsics.checkExpressionValueIsNotNull(newEncoder, "Charsets.UTF_8.newEncoder()");
        forEach(EncodingKt.encode$default(newEncoder, receiver$0, 0, 0, 6, null), new CodecsKt$encodeURLParameter$$inlined$buildString$lambda$1(sb, receiver$0, z));
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public static /* synthetic */ String encodeURLParameter$default(String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return encodeURLParameter(str, z);
    }

    @NotNull
    public static final String encodeURLPath(@NotNull String receiver$0) {
        int i;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (i2 < receiver$0.length()) {
            char charAt = receiver$0.charAt(i2);
            if (charAt != '/') {
                byte b = (byte) charAt;
                if (!URL_ALPHABET.contains(Byte.valueOf(b)) && !VALID_PATH_PART.contains(Character.valueOf(charAt))) {
                    if (charAt == '%' && (i = i2 + 2) < receiver$0.length()) {
                        int i3 = i2 + 1;
                        if (HEX_ALPHABET.contains(Character.valueOf(receiver$0.charAt(i3))) && HEX_ALPHABET.contains(Character.valueOf(receiver$0.charAt(i)))) {
                            sb.append(charAt);
                            sb.append(receiver$0.charAt(i3));
                            sb.append(receiver$0.charAt(i));
                            i2 += 3;
                        }
                    }
                    sb.append(percentEncode(b));
                    i2++;
                }
            }
            sb.append(charAt);
            i2++;
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public static final String encodeURLQueryComponent(@NotNull String receiver$0, boolean z, boolean z2, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        StringBuilder sb = new StringBuilder();
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkExpressionValueIsNotNull(newEncoder, "charset.newEncoder()");
        forEach(EncodingKt.encode$default(newEncoder, receiver$0, 0, 0, 6, null), new CodecsKt$encodeURLQueryComponent$$inlined$buildString$lambda$1(sb, receiver$0, charset, z2, z));
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public static /* synthetic */ String encodeURLQueryComponent$default(String str, boolean z, boolean z2, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            charset = Charsets.UTF_8;
        }
        return encodeURLQueryComponent(str, z, z2, charset);
    }

    private static final void forEach(@NotNull ByteReadPacket byteReadPacket, Function1<? super Byte, Unit> function1) {
        boolean z = true;
        IoBuffer prepareReadFirstHead = UnsafeKt.prepareReadFirstHead(byteReadPacket, 1);
        if (prepareReadFirstHead != null) {
            while (true) {
                try {
                    if (prepareReadFirstHead.canRead()) {
                        function1.mo12165invoke(Byte.valueOf(prepareReadFirstHead.readByte()));
                    } else {
                        try {
                            prepareReadFirstHead = UnsafeKt.prepareReadNextHead(byteReadPacket, prepareReadFirstHead);
                            if (prepareReadFirstHead == null) {
                                return;
                            }
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(byteReadPacket, prepareReadFirstHead);
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String percentEncode(byte b) {
        int checkRadix;
        String padStart;
        checkRadix = CharsKt__CharJVMKt.checkRadix(16);
        String num = Integer.toString(b & 255, checkRadix);
        Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        String upperCase = num.toUpperCase();
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
        StringBuilder sb = new StringBuilder();
        sb.append('%');
        padStart = StringsKt__StringsKt.padStart(upperCase, 2, '0');
        sb.append(padStart);
        return sb.toString();
    }
}
