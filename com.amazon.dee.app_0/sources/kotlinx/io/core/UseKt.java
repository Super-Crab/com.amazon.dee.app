package kotlinx.io.core;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Use.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a6\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u0004\b\u0001\u0010\u0001*\u0002H\u00022\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0005H\u0086\b¢\u0006\u0002\u0010\u0006\u001a6\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0007*\u00020\b\"\u0004\b\u0001\u0010\u0001*\u0002H\u00072\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00010\u0005H\u0086\b¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"use", "R", "I", "Lkotlinx/io/core/Input;", "block", "Lkotlin/Function1;", "(Lkotlinx/io/core/Input;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "O", "Lkotlinx/io/core/Output;", "(Lkotlinx/io/core/Output;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class UseKt {
    public static final <I extends Input, R> R use(@NotNull I receiver$0, @NotNull Function1<? super I, ? extends R> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        try {
            return block.mo12165invoke(receiver$0);
        } finally {
            InlineMarker.finallyStart(1);
            receiver$0.close();
            InlineMarker.finallyEnd(1);
        }
    }

    public static final <O extends Output, R> R use(@NotNull O receiver$0, @NotNull Function1<? super O, ? extends R> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        try {
            return block.mo12165invoke(receiver$0);
        } finally {
            InlineMarker.finallyStart(1);
            receiver$0.close();
            InlineMarker.finallyEnd(1);
        }
    }
}
