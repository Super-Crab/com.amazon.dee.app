package io.ktor.network.sockets;

import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectorManager;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.io.ByteChannel;
import kotlinx.coroutines.io.WriterScope;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CIOReader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/WriterScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.network.sockets.CIOReaderKt$attachForReadingImpl$1", f = "CIOReader.kt", i = {0, 1}, l = {31, 35}, m = "invokeSuspend", n = {"rc", "rc"}, s = {"I$0", "I$0"})
/* loaded from: classes3.dex */
public final class CIOReaderKt$attachForReadingImpl$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteBuffer $buffer;
    final /* synthetic */ ByteChannel $channel;
    final /* synthetic */ ReadableByteChannel $nioChannel;
    final /* synthetic */ ObjectPool $pool;
    final /* synthetic */ Selectable $selectable;
    final /* synthetic */ SelectorManager $selector;
    int I$0;
    int label;
    private WriterScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CIOReaderKt$attachForReadingImpl$1(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, ByteChannel byteChannel, Selectable selectable, SelectorManager selectorManager, ObjectPool objectPool, Continuation continuation) {
        super(2, continuation);
        this.$nioChannel = readableByteChannel;
        this.$buffer = byteBuffer;
        this.$channel = byteChannel;
        this.$selectable = selectable;
        this.$selector = selectorManager;
        this.$pool = objectPool;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CIOReaderKt$attachForReadingImpl$1 cIOReaderKt$attachForReadingImpl$1 = new CIOReaderKt$attachForReadingImpl$1(this.$nioChannel, this.$buffer, this.$channel, this.$selectable, this.$selector, this.$pool, completion);
        cIOReaderKt$attachForReadingImpl$1.p$ = (WriterScope) obj;
        return cIOReaderKt$attachForReadingImpl$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((CIOReaderKt$attachForReadingImpl$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0040 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0099 -> B:23:0x0035). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L30
            if (r1 == r3) goto L22
            if (r1 != r2) goto L1a
            boolean r1 = r8 instanceof kotlin.Result.Failure     // Catch: java.lang.Throwable -> L2c
            if (r1 != 0) goto L15
            r8 = r7
            goto L99
        L15:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8     // Catch: java.lang.Throwable -> L2c
            java.lang.Throwable r8 = r8.exception     // Catch: java.lang.Throwable -> L2c
            throw r8     // Catch: java.lang.Throwable -> L2c
        L1a:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L22:
            boolean r1 = r8 instanceof kotlin.Result.Failure     // Catch: java.lang.Throwable -> L2c
            if (r1 != 0) goto L27
            goto L34
        L27:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8     // Catch: java.lang.Throwable -> L2c
            java.lang.Throwable r8 = r8.exception     // Catch: java.lang.Throwable -> L2c
            throw r8     // Catch: java.lang.Throwable -> L2c
        L2c:
            r8 = move-exception
            r0 = r8
            r8 = r7
            goto La0
        L30:
            boolean r1 = r8 instanceof kotlin.Result.Failure
            if (r1 != 0) goto Lb7
        L34:
            r8 = r7
        L35:
            java.nio.channels.ReadableByteChannel r1 = r8.$nioChannel     // Catch: java.lang.Throwable -> L9f
            java.nio.ByteBuffer r4 = r8.$buffer     // Catch: java.lang.Throwable -> L9f
            int r1 = r1.read(r4)     // Catch: java.lang.Throwable -> L9f
            r4 = -1
            if (r1 != r4) goto L5e
            kotlinx.coroutines.io.ByteChannel r0 = r8.$channel     // Catch: java.lang.Throwable -> L9f
            kotlinx.coroutines.io.ByteWriteChannelKt.close(r0)     // Catch: java.lang.Throwable -> L9f
            kotlinx.io.pool.ObjectPool r0 = r8.$pool
            java.nio.ByteBuffer r1 = r8.$buffer
            r0.recycle(r1)
            java.nio.channels.ReadableByteChannel r8 = r8.$nioChannel
            boolean r0 = r8 instanceof java.nio.channels.SocketChannel
            if (r0 == 0) goto L5b
            java.nio.channels.SocketChannel r8 = (java.nio.channels.SocketChannel) r8     // Catch: java.nio.channels.ClosedChannelException -> L5b
            java.net.Socket r8 = r8.socket()     // Catch: java.nio.channels.ClosedChannelException -> L5b
            r8.shutdownInput()     // Catch: java.nio.channels.ClosedChannelException -> L5b
        L5b:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L5e:
            if (r1 != 0) goto L7d
            kotlinx.coroutines.io.ByteChannel r4 = r8.$channel     // Catch: java.lang.Throwable -> L9f
            r4.flush()     // Catch: java.lang.Throwable -> L9f
            io.ktor.network.selector.Selectable r4 = r8.$selectable     // Catch: java.lang.Throwable -> L9f
            io.ktor.network.selector.SelectInterest r5 = io.ktor.network.selector.SelectInterest.READ     // Catch: java.lang.Throwable -> L9f
            r4.interestOp(r5, r3)     // Catch: java.lang.Throwable -> L9f
            io.ktor.network.selector.SelectorManager r4 = r8.$selector     // Catch: java.lang.Throwable -> L9f
            io.ktor.network.selector.Selectable r5 = r8.$selectable     // Catch: java.lang.Throwable -> L9f
            io.ktor.network.selector.SelectInterest r6 = io.ktor.network.selector.SelectInterest.READ     // Catch: java.lang.Throwable -> L9f
            r8.I$0 = r1     // Catch: java.lang.Throwable -> L9f
            r8.label = r3     // Catch: java.lang.Throwable -> L9f
            java.lang.Object r1 = r4.select(r5, r6, r8)     // Catch: java.lang.Throwable -> L9f
            if (r1 != r0) goto L35
            return r0
        L7d:
            io.ktor.network.selector.Selectable r4 = r8.$selectable     // Catch: java.lang.Throwable -> L9f
            io.ktor.network.selector.SelectInterest r5 = io.ktor.network.selector.SelectInterest.READ     // Catch: java.lang.Throwable -> L9f
            r6 = 0
            r4.interestOp(r5, r6)     // Catch: java.lang.Throwable -> L9f
            java.nio.ByteBuffer r4 = r8.$buffer     // Catch: java.lang.Throwable -> L9f
            r4.flip()     // Catch: java.lang.Throwable -> L9f
            kotlinx.coroutines.io.ByteChannel r4 = r8.$channel     // Catch: java.lang.Throwable -> L9f
            java.nio.ByteBuffer r5 = r8.$buffer     // Catch: java.lang.Throwable -> L9f
            r8.I$0 = r1     // Catch: java.lang.Throwable -> L9f
            r8.label = r2     // Catch: java.lang.Throwable -> L9f
            java.lang.Object r1 = r4.writeFully(r5, r8)     // Catch: java.lang.Throwable -> L9f
            if (r1 != r0) goto L99
            return r0
        L99:
            java.nio.ByteBuffer r1 = r8.$buffer     // Catch: java.lang.Throwable -> L9f
            r1.clear()     // Catch: java.lang.Throwable -> L9f
            goto L35
        L9f:
            r0 = move-exception
        La0:
            kotlinx.io.pool.ObjectPool r1 = r8.$pool
            java.nio.ByteBuffer r2 = r8.$buffer
            r1.recycle(r2)
            java.nio.channels.ReadableByteChannel r8 = r8.$nioChannel
            boolean r1 = r8 instanceof java.nio.channels.SocketChannel
            if (r1 == 0) goto Lb6
            java.nio.channels.SocketChannel r8 = (java.nio.channels.SocketChannel) r8     // Catch: java.nio.channels.ClosedChannelException -> Lb6
            java.net.Socket r8 = r8.socket()     // Catch: java.nio.channels.ClosedChannelException -> Lb6
            r8.shutdownInput()     // Catch: java.nio.channels.ClosedChannelException -> Lb6
        Lb6:
            throw r0
        Lb7:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r8 = r8.exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.CIOReaderKt$attachForReadingImpl$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
