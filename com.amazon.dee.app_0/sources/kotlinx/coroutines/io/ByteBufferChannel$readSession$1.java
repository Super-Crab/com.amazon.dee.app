package kotlinx.coroutines.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.io.internal.RingBufferCapacity;
import kotlinx.io.core.IoBuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteBufferChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/LookAheadSession;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteBufferChannel$readSession$1 extends Lambda implements Function1<LookAheadSession, Unit> {
    final /* synthetic */ Function1 $consumer;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel$readSession$1(ByteBufferChannel byteBufferChannel, Function1 function1) {
        super(1);
        this.this$0 = byteBufferChannel;
        this.$consumer = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(LookAheadSession lookAheadSession) {
        invoke2(lookAheadSession);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull final LookAheadSession receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        this.$consumer.mo12165invoke(new ReadSession() { // from class: kotlinx.coroutines.io.ByteBufferChannel$readSession$1.1
            @Override // kotlinx.coroutines.io.ReadSession
            public int discard(int i) {
                ByteBufferChannel byteBufferChannel = ByteBufferChannel$readSession$1.this.this$0;
                ByteBuffer byteBuffer = byteBufferChannel.setupStateForRead();
                if (byteBuffer != null) {
                    RingBufferCapacity ringBufferCapacity = byteBufferChannel.state.capacity;
                    try {
                        if (ringBufferCapacity.availableForRead != 0) {
                            int availableForRead = getAvailableForRead();
                            ByteBufferChannel$readSession$1.this.this$0.bytesRead(byteBuffer, ringBufferCapacity, availableForRead);
                            return availableForRead;
                        }
                    } finally {
                        byteBufferChannel.restoreStateAfterRead();
                        byteBufferChannel.tryTerminate();
                    }
                }
                return 0;
            }

            @Override // kotlinx.coroutines.io.ReadSession
            public int getAvailableForRead() {
                return ByteBufferChannel$readSession$1.this.this$0.getAvailableForRead();
            }

            @Override // kotlinx.coroutines.io.ReadSession
            @Nullable
            public IoBuffer request(int i) {
                ByteBuffer request = receiver$0.request(0, i);
                if (request != null) {
                    IoBuffer ioBuffer = new IoBuffer(request);
                    ioBuffer.resetForRead();
                    return ioBuffer;
                }
                return null;
            }
        });
    }
}
