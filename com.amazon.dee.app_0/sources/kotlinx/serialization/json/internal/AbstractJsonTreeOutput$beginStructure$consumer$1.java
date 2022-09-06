package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonElement;
import org.jetbrains.annotations.NotNull;
/* compiled from: TreeJsonOutput.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "node", "Lkotlinx/serialization/json/JsonElement;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class AbstractJsonTreeOutput$beginStructure$consumer$1 extends Lambda implements Function1<JsonElement, Unit> {
    final /* synthetic */ AbstractJsonTreeOutput this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractJsonTreeOutput$beginStructure$consumer$1(AbstractJsonTreeOutput abstractJsonTreeOutput) {
        super(1);
        this.this$0 = abstractJsonTreeOutput;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(JsonElement jsonElement) {
        invoke2(jsonElement);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull JsonElement node) {
        String currentTag;
        Intrinsics.checkParameterIsNotNull(node, "node");
        AbstractJsonTreeOutput abstractJsonTreeOutput = this.this$0;
        currentTag = abstractJsonTreeOutput.getCurrentTag();
        abstractJsonTreeOutput.putElement(currentTag, node);
    }
}
