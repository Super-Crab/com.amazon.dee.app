package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Encoding.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0006\u001a+\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b2\u0006\u0010\t\u001a\u0002H\u0002¢\u0006\u0002\u0010\n\u001a0\u0010\u000b\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0019\b\u0004\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0011H\u0086\b¨\u0006\u0012"}, d2 = {"encode", "", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/serialization/Encoder;", "obj", "(Lkotlinx/serialization/Encoder;Ljava/lang/Object;)V", "strategy", "Lkotlinx/serialization/SerializationStrategy;", "value", "(Lkotlinx/serialization/Encoder;Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "encodeStructure", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "block", "Lkotlin/Function1;", "Lkotlinx/serialization/CompositeEncoder;", "Lkotlin/ExtensionFunctionType;", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class EncodingKt {
    public static final <T> void encode(@NotNull Encoder encode, @NotNull SerializationStrategy<? super T> strategy, T t) {
        Intrinsics.checkParameterIsNotNull(encode, "$this$encode");
        Intrinsics.checkParameterIsNotNull(strategy, "strategy");
        encode.encodeSerializableValue(strategy, t);
    }

    public static final void encodeStructure(@NotNull Encoder encodeStructure, @NotNull SerialDescriptor descriptor, @NotNull Function1<? super CompositeEncoder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(encodeStructure, "$this$encodeStructure");
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(block, "block");
        CompositeEncoder beginStructure = encodeStructure.beginStructure(descriptor, new KSerializer[0]);
        block.mo12165invoke(beginStructure);
        beginStructure.endStructure(descriptor);
    }

    @ImplicitReflectionSerializer
    public static final /* synthetic */ <T> void encode(@NotNull Encoder encode, @NotNull T obj) {
        Intrinsics.checkParameterIsNotNull(encode, "$this$encode");
        Intrinsics.checkParameterIsNotNull(obj, "obj");
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        KSerializer<Object> serializer = SerializerResolvingKt.serializer(null);
        if (serializer != null) {
            encode(encode, serializer, obj);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
    }
}
