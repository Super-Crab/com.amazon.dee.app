package kotlinx.serialization.json;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PrimitiveKind;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialDescriptorBuilderKt;
import kotlinx.serialization.Serializer;
import org.jetbrains.annotations.NotNull;
/* compiled from: JsonElementSerializer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lkotlinx/serialization/json/JsonLiteralSerializer;", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/serialization/json/JsonLiteral;", "()V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "value", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@Serializer(forClass = JsonLiteral.class)
/* loaded from: classes4.dex */
public final class JsonLiteralSerializer implements KSerializer<JsonLiteral> {
    public static final JsonLiteralSerializer INSTANCE = new JsonLiteralSerializer();
    @NotNull
    private static final SerialDescriptor descriptor = SerialDescriptorBuilderKt.PrimitiveDescriptor("kotlinx.serialization.json.JsonLiteral", PrimitiveKind.STRING.INSTANCE);

    private JsonLiteralSerializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize  reason: collision with other method in class */
    public JsonLiteral mo12413deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        JsonElement decodeJson = JsonElementSerializerKt.asJsonInput(decoder).decodeJson();
        if (decodeJson instanceof JsonLiteral) {
            return (JsonLiteral) decodeJson;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected JSON element, expected JsonLiteral, had ");
        outline107.append(Reflection.getOrCreateKotlinClass(decodeJson.getClass()));
        throw JsonExceptionsKt.JsonDecodingException(-1, outline107.toString(), decodeJson.toString());
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public JsonLiteral patch(@NotNull Decoder decoder, @NotNull JsonLiteral old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (JsonLiteral) KSerializer.DefaultImpls.patch(this, decoder, old);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull JsonLiteral value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        JsonElementSerializerKt.verify(encoder);
        if (value.isString()) {
            encoder.encodeString(value.getContent());
            return;
        }
        Long longOrNull = value.getLongOrNull();
        if (longOrNull != null) {
            encoder.encodeLong(longOrNull.longValue());
            return;
        }
        Double doubleOrNull = value.getDoubleOrNull();
        if (doubleOrNull != null) {
            encoder.encodeDouble(doubleOrNull.doubleValue());
            return;
        }
        Boolean booleanOrNull = value.getBooleanOrNull();
        if (booleanOrNull != null) {
            encoder.encodeBoolean(booleanOrNull.booleanValue());
        } else {
            encoder.encodeString(value.getContent());
        }
    }
}
