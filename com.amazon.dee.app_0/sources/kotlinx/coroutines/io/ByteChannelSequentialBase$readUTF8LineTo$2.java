package kotlinx.coroutines.io;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.core.ByteReadPacketBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteChannelSequential.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\f\b\u0000\u0010\u0002*\u00060\u0003j\u0002`\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "Lkotlinx/io/core/ByteReadPacketBase;", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "size", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx.coroutines.io.ByteChannelSequentialBase$readUTF8LineTo$2", f = "ByteChannelSequential.kt", i = {}, l = {569}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class ByteChannelSequentialBase$readUTF8LineTo$2 extends SuspendLambda implements Function2<Integer, Continuation<? super ByteReadPacketBase>, Object> {
    int label;
    private int p$0;
    final /* synthetic */ ByteChannelSequentialBase this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteChannelSequentialBase$readUTF8LineTo$2(ByteChannelSequentialBase byteChannelSequentialBase, Continuation continuation) {
        super(2, continuation);
        this.this$0 = byteChannelSequentialBase;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ByteChannelSequentialBase$readUTF8LineTo$2 byteChannelSequentialBase$readUTF8LineTo$2 = new ByteChannelSequentialBase$readUTF8LineTo$2(this.this$0, completion);
        Number number = (Number) obj;
        number.intValue();
        byteChannelSequentialBase$readUTF8LineTo$2.p$0 = number.intValue();
        return byteChannelSequentialBase$readUTF8LineTo$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(Integer num, Continuation<? super ByteReadPacketBase> continuation) {
        return ((ByteChannelSequentialBase$readUTF8LineTo$2) create(num, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
        } else if (obj instanceof Result.Failure) {
            throw ((Result.Failure) obj).exception;
        } else {
            int i2 = this.p$0;
            this.this$0.afterRead();
            ByteChannelSequentialBase byteChannelSequentialBase = this.this$0;
            this.label = 1;
            obj = byteChannelSequentialBase.await(i2, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        if (((Boolean) obj).booleanValue()) {
            return this.this$0.getReadable();
        }
        return null;
    }
}
