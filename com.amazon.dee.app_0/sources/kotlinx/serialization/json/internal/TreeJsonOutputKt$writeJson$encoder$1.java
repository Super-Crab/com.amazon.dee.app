package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.serialization.json.JsonElement;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TreeJsonOutput.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "it", "Lkotlinx/serialization/json/JsonElement;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TreeJsonOutputKt$writeJson$encoder$1 extends Lambda implements Function1<JsonElement, Unit> {
    final /* synthetic */ Ref.ObjectRef $result;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TreeJsonOutputKt$writeJson$encoder$1(Ref.ObjectRef objectRef) {
        super(1);
        this.$result = objectRef;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(JsonElement jsonElement) {
        invoke2(jsonElement);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull JsonElement it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        this.$result.element = it2;
    }
}
