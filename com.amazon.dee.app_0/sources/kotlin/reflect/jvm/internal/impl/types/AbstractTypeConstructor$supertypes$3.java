package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AbstractTypeConstructor.kt */
/* loaded from: classes4.dex */
public final class AbstractTypeConstructor$supertypes$3 extends Lambda implements Function1<AbstractTypeConstructor.Supertypes, Unit> {
    final /* synthetic */ AbstractTypeConstructor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AbstractTypeConstructor.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3$2  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass2 extends Lambda implements Function1<TypeConstructor, Collection<? extends KotlinType>> {
        AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        @NotNull
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Collection<KotlinType> mo12165invoke(@NotNull TypeConstructor it2) {
            Collection<KotlinType> computeNeighbours;
            Intrinsics.checkParameterIsNotNull(it2, "it");
            computeNeighbours = AbstractTypeConstructor$supertypes$3.this.this$0.computeNeighbours(it2, true);
            return computeNeighbours;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AbstractTypeConstructor.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor$supertypes$3$3  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass3 extends Lambda implements Function1<KotlinType, Unit> {
        AnonymousClass3() {
            super(1);
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
            AbstractTypeConstructor$supertypes$3.this.this$0.reportScopesLoopError(it2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractTypeConstructor$supertypes$3(AbstractTypeConstructor abstractTypeConstructor) {
        super(1);
        this.this$0 = abstractTypeConstructor;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(AbstractTypeConstructor.Supertypes supertypes) {
        invoke2(supertypes);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull AbstractTypeConstructor.Supertypes supertypes) {
        Intrinsics.checkParameterIsNotNull(supertypes, "supertypes");
        Collection findLoopsInSupertypesAndDisconnect = this.this$0.getSupertypeLoopChecker().findLoopsInSupertypesAndDisconnect(this.this$0, supertypes.getAllSupertypes(), new AbstractTypeConstructor$supertypes$3$resultWithoutCycles$1(this), new AbstractTypeConstructor$supertypes$3$resultWithoutCycles$2(this));
        Collection collection = null;
        if (findLoopsInSupertypesAndDisconnect.isEmpty()) {
            KotlinType defaultSupertypeIfEmpty = this.this$0.defaultSupertypeIfEmpty();
            findLoopsInSupertypesAndDisconnect = defaultSupertypeIfEmpty != null ? CollectionsKt__CollectionsJVMKt.listOf(defaultSupertypeIfEmpty) : null;
            if (findLoopsInSupertypesAndDisconnect == null) {
                findLoopsInSupertypesAndDisconnect = CollectionsKt__CollectionsKt.emptyList();
            }
        }
        this.this$0.getSupertypeLoopChecker().findLoopsInSupertypesAndDisconnect(this.this$0, findLoopsInSupertypesAndDisconnect, new AnonymousClass2(), new AnonymousClass3());
        if (findLoopsInSupertypesAndDisconnect instanceof List) {
            collection = findLoopsInSupertypesAndDisconnect;
        }
        List<? extends KotlinType> list = (List) collection;
        if (list == null) {
            list = CollectionsKt___CollectionsKt.toList(findLoopsInSupertypesAndDisconnect);
        }
        supertypes.setSupertypesWithoutCycles(list);
    }
}
