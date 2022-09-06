package kotlinx.io.core;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteReadPacket.kt */
@ExperimentalIoApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001B)\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH$J\n\u0010\u000b\u001a\u0004\u0018\u00010\u0003H$¨\u0006\f"}, d2 = {"Lkotlinx/io/core/AbstractInput;", "Lkotlinx/io/core/ByteReadPacketPlatformBase;", TtmlNode.TAG_HEAD, "Lkotlinx/io/core/IoBuffer;", "remaining", "", "pool", "Lkotlinx/io/pool/ObjectPool;", "(Lkotlinx/io/core/IoBuffer;JLkotlinx/io/pool/ObjectPool;)V", "closeSource", "", "fill", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public abstract class AbstractInput extends ByteReadPacketPlatformBase {
    public AbstractInput() {
        this(null, 0L, null, 7, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ AbstractInput(kotlinx.io.core.IoBuffer r1, long r2, kotlinx.io.pool.ObjectPool<kotlinx.io.core.IoBuffer> r4, int r5, kotlin.jvm.internal.DefaultConstructorMarker r6) {
        /*
            r0 = this;
            r6 = r5 & 1
            if (r6 == 0) goto La
            kotlinx.io.core.IoBuffer$Companion r1 = kotlinx.io.core.IoBuffer.Companion
            kotlinx.io.core.IoBuffer r1 = r1.getEmpty()
        La:
            r6 = r5 & 2
            if (r6 == 0) goto L12
            long r2 = kotlinx.io.core.BuffersKt.remainingAll(r1)
        L12:
            r5 = r5 & 4
            if (r5 == 0) goto L1c
            kotlinx.io.core.IoBuffer$Companion r4 = kotlinx.io.core.IoBuffer.Companion
            kotlinx.io.pool.ObjectPool r4 = r4.getPool()
        L1c:
            r0.<init>(r1, r2, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.io.core.AbstractInput.<init>(kotlinx.io.core.IoBuffer, long, kotlinx.io.pool.ObjectPool, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    @Override // kotlinx.io.core.ByteReadPacketBase
    protected abstract void closeSource();

    @Override // kotlinx.io.core.ByteReadPacketBase
    @Nullable
    /* renamed from: fill */
    protected abstract IoBuffer mo12334fill();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractInput(@NotNull IoBuffer head, long j, @NotNull ObjectPool<IoBuffer> pool) {
        super(head, j, pool);
        Intrinsics.checkParameterIsNotNull(head, "head");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
    }
}
