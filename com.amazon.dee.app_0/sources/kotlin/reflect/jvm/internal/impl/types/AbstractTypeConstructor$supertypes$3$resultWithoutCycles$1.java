package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: AbstractTypeConstructor.kt */
/* loaded from: classes4.dex */
final class AbstractTypeConstructor$supertypes$3$resultWithoutCycles$1 extends Lambda implements Function1<TypeConstructor, Collection<? extends KotlinType>> {
    final /* synthetic */ AbstractTypeConstructor$supertypes$3 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractTypeConstructor$supertypes$3$resultWithoutCycles$1(AbstractTypeConstructor$supertypes$3 abstractTypeConstructor$supertypes$3) {
        super(1);
        this.this$0 = abstractTypeConstructor$supertypes$3;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Collection<KotlinType> mo12165invoke(@NotNull TypeConstructor it2) {
        Collection<KotlinType> computeNeighbours;
        Intrinsics.checkParameterIsNotNull(it2, "it");
        computeNeighbours = this.this$0.this$0.computeNeighbours(it2, false);
        return computeNeighbours;
    }
}
