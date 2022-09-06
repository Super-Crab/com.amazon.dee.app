package io.ktor.network.sockets;

import io.ktor.network.selector.SelectInterest;
import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectorManager;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.io.ByteChannel;
import kotlinx.coroutines.io.ByteWriteChannelKt;
import kotlinx.coroutines.io.WriterScope;
import kotlinx.coroutines.io.WriterSuspendSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CIOReader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/WriterScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1", f = "CIOReader.kt", i = {}, l = {61}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class CIOReaderKt$attachForReadingDirectImpl$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteChannel $channel;
    final /* synthetic */ ReadableByteChannel $nioChannel;
    final /* synthetic */ Selectable $selectable;
    final /* synthetic */ SelectorManager $selector;
    int label;
    private WriterScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CIOReader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/WriterSuspendSession;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
    @DebugMetadata(c = "io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1$1", f = "CIOReader.kt", i = {0, 1, 1}, l = {67, 77}, m = "invokeSuspend", n = {"buffer", "buffer", "rc"}, s = {"L$1", "L$1", "I$0"})
    /* renamed from: io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<WriterSuspendSession, Continuation<? super Unit>, Object> {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        private WriterSuspendSession p$;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
            anonymousClass1.p$ = (WriterSuspendSession) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: invoke */
        public final Object mo12248invoke(WriterSuspendSession writerSuspendSession, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(writerSuspendSession, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0074, code lost:
            return kotlin.Unit.INSTANCE;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L3a
                if (r1 == r3) goto L28
                if (r1 != r2) goto L20
                java.lang.Object r1 = r9.L$1
                kotlinx.io.core.IoBuffer r1 = (kotlinx.io.core.IoBuffer) r1
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.io.WriterSuspendSession r1 = (kotlinx.coroutines.io.WriterSuspendSession) r1
                boolean r4 = r10 instanceof kotlin.Result.Failure
                if (r4 != 0) goto L1b
                goto L34
            L1b:
                kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
                java.lang.Throwable r10 = r10.exception
                throw r10
            L20:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L28:
                java.lang.Object r1 = r9.L$1
                kotlinx.io.core.IoBuffer r1 = (kotlinx.io.core.IoBuffer) r1
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.io.WriterSuspendSession r1 = (kotlinx.coroutines.io.WriterSuspendSession) r1
                boolean r4 = r10 instanceof kotlin.Result.Failure
                if (r4 != 0) goto L35
            L34:
                goto L41
            L35:
                kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
                java.lang.Throwable r10 = r10.exception
                throw r10
            L3a:
                boolean r1 = r10 instanceof kotlin.Result.Failure
                if (r1 != 0) goto La2
                kotlinx.coroutines.io.WriterSuspendSession r10 = r9.p$
                r1 = r10
            L41:
                r10 = r9
            L42:
                kotlinx.io.core.IoBuffer r4 = r1.request(r3)
                if (r4 != 0) goto L67
                io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1 r5 = io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1.this
                kotlinx.coroutines.io.ByteChannel r5 = r5.$channel
                boolean r5 = r5.isClosedForWrite()
                if (r5 == 0) goto L53
                goto L72
            L53:
                io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1 r5 = io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1.this
                kotlinx.coroutines.io.ByteChannel r5 = r5.$channel
                r5.flush()
                r10.L$0 = r1
                r10.L$1 = r4
                r10.label = r3
                java.lang.Object r4 = r1.tryAwait(r3, r10)
                if (r4 != r0) goto L42
                return r0
            L67:
                io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1 r5 = io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1.this
                java.nio.channels.ReadableByteChannel r5 = r5.$nioChannel
                int r5 = kotlinx.io.nio.ChannelsKt.read(r5, r4)
                r6 = -1
                if (r5 != r6) goto L75
            L72:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            L75:
                if (r5 != 0) goto L9e
                io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1 r6 = io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1.this
                kotlinx.coroutines.io.ByteChannel r6 = r6.$channel
                r6.flush()
                io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1 r6 = io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1.this
                io.ktor.network.selector.Selectable r6 = r6.$selectable
                io.ktor.network.selector.SelectInterest r7 = io.ktor.network.selector.SelectInterest.READ
                r6.interestOp(r7, r3)
                io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1 r6 = io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1.this
                io.ktor.network.selector.SelectorManager r7 = r6.$selector
                io.ktor.network.selector.Selectable r6 = r6.$selectable
                io.ktor.network.selector.SelectInterest r8 = io.ktor.network.selector.SelectInterest.READ
                r10.L$0 = r1
                r10.L$1 = r4
                r10.I$0 = r5
                r10.label = r2
                java.lang.Object r4 = r7.select(r6, r8, r10)
                if (r4 != r0) goto L42
                return r0
            L9e:
                r1.written(r5)
                goto L42
            La2:
                kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
                java.lang.Throwable r10 = r10.exception
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CIOReaderKt$attachForReadingDirectImpl$1(Selectable selectable, ByteChannel byteChannel, ReadableByteChannel readableByteChannel, SelectorManager selectorManager, Continuation continuation) {
        super(2, continuation);
        this.$selectable = selectable;
        this.$channel = byteChannel;
        this.$nioChannel = readableByteChannel;
        this.$selector = selectorManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CIOReaderKt$attachForReadingDirectImpl$1 cIOReaderKt$attachForReadingDirectImpl$1 = new CIOReaderKt$attachForReadingDirectImpl$1(this.$selectable, this.$channel, this.$nioChannel, this.$selector, completion);
        cIOReaderKt$attachForReadingDirectImpl$1.p$ = (WriterScope) obj;
        return cIOReaderKt$attachForReadingDirectImpl$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((CIOReaderKt$attachForReadingDirectImpl$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        boolean z;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                if (obj instanceof Result.Failure) {
                    throw ((Result.Failure) obj).exception;
                }
            } else if (!(obj instanceof Result.Failure)) {
                this.$selectable.interestOp(SelectInterest.READ, false);
                ByteChannel byteChannel = this.$channel;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(null);
                this.label = 1;
                if (byteChannel.writeSuspendSession(anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                throw ((Result.Failure) obj).exception;
            }
            ByteWriteChannelKt.close(this.$channel);
            if (z) {
                try {
                    ((SocketChannel) this.$nioChannel).socket().shutdownInput();
                } catch (ClosedChannelException unused) {
                }
            }
            return Unit.INSTANCE;
        } finally {
            ReadableByteChannel readableByteChannel = this.$nioChannel;
            if (readableByteChannel instanceof SocketChannel) {
                try {
                    ((SocketChannel) readableByteChannel).socket().shutdownInput();
                } catch (ClosedChannelException unused2) {
                }
            }
        }
    }
}
