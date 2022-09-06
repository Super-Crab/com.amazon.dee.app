package kotlin.text;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.uimanager.ViewProps;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Appendable.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u001a5\u0010\u0000\u001a\u0002H\u0001\"\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u0003*\u0002H\u00012\u0016\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u0005\"\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007\u001a9\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u00032\u0006\u0010\n\u001a\u0002H\u00012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0006\u0018\u00010\fH\u0000¢\u0006\u0002\u0010\r\u001a9\u0010\u000e\u001a\u0002H\u0001\"\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u0003*\u0002H\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"append", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "value", "", "", "(Ljava/lang/Appendable;[Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "appendElement", "", "element", ViewProps.TRANSFORM, "Lkotlin/Function1;", "(Ljava/lang/Appendable;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "appendRange", "startIndex", "", "endIndex", "(Ljava/lang/Appendable;Ljava/lang/CharSequence;II)Ljava/lang/Appendable;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 16}, xi = 1, xs = "kotlin/text/StringsKt")
/* loaded from: classes4.dex */
public class StringsKt__AppendableKt {
    @NotNull
    public static final <T extends Appendable> T append(@NotNull T append, @NotNull CharSequence... value) {
        Intrinsics.checkParameterIsNotNull(append, "$this$append");
        Intrinsics.checkParameterIsNotNull(value, "value");
        for (CharSequence charSequence : value) {
            append.append(charSequence);
        }
        return append;
    }

    public static <T> void appendElement(@NotNull Appendable appendElement, T t, @Nullable Function1<? super T, ? extends CharSequence> function1) {
        Intrinsics.checkParameterIsNotNull(appendElement, "$this$appendElement");
        if (function1 != null) {
            appendElement.append(function1.mo12165invoke(t));
            return;
        }
        if (t != null ? t instanceof CharSequence : true) {
            appendElement.append((CharSequence) t);
        } else if (t instanceof Character) {
            appendElement.append(((Character) t).charValue());
        } else {
            appendElement.append(String.valueOf(t));
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @NotNull
    public static final <T extends Appendable> T appendRange(@NotNull T appendRange, @Nullable CharSequence charSequence, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(appendRange, "$this$appendRange");
        T t = (T) appendRange.append(charSequence, i, i2);
        if (t != null) {
            return t;
        }
        throw new TypeCastException("null cannot be cast to non-null type T");
    }
}
