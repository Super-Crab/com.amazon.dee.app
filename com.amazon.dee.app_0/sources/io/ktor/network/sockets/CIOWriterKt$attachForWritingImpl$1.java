package io.ktor.network.sockets;

import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectorManager;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.io.ByteChannel;
import kotlinx.coroutines.io.ReaderScope;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CIOWriter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/ReaderScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.network.sockets.CIOWriterKt$attachForWritingImpl$1", f = "CIOWriter.kt", i = {1}, l = {25, 34}, m = "invokeSuspend", n = {"rc"}, s = {"I$0"})
/* loaded from: classes3.dex */
final class CIOWriterKt$attachForWritingImpl$1 extends SuspendLambda implements Function2<ReaderScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteBuffer $buffer;
    final /* synthetic */ ByteChannel $channel;
    final /* synthetic */ WritableByteChannel $nioChannel;
    final /* synthetic */ ObjectPool $pool;
    final /* synthetic */ Selectable $selectable;
    final /* synthetic */ SelectorManager $selector;
    int I$0;
    int label;
    private ReaderScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CIOWriterKt$attachForWritingImpl$1(ByteBuffer byteBuffer, ByteChannel byteChannel, WritableByteChannel writableByteChannel, Selectable selectable, SelectorManager selectorManager, ObjectPool objectPool, Continuation continuation) {
        super(2, continuation);
        this.$buffer = byteBuffer;
        this.$channel = byteChannel;
        this.$nioChannel = writableByteChannel;
        this.$selectable = selectable;
        this.$selector = selectorManager;
        this.$pool = objectPool;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CIOWriterKt$attachForWritingImpl$1 cIOWriterKt$attachForWritingImpl$1 = new CIOWriterKt$attachForWritingImpl$1(this.$buffer, this.$channel, this.$nioChannel, this.$selectable, this.$selector, this.$pool, completion);
        cIOWriterKt$attachForWritingImpl$1.p$ = (ReaderScope) obj;
        return cIOWriterKt$attachForWritingImpl$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(ReaderScope readerScope, Continuation<? super Unit> continuation) {
        return ((CIOWriterKt$attachForWritingImpl$1) create(readerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0049 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0070 A[Catch: all -> 0x00aa, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x00aa, blocks: (B:27:0x004e, B:35:0x0070), top: B:61:0x004e }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x007f A[Catch: all -> 0x00af, TryCatch #4 {all -> 0x00af, blocks: (B:37:0x0077, B:23:0x0038, B:39:0x007f, B:41:0x0089, B:44:0x00a1), top: B:64:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00bd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x007d -> B:23:0x0038). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) {
        /*
            Method dump skipped, instructions count: 204
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.CIOWriterKt$attachForWritingImpl$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
