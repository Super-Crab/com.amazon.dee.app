package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.EncodingKt;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import org.jetbrains.annotations.NotNull;
/* compiled from: TreeJsonOutput.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0002\u001a\u0002H\u0003\"\n\b\u0000\u0010\u0003\u0018\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0080\b¢\u0006\u0002\u0010\u0006\u001a-\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0003*\u00020\b2\u0006\u0010\u0005\u001a\u0002H\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00030\nH\u0000¢\u0006\u0002\u0010\u000b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"PRIMITIVE_TAG", "", "cast", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/serialization/json/JsonElement;", "value", "(Lkotlinx/serialization/json/JsonElement;)Lkotlinx/serialization/json/JsonElement;", "writeJson", "Lkotlinx/serialization/json/Json;", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/json/Json;Ljava/lang/Object;Lkotlinx/serialization/SerializationStrategy;)Lkotlinx/serialization/json/JsonElement;", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TreeJsonOutputKt {
    @NotNull
    public static final String PRIMITIVE_TAG = "primitive";

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final /* synthetic */ <T extends JsonElement> T cast(@NotNull JsonElement value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
        if (value instanceof JsonElement) {
            return value;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected ");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        sb.append(Reflection.getOrCreateKotlinClass(JsonElement.class));
        sb.append(" but found ");
        sb.append(Reflection.getOrCreateKotlinClass(value.getClass()));
        throw new IllegalStateException(sb.toString().toString());
    }

    @NotNull
    public static final <T> JsonElement writeJson(@NotNull Json writeJson, T t, @NotNull SerializationStrategy<? super T> serializer) {
        Intrinsics.checkParameterIsNotNull(writeJson, "$this$writeJson");
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = null;
        EncodingKt.encode(new JsonTreeOutput(writeJson, new TreeJsonOutputKt$writeJson$encoder$1(objectRef)), serializer, t);
        T t2 = objectRef.element;
        if (t2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("result");
        }
        return (JsonElement) t2;
    }
}
