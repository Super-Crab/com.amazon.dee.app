package io.ktor.client.engine.android;

import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.io.ByteWriteChannel;
import kotlinx.coroutines.io.WriterScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AndroidClientEngine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/WriterScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "io/ktor/client/engine/android/AndroidClientEngineKt$writeTo$1$1"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.engine.android.AndroidClientEngineKt$writeTo$1$1", f = "AndroidClientEngine.kt", i = {}, l = {107}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AndroidClientEngineKt$writeTo$$inlined$use$lambda$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CoroutineContext $callContext$inlined;
    final /* synthetic */ OutgoingContent $this_writeTo$inlined;
    int label;
    private WriterScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidClientEngineKt$writeTo$$inlined$use$lambda$1(Continuation continuation, OutgoingContent outgoingContent, CoroutineContext coroutineContext) {
        super(2, continuation);
        this.$this_writeTo$inlined = outgoingContent;
        this.$callContext$inlined = coroutineContext;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AndroidClientEngineKt$writeTo$$inlined$use$lambda$1 androidClientEngineKt$writeTo$$inlined$use$lambda$1 = new AndroidClientEngineKt$writeTo$$inlined$use$lambda$1(completion, this.$this_writeTo$inlined, this.$callContext$inlined);
        androidClientEngineKt$writeTo$$inlined$use$lambda$1.p$ = (WriterScope) obj;
        return androidClientEngineKt$writeTo$$inlined$use$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((AndroidClientEngineKt$writeTo$$inlined$use$lambda$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            ByteWriteChannel mo12311getChannel = this.p$.mo12311getChannel();
            this.label = 1;
            if (((OutgoingContent.WriteChannelContent) this.$this_writeTo$inlined).writeTo(mo12311getChannel, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
