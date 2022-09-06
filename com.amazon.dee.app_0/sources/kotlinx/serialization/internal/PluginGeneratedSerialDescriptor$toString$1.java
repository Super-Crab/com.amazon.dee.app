package kotlinx.serialization.internal;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: PluginGeneratedSerialDescriptor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010&\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class PluginGeneratedSerialDescriptor$toString$1 extends Lambda implements Function1<Map.Entry<? extends String, ? extends Integer>, String> {
    final /* synthetic */ PluginGeneratedSerialDescriptor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PluginGeneratedSerialDescriptor$toString$1(PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor) {
        super(1);
        this.this$0 = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ String mo12165invoke(Map.Entry<? extends String, ? extends Integer> entry) {
        return invoke2((Map.Entry<String, Integer>) entry);
    }

    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final String invoke2(@NotNull Map.Entry<String, Integer> it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        return it2.getKey() + RealTimeTextConstants.COLON_SPACE + this.this$0.getElementDescriptor(it2.getValue().intValue()).getSerialName();
    }
}
