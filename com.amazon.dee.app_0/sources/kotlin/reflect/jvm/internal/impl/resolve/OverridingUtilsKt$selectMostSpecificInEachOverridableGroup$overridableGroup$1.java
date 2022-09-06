package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;
/* compiled from: overridingUtils.kt */
/* loaded from: classes4.dex */
final class OverridingUtilsKt$selectMostSpecificInEachOverridableGroup$overridableGroup$1 extends Lambda implements Function1<H, Unit> {
    final /* synthetic */ SmartSet $conflictedHandles;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OverridingUtilsKt$selectMostSpecificInEachOverridableGroup$overridableGroup$1(SmartSet smartSet) {
        super(1);
        this.$conflictedHandles = smartSet;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Object obj) {
        mo12165invoke((OverridingUtilsKt$selectMostSpecificInEachOverridableGroup$overridableGroup$1) obj);
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke  reason: collision with other method in class */
    public final void mo12165invoke(H it2) {
        SmartSet smartSet = this.$conflictedHandles;
        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
        smartSet.add(it2);
    }
}
