package kotlinx.io.core;

import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Copy.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\u0006"}, d2 = {"copyTo", "", "Lkotlinx/io/core/Input;", ContactsModuleConstants.OUTPUT, "Lkotlinx/io/core/Output;", "copyToFallback", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CopyKt {
    public static final long copyTo(@NotNull Input receiver$0, @NotNull Output output) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(output, "output");
        if (!(receiver$0 instanceof ByteReadPacketBase) || !(output instanceof BytePacketBuilderBase)) {
            return copyToFallback(receiver$0, output);
        }
        long j = 0;
        while (true) {
            ByteReadPacketBase byteReadPacketBase = (ByteReadPacketBase) receiver$0;
            IoBuffer stealAll$kotlinx_io = byteReadPacketBase.stealAll$kotlinx_io();
            if (stealAll$kotlinx_io == null) {
                if (byteReadPacketBase.prepareRead(1, byteReadPacketBase.getHead()) == null) {
                    return j;
                }
            } else {
                long remainingAll = BuffersKt.remainingAll(stealAll$kotlinx_io) + j;
                ((BytePacketBuilderBase) output).last$kotlinx_io(stealAll$kotlinx_io);
                j = remainingAll;
            }
        }
    }

    private static final long copyToFallback(@NotNull Input input, Output output) {
        IoBuffer mo12351borrow = IoBuffer.Companion.getPool().mo12351borrow();
        long j = 0;
        while (true) {
            try {
                mo12351borrow.resetForWrite();
                int readAvailable$default = InputKt.readAvailable$default(input, mo12351borrow, 0, 2, null);
                if (readAvailable$default == -1) {
                    return j;
                }
                j += readAvailable$default;
                OutputKt.writeFully$default(output, mo12351borrow, 0, 2, null);
            } finally {
                mo12351borrow.release(IoBuffer.Companion.getPool());
            }
        }
    }
}
