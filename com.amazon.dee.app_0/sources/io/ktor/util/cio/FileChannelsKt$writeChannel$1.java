package io.ktor.util.cio;

import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.io.ReaderScope;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FileChannels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/ReaderScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.util.cio.FileChannelsKt$writeChannel$1", f = "FileChannels.kt", i = {0, 0, 0, 0, 0, 0}, l = {90}, m = "invokeSuspend", n = {"$receiver$iv", "closed$iv", "file", "$receiver$iv", "instance$iv", "buffer"}, s = {"L$1", "I$0", "L$2", "L$3", "L$4", "L$5"})
/* loaded from: classes3.dex */
final class FileChannelsKt$writeChannel$1 extends SuspendLambda implements Function2<ReaderScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ObjectPool $pool;
    final /* synthetic */ File $this_writeChannel;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    private ReaderScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileChannelsKt$writeChannel$1(File file, ObjectPool objectPool, Continuation continuation) {
        super(2, continuation);
        this.$this_writeChannel = file;
        this.$pool = objectPool;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FileChannelsKt$writeChannel$1 fileChannelsKt$writeChannel$1 = new FileChannelsKt$writeChannel$1(this.$this_writeChannel, this.$pool, completion);
        fileChannelsKt$writeChannel$1.p$ = (ReaderScope) obj;
        return fileChannelsKt$writeChannel$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(ReaderScope readerScope, Continuation<? super Unit> continuation) {
        return ((FileChannelsKt$writeChannel$1) create(readerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 7, insn: 0x00a9: INVOKE  (r7 I:java.io.Closeable) type: INTERFACE call: java.io.Closeable.close():void, block:B:33:0x00a9 */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0061 A[Catch: all -> 0x002e, TryCatch #3 {all -> 0x00a8, blocks: (B:27:0x0099, B:18:0x0049, B:6:0x0023, B:25:0x007f, B:20:0x0057, B:22:0x0061, B:26:0x0097, B:9:0x0029, B:10:0x002d, B:19:0x004f), top: B:44:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0097 A[Catch: all -> 0x002e, TRY_LEAVE, TryCatch #3 {all -> 0x00a8, blocks: (B:27:0x0099, B:18:0x0049, B:6:0x0023, B:25:0x007f, B:20:0x0057, B:22:0x0061, B:26:0x0097, B:9:0x0029, B:10:0x002d, B:19:0x004f), top: B:44:0x0007 }] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.Closeable] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x007c -> B:25:0x007f). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 1
            if (r1 == 0) goto L39
            if (r1 != r2) goto L31
            java.lang.Object r1 = r12.L$5
            java.nio.ByteBuffer r1 = (java.nio.ByteBuffer) r1
            java.lang.Object r3 = r12.L$4
            java.lang.Object r4 = r12.L$3
            kotlinx.io.pool.ObjectPool r4 = (kotlinx.io.pool.ObjectPool) r4
            java.lang.Object r5 = r12.L$2
            java.io.RandomAccessFile r5 = (java.io.RandomAccessFile) r5
            int r6 = r12.I$0
            java.lang.Object r7 = r12.L$1
            java.io.Closeable r7 = (java.io.Closeable) r7
            java.lang.Object r8 = r12.L$0
            kotlinx.coroutines.io.ReaderScope r8 = (kotlinx.coroutines.io.ReaderScope) r8
            boolean r9 = r13 instanceof kotlin.Result.Failure     // Catch: java.lang.Throwable -> L2e
            if (r9 != 0) goto L29
            r13 = r12
            goto L7f
        L29:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13     // Catch: java.lang.Throwable -> L2e
            java.lang.Throwable r13 = r13.exception     // Catch: java.lang.Throwable -> L2e
            throw r13     // Catch: java.lang.Throwable -> L2e
        L2e:
            r13 = move-exception
            goto La4
        L31:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L39:
            boolean r1 = r13 instanceof kotlin.Result.Failure
            if (r1 != 0) goto Lb4
            kotlinx.coroutines.io.ReaderScope r13 = r12.p$
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile
            java.io.File r1 = r12.$this_writeChannel
            java.lang.String r3 = "rw"
            r7.<init>(r1, r3)
            r1 = 0
            kotlinx.io.pool.ObjectPool r4 = r12.$pool     // Catch: java.lang.Throwable -> La8
            java.lang.Object r3 = r4.mo12351borrow()     // Catch: java.lang.Throwable -> La8
            r5 = r3
            java.nio.ByteBuffer r5 = (java.nio.ByteBuffer) r5     // Catch: java.lang.Throwable -> L2e
            r8 = r13
            r6 = r1
            r1 = r5
            r5 = r7
            r13 = r12
        L57:
            kotlinx.coroutines.io.ByteReadChannel r9 = r8.mo12311getChannel()     // Catch: java.lang.Throwable -> L2e
            boolean r9 = r9.isClosedForRead()     // Catch: java.lang.Throwable -> L2e
            if (r9 != 0) goto L97
            r1.clear()     // Catch: java.lang.Throwable -> L2e
            kotlinx.coroutines.io.ByteReadChannel r9 = r8.mo12311getChannel()     // Catch: java.lang.Throwable -> L2e
            r13.L$0 = r8     // Catch: java.lang.Throwable -> L2e
            r13.L$1 = r7     // Catch: java.lang.Throwable -> L2e
            r13.I$0 = r6     // Catch: java.lang.Throwable -> L2e
            r13.L$2 = r5     // Catch: java.lang.Throwable -> L2e
            r13.L$3 = r4     // Catch: java.lang.Throwable -> L2e
            r13.L$4 = r3     // Catch: java.lang.Throwable -> L2e
            r13.L$5 = r1     // Catch: java.lang.Throwable -> L2e
            r13.label = r2     // Catch: java.lang.Throwable -> L2e
            java.lang.Object r9 = r9.readAvailable(r1, r13)     // Catch: java.lang.Throwable -> L2e
            if (r9 != r0) goto L7f
            return r0
        L7f:
            r1.flip()     // Catch: java.lang.Throwable -> L2e
            byte[] r9 = r1.array()     // Catch: java.lang.Throwable -> L2e
            int r10 = r1.arrayOffset()     // Catch: java.lang.Throwable -> L2e
            int r11 = r1.position()     // Catch: java.lang.Throwable -> L2e
            int r10 = r10 + r11
            int r11 = r1.limit()     // Catch: java.lang.Throwable -> L2e
            r5.write(r9, r10, r11)     // Catch: java.lang.Throwable -> L2e
            goto L57
        L97:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L2e
            r4.recycle(r3)     // Catch: java.lang.Throwable -> La8
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> La8
            r7.close()
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        La4:
            r4.recycle(r3)     // Catch: java.lang.Throwable -> La8
            throw r13     // Catch: java.lang.Throwable -> La8
        La8:
            r13 = move-exception
            r7.close()     // Catch: java.lang.Throwable -> Lad
            goto Lb1
        Lad:
            r0 = move-exception
            kotlinx.io.core.CloseableJVMKt.addSuppressedInternal(r13, r0)     // Catch: java.lang.Throwable -> Lb2
        Lb1:
            throw r13     // Catch: java.lang.Throwable -> Lb2
        Lb2:
            r13 = move-exception
            throw r13
        Lb4:
            kotlin.Result$Failure r13 = (kotlin.Result.Failure) r13
            java.lang.Throwable r13 = r13.exception
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.cio.FileChannelsKt$writeChannel$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
