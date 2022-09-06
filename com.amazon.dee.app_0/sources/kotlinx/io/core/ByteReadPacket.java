package kotlinx.io.core;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.pool.NoPoolImpl;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteReadPacket.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u00012\u00020\u0002:\u0001\u000fB\u001d\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0002\u0010\u0007B%\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0014J\n\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0004¨\u0006\u0010"}, d2 = {"Lkotlinx/io/core/ByteReadPacket;", "Lkotlinx/io/core/ByteReadPacketPlatformBase;", "Lkotlinx/io/core/Input;", TtmlNode.TAG_HEAD, "Lkotlinx/io/core/IoBuffer;", "pool", "Lkotlinx/io/pool/ObjectPool;", "(Lkotlinx/io/core/IoBuffer;Lkotlinx/io/pool/ObjectPool;)V", "remaining", "", "(Lkotlinx/io/core/IoBuffer;JLkotlinx/io/pool/ObjectPool;)V", "closeSource", "", "fill", "", "Companion", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteReadPacket extends ByteReadPacketPlatformBase implements Input {
    public static final Companion Companion = new Companion(null);

    /* compiled from: ByteReadPacket.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lkotlinx/io/core/ByteReadPacket$Companion;", "", "()V", "Empty", "Lkotlinx/io/core/ByteReadPacket;", "getEmpty", "()Lkotlinx/io/core/ByteReadPacket;", "ReservedSize", "", "getReservedSize", "()I", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final ByteReadPacket getEmpty() {
            return new ByteReadPacket(IoBuffer.Companion.getEmpty(), new NoPoolImpl<IoBuffer>() { // from class: kotlinx.io.core.ByteReadPacket$Companion$Empty$1
                @Override // kotlinx.io.pool.ObjectPool
                @NotNull
                /* renamed from: borrow  reason: collision with other method in class */
                public IoBuffer mo12351borrow() {
                    return IoBuffer.Companion.getEmpty();
                }
            });
        }

        public final int getReservedSize() {
            return IoBuffer.Companion.getReservedSize();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteReadPacket(@NotNull IoBuffer head, long j, @NotNull ObjectPool<IoBuffer> pool) {
        super(head, j, pool);
        Intrinsics.checkParameterIsNotNull(head, "head");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        markNoMoreChunksAvailable$kotlinx_io();
    }

    @Override // kotlinx.io.core.ByteReadPacketBase
    protected void closeSource() {
    }

    @Override // kotlinx.io.core.ByteReadPacketBase
    @Nullable
    /* renamed from: fill */
    protected final Void mo12334fill() {
        return null;
    }

    @Override // kotlinx.io.core.ByteReadPacketBase
    /* renamed from: fill  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ IoBuffer mo12334fill() {
        return (IoBuffer) mo12334fill();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ByteReadPacket(@NotNull IoBuffer head, @NotNull ObjectPool<IoBuffer> pool) {
        this(head, BuffersKt.remainingAll(head), pool);
        Intrinsics.checkParameterIsNotNull(head, "head");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
    }
}
