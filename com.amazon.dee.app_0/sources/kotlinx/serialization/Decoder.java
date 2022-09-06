package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.modules.SerialModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Decoding.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J1\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u001a\u0010\u000e\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f\"\u0006\u0012\u0002\b\u00030\u0010H&¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0017H&J\b\u0010\u0018\u001a\u00020\u0019H&J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\rH&J\b\u0010\u001d\u001a\u00020\u001eH&J\b\u0010\u001f\u001a\u00020\u001bH&J\b\u0010 \u001a\u00020!H&J\b\u0010\"\u001a\u00020\u0013H&J\n\u0010#\u001a\u0004\u0018\u00010$H&J)\u0010%\u001a\u0004\u0018\u0001H&\"\b\b\u0000\u0010&*\u00020\u00012\u000e\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H&0(H\u0016¢\u0006\u0002\u0010)J!\u0010*\u001a\u0002H&\"\u0004\b\u0000\u0010&2\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H&0(H\u0016¢\u0006\u0002\u0010)J\b\u0010+\u001a\u00020,H&J\b\u0010-\u001a\u00020.H&J\b\u0010/\u001a\u000200H&J3\u00101\u001a\u0004\u0018\u0001H&\"\b\b\u0000\u0010&*\u00020\u00012\u000e\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H&0(2\b\u00102\u001a\u0004\u0018\u0001H&H\u0016¢\u0006\u0002\u00103J)\u00104\u001a\u0002H&\"\u0004\b\u0000\u0010&2\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H&0(2\u0006\u00102\u001a\u0002H&H\u0016¢\u0006\u0002\u00103R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u00065"}, d2 = {"Lkotlinx/serialization/Decoder;", "", "context", "Lkotlinx/serialization/modules/SerialModule;", "getContext", "()Lkotlinx/serialization/modules/SerialModule;", "updateMode", "Lkotlinx/serialization/UpdateMode;", "getUpdateMode", "()Lkotlinx/serialization/UpdateMode;", "beginStructure", "Lkotlinx/serialization/CompositeDecoder;", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "typeParams", "", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/SerialDescriptor;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/CompositeDecoder;", "decodeBoolean", "", "decodeByte", "", "decodeChar", "", "decodeDouble", "", "decodeEnum", "", "enumDescriptor", "decodeFloat", "", "decodeInt", "decodeLong", "", "decodeNotNullMark", "decodeNull", "", "decodeNullableSerializableValue", ExifInterface.GPS_DIRECTION_TRUE, "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "decodeSerializableValue", "decodeShort", "", "decodeString", "", "decodeUnit", "", "updateNullableSerializableValue", "old", "(Lkotlinx/serialization/DeserializationStrategy;Ljava/lang/Object;)Ljava/lang/Object;", "updateSerializableValue", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface Decoder {

    /* compiled from: Decoding.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        @Nullable
        public static <T> T decodeNullableSerializableValue(Decoder decoder, @NotNull DeserializationStrategy<T> deserializer) {
            Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
            return decoder.decodeNotNullMark() ? (T) decoder.decodeSerializableValue(deserializer) : (T) decoder.decodeNull();
        }

        public static <T> T decodeSerializableValue(Decoder decoder, @NotNull DeserializationStrategy<T> deserializer) {
            Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
            return deserializer.mo12413deserialize(decoder);
        }

        @Nullable
        public static <T> T updateNullableSerializableValue(Decoder decoder, @NotNull DeserializationStrategy<T> deserializer, @Nullable T t) {
            Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
            if (decoder.getUpdateMode() != UpdateMode.BANNED) {
                if (decoder.getUpdateMode() != UpdateMode.OVERWRITE && t != null) {
                    if (decoder.decodeNotNullMark()) {
                        return deserializer.patch(decoder, t);
                    }
                    decoder.decodeNull();
                    return t;
                }
                return (T) decoder.decodeNullableSerializableValue(deserializer);
            }
            throw new UpdateNotSupportedException(deserializer.getDescriptor().getSerialName());
        }

        public static <T> T updateSerializableValue(Decoder decoder, @NotNull DeserializationStrategy<T> deserializer, T t) {
            Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
            int i = WhenMappings.$EnumSwitchMapping$0[decoder.getUpdateMode().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    return (T) decoder.decodeSerializableValue(deserializer);
                }
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                return deserializer.patch(decoder, t);
            }
            throw new UpdateNotSupportedException(deserializer.getDescriptor().getSerialName());
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[UpdateMode.values().length];

        static {
            $EnumSwitchMapping$0[UpdateMode.BANNED.ordinal()] = 1;
            $EnumSwitchMapping$0[UpdateMode.OVERWRITE.ordinal()] = 2;
            $EnumSwitchMapping$0[UpdateMode.UPDATE.ordinal()] = 3;
        }
    }

    @NotNull
    CompositeDecoder beginStructure(@NotNull SerialDescriptor serialDescriptor, @NotNull KSerializer<?>... kSerializerArr);

    boolean decodeBoolean();

    byte decodeByte();

    char decodeChar();

    double decodeDouble();

    int decodeEnum(@NotNull SerialDescriptor serialDescriptor);

    float decodeFloat();

    int decodeInt();

    long decodeLong();

    boolean decodeNotNullMark();

    @Nullable
    Void decodeNull();

    @Nullable
    <T> T decodeNullableSerializableValue(@NotNull DeserializationStrategy<T> deserializationStrategy);

    <T> T decodeSerializableValue(@NotNull DeserializationStrategy<T> deserializationStrategy);

    short decodeShort();

    @NotNull
    String decodeString();

    void decodeUnit();

    @NotNull
    SerialModule getContext();

    @NotNull
    UpdateMode getUpdateMode();

    @Nullable
    <T> T updateNullableSerializableValue(@NotNull DeserializationStrategy<T> deserializationStrategy, @Nullable T t);

    <T> T updateSerializableValue(@NotNull DeserializationStrategy<T> deserializationStrategy, T t);
}
