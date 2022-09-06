package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerializerResolving.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001H\u0087\b\u001a\u0016\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00012\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"serializer", "Lkotlinx/serialization/KSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "type", "Lkotlin/reflect/KType;", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerializerResolvingKt {
    @ImplicitReflectionSerializer
    @NotNull
    public static final /* synthetic */ <T> KSerializer<T> serializer() {
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        KSerializer<T> kSerializer = (KSerializer<T>) serializer(null);
        if (kSerializer != null) {
            return kSerializer;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
    }

    @NotNull
    public static final KSerializer<Object> serializer(@NotNull KType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        KSerializer<Object> mo12165invoke = SerializerResolvingKt$serializer$1.INSTANCE.mo12165invoke(type);
        if (type.isMarkedNullable()) {
            return BuiltinSerializersKt.getNullable(mo12165invoke);
        }
        if (mo12165invoke == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
        }
        return mo12165invoke;
    }
}
