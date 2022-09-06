package kotlinx.coroutines.io;

import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.facebook.common.callercontext.ContextChain;
import java.nio.ByteBuffer;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.io.core.ByteOrder;
import kotlinx.io.core.ByteReadPacket;
import kotlinx.io.core.ExperimentalIoApi;
import kotlinx.io.core.IoBuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteWriteChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u001b\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u000bH&J\b\u0010\u001d\u001a\u00020\u001eH&J/\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010 \u001a\u00020\u00072\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u001e0\"H¦@ø\u0001\u0000¢\u0006\u0002\u0010$J\u0019\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020#H¦@ø\u0001\u0000¢\u0006\u0002\u0010'J)\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007H¦@ø\u0001\u0000¢\u0006\u0002\u0010+J\u0019\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020,H¦@ø\u0001\u0000¢\u0006\u0002\u0010-J\u0019\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u000200H¦@ø\u0001\u0000¢\u0006\u0002\u00101J\u0019\u00102\u001a\u00020\u001e2\u0006\u00103\u001a\u000204H¦@ø\u0001\u0000¢\u0006\u0002\u00105J\u0019\u00106\u001a\u00020\u001e2\u0006\u00107\u001a\u000208H¦@ø\u0001\u0000¢\u0006\u0002\u00109J\u0019\u0010:\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020#H¦@ø\u0001\u0000¢\u0006\u0002\u0010'J)\u0010:\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007H¦@ø\u0001\u0000¢\u0006\u0002\u0010+J\u0019\u0010:\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020,H¦@ø\u0001\u0000¢\u0006\u0002\u0010-J\u0019\u0010;\u001a\u00020\u001e2\u0006\u0010<\u001a\u00020\u0007H¦@ø\u0001\u0000¢\u0006\u0002\u0010=J\u0019\u0010>\u001a\u00020\u001e2\u0006\u0010?\u001a\u00020\u0010H¦@ø\u0001\u0000¢\u0006\u0002\u0010@J\u0019\u0010A\u001a\u00020\u001e2\u0006\u0010B\u001a\u00020CH¦@ø\u0001\u0000¢\u0006\u0002\u0010DJ\u0019\u0010E\u001a\u00020\u001e2\u0006\u0010F\u001a\u00020GH¦@ø\u0001\u0000¢\u0006\u0002\u0010HJ:\u0010I\u001a\u00020\u001e2'\u0010J\u001a#\b\u0001\u0012\u0004\u0012\u00020L\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0M\u0012\u0006\u0012\u0004\u0018\u00010\u00010K¢\u0006\u0002\bNH§@ø\u0001\u0000¢\u0006\u0002\u0010OJ%\u0010P\u001a\u00020\u001e2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00030\"H¦@ø\u0001\u0000¢\u0006\u0002\u0010QR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0005R\u001a\u0010\u000f\u001a\u00020\u00108&X§\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\u00020\u0016X¦\u000e¢\u0006\f\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006R"}, d2 = {"Lkotlinx/coroutines/io/ByteWriteChannel;", "", "autoFlush", "", "getAutoFlush", "()Z", "availableForWrite", "", "getAvailableForWrite", "()I", "closedCause", "", "getClosedCause", "()Ljava/lang/Throwable;", "isClosedForWrite", "totalBytesWritten", "", "totalBytesWritten$annotations", "()V", "getTotalBytesWritten", "()J", "writeByteOrder", "Lkotlinx/io/core/ByteOrder;", "getWriteByteOrder", "()Lkotlinx/io/core/ByteOrder;", "setWriteByteOrder", "(Lkotlinx/io/core/ByteOrder;)V", "close", "cause", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "", "write", ReactProperties.GEOFENCE_MINIMUM_RANGE, "block", "Lkotlin/Function1;", "Ljava/nio/ByteBuffer;", "(ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeAvailable", "src", "(Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "offset", "length", "([BIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/core/IoBuffer;", "(Lkotlinx/io/core/IoBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeByte", "b", "", "(BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeDouble", "d", "", "(DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFloat", "f", "", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFully", "writeInt", ContextChain.TAG_INFRA, "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeLong", "l", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writePacket", "packet", "Lkotlinx/io/core/ByteReadPacket;", "(Lkotlinx/io/core/ByteReadPacket;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeShort", "s", "", "(SLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeSuspendSession", "visitor", "Lkotlin/Function2;", "Lkotlinx/coroutines/io/WriterSuspendSession;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeWhile", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface ByteWriteChannel {

    /* compiled from: ByteWriteChannel.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        @Deprecated(message = "Counter is no longer supported")
        public static /* synthetic */ void totalBytesWritten$annotations() {
        }

        @Nullable
        public static /* synthetic */ Object write$default(ByteWriteChannel byteWriteChannel, int i, Function1 function1, Continuation continuation, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 1) != 0) {
                    i = 1;
                }
                return byteWriteChannel.write(i, function1, continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: write");
        }
    }

    boolean close(@Nullable Throwable th);

    void flush();

    boolean getAutoFlush();

    int getAvailableForWrite();

    @Nullable
    Throwable getClosedCause();

    long getTotalBytesWritten();

    @NotNull
    ByteOrder getWriteByteOrder();

    boolean isClosedForWrite();

    void setWriteByteOrder(@NotNull ByteOrder byteOrder);

    @Nullable
    Object write(int i, @NotNull Function1<? super ByteBuffer, Unit> function1, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object writeAvailable(@NotNull ByteBuffer byteBuffer, @NotNull Continuation<? super Integer> continuation);

    @Nullable
    Object writeAvailable(@NotNull IoBuffer ioBuffer, @NotNull Continuation<? super Integer> continuation);

    @Nullable
    Object writeAvailable(@NotNull byte[] bArr, int i, int i2, @NotNull Continuation<? super Integer> continuation);

    @Nullable
    Object writeByte(byte b, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object writeDouble(double d, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object writeFloat(float f, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object writeFully(@NotNull ByteBuffer byteBuffer, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object writeFully(@NotNull IoBuffer ioBuffer, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object writeFully(@NotNull byte[] bArr, int i, int i2, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object writeInt(int i, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object writeLong(long j, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object writePacket(@NotNull ByteReadPacket byteReadPacket, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object writeShort(short s, @NotNull Continuation<? super Unit> continuation);

    @ExperimentalIoApi
    @Nullable
    Object writeSuspendSession(@NotNull Function2<? super WriterSuspendSession, ? super Continuation<? super Unit>, ? extends Object> function2, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object writeWhile(@NotNull Function1<? super ByteBuffer, Boolean> function1, @NotNull Continuation<? super Unit> continuation);
}
