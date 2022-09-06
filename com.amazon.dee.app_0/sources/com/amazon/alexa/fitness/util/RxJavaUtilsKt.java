package com.amazon.alexa.fitness.util;

import io.reactivex.rxjava3.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: RxJavaUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"addTo", "Lio/reactivex/rxjava3/disposables/Disposable;", "disposableManager", "Lcom/amazon/alexa/fitness/util/DisposableManager;", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class RxJavaUtilsKt {
    @NotNull
    public static final Disposable addTo(@NotNull Disposable addTo, @NotNull DisposableManager disposableManager) {
        Intrinsics.checkParameterIsNotNull(addTo, "$this$addTo");
        Intrinsics.checkParameterIsNotNull(disposableManager, "disposableManager");
        return disposableManager.add(addTo);
    }
}
