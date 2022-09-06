package io.ktor.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.IoBuffer;
import kotlinx.io.core.internal.RequireFailureCapture;
import kotlinx.io.internal.jvm.ErrorsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: BufferViewJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0007"}, d2 = {"read", "", "Ljava/nio/channels/ReadableByteChannel;", "buffer", "Lkotlinx/io/core/IoBuffer;", "write", "Ljava/nio/channels/WritableByteChannel;", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class BufferViewJvmKt {
    @InternalAPI
    public static final int read(@NotNull ReadableByteChannel receiver$0, @NotNull IoBuffer buffer) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        boolean z = false;
        if (buffer.getWriteRemaining() == 0) {
            return 0;
        }
        final int writeRemaining = buffer.getWriteRemaining();
        if (1 <= writeRemaining) {
            z = true;
        }
        if (z) {
            ByteBuffer byteBuffer = buffer.writeBuffer;
            int position = byteBuffer.position();
            int read = receiver$0.read(byteBuffer);
            int position2 = byteBuffer.position() - position;
            if (position2 >= 0 && position2 <= writeRemaining) {
                buffer.readBuffer.limit(buffer.writeBuffer.position());
                return read;
            }
            ErrorsKt.wrongBufferPositionChangeError(position2, 1);
            throw null;
        }
        new RequireFailureCapture() { // from class: io.ktor.util.BufferViewJvmKt$read$$inlined$writeDirect$1
            @Override // kotlinx.io.core.internal.RequireFailureCapture
            @NotNull
            public Void doFail() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("size ");
                outline107.append(r1);
                outline107.append(" is greater than buffer's remaining capacity ");
                outline107.append(writeRemaining);
                throw new IllegalArgumentException(outline107.toString());
            }
        }.doFail();
        throw null;
    }

    @InternalAPI
    public static final int write(@NotNull WritableByteChannel receiver$0, @NotNull IoBuffer buffer) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        ByteBuffer byteBuffer = buffer.readBuffer;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int write = receiver$0.write(byteBuffer);
        int position2 = byteBuffer.position() - position;
        if (position2 >= 0) {
            if (byteBuffer.limit() == limit) {
                return write;
            }
            ErrorsKt.limitChangeError();
            throw null;
        }
        ErrorsKt.negativeShiftError(position2);
        throw null;
    }
}
