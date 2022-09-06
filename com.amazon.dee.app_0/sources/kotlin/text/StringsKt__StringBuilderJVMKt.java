package kotlin.text;

import com.dee.app.metrics.MetricsConstants;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StringBuilderJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\u001a-\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u001a/\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u001a\u0012\u0010\t\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b\u001a\u001d\u0010\t\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\u0006\u0010\u0003\u001a\u00020\fH\u0087\b\u001a\u001f\u0010\t\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\bH\u0087\b\u001a\u0012\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002\u001a\u001f\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\rH\u0087\b\u001a\u001f\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u000eH\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u000fH\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0010H\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\fH\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u001f\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\bH\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0011H\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0012H\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0013H\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0014H\u0087\b\u001a\u001f\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0015H\u0087\b\u001a%\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u000e\u0010\u0003\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002H\u0087\b\u001a\u0014\u0010\u0016\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002H\u0007\u001a\u001d\u0010\u0017\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0018\u001a\u00020\u0006H\u0087\b\u001a%\u0010\u0019\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u001a5\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u001a7\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0018\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u001a!\u0010\u001b\u001a\u00020\u001c*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\fH\u0087\n\u001a-\u0010\u001d\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0015H\u0087\b\u001a7\u0010\u001e\u001a\u00020\u001c*\u00060\u0001j\u0002`\u00022\u0006\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u00062\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\b¨\u0006!"}, d2 = {"appendRange", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "value", "", "startIndex", "", "endIndex", "", "appendln", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "", "Ljava/lang/StringBuffer;", "", "", "", "", "", "", "", "", MetricsConstants.Method.CACHE_CLEAR, "deleteAt", "index", "deleteRange", "insertRange", "set", "", "setRange", "toCharArray", "destination", "destinationOffset", "kotlin-stdlib"}, k = 5, mv = {1, 1, 16}, xi = 1, xs = "kotlin/text/StringsKt")
/* loaded from: classes4.dex */
public class StringsKt__StringBuilderJVMKt extends StringsKt__RegexExtensionsKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final StringBuilder appendRange(@NotNull StringBuilder sb, char[] cArr, int i, int i2) {
        sb.append(cArr, i, i2 - i);
        Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, start…x, endIndex - startIndex)");
        return sb;
    }

    @NotNull
    public static final Appendable appendln(@NotNull Appendable appendln) {
        Intrinsics.checkParameterIsNotNull(appendln, "$this$appendln");
        Appendable append = appendln.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkExpressionValueIsNotNull(append, "append(SystemProperties.LINE_SEPARATOR)");
        return append;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final StringBuilder clear(@NotNull StringBuilder clear) {
        Intrinsics.checkParameterIsNotNull(clear, "$this$clear");
        clear.setLength(0);
        return clear;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final StringBuilder deleteAt(@NotNull StringBuilder sb, int i) {
        StringBuilder deleteCharAt = sb.deleteCharAt(i);
        Intrinsics.checkExpressionValueIsNotNull(deleteCharAt, "this.deleteCharAt(index)");
        return deleteCharAt;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final StringBuilder deleteRange(@NotNull StringBuilder sb, int i, int i2) {
        StringBuilder delete = sb.delete(i, i2);
        Intrinsics.checkExpressionValueIsNotNull(delete, "this.delete(startIndex, endIndex)");
        return delete;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final StringBuilder insertRange(@NotNull StringBuilder sb, int i, char[] cArr, int i2, int i3) {
        StringBuilder insert = sb.insert(i, cArr, i2, i3 - i2);
        Intrinsics.checkExpressionValueIsNotNull(insert, "this.insert(index, value…x, endIndex - startIndex)");
        return insert;
    }

    @InlineOnly
    private static final void set(@NotNull StringBuilder set, int i, char c) {
        Intrinsics.checkParameterIsNotNull(set, "$this$set");
        set.setCharAt(i, c);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final StringBuilder setRange(@NotNull StringBuilder sb, int i, int i2, String str) {
        StringBuilder replace = sb.replace(i, i2, str);
        Intrinsics.checkExpressionValueIsNotNull(replace, "this.replace(startIndex, endIndex, value)");
        return replace;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final void toCharArray(@NotNull StringBuilder sb, char[] cArr, int i, int i2, int i3) {
        sb.getChars(i2, i3, cArr, i);
    }

    static /* synthetic */ void toCharArray$default(StringBuilder sb, char[] cArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = sb.length();
        }
        sb.getChars(i2, i3, cArr, i);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final StringBuilder appendRange(@NotNull StringBuilder sb, CharSequence charSequence, int i, int i2) {
        sb.append(charSequence, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, startIndex, endIndex)");
        return sb;
    }

    @InlineOnly
    private static final Appendable appendln(@NotNull Appendable appendable, CharSequence charSequence) {
        Appendable append = appendable.append(charSequence);
        Intrinsics.checkExpressionValueIsNotNull(append, "append(value)");
        return appendln(append);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final StringBuilder insertRange(@NotNull StringBuilder sb, int i, CharSequence charSequence, int i2, int i3) {
        StringBuilder insert = sb.insert(i, charSequence, i2, i3);
        Intrinsics.checkExpressionValueIsNotNull(insert, "this.insert(index, value, startIndex, endIndex)");
        return insert;
    }

    @InlineOnly
    private static final Appendable appendln(@NotNull Appendable appendable, char c) {
        Appendable append = appendable.append(c);
        Intrinsics.checkExpressionValueIsNotNull(append, "append(value)");
        return appendln(append);
    }

    @NotNull
    public static StringBuilder appendln(@NotNull StringBuilder appendln) {
        Intrinsics.checkParameterIsNotNull(appendln, "$this$appendln");
        appendln.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkExpressionValueIsNotNull(appendln, "append(SystemProperties.LINE_SEPARATOR)");
        return appendln;
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, StringBuffer stringBuffer) {
        sb.append(stringBuffer);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, CharSequence charSequence) {
        sb.append(charSequence);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, String str) {
        sb.append(str);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, Object obj) {
        sb.append(obj);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, StringBuilder sb2) {
        sb.append((CharSequence) sb2);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, char[] cArr) {
        sb.append(cArr);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, char c) {
        sb.append(c);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, boolean z) {
        sb.append(z);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, int i) {
        sb.append(i);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, short s) {
        sb.append((int) s);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value.toInt())");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, byte b) {
        sb.append((int) b);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value.toInt())");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, long j) {
        sb.append(j);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, float f) {
        sb.append(f);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, double d) {
        sb.append(d);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }
}
