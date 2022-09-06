package com.amazon.alexa.fitness.util;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: EnumUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0010\b\u0000\u0010\u0001\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"asEnumByName", ExifInterface.LONGITUDE_EAST, "", "", "(Ljava/lang/String;)Ljava/lang/Enum;", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class EnumUtilsKt {
    @Nullable
    public static final /* synthetic */ <E extends Enum<E>> E asEnumByName(@NotNull String asEnumByName) {
        Intrinsics.checkParameterIsNotNull(asEnumByName, "$this$asEnumByName");
        Intrinsics.reifiedOperationMarker(5, ExifInterface.LONGITUDE_EAST);
        for (Enum r3 : new Enum[0]) {
            E e = (E) r3;
            if (Intrinsics.areEqual(e.name(), asEnumByName)) {
                return e;
            }
        }
        return null;
    }
}
