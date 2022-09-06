package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.DecodingKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.NotNull;
/* compiled from: TreeJsonInput.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006H\u0000¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"readJson", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/serialization/json/Json;", "element", "Lkotlinx/serialization/json/JsonElement;", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/JsonElement;Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TreeJsonInputKt {
    public static final <T> T readJson(@NotNull Json readJson, @NotNull JsonElement element, @NotNull DeserializationStrategy<T> deserializer) {
        Decoder jsonPrimitiveInput;
        Intrinsics.checkParameterIsNotNull(readJson, "$this$readJson");
        Intrinsics.checkParameterIsNotNull(element, "element");
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        if (element instanceof JsonObject) {
            jsonPrimitiveInput = new JsonTreeInput(readJson, (JsonObject) element);
        } else if (element instanceof JsonArray) {
            jsonPrimitiveInput = new JsonTreeListInput(readJson, (JsonArray) element);
        } else if (!(element instanceof JsonLiteral) && !Intrinsics.areEqual(element, JsonNull.INSTANCE)) {
            throw new NoWhenBranchMatchedException();
        } else {
            jsonPrimitiveInput = new JsonPrimitiveInput(readJson, (JsonPrimitive) element);
        }
        return (T) DecodingKt.decode(jsonPrimitiveInput, deserializer);
    }
}
