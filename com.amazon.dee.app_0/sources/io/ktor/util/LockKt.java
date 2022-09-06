package io.ktor.util;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Lock.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\b¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"use", "R", "Lio/ktor/util/Lock;", "block", "Lkotlin/Function0;", "(Lio/ktor/util/Lock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class LockKt {
    @InternalAPI
    public static final <R> R use(@NotNull Lock receiver$0, @NotNull Function0<? extends R> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        try {
            receiver$0.lock();
            return block.mo12560invoke();
        } finally {
            InlineMarker.finallyStart(1);
            receiver$0.unlock();
            InlineMarker.finallyEnd(1);
        }
    }
}
