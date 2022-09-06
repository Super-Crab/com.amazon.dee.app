package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.modules.SerialModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Encoding.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J \u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000fH&J \u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0011H&J \u0010\u0012\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0013H&J \u0010\u0014\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0015H&J \u0010\u0016\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH&J \u0010\u0017\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0018H&J \u0010\u0019\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0001H\u0017J?\u0010\u001a\u001a\u00020\u0007\"\b\b\u0000\u0010\u001b*\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001d2\b\u0010\f\u001a\u0004\u0018\u0001H\u001bH&¢\u0006\u0002\u0010\u001eJ9\u0010\u001f\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u001b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001d2\u0006\u0010\f\u001a\u0002H\u001bH&¢\u0006\u0002\u0010\u001eJ \u0010 \u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020!H&J \u0010\"\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020#H&J\u0018\u0010$\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010%\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010&\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006'"}, d2 = {"Lkotlinx/serialization/CompositeEncoder;", "", "context", "Lkotlinx/serialization/modules/SerialModule;", "getContext", "()Lkotlinx/serialization/modules/SerialModule;", "encodeBooleanElement", "", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "index", "", "value", "", "encodeByteElement", "", "encodeCharElement", "", "encodeDoubleElement", "", "encodeFloatElement", "", "encodeIntElement", "encodeLongElement", "", "encodeNonSerializableElement", "encodeNullableSerializableElement", ExifInterface.GPS_DIRECTION_TRUE, "serializer", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/SerialDescriptor;ILkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "encodeSerializableElement", "encodeShortElement", "", "encodeStringElement", "", "encodeUnitElement", "endStructure", "shouldEncodeElementDefault", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface CompositeEncoder {

    /* compiled from: Encoding.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        @Deprecated(level = DeprecationLevel.ERROR, message = "This method is deprecated for removal. Please remove it from your implementation and delegate to default method instead")
        public static void encodeNonSerializableElement(CompositeEncoder compositeEncoder, @NotNull SerialDescriptor descriptor, int i, @NotNull Object value) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(value, "value");
        }

        public static boolean shouldEncodeElementDefault(CompositeEncoder compositeEncoder, @NotNull SerialDescriptor descriptor, int i) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            return true;
        }
    }

    void encodeBooleanElement(@NotNull SerialDescriptor serialDescriptor, int i, boolean z);

    void encodeByteElement(@NotNull SerialDescriptor serialDescriptor, int i, byte b);

    void encodeCharElement(@NotNull SerialDescriptor serialDescriptor, int i, char c);

    void encodeDoubleElement(@NotNull SerialDescriptor serialDescriptor, int i, double d);

    void encodeFloatElement(@NotNull SerialDescriptor serialDescriptor, int i, float f);

    void encodeIntElement(@NotNull SerialDescriptor serialDescriptor, int i, int i2);

    void encodeLongElement(@NotNull SerialDescriptor serialDescriptor, int i, long j);

    @Deprecated(level = DeprecationLevel.ERROR, message = "This method is deprecated for removal. Please remove it from your implementation and delegate to default method instead")
    void encodeNonSerializableElement(@NotNull SerialDescriptor serialDescriptor, int i, @NotNull Object obj);

    <T> void encodeNullableSerializableElement(@NotNull SerialDescriptor serialDescriptor, int i, @NotNull SerializationStrategy<? super T> serializationStrategy, @Nullable T t);

    <T> void encodeSerializableElement(@NotNull SerialDescriptor serialDescriptor, int i, @NotNull SerializationStrategy<? super T> serializationStrategy, T t);

    void encodeShortElement(@NotNull SerialDescriptor serialDescriptor, int i, short s);

    void encodeStringElement(@NotNull SerialDescriptor serialDescriptor, int i, @NotNull String str);

    void encodeUnitElement(@NotNull SerialDescriptor serialDescriptor, int i);

    void endStructure(@NotNull SerialDescriptor serialDescriptor);

    @NotNull
    SerialModule getContext();

    boolean shouldEncodeElementDefault(@NotNull SerialDescriptor serialDescriptor, int i);
}
