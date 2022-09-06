package kotlinx.serialization.builtins;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.modules.EmptyModule;
import kotlinx.serialization.modules.SerialModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AbstractEncoder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J1\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u001a\u0010\u000b\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\r0\f\"\u0006\u0012\u0002\b\u00030\rH\u0016¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0017H\u0016J\u001e\u0010\u0018\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0017J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u001aH\u0016J\u001e\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u001aJ\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u001dH\u0016J\u001e\u0010\u001e\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u001dJ\u0018\u0010\u001f\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\"\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020#H\u0016J\u001e\u0010$\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020#J\u0010\u0010%\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0015H\u0016J\u001e\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0015J\u0010\u0010'\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020(H\u0016J\u001e\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020(J\b\u0010*\u001a\u00020\u0010H\u0016J=\u0010+\u001a\u00020\u0010\"\b\b\u0000\u0010,*\u00020-2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H,0/2\b\u0010\u0011\u001a\u0004\u0018\u0001H,¢\u0006\u0002\u00100J7\u00101\u001a\u00020\u0010\"\u0004\b\u0000\u0010,2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H,0/2\u0006\u0010\u0011\u001a\u0002H,¢\u0006\u0002\u00100J\u0010\u00102\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u000203H\u0016J\u001e\u00104\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u000203J\u0010\u00105\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u000206H\u0016J\u001e\u00107\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u000206J\b\u00108\u001a\u00020\u0010H\u0016J\u0016\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010:\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020-H\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006;"}, d2 = {"Lkotlinx/serialization/builtins/AbstractEncoder;", "Lkotlinx/serialization/Encoder;", "Lkotlinx/serialization/CompositeEncoder;", "()V", "context", "Lkotlinx/serialization/modules/SerialModule;", "getContext", "()Lkotlinx/serialization/modules/SerialModule;", "beginStructure", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "typeSerializers", "", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/SerialDescriptor;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/CompositeEncoder;", "encodeBoolean", "", "value", "", "encodeBooleanElement", "index", "", "encodeByte", "", "encodeByteElement", "encodeChar", "", "encodeCharElement", "encodeDouble", "", "encodeDoubleElement", "encodeElement", "encodeEnum", "enumDescriptor", "encodeFloat", "", "encodeFloatElement", "encodeInt", "encodeIntElement", "encodeLong", "", "encodeLongElement", "encodeNull", "encodeNullableSerializableElement", ExifInterface.GPS_DIRECTION_TRUE, "", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/SerialDescriptor;ILkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "encodeSerializableElement", "encodeShort", "", "encodeShortElement", "encodeString", "", "encodeStringElement", "encodeUnit", "encodeUnitElement", "encodeValue", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class AbstractEncoder implements Encoder, CompositeEncoder {
    @Override // kotlinx.serialization.Encoder
    @NotNull
    public CompositeEncoder beginCollection(@NotNull SerialDescriptor descriptor, int i, @NotNull KSerializer<?>... typeSerializers) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(typeSerializers, "typeSerializers");
        return Encoder.DefaultImpls.beginCollection(this, descriptor, i, typeSerializers);
    }

    @Override // kotlinx.serialization.Encoder
    @NotNull
    public CompositeEncoder beginStructure(@NotNull SerialDescriptor descriptor, @NotNull KSerializer<?>... typeSerializers) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(typeSerializers, "typeSerializers");
        return this;
    }

    @Override // kotlinx.serialization.Encoder
    public void encodeBoolean(boolean z) {
        encodeValue(Boolean.valueOf(z));
    }

    @Override // kotlinx.serialization.CompositeEncoder
    public final void encodeBooleanElement(@NotNull SerialDescriptor descriptor, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (encodeElement(descriptor, i)) {
            encodeBoolean(z);
        }
    }

    @Override // kotlinx.serialization.Encoder
    public void encodeByte(byte b) {
        encodeValue(Byte.valueOf(b));
    }

    @Override // kotlinx.serialization.CompositeEncoder
    public final void encodeByteElement(@NotNull SerialDescriptor descriptor, int i, byte b) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (encodeElement(descriptor, i)) {
            encodeByte(b);
        }
    }

    @Override // kotlinx.serialization.Encoder
    public void encodeChar(char c) {
        encodeValue(Character.valueOf(c));
    }

    @Override // kotlinx.serialization.CompositeEncoder
    public final void encodeCharElement(@NotNull SerialDescriptor descriptor, int i, char c) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (encodeElement(descriptor, i)) {
            encodeChar(c);
        }
    }

    @Override // kotlinx.serialization.Encoder
    public void encodeDouble(double d) {
        encodeValue(Double.valueOf(d));
    }

    @Override // kotlinx.serialization.CompositeEncoder
    public final void encodeDoubleElement(@NotNull SerialDescriptor descriptor, int i, double d) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (encodeElement(descriptor, i)) {
            encodeDouble(d);
        }
    }

    public boolean encodeElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return true;
    }

    @Override // kotlinx.serialization.Encoder
    public void encodeEnum(@NotNull SerialDescriptor enumDescriptor, int i) {
        Intrinsics.checkParameterIsNotNull(enumDescriptor, "enumDescriptor");
        encodeValue(Integer.valueOf(i));
    }

    @Override // kotlinx.serialization.Encoder
    public void encodeFloat(float f) {
        encodeValue(Float.valueOf(f));
    }

    @Override // kotlinx.serialization.CompositeEncoder
    public final void encodeFloatElement(@NotNull SerialDescriptor descriptor, int i, float f) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (encodeElement(descriptor, i)) {
            encodeFloat(f);
        }
    }

    @Override // kotlinx.serialization.Encoder
    public void encodeInt(int i) {
        encodeValue(Integer.valueOf(i));
    }

    @Override // kotlinx.serialization.CompositeEncoder
    public final void encodeIntElement(@NotNull SerialDescriptor descriptor, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (encodeElement(descriptor, i)) {
            encodeInt(i2);
        }
    }

    @Override // kotlinx.serialization.Encoder
    public void encodeLong(long j) {
        encodeValue(Long.valueOf(j));
    }

    @Override // kotlinx.serialization.CompositeEncoder
    public final void encodeLongElement(@NotNull SerialDescriptor descriptor, int i, long j) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (encodeElement(descriptor, i)) {
            encodeLong(j);
        }
    }

    @Override // kotlinx.serialization.CompositeEncoder
    @Deprecated(level = DeprecationLevel.ERROR, message = "This method is deprecated for removal. Please remove it from your implementation and delegate to default method instead")
    public void encodeNonSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull Object value) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(value, "value");
        CompositeEncoder.DefaultImpls.encodeNonSerializableElement(this, descriptor, i, value);
    }

    @Override // kotlinx.serialization.Encoder
    public void encodeNotNullMark() {
        Encoder.DefaultImpls.encodeNotNullMark(this);
    }

    @Override // kotlinx.serialization.Encoder
    public void encodeNull() {
        throw new SerializationException("'null' is not supported by default", null, 2, null);
    }

    @Override // kotlinx.serialization.CompositeEncoder
    public final <T> void encodeNullableSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull SerializationStrategy<? super T> serializer, @Nullable T t) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        if (encodeElement(descriptor, i)) {
            encodeNullableSerializableValue(serializer, t);
        }
    }

    @Override // kotlinx.serialization.Encoder
    public <T> void encodeNullableSerializableValue(@NotNull SerializationStrategy<? super T> serializer, @Nullable T t) {
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        Encoder.DefaultImpls.encodeNullableSerializableValue(this, serializer, t);
    }

    @Override // kotlinx.serialization.CompositeEncoder
    public final <T> void encodeSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull SerializationStrategy<? super T> serializer, T t) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        if (encodeElement(descriptor, i)) {
            encodeSerializableValue(serializer, t);
        }
    }

    @Override // kotlinx.serialization.Encoder
    public <T> void encodeSerializableValue(@NotNull SerializationStrategy<? super T> serializer, T t) {
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        Encoder.DefaultImpls.encodeSerializableValue(this, serializer, t);
    }

    @Override // kotlinx.serialization.Encoder
    public void encodeShort(short s) {
        encodeValue(Short.valueOf(s));
    }

    @Override // kotlinx.serialization.CompositeEncoder
    public final void encodeShortElement(@NotNull SerialDescriptor descriptor, int i, short s) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (encodeElement(descriptor, i)) {
            encodeShort(s);
        }
    }

    @Override // kotlinx.serialization.Encoder
    public void encodeString(@NotNull String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        encodeValue(value);
    }

    @Override // kotlinx.serialization.CompositeEncoder
    public final void encodeStringElement(@NotNull SerialDescriptor descriptor, int i, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (encodeElement(descriptor, i)) {
            encodeString(value);
        }
    }

    @Override // kotlinx.serialization.Encoder
    public void encodeUnit() {
        PrimitiveSerializersKt.UnitSerializer().serialize(this, Unit.INSTANCE);
    }

    @Override // kotlinx.serialization.CompositeEncoder
    public final void encodeUnitElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (encodeElement(descriptor, i)) {
            encodeUnit();
        }
    }

    public void encodeValue(@NotNull Object value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Non-serializable ");
        outline107.append(Reflection.getOrCreateKotlinClass(value.getClass()));
        outline107.append(" is not supported by ");
        outline107.append(Reflection.getOrCreateKotlinClass(getClass()));
        outline107.append(" encoder");
        throw new SerializationException(outline107.toString(), null, 2, null);
    }

    @Override // kotlinx.serialization.Encoder, kotlinx.serialization.CompositeEncoder
    @NotNull
    public SerialModule getContext() {
        return EmptyModule.INSTANCE;
    }

    @Override // kotlinx.serialization.CompositeEncoder
    public boolean shouldEncodeElementDefault(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return CompositeEncoder.DefaultImpls.shouldEncodeElementDefault(this, descriptor, i);
    }
}
