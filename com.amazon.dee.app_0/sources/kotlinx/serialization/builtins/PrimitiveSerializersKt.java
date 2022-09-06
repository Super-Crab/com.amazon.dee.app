package kotlinx.serialization.builtins;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
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
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.internal.BooleanArraySerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.ByteSerializer;
import kotlinx.serialization.internal.CharArraySerializer;
import kotlinx.serialization.internal.CharSerializer;
import kotlinx.serialization.internal.DoubleArraySerializer;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.FloatArraySerializer;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.IntArraySerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongArraySerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.ShortArraySerializer;
import kotlinx.serialization.internal.ShortSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.internal.UnitSerializer;
import org.jetbrains.annotations.NotNull;
/* compiled from: PrimitiveSerializers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u009e\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001aD\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0004*\u00020\u0005\"\n\b\u0001\u0010\u0003*\u0004\u0018\u0001H\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001\u001a=\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005\"\f\b\u0001\u0010\u0003\u0018\u0001*\u0004\u0018\u0001H\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0086\b\u001a\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001\u001a\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001\u001a\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001\u001a\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0001\u001a\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0001\u001a\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0001\u001a\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0001\u001a\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0001\u001a\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0001\u001a\u0015\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0001*\u00020\u001d¢\u0006\u0002\u0010\u001e\u001a\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0001*\u00020 \u001a\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020!0\u0001*\u00020\"\u001a\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020#0\u0001*\u00020$\u001a\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020%0\u0001*\u00020&\u001a\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020'0\u0001*\u00020(\u001a\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020)0\u0001*\u00020*\u001a\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020+0\u0001*\u00020,\u001a\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020-0\u0001*\u00020.¨\u0006/"}, d2 = {"ArraySerializer", "Lkotlinx/serialization/KSerializer;", "", ExifInterface.LONGITUDE_EAST, ExifInterface.GPS_DIRECTION_TRUE, "", "kClass", "Lkotlin/reflect/KClass;", "elementSerializer", "BooleanArraySerializer", "", "ByteArraySerializer", "", "CharArraySerializer", "", "DoubleArraySerializer", "", "FloatArraySerializer", "", "IntArraySerializer", "", "LongArraySerializer", "", "ShortArraySerializer", "", "UnitSerializer", "", "serializer", "", "Lkotlin/Boolean$Companion;", "(Lkotlin/jvm/internal/BooleanCompanionObject;)Lkotlinx/serialization/KSerializer;", "", "Lkotlin/Byte$Companion;", "", "Lkotlin/Char$Companion;", "", "Lkotlin/Double$Companion;", "", "Lkotlin/Float$Companion;", "", "Lkotlin/Int$Companion;", "", "Lkotlin/Long$Companion;", "", "Lkotlin/Short$Companion;", "", "Lkotlin/String$Companion;", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PrimitiveSerializersKt {
    @NotNull
    public static final /* synthetic */ <T, E extends T> KSerializer<E[]> ArraySerializer(@NotNull KSerializer<E> elementSerializer) {
        Intrinsics.checkParameterIsNotNull(elementSerializer, "elementSerializer");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return ArraySerializer(Reflection.getOrCreateKotlinClass(Object.class), elementSerializer);
    }

    @NotNull
    public static final KSerializer<boolean[]> BooleanArraySerializer() {
        return BooleanArraySerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<byte[]> ByteArraySerializer() {
        return ByteArraySerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<char[]> CharArraySerializer() {
        return CharArraySerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<double[]> DoubleArraySerializer() {
        return DoubleArraySerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<float[]> FloatArraySerializer() {
        return FloatArraySerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<int[]> IntArraySerializer() {
        return IntArraySerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<long[]> LongArraySerializer() {
        return LongArraySerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<short[]> ShortArraySerializer() {
        return ShortArraySerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Unit> UnitSerializer() {
        return UnitSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Character> serializer(@NotNull CharCompanionObject serializer) {
        Intrinsics.checkParameterIsNotNull(serializer, "$this$serializer");
        return CharSerializer.INSTANCE;
    }

    @NotNull
    public static final <T, E extends T> KSerializer<E[]> ArraySerializer(@NotNull KClass<T> kClass, @NotNull KSerializer<E> elementSerializer) {
        Intrinsics.checkParameterIsNotNull(kClass, "kClass");
        Intrinsics.checkParameterIsNotNull(elementSerializer, "elementSerializer");
        return new ReferenceArraySerializer(kClass, elementSerializer);
    }

    @NotNull
    public static final KSerializer<Byte> serializer(@NotNull ByteCompanionObject serializer) {
        Intrinsics.checkParameterIsNotNull(serializer, "$this$serializer");
        return ByteSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Short> serializer(@NotNull ShortCompanionObject serializer) {
        Intrinsics.checkParameterIsNotNull(serializer, "$this$serializer");
        return ShortSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Integer> serializer(@NotNull IntCompanionObject serializer) {
        Intrinsics.checkParameterIsNotNull(serializer, "$this$serializer");
        return IntSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Long> serializer(@NotNull LongCompanionObject serializer) {
        Intrinsics.checkParameterIsNotNull(serializer, "$this$serializer");
        return LongSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Float> serializer(@NotNull FloatCompanionObject serializer) {
        Intrinsics.checkParameterIsNotNull(serializer, "$this$serializer");
        return FloatSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Double> serializer(@NotNull DoubleCompanionObject serializer) {
        Intrinsics.checkParameterIsNotNull(serializer, "$this$serializer");
        return DoubleSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Boolean> serializer(@NotNull BooleanCompanionObject serializer) {
        Intrinsics.checkParameterIsNotNull(serializer, "$this$serializer");
        return BooleanSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<String> serializer(@NotNull StringCompanionObject serializer) {
        Intrinsics.checkParameterIsNotNull(serializer, "$this$serializer");
        return StringSerializer.INSTANCE;
    }
}
