package com.amazon.alexa.fitness.utils;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* compiled from: FullScreenUpdaterUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\b\u0010\u0002\u001a\u0004\u0018\u0001H\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"getValue", ExifInterface.GPS_DIRECTION_TRUE, "aggregatedValue", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class FullScreenUpdaterUtil$Companion$initFitnessData$1 extends Lambda implements Function1<T, T> {
    final /* synthetic */ boolean $showLastSummary;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullScreenUpdaterUtil$Companion$initFitnessData$1(boolean z) {
        super(1);
        this.$showLastSummary = z;
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke */
    public final <T> T mo12165invoke(@Nullable T t) {
        if (this.$showLastSummary) {
            return t;
        }
        return null;
    }
}
