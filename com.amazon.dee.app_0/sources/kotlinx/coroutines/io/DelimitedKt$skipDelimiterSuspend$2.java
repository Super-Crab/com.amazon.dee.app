package kotlinx.coroutines.io;

import java.io.IOException;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Delimited.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/io/LookAheadSuspendSession;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "kotlinx.coroutines.io.DelimitedKt$skipDelimiterSuspend$2", f = "Delimited.kt", i = {}, l = {57}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class DelimitedKt$skipDelimiterSuspend$2 extends SuspendLambda implements Function2<LookAheadSuspendSession, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteBuffer $delimiter;
    Object L$0;
    int label;
    private LookAheadSuspendSession p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DelimitedKt$skipDelimiterSuspend$2(ByteBuffer byteBuffer, Continuation continuation) {
        super(2, continuation);
        this.$delimiter = byteBuffer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DelimitedKt$skipDelimiterSuspend$2 delimitedKt$skipDelimiterSuspend$2 = new DelimitedKt$skipDelimiterSuspend$2(this.$delimiter, completion);
        delimitedKt$skipDelimiterSuspend$2.p$ = (LookAheadSuspendSession) obj;
        return delimitedKt$skipDelimiterSuspend$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(LookAheadSuspendSession lookAheadSuspendSession, Continuation<? super Unit> continuation) {
        return ((DelimitedKt$skipDelimiterSuspend$2) create(lookAheadSuspendSession, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        LookAheadSuspendSession lookAheadSuspendSession;
        int tryEnsureDelimiter;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            LookAheadSuspendSession lookAheadSuspendSession2 = (LookAheadSuspendSession) this.L$0;
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
            lookAheadSuspendSession = lookAheadSuspendSession2;
        } else if (!(obj instanceof Result.Failure)) {
            lookAheadSuspendSession = this.p$;
            int remaining = this.$delimiter.remaining();
            this.L$0 = lookAheadSuspendSession;
            this.label = 1;
            if (lookAheadSuspendSession.awaitAtLeast(remaining, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            throw ((Result.Failure) obj).exception;
        }
        tryEnsureDelimiter = DelimitedKt.tryEnsureDelimiter(lookAheadSuspendSession, this.$delimiter);
        if (tryEnsureDelimiter == this.$delimiter.remaining()) {
            return Unit.INSTANCE;
        }
        throw new IOException("Broken delimiter occurred");
    }
}
