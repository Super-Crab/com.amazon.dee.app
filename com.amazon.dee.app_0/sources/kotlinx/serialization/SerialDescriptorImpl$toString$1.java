package kotlinx.serialization;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialDescriptorBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class SerialDescriptorImpl$toString$1 extends Lambda implements Function1<Integer, String> {
    final /* synthetic */ SerialDescriptorImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SerialDescriptorImpl$toString$1(SerialDescriptorImpl serialDescriptorImpl) {
        super(1);
        this.this$0 = serialDescriptorImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ String mo12165invoke(Integer num) {
        return invoke(num.intValue());
    }

    @NotNull
    public final String invoke(int i) {
        return this.this$0.getElementName(i) + RealTimeTextConstants.COLON_SPACE + this.this$0.getElementDescriptor(i).getSerialName();
    }
}
