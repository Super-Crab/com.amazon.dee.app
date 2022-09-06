package kotlinx.coroutines.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.io.internal.RingBufferCapacity;
import kotlinx.io.core.IoBuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteBufferChannel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/LookAheadSuspendSession;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx.coroutines.io.ByteBufferChannel$readSuspendableSession$2", f = "ByteBufferChannel.kt", i = {0, 0, 0}, l = {1637}, m = "invokeSuspend", n = {"lastAvailable", "lastView", "completed$"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes4.dex */
public final class ByteBufferChannel$readSuspendableSession$2 extends SuspendLambda implements Function2<LookAheadSuspendSession, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $consumer;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private LookAheadSuspendSession p$;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ByteBufferChannel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"completed", "", "newView", "Lkotlinx/io/core/IoBuffer;", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: kotlinx.coroutines.io.ByteBufferChannel$readSuspendableSession$2$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<IoBuffer, Unit> {
        final /* synthetic */ Ref.IntRef $lastAvailable;
        final /* synthetic */ Ref.ObjectRef $lastView;
        final /* synthetic */ LookAheadSuspendSession $receiver$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LookAheadSuspendSession lookAheadSuspendSession, Ref.IntRef intRef, Ref.ObjectRef objectRef) {
            super(1);
            this.$receiver$0 = lookAheadSuspendSession;
            this.$lastAvailable = intRef;
            this.$lastView = objectRef;
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12165invoke(IoBuffer ioBuffer) {
            invoke2(ioBuffer);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull IoBuffer newView) {
            Intrinsics.checkParameterIsNotNull(newView, "newView");
            int readRemaining = this.$lastAvailable.element - ((IoBuffer) this.$lastView.element).getReadRemaining();
            if (readRemaining > 0) {
                this.$receiver$0.consumed(readRemaining);
            }
            this.$lastView.element = newView;
            this.$lastAvailable.element = newView.getReadRemaining();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel$readSuspendableSession$2(ByteBufferChannel byteBufferChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = byteBufferChannel;
        this.$consumer = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ByteBufferChannel$readSuspendableSession$2 byteBufferChannel$readSuspendableSession$2 = new ByteBufferChannel$readSuspendableSession$2(this.this$0, this.$consumer, completion);
        byteBufferChannel$readSuspendableSession$2.p$ = (LookAheadSuspendSession) obj;
        return byteBufferChannel$readSuspendableSession$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(LookAheadSuspendSession lookAheadSuspendSession, Continuation<? super Unit> continuation) {
        return ((ByteBufferChannel$readSuspendableSession$2) create(lookAheadSuspendSession, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [T, kotlinx.io.core.IoBuffer] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        AnonymousClass1 anonymousClass1;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            anonymousClass1 = (AnonymousClass1) this.L$2;
            Ref.ObjectRef objectRef = (Ref.ObjectRef) this.L$1;
            Ref.IntRef intRef = (Ref.IntRef) this.L$0;
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
        } else if (!(obj instanceof Result.Failure)) {
            final LookAheadSuspendSession lookAheadSuspendSession = this.p$;
            Ref.IntRef intRef2 = new Ref.IntRef();
            intRef2.element = 0;
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = IoBuffer.Companion.getEmpty();
            final AnonymousClass1 anonymousClass12 = new AnonymousClass1(lookAheadSuspendSession, intRef2, objectRef2);
            Function2 function2 = this.$consumer;
            SuspendableReadSession suspendableReadSession = new SuspendableReadSession() { // from class: kotlinx.coroutines.io.ByteBufferChannel$readSuspendableSession$2.2
                @Override // kotlinx.coroutines.io.SuspendableReadSession
                @Nullable
                public Object await(int i2, @NotNull Continuation<? super Boolean> continuation) {
                    anonymousClass12.invoke2(IoBuffer.Companion.getEmpty());
                    return lookAheadSuspendSession.awaitAtLeast(i2, continuation);
                }

                @Override // kotlinx.coroutines.io.ReadSession
                public int discard(int i2) {
                    anonymousClass12.invoke2(IoBuffer.Companion.getEmpty());
                    ByteBufferChannel byteBufferChannel = ByteBufferChannel$readSuspendableSession$2.this.this$0;
                    ByteBuffer byteBuffer = byteBufferChannel.setupStateForRead();
                    if (byteBuffer != null) {
                        RingBufferCapacity ringBufferCapacity = byteBufferChannel.state.capacity;
                        try {
                            if (ringBufferCapacity.availableForRead != 0) {
                                int availableForRead = getAvailableForRead();
                                ByteBufferChannel$readSuspendableSession$2.this.this$0.bytesRead(byteBuffer, ringBufferCapacity, availableForRead);
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
                    return ByteBufferChannel$readSuspendableSession$2.this.this$0.getAvailableForRead();
                }

                @Override // kotlinx.coroutines.io.ReadSession
                @Nullable
                public IoBuffer request(int i2) {
                    ByteBuffer request = lookAheadSuspendSession.request(0, i2);
                    if (request != null) {
                        IoBuffer ioBuffer = new IoBuffer(request);
                        ioBuffer.resetForRead();
                        anonymousClass12.invoke2(ioBuffer);
                        return ioBuffer;
                    }
                    return null;
                }
            };
            this.L$0 = intRef2;
            this.L$1 = objectRef2;
            this.L$2 = anonymousClass12;
            this.label = 1;
            if (function2.mo12248invoke(suspendableReadSession, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            anonymousClass1 = anonymousClass12;
        } else {
            throw ((Result.Failure) obj).exception;
        }
        anonymousClass1.invoke2(IoBuffer.Companion.getEmpty());
        return Unit.INSTANCE;
    }
}
