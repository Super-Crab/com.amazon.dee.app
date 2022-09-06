package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import java.lang.Enum;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialDescriptorBuilderKt;
import kotlinx.serialization.UnionKind;
import org.jetbrains.annotations.NotNull;
/* compiled from: Enums.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0002\u0010\u0011J\u001d\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0017R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u0018"}, d2 = {"Lkotlinx/serialization/internal/EnumSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/serialization/KSerializer;", "serialName", "", "values", "", "(Ljava/lang/String;[Ljava/lang/Enum;)V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "[Ljava/lang/Enum;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "(Lkotlinx/serialization/Decoder;)Ljava/lang/Enum;", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "value", "(Lkotlinx/serialization/Encoder;Ljava/lang/Enum;)V", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
@Deprecated(level = DeprecationLevel.ERROR, message = "For plugin-generated code, should not be used directly. For the custom serializers please report your use-case to project issues, so proper public API could be introduced instead")
/* loaded from: classes4.dex */
public final class EnumSerializer<T extends Enum<T>> implements KSerializer<T> {
    @NotNull
    private final SerialDescriptor descriptor;
    private final T[] values;

    public EnumSerializer(@NotNull String serialName, @NotNull T[] values) {
        Intrinsics.checkParameterIsNotNull(serialName, "serialName");
        Intrinsics.checkParameterIsNotNull(values, "values");
        this.values = values;
        this.descriptor = SerialDescriptorBuilderKt.SerialDescriptor(serialName, UnionKind.ENUM_KIND.INSTANCE, new EnumSerializer$descriptor$1(this, serialName));
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @NotNull
    public T patch(@NotNull Decoder decoder, @NotNull T old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (T) KSerializer.DefaultImpls.patch(this, decoder, old);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder encoder, Object obj) {
        serialize(encoder, (Encoder) ((Enum) obj));
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize */
    public T mo12413deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        int decodeEnum = decoder.decodeEnum(getDescriptor());
        if (decodeEnum >= 0 && this.values.length > decodeEnum) {
            return this.values[decodeEnum];
        }
        throw new IllegalStateException((decodeEnum + " is not among valid $" + getDescriptor().getSerialName() + " enum values, values size is " + this.values.length).toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    public /* bridge */ /* synthetic */ Object patch(Decoder decoder, Object obj) {
        return patch(decoder, (Decoder) ((Enum) obj));
    }

    public void serialize(@NotNull Encoder encoder, @NotNull T value) {
        int indexOf;
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        indexOf = ArraysKt___ArraysKt.indexOf(this.values, value);
        if (indexOf != -1) {
            encoder.encodeEnum(getDescriptor(), indexOf);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        sb.append(" is not a valid enum ");
        sb.append(getDescriptor().getSerialName());
        sb.append(", must be one of ");
        String arrays = Arrays.toString(this.values);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        sb.append(arrays);
        throw new IllegalStateException(sb.toString().toString());
    }
}
