package kotlinx.io.core;

import com.amazon.alexa.presence.service.PresenceJobService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.lwansbrough.RCTCamera.RCTCameraModule;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.internal.RequireFailureCapture;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Builder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\r\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u0018J\u0010\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0012\u0010\u0019\u001a\u00020\u00002\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\"\u0010\u0019\u001a\u00020\u00002\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0003H\u0016J\u0006\u0010 \u001a\u00020!J\b\u0010\"\u001a\u00020\u0017H\u0016J\b\u0010#\u001a\u00020\u0017H\u0016J\u0015\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u0006H\u0010¢\u0006\u0002\b&J\b\u0010'\u001a\u00020!H\u0001J4\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010(2!\u0010)\u001a\u001d\u0012\u0013\u0012\u00110!¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(-\u0012\u0004\u0012\u0002H(0*¢\u0006\u0002\u0010.J\b\u0010/\u001a\u00020\u0017H\u0016J\u000f\u00100\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0002\b1J\u0010\u00102\u001a\u00020\u00172\u0006\u00103\u001a\u00020!H\u0016J \u00104\u001a\u00020\u00172\u0006\u00105\u001a\u00020\u00062\u0006\u00106\u001a\u00020\u00062\u0006\u00103\u001a\u00020!H\u0002J\u0018\u00107\u001a\u00020\u00172\u0006\u00106\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u0006H\u0002R$\u0010\b\u001a\u00020\u00068\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0013\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u00068"}, d2 = {"Lkotlinx/io/core/BytePacketBuilder;", "Lkotlinx/io/core/BytePacketBuilderPlatformBase;", "headerSizeHint", "", "pool", "Lkotlinx/io/pool/ObjectPool;", "Lkotlinx/io/core/IoBuffer;", "(ILkotlinx/io/pool/ObjectPool;)V", TtmlNode.TAG_HEAD, "head$annotations", "()V", "getHead", "()Lkotlinx/io/core/IoBuffer;", "setHead", "(Lkotlinx/io/core/IoBuffer;)V", "isEmpty", "", "()Z", "isNotEmpty", "size", "getSize", "()I", "afterBytesStolen", "", "afterBytesStolen$kotlinx_io", "append", "c", "", "csq", "", "start", "end", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lkotlinx/io/core/ByteReadPacket;", "close", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "last", "buffer", "last$kotlinx_io", RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_PREVIEW, "R", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "tmp", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "release", "stealAll", "stealAll$kotlinx_io", "writePacket", "p", "writePacketSlow", "tail", "foreignStolen", "writePacketSlowPrepend", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class BytePacketBuilder extends BytePacketBuilderPlatformBase {
    @NotNull
    private IoBuffer head;
    private int headerSizeHint;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BytePacketBuilder(int i, @NotNull ObjectPool<IoBuffer> pool) {
        super(pool);
        Intrinsics.checkParameterIsNotNull(pool, "pool");
        this.headerSizeHint = i;
        if (this.headerSizeHint >= 0) {
            this.head = IoBuffer.Companion.getEmpty();
        } else {
            new RequireFailureCapture() { // from class: kotlinx.io.core.BytePacketBuilder$$special$$inlined$require$1
                @Override // kotlinx.io.core.internal.RequireFailureCapture
                @NotNull
                public Void doFail() {
                    int i2;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("shouldn't be negative: headerSizeHint = ");
                    i2 = BytePacketBuilder.this.headerSizeHint;
                    outline107.append(i2);
                    throw new IllegalArgumentException(outline107.toString());
                }
            }.doFail();
            throw null;
        }
    }

    @PublishedApi
    public static /* synthetic */ void head$annotations() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0018, code lost:
        if (r1 <= (r7.getWriteRemaining() + r7.getEndGap())) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void writePacketSlow(kotlinx.io.core.IoBuffer r7, kotlinx.io.core.IoBuffer r8, kotlinx.io.core.ByteReadPacket r9) {
        /*
            r6 = this;
            int r0 = r7.getReadRemaining()
            int r1 = r8.getReadRemaining()
            int r2 = kotlinx.io.core.PacketJVMKt.getPACKET_MAX_COPY_SIZE()
            r3 = -1
            if (r1 >= r2) goto L1b
            int r4 = r7.getEndGap()
            int r5 = r7.getWriteRemaining()
            int r5 = r5 + r4
            if (r1 > r5) goto L1b
            goto L1c
        L1b:
            r1 = r3
        L1c:
            if (r0 >= r2) goto L2b
            int r2 = r8.getStartGap()
            if (r0 > r2) goto L2b
            boolean r2 = r8.isExclusivelyOwned()
            if (r2 == 0) goto L2b
            goto L2c
        L2b:
            r0 = r3
        L2c:
            if (r1 != r3) goto L45
            if (r0 != r3) goto L45
            r7.setNext(r8)
            kotlinx.io.core.IoBuffer r7 = kotlinx.io.core.BuffersKt.findTail(r8)
            r6.setTail(r7)
            kotlinx.io.core.IoBuffer r7 = r6.head
            long r7 = kotlinx.io.core.BuffersKt.remainingAll(r7)
            int r7 = (int) r7
            r6.set_size(r7)
            goto L98
        L45:
            if (r0 == r3) goto L61
            if (r1 > r0) goto L4a
            goto L61
        L4a:
            if (r1 == r3) goto L5d
            if (r0 >= r1) goto L4f
            goto L5d
        L4f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "prep = "
            java.lang.String r9 = ", app = "
            java.lang.String r8 = com.android.tools.r8.GeneratedOutlineSupport1.outline53(r8, r0, r9, r1)
            r7.<init>(r8)
            throw r7
        L5d:
            r6.writePacketSlowPrepend(r8, r7)
            goto L98
        L61:
            int r0 = r7.getWriteRemaining()
            int r1 = r7.getEndGap()
            int r1 = r1 + r0
            r7.writeBufferAppend$kotlinx_io(r8, r1)
            kotlinx.io.core.IoBuffer r0 = r8.getNext()
            r7.setNext(r0)
            kotlinx.io.core.IoBuffer r0 = kotlinx.io.core.BuffersKt.findTail(r8)
            if (r0 != r8) goto L7c
            r1 = 1
            goto L7d
        L7c:
            r1 = 0
        L7d:
            if (r1 != 0) goto L80
            goto L81
        L80:
            r0 = 0
        L81:
            if (r0 == 0) goto L84
            r7 = r0
        L84:
            r6.setTail(r7)
            kotlinx.io.pool.ObjectPool r7 = r9.getPool()
            r8.release(r7)
            kotlinx.io.core.IoBuffer r7 = r6.head
            long r7 = kotlinx.io.core.BuffersKt.remainingAll(r7)
            int r7 = (int) r7
            r6.set_size(r7)
        L98:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.BytePacketBuilder.writePacketSlow(kotlinx.io.core.IoBuffer, kotlinx.io.core.IoBuffer, kotlinx.io.core.ByteReadPacket):void");
    }

    private final void writePacketSlowPrepend(IoBuffer ioBuffer, IoBuffer ioBuffer2) {
        ioBuffer.writeBufferPrepend$kotlinx_io(ioBuffer2);
        IoBuffer ioBuffer3 = this.head;
        if (ioBuffer3 == ioBuffer2) {
            this.head = ioBuffer;
        } else {
            while (true) {
                IoBuffer next = ioBuffer3.getNext();
                if (next == null) {
                    Intrinsics.throwNpe();
                }
                if (next == ioBuffer2) {
                    break;
                }
                ioBuffer3 = next;
            }
            ioBuffer3.setNext(ioBuffer);
        }
        ioBuffer2.release(getPool());
        setTail(BuffersKt.findTail(ioBuffer));
        set_size((int) BuffersKt.remainingAll(this.head));
    }

    public final void afterBytesStolen$kotlinx_io() {
        IoBuffer ioBuffer = this.head;
        if (ioBuffer.getNext() == null) {
            set_size(0);
            ioBuffer.resetForWrite();
            ioBuffer.reserveStartGap(this.headerSizeHint);
            ioBuffer.reserveEndGap(IoBuffer.Companion.getReservedSize());
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    @NotNull
    public final ByteReadPacket build() {
        int size = getSize();
        IoBuffer stealAll$kotlinx_io = stealAll$kotlinx_io();
        if (stealAll$kotlinx_io == null) {
            return ByteReadPacket.Companion.getEmpty();
        }
        return new ByteReadPacket(stealAll$kotlinx_io, size, getPool());
    }

    @Override // kotlinx.io.core.Output, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        release();
    }

    @Override // kotlinx.io.core.Output
    public void flush() {
    }

    @NotNull
    public final IoBuffer getHead() {
        return this.head;
    }

    public final int getSize() {
        int i = get_size();
        if (i == -1) {
            set_size((int) BuffersKt.remainingAll(this.head));
            return get_size();
        }
        return i;
    }

    public final boolean isEmpty() {
        int i = get_size();
        if (i > 0) {
            return false;
        }
        return i == 0 || (!this.head.canRead() && getSize() == 0);
    }

    public final boolean isNotEmpty() {
        int i = get_size();
        if (i > 0) {
            return true;
        }
        return i != 0 && (this.head.canRead() || getSize() > 0);
    }

    @Override // kotlinx.io.core.BytePacketBuilderBase
    public void last$kotlinx_io(@NotNull IoBuffer buffer) {
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        if (this.head == IoBuffer.Companion.getEmpty()) {
            if (BuffersKt.isEmpty(buffer)) {
                buffer.reserveStartGap(this.headerSizeHint);
            }
            setTail(buffer);
            this.head = buffer;
            set_size((int) BuffersKt.remainingAll(buffer));
            return;
        }
        getTail().setNext(buffer);
        setTail(buffer);
        set_size(-1);
    }

    public final <R> R preview(@NotNull Function1<? super ByteReadPacket, ? extends R> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        ByteReadPacket preview = preview();
        try {
            return block.mo12165invoke(preview);
        } finally {
            preview.release();
        }
    }

    @Override // kotlinx.io.core.BytePacketBuilderBase
    public void release() {
        IoBuffer ioBuffer = this.head;
        IoBuffer empty = IoBuffer.Companion.getEmpty();
        if (ioBuffer != empty) {
            this.head = empty;
            setTail(empty);
            BuffersKt.releaseAll(ioBuffer, getPool());
            set_size(0);
        }
    }

    public final void setHead(@NotNull IoBuffer ioBuffer) {
        Intrinsics.checkParameterIsNotNull(ioBuffer, "<set-?>");
        this.head = ioBuffer;
    }

    @Nullable
    public final IoBuffer stealAll$kotlinx_io() {
        IoBuffer ioBuffer = this.head;
        IoBuffer empty = IoBuffer.Companion.getEmpty();
        this.head = empty;
        setTail(empty);
        set_size(0);
        if (ioBuffer == empty) {
            return null;
        }
        return ioBuffer;
    }

    @Override // kotlinx.io.core.BytePacketBuilderBase
    public void writePacket(@NotNull ByteReadPacket p) {
        Intrinsics.checkParameterIsNotNull(p, "p");
        IoBuffer stealAll$kotlinx_io = p.stealAll$kotlinx_io();
        if (stealAll$kotlinx_io == null) {
            p.release();
            return;
        }
        IoBuffer tail = getTail();
        if (tail == IoBuffer.Companion.getEmpty()) {
            this.head = stealAll$kotlinx_io;
            setTail(BuffersKt.findTail(stealAll$kotlinx_io));
            set_size((int) BuffersKt.remainingAll(stealAll$kotlinx_io));
            return;
        }
        writePacketSlow(tail, stealAll$kotlinx_io, p);
    }

    @PublishedApi
    @NotNull
    public final ByteReadPacket preview() {
        IoBuffer ioBuffer = this.head;
        return ioBuffer == IoBuffer.Companion.getEmpty() ? ByteReadPacket.Companion.getEmpty() : new ByteReadPacket(BuffersKt.copyAll(ioBuffer), getPool());
    }

    @Override // kotlinx.io.core.BytePacketBuilderBase, java.lang.Appendable
    @NotNull
    /* renamed from: append  reason: collision with other method in class */
    public BytePacketBuilder mo12331append(char c) {
        BytePacketBuilderBase mo12331append = super.mo12331append(c);
        if (mo12331append != null) {
            return (BytePacketBuilder) mo12331append;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.io.core.BytePacketBuilder");
    }

    @Override // kotlinx.io.core.BytePacketBuilderBase, java.lang.Appendable
    @NotNull
    /* renamed from: append  reason: collision with other method in class */
    public BytePacketBuilder mo12332append(@Nullable CharSequence charSequence) {
        BytePacketBuilderBase mo12332append = super.mo12332append(charSequence);
        if (mo12332append != null) {
            return (BytePacketBuilder) mo12332append;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.io.core.BytePacketBuilder");
    }

    @Override // kotlinx.io.core.BytePacketBuilderBase, java.lang.Appendable
    @NotNull
    /* renamed from: append  reason: collision with other method in class */
    public BytePacketBuilder mo12333append(@Nullable CharSequence charSequence, int i, int i2) {
        BytePacketBuilderBase mo12333append = super.mo12333append(charSequence, i, i2);
        if (mo12333append != null) {
            return (BytePacketBuilder) mo12333append;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.io.core.BytePacketBuilder");
    }
}
