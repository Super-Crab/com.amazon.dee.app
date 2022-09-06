package kotlinx.io.core;

import java.io.Closeable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Closeable.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a:\u0010\u0000\u001a\u0002H\u0001\"\f\b\u0000\u0010\u0002*\u00060\u0003j\u0002`\u0004\"\u0004\b\u0001\u0010\u0001*\u0002H\u00022\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0006H\u0086\b¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"use", "R", "C", "Ljava/io/Closeable;", "Lkotlinx/io/core/Closeable;", "block", "Lkotlin/Function1;", "(Ljava/io/Closeable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CloseableKt {
    public static final <C extends Closeable, R> R use(@NotNull C receiver$0, @NotNull Function1<? super C, ? extends R> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        try {
            R mo12165invoke = block.mo12165invoke(receiver$0);
            InlineMarker.finallyStart(1);
            receiver$0.close();
            InlineMarker.finallyEnd(1);
            return mo12165invoke;
        } catch (Throwable th) {
            try {
                receiver$0.close();
                throw th;
            }
        }
    }
}
