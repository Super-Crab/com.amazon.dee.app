package com.amazon.alexa.fitness.metrics;

import androidx.exifinterface.media.ExifInterface;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: RxJavaMetricsExtensions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lio/reactivex/rxjava3/core/Single;", ExifInterface.GPS_DIRECTION_TRUE, "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class RxJavaMetricsExtensionsKt$instrumentAsync$1 extends Lambda implements Function0<Single<T>> {
    final /* synthetic */ Single $single;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RxJavaMetricsExtensionsKt$instrumentAsync$1(Single single) {
        super(0);
        this.$single = single;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke */
    public final Single<T> mo12560invoke() {
        return this.$single;
    }
}
