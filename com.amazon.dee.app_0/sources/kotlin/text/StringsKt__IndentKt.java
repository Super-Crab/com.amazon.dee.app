package kotlin.text;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Indent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\u001a!\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0002\b\u0004\u001a\u0011\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0002¢\u0006\u0002\b\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u001aJ\u0010\t\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\u0082\b¢\u0006\u0002\b\u000e\u001a\u0014\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u001a\u001e\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u001a\n\u0010\u0013\u001a\u00020\u0002*\u00020\u0002\u001a\u0014\u0010\u0014\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002¨\u0006\u0015"}, d2 = {"getIndentFunction", "Lkotlin/Function1;", "", "indent", "getIndentFunction$StringsKt__IndentKt", "indentWidth", "", "indentWidth$StringsKt__IndentKt", "prependIndent", "reindent", "", "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "replaceIndent", "newIndent", "replaceIndentByMargin", "marginPrefix", "trimIndent", "trimMargin", "kotlin-stdlib"}, k = 5, mv = {1, 1, 16}, xi = 1, xs = "kotlin/text/StringsKt")
/* loaded from: classes4.dex */
public class StringsKt__IndentKt extends StringsKt__AppendableKt {
    private static final Function1<String, String> getIndentFunction$StringsKt__IndentKt(String str) {
        return str.length() == 0 ? StringsKt__IndentKt$getIndentFunction$1.INSTANCE : new StringsKt__IndentKt$getIndentFunction$2(str);
    }

    private static final int indentWidth$StringsKt__IndentKt(@NotNull String str) {
        int length = str.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            } else if (!CharsKt__CharJVMKt.isWhitespace(str.charAt(i))) {
                break;
            } else {
                i++;
            }
        }
        return i == -1 ? str.length() : i;
    }

    @NotNull
    public static final String prependIndent(@NotNull String prependIndent, @NotNull String indent) {
        Sequence map;
        String joinToString$default;
        Intrinsics.checkParameterIsNotNull(prependIndent, "$this$prependIndent");
        Intrinsics.checkParameterIsNotNull(indent, "indent");
        map = SequencesKt___SequencesKt.map(StringsKt__StringsKt.lineSequence(prependIndent), new StringsKt__IndentKt$prependIndent$1(indent));
        joinToString$default = SequencesKt___SequencesKt.joinToString$default(map, "\n", null, null, 0, null, null, 62, null);
        return joinToString$default;
    }

    public static /* synthetic */ String prependIndent$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "    ";
        }
        return prependIndent(str, str2);
    }

    private static final String reindent$StringsKt__IndentKt(@NotNull List<String> list, int i, Function1<? super String, String> function1, Function1<? super String, String> function12) {
        int lastIndex;
        Appendable joinTo$default;
        String mo12165invoke;
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (Object obj : list) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                if (!PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
                    throw new ArithmeticException("Index overflow has happened.");
                }
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            if ((i2 == 0 || i2 == lastIndex) && StringsKt__StringsJVMKt.isBlank(str)) {
                str = null;
            } else {
                String mo12165invoke2 = function12.mo12165invoke(str);
                if (mo12165invoke2 != null && (mo12165invoke = function1.mo12165invoke(mo12165invoke2)) != null) {
                    str = mo12165invoke;
                }
            }
            if (str != null) {
                arrayList.add(str);
            }
            i2 = i3;
        }
        joinTo$default = CollectionsKt___CollectionsKt.joinTo$default(arrayList, new StringBuilder(i), "\n", null, null, 0, null, null, 124, null);
        String sb = ((StringBuilder) joinTo$default).toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return sb;
    }

    @NotNull
    public static final String replaceIndent(@NotNull String replaceIndent, @NotNull String newIndent) {
        int collectionSizeOrDefault;
        int lastIndex;
        Appendable joinTo$default;
        String mo12165invoke;
        Intrinsics.checkParameterIsNotNull(replaceIndent, "$this$replaceIndent");
        Intrinsics.checkParameterIsNotNull(newIndent, "newIndent");
        List<String> lines = StringsKt__StringsKt.lines(replaceIndent);
        ArrayList<String> arrayList = new ArrayList();
        for (Object obj : lines) {
            if (!StringsKt__StringsJVMKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        for (String str : arrayList) {
            arrayList2.add(Integer.valueOf(indentWidth$StringsKt__IndentKt(str)));
        }
        Integer num = (Integer) CollectionsKt.min((Iterable<? extends Comparable>) arrayList2);
        int i = 0;
        int intValue = num != null ? num.intValue() : 0;
        int size = (lines.size() * newIndent.length()) + replaceIndent.length();
        Function1<String, String> indentFunction$StringsKt__IndentKt = getIndentFunction$StringsKt__IndentKt(newIndent);
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(lines);
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : lines) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            String str2 = (String) obj2;
            if ((i == 0 || i == lastIndex) && StringsKt__StringsJVMKt.isBlank(str2)) {
                str2 = null;
            } else {
                String drop = StringsKt___StringsKt.drop(str2, intValue);
                if (drop != null && (mo12165invoke = indentFunction$StringsKt__IndentKt.mo12165invoke(drop)) != null) {
                    str2 = mo12165invoke;
                }
            }
            if (str2 != null) {
                arrayList3.add(str2);
            }
            i = i2;
        }
        joinTo$default = CollectionsKt___CollectionsKt.joinTo$default(arrayList3, new StringBuilder(size), "\n", null, null, 0, null, null, 124, null);
        String sb = ((StringBuilder) joinTo$default).toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return sb;
    }

    public static /* synthetic */ String replaceIndent$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        return replaceIndent(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:88:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x00bd A[SYNTHETIC] */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String replaceIndentByMargin(@org.jetbrains.annotations.NotNull java.lang.String r21, @org.jetbrains.annotations.NotNull java.lang.String r22, @org.jetbrains.annotations.NotNull java.lang.String r23) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__IndentKt.replaceIndentByMargin(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public static /* synthetic */ String replaceIndentByMargin$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        if ((i & 2) != 0) {
            str3 = AccessoryMetricsConstants.DELIMITER;
        }
        return replaceIndentByMargin(str, str2, str3);
    }

    @NotNull
    public static String trimIndent(@NotNull String trimIndent) {
        Intrinsics.checkParameterIsNotNull(trimIndent, "$this$trimIndent");
        return replaceIndent(trimIndent, "");
    }

    @NotNull
    public static final String trimMargin(@NotNull String trimMargin, @NotNull String marginPrefix) {
        Intrinsics.checkParameterIsNotNull(trimMargin, "$this$trimMargin");
        Intrinsics.checkParameterIsNotNull(marginPrefix, "marginPrefix");
        return replaceIndentByMargin(trimMargin, "", marginPrefix);
    }

    public static /* synthetic */ String trimMargin$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = AccessoryMetricsConstants.DELIMITER;
        }
        return trimMargin(str, str2);
    }
}
