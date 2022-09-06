package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.internal.PrimitivesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: PlatformUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0007\u001a$\u0010\u0005\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0007¨\u0006\u0006"}, d2 = {"serializer", "Lkotlinx/serialization/KSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlin/reflect/KClass;", "serializerOrNull", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PlatformUtilsKt {
    @ImplicitReflectionSerializer
    @NotNull
    public static final <T> KSerializer<T> serializer(@NotNull KClass<T> serializer) {
        Intrinsics.checkParameterIsNotNull(serializer, "$this$serializer");
        KSerializer<T> serializerOrNull = serializerOrNull(serializer);
        if (serializerOrNull != null) {
            return serializerOrNull;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can't locate argument-less serializer for class ");
        outline107.append(SerializationKt.simpleName(serializer));
        outline107.append(". ");
        outline107.append("For generic classes, such as lists, please provide serializer explicitly.");
        throw new SerializationException(outline107.toString(), null, 2, null);
    }

    @ImplicitReflectionSerializer
    @Nullable
    public static final <T> KSerializer<T> serializerOrNull(@NotNull KClass<T> serializerOrNull) {
        Intrinsics.checkParameterIsNotNull(serializerOrNull, "$this$serializerOrNull");
        KSerializer<T> compiledSerializerImpl = SerializationKt.compiledSerializerImpl(serializerOrNull);
        return compiledSerializerImpl != null ? compiledSerializerImpl : PrimitivesKt.builtinSerializerOrNull(serializerOrNull);
    }
}
