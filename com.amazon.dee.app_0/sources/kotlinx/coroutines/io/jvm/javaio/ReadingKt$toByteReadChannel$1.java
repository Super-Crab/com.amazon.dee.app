package kotlinx.coroutines.io.jvm.javaio;

import java.io.InputStream;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.io.ByteWriteChannel;
import kotlinx.coroutines.io.WriterScope;
import kotlinx.io.pool.ObjectPool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Reading.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/WriterScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx.coroutines.io.jvm.javaio.ReadingKt$toByteReadChannel$1", f = "Reading.kt", i = {0, 0}, l = {61}, m = "invokeSuspend", n = {"buffer", "readCount"}, s = {"L$1", "I$0"})
/* loaded from: classes4.dex */
public final class ReadingKt$toByteReadChannel$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ObjectPool $pool;
    final /* synthetic */ InputStream $this_toByteReadChannel;
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    private WriterScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReadingKt$toByteReadChannel$1(InputStream inputStream, ObjectPool objectPool, Continuation continuation) {
        super(2, continuation);
        this.$this_toByteReadChannel = inputStream;
        this.$pool = objectPool;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ReadingKt$toByteReadChannel$1 readingKt$toByteReadChannel$1 = new ReadingKt$toByteReadChannel$1(this.$this_toByteReadChannel, this.$pool, completion);
        readingKt$toByteReadChannel$1.p$ = (WriterScope) obj;
        return readingKt$toByteReadChannel$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((ReadingKt$toByteReadChannel$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ByteBuffer byteBuffer;
        WriterScope writerScope;
        ReadingKt$toByteReadChannel$1 readingKt$toByteReadChannel$1;
        Throwable th;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            byteBuffer = (ByteBuffer) this.L$1;
            writerScope = (WriterScope) this.L$0;
            try {
                if (obj instanceof Result.Failure) {
                    throw ((Result.Failure) obj).exception;
                }
            } catch (Throwable th2) {
                th = th2;
                readingKt$toByteReadChannel$1 = this;
                try {
                    writerScope.mo12311getChannel().close(th);
                    readingKt$toByteReadChannel$1.$pool.recycle(byteBuffer);
                    readingKt$toByteReadChannel$1.$this_toByteReadChannel.close();
                    return Unit.INSTANCE;
                } catch (Throwable th3) {
                    readingKt$toByteReadChannel$1.$pool.recycle(byteBuffer);
                    readingKt$toByteReadChannel$1.$this_toByteReadChannel.close();
                    throw th3;
                }
            }
        } else if (!(obj instanceof Result.Failure)) {
            WriterScope writerScope2 = this.p$;
            byteBuffer = (ByteBuffer) this.$pool.mo12351borrow();
            writerScope = writerScope2;
        } else {
            throw ((Result.Failure) obj).exception;
        }
        readingKt$toByteReadChannel$1 = this;
        while (true) {
            try {
                byteBuffer.clear();
                int read = readingKt$toByteReadChannel$1.$this_toByteReadChannel.read(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
                if (read < 0) {
                    break;
                } else if (read != 0) {
                    byteBuffer.position(byteBuffer.position() + read);
                    byteBuffer.flip();
                    ByteWriteChannel mo12311getChannel = writerScope.mo12311getChannel();
                    readingKt$toByteReadChannel$1.L$0 = writerScope;
                    readingKt$toByteReadChannel$1.L$1 = byteBuffer;
                    readingKt$toByteReadChannel$1.I$0 = read;
                    readingKt$toByteReadChannel$1.label = 1;
                    if (mo12311getChannel.writeFully(byteBuffer, readingKt$toByteReadChannel$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                writerScope.mo12311getChannel().close(th);
                readingKt$toByteReadChannel$1.$pool.recycle(byteBuffer);
                readingKt$toByteReadChannel$1.$this_toByteReadChannel.close();
                return Unit.INSTANCE;
            }
        }
    }
}
