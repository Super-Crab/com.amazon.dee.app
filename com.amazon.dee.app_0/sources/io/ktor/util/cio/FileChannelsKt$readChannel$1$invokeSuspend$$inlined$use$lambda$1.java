package io.ktor.util.cio;

import io.ktor.util.BufferViewJvmKt;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.io.WriterScope;
import kotlinx.coroutines.io.WriterSuspendSession;
import kotlinx.io.core.IoBuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FileChannels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/WriterSuspendSession;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "io/ktor/util/cio/FileChannelsKt$readChannel$1$3$1"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.util.cio.FileChannelsKt$readChannel$1$3$1", f = "FileChannels.kt", i = {0}, l = {43}, m = "invokeSuspend", n = {"buffer"}, s = {"L$1"})
/* loaded from: classes3.dex */
final class FileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$1 extends SuspendLambda implements Function2<WriterSuspendSession, Continuation<? super Unit>, Object> {
    final /* synthetic */ FileChannel $fileChannel;
    final /* synthetic */ WriterScope $receiver$0$inlined;
    Object L$0;
    Object L$1;
    int label;
    private WriterSuspendSession p$;
    final /* synthetic */ FileChannelsKt$readChannel$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$1(FileChannel fileChannel, Continuation continuation, FileChannelsKt$readChannel$1 fileChannelsKt$readChannel$1, WriterScope writerScope) {
        super(2, continuation);
        this.$fileChannel = fileChannel;
        this.this$0 = fileChannelsKt$readChannel$1;
        this.$receiver$0$inlined = writerScope;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$1 fileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$1 = new FileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$1(this.$fileChannel, completion, this.this$0, this.$receiver$0$inlined);
        fileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$1.p$ = (WriterSuspendSession) obj;
        return fileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(WriterSuspendSession writerSuspendSession, Continuation<? super Unit> continuation) {
        return ((FileChannelsKt$readChannel$1$invokeSuspend$$inlined$use$lambda$1) create(writerSuspendSession, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        WriterSuspendSession writerSuspendSession;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            IoBuffer ioBuffer = (IoBuffer) this.L$1;
            writerSuspendSession = (WriterSuspendSession) this.L$0;
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
        } else if (obj instanceof Result.Failure) {
            throw ((Result.Failure) obj).exception;
        } else {
            writerSuspendSession = this.p$;
        }
        while (true) {
            IoBuffer request = writerSuspendSession.request(1);
            if (request == null) {
                this.$receiver$0$inlined.mo12311getChannel().flush();
                this.L$0 = writerSuspendSession;
                this.L$1 = request;
                this.label = 1;
                if (writerSuspendSession.tryAwait(1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                int read = BufferViewJvmKt.read(this.$fileChannel, request);
                if (read == -1) {
                    return Unit.INSTANCE;
                }
                writerSuspendSession.written(read);
            }
        }
    }
}
