package kotlinx.serialization.json;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: JsonElement.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010&\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "<name for destructuring parameter 0>", "", "Lkotlinx/serialization/json/JsonElement;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class JsonObject$toString$1 extends Lambda implements Function1<Map.Entry<? extends String, ? extends JsonElement>, String> {
    public static final JsonObject$toString$1 INSTANCE = new JsonObject$toString$1();

    JsonObject$toString$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ String mo12165invoke(Map.Entry<? extends String, ? extends JsonElement> entry) {
        return invoke2((Map.Entry<String, ? extends JsonElement>) entry);
    }

    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final String invoke2(@NotNull Map.Entry<String, ? extends JsonElement> entry) {
        Intrinsics.checkParameterIsNotNull(entry, "<name for destructuring parameter 0>");
        return '\"' + entry.getKey() + "\":" + entry.getValue();
    }
}
