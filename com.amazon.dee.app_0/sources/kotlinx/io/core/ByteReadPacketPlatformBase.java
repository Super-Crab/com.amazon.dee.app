package kotlinx.io.core;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.internal.DangerousInternalIoApi;
import kotlinx.io.core.internal.UnsafeKt;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
/* compiled from: ByteReadPacket.kt */
@DangerousInternalIoApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b'\u0018\u00002\u00020\u00012\u00020\u0002B%\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\u0010\tJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016¨\u0006\u0011"}, d2 = {"Lkotlinx/io/core/ByteReadPacketPlatformBase;", "Lkotlinx/io/core/ByteReadPacketBase;", "Lkotlinx/io/core/Input;", TtmlNode.TAG_HEAD, "Lkotlinx/io/core/IoBuffer;", "remaining", "", "pool", "Lkotlinx/io/pool/ObjectPool;", "(Lkotlinx/io/core/IoBuffer;JLkotlinx/io/pool/ObjectPool;)V", "readAvailable", "", "dst", "Ljava/nio/ByteBuffer;", "length", "readFully", "", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public abstract class ByteReadPacketPlatformBase extends ByteReadPacketBase implements Input {
    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteReadPacketPlatformBase(@NotNull IoBuffer head, long j, @NotNull ObjectPool<IoBuffer> pool) {
        super(head, j, pool);
        Intrinsics.checkParameterIsNotNull(head, "head");
        Intrinsics.checkParameterIsNotNull(pool, "pool");
    }

    @Override // kotlinx.io.core.Input
    public int readAvailable(@NotNull ByteBuffer dst, int i) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        long m12336getRemaining = m12336getRemaining();
        if (m12336getRemaining == 0) {
            return -1;
        }
        int min = (int) Math.min(dst.remaining(), Math.min(i, m12336getRemaining));
        readFully(dst, min);
        return min;
    }

    @Override // kotlinx.io.core.Input
    public void readFully(@NotNull ByteBuffer dst, int i) {
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        boolean z = true;
        if (((long) i) <= m12336getRemaining()) {
            if (i <= dst.remaining()) {
                IoBuffer prepareReadFirstHead = UnsafeKt.prepareReadFirstHead(this, 1);
                if (prepareReadFirstHead == null) {
                    return;
                }
                IoBuffer ioBuffer = prepareReadFirstHead;
                int i2 = 0;
                while (true) {
                    try {
                        int readAvailable = ioBuffer.readAvailable(dst, i - i2);
                        if (readAvailable > 0) {
                            i2 += readAvailable;
                        }
                        if (!(i2 < i)) {
                            break;
                        }
                        try {
                            IoBuffer prepareReadNextHead = UnsafeKt.prepareReadNextHead(this, ioBuffer);
                            if (prepareReadNextHead == null) {
                                z = false;
                                break;
                            }
                            ioBuffer = prepareReadNextHead;
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(this, ioBuffer);
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                if (!z) {
                    return;
                }
                UnsafeKt.completeReadHead(this, ioBuffer);
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Not enough free space in destination buffer to write ", i, " bytes").toString());
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Not enough bytes available (");
        outline107.append(m12336getRemaining());
        outline107.append(") to read ");
        outline107.append(i);
        outline107.append(" bytes");
        throw new IllegalArgumentException(outline107.toString().toString());
    }
}
