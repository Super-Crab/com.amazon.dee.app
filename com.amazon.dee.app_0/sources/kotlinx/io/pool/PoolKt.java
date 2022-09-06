package kotlinx.io.pool;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Pool.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a<\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0006H\u0087\b¢\u0006\u0002\u0010\u0007\u001a<\u0010\b\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0006H\u0086\b¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"useBorrowed", "R", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/io/pool/ObjectPool;", "block", "Lkotlin/Function1;", "(Lkotlinx/io/pool/ObjectPool;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "useInstance", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class PoolKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use useInstance instead", replaceWith = @ReplaceWith(expression = "useInstance(block)", imports = {}))
    public static final <T, R> R useBorrowed(@NotNull ObjectPool<T> receiver$0, @NotNull Function1<? super T, ? extends R> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        T mo12351borrow = receiver$0.mo12351borrow();
        try {
            return block.mo12165invoke(mo12351borrow);
        } finally {
            InlineMarker.finallyStart(1);
            receiver$0.recycle(mo12351borrow);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final <T, R> R useInstance(@NotNull ObjectPool<T> receiver$0, @NotNull Function1<? super T, ? extends R> block) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(block, "block");
        T mo12351borrow = receiver$0.mo12351borrow();
        try {
            return block.mo12165invoke(mo12351borrow);
        } finally {
            InlineMarker.finallyStart(1);
            receiver$0.recycle(mo12351borrow);
            InlineMarker.finallyEnd(1);
        }
    }
}
