package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PolymorphicKind;
import kotlinx.serialization.PrimitiveKind;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialKind;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.StructureKind;
import kotlinx.serialization.UnionKind;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.internal.NamedValueEncoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementSerializer;
import kotlinx.serialization.json.JsonElementsKt;
import kotlinx.serialization.json.JsonExceptionsKt;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonOutput;
import kotlinx.serialization.modules.SerialModule;
import org.jetbrains.annotations.NotNull;
/* compiled from: TreeJsonOutput.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u00012\u00020\u0002B#\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\u0010\tJ1\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u001a\u0010\u001a\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u001c0\u001b\"\u0006\u0012\u0002\b\u00030\u001cH\u0016¢\u0006\u0002\u0010\u001dJ\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001fH\u0016J\u0010\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\u0007H\u0016J)\u0010$\u001a\u00020\b\"\u0004\b\u0000\u0010%2\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H%0'2\u0006\u0010(\u001a\u0002H%H\u0016¢\u0006\u0002\u0010)J\u0018\u0010*\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020\u0015H\u0016J\u0018\u0010,\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020-H\u0016J\u0018\u0010.\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020/H\u0016J\u0018\u00100\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010(\u001a\u000201H\u0016J \u00102\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u001f2\u0006\u00103\u001a\u00020\u00192\u0006\u00104\u001a\u000205H\u0016J\u0018\u00106\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010(\u001a\u000207H\u0016J\u0018\u00108\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010(\u001a\u000205H\u0016J\u0018\u00109\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020:H\u0016J\u0010\u0010;\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u001fH\u0016J\u0018\u0010<\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020=H\u0016J\u0018\u0010>\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020\u001fH\u0016J\u0018\u0010?\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020@H\u0016J\u0010\u0010A\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010B\u001a\u00020\u0007H&J\u0018\u0010C\u001a\u00020\b2\u0006\u0010D\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u0007H&J\u0018\u0010E\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010F\u001a\u000205H\u0016R\u0010\u0010\n\u001a\u00020\u000b8\u0004X\u0085\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0001\u0003GHI¨\u0006J"}, d2 = {"Lkotlinx/serialization/json/internal/AbstractJsonTreeOutput;", "Lkotlinx/serialization/internal/NamedValueEncoder;", "Lkotlinx/serialization/json/JsonOutput;", "json", "Lkotlinx/serialization/json/Json;", "nodeConsumer", "Lkotlin/Function1;", "Lkotlinx/serialization/json/JsonElement;", "", "(Lkotlinx/serialization/json/Json;Lkotlin/jvm/functions/Function1;)V", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lkotlinx/serialization/json/JsonConfiguration;", "context", "Lkotlinx/serialization/modules/SerialModule;", "getContext", "()Lkotlinx/serialization/modules/SerialModule;", "getJson", "()Lkotlinx/serialization/json/Json;", "getNodeConsumer", "()Lkotlin/jvm/functions/Function1;", "writePolymorphic", "", "beginStructure", "Lkotlinx/serialization/CompositeEncoder;", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "typeSerializers", "", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/SerialDescriptor;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/CompositeEncoder;", "composeName", "", "parentName", "childName", "encodeJson", "element", "encodeSerializableValue", ExifInterface.GPS_DIRECTION_TRUE, "serializer", "Lkotlinx/serialization/SerializationStrategy;", "value", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "encodeTaggedBoolean", "tag", "encodeTaggedByte", "", "encodeTaggedChar", "", "encodeTaggedDouble", "", "encodeTaggedEnum", "enumDescription", "ordinal", "", "encodeTaggedFloat", "", "encodeTaggedInt", "encodeTaggedLong", "", "encodeTaggedNull", "encodeTaggedShort", "", "encodeTaggedString", "encodeTaggedValue", "", "endEncode", "getCurrent", "putElement", "key", "shouldEncodeElementDefault", "index", "Lkotlinx/serialization/json/internal/JsonPrimitiveOutput;", "Lkotlinx/serialization/json/internal/JsonTreeOutput;", "Lkotlinx/serialization/json/internal/JsonTreeListOutput;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
abstract class AbstractJsonTreeOutput extends NamedValueEncoder implements JsonOutput {
    @JvmField
    @NotNull
    protected final JsonConfiguration configuration;
    @NotNull
    private final Json json;
    @NotNull
    private final Function1<JsonElement, Unit> nodeConsumer;
    private boolean writePolymorphic;

    public /* synthetic */ AbstractJsonTreeOutput(Json json, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(json, function1);
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.Encoder
    @NotNull
    public CompositeEncoder beginStructure(@NotNull SerialDescriptor descriptor, @NotNull KSerializer<?>... typeSerializers) {
        AbstractJsonTreeOutput jsonTreeListOutput;
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(typeSerializers, "typeSerializers");
        Function1 abstractJsonTreeOutput$beginStructure$consumer$1 = getCurrentTagOrNull() == null ? this.nodeConsumer : new AbstractJsonTreeOutput$beginStructure$consumer$1(this);
        SerialKind mo12397getKind = descriptor.mo12397getKind();
        if (Intrinsics.areEqual(mo12397getKind, StructureKind.LIST.INSTANCE) || (mo12397getKind instanceof PolymorphicKind)) {
            jsonTreeListOutput = new JsonTreeListOutput(this.json, abstractJsonTreeOutput$beginStructure$consumer$1);
        } else if (Intrinsics.areEqual(mo12397getKind, StructureKind.MAP.INSTANCE)) {
            Json json = this.json;
            SerialDescriptor elementDescriptor = descriptor.getElementDescriptor(0);
            SerialKind mo12397getKind2 = elementDescriptor.mo12397getKind();
            if (!(mo12397getKind2 instanceof PrimitiveKind) && !Intrinsics.areEqual(mo12397getKind2, UnionKind.ENUM_KIND.INSTANCE)) {
                if (json.configuration.getAllowStructuredMapKeys$kotlinx_serialization_runtime()) {
                    jsonTreeListOutput = new JsonTreeListOutput(this.json, abstractJsonTreeOutput$beginStructure$consumer$1);
                } else {
                    throw JsonExceptionsKt.InvalidKeyKindException(elementDescriptor);
                }
            } else {
                jsonTreeListOutput = new JsonTreeMapOutput(this.json, abstractJsonTreeOutput$beginStructure$consumer$1);
            }
        } else {
            jsonTreeListOutput = new JsonTreeOutput(this.json, abstractJsonTreeOutput$beginStructure$consumer$1);
        }
        if (this.writePolymorphic) {
            this.writePolymorphic = false;
            jsonTreeListOutput.putElement(this.configuration.getClassDiscriminator$kotlinx_serialization_runtime(), JsonElementsKt.JsonPrimitive(descriptor.getSerialName()));
        }
        return jsonTreeListOutput;
    }

    @Override // kotlinx.serialization.internal.NamedValueEncoder
    @NotNull
    public String composeName(@NotNull String parentName, @NotNull String childName) {
        Intrinsics.checkParameterIsNotNull(parentName, "parentName");
        Intrinsics.checkParameterIsNotNull(childName, "childName");
        return childName;
    }

    @Override // kotlinx.serialization.json.JsonOutput
    public void encodeJson(@NotNull JsonElement element) {
        Intrinsics.checkParameterIsNotNull(element, "element");
        encodeSerializableValue(JsonElementSerializer.INSTANCE, element);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.Encoder
    public <T> void encodeSerializableValue(@NotNull SerializationStrategy<? super T> serializer, T t) {
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        if (getCurrentTagOrNull() == null && ((serializer.getDescriptor().mo12397getKind() instanceof PrimitiveKind) || serializer.getDescriptor().mo12397getKind() == UnionKind.ENUM_KIND.INSTANCE)) {
            JsonPrimitiveOutput jsonPrimitiveOutput = new JsonPrimitiveOutput(this.json, this.nodeConsumer);
            jsonPrimitiveOutput.encodeSerializableValue(serializer, t);
            jsonPrimitiveOutput.endEncode(serializer.getDescriptor());
        } else if ((serializer instanceof AbstractPolymorphicSerializer) && !getJson().configuration.getUseArrayPolymorphism$kotlinx_serialization_runtime()) {
            AbstractPolymorphicSerializer abstractPolymorphicSerializer = (AbstractPolymorphicSerializer) serializer;
            if (t != 0) {
                KSerializer<? extends T> findPolymorphicSerializer = abstractPolymorphicSerializer.findPolymorphicSerializer((Encoder) this, (AbstractJsonTreeOutput) t);
                if (findPolymorphicSerializer != null) {
                    PolymorphicKt.validateIfSealed((KSerializer) serializer, findPolymorphicSerializer, getJson().configuration.getClassDiscriminator$kotlinx_serialization_runtime());
                    PolymorphicKt.checkKind(findPolymorphicSerializer.getDescriptor().mo12397getKind());
                    this.writePolymorphic = true;
                    findPolymorphicSerializer.serialize(this, t);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Any");
        } else {
            serializer.serialize(this, t);
        }
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void endEncode(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        this.nodeConsumer.mo12165invoke(getCurrent());
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.Encoder, kotlinx.serialization.CompositeEncoder
    @NotNull
    public final SerialModule getContext() {
        return this.json.getContext();
    }

    @NotNull
    public abstract JsonElement getCurrent();

    @Override // kotlinx.serialization.json.JsonOutput
    @NotNull
    public final Json getJson() {
        return this.json;
    }

    @NotNull
    public final Function1<JsonElement, Unit> getNodeConsumer() {
        return this.nodeConsumer;
    }

    public abstract void putElement(@NotNull String str, @NotNull JsonElement jsonElement);

    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.CompositeEncoder
    public boolean shouldEncodeElementDefault(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        return this.configuration.getEncodeDefaults$kotlinx_serialization_runtime();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private AbstractJsonTreeOutput(Json json, Function1<? super JsonElement, Unit> function1) {
        super(null, 1, null);
        this.json = json;
        this.nodeConsumer = function1;
        this.configuration = this.json.configuration;
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedBoolean(@NotNull String tag, boolean z) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        putElement(tag, new JsonLiteral(z));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedByte(@NotNull String tag, byte b) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        putElement(tag, new JsonLiteral(Byte.valueOf(b)));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedChar(@NotNull String tag, char c) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        putElement(tag, new JsonLiteral(String.valueOf(c)));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedDouble(@NotNull String tag, double d) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        putElement(tag, new JsonLiteral(Double.valueOf(d)));
        if (!this.configuration.getSerializeSpecialFloatingPointValues$kotlinx_serialization_runtime()) {
            if (!(!Double.isInfinite(d) && !Double.isNaN(d))) {
                throw JsonExceptionsKt.InvalidFloatingPoint(Double.valueOf(d), tag, "double", getCurrent().toString());
            }
        }
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedEnum(@NotNull String tag, @NotNull SerialDescriptor enumDescription, int i) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(enumDescription, "enumDescription");
        putElement(tag, new JsonLiteral(enumDescription.getElementName(i)));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedFloat(@NotNull String tag, float f) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        putElement(tag, new JsonLiteral(Float.valueOf(f)));
        if (!this.configuration.getSerializeSpecialFloatingPointValues$kotlinx_serialization_runtime()) {
            if (!(!Float.isInfinite(f) && !Float.isNaN(f))) {
                throw JsonExceptionsKt.InvalidFloatingPoint(Float.valueOf(f), tag, "float", getCurrent().toString());
            }
        }
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedInt(@NotNull String tag, int i) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        putElement(tag, new JsonLiteral(Integer.valueOf(i)));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedLong(@NotNull String tag, long j) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        putElement(tag, new JsonLiteral(Long.valueOf(j)));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedNull(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        putElement(tag, JsonNull.INSTANCE);
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedShort(@NotNull String tag, short s) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        putElement(tag, new JsonLiteral(Short.valueOf(s)));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedString(@NotNull String tag, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(value, "value");
        putElement(tag, new JsonLiteral(value));
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedValue(@NotNull String tag, @NotNull Object value) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(value, "value");
        putElement(tag, new JsonLiteral(value.toString()));
    }
}
