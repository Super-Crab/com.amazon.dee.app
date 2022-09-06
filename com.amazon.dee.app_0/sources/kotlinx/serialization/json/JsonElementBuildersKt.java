package kotlinx.serialization.json;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: JsonElementBuilders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006\u001a\u001f\u0010\u0007\u001a\u00020\b2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006¨\u0006\n"}, d2 = {"json", "Lkotlinx/serialization/json/JsonObject;", "init", "Lkotlin/Function1;", "Lkotlinx/serialization/json/JsonObjectBuilder;", "", "Lkotlin/ExtensionFunctionType;", "jsonArray", "Lkotlinx/serialization/json/JsonArray;", "Lkotlinx/serialization/json/JsonArrayBuilder;", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class JsonElementBuildersKt {
    @NotNull
    public static final JsonObject json(@NotNull Function1<? super JsonObjectBuilder, Unit> init) {
        Intrinsics.checkParameterIsNotNull(init, "init");
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        init.mo12165invoke(jsonObjectBuilder);
        return new JsonObject(jsonObjectBuilder.getContent$kotlinx_serialization_runtime());
    }

    @NotNull
    public static final JsonArray jsonArray(@NotNull Function1<? super JsonArrayBuilder, Unit> init) {
        Intrinsics.checkParameterIsNotNull(init, "init");
        JsonArrayBuilder jsonArrayBuilder = new JsonArrayBuilder();
        init.mo12165invoke(jsonArrayBuilder);
        return new JsonArray(jsonArrayBuilder.getContent$kotlinx_serialization_runtime());
    }
}
