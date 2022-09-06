package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.BooleanCompanionObject;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.ShortCompanionObject;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt__IndentKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PrimitiveKind;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.builtins.PrimitiveSerializersKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Primitives.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\bH\u0002\u001a$\u0010\u0010\u001a\n\u0012\u0004\u0012\u0002H\u0011\u0018\u00010\u0004\"\b\b\u0000\u0010\u0011*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00110\u0002H\u0000\"2\u0010\u0000\u001a\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u00040\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0006\"\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"BUILTIN_SERIALIZERS", "", "Lkotlin/reflect/KClass;", "", "Lkotlinx/serialization/KSerializer;", "BUILTIN_SERIALIZERS$annotations", "()V", "message", "", "PrimitiveDescriptorSafe", "Lkotlinx/serialization/SerialDescriptor;", "serialName", ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, "Lkotlinx/serialization/PrimitiveKind;", "checkName", "", "builtinSerializerOrNull", ExifInterface.GPS_DIRECTION_TRUE, "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PrimitivesKt {
    private static final Map<KClass<? extends Object>, KSerializer<? extends Object>> BUILTIN_SERIALIZERS;
    private static final String message = "Top level primitive descriptors are unavailable to avoid accidental misuage. Please use kind for comparison and primitive descriptor with a unique name for implementation";

    static {
        Map<KClass<? extends Object>, KSerializer<? extends Object>> mapOf;
        mapOf = MapsKt__MapsKt.mapOf(kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(String.class), PrimitiveSerializersKt.serializer(StringCompanionObject.INSTANCE)), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(Character.TYPE), PrimitiveSerializersKt.serializer(CharCompanionObject.INSTANCE)), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(char[].class), PrimitiveSerializersKt.CharArraySerializer()), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(Double.TYPE), PrimitiveSerializersKt.serializer(DoubleCompanionObject.INSTANCE)), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(double[].class), PrimitiveSerializersKt.DoubleArraySerializer()), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(Float.TYPE), PrimitiveSerializersKt.serializer(FloatCompanionObject.INSTANCE)), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(float[].class), PrimitiveSerializersKt.FloatArraySerializer()), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(Long.TYPE), PrimitiveSerializersKt.serializer(LongCompanionObject.INSTANCE)), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(long[].class), PrimitiveSerializersKt.LongArraySerializer()), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(Integer.TYPE), PrimitiveSerializersKt.serializer(IntCompanionObject.INSTANCE)), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(int[].class), PrimitiveSerializersKt.IntArraySerializer()), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(Short.TYPE), PrimitiveSerializersKt.serializer(ShortCompanionObject.INSTANCE)), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(short[].class), PrimitiveSerializersKt.ShortArraySerializer()), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(Byte.TYPE), PrimitiveSerializersKt.serializer(ByteCompanionObject.INSTANCE)), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(byte[].class), PrimitiveSerializersKt.ByteArraySerializer()), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(Boolean.TYPE), PrimitiveSerializersKt.serializer(BooleanCompanionObject.INSTANCE)), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(boolean[].class), PrimitiveSerializersKt.BooleanArraySerializer()), kotlin.TuplesKt.to(Reflection.getOrCreateKotlinClass(Unit.class), PrimitiveSerializersKt.UnitSerializer()));
        BUILTIN_SERIALIZERS = mapOf;
    }

    private static /* synthetic */ void BUILTIN_SERIALIZERS$annotations() {
    }

    @NotNull
    public static final SerialDescriptor PrimitiveDescriptorSafe(@NotNull String serialName, @NotNull PrimitiveKind kind) {
        Intrinsics.checkParameterIsNotNull(serialName, "serialName");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        checkName(serialName);
        return new PrimitiveSerialDescriptor(serialName, kind);
    }

    @Nullable
    public static final <T> KSerializer<T> builtinSerializerOrNull(@NotNull KClass<T> builtinSerializerOrNull) {
        Intrinsics.checkParameterIsNotNull(builtinSerializerOrNull, "$this$builtinSerializerOrNull");
        return (KSerializer<T>) BUILTIN_SERIALIZERS.get(builtinSerializerOrNull);
    }

    private static final void checkName(String str) {
        String capitalize;
        boolean equals;
        String capitalize2;
        String trimIndent;
        boolean equals2;
        for (KClass<? extends Object> kClass : BUILTIN_SERIALIZERS.keySet()) {
            String simpleName = kClass.getSimpleName();
            if (simpleName == null) {
                Intrinsics.throwNpe();
            }
            capitalize = StringsKt__StringsJVMKt.capitalize(simpleName);
            equals = StringsKt__StringsJVMKt.equals(str, "kotlin." + capitalize, true);
            if (!equals) {
                equals2 = StringsKt__StringsJVMKt.equals(str, capitalize, true);
                if (equals2) {
                }
            }
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("\n                The name of serial descriptor should uniquely identify associated serializer.\n                For serial name ", str, " there already exist ");
            capitalize2 = StringsKt__StringsJVMKt.capitalize(capitalize);
            outline115.append(capitalize2);
            outline115.append("Serializer.\n                Please refer to SerialDescriptor documentation for additional information.\n            ");
            trimIndent = StringsKt__IndentKt.trimIndent(outline115.toString());
            throw new IllegalArgumentException(trimIndent);
        }
    }
}
