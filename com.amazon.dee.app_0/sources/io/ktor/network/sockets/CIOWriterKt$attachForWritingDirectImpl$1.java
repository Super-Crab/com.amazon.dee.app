package io.ktor.network.sockets;

import io.ktor.network.selector.SelectInterest;
import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectorManager;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
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
import kotlinx.coroutines.io.LookAheadSuspendSession;
import kotlinx.coroutines.io.ReaderScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CIOWriter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/ReaderScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1", f = "CIOWriter.kt", i = {}, l = {62}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class CIOWriterKt$attachForWritingDirectImpl$1 extends SuspendLambda implements Function2<ReaderScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteChannel $channel;
    final /* synthetic */ WritableByteChannel $nioChannel;
    final /* synthetic */ Selectable $selectable;
    final /* synthetic */ SelectorManager $selector;
    int label;
    private ReaderScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CIOWriter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/LookAheadSuspendSession;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
    @DebugMetadata(c = "io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1$1", f = "CIOWriter.kt", i = {0, 1, 1}, l = {67, 76}, m = "invokeSuspend", n = {"buffer", "buffer", "r"}, s = {"L$1", "L$1", "I$0"})
    /* renamed from: io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<LookAheadSuspendSession, Continuation<? super Unit>, Object> {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        private LookAheadSuspendSession p$;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
            anonymousClass1.p$ = (LookAheadSuspendSession) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: invoke */
        public final Object mo12248invoke(LookAheadSuspendSession lookAheadSuspendSession, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(lookAheadSuspendSession, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x004f  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0068  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x006b  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x006e  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0078  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x006b -> B:32:0x006c). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x006e -> B:34:0x0072). Please submit an issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L40
                if (r1 == r3) goto L2c
                if (r1 != r2) goto L24
                java.lang.Object r1 = r10.L$1
                java.nio.ByteBuffer r1 = (java.nio.ByteBuffer) r1
                java.lang.Object r4 = r10.L$0
                kotlinx.coroutines.io.LookAheadSuspendSession r4 = (kotlinx.coroutines.io.LookAheadSuspendSession) r4
                boolean r5 = r11 instanceof kotlin.Result.Failure
                if (r5 != 0) goto L1f
                r11 = r10
                r9 = r4
                r4 = r0
                r0 = r9
                goto L72
            L1f:
                kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
                java.lang.Throwable r11 = r11.exception
                throw r11
            L24:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L2c:
                java.lang.Object r1 = r10.L$1
                java.nio.ByteBuffer r1 = (java.nio.ByteBuffer) r1
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.io.LookAheadSuspendSession r1 = (kotlinx.coroutines.io.LookAheadSuspendSession) r1
                boolean r4 = r11 instanceof kotlin.Result.Failure
                if (r4 != 0) goto L3b
                r4 = r0
                r0 = r10
                goto L60
            L3b:
                kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
                java.lang.Throwable r11 = r11.exception
                throw r11
            L40:
                boolean r1 = r11 instanceof kotlin.Result.Failure
                if (r1 != 0) goto La8
                kotlinx.coroutines.io.LookAheadSuspendSession r11 = r10.p$
                r1 = r11
                r11 = r10
            L48:
                r4 = 0
                java.nio.ByteBuffer r4 = r1.request(r4, r3)
                if (r4 != 0) goto L6e
                r11.L$0 = r1
                r11.L$1 = r4
                r11.label = r3
                java.lang.Object r4 = r1.awaitAtLeast(r3, r11)
                if (r4 != r0) goto L5c
                return r0
            L5c:
                r9 = r0
                r0 = r11
                r11 = r4
                r4 = r9
            L60:
                java.lang.Boolean r11 = (java.lang.Boolean) r11
                boolean r11 = r11.booleanValue()
                if (r11 != 0) goto L6b
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            L6b:
                r11 = r0
            L6c:
                r0 = r4
                goto L48
            L6e:
                r9 = r4
                r4 = r0
                r0 = r1
                r1 = r9
            L72:
                boolean r5 = r1.hasRemaining()
                if (r5 == 0) goto La6
                io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1 r5 = io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1.this
                java.nio.channels.WritableByteChannel r5 = r5.$nioChannel
                int r5 = r5.write(r1)
                if (r5 != 0) goto La2
                io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1 r6 = io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1.this
                io.ktor.network.selector.Selectable r6 = r6.$selectable
                io.ktor.network.selector.SelectInterest r7 = io.ktor.network.selector.SelectInterest.WRITE
                r6.interestOp(r7, r3)
                io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1 r6 = io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1.this
                io.ktor.network.selector.SelectorManager r7 = r6.$selector
                io.ktor.network.selector.Selectable r6 = r6.$selectable
                io.ktor.network.selector.SelectInterest r8 = io.ktor.network.selector.SelectInterest.WRITE
                r11.L$0 = r0
                r11.L$1 = r1
                r11.I$0 = r5
                r11.label = r2
                java.lang.Object r5 = r7.select(r6, r8, r11)
                if (r5 != r4) goto L72
                return r4
            La2:
                r0.consumed(r5)
                goto L72
            La6:
                r1 = r0
                goto L6c
            La8:
                kotlin.Result$Failure r11 = (kotlin.Result.Failure) r11
                java.lang.Throwable r11 = r11.exception
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CIOWriterKt$attachForWritingDirectImpl$1(Selectable selectable, ByteChannel byteChannel, WritableByteChannel writableByteChannel, SelectorManager selectorManager, Continuation continuation) {
        super(2, continuation);
        this.$selectable = selectable;
        this.$channel = byteChannel;
        this.$nioChannel = writableByteChannel;
        this.$selector = selectorManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CIOWriterKt$attachForWritingDirectImpl$1 cIOWriterKt$attachForWritingDirectImpl$1 = new CIOWriterKt$attachForWritingDirectImpl$1(this.$selectable, this.$channel, this.$nioChannel, this.$selector, completion);
        cIOWriterKt$attachForWritingDirectImpl$1.p$ = (ReaderScope) obj;
        return cIOWriterKt$attachForWritingDirectImpl$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(ReaderScope readerScope, Continuation<? super Unit> continuation) {
        return ((CIOWriterKt$attachForWritingDirectImpl$1) create(readerScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                this.$selectable.interestOp(SelectInterest.WRITE, false);
                ByteChannel byteChannel = this.$channel;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(null);
                this.label = 1;
                if (byteChannel.lookAheadSuspend(anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                throw ((Result.Failure) obj).exception;
            }
            if (z) {
                try {
                    ((SocketChannel) this.$nioChannel).socket().shutdownOutput();
                } catch (ClosedChannelException unused) {
                }
            }
            return Unit.INSTANCE;
        } finally {
            this.$selectable.interestOp(SelectInterest.WRITE, false);
            WritableByteChannel writableByteChannel = this.$nioChannel;
            if (writableByteChannel instanceof SocketChannel) {
                try {
                    ((SocketChannel) writableByteChannel).socket().shutdownOutput();
                } catch (ClosedChannelException unused2) {
                }
            }
        }
    }
}
