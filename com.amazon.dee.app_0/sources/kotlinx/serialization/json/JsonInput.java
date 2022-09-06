package kotlinx.serialization.json;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.CompositeDecoder;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JsonInput.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0007\u001a\u00020\bH&R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlinx/serialization/json/JsonInput;", "Lkotlinx/serialization/Decoder;", "Lkotlinx/serialization/CompositeDecoder;", "json", "Lkotlinx/serialization/json/Json;", "getJson", "()Lkotlinx/serialization/json/Json;", "decodeJson", "Lkotlinx/serialization/json/JsonElement;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface JsonInput extends Decoder, CompositeDecoder {

    /* compiled from: JsonInput.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static int decodeCollectionSize(JsonInput jsonInput, @NotNull SerialDescriptor descriptor) {
            Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
            return CompositeDecoder.DefaultImpls.decodeCollectionSize(jsonInput, descriptor);
        }

        @Nullable
        public static <T> T decodeNullableSerializableValue(JsonInput jsonInput, @NotNull DeserializationStrategy<T> deserializer) {
            Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
            return (T) Decoder.DefaultImpls.decodeNullableSerializableValue(jsonInput, deserializer);
        }

        public static boolean decodeSequentially(JsonInput jsonInput) {
            return CompositeDecoder.DefaultImpls.decodeSequentially(jsonInput);
        }

        public static <T> T decodeSerializableValue(JsonInput jsonInput, @NotNull DeserializationStrategy<T> deserializer) {
            Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
            return (T) Decoder.DefaultImpls.decodeSerializableValue(jsonInput, deserializer);
        }

        @Nullable
        public static <T> T updateNullableSerializableValue(JsonInput jsonInput, @NotNull DeserializationStrategy<T> deserializer, @Nullable T t) {
            Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
            return (T) Decoder.DefaultImpls.updateNullableSerializableValue(jsonInput, deserializer, t);
        }

        public static <T> T updateSerializableValue(JsonInput jsonInput, @NotNull DeserializationStrategy<T> deserializer, T t) {
            Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
            return (T) Decoder.DefaultImpls.updateSerializableValue(jsonInput, deserializer, t);
        }
    }

    @NotNull
    JsonElement decodeJson();

    @NotNull
    Json getJson();
}
