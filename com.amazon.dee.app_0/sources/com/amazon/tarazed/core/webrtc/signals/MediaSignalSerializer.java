package com.amazon.tarazed.core.webrtc.signals;

import com.android.tools.r8.GeneratedOutlineSupport1;
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
/* compiled from: MediaSignalSerializer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0014\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001c\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/signals/MediaSignalSerializer;", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/webrtc/signals/MediaSignal;", "()V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "getSerializerForSignalType", "", "signalType", "", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "obj", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializer(forClass = MediaSignal.class)
/* loaded from: classes13.dex */
public final class MediaSignalSerializer implements KSerializer<MediaSignal<?>> {
    public static final MediaSignalSerializer INSTANCE = new MediaSignalSerializer();
    @NotNull
    private static final SerialDescriptor descriptor = SerialDescriptorBuilderKt.SerialDescriptor$default("MediaSignalSerializer", null, null, 6, null);

    private MediaSignalSerializer() {
    }

    private final KSerializer<? extends Object> getSerializerForSignalType(String str) {
        int hashCode = str.hashCode();
        if (hashCode != -1412808770) {
            if (hashCode == 105650780 && str.equals(WebRTCSignalTypes.OFFER)) {
                return Offer.Companion.serializer();
            }
        } else if (str.equals(WebRTCSignalTypes.ANSWER)) {
            return Answer.Companion.serializer();
        }
        throw new SerializationException(GeneratedOutlineSupport1.outline72("Unknown signal type, no known serializer: ", str), null, 2, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public MediaSignal<?> patch(@NotNull Decoder decoder, @NotNull MediaSignal<?> old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (MediaSignal) KSerializer.DefaultImpls.patch(this, decoder, old);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize */
    public MediaSignal<?> mo12413deserialize(@NotNull Decoder decoder) {
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
                JsonElement jsonElement = (JsonElement) jsonObject2.get((Object) "signalType");
                if (jsonElement != null && (primitive = jsonElement.getPrimitive()) != null && (content = primitive.getContent()) != null) {
                    JsonElement jsonElement2 = (JsonElement) jsonObject2.get((Object) "signalPayload");
                    if (jsonElement2 != null) {
                        jsonObject = jsonElement2.getJsonObject();
                    }
                    return new MediaSignal<>(content, Json.Default.parse(getSerializerForSignalType(content), String.valueOf(jsonObject)));
                }
                throw new SerializationException("\"signalType\" cannot be null", null, 2, null);
            } catch (NoSuchElementException e) {
                throw new SerializationException("Expected JSON element was missing", e);
            }
        }
        throw new SerializationException("This serializer only supports JSON", null, 2, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull MediaSignal<?> obj) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(obj, "obj");
        KSerializer serializer = MediaSignal.Companion.serializer(getSerializerForSignalType(obj.getSignalType()));
        if (serializer != null) {
            encoder.encodeSerializableValue(serializer, obj);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.SerializationStrategy<com.amazon.tarazed.core.webrtc.signals.MediaSignal<*>>");
    }
}
