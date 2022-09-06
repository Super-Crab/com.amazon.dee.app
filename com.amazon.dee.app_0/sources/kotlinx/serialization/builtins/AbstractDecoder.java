package kotlinx.serialization.builtins;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.CompositeDecoder;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.UpdateMode;
import kotlinx.serialization.modules.EmptyModule;
import kotlinx.serialization.modules.SerialModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AbstractDecoder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J1\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\u001a\u0010\u000f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010\"\u0006\u0012\u0002\b\u00030\u0011H\u0016¢\u0006\u0002\u0010\u0012J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0015\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0016\u0010\u001a\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0016\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0016\u0010 \u001a\u00020\u001f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u000eH\u0016J\b\u0010#\u001a\u00020$H\u0016J\u0016\u0010%\u001a\u00020$2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010&\u001a\u00020\u0017H\u0016J\u0016\u0010'\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010(\u001a\u00020)H\u0016J\u0016\u0010*\u001a\u00020)2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010+\u001a\u00020\u0014H\u0016J\n\u0010,\u001a\u0004\u0018\u00010-H\u0016J7\u0010.\u001a\u0004\u0018\u0001H/\"\b\b\u0000\u0010/*\u0002002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\u000e\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H/02¢\u0006\u0002\u00103J/\u00104\u001a\u0002H/\"\u0004\b\u0000\u0010/2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\f\u00101\u001a\b\u0012\u0004\u0012\u0002H/02¢\u0006\u0002\u00103J\b\u00105\u001a\u000206H\u0016J\u0016\u00107\u001a\u0002062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017J\b\u00108\u001a\u000209H\u0016J\u0016\u0010:\u001a\u0002092\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010;\u001a\u00020<H\u0016J\u0016\u0010=\u001a\u00020<2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010>\u001a\u000200H\u0016J\u0010\u0010?\u001a\u00020<2\u0006\u0010\r\u001a\u00020\u000eH\u0016JA\u0010@\u001a\u0004\u0018\u0001H/\"\b\b\u0000\u0010/*\u0002002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\u000e\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H/022\b\u0010A\u001a\u0004\u0018\u0001H/¢\u0006\u0002\u0010BJ7\u0010C\u001a\u0002H/\"\u0004\b\u0000\u0010/2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\f\u00101\u001a\b\u0012\u0004\u0012\u0002H/022\u0006\u0010A\u001a\u0002H/¢\u0006\u0002\u0010BR\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006D"}, d2 = {"Lkotlinx/serialization/builtins/AbstractDecoder;", "Lkotlinx/serialization/Decoder;", "Lkotlinx/serialization/CompositeDecoder;", "()V", "context", "Lkotlinx/serialization/modules/SerialModule;", "getContext", "()Lkotlinx/serialization/modules/SerialModule;", "updateMode", "Lkotlinx/serialization/UpdateMode;", "getUpdateMode", "()Lkotlinx/serialization/UpdateMode;", "beginStructure", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "typeParams", "", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/SerialDescriptor;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/CompositeDecoder;", "decodeBoolean", "", "decodeBooleanElement", "index", "", "decodeByte", "", "decodeByteElement", "decodeChar", "", "decodeCharElement", "decodeDouble", "", "decodeDoubleElement", "decodeEnum", "enumDescriptor", "decodeFloat", "", "decodeFloatElement", "decodeInt", "decodeIntElement", "decodeLong", "", "decodeLongElement", "decodeNotNullMark", "decodeNull", "", "decodeNullableSerializableElement", ExifInterface.GPS_DIRECTION_TRUE, "", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/SerialDescriptor;ILkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "decodeSerializableElement", "decodeShort", "", "decodeShortElement", "decodeString", "", "decodeStringElement", "decodeUnit", "", "decodeUnitElement", "decodeValue", "endStructure", "updateNullableSerializableElement", "old", "(Lkotlinx/serialization/SerialDescriptor;ILkotlinx/serialization/DeserializationStrategy;Ljava/lang/Object;)Ljava/lang/Object;", "updateSerializableElement", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class AbstractDecoder implements Decoder, CompositeDecoder {
    @NotNull
    private final UpdateMode updateMode = UpdateMode.UPDATE;

    @Override // kotlinx.serialization.Decoder
    @NotNull
    public CompositeDecoder beginStructure(@NotNull SerialDescriptor descriptor, @NotNull KSerializer<?>... typeParams) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(typeParams, "typeParams");
        return this;
    }

    @Override // kotlinx.serialization.Decoder
    public boolean decodeBoolean() {
        Object decodeValue = decodeValue();
        if (decodeValue != null) {
            return ((Boolean) decodeValue).booleanValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public final boolean decodeBooleanElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return decodeBoolean();
    }

    @Override // kotlinx.serialization.Decoder
    public byte decodeByte() {
        Object decodeValue = decodeValue();
        if (decodeValue != null) {
            return ((Byte) decodeValue).byteValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Byte");
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public final byte decodeByteElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return decodeByte();
    }

    @Override // kotlinx.serialization.Decoder
    public char decodeChar() {
        Object decodeValue = decodeValue();
        if (decodeValue != null) {
            return ((Character) decodeValue).charValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Char");
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public final char decodeCharElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return decodeChar();
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public int decodeCollectionSize(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return CompositeDecoder.DefaultImpls.decodeCollectionSize(this, descriptor);
    }

    @Override // kotlinx.serialization.Decoder
    public double decodeDouble() {
        Object decodeValue = decodeValue();
        if (decodeValue != null) {
            return ((Double) decodeValue).doubleValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Double");
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public final double decodeDoubleElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return decodeDouble();
    }

    @Override // kotlinx.serialization.Decoder
    public int decodeEnum(@NotNull SerialDescriptor enumDescriptor) {
        Intrinsics.checkParameterIsNotNull(enumDescriptor, "enumDescriptor");
        Object decodeValue = decodeValue();
        if (decodeValue != null) {
            return ((Integer) decodeValue).intValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
    }

    @Override // kotlinx.serialization.Decoder
    public float decodeFloat() {
        Object decodeValue = decodeValue();
        if (decodeValue != null) {
            return ((Float) decodeValue).floatValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public final float decodeFloatElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return decodeFloat();
    }

    @Override // kotlinx.serialization.Decoder
    public int decodeInt() {
        Object decodeValue = decodeValue();
        if (decodeValue != null) {
            return ((Integer) decodeValue).intValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public final int decodeIntElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return decodeInt();
    }

    @Override // kotlinx.serialization.Decoder
    public long decodeLong() {
        Object decodeValue = decodeValue();
        if (decodeValue != null) {
            return ((Long) decodeValue).longValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Long");
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public final long decodeLongElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return decodeLong();
    }

    @Override // kotlinx.serialization.Decoder
    public boolean decodeNotNullMark() {
        return true;
    }

    @Override // kotlinx.serialization.Decoder
    @Nullable
    public Void decodeNull() {
        return null;
    }

    @Override // kotlinx.serialization.CompositeDecoder
    @Nullable
    public final <T> T decodeNullableSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull DeserializationStrategy<T> deserializer) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        return (T) decodeNullableSerializableValue(deserializer);
    }

    @Override // kotlinx.serialization.Decoder
    @Nullable
    public <T> T decodeNullableSerializableValue(@NotNull DeserializationStrategy<T> deserializer) {
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        return (T) Decoder.DefaultImpls.decodeNullableSerializableValue(this, deserializer);
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public boolean decodeSequentially() {
        return CompositeDecoder.DefaultImpls.decodeSequentially(this);
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public final <T> T decodeSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull DeserializationStrategy<T> deserializer) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        return (T) decodeSerializableValue(deserializer);
    }

    @Override // kotlinx.serialization.Decoder
    public <T> T decodeSerializableValue(@NotNull DeserializationStrategy<T> deserializer) {
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        return (T) Decoder.DefaultImpls.decodeSerializableValue(this, deserializer);
    }

    @Override // kotlinx.serialization.Decoder
    public short decodeShort() {
        Object decodeValue = decodeValue();
        if (decodeValue != null) {
            return ((Short) decodeValue).shortValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Short");
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public final short decodeShortElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return decodeShort();
    }

    @Override // kotlinx.serialization.Decoder
    @NotNull
    public String decodeString() {
        Object decodeValue = decodeValue();
        if (decodeValue != null) {
            return (String) decodeValue;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
    }

    @Override // kotlinx.serialization.CompositeDecoder
    @NotNull
    public final String decodeStringElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return decodeString();
    }

    @Override // kotlinx.serialization.Decoder
    public void decodeUnit() {
        PrimitiveSerializersKt.UnitSerializer().mo12413deserialize(this);
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public final void decodeUnitElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        decodeUnit();
    }

    @NotNull
    public Object decodeValue() {
        throw new SerializationException(Reflection.getOrCreateKotlinClass(getClass()) + " can't retrieve untyped values", null, 2, null);
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public void endStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
    }

    @Override // kotlinx.serialization.Decoder, kotlinx.serialization.CompositeDecoder
    @NotNull
    public SerialModule getContext() {
        return EmptyModule.INSTANCE;
    }

    @Override // kotlinx.serialization.Decoder, kotlinx.serialization.CompositeDecoder
    @NotNull
    public UpdateMode getUpdateMode() {
        return this.updateMode;
    }

    @Override // kotlinx.serialization.CompositeDecoder
    @Nullable
    public final <T> T updateNullableSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull DeserializationStrategy<T> deserializer, @Nullable T t) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        return (T) updateNullableSerializableValue(deserializer, t);
    }

    @Override // kotlinx.serialization.Decoder
    @Nullable
    public <T> T updateNullableSerializableValue(@NotNull DeserializationStrategy<T> deserializer, @Nullable T t) {
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        return (T) Decoder.DefaultImpls.updateNullableSerializableValue(this, deserializer, t);
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public final <T> T updateSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull DeserializationStrategy<T> deserializer, T t) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        return (T) updateSerializableValue(deserializer, t);
    }

    @Override // kotlinx.serialization.Decoder
    public <T> T updateSerializableValue(@NotNull DeserializationStrategy<T> deserializer, T t) {
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        return (T) Decoder.DefaultImpls.updateSerializableValue(this, deserializer, t);
    }
}
