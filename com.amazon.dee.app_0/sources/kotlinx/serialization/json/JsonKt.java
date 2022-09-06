package kotlinx.serialization.json;

import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.modules.SerialModule;
import kotlinx.serialization.modules.SerialModuleBuildersKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Json.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\"\u0016\u0010\u0000\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"defaultJsonModule", "Lkotlinx/serialization/modules/SerialModule;", "defaultJsonModule$annotations", "()V", "lenientHint", "", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class JsonKt {
    private static final SerialModule defaultJsonModule;
    @NotNull
    public static final String lenientHint = "Use 'JsonConfiguration.isLenient = true' to accept non-compliant JSON";

    static {
        Map mapOf;
        mapOf = MapsKt__MapsKt.mapOf(TuplesKt.to(Reflection.getOrCreateKotlinClass(JsonElement.class), JsonElementSerializer.INSTANCE), TuplesKt.to(Reflection.getOrCreateKotlinClass(JsonPrimitive.class), JsonPrimitiveSerializer.INSTANCE), TuplesKt.to(Reflection.getOrCreateKotlinClass(JsonLiteral.class), JsonLiteralSerializer.INSTANCE), TuplesKt.to(Reflection.getOrCreateKotlinClass(JsonNull.class), JsonNullSerializer.INSTANCE), TuplesKt.to(Reflection.getOrCreateKotlinClass(JsonObject.class), JsonObjectSerializer.INSTANCE), TuplesKt.to(Reflection.getOrCreateKotlinClass(JsonArray.class), JsonArraySerializer.INSTANCE));
        defaultJsonModule = SerialModuleBuildersKt.serializersModuleOf(mapOf);
    }

    private static /* synthetic */ void defaultJsonModule$annotations() {
    }
}
