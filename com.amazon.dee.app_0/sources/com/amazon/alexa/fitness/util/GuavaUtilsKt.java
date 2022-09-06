package com.amazon.alexa.fitness.util;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.base.Optional;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: GuavaUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a<\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0005¨\u0006\u0006"}, d2 = {"flatMap", "Lcom/google/common/base/Optional;", "U", ExifInterface.GPS_DIRECTION_TRUE, "f", "Lkotlin/Function1;", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class GuavaUtilsKt {
    @NotNull
    public static final <T, U> Optional<U> flatMap(@NotNull Optional<T> flatMap, @NotNull Function1<? super T, ? extends Optional<U>> f) {
        Intrinsics.checkParameterIsNotNull(flatMap, "$this$flatMap");
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (flatMap.isPresent()) {
            return f.mo12165invoke(flatMap.get());
        }
        Optional<U> absent = Optional.absent();
        Intrinsics.checkExpressionValueIsNotNull(absent, "Optional.absent()");
        return absent;
    }
}
