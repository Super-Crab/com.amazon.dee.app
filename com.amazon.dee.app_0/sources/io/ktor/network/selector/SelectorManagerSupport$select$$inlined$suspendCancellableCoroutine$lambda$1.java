package io.ktor.network.selector;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* compiled from: SelectorManagerSupport.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "io/ktor/network/selector/SelectorManagerSupport$select$2$1"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class SelectorManagerSupport$select$$inlined$suspendCancellableCoroutine$lambda$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ SelectInterest $interest$inlined;
    final /* synthetic */ Selectable $selectable$inlined;
    final /* synthetic */ SelectorManagerSupport this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectorManagerSupport$select$$inlined$suspendCancellableCoroutine$lambda$1(SelectorManagerSupport selectorManagerSupport, Selectable selectable, SelectInterest selectInterest) {
        super(1);
        this.this$0 = selectorManagerSupport;
        this.$selectable$inlined = selectable;
        this.$interest$inlined = selectInterest;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@Nullable Throwable th) {
        this.$selectable$inlined.dispose();
    }
}
