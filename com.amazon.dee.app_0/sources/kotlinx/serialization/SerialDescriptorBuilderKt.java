package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.serialization.StructureKind;
import kotlinx.serialization.internal.ArrayListClassDesc;
import kotlinx.serialization.internal.HashMapClassDesc;
import kotlinx.serialization.internal.HashSetClassDesc;
import kotlinx.serialization.internal.PrimitivesKt;
import kotlinx.serialization.internal.SerialDescriptorForNullable;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialDescriptorBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\u0016\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a3\u0010\t\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\n2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f\u001a\u0015\u0010\u0010\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0011\u0018\u0001*\u00020\rH\u0087\b\u001a\u0015\u0010\u0012\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0011\u0018\u0001*\u00020\rH\u0087\b\u001a\u0012\u0010\u0012\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0001\u001a\u001d\u0010\u0014\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0015\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001*\u00020\rH\u0087\b\u001a\u001a\u0010\u0014\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0001\u001a\u0015\u0010\u0019\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0011\u0018\u0001*\u00020\rH\u0087\b\u001a\u0012\u0010\u0019\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0001\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u001a"}, d2 = {"nullable", "Lkotlinx/serialization/SerialDescriptor;", "getNullable", "(Lkotlinx/serialization/SerialDescriptor;)Lkotlinx/serialization/SerialDescriptor;", "PrimitiveDescriptor", "serialName", "", ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, "Lkotlinx/serialization/PrimitiveKind;", "SerialDescriptor", "Lkotlinx/serialization/SerialKind;", "builder", "Lkotlin/Function1;", "Lkotlinx/serialization/SerialDescriptorBuilder;", "", "Lkotlin/ExtensionFunctionType;", "descriptor", ExifInterface.GPS_DIRECTION_TRUE, "listDescriptor", "typeDescriptor", "mapDescriptor", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "keyDescriptor", "valueDescriptor", "setDescriptor", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerialDescriptorBuilderKt {
    @NotNull
    public static final SerialDescriptor PrimitiveDescriptor(@NotNull String serialName, @NotNull PrimitiveKind kind) {
        boolean isBlank;
        Intrinsics.checkParameterIsNotNull(serialName, "serialName");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        isBlank = StringsKt__StringsJVMKt.isBlank(serialName);
        if (!isBlank) {
            return PrimitivesKt.PrimitiveDescriptorSafe(serialName, kind);
        }
        throw new IllegalArgumentException("Blank serial names are prohibited".toString());
    }

    @NotNull
    public static final SerialDescriptor SerialDescriptor(@NotNull String serialName, @NotNull SerialKind kind, @NotNull Function1<? super SerialDescriptorBuilder, Unit> builder) {
        boolean isBlank;
        Intrinsics.checkParameterIsNotNull(serialName, "serialName");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        isBlank = StringsKt__StringsJVMKt.isBlank(serialName);
        if (!isBlank) {
            SerialDescriptorBuilder serialDescriptorBuilder = new SerialDescriptorBuilder(serialName);
            builder.mo12165invoke(serialDescriptorBuilder);
            return new SerialDescriptorImpl(serialName, kind, serialDescriptorBuilder.getElementNames$kotlinx_serialization_runtime().size(), serialDescriptorBuilder);
        }
        throw new IllegalArgumentException("Blank serial names are prohibited".toString());
    }

    public static /* synthetic */ SerialDescriptor SerialDescriptor$default(String str, SerialKind serialKind, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            serialKind = StructureKind.CLASS.INSTANCE;
        }
        if ((i & 4) != 0) {
            function1 = SerialDescriptorBuilderKt$SerialDescriptor$1.INSTANCE;
        }
        return SerialDescriptor(str, serialKind, function1);
    }

    @ImplicitReflectionSerializer
    @NotNull
    public static final /* synthetic */ <T> SerialDescriptor descriptor(@NotNull SerialDescriptorBuilder descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "$this$descriptor");
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        KSerializer<Object> serializer = SerializerResolvingKt.serializer(null);
        if (serializer != null) {
            return serializer.getDescriptor();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
    }

    @NotNull
    public static final SerialDescriptor getNullable(@NotNull SerialDescriptor nullable) {
        Intrinsics.checkParameterIsNotNull(nullable, "$this$nullable");
        return nullable.isNullable() ? nullable : new SerialDescriptorForNullable(nullable);
    }

    @NotNull
    public static final SerialDescriptor listDescriptor(@NotNull SerialDescriptorBuilder listDescriptor, @NotNull SerialDescriptor typeDescriptor) {
        Intrinsics.checkParameterIsNotNull(listDescriptor, "$this$listDescriptor");
        Intrinsics.checkParameterIsNotNull(typeDescriptor, "typeDescriptor");
        return new ArrayListClassDesc(typeDescriptor);
    }

    @NotNull
    public static final SerialDescriptor mapDescriptor(@NotNull SerialDescriptorBuilder mapDescriptor, @NotNull SerialDescriptor keyDescriptor, @NotNull SerialDescriptor valueDescriptor) {
        Intrinsics.checkParameterIsNotNull(mapDescriptor, "$this$mapDescriptor");
        Intrinsics.checkParameterIsNotNull(keyDescriptor, "keyDescriptor");
        Intrinsics.checkParameterIsNotNull(valueDescriptor, "valueDescriptor");
        return new HashMapClassDesc(keyDescriptor, valueDescriptor);
    }

    @NotNull
    public static final SerialDescriptor setDescriptor(@NotNull SerialDescriptorBuilder setDescriptor, @NotNull SerialDescriptor typeDescriptor) {
        Intrinsics.checkParameterIsNotNull(setDescriptor, "$this$setDescriptor");
        Intrinsics.checkParameterIsNotNull(typeDescriptor, "typeDescriptor");
        return new HashSetClassDesc(typeDescriptor);
    }

    @ImplicitReflectionSerializer
    @NotNull
    public static final /* synthetic */ <T> SerialDescriptor listDescriptor(@NotNull SerialDescriptorBuilder listDescriptor) {
        Intrinsics.checkParameterIsNotNull(listDescriptor, "$this$listDescriptor");
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        KSerializer<Object> serializer = SerializerResolvingKt.serializer(null);
        if (serializer != null) {
            return listDescriptor(listDescriptor, serializer.getDescriptor());
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
    }

    @ImplicitReflectionSerializer
    @NotNull
    public static final /* synthetic */ <K, V> SerialDescriptor mapDescriptor(@NotNull SerialDescriptorBuilder mapDescriptor) {
        Intrinsics.checkParameterIsNotNull(mapDescriptor, "$this$mapDescriptor");
        Intrinsics.reifiedOperationMarker(6, "K");
        KSerializer<Object> serializer = SerializerResolvingKt.serializer(null);
        if (serializer != null) {
            SerialDescriptor descriptor = serializer.getDescriptor();
            Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
            KSerializer<Object> serializer2 = SerializerResolvingKt.serializer(null);
            if (serializer2 != null) {
                return mapDescriptor(mapDescriptor, descriptor, serializer2.getDescriptor());
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
    }

    @ImplicitReflectionSerializer
    @NotNull
    public static final /* synthetic */ <T> SerialDescriptor setDescriptor(@NotNull SerialDescriptorBuilder setDescriptor) {
        Intrinsics.checkParameterIsNotNull(setDescriptor, "$this$setDescriptor");
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        KSerializer<Object> serializer = SerializerResolvingKt.serializer(null);
        if (serializer != null) {
            return setDescriptor(setDescriptor, serializer.getDescriptor());
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
    }
}
