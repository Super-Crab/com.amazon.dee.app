package kotlinx.io.core;

import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.internal.DangerousInternalIoApi;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Buffers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0007\u001a\u00020\b*\u00020\tH\u0080\b\u001a\u0015\u0010\n\u001a\u00020\b*\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0080\b\u001a\f\u0010\r\u001a\u00020\u0002*\u00020\u0002H\u0000\u001a\u001d\u0010\r\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0002H\u0082\u0010\u001a\r\u0010\u0010\u001a\u00020\u0002*\u00020\u0002H\u0080\u0010\u001a\r\u0010\u0011\u001a\u00020\u0012*\u00020\u0002H\u0080\u0010\u001a\u001d\u0010\u0013\u001a\u00020\u0014*\u0004\u0018\u00010\u00022\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0080\u0010\u001a\f\u0010\u0016\u001a\u00020\t*\u00020\u0002H\u0007\u001a\u0015\u0010\u0016\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\tH\u0082\u0010\" \u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006*J\b\u0007\u0010\u0018\"\u00020\u00022\u00020\u0002B<\b\u0019\u0012\b\b\u000b\u0012\u0004\b\b(\u001a\u0012\"\b\u001b\u0012\u001e\b\u000bB\u001a\b\u001c\u0012\f\b\u001d\u0012\b\b\fJ\u0004\b\b(\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\n\b!\u0012\u0006\b\n0\"8#¨\u0006$"}, d2 = {"EmptyBufferViewPool", "Lkotlinx/io/pool/ObjectPool;", "Lkotlinx/io/core/IoBuffer;", "EmptyBufferViewPool$annotations", "()V", "getEmptyBufferViewPool", "()Lkotlinx/io/pool/ObjectPool;", "coerceAtMostMaxInt", "", "", "coerceAtMostMaxIntOrFail", "message", "", "copyAll", TtmlNode.TAG_HEAD, "prev", "findTail", "isEmpty", "", "releaseAll", "", "pool", "remainingAll", JsonReportFormat.COUNT, "BufferView", "Lkotlin/Deprecated;", "Use IoBuffer instead", "replaceWith", "Lkotlin/ReplaceWith;", "imports", "kotlinx.io.core.IoBuffer", "expression", "IoBuffer", ModelTransformer.KEY_BATTERY_LEVEL, "Lkotlin/DeprecationLevel;", "ERROR", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class BuffersKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use IoBuffer instead", replaceWith = @ReplaceWith(expression = "IoBuffer", imports = {"kotlinx.io.core.IoBuffer"}))
    public static /* synthetic */ void BufferView$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use IoBuffer.EmptyPool instead.", replaceWith = @ReplaceWith(expression = "IoBuffer.EmptyPool", imports = {"kotlinx.io.core.IoBuffer"}))
    public static /* synthetic */ void EmptyBufferViewPool$annotations() {
    }

    public static final int coerceAtMostMaxInt(long j) {
        return (int) Math.min(j, Integer.MAX_VALUE);
    }

    public static final int coerceAtMostMaxIntOrFail(long j, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (j <= Integer.MAX_VALUE) {
            return (int) j;
        }
        throw new IllegalArgumentException(message);
    }

    @NotNull
    public static final IoBuffer copyAll(@NotNull IoBuffer receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        IoBuffer makeView = receiver$0.makeView();
        IoBuffer next = receiver$0.getNext();
        return next != null ? copyAll(next, makeView, makeView) : makeView;
    }

    @NotNull
    public static final IoBuffer findTail(@NotNull IoBuffer receiver$0) {
        while (true) {
            Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
            IoBuffer next = receiver$0.getNext();
            if (next != null) {
                receiver$0 = next;
            } else {
                return receiver$0;
            }
        }
    }

    @NotNull
    public static final ObjectPool<IoBuffer> getEmptyBufferViewPool() {
        return IoBuffer.Companion.getEmptyPool();
    }

    public static final boolean isEmpty(@NotNull IoBuffer receiver$0) {
        do {
            Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
            if (receiver$0.getReadRemaining() > 0) {
                return false;
            }
            receiver$0 = receiver$0.getNext();
        } while (receiver$0 != null);
        return true;
    }

    public static final void releaseAll(@Nullable IoBuffer ioBuffer, @NotNull ObjectPool<IoBuffer> pool) {
        while (true) {
            Intrinsics.checkParameterIsNotNull(pool, "pool");
            if (ioBuffer == null) {
                return;
            }
            ioBuffer.release(pool);
            ioBuffer = ioBuffer.getNext();
        }
    }

    @DangerousInternalIoApi
    public static final long remainingAll(@NotNull IoBuffer receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return remainingAll(receiver$0, 0L);
    }

    private static final long remainingAll(@NotNull IoBuffer ioBuffer, long j) {
        do {
            j += ioBuffer.getReadRemaining();
            ioBuffer = ioBuffer.getNext();
        } while (ioBuffer != null);
        return j;
    }

    private static final IoBuffer copyAll(@NotNull IoBuffer ioBuffer, IoBuffer ioBuffer2, IoBuffer ioBuffer3) {
        while (true) {
            IoBuffer makeView = ioBuffer.makeView();
            ioBuffer3.setNext(makeView);
            ioBuffer = ioBuffer.getNext();
            if (ioBuffer != null) {
                ioBuffer3 = makeView;
            } else {
                return ioBuffer2;
            }
        }
    }
}
