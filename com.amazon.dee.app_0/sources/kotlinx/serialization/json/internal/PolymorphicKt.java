package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PolymorphicKind;
import kotlinx.serialization.PrimitiveKind;
import kotlinx.serialization.SealedClassSerializer;
import kotlinx.serialization.SerialKind;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.UnionKind;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.internal.UtilKt;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementsKt;
import kotlinx.serialization.json.JsonInput;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonOutput;
import org.jetbrains.annotations.NotNull;
/* compiled from: Polymorphic.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a*\u0010\u0004\u001a\u00020\u00012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\t\u001a\u00020\nH\u0002\u001a%\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\f*\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\f0\u000fH\u0000¢\u0006\u0002\u0010\u0010\u001a<\u0010\u0011\u001a\u00020\u0001\"\u0004\b\u0000\u0010\f*\u00020\u00122\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\f0\u00132\u0006\u0010\u0014\u001a\u0002H\f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016H\u0080\b¢\u0006\u0002\u0010\u0017¨\u0006\u0018"}, d2 = {"checkKind", "", ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, "Lkotlinx/serialization/SerialKind;", "validateIfSealed", "serializer", "Lkotlinx/serialization/KSerializer;", "actualSerializer", "", "classDiscriminator", "", "decodeSerializableValuePolymorphic", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/serialization/json/JsonInput;", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/json/JsonInput;Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "encodePolymorphically", "Lkotlinx/serialization/json/JsonOutput;", "Lkotlinx/serialization/SerializationStrategy;", "value", "ifPolymorphic", "Lkotlin/Function0;", "(Lkotlinx/serialization/json/JsonOutput;Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)V", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PolymorphicKt {
    public static final void checkKind(@NotNull SerialKind kind) {
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        if (!(kind instanceof UnionKind.ENUM_KIND)) {
            if (!(kind instanceof PrimitiveKind)) {
                if (kind instanceof PolymorphicKind) {
                    throw new IllegalStateException("Actual serializer for polymorphic cannot be polymorphic itself".toString());
                }
                return;
            }
            throw new IllegalStateException("Primitives cannot be serialized polymorphically with 'type' parameter. You can use 'JsonConfiguration.useArrayPolymorphism' instead".toString());
        }
        throw new IllegalStateException("Enums cannot be serialized polymorphically with 'type' parameter. You can use 'JsonConfiguration.useArrayPolymorphism' instead".toString());
    }

    public static final <T> T decodeSerializableValuePolymorphic(@NotNull JsonInput decodeSerializableValuePolymorphic, @NotNull DeserializationStrategy<T> deserializer) {
        Intrinsics.checkParameterIsNotNull(decodeSerializableValuePolymorphic, "$this$decodeSerializableValuePolymorphic");
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        if ((deserializer instanceof AbstractPolymorphicSerializer) && !decodeSerializableValuePolymorphic.getJson().configuration.getUseArrayPolymorphism$kotlinx_serialization_runtime()) {
            JsonElement decodeJson = decodeSerializableValuePolymorphic.decodeJson();
            if (decodeJson instanceof JsonObject) {
                JsonObject jsonObject = (JsonObject) decodeJson;
                String content = JsonElementsKt.getContent((JsonElement) MapsKt.getValue(jsonObject, decodeSerializableValuePolymorphic.getJson().configuration.getClassDiscriminator$kotlinx_serialization_runtime()));
                Map<String, JsonElement> content2 = jsonObject.getContent();
                if (content2 != null) {
                    TypeIntrinsics.asMutableMap(content2).remove(decodeSerializableValuePolymorphic.getJson().configuration.getClassDiscriminator$kotlinx_serialization_runtime());
                    KSerializer<? extends T> findPolymorphicSerializer = ((AbstractPolymorphicSerializer) deserializer).findPolymorphicSerializer(decodeSerializableValuePolymorphic, content);
                    if (findPolymorphicSerializer != null) {
                        return (T) TreeJsonInputKt.readJson(decodeSerializableValuePolymorphic.getJson(), jsonObject, findPolymorphicSerializer);
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlinx.serialization.json.JsonElement>");
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected ");
            outline107.append(Reflection.getOrCreateKotlinClass(JsonObject.class));
            outline107.append(" but found ");
            outline107.append(Reflection.getOrCreateKotlinClass(decodeJson.getClass()));
            throw new IllegalStateException(outline107.toString().toString());
        }
        return deserializer.mo12413deserialize(decodeSerializableValuePolymorphic);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> void encodePolymorphically(@NotNull JsonOutput encodePolymorphically, @NotNull SerializationStrategy<? super T> serializer, T t, @NotNull Function0<Unit> ifPolymorphic) {
        Intrinsics.checkParameterIsNotNull(encodePolymorphically, "$this$encodePolymorphically");
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        Intrinsics.checkParameterIsNotNull(ifPolymorphic, "ifPolymorphic");
        if ((serializer instanceof AbstractPolymorphicSerializer) && !encodePolymorphically.getJson().configuration.getUseArrayPolymorphism$kotlinx_serialization_runtime()) {
            AbstractPolymorphicSerializer abstractPolymorphicSerializer = (AbstractPolymorphicSerializer) serializer;
            if (t != 0) {
                KSerializer<? extends T> findPolymorphicSerializer = abstractPolymorphicSerializer.findPolymorphicSerializer((Encoder) encodePolymorphically, (JsonOutput) t);
                if (findPolymorphicSerializer != null) {
                    validateIfSealed((KSerializer) serializer, findPolymorphicSerializer, encodePolymorphically.getJson().configuration.getClassDiscriminator$kotlinx_serialization_runtime());
                    checkKind(findPolymorphicSerializer.getDescriptor().mo12397getKind());
                    ifPolymorphic.mo12560invoke();
                    findPolymorphicSerializer.serialize(encodePolymorphically, t);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Any");
        }
        serializer.serialize(encodePolymorphically, t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateIfSealed(KSerializer<?> kSerializer, KSerializer<Object> kSerializer2, String str) {
        if ((kSerializer instanceof SealedClassSerializer) && UtilKt.cachedSerialNames(kSerializer2.getDescriptor()).contains(str)) {
            StringBuilder outline116 = GeneratedOutlineSupport1.outline116("Sealed class '", kSerializer2.getDescriptor().getSerialName(), "' cannot be serialized as base class '", kSerializer.getDescriptor().getSerialName(), "' because");
            GeneratedOutlineSupport1.outline181(outline116, " it has property name that conflicts with JSON class discriminator '", str, "'. ", "You can either change class discriminator in JsonConfiguration, ");
            outline116.append("rename property with @SerialName annotation or fall back to array polymorphism");
            throw new IllegalStateException(outline116.toString().toString());
        }
    }
}
