package kotlinx.serialization.modules;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialModuleCollector.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a'\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0086\b¨\u0006\u0007"}, d2 = {"contextual", "", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/serialization/modules/SerialModuleCollector;", "serializer", "Lkotlinx/serialization/KSerializer;", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerialModuleCollectorKt {
    public static final /* synthetic */ <T> void contextual(@NotNull SerialModuleCollector contextual, @NotNull KSerializer<T> serializer) {
        Intrinsics.checkParameterIsNotNull(contextual, "$this$contextual");
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        contextual.contextual(Reflection.getOrCreateKotlinClass(Object.class), serializer);
    }
}
