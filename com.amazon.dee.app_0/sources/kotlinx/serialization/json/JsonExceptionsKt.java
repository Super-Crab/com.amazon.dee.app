package kotlinx.serialization.json;

import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.SerialDescriptor;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: JsonExceptions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\u0010\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\u0000\u001a \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0000\u001a\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0000\u001a\u0016\u0010\u0012\u001a\u00020\u0005*\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0002¨\u0006\u0013"}, d2 = {"InvalidFloatingPoint", "Lkotlinx/serialization/json/JsonEncodingException;", "value", "", "type", "", ContactsModuleConstants.OUTPUT, "key", "InvalidKeyKindException", "keyDescriptor", "Lkotlinx/serialization/SerialDescriptor;", "JsonDecodingException", "Lkotlinx/serialization/json/JsonDecodingException;", "offset", "", "message", "input", "UnknownKeyException", "minify", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class JsonExceptionsKt {
    @NotNull
    public static final JsonEncodingException InvalidFloatingPoint(@NotNull Number value, @NotNull String type, @NotNull String output) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(output, "output");
        return new JsonEncodingException(Chars.QUOTE + value + "' is not a valid '" + type + "' as per JSON specification. You can enable 'serializeSpecialFloatingPointValues' property to serialize such values\nCurrent output: " + minify$default(output, 0, 1, null));
    }

    @NotNull
    public static final JsonEncodingException InvalidKeyKindException(@NotNull SerialDescriptor keyDescriptor) {
        Intrinsics.checkParameterIsNotNull(keyDescriptor, "keyDescriptor");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Value of type '");
        outline107.append(keyDescriptor.getSerialName());
        outline107.append("' can't be used in JSON as a key in the map. ");
        outline107.append("It should have either primitive or enum kind, but its kind is '");
        outline107.append(keyDescriptor.mo12397getKind());
        outline107.append(".'\n");
        outline107.append("You can convert such maps to arrays [key1, value1, key2, value2,...] using 'allowStructuredMapKeys' property in JsonConfiguration");
        return new JsonEncodingException(outline107.toString());
    }

    @NotNull
    public static final JsonDecodingException JsonDecodingException(int i, @NotNull String message, @NotNull String input) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(input, "input");
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(message, ".\n JSON input: ");
        outline113.append(minify(input, i));
        return new JsonDecodingException(i, outline113.toString());
    }

    @NotNull
    public static final JsonDecodingException UnknownKeyException(@NotNull String key, @NotNull String input) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(input, "input");
        return new JsonDecodingException(-1, "JSON encountered unknown key: '" + key + "'. You can enable 'JsonConfiguration.ignoreUnknownKeys' property to ignore unknown keys.\n JSON input: " + minify$default(input, 0, 1, null));
    }

    private static final String minify(@NotNull String str, int i) {
        int coerceAtLeast;
        int coerceAtMost;
        if (str.length() < 200) {
            return str;
        }
        String str2 = ".....";
        if (i == -1) {
            int length = str.length() - 60;
            if (length <= 0) {
                return str;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str2);
            String substring = str.substring(length);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            outline107.append(substring);
            return outline107.toString();
        }
        int i2 = i - 30;
        int i3 = i + 30;
        String str3 = i2 <= 0 ? "" : str2;
        if (i3 >= str.length()) {
            str2 = "";
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(str3);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(i2, 0);
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(i3, str.length());
        String substring2 = str.substring(coerceAtLeast, coerceAtMost);
        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        outline1072.append(substring2);
        outline1072.append(str2);
        return outline1072.toString();
    }

    static /* synthetic */ String minify$default(String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = -1;
        }
        return minify(str, i);
    }

    @NotNull
    public static final JsonEncodingException InvalidFloatingPoint(@NotNull Number value, @NotNull String key, @NotNull String type, @NotNull String output) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(output, "output");
        StringBuilder sb = new StringBuilder();
        sb.append(Chars.QUOTE);
        sb.append(value);
        sb.append("' with key '");
        sb.append(key);
        sb.append("' is not a valid ");
        GeneratedOutlineSupport1.outline181(sb, type, " as per JSON specification. ", "You can enable 'serializeSpecialFloatingPointValues' property to serialize such values.\n", "Current output: ");
        sb.append(minify$default(output, 0, 1, null));
        return new JsonEncodingException(sb.toString());
    }
}
