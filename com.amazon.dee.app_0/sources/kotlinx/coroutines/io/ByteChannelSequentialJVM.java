package kotlinx.coroutines.io;

import com.amazon.deecomms.common.Constants;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ClosedSendChannelException;
import kotlinx.io.core.ByteBuffersKt;
import kotlinx.io.core.BytePacketBuilder;
import kotlinx.io.core.ExperimentalIoApi;
import kotlinx.io.core.Input;
import kotlinx.io.core.IoBuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteChannelSequentialJVM.kt */
@ExperimentalIoApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0016JM\u0010\f\u001a\u00020\n2:\u0010\r\u001a6\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00050\u000ej\u0002`\u0014H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J,\u0010\u0016\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u0002H\u00170\u0018¢\u0006\u0002\b\u001aH\u0016¢\u0006\u0002\u0010\u001bJ@\u0010\u001c\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172'\u0010\r\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u000e¢\u0006\u0002\b\u001aH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J-\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\"2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\n0\u0018H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010$J\u0019\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010'J\u0019\u0010(\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u000fH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010'J\u0019\u0010)\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010'J!\u0010*\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\"H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010,J\u0010\u0010-\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u000fH\u0002J\u0010\u0010.\u001a\u00020\"2\u0006\u0010/\u001a\u00020\u000fH\u0002J-\u00100\u001a\u00020\n2\u0006\u0010!\u001a\u00020\"2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\n0\u0018H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010$J\u0019\u00102\u001a\u00020\"2\u0006\u0010/\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010'J\u0019\u00103\u001a\u00020\"2\u0006\u0010/\u001a\u00020\u000fH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010'J\u0019\u00104\u001a\u00020\n2\u0006\u0010/\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010'J\u0019\u00105\u001a\u00020\n2\u0006\u0010/\u001a\u00020\u000fH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010'J%\u00106\u001a\u00020\n2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00050\u0018H\u0096@ø\u0001\u0000¢\u0006\u0002\u00107R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00068"}, d2 = {"Lkotlinx/coroutines/io/ByteChannelSequentialJVM;", "Lkotlinx/coroutines/io/ByteChannelSequentialBase;", Constants.ACCESSORY_PRIVACY_INITIAL_STATUS, "Lkotlinx/io/core/IoBuffer;", "autoFlush", "", "(Lkotlinx/io/core/IoBuffer;Z)V", "attachedJob", "Lkotlinx/coroutines/Job;", "attachJob", "", "job", "consumeEachBufferRange", "visitor", "Lkotlin/Function2;", "Ljava/nio/ByteBuffer;", "Lkotlin/ParameterName;", "name", "buffer", "last", "Lkotlinx/coroutines/io/ConsumeEachBufferVisitor;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lookAhead", "R", "Lkotlin/Function1;", "Lkotlinx/coroutines/io/LookAheadSession;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "lookAheadSuspend", "Lkotlinx/coroutines/io/LookAheadSuspendSession;", "Lkotlin/coroutines/Continuation;", "", "read", ReactProperties.GEOFENCE_MINIMUM_RANGE, "", "consumer", "(ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAvailable", "dst", "(Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAvailableSuspend", "readFully", "readFullySuspend", "rc0", "(Ljava/nio/ByteBuffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryReadAvailable", "tryWriteAvailable", "src", "write", "block", "writeAvailable", "writeAvailableSuspend", "writeFully", "writeFullySuspend", "writeWhile", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ByteChannelSequentialJVM extends ByteChannelSequentialBase {
    private volatile Job attachedJob;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteChannelSequentialJVM(@NotNull IoBuffer initial, boolean z) {
        super(initial, z);
        Intrinsics.checkParameterIsNotNull(initial, "initial");
    }

    private final int tryReadAvailable(ByteBuffer byteBuffer) {
        boolean closed = getClosed();
        Throwable closedCause = getClosedCause();
        if (closedCause == null) {
            boolean z = false;
            if (closed) {
                Integer valueOf = Integer.valueOf(Input.DefaultImpls.readAvailable$default(getReadable(), byteBuffer, 0, 2, null));
                if (valueOf.intValue() != 0) {
                    z = true;
                }
                if (!z) {
                    valueOf = null;
                }
                afterRead();
                if (valueOf == null) {
                    return -1;
                }
                return valueOf.intValue();
            }
            Integer valueOf2 = Integer.valueOf(Input.DefaultImpls.readAvailable$default(getReadable(), byteBuffer, 0, 2, null));
            valueOf2.intValue();
            afterRead();
            return valueOf2.intValue();
        }
        throw closedCause;
    }

    private final int tryWriteAvailable(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        int availableForWrite = getAvailableForWrite();
        if (getClosed()) {
            Throwable closedCause = getClosedCause();
            if (closedCause == null) {
                throw new ClosedSendChannelException("Channel closed for write");
            }
            throw closedCause;
        } else if (remaining == 0) {
            return 0;
        } else {
            if (remaining <= availableForWrite) {
                getWritable().writeFully(byteBuffer);
                return remaining;
            } else if (availableForWrite == 0) {
                return 0;
            } else {
                int limit = byteBuffer.limit();
                byteBuffer.limit(byteBuffer.position() + availableForWrite);
                getWritable().writeFully(byteBuffer);
                byteBuffer.limit(limit);
                return availableForWrite;
            }
        }
    }

    @Override // kotlinx.coroutines.io.ByteChannel
    public void attachJob(@NotNull Job job) {
        Intrinsics.checkParameterIsNotNull(job, "job");
        Job job2 = this.attachedJob;
        if (job2 != null) {
            job2.mo12309cancel();
        }
        this.attachedJob = job;
        Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new ByteChannelSequentialJVM$attachJob$1(this), 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ac A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00ad -> B:14:0x003d). Please submit an issue!!! */
    @Override // kotlinx.coroutines.io.ByteReadChannel
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object consumeEachBufferRange(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.nio.ByteBuffer, ? super java.lang.Boolean, java.lang.Boolean> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            Method dump skipped, instructions count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialJVM.consumeEachBufferRange(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    public <R> R lookAhead(@NotNull Function1<? super LookAheadSession, ? extends R> visitor) {
        Intrinsics.checkParameterIsNotNull(visitor, "visitor");
        throw new NotImplementedError(GeneratedOutlineSupport1.outline72("An operation is not implemented: ", "not implemented"));
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public <R> Object lookAheadSuspend(@NotNull Function2<? super LookAheadSuspendSession, ? super Continuation<? super R>, ? extends Object> function2, @NotNull Continuation<? super R> continuation) {
        throw new NotImplementedError(GeneratedOutlineSupport1.outline72("An operation is not implemented: ", "not implemented"));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x008c  */
    @Override // kotlinx.coroutines.io.ByteReadChannel
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object read(int r5, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, kotlin.Unit> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.io.ByteChannelSequentialJVM$read$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.io.ByteChannelSequentialJVM$read$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialJVM$read$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialJVM$read$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialJVM$read$1
            r0.<init>(r4, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L42
            if (r2 != r3) goto L3a
            java.lang.Object r5 = r0.L$1
            r6 = r5
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            int r5 = r0.I$0
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialJVM r0 = (kotlinx.coroutines.io.ByteChannelSequentialJVM) r0
            boolean r1 = r7 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L35
            goto L5d
        L35:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        L3a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L42:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto La6
            if (r5 < 0) goto L4a
            r7 = r3
            goto L4b
        L4a:
            r7 = 0
        L4b:
            if (r7 == 0) goto L9a
            r0.L$0 = r4
            r0.I$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r4.await(r5, r0)
            if (r7 != r1) goto L5c
            return r1
        L5c:
            r0 = r4
        L5d:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L8c
            kotlinx.io.core.ByteReadPacket r7 = r0.getReadable()
            java.nio.ByteBuffer r5 = kotlinx.io.core.ByteBuffersKt.nioBuffer(r7, r5)
            if (r5 == 0) goto L89
            int r0 = r5.position()
            r6.mo12165invoke(r5)     // Catch: java.lang.Throwable -> L7f
            int r5 = r5.position()
            int r5 = r5 - r0
            kotlinx.io.core.ByteBuffersKt.afterNioBufferUsed(r7, r5)
            goto L89
        L7f:
            r6 = move-exception
            int r5 = r5.position()
            int r5 = r5 - r0
            kotlinx.io.core.ByteBuffersKt.afterNioBufferUsed(r7, r5)
            throw r6
        L89:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L8c:
            java.io.EOFException r6 = new java.io.EOFException
            java.lang.String r7 = "Channel closed while "
            java.lang.String r0 = " bytes expected"
            java.lang.String r5 = com.android.tools.r8.GeneratedOutlineSupport1.outline52(r7, r5, r0)
            r6.<init>(r5)
            throw r6
        L9a:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Failed requirement."
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        La6:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialJVM.read(int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readAvailable(@NotNull ByteBuffer byteBuffer, @NotNull Continuation<? super Integer> continuation) {
        int tryReadAvailable = tryReadAvailable(byteBuffer);
        if (tryReadAvailable != 0) {
            return Boxing.boxInt(tryReadAvailable);
        }
        return !byteBuffer.hasRemaining() ? Boxing.boxInt(0) : readAvailableSuspend(byteBuffer, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0074  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readAvailableSuspend(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.io.ByteChannelSequentialJVM$readAvailableSuspend$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.io.ByteChannelSequentialJVM$readAvailableSuspend$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialJVM$readAvailableSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialJVM$readAvailableSuspend$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialJVM$readAvailableSuspend$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L54
            if (r2 == r4) goto L42
            if (r2 != r3) goto L3a
            java.lang.Object r6 = r0.L$1
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialJVM r6 = (kotlinx.coroutines.io.ByteChannelSequentialJVM) r6
            boolean r6 = r7 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L35
            goto L81
        L35:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L3a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L42:
            java.lang.Object r6 = r0.L$1
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialJVM r2 = (kotlinx.coroutines.io.ByteChannelSequentialJVM) r2
            boolean r4 = r7 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L4f
            goto L66
        L4f:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L54:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L82
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r5.await(r4, r0)
            if (r7 != r1) goto L65
            return r1
        L65:
            r2 = r5
        L66:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L74
            r6 = -1
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r6
        L74:
            r0.L$0 = r2
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r2.readAvailable(r6, r0)
            if (r7 != r1) goto L81
            return r1
        L81:
            return r7
        L82:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialJVM.readAvailableSuspend(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteReadChannel
    @Nullable
    public Object readFully(@NotNull ByteBuffer byteBuffer, @NotNull Continuation<? super Integer> continuation) {
        int tryReadAvailable = tryReadAvailable(byteBuffer);
        if (tryReadAvailable != -1) {
            return !byteBuffer.hasRemaining() ? Boxing.boxInt(tryReadAvailable) : readFullySuspend(byteBuffer, tryReadAvailable, continuation);
        }
        throw new EOFException("Channel closed");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0089  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0066 -> B:27:0x0069). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object readFullySuspend(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r9, int r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof kotlinx.coroutines.io.ByteChannelSequentialJVM$readFullySuspend$1
            if (r0 == 0) goto L13
            r0 = r11
            kotlinx.coroutines.io.ByteChannelSequentialJVM$readFullySuspend$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialJVM$readFullySuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialJVM$readFullySuspend$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialJVM$readFullySuspend$1
            r0.<init>(r8, r11)
        L18:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L48
            if (r2 != r3) goto L40
            int r9 = r0.I$1
            int r10 = r0.I$0
            java.lang.Object r2 = r0.L$1
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialJVM r4 = (kotlinx.coroutines.io.ByteChannelSequentialJVM) r4
            boolean r5 = r11 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L3b
            r7 = r10
            r10 = r9
            r9 = r2
            r2 = r1
            r1 = r7
            goto L69
        L3b:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r9 = r11.exception
            throw r9
        L40:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L48:
            boolean r2 = r11 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L8e
            r11 = r8
            r2 = r1
            r1 = r10
        L4f:
            boolean r4 = r9.hasRemaining()
            if (r4 == 0) goto L89
            r0.L$0 = r11
            r0.L$1 = r9
            r0.I$0 = r1
            r0.I$1 = r10
            r0.label = r3
            java.lang.Object r4 = r11.await(r3, r0)
            if (r4 != r2) goto L66
            return r2
        L66:
            r7 = r4
            r4 = r11
            r11 = r7
        L69:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            java.lang.String r5 = "Channel closed"
            if (r11 == 0) goto L83
            int r11 = r4.tryReadAvailable(r9)
            r6 = -1
            if (r11 == r6) goto L7d
            int r10 = r10 + r11
            r11 = r4
            goto L4f
        L7d:
            java.io.EOFException r9 = new java.io.EOFException
            r9.<init>(r5)
            throw r9
        L83:
            java.io.EOFException r9 = new java.io.EOFException
            r9.<init>(r5)
            throw r9
        L89:
            java.lang.Integer r9 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r10)
            return r9
        L8e:
            kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
            java.lang.Throwable r9 = r11.exception
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialJVM.readFullySuspend(java.nio.ByteBuffer, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object write(int i, @NotNull Function1<? super ByteBuffer, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        if (getClosed()) {
            Throwable closedCause = getClosedCause();
            if (closedCause == null) {
                throw new ClosedSendChannelException("Channel closed for write");
            }
            throw closedCause;
        }
        BytePacketBuilder writable = getWritable();
        ByteBuffer nioBuffer = ByteBuffersKt.nioBuffer(writable, i);
        int position = nioBuffer.position();
        function1.mo12165invoke(nioBuffer);
        ByteBuffersKt.afterNioBufferUsed(writable, nioBuffer.position() - position);
        return awaitFreeSpace(continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0041  */
    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object writeAvailable(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.io.ByteChannelSequentialJVM$writeAvailable$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.io.ByteChannelSequentialJVM$writeAvailable$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialJVM$writeAvailable$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialJVM$writeAvailable$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialJVM$writeAvailable$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L41
            if (r2 != r3) goto L39
            int r5 = r0.I$0
            java.lang.Object r5 = r0.L$1
            java.nio.ByteBuffer r5 = (java.nio.ByteBuffer) r5
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialJVM r5 = (kotlinx.coroutines.io.ByteChannelSequentialJVM) r5
            boolean r5 = r6 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L34
            goto L63
        L34:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r5 = r6.exception
            throw r5
        L39:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L41:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L6e
            int r6 = r4.tryWriteAvailable(r5)
            if (r6 <= 0) goto L4c
            goto L69
        L4c:
            boolean r2 = r5.hasRemaining()
            if (r2 != 0) goto L54
            r6 = 0
            goto L69
        L54:
            r0.L$0 = r4
            r0.L$1 = r5
            r0.I$0 = r6
            r0.label = r3
            java.lang.Object r6 = r4.writeAvailableSuspend(r5, r0)
            if (r6 != r1) goto L63
            return r1
        L63:
            java.lang.Number r6 = (java.lang.Number) r6
            int r6 = r6.intValue()
        L69:
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r5
        L6e:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r5 = r6.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialJVM.writeAvailable(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0072 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0073 A[PHI: r7 
      PHI: (r7v5 java.lang.Object) = (r7v4 java.lang.Object), (r7v1 java.lang.Object) binds: [B:31:0x0070, B:13:0x0032] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeAvailableSuspend(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.io.ByteChannelSequentialJVM$writeAvailableSuspend$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.io.ByteChannelSequentialJVM$writeAvailableSuspend$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialJVM$writeAvailableSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialJVM$writeAvailableSuspend$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialJVM$writeAvailableSuspend$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L54
            if (r2 == r4) goto L42
            if (r2 != r3) goto L3a
            java.lang.Object r6 = r0.L$1
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialJVM r6 = (kotlinx.coroutines.io.ByteChannelSequentialJVM) r6
            boolean r6 = r7 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L35
            goto L73
        L35:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L3a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L42:
            java.lang.Object r6 = r0.L$1
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialJVM r2 = (kotlinx.coroutines.io.ByteChannelSequentialJVM) r2
            boolean r4 = r7 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L4f
            goto L66
        L4f:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L54:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L74
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r5.awaitFreeSpace(r0)
            if (r7 != r1) goto L65
            return r1
        L65:
            r2 = r5
        L66:
            r0.L$0 = r2
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r2.writeAvailable(r6, r0)
            if (r7 != r1) goto L73
            return r1
        L73:
            return r7
        L74:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialJVM.writeAvailableSuspend(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @Nullable
    public Object writeFully(@NotNull ByteBuffer byteBuffer, @NotNull Continuation<? super Unit> continuation) {
        tryWriteAvailable(byteBuffer);
        return !byteBuffer.hasRemaining() ? Unit.INSTANCE : writeFullySuspend(byteBuffer, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0055 -> B:26:0x0058). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object writeFullySuspend(@org.jetbrains.annotations.NotNull java.nio.ByteBuffer r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.io.ByteChannelSequentialJVM$writeFullySuspend$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.io.ByteChannelSequentialJVM$writeFullySuspend$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialJVM$writeFullySuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialJVM$writeFullySuspend$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialJVM$writeFullySuspend$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L40
            if (r2 != r3) goto L38
            java.lang.Object r6 = r0.L$1
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialJVM r2 = (kotlinx.coroutines.io.ByteChannelSequentialJVM) r2
            boolean r4 = r7 instanceof kotlin.Result.Failure
            if (r4 != 0) goto L33
            r7 = r2
            goto L58
        L33:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        L38:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L40:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L5f
            r7 = r5
        L45:
            boolean r2 = r6.hasRemaining()
            if (r2 == 0) goto L5c
            r0.L$0 = r7
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r2 = r7.awaitFreeSpace(r0)
            if (r2 != r1) goto L58
            return r1
        L58:
            r7.tryWriteAvailable(r6)
            goto L45
        L5c:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L5f:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r6 = r7.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialJVM.writeFullySuspend(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x009f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0096 -> B:32:0x0098). Please submit an issue!!! */
    @Override // kotlinx.coroutines.io.ByteWriteChannel
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object writeWhile(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, java.lang.Boolean> r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof kotlinx.coroutines.io.ByteChannelSequentialJVM$writeWhile$1
            if (r0 == 0) goto L13
            r0 = r10
            kotlinx.coroutines.io.ByteChannelSequentialJVM$writeWhile$1 r0 = (kotlinx.coroutines.io.ByteChannelSequentialJVM$writeWhile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.io.ByteChannelSequentialJVM$writeWhile$1 r0 = new kotlinx.coroutines.io.ByteChannelSequentialJVM$writeWhile$1
            r0.<init>(r8, r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            java.lang.Object r9 = r0.L$2
            kotlin.jvm.internal.Ref$BooleanRef r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9
            java.lang.Object r2 = r0.L$1
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.io.ByteChannelSequentialJVM r4 = (kotlinx.coroutines.io.ByteChannelSequentialJVM) r4
            boolean r5 = r10 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L37
            r10 = r2
            goto L98
        L37:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r9 = r10.exception
            throw r9
        L3c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L44:
            boolean r2 = r10 instanceof kotlin.Result.Failure
            if (r2 != 0) goto La1
            r10 = r9
            r9 = r8
        L4a:
            boolean r2 = r9.getClosed()
            if (r2 == 0) goto L5f
            java.lang.Throwable r9 = r9.getClosedCause()
            if (r9 == 0) goto L57
            goto L5e
        L57:
            kotlinx.coroutines.channels.ClosedSendChannelException r9 = new kotlinx.coroutines.channels.ClosedSendChannelException
            java.lang.String r10 = "Channel closed for write"
            r9.<init>(r10)
        L5e:
            throw r9
        L5f:
            kotlin.jvm.internal.Ref$BooleanRef r2 = new kotlin.jvm.internal.Ref$BooleanRef
            r2.<init>()
            r4 = 0
            r2.element = r4
            kotlinx.io.core.BytePacketBuilder r4 = r9.getWritable()
            java.nio.ByteBuffer r5 = kotlinx.io.core.ByteBuffersKt.nioBuffer(r4, r3)
            int r6 = r5.position()
            java.lang.Object r7 = r10.mo12165invoke(r5)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            r2.element = r7
            int r5 = r5.position()
            int r5 = r5 - r6
            kotlinx.io.core.ByteBuffersKt.afterNioBufferUsed(r4, r5)
            r0.L$0 = r9
            r0.L$1 = r10
            r0.L$2 = r2
            r0.label = r3
            java.lang.Object r4 = r9.awaitFreeSpace(r0)
            if (r4 != r1) goto L96
            return r1
        L96:
            r4 = r9
            r9 = r2
        L98:
            boolean r9 = r9.element
            if (r9 != 0) goto L9f
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L9f:
            r9 = r4
            goto L4a
        La1:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r9 = r10.exception
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.io.ByteChannelSequentialJVM.writeWhile(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
