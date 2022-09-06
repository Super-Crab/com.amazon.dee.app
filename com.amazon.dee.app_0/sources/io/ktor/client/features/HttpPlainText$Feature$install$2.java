package io.ktor.client.features;

import com.amazon.alexa.mobilytics.event.LogLevel;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.call.TypeInfo;
import io.ktor.client.response.HttpResponse;
import io.ktor.client.response.HttpResponseContainer;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpPlainText.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/response/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "<name for destructuring parameter 0>", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.features.HttpPlainText$Feature$install$2", f = "HttpPlainText.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {48, 49}, m = "invokeSuspend", n = {"$info_response", LogLevel.INFO, "response", "$info_response", LogLevel.INFO, "response", "content"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$4"})
/* loaded from: classes3.dex */
final class HttpPlainText$Feature$install$2 extends SuspendLambda implements Function3<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, Continuation<? super Unit>, Object> {
    final /* synthetic */ HttpPlainText $feature;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private PipelineContext p$;
    private HttpResponseContainer p$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpPlainText$Feature$install$2(HttpPlainText httpPlainText, Continuation continuation) {
        super(3, continuation);
        this.$feature = httpPlainText;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull PipelineContext<HttpResponseContainer, HttpClientCall> receiver$0, @NotNull HttpResponseContainer httpResponseContainer, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(httpResponseContainer, "<name for destructuring parameter 0>");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        HttpPlainText$Feature$install$2 httpPlainText$Feature$install$2 = new HttpPlainText$Feature$install$2(this.$feature, continuation);
        httpPlainText$Feature$install$2.p$ = receiver$0;
        httpPlainText$Feature$install$2.p$0 = httpResponseContainer;
        return httpPlainText$Feature$install$2;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, Continuation<? super Unit> continuation) {
        return ((HttpPlainText$Feature$install$2) create(pipelineContext, httpResponseContainer, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v13 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        PipelineContext pipelineContext;
        HttpResponseContainer httpResponseContainer;
        Object component2;
        TypeInfo typeInfo;
        Object obj2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r1 = this.label;
        try {
            if (r1 != 0) {
                if (r1 != 1) {
                    if (r1 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    String str = (String) this.L$4;
                    obj2 = this.L$3;
                    TypeInfo typeInfo2 = (TypeInfo) this.L$2;
                    HttpResponseContainer httpResponseContainer2 = (HttpResponseContainer) this.L$1;
                    PipelineContext pipelineContext2 = (PipelineContext) this.L$0;
                    try {
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        ((HttpResponse) obj2).close();
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        th = th;
                        r1 = obj2;
                        ((HttpResponse) r1).close();
                        throw th;
                    }
                }
                component2 = this.L$3;
                typeInfo = (TypeInfo) this.L$2;
                httpResponseContainer = (HttpResponseContainer) this.L$1;
                pipelineContext = (PipelineContext) this.L$0;
                if (obj instanceof Result.Failure) {
                    throw ((Result.Failure) obj).exception;
                }
            } else if (!(obj instanceof Result.Failure)) {
                pipelineContext = this.p$;
                httpResponseContainer = this.p$0;
                TypeInfo component1 = httpResponseContainer.component1();
                component2 = httpResponseContainer.component2();
                if (!(!Intrinsics.areEqual(component1.getType(), Reflection.getOrCreateKotlinClass(String.class))) && (component2 instanceof HttpResponse)) {
                    this.L$0 = pipelineContext;
                    this.L$1 = httpResponseContainer;
                    this.L$2 = component1;
                    this.L$3 = component2;
                    this.label = 1;
                    Object read$ktor_client_core = this.$feature.read$ktor_client_core((HttpResponse) component2, this);
                    if (read$ktor_client_core == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    typeInfo = component1;
                    obj = read$ktor_client_core;
                } else {
                    return Unit.INSTANCE;
                }
            } else {
                throw ((Result.Failure) obj).exception;
            }
            String str2 = (String) obj;
            HttpResponseContainer httpResponseContainer3 = new HttpResponseContainer(typeInfo, str2);
            this.L$0 = pipelineContext;
            this.L$1 = httpResponseContainer;
            this.L$2 = typeInfo;
            this.L$3 = component2;
            this.L$4 = str2;
            this.label = 2;
            if (pipelineContext.proceedWith(httpResponseContainer3, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj2 = component2;
            ((HttpResponse) obj2).close();
            return Unit.INSTANCE;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
