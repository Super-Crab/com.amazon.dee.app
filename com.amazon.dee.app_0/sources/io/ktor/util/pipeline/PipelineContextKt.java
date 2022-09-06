package io.ktor.util.pipeline;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.deecomms.common.Constants;
import io.ktor.util.KtorExperimentalAPI;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: PipelineContext.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u001a{\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u00032\u0006\u0010\u0005\u001a\u0002H\u00042?\u0010\u0006\u001a;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\t\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b¢\u0006\u0002\b\f0\u00072\u0006\u0010\r\u001a\u0002H\u0002H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001ah\u0010\u000f\u001a\u0004\u0018\u00010\u0003\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0011*)\b\u0001\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b¢\u0006\u0002\b\f2\u0006\u0010\u0012\u001a\u0002H\u00102\u0006\u0010\u0013\u001a\u0002H\u00112\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0082\bø\u0001\u0000¢\u0006\u0002\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"pipelineExecutorFor", "Lio/ktor/util/pipeline/PipelineExecutor;", "TSubject", "", "TContext", "context", "interceptors", "", "Lkotlin/Function3;", "Lio/ktor/util/pipeline/PipelineContext;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "subject", "(Ljava/lang/Object;Ljava/util/List;Ljava/lang/Object;)Lio/ktor/util/pipeline/PipelineExecutor;", "startCoroutineUninterceptedOrReturn3", "R", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, Constants.BUNDLE_RECEIVER_TAG, "arg", "continuation", "(Lkotlin/jvm/functions/Function3;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class PipelineContextKt {
    @KtorExperimentalAPI
    @NotNull
    public static final <TSubject, TContext> PipelineExecutor<TSubject> pipelineExecutorFor(@NotNull TContext context, @NotNull List<? extends Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object>> interceptors, @NotNull TSubject subject) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(interceptors, "interceptors");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        return new SuspendFunctionGun(subject, context, interceptors);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <R, A> Object startCoroutineUninterceptedOrReturn3(@NotNull Function3<? super R, ? super A, ? super Continuation<? super Unit>, ? extends Object> function3, R r, A a, Continuation<? super Unit> continuation) {
        if (function3 != null) {
            return ((Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function3, 3)).invoke(r, a, continuation);
        }
        throw new TypeCastException("null cannot be cast to non-null type (R, A, kotlin.coroutines.Continuation<kotlin.Unit>) -> kotlin.Any?");
    }
}
