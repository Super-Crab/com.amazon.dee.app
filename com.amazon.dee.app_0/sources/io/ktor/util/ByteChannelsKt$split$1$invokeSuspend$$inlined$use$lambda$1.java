package io.ktor.util;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.io.ByteChannel;
import kotlinx.io.core.ByteReadPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteChannels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "io/ktor/util/ByteChannelsKt$split$1$1$1"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.util.ByteChannelsKt$split$1$1$1", f = "ByteChannels.kt", i = {}, l = {23}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class ByteChannelsKt$split$1$invokeSuspend$$inlined$use$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteReadPacket $chunk;
    final /* synthetic */ CoroutineScope $receiver$0$inlined;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ ByteChannelsKt$split$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteChannelsKt$split$1$invokeSuspend$$inlined$use$lambda$1(ByteReadPacket byteReadPacket, Continuation continuation, ByteChannelsKt$split$1 byteChannelsKt$split$1, CoroutineScope coroutineScope) {
        super(2, continuation);
        this.$chunk = byteReadPacket;
        this.this$0 = byteChannelsKt$split$1;
        this.$receiver$0$inlined = coroutineScope;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ByteChannelsKt$split$1$invokeSuspend$$inlined$use$lambda$1 byteChannelsKt$split$1$invokeSuspend$$inlined$use$lambda$1 = new ByteChannelsKt$split$1$invokeSuspend$$inlined$use$lambda$1(this.$chunk, completion, this.this$0, this.$receiver$0$inlined);
        byteChannelsKt$split$1$invokeSuspend$$inlined$use$lambda$1.p$ = (CoroutineScope) obj;
        return byteChannelsKt$split$1$invokeSuspend$$inlined$use$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ByteChannelsKt$split$1$invokeSuspend$$inlined$use$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            ByteChannel byteChannel = this.this$0.$first;
            ByteReadPacket copy = this.$chunk.copy();
            this.label = 1;
            if (byteChannel.writePacket(copy, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
