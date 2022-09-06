package kotlinx.serialization.json;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.json.internal.StringOpsKt;
import kotlinx.serialization.json.internal.TreeJsonOutputKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JsonElement.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 12\u00020\u0001:\u00011B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u00100\u001a\u00020\u000bH\u0016R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u00188F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b \u0010!R\u0013\u0010\"\u001a\u0004\u0018\u00010\u001f8F¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0013\u0010)\u001a\u0004\u0018\u00010&8F¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0017\u0010,\u001a\u00020\u0000¢\u0006\u000e\n\u0000\u0012\u0004\b-\u0010\u0002\u001a\u0004\b.\u0010/\u0082\u0001\u000223¨\u00064"}, d2 = {"Lkotlinx/serialization/json/JsonPrimitive;", "Lkotlinx/serialization/json/JsonElement;", "()V", "boolean", "", "getBoolean", "()Z", "booleanOrNull", "getBooleanOrNull", "()Ljava/lang/Boolean;", "content", "", "getContent", "()Ljava/lang/String;", "contentOrNull", "getContentOrNull", "double", "", "getDouble", "()D", "doubleOrNull", "getDoubleOrNull", "()Ljava/lang/Double;", "float", "", "getFloat", "()F", "floatOrNull", "getFloatOrNull", "()Ljava/lang/Float;", "int", "", "getInt", "()I", "intOrNull", "getIntOrNull", "()Ljava/lang/Integer;", "long", "", "getLong", "()J", "longOrNull", "getLongOrNull", "()Ljava/lang/Long;", TreeJsonOutputKt.PRIMITIVE_TAG, "primitive$annotations", "getPrimitive", "()Lkotlinx/serialization/json/JsonPrimitive;", "toString", "Companion", "Lkotlinx/serialization/json/JsonLiteral;", "Lkotlinx/serialization/json/JsonNull;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@Serializable(with = JsonPrimitiveSerializer.class)
/* loaded from: classes4.dex */
public abstract class JsonPrimitive extends JsonElement {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final JsonPrimitive primitive;

    /* compiled from: JsonElement.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lkotlinx/serialization/json/JsonPrimitive$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/serialization/json/JsonPrimitive;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<JsonPrimitive> serializer() {
            return JsonPrimitiveSerializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private JsonPrimitive() {
        super(null);
        this.primitive = this;
    }

    public static /* synthetic */ void primitive$annotations() {
    }

    public final boolean getBoolean() {
        return StringOpsKt.toBooleanStrict(getContent());
    }

    @Nullable
    public final Boolean getBooleanOrNull() {
        return StringOpsKt.toBooleanStrictOrNull(getContent());
    }

    @NotNull
    public abstract String getContent();

    @Nullable
    public abstract String getContentOrNull();

    public final double getDouble() {
        return Double.parseDouble(getContent());
    }

    @Nullable
    public final Double getDoubleOrNull() {
        Double doubleOrNull;
        doubleOrNull = StringsKt__StringNumberConversionsJVMKt.toDoubleOrNull(getContent());
        return doubleOrNull;
    }

    public final float getFloat() {
        return Float.parseFloat(getContent());
    }

    @Nullable
    public final Float getFloatOrNull() {
        Float floatOrNull;
        floatOrNull = StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(getContent());
        return floatOrNull;
    }

    public final int getInt() {
        return Integer.parseInt(getContent());
    }

    @Nullable
    public final Integer getIntOrNull() {
        Integer intOrNull;
        intOrNull = StringsKt__StringNumberConversionsKt.toIntOrNull(getContent());
        return intOrNull;
    }

    public final long getLong() {
        return Long.parseLong(getContent());
    }

    @Nullable
    public final Long getLongOrNull() {
        Long longOrNull;
        longOrNull = StringsKt__StringNumberConversionsKt.toLongOrNull(getContent());
        return longOrNull;
    }

    @Override // kotlinx.serialization.json.JsonElement
    @NotNull
    public final JsonPrimitive getPrimitive() {
        return this.primitive;
    }

    @NotNull
    public String toString() {
        return getContent();
    }

    public /* synthetic */ JsonPrimitive(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
