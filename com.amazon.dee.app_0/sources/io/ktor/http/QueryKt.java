package io.ktor.http;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.google.android.gms.actions.SearchIntents;
import io.ktor.http.Parameters;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Query.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u001a \u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a \u0010\f\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u000bH\u0002\u001a,\u0010\r\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0002\u001a$\u0010\u0013\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¨\u0006\u0014"}, d2 = {"parseQueryString", "Lio/ktor/http/Parameters;", SearchIntents.EXTRA_QUERY, "", "startIndex", "", MetricsUtil.LegacyMetricTypes.LIMIT, "trimEnd", "start", "end", "text", "", "trimStart", "appendParam", "", "Lio/ktor/http/ParametersBuilder;", "nameIndex", "equalIndex", "endIndex", "parse", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class QueryKt {
    private static final void appendParam(@NotNull ParametersBuilder parametersBuilder, String str, int i, int i2, int i3) {
        List emptyList;
        if (i2 == -1) {
            int trimStart = trimStart(i, i3, str);
            int trimEnd = trimEnd(trimStart, i3, str);
            if (trimEnd <= trimStart) {
                return;
            }
            String decodeURLQueryComponent$default = CodecsKt.decodeURLQueryComponent$default(str, trimStart, trimEnd, false, null, 12, null);
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            parametersBuilder.appendAll(decodeURLQueryComponent$default, emptyList);
            return;
        }
        int trimStart2 = trimStart(i, i2, str);
        int trimEnd2 = trimEnd(trimStart2, i2, str);
        if (trimEnd2 <= trimStart2) {
            return;
        }
        String decodeURLQueryComponent$default2 = CodecsKt.decodeURLQueryComponent$default(str, trimStart2, trimEnd2, false, null, 12, null);
        int trimStart3 = trimStart(i2 + 1, i3, str);
        parametersBuilder.append(decodeURLQueryComponent$default2, CodecsKt.decodeURLQueryComponent$default(str, trimStart3, trimEnd(trimStart3, i3, str), true, null, 8, null));
    }

    private static final void parse(@NotNull ParametersBuilder parametersBuilder, String str, int i, int i2) {
        int lastIndex;
        lastIndex = StringsKt__StringsKt.getLastIndex(str);
        int i3 = -1;
        int i4 = 0;
        if (i <= lastIndex) {
            int i5 = i;
            int i6 = -1;
            while (i4 != i2) {
                char charAt = str.charAt(i);
                if (charAt == '&') {
                    appendParam(parametersBuilder, str, i5, i6, i);
                    i5 = i + 1;
                    i4++;
                    i6 = -1;
                } else if (charAt == '=' && i6 == -1) {
                    i6 = i;
                }
                if (i != lastIndex) {
                    i++;
                } else {
                    i = i5;
                    i3 = i6;
                }
            }
            return;
        }
        if (i4 == i2) {
            return;
        }
        appendParam(parametersBuilder, str, i, i3, str.length());
    }

    @NotNull
    public static final Parameters parseQueryString(@NotNull String query, int i, int i2) {
        int lastIndex;
        Intrinsics.checkParameterIsNotNull(query, "query");
        lastIndex = StringsKt__StringsKt.getLastIndex(query);
        if (i > lastIndex) {
            return Parameters.Companion.getEmpty();
        }
        Parameters.Companion companion = Parameters.Companion;
        ParametersBuilder parametersBuilder = new ParametersBuilder(0, 1, null);
        parse(parametersBuilder, query, i, i2);
        return parametersBuilder.mo10292build();
    }

    @NotNull
    public static /* synthetic */ Parameters parseQueryString$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = 1000;
        }
        return parseQueryString(str, i, i2);
    }

    private static final int trimEnd(int i, int i2, CharSequence charSequence) {
        boolean isWhitespace;
        while (i2 > i) {
            isWhitespace = CharsKt__CharJVMKt.isWhitespace(charSequence.charAt(i2 - 1));
            if (!isWhitespace) {
                break;
            }
            i2--;
        }
        return i2;
    }

    private static final int trimStart(int i, int i2, CharSequence charSequence) {
        boolean isWhitespace;
        while (i < i2) {
            isWhitespace = CharsKt__CharJVMKt.isWhitespace(charSequence.charAt(i));
            if (!isWhitespace) {
                break;
            }
            i++;
        }
        return i;
    }
}
