package kotlin.text;

import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: _StringsJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\f\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0010\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006*\u00020\u0002¨\u0006\u0007"}, d2 = {"elementAt", "", "", "index", "", "toSortedSet", "Ljava/util/SortedSet;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 16}, xi = 1, xs = "kotlin/text/StringsKt")
/* loaded from: classes4.dex */
public class StringsKt___StringsJvmKt extends StringsKt__StringsKt {
    @InlineOnly
    private static final char elementAt(@NotNull CharSequence charSequence, int i) {
        return charSequence.charAt(i);
    }

    @NotNull
    public static final SortedSet<Character> toSortedSet(@NotNull CharSequence toSortedSet) {
        Intrinsics.checkParameterIsNotNull(toSortedSet, "$this$toSortedSet");
        return (SortedSet) StringsKt___StringsKt.toCollection(toSortedSet, new TreeSet());
    }
}
