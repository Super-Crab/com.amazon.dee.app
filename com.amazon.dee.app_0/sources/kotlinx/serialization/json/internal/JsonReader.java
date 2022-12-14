package kotlinx.serialization.json.internal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.json.JsonExceptionsKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: JsonReader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\b\n\u0002\u0010\u0001\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\fH\u0002J\u0018\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\fH\u0002J \u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\fH\u0002J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\fJ\u0018\u0010\"\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010#\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\fH\u0002J\u0018\u0010$\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\fH\u0002J\u0006\u0010%\u001a\u00020\u0014J.\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030(H\u0080\b¢\u0006\u0002\b)J*\u0010*\u001a\u00020\u00142\u0006\u0010+\u001a\u00020\u00112\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00030-H\u0080\b¢\u0006\u0002\b.J\u0006\u0010/\u001a\u00020\u0014J\u0006\u00100\u001a\u00020\u0003J\u0006\u00101\u001a\u00020\u0003J\b\u00102\u001a\u00020\u0003H\u0002J\u0006\u00103\u001a\u00020\u0003J\b\u00104\u001a\u00020\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\r\u0010\nR\u000e\u0010\u000e\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lkotlinx/serialization/json/internal/JsonReader;", "", "source", "", "(Ljava/lang/String;)V", "buf", "", "canBeginValue", "", "getCanBeginValue", "()Z", "currentPosition", "", "isDone", "length", "offset", "tokenClass", "", "tokenPosition", "append", "", "ch", "", "appendEsc", "startPosition", "appendHex", "startPos", "appendRange", "fromIndex", "toIndex", "fail", "", "message", ViewProps.POSITION, "fromHexChar", "nextLiteral", "nextString", "nextToken", "require", "condition", "Lkotlin/Function0;", "require$kotlinx_serialization_runtime", "requireTokenClass", "expected", "errorMessage", "Lkotlin/Function1;", "requireTokenClass$kotlinx_serialization_runtime", "skipElement", "takeBooleanStringUnquoted", "takeString", "takeStringInternal", "takeStringQuoted", "toString", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class JsonReader {
    private char[] buf;
    @JvmField
    public int currentPosition;
    private int length;
    private int offset;
    private final String source;
    @JvmField
    public byte tokenClass;
    private int tokenPosition;

    public JsonReader(@NotNull String source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.source = source;
        this.tokenClass = (byte) 12;
        this.offset = -1;
        this.buf = new char[16];
        nextToken();
    }

    private final void append(char c) {
        int i = this.length;
        char[] cArr = this.buf;
        if (i >= cArr.length) {
            char[] copyOf = Arrays.copyOf(cArr, cArr.length * 2);
            Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
            this.buf = copyOf;
        }
        char[] cArr2 = this.buf;
        int i2 = this.length;
        this.length = i2 + 1;
        cArr2[i2] = c;
    }

    private final int appendEsc(String str, int i) {
        boolean z = false;
        if (i < str.length()) {
            int i2 = i + 1;
            char charAt = str.charAt(i);
            if (charAt == 'u') {
                return appendHex(str, i2);
            }
            char escapeToChar = JsonReaderKt.escapeToChar(charAt);
            if (escapeToChar != 0) {
                z = true;
            }
            if (z) {
                append(escapeToChar);
                return i2;
            }
            fail("Invalid escaped char '" + charAt + Chars.QUOTE, i2);
            throw null;
        }
        fail("Unexpected EOF after escape character", i);
        throw null;
    }

    private final int appendHex(String str, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int fromHexChar = (fromHexChar(str, i) << 12) + (fromHexChar(str, i2) << 8);
        int i4 = i3 + 1;
        int fromHexChar2 = fromHexChar + (fromHexChar(str, i3) << 4);
        int i5 = i4 + 1;
        append((char) (fromHexChar2 + fromHexChar(str, i4)));
        return i5;
    }

    private final void appendRange(String str, int i, int i2) {
        int coerceAtLeast;
        int i3 = i2 - i;
        int i4 = this.length;
        int i5 = i4 + i3;
        char[] cArr = this.buf;
        if (i5 > cArr.length) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(i5, cArr.length * 2);
            char[] copyOf = Arrays.copyOf(cArr, coerceAtLeast);
            Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
            this.buf = copyOf;
        }
        for (int i6 = 0; i6 < i3; i6++) {
            this.buf[i4 + i6] = str.charAt(i + i6);
        }
        this.length += i3;
    }

    public static /* synthetic */ Void fail$default(JsonReader jsonReader, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = jsonReader.currentPosition;
        }
        return jsonReader.fail(str, i);
    }

    private final int fromHexChar(String str, int i) {
        if (i < str.length()) {
            char charAt = str.charAt(i);
            if ('0' <= charAt && '9' >= charAt) {
                return charAt - '0';
            }
            char c = 'a';
            if ('a' > charAt || 'f' < charAt) {
                c = 'A';
                if ('A' > charAt || 'F' < charAt) {
                    fail$default(this, GeneratedOutlineSupport1.outline48("Invalid toHexChar char '", charAt, "' in unicode escape"), 0, 2, null);
                    throw null;
                }
            }
            return (charAt - c) + 10;
        }
        fail("Unexpected EOF during unicode escape", i);
        throw null;
    }

    private final void nextLiteral(String str, int i) {
        boolean rangeEquals;
        this.tokenPosition = i;
        this.offset = i;
        while (i < str.length() && JsonReaderKt.charToTokenClass(str.charAt(i)) == 0) {
            i++;
        }
        this.currentPosition = i;
        int i2 = this.offset;
        this.length = i - i2;
        rangeEquals = JsonReaderKt.rangeEquals(str, i2, this.length, "null");
        this.tokenClass = rangeEquals ? (byte) 10 : (byte) 0;
    }

    private final void nextString(String str, int i) {
        this.tokenPosition = i;
        this.length = 0;
        int i2 = i + 1;
        int i3 = i2;
        while (true) {
            int i4 = i3;
            while (str.charAt(i3) != '\"') {
                if (str.charAt(i3) == '\\') {
                    break;
                }
                i3++;
                if (i3 >= str.length()) {
                    fail("EOF", i3);
                    throw null;
                }
            }
            if (i4 == i2) {
                this.offset = i4;
                this.length = i3 - i4;
            } else {
                appendRange(str, i4, i3);
                this.offset = -1;
            }
            this.currentPosition = i3 + 1;
            this.tokenClass = (byte) 1;
            return;
            appendRange(str, i4, i3);
            i3 = appendEsc(str, i3 + 1);
        }
    }

    public static /* synthetic */ void require$kotlinx_serialization_runtime$default(JsonReader jsonReader, boolean z, int i, Function0 message, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = jsonReader.currentPosition;
        }
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (z) {
            return;
        }
        jsonReader.fail((String) message.mo12560invoke(), i);
        throw null;
    }

    private final String takeStringInternal() {
        String substring;
        int i = this.offset;
        if (i < 0) {
            substring = new String(this.buf, 0, this.length);
        } else {
            String str = this.source;
            int i2 = this.length + i;
            if (str == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            substring = str.substring(i, i2);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        }
        nextToken();
        return substring;
    }

    @NotNull
    public final Void fail(@NotNull String message, int i) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        throw JsonExceptionsKt.JsonDecodingException(i, message, this.source);
    }

    public final boolean getCanBeginValue() {
        byte b = this.tokenClass;
        return b == 0 || b == 1 || b == 6 || b == 8 || b == 10;
    }

    public final boolean isDone() {
        return this.tokenClass == 12;
    }

    public final void nextToken() {
        String str = this.source;
        int i = this.currentPosition;
        while (i < str.length()) {
            byte charToTokenClass = JsonReaderKt.charToTokenClass(str.charAt(i));
            if (charToTokenClass != 3) {
                if (charToTokenClass == 0) {
                    nextLiteral(str, i);
                    return;
                } else if (charToTokenClass == 1) {
                    nextString(str, i);
                    return;
                } else {
                    this.tokenPosition = i;
                    this.tokenClass = charToTokenClass;
                    this.currentPosition = i + 1;
                    return;
                }
            }
            i++;
        }
        this.tokenPosition = i;
        this.tokenClass = (byte) 12;
    }

    public final void require$kotlinx_serialization_runtime(boolean z, int i, @NotNull Function0<String> message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (z) {
            return;
        }
        fail(message.mo12560invoke(), i);
        throw null;
    }

    public final void requireTokenClass$kotlinx_serialization_runtime(byte b, @NotNull Function1<? super Character, String> errorMessage) {
        Intrinsics.checkParameterIsNotNull(errorMessage, "errorMessage");
        byte b2 = this.tokenClass;
        if (b2 == b) {
            return;
        }
        fail(errorMessage.mo12165invoke(Character.valueOf((char) b2)), this.tokenPosition);
        throw null;
    }

    public final void skipElement() {
        byte b = this.tokenClass;
        if (b != 6 && b != 8) {
            nextToken();
            return;
        }
        ArrayList arrayList = new ArrayList();
        do {
            byte b2 = this.tokenClass;
            switch (b2) {
                case 6:
                case 8:
                    arrayList.add(Byte.valueOf(b2));
                    break;
                case 7:
                    if (((Number) CollectionsKt.last((List<? extends Object>) arrayList)).byteValue() == 6) {
                        arrayList.remove(arrayList.size() - 1);
                        break;
                    } else {
                        throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found } instead of ]", this.source);
                    }
                case 9:
                    if (((Number) CollectionsKt.last((List<? extends Object>) arrayList)).byteValue() == 8) {
                        arrayList.remove(arrayList.size() - 1);
                        break;
                    } else {
                        throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found ] instead of }", this.source);
                    }
            }
            nextToken();
        } while (!arrayList.isEmpty());
    }

    @NotNull
    public final String takeBooleanStringUnquoted() {
        if (this.tokenClass == 0) {
            return takeStringInternal();
        }
        fail("Expected start of the unquoted boolean literal. Use 'JsonConfiguration.isLenient = true' to accept non-compliant JSON", this.tokenPosition);
        throw null;
    }

    @NotNull
    public final String takeString() {
        byte b = this.tokenClass;
        if (b != 0 && b != 1) {
            fail("Expected string or non-null literal", this.tokenPosition);
            throw null;
        }
        return takeStringInternal();
    }

    @NotNull
    public final String takeStringQuoted() {
        if (this.tokenClass == 1) {
            return takeStringInternal();
        }
        fail("Expected string literal with quotes. Use 'JsonConfiguration.isLenient = true' to accept non-compliant JSON", this.tokenPosition);
        throw null;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("JsonReader(source='");
        outline107.append(this.source);
        outline107.append("', currentPosition=");
        outline107.append(this.currentPosition);
        outline107.append(", tokenClass=");
        outline107.append((int) this.tokenClass);
        outline107.append(", tokenPosition=");
        outline107.append(this.tokenPosition);
        outline107.append(", offset=");
        return GeneratedOutlineSupport1.outline85(outline107, this.offset, ')');
    }
}
