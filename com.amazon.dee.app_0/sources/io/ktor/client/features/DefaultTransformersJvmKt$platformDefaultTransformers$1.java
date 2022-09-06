package io.ktor.client.features;

import com.amazon.alexa.mobilytics.event.LogLevel;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.call.TypeInfo;
import io.ktor.client.response.HttpResponse;
import io.ktor.client.response.HttpResponseContainer;
import io.ktor.util.pipeline.PipelineContext;
import java.io.InputStream;
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
import kotlinx.coroutines.Job;
import kotlinx.coroutines.io.jvm.javaio.BlockingKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DefaultTransformersJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/response/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "<name for destructuring parameter 0>", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "io.ktor.client.features.DefaultTransformersJvmKt$platformDefaultTransformers$1", f = "DefaultTransformersJvm.kt", i = {0, 0, 0, 0}, l = {15}, m = "invokeSuspend", n = {"$info_response", LogLevel.INFO, "response", "stream"}, s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes3.dex */
public final class DefaultTransformersJvmKt$platformDefaultTransformers$1 extends SuspendLambda implements Function3<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    private PipelineContext p$;
    private HttpResponseContainer p$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultTransformersJvmKt$platformDefaultTransformers$1(Continuation continuation) {
        super(3, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull PipelineContext<HttpResponseContainer, HttpClientCall> receiver$0, @NotNull HttpResponseContainer httpResponseContainer, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(httpResponseContainer, "<name for destructuring parameter 0>");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        DefaultTransformersJvmKt$platformDefaultTransformers$1 defaultTransformersJvmKt$platformDefaultTransformers$1 = new DefaultTransformersJvmKt$platformDefaultTransformers$1(continuation);
        defaultTransformersJvmKt$platformDefaultTransformers$1.p$ = receiver$0;
        defaultTransformersJvmKt$platformDefaultTransformers$1.p$0 = httpResponseContainer;
        return defaultTransformersJvmKt$platformDefaultTransformers$1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, Continuation<? super Unit> continuation) {
        return ((DefaultTransformersJvmKt$platformDefaultTransformers$1) create(pipelineContext, httpResponseContainer, continuation)).invokeSuspend(Unit.INSTANCE);
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
            InputStream inputStream = (InputStream) this.L$3;
            TypeInfo typeInfo = (TypeInfo) this.L$2;
            HttpResponseContainer httpResponseContainer = (HttpResponseContainer) this.L$1;
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            if (obj instanceof Result.Failure) {
                throw ((Result.Failure) obj).exception;
            }
        } else if (!(obj instanceof Result.Failure)) {
            PipelineContext pipelineContext2 = this.p$;
            HttpResponseContainer httpResponseContainer2 = this.p$0;
            TypeInfo component1 = httpResponseContainer2.component1();
            Object component2 = httpResponseContainer2.component2();
            if (!(component2 instanceof HttpResponse)) {
                return Unit.INSTANCE;
            }
            if (Intrinsics.areEqual(component1.getType(), Reflection.getOrCreateKotlinClass(InputStream.class))) {
                HttpResponse httpResponse = (HttpResponse) component2;
                InputStream inputStream2 = BlockingKt.toInputStream(httpResponse.getContent(), (Job) httpResponse.getCoroutineContext().get(Job.Key));
                HttpResponseContainer httpResponseContainer3 = new HttpResponseContainer(component1, inputStream2);
                this.L$0 = pipelineContext2;
                this.L$1 = httpResponseContainer2;
                this.L$2 = component1;
                this.L$3 = inputStream2;
                this.label = 1;
                if (pipelineContext2.proceedWith(httpResponseContainer3, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            throw ((Result.Failure) obj).exception;
        }
        return Unit.INSTANCE;
    }
}
