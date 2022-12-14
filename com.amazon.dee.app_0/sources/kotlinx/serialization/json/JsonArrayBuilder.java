package kotlinx.serialization.json;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JsonElementBuilders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u0014\u0010\b\u001a\u00020\t*\u0004\u0018\u00010\nH\u0086\u0002¢\u0006\u0002\u0010\u000bJ\u000f\u0010\b\u001a\u00020\t*\u0004\u0018\u00010\fH\u0086\u0002J\u000f\u0010\b\u001a\u00020\t*\u0004\u0018\u00010\rH\u0086\u0002J\r\u0010\b\u001a\u00020\t*\u00020\u0005H\u0086\u0002R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lkotlinx/serialization/json/JsonArrayBuilder;", "", "()V", "content", "", "Lkotlinx/serialization/json/JsonElement;", "getContent$kotlinx_serialization_runtime", "()Ljava/util/List;", "unaryPlus", "", "", "(Ljava/lang/Boolean;)V", "", "", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class JsonArrayBuilder {
    @NotNull
    private final List<JsonElement> content = new ArrayList();

    @NotNull
    public final List<JsonElement> getContent$kotlinx_serialization_runtime() {
        return this.content;
    }

    public final void unaryPlus(@Nullable String str) {
        this.content.add(JsonElementsKt.JsonPrimitive(str));
    }

    public final void unaryPlus(@Nullable Number number) {
        this.content.add(JsonElementsKt.JsonPrimitive(number));
    }

    public final void unaryPlus(@Nullable Boolean bool) {
        this.content.add(JsonElementsKt.JsonPrimitive(bool));
    }

    public final void unaryPlus(@NotNull JsonElement unaryPlus) {
        Intrinsics.checkParameterIsNotNull(unaryPlus, "$this$unaryPlus");
        this.content.add(unaryPlus);
    }
}
