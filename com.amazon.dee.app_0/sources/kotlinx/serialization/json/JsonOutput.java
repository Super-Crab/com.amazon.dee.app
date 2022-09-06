package kotlinx.serialization.json;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerializationStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JsonOutput.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lkotlinx/serialization/json/JsonOutput;", "Lkotlinx/serialization/Encoder;", "Lkotlinx/serialization/CompositeEncoder;", "json", "Lkotlinx/serialization/json/Json;", "getJson", "()Lkotlinx/serialization/json/Json;", "encodeJson", "", "element", "Lkotlinx/serialization/json/JsonElement;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface JsonOutput extends Encoder, CompositeEncoder {

    /* compiled from: JsonOutput.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        @NotNull
        public static CompositeEncoder beginCollection(JsonOutput jsonOutput, @NotNull SerialDescriptor descriptor, int i, @NotNull KSerializer<?>... typeSerializers) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(typeSerializers, "typeSerializers");
            return Encoder.DefaultImpls.beginCollection(jsonOutput, descriptor, i, typeSerializers);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "This method is deprecated for removal. Please remove it from your implementation and delegate to default method instead")
        public static void encodeNonSerializableElement(JsonOutput jsonOutput, @NotNull SerialDescriptor descriptor, int i, @NotNull Object value) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            Intrinsics.checkParameterIsNotNull(value, "value");
            CompositeEncoder.DefaultImpls.encodeNonSerializableElement(jsonOutput, descriptor, i, value);
        }

        public static void encodeNotNullMark(JsonOutput jsonOutput) {
            Encoder.DefaultImpls.encodeNotNullMark(jsonOutput);
        }

        public static <T> void encodeNullableSerializableValue(JsonOutput jsonOutput, @NotNull SerializationStrategy<? super T> serializer, @Nullable T t) {
            Intrinsics.checkParameterIsNotNull(serializer, "serializer");
            Encoder.DefaultImpls.encodeNullableSerializableValue(jsonOutput, serializer, t);
        }

        public static <T> void encodeSerializableValue(JsonOutput jsonOutput, @NotNull SerializationStrategy<? super T> serializer, T t) {
            Intrinsics.checkParameterIsNotNull(serializer, "serializer");
            Encoder.DefaultImpls.encodeSerializableValue(jsonOutput, serializer, t);
        }

        public static boolean shouldEncodeElementDefault(JsonOutput jsonOutput, @NotNull SerialDescriptor descriptor, int i) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            return CompositeEncoder.DefaultImpls.shouldEncodeElementDefault(jsonOutput, descriptor, i);
        }
    }

    void encodeJson(@NotNull JsonElement jsonElement);

    @NotNull
    Json getJson();
}
