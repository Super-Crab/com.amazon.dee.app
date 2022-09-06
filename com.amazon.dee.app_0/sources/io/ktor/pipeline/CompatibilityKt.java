package io.ktor.pipeline;

import com.amazon.alexa.accessorykit.ModelTransformer;
import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Compatibility.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aC\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u001e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\u00020\u0004j\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\u0002`\u00052\u0006\u0010\u0006\u001a\u0002H\u0002H\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u009c\u0001\u0010\b\u001a\u00020\u0001\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u0003*\u001a\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u00020\u0004j\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u0002`\u00052\n\u0010\n\u001a\u00060\u000bj\u0002`\f2K\b\b\u0010\r\u001aE\b\u0001\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u00020\u000fj\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u0002`\u0010\u0012\u0004\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000e¢\u0006\u0002\b\u0012H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0013*&\b\u0007\u0010\u0014\"\u00020\u00152\u00020\u0015B\u0018\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\n\b\u0019\u0012\u0006\b\n0\u001a8\u001b*&\b\u0007\u0010\u001c\"\u00020\u001d2\u00020\u001dB\u0018\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001e\u0012\n\b\u0019\u0012\u0006\b\n0\u001a8\u001b*J\b\u0007\u0010\u001f\u001a\u0004\b\u0000\u0010\t\u001a\u0004\b\u0001\u0010\u0002\"\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u00020\u00042\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u00020\u0004B\u0018\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\n\b\u0019\u0012\u0006\b\n0\u001a8\u001b*J\b\u0007\u0010 \u001a\u0004\b\u0000\u0010\t\u001a\u0004\b\u0001\u0010\u0002\"\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u00020\u000f2\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u00020\u000fB\u0018\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\n\b\u0019\u0012\u0006\b\n0\u001a8\u001b*q\b\u0007\u0010!\u001a\u0004\b\u0000\u0010\t\u001a\u0004\b\u0001\u0010\u0002\"\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u0002`\"25\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u00020\u000f\u0012\u0004\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000e¢\u0006\u0002\b\u0012B\u0018\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\n\b\u0019\u0012\u0006\b\n0\u001a8\u001b*&\b\u0007\u0010#\"\u00020\u000b2\u00020\u000bB\u0018\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\n\b\u0019\u0012\u0006\b\n0\u001a8\u001b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"execute", "", "TContext", "", "Lio/ktor/util/pipeline/Pipeline;", "Lio/ktor/pipeline/Pipeline;", "context", "(Lio/ktor/util/pipeline/Pipeline;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "intercept", "TSubject", "phase", "Lio/ktor/util/pipeline/PipelinePhase;", "Lio/ktor/pipeline/PipelinePhase;", "block", "Lkotlin/Function3;", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/pipeline/PipelineContext;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/util/pipeline/Pipeline;Lio/ktor/util/pipeline/PipelinePhase;Lkotlin/jvm/functions/Function3;)V", "ContextDsl", "Lio/ktor/util/pipeline/ContextDsl;", "Lkotlin/Deprecated;", "message", "Import it from another package", ModelTransformer.KEY_BATTERY_LEVEL, "Lkotlin/DeprecationLevel;", "ERROR", "InvalidPhaseException", "Lio/ktor/util/pipeline/InvalidPhaseException;", "Import from another package", "Pipeline", "PipelineContext", "PipelineInterceptor", "Lio/ktor/util/pipeline/PipelineInterceptor;", "PipelinePhase", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class CompatibilityKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Import it from another package")
    public static /* synthetic */ void ContextDsl$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Import from another package")
    public static /* synthetic */ void InvalidPhaseException$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Import it from another package")
    public static /* synthetic */ void Pipeline$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Import it from another package")
    public static /* synthetic */ void PipelineContext$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Import it from another package")
    public static /* synthetic */ void PipelineInterceptor$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Import it from another package")
    public static /* synthetic */ void PipelinePhase$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Import it from another package")
    @Nullable
    public static final <TContext> Object execute(@NotNull Pipeline<Unit, TContext> pipeline, @NotNull TContext tcontext, @NotNull Continuation<? super Unit> continuation) {
        return pipeline.execute(tcontext, Unit.INSTANCE, continuation);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Import it from another package")
    @Nullable
    private static final Object execute$$forInline(@NotNull Pipeline pipeline, @NotNull Object obj, @NotNull Continuation continuation) {
        Unit unit = Unit.INSTANCE;
        InlineMarker.mark(0);
        Object execute = pipeline.execute(obj, unit, continuation);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        return execute;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Import it from another package")
    private static final <TSubject, TContext> void intercept(@NotNull Pipeline<?, TContext> pipeline, PipelinePhase pipelinePhase, Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.needClassReification();
        pipeline.intercept(pipelinePhase, new CompatibilityKt$intercept$1(function3, null));
    }
}
