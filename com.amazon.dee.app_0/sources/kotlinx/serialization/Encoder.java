package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.modules.SerialModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Encoding.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J9\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u001a\u0010\f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u000e0\r\"\u0006\u0012\u0002\b\u00030\u000eH\u0016¢\u0006\u0002\u0010\u000fJ1\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u001a\u0010\f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u000e0\r\"\u0006\u0012\u0002\b\u00030\u000eH&¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0017H&J\u0010\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0019H&J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u001bH&J\u0018\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u000bH&J\u0010\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020 H&J\u0010\u0010!\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000bH&J\u0010\u0010\"\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020#H&J\b\u0010$\u001a\u00020\u0013H\u0016J\b\u0010%\u001a\u00020\u0013H&J/\u0010&\u001a\u00020\u0013\"\b\b\u0000\u0010'*\u00020\u00012\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0)2\b\u0010\u0014\u001a\u0004\u0018\u0001H'H\u0016¢\u0006\u0002\u0010*J)\u0010+\u001a\u00020\u0013\"\u0004\b\u0000\u0010'2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0)2\u0006\u0010\u0014\u001a\u0002H'H\u0016¢\u0006\u0002\u0010*J\u0010\u0010,\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020-H&J\u0010\u0010.\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020/H&J\b\u00100\u001a\u00020\u0013H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u00061"}, d2 = {"Lkotlinx/serialization/Encoder;", "", "context", "Lkotlinx/serialization/modules/SerialModule;", "getContext", "()Lkotlinx/serialization/modules/SerialModule;", "beginCollection", "Lkotlinx/serialization/CompositeEncoder;", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "collectionSize", "", "typeSerializers", "", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/SerialDescriptor;I[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/CompositeEncoder;", "beginStructure", "(Lkotlinx/serialization/SerialDescriptor;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/CompositeEncoder;", "encodeBoolean", "", "value", "", "encodeByte", "", "encodeChar", "", "encodeDouble", "", "encodeEnum", "enumDescriptor", "index", "encodeFloat", "", "encodeInt", "encodeLong", "", "encodeNotNullMark", "encodeNull", "encodeNullableSerializableValue", ExifInterface.GPS_DIRECTION_TRUE, "serializer", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "encodeSerializableValue", "encodeShort", "", "encodeString", "", "encodeUnit", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface Encoder {

    /* compiled from: Encoding.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        @NotNull
        public static CompositeEncoder beginCollection(Encoder encoder, @NotNull SerialDescriptor descriptor, int i, @NotNull KSerializer<?>... typeSerializers) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(typeSerializers, "typeSerializers");
            return encoder.beginStructure(descriptor, (KSerializer[]) Arrays.copyOf(typeSerializers, typeSerializers.length));
        }

        public static void encodeNotNullMark(Encoder encoder) {
        }

        public static <T> void encodeNullableSerializableValue(Encoder encoder, @NotNull SerializationStrategy<? super T> serializer, @Nullable T t) {
            Intrinsics.checkParameterIsNotNull(serializer, "serializer");
            if (t == null) {
                encoder.encodeNull();
                return;
            }
            encoder.encodeNotNullMark();
            encoder.encodeSerializableValue(serializer, t);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static <T> void encodeSerializableValue(Encoder encoder, @NotNull SerializationStrategy<? super T> serializer, T t) {
            Intrinsics.checkParameterIsNotNull(serializer, "serializer");
            serializer.serialize(encoder, t);
        }
    }

    @NotNull
    CompositeEncoder beginCollection(@NotNull SerialDescriptor serialDescriptor, int i, @NotNull KSerializer<?>... kSerializerArr);

    @NotNull
    CompositeEncoder beginStructure(@NotNull SerialDescriptor serialDescriptor, @NotNull KSerializer<?>... kSerializerArr);

    void encodeBoolean(boolean z);

    void encodeByte(byte b);

    void encodeChar(char c);

    void encodeDouble(double d);

    void encodeEnum(@NotNull SerialDescriptor serialDescriptor, int i);

    void encodeFloat(float f);

    void encodeInt(int i);

    void encodeLong(long j);

    void encodeNotNullMark();

    void encodeNull();

    <T> void encodeNullableSerializableValue(@NotNull SerializationStrategy<? super T> serializationStrategy, @Nullable T t);

    <T> void encodeSerializableValue(@NotNull SerializationStrategy<? super T> serializationStrategy, T t);

    void encodeShort(short s);

    void encodeString(@NotNull String str);

    void encodeUnit();

    @NotNull
    SerialModule getContext();
}
