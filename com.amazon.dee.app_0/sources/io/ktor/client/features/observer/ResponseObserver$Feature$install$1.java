package io.ktor.client.features.observer;

import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.response.HttpResponse;
import io.ktor.util.ByteChannelsKt;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.io.ByteReadChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ResponseObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/response/HttpResponse;", "Lio/ktor/client/call/HttpClientCall;", "response", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.features.observer.ResponseObserver$Feature$install$1", f = "ResponseObserver.kt", i = {0, 0, 0}, l = {52}, m = "invokeSuspend", n = {"loggingContent", "responseContent", "newCall"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes3.dex */
public final class ResponseObserver$Feature$install$1 extends SuspendLambda implements Function3<PipelineContext<HttpResponse, HttpClientCall>, HttpResponse, Continuation<? super Unit>, Object> {
    final /* synthetic */ ResponseObserver $feature;
    final /* synthetic */ HttpClient $scope;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private PipelineContext p$;
    private HttpResponse p$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ResponseObserver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
    @DebugMetadata(c = "io.ktor.client.features.observer.ResponseObserver$Feature$install$1$1", f = "ResponseObserver.kt", i = {0}, l = {45}, m = "invokeSuspend", n = {"callForLog"}, s = {"L$0"})
    /* renamed from: io.ktor.client.features.observer.ResponseObserver$Feature$install$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ByteReadChannel $loggingContent;
        final /* synthetic */ PipelineContext $receiver$0;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PipelineContext pipelineContext, ByteReadChannel byteReadChannel, Continuation continuation) {
            super(2, continuation);
            this.$receiver$0 = pipelineContext;
            this.$loggingContent = byteReadChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$receiver$0, this.$loggingContent, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: invoke */
        public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended;
            Function2 function2;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                HttpClientCall httpClientCall = (HttpClientCall) this.L$0;
                if (obj instanceof Result.Failure) {
                    throw ((Result.Failure) obj).exception;
                }
            } else if (!(obj instanceof Result.Failure)) {
                HttpClientCall wrapWithContent = DelegatedCallKt.wrapWithContent((HttpClientCall) this.$receiver$0.getContext(), this.$loggingContent, false);
                function2 = ResponseObserver$Feature$install$1.this.$feature.responseHandler;
                HttpResponse response = wrapWithContent.getResponse();
                this.L$0 = wrapWithContent;
                this.label = 1;
                if (function2.mo12248invoke(response, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                throw ((Result.Failure) obj).exception;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ResponseObserver$Feature$install$1(HttpClient httpClient, ResponseObserver responseObserver, Continuation continuation) {
        super(3, continuation);
        this.$scope = httpClient;
        this.$feature = responseObserver;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull PipelineContext<HttpResponse, HttpClientCall> receiver$0, @NotNull HttpResponse response, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(response, "response");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        ResponseObserver$Feature$install$1 responseObserver$Feature$install$1 = new ResponseObserver$Feature$install$1(this.$scope, this.$feature, continuation);
        responseObserver$Feature$install$1.p$ = receiver$0;
        responseObserver$Feature$install$1.p$0 = response;
        return responseObserver$Feature$install$1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<HttpResponse, HttpClientCall> pipelineContext, HttpResponse httpResponse, Continuation<? super Unit> continuation) {
        return ((ResponseObserver$Feature$install$1) create(pipelineContext, httpResponse, continuation)).invokeSuspend(Unit.INSTANCE);
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
            HttpClientCall httpClientCall = (HttpClientCall) this.L$2;
            ByteReadChannel byteReadChannel = (ByteReadChannel) this.L$1;
            ByteReadChannel byteReadChannel2 = (ByteReadChannel) this.L$0;
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
        } else if (!(obj instanceof Result.Failure)) {
            PipelineContext pipelineContext = this.p$;
            Pair<ByteReadChannel, ByteReadChannel> split = ByteChannelsKt.split(this.p$0.getContent(), this.$scope);
            ByteReadChannel component1 = split.component1();
            ByteReadChannel component2 = split.component2();
            BuildersKt__Builders_commonKt.launch$default(pipelineContext, null, null, new AnonymousClass1(pipelineContext, component1, null), 3, null);
            HttpClientCall wrapWithContent = DelegatedCallKt.wrapWithContent((HttpClientCall) pipelineContext.getContext(), component2, true);
            ((HttpClientCall) pipelineContext.getContext()).setResponse$ktor_client_core(wrapWithContent.getResponse());
            ((HttpClientCall) pipelineContext.getContext()).setRequest$ktor_client_core(wrapWithContent.getRequest());
            HttpResponse response = ((HttpClientCall) pipelineContext.getContext()).getResponse();
            this.L$0 = component1;
            this.L$1 = component2;
            this.L$2 = wrapWithContent;
            this.label = 1;
            if (pipelineContext.proceedWith(response, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            throw ((Result.Failure) obj).exception;
        }
        return Unit.INSTANCE;
    }
}
