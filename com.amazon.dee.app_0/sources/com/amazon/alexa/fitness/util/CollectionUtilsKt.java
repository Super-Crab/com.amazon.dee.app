package com.amazon.alexa.fitness.util;

import androidx.exifinterface.media.ExifInterface;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CollectionUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a7\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u000e\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u0004*\u0002H\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"ifEmpty", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.LONGITUDE_EAST, "", "", "func", "Lkotlin/Function0;", "", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;)Ljava/util/Collection;", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class CollectionUtilsKt {
    @NotNull
    public static final <E, T extends Collection<? extends E>> T ifEmpty(@NotNull T ifEmpty, @NotNull Function0<Unit> func) {
        Intrinsics.checkParameterIsNotNull(ifEmpty, "$this$ifEmpty");
        Intrinsics.checkParameterIsNotNull(func, "func");
        if (ifEmpty.isEmpty()) {
            func.mo12560invoke();
        }
        return ifEmpty;
    }
}
