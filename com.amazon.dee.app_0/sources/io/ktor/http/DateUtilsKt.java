package io.ktor.http;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.util.date.DateJvmKt;
import io.ktor.util.date.GMTDate;
import io.ktor.util.date.Month;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: DateUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\u001a\u0014\u0010\u0005\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a\n\u0010\u0007\u001a\u00020\u0004*\u00020\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"HTTP_DATE_LENGTH", "", "fromHttpToGmtDate", "Lio/ktor/util/date/GMTDate;", "", "padZero", "length", "toHttpDate", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class DateUtilsKt {
    private static final int HTTP_DATE_LENGTH = 29;

    @NotNull
    public static final GMTDate fromHttpToGmtDate(@NotNull String receiver$0) {
        CharSequence trim;
        boolean endsWith$default;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        trim = StringsKt__StringsKt.trim((CharSequence) receiver$0);
        String obj = trim.toString();
        if (obj.length() == 29) {
            endsWith$default = StringsKt__StringsJVMKt.endsWith$default(obj, "GMT", false, 2, null);
            if (endsWith$default) {
                String substring = obj.substring(5, 7);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                int parseInt = Integer.parseInt(substring);
                Month.Companion companion = Month.Companion;
                String substring2 = obj.substring(8, 11);
                Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                Month from = companion.from(substring2);
                String substring3 = obj.substring(12, 16);
                Intrinsics.checkExpressionValueIsNotNull(substring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                int parseInt2 = Integer.parseInt(substring3);
                String substring4 = obj.substring(17, 19);
                Intrinsics.checkExpressionValueIsNotNull(substring4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                int parseInt3 = Integer.parseInt(substring4);
                String substring5 = obj.substring(20, 22);
                Intrinsics.checkExpressionValueIsNotNull(substring5, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                int parseInt4 = Integer.parseInt(substring5);
                String substring6 = obj.substring(23, 25);
                Intrinsics.checkExpressionValueIsNotNull(substring6, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return DateJvmKt.GMTDate(Integer.parseInt(substring6), parseInt4, parseInt3, parseInt, from, parseInt2);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Invalid timezone. Expected GMT. On string: ", obj).toString());
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid date length. Expected 29, actual ");
        outline107.append(obj.length());
        outline107.append(". On string: ");
        outline107.append(obj);
        throw new IllegalStateException(outline107.toString().toString());
    }

    private static final String padZero(int i, int i2) {
        String padStart;
        padStart = StringsKt__StringsKt.padStart(String.valueOf(i), i2, '0');
        return padStart;
    }

    @NotNull
    public static final String toHttpDate(@NotNull GMTDate receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        StringBuilder sb = new StringBuilder();
        sb.append(receiver$0.getDayOfWeek().getValue() + ", ");
        sb.append(padZero(receiver$0.getDayOfMonth(), 2) + Chars.SPACE);
        sb.append(receiver$0.getMonth().getValue() + Chars.SPACE);
        sb.append(padZero(receiver$0.getYear(), 4));
        sb.append(Chars.SPACE + padZero(receiver$0.getHours(), 2) + JsonReaderKt.COLON + padZero(receiver$0.getMinutes(), 2) + JsonReaderKt.COLON + padZero(receiver$0.getSeconds(), 2) + Chars.SPACE);
        sb.append("GMT");
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
