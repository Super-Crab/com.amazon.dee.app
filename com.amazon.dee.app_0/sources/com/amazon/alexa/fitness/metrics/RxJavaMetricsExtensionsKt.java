package com.amazon.alexa.fitness.metrics;

import androidx.exifinterface.media.ExifInterface;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: RxJavaMetricsExtensions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\u001a*\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00050\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001\u001a$\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006*\u00020\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005¨\u0006\t"}, d2 = {"instrumentAsync", "Lio/reactivex/rxjava3/core/Completable;", "Lcom/amazon/alexa/fitness/metrics/OperationWithMetricEvent;", "codeBlock", "Lkotlin/Function0;", "Lio/reactivex/rxjava3/core/Single;", ExifInterface.GPS_DIRECTION_TRUE, "completable", "single", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class RxJavaMetricsExtensionsKt {
    @NotNull
    public static final <T> Single<T> instrumentAsync(@NotNull OperationWithMetricEvent instrumentAsync, @NotNull Single<T> single) {
        Intrinsics.checkParameterIsNotNull(instrumentAsync, "$this$instrumentAsync");
        Intrinsics.checkParameterIsNotNull(single, "single");
        return m1409instrumentAsync(instrumentAsync, (Function0) TypeIntrinsics.beforeCheckcastToFunctionOfArity(new RxJavaMetricsExtensionsKt$instrumentAsync$1(single), 0));
    }

    @NotNull
    /* renamed from: instrumentAsync  reason: collision with other method in class */
    public static final <T> Single<T> m1409instrumentAsync(@NotNull final OperationWithMetricEvent instrumentAsync, @NotNull Function0<? extends Single<T>> codeBlock) {
        Intrinsics.checkParameterIsNotNull(instrumentAsync, "$this$instrumentAsync");
        Intrinsics.checkParameterIsNotNull(codeBlock, "codeBlock");
        OperationKt.decorateOnOperationStart(instrumentAsync.getMetricEvent$AlexaMobileAndroidFitnessExtension_release(), instrumentAsync.getOperation$AlexaMobileAndroidFitnessExtension_release());
        Single<T> doOnError = codeBlock.mo12560invoke().doOnSuccess(new Consumer<T>() { // from class: com.amazon.alexa.fitness.metrics.RxJavaMetricsExtensionsKt$instrumentAsync$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(T t) {
                OperationKt.decorateOnOperationSuccess(OperationWithMetricEvent.this.getMetricEvent$AlexaMobileAndroidFitnessExtension_release(), OperationWithMetricEvent.this.getOperation$AlexaMobileAndroidFitnessExtension_release());
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.amazon.alexa.fitness.metrics.RxJavaMetricsExtensionsKt$instrumentAsync$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable th) {
                OperationKt.decorateOnOperationError$default(OperationWithMetricEvent.this.getMetricEvent$AlexaMobileAndroidFitnessExtension_release(), OperationWithMetricEvent.this.getOperation$AlexaMobileAndroidFitnessExtension_release(), th, (String) null, 4, (Object) null);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(doOnError, "codeBlock()\n            …(operation, throwable) })");
        return doOnError;
    }

    @NotNull
    public static final Completable instrumentAsync(@NotNull OperationWithMetricEvent instrumentAsync, @NotNull Completable completable) {
        Intrinsics.checkParameterIsNotNull(instrumentAsync, "$this$instrumentAsync");
        Intrinsics.checkParameterIsNotNull(completable, "completable");
        return instrumentAsync(instrumentAsync, (Function0<? extends Completable>) new RxJavaMetricsExtensionsKt$instrumentAsync$4(completable));
    }

    @NotNull
    public static final Completable instrumentAsync(@NotNull final OperationWithMetricEvent instrumentAsync, @NotNull Function0<? extends Completable> codeBlock) {
        Intrinsics.checkParameterIsNotNull(instrumentAsync, "$this$instrumentAsync");
        Intrinsics.checkParameterIsNotNull(codeBlock, "codeBlock");
        OperationKt.decorateOnOperationStart(instrumentAsync.getMetricEvent$AlexaMobileAndroidFitnessExtension_release(), instrumentAsync.getOperation$AlexaMobileAndroidFitnessExtension_release());
        Completable doOnError = codeBlock.mo12560invoke().doOnComplete(new Action() { // from class: com.amazon.alexa.fitness.metrics.RxJavaMetricsExtensionsKt$instrumentAsync$5
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                OperationKt.decorateOnOperationSuccess(OperationWithMetricEvent.this.getMetricEvent$AlexaMobileAndroidFitnessExtension_release(), OperationWithMetricEvent.this.getOperation$AlexaMobileAndroidFitnessExtension_release());
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.amazon.alexa.fitness.metrics.RxJavaMetricsExtensionsKt$instrumentAsync$6
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable th) {
                OperationKt.decorateOnOperationError$default(OperationWithMetricEvent.this.getMetricEvent$AlexaMobileAndroidFitnessExtension_release(), OperationWithMetricEvent.this.getOperation$AlexaMobileAndroidFitnessExtension_release(), th, (String) null, 4, (Object) null);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(doOnError, "codeBlock()\n            …(operation, throwable) })");
        return doOnError;
    }
}
