package kotlinx.serialization.json;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.json.internal.StringOpsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JsonElement.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u000f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB\u0017\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0006¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\fH\u0096\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\tH\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0016¨\u0006\u001d"}, d2 = {"Lkotlinx/serialization/json/JsonLiteral;", "Lkotlinx/serialization/json/JsonPrimitive;", "number", "", "(Ljava/lang/Number;)V", "boolean", "", "(Z)V", "string", "", "(Ljava/lang/String;)V", "body", "", "isString", "(Ljava/lang/Object;Z)V", "getBody", "()Ljava/lang/Object;", "content", "getContent", "()Ljava/lang/String;", "contentOrNull", "getContentOrNull", "()Z", "equals", "other", "hashCode", "", "toString", "Companion", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@Serializable(with = JsonLiteralSerializer.class)
/* loaded from: classes4.dex */
public final class JsonLiteral extends JsonPrimitive {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Object body;
    @NotNull
    private final String content;
    @NotNull
    private final String contentOrNull;
    private final boolean isString;

    /* compiled from: JsonElement.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lkotlinx/serialization/json/JsonLiteral$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/serialization/json/JsonLiteral;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<JsonLiteral> serializer() {
            return JsonLiteralSerializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonLiteral(@NotNull Object body, boolean z) {
        super(null);
        Intrinsics.checkParameterIsNotNull(body, "body");
        this.body = body;
        this.isString = z;
        this.content = this.body.toString();
        this.contentOrNull = getContent();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || (!Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(JsonLiteral.class), Reflection.getOrCreateKotlinClass(obj.getClass())))) {
            return false;
        }
        JsonLiteral jsonLiteral = (JsonLiteral) obj;
        return this.isString == jsonLiteral.isString && !(Intrinsics.areEqual(getContent(), jsonLiteral.getContent()) ^ true);
    }

    @NotNull
    public final Object getBody() {
        return this.body;
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    @NotNull
    public String getContent() {
        return this.content;
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    @NotNull
    public String getContentOrNull() {
        return this.contentOrNull;
    }

    public int hashCode() {
        return getContent().hashCode() + (Boolean.valueOf(this.isString).hashCode() * 31);
    }

    public final boolean isString() {
        return this.isString;
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    @NotNull
    public String toString() {
        if (this.isString) {
            StringBuilder sb = new StringBuilder();
            StringOpsKt.printQuoted(sb, getContent());
            String sb2 = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
            return sb2;
        }
        return getContent();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JsonLiteral(@NotNull Number number) {
        this(number, false);
        Intrinsics.checkParameterIsNotNull(number, "number");
    }

    public JsonLiteral(boolean z) {
        this(Boolean.valueOf(z), false);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JsonLiteral(@NotNull String string) {
        this(string, true);
        Intrinsics.checkParameterIsNotNull(string, "string");
    }
}
