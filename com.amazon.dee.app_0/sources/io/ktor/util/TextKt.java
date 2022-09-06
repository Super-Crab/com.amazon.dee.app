package io.ktor.util;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Text.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a;\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00022\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00040\u0007H\u0086\b\u001a\n\u0010\b\u001a\u00020\u0002*\u00020\u0002¨\u0006\t"}, d2 = {"caseInsensitive", "Lio/ktor/util/CaseInsensitiveString;", "", "chomp", "Lkotlin/Pair;", "separator", "onMissingDelimiter", "Lkotlin/Function0;", "escapeHTML", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class TextKt {
    @NotNull
    public static final CaseInsensitiveString caseInsensitive(@NotNull String receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new CaseInsensitiveString(receiver$0);
    }

    @NotNull
    public static final Pair<String, String> chomp(@NotNull String receiver$0, @NotNull String separator, @NotNull Function0<Pair<String, String>> onMissingDelimiter) {
        int indexOf$default;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(separator, "separator");
        Intrinsics.checkParameterIsNotNull(onMissingDelimiter, "onMissingDelimiter");
        indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) receiver$0, separator, 0, false, 6, (Object) null);
        if (indexOf$default != -1) {
            String substring = receiver$0.substring(0, indexOf$default);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            String substring2 = receiver$0.substring(indexOf$default + 1);
            Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
            return TuplesKt.to(substring, substring2);
        }
        return onMissingDelimiter.mo12560invoke();
    }

    @NotNull
    public static final String escapeHTML(@NotNull String receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (receiver$0.length() == 0) {
            return receiver$0;
        }
        StringBuilder sb = new StringBuilder(receiver$0.length());
        int length = receiver$0.length();
        for (int i = 0; i < length; i++) {
            char charAt = receiver$0.charAt(i);
            if (charAt == '\"') {
                sb.append("&quot;");
            } else if (charAt == '<') {
                sb.append("&lt;");
            } else if (charAt == '>') {
                sb.append("&gt;");
            } else if (charAt == '&') {
                sb.append("&amp;");
            } else if (charAt != '\'') {
                sb.append(charAt);
            } else {
                sb.append("&#x27;");
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }
}
