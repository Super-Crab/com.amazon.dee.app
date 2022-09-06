package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt___StringsKt;
import kotlinx.serialization.CompositeDecoder;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PolymorphicKind;
import kotlinx.serialization.PrimitiveKind;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialDescriptorKt;
import kotlinx.serialization.SerialKind;
import kotlinx.serialization.StructureKind;
import kotlinx.serialization.UnionKind;
import kotlinx.serialization.UpdateMode;
import kotlinx.serialization.internal.NamedValueDecoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonExceptionsKt;
import kotlinx.serialization.json.JsonInput;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.modules.SerialModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TreeJsonInput.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J1\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u001a\u0010\u001c\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u001e0\u001d\"\u0006\u0012\u0002\b\u00030\u001eH\u0016¢\u0006\u0002\u0010\u001fJ\u0018\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!H\u0016J\u0010\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020!H$J\b\u0010&\u001a\u00020\u0006H\u0002J\b\u0010'\u001a\u00020\u0006H\u0016J!\u0010(\u001a\u0002H)\"\u0004\b\u0000\u0010)2\f\u0010*\u001a\b\u0012\u0004\u0012\u0002H)0+H\u0016¢\u0006\u0002\u0010,J\u0010\u0010-\u001a\u00020.2\u0006\u0010%\u001a\u00020!H\u0016J\u0010\u0010/\u001a\u0002002\u0006\u0010%\u001a\u00020!H\u0016J\u0010\u00101\u001a\u0002022\u0006\u0010%\u001a\u00020!H\u0016J\u0010\u00103\u001a\u0002042\u0006\u0010%\u001a\u00020!H\u0016J\u0018\u00105\u001a\u0002062\u0006\u0010%\u001a\u00020!2\u0006\u00107\u001a\u00020\u001bH\u0016J\u0010\u00108\u001a\u0002092\u0006\u0010%\u001a\u00020!H\u0016J\u0010\u0010:\u001a\u0002062\u0006\u0010%\u001a\u00020!H\u0016J\u0010\u0010;\u001a\u00020<2\u0006\u0010%\u001a\u00020!H\u0016J\u0010\u0010=\u001a\u00020.2\u0006\u0010%\u001a\u00020!H\u0016J\u0012\u0010>\u001a\u0004\u0018\u00010?2\u0006\u0010%\u001a\u00020!H\u0016J\u0010\u0010@\u001a\u00020A2\u0006\u0010%\u001a\u00020!H\u0016J\u0010\u0010B\u001a\u00020!2\u0006\u0010%\u001a\u00020!H\u0016J\u0010\u0010C\u001a\u00020D2\u0006\u0010%\u001a\u00020!H\u0016J\u0010\u0010E\u001a\u00020D2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u0016\u001a\u00020F2\u0006\u0010%\u001a\u00020!H\u0014J=\u0010G\u001a\u0002H)\"\b\b\u0000\u0010)*\u00020H*\u00020F2\u0006\u0010G\u001a\u00020!2\u0017\u0010I\u001a\u0013\u0012\u0004\u0012\u00020F\u0012\u0004\u0012\u0002H)0J¢\u0006\u0002\bKH\u0082\b¢\u0006\u0002\u0010LR\u0010\u0010\b\u001a\u00020\t8\u0004X\u0085\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u0082\u0001\u0003MNO¨\u0006P"}, d2 = {"Lkotlinx/serialization/json/internal/AbstractJsonTreeInput;", "Lkotlinx/serialization/internal/NamedValueDecoder;", "Lkotlinx/serialization/json/JsonInput;", "json", "Lkotlinx/serialization/json/Json;", "value", "Lkotlinx/serialization/json/JsonElement;", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/JsonElement;)V", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lkotlinx/serialization/json/JsonConfiguration;", "context", "Lkotlinx/serialization/modules/SerialModule;", "getContext", "()Lkotlinx/serialization/modules/SerialModule;", "getJson", "()Lkotlinx/serialization/json/Json;", "updateMode", "Lkotlinx/serialization/UpdateMode;", "updateMode$annotations", "()V", "getUpdateMode", "()Lkotlinx/serialization/UpdateMode;", "getValue", "()Lkotlinx/serialization/json/JsonElement;", "beginStructure", "Lkotlinx/serialization/CompositeDecoder;", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "typeParams", "", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/SerialDescriptor;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/CompositeDecoder;", "composeName", "", "parentName", "childName", "currentElement", "tag", "currentObject", "decodeJson", "decodeSerializableValue", ExifInterface.GPS_DIRECTION_TRUE, "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "decodeTaggedBoolean", "", "decodeTaggedByte", "", "decodeTaggedChar", "", "decodeTaggedDouble", "", "decodeTaggedEnum", "", "enumDescription", "decodeTaggedFloat", "", "decodeTaggedInt", "decodeTaggedLong", "", "decodeTaggedNotNullMark", "decodeTaggedNull", "", "decodeTaggedShort", "", "decodeTaggedString", "decodeTaggedUnit", "", "endStructure", "Lkotlinx/serialization/json/JsonPrimitive;", TreeJsonOutputKt.PRIMITIVE_TAG, "", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/serialization/json/JsonPrimitive;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Lkotlinx/serialization/json/internal/JsonPrimitiveInput;", "Lkotlinx/serialization/json/internal/JsonTreeInput;", "Lkotlinx/serialization/json/internal/JsonTreeListInput;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class AbstractJsonTreeInput extends NamedValueDecoder implements JsonInput {
    @JvmField
    @NotNull
    protected final JsonConfiguration configuration;
    @NotNull
    private final Json json;
    @NotNull
    private final JsonElement value;

    public /* synthetic */ AbstractJsonTreeInput(Json json, JsonElement jsonElement, DefaultConstructorMarker defaultConstructorMarker) {
        this(json, jsonElement);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JsonElement currentObject() {
        JsonElement currentElement;
        String currentTagOrNull = getCurrentTagOrNull();
        return (currentTagOrNull == null || (currentElement = currentElement(currentTagOrNull)) == null) ? mo12417getValue() : currentElement;
    }

    private final <T> T primitive(@NotNull JsonPrimitive jsonPrimitive, String str, Function1<? super JsonPrimitive, ? extends T> function1) {
        return function1.mo12165invoke(jsonPrimitive);
    }

    public static /* synthetic */ void updateMode$annotations() {
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.Decoder
    @NotNull
    public CompositeDecoder beginStructure(@NotNull SerialDescriptor descriptor, @NotNull KSerializer<?>... typeParams) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(typeParams, "typeParams");
        JsonElement currentObject = currentObject();
        SerialKind mo12397getKind = descriptor.mo12397getKind();
        if (Intrinsics.areEqual(mo12397getKind, StructureKind.LIST.INSTANCE) || (mo12397getKind instanceof PolymorphicKind)) {
            Json json = getJson();
            if (currentObject instanceof JsonArray) {
                return new JsonTreeListInput(json, (JsonArray) currentObject);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected ");
            outline107.append(Reflection.getOrCreateKotlinClass(JsonArray.class));
            outline107.append(" but found ");
            outline107.append(Reflection.getOrCreateKotlinClass(currentObject.getClass()));
            throw new IllegalStateException(outline107.toString().toString());
        } else if (Intrinsics.areEqual(mo12397getKind, StructureKind.MAP.INSTANCE)) {
            Json json2 = getJson();
            SerialDescriptor elementDescriptor = descriptor.getElementDescriptor(0);
            SerialKind mo12397getKind2 = elementDescriptor.mo12397getKind();
            if (!(mo12397getKind2 instanceof PrimitiveKind) && !Intrinsics.areEqual(mo12397getKind2, UnionKind.ENUM_KIND.INSTANCE)) {
                if (json2.configuration.getAllowStructuredMapKeys$kotlinx_serialization_runtime()) {
                    Json json3 = getJson();
                    if (currentObject instanceof JsonArray) {
                        return new JsonTreeListInput(json3, (JsonArray) currentObject);
                    }
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Expected ");
                    outline1072.append(Reflection.getOrCreateKotlinClass(JsonArray.class));
                    outline1072.append(" but found ");
                    outline1072.append(Reflection.getOrCreateKotlinClass(currentObject.getClass()));
                    throw new IllegalStateException(outline1072.toString().toString());
                }
                throw JsonExceptionsKt.InvalidKeyKindException(elementDescriptor);
            }
            Json json4 = getJson();
            if (currentObject instanceof JsonObject) {
                return new JsonTreeMapInput(json4, (JsonObject) currentObject);
            }
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Expected ");
            outline1073.append(Reflection.getOrCreateKotlinClass(JsonObject.class));
            outline1073.append(" but found ");
            outline1073.append(Reflection.getOrCreateKotlinClass(currentObject.getClass()));
            throw new IllegalStateException(outline1073.toString().toString());
        } else {
            Json json5 = getJson();
            if (currentObject instanceof JsonObject) {
                return new JsonTreeInput(json5, (JsonObject) currentObject);
            }
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Expected ");
            outline1074.append(Reflection.getOrCreateKotlinClass(JsonObject.class));
            outline1074.append(" but found ");
            outline1074.append(Reflection.getOrCreateKotlinClass(currentObject.getClass()));
            throw new IllegalStateException(outline1074.toString().toString());
        }
    }

    @Override // kotlinx.serialization.internal.NamedValueDecoder
    @NotNull
    public String composeName(@NotNull String parentName, @NotNull String childName) {
        Intrinsics.checkParameterIsNotNull(parentName, "parentName");
        Intrinsics.checkParameterIsNotNull(childName, "childName");
        return childName;
    }

    @NotNull
    protected abstract JsonElement currentElement(@NotNull String str);

    @Override // kotlinx.serialization.json.JsonInput
    @NotNull
    public JsonElement decodeJson() {
        return currentObject();
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.Decoder
    public <T> T decodeSerializableValue(@NotNull DeserializationStrategy<T> deserializer) {
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        return (T) PolymorphicKt.decodeSerializableValuePolymorphic(this, deserializer);
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    @Nullable
    public Void decodeTaggedNull(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        return null;
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public void decodeTaggedUnit(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.CompositeDecoder
    public void endStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.Decoder, kotlinx.serialization.CompositeDecoder
    @NotNull
    public SerialModule getContext() {
        return getJson().getContext();
    }

    @Override // kotlinx.serialization.json.JsonInput
    @NotNull
    public Json getJson() {
        return this.json;
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.Decoder, kotlinx.serialization.CompositeDecoder
    @NotNull
    public UpdateMode getUpdateMode() {
        return this.configuration.getUpdateMode$kotlinx_serialization_runtime();
    }

    @NotNull
    /* renamed from: getValue */
    public JsonElement mo12417getValue() {
        return this.value;
    }

    private AbstractJsonTreeInput(Json json, JsonElement jsonElement) {
        super(null, 1, null);
        this.json = json;
        this.value = jsonElement;
        this.configuration = getJson().configuration;
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public boolean decodeTaggedBoolean(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        JsonPrimitive value = getValue(tag);
        if (!getJson().configuration.isLenient$kotlinx_serialization_runtime()) {
            if (value != null) {
                if (((JsonLiteral) value).isString()) {
                    throw JsonExceptionsKt.JsonDecodingException(-1, GeneratedOutlineSupport1.outline75("Boolean literal for key '", tag, "' should be unquoted. Use 'JsonConfiguration.isLenient = true' to accept non-compliant JSON"), currentObject().toString());
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.json.JsonLiteral");
            }
        }
        return value.getBoolean();
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public byte decodeTaggedByte(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        return (byte) getValue(tag).getInt();
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public char decodeTaggedChar(@NotNull String tag) {
        char single;
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        single = StringsKt___StringsKt.single(getValue(tag).getContent());
        return single;
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public double decodeTaggedDouble(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        return getValue(tag).getDouble();
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public int decodeTaggedEnum(@NotNull String tag, @NotNull SerialDescriptor enumDescription) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(enumDescription, "enumDescription");
        return SerialDescriptorKt.getElementIndexOrThrow(enumDescription, getValue(tag).getContent());
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public float decodeTaggedFloat(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        return getValue(tag).getFloat();
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public int decodeTaggedInt(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        return getValue(tag).getInt();
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public long decodeTaggedLong(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        return getValue(tag).getLong();
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public boolean decodeTaggedNotNullMark(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        return currentElement(tag) != JsonNull.INSTANCE;
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    public short decodeTaggedShort(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        return (short) getValue(tag).getInt();
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder
    @NotNull
    public String decodeTaggedString(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        JsonPrimitive value = getValue(tag);
        if (!getJson().configuration.isLenient$kotlinx_serialization_runtime()) {
            if (value != null) {
                if (!((JsonLiteral) value).isString()) {
                    throw JsonExceptionsKt.JsonDecodingException(-1, GeneratedOutlineSupport1.outline75("String literal for key '", tag, "' should be quoted. Use 'JsonConfiguration.isLenient = true' to accept non-compliant JSON"), currentObject().toString());
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.json.JsonLiteral");
            }
        }
        return value.getContent();
    }

    @NotNull
    protected JsonPrimitive getValue(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        JsonElement currentElement = currentElement(tag);
        JsonPrimitive jsonPrimitive = (JsonPrimitive) (!(currentElement instanceof JsonPrimitive) ? null : currentElement);
        if (jsonPrimitive != null) {
            return jsonPrimitive;
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected JsonPrimitive at " + tag + ", found " + currentElement, currentObject().toString());
    }
}
