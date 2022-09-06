package io.ktor.client.engine.android;

import io.ktor.client.call.HttpClientCall;
import io.ktor.client.call.HttpEngineCall;
import io.ktor.client.request.HttpRequestData;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AndroidClientEngine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Lio/ktor/client/call/HttpEngineCall;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.engine.android.AndroidClientEngine$execute$2", f = "AndroidClientEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class AndroidClientEngine$execute$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super HttpEngineCall>, Object> {
    final /* synthetic */ HttpClientCall $call;
    final /* synthetic */ HttpRequestData $data;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ AndroidClientEngine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AndroidClientEngine.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 13})
    /* renamed from: io.ktor.client.engine.android.AndroidClientEngine$execute$2$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<Throwable, Unit> {
        final /* synthetic */ CompletableDeferred $userContext;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(CompletableDeferred completableDeferred) {
            super(1);
            this.$userContext = completableDeferred;
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12165invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable Throwable th) {
            if (th == null) {
                this.$userContext.complete(Unit.INSTANCE);
            } else {
                this.$userContext.completeExceptionally(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidClientEngine$execute$2(AndroidClientEngine androidClientEngine, HttpRequestData httpRequestData, HttpClientCall httpClientCall, Continuation continuation) {
        super(2, continuation);
        this.this$0 = androidClientEngine;
        this.$data = httpRequestData;
        this.$call = httpClientCall;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AndroidClientEngine$execute$2 androidClientEngine$execute$2 = new AndroidClientEngine$execute$2(this.this$0, this.$data, this.$call, completion);
        androidClientEngine$execute$2.p$ = (CoroutineScope) obj;
        return androidClientEngine$execute$2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super HttpEngineCall> continuation) {
        return ((AndroidClientEngine$execute$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        CoroutineContext createCallContext;
        AndroidHttpResponse execute;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            if (!(obj instanceof Result.Failure)) {
                createCallContext = this.this$0.createCallContext();
                Job executionContext = this.$data.getExecutionContext();
                if (executionContext != null) {
                    CompletableDeferred completableDeferred = (CompletableDeferred) executionContext;
                    CoroutineContext.Element element = createCallContext.get(Job.Key);
                    if (element == null) {
                        Intrinsics.throwNpe();
                    }
                    ((Job) element).invokeOnCompletion(new AnonymousClass1(completableDeferred));
                    AndroidHttpRequest androidHttpRequest = new AndroidHttpRequest(this.$call, this.$data);
                    execute = this.this$0.execute(androidHttpRequest, createCallContext);
                    return new HttpEngineCall(androidHttpRequest, execute);
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.CompletableDeferred<kotlin.Unit>");
            }
            throw ((Result.Failure) obj).exception;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
