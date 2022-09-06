package com.amazon.tarazed.core.types.annotations;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialDescriptorBuilderKt;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.Serializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonInput;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.NotNull;
/* compiled from: AnnotationEventSerializer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u001c\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/amazon/tarazed/core/types/annotations/AnnotationEventSerializer;", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/types/annotations/AnnotationEvent;", "()V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "obj", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializer(forClass = AnnotationEvent.class)
/* loaded from: classes13.dex */
public final class AnnotationEventSerializer implements KSerializer<AnnotationEvent<?>> {
    @NotNull
    private final SerialDescriptor descriptor = SerialDescriptorBuilderKt.SerialDescriptor$default("AnnotationEventSerializer", null, null, 6, null);

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public AnnotationEvent<?> patch(@NotNull Decoder decoder, @NotNull AnnotationEvent<?> old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (AnnotationEvent) KSerializer.DefaultImpls.patch(this, decoder, old);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize */
    public AnnotationEvent<?> mo12413deserialize(@NotNull Decoder decoder) {
        JsonPrimitive primitive;
        String content;
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        JsonObject jsonObject = null;
        if (!(decoder instanceof JsonInput)) {
            decoder = null;
        }
        JsonInput jsonInput = (JsonInput) decoder;
        if (jsonInput != null) {
            JsonObject jsonObject2 = jsonInput.decodeJson().getJsonObject();
            try {
                JsonElement jsonElement = (JsonElement) jsonObject2.get((Object) AnnotationEvent.KEY_ANNOTATION_TYPE);
                if (jsonElement != null && (primitive = jsonElement.getPrimitive()) != null && (content = primitive.getContent()) != null) {
                    AnnotationType fromString = AnnotationType.Companion.fromString(content);
                    if (fromString != null) {
                        JsonElement jsonElement2 = (JsonElement) jsonObject2.get((Object) "color");
                        String valueOf = String.valueOf(jsonElement2 != null ? jsonElement2.getJsonObject() : null);
                        JsonElement jsonElement3 = (JsonElement) jsonObject2.get((Object) "annotation");
                        if (jsonElement3 != null) {
                            jsonObject = jsonElement3.getJsonObject();
                        }
                        return new AnnotationEvent<>(fromString, (AnnotationColor) Json.Default.parse(AnnotationColor.Companion.serializer(), valueOf), Json.Default.parse(fromString.getSerializer(), String.valueOf(jsonObject)));
                    }
                    throw new SerializationException("Unknown annotation type, cannot deserialize: " + content, null, 2, null);
                }
                throw new SerializationException("\"annotationType\" cannot be null", null, 2, null);
            } catch (NoSuchElementException e) {
                throw new SerializationException("Expected JSON element was missing", e);
            }
        }
        throw new SerializationException("This serializer only supports JSON", null, 2, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull AnnotationEvent<?> obj) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(obj, "obj");
        KSerializer serializer = AnnotationEvent.Companion.serializer(obj.getAnnotationType().getSerializer());
        if (serializer != null) {
            encoder.encodeSerializableValue(serializer, obj);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.SerializationStrategy<com.amazon.tarazed.core.types.annotations.AnnotationEvent<*>>");
    }
}
