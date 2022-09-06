package com.amazon.alexa.fitness.metrics;

import io.reactivex.rxjava3.core.Completable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: RxJavaMetricsExtensions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/reactivex/rxjava3/core/Completable;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class RxJavaMetricsExtensionsKt$instrumentAsync$4 extends Lambda implements Function0<Completable> {
    final /* synthetic */ Completable $completable;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RxJavaMetricsExtensionsKt$instrumentAsync$4(Completable completable) {
        super(0);
        this.$completable = completable;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke */
    public final Completable mo12560invoke() {
        return this.$completable;
    }
}
