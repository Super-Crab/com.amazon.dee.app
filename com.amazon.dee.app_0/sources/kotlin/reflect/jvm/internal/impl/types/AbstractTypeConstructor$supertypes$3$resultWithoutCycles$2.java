package kotlin.reflect.jvm.internal.impl.types;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: AbstractTypeConstructor.kt */
/* loaded from: classes4.dex */
final class AbstractTypeConstructor$supertypes$3$resultWithoutCycles$2 extends Lambda implements Function1<KotlinType, Unit> {
    final /* synthetic */ AbstractTypeConstructor$supertypes$3 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractTypeConstructor$supertypes$3$resultWithoutCycles$2(AbstractTypeConstructor$supertypes$3 abstractTypeConstructor$supertypes$3) {
        super(1);
        this.this$0 = abstractTypeConstructor$supertypes$3;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(KotlinType kotlinType) {
        invoke2(kotlinType);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull KotlinType it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        this.this$0.this$0.reportSupertypeLoopError(it2);
    }
}
