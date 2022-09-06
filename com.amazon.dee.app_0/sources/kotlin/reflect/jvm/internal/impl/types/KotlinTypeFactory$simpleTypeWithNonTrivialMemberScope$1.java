package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KotlinTypeFactory.kt */
/* loaded from: classes4.dex */
public final class KotlinTypeFactory$simpleTypeWithNonTrivialMemberScope$1 extends Lambda implements Function1<KotlinTypeRefiner, SimpleType> {
    final /* synthetic */ Annotations $annotations;
    final /* synthetic */ List $arguments;
    final /* synthetic */ TypeConstructor $constructor;
    final /* synthetic */ MemberScope $memberScope;
    final /* synthetic */ boolean $nullable;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinTypeFactory$simpleTypeWithNonTrivialMemberScope$1(TypeConstructor typeConstructor, List list, Annotations annotations, boolean z, MemberScope memberScope) {
        super(1);
        this.$constructor = typeConstructor;
        this.$arguments = list;
        this.$annotations = annotations;
        this.$nullable = z;
        this.$memberScope = memberScope;
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final SimpleType mo12165invoke(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        KotlinTypeFactory.ExpandedTypeOrRefinedConstructor refineConstructor;
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        refineConstructor = KotlinTypeFactory.INSTANCE.refineConstructor(this.$constructor, kotlinTypeRefiner, this.$arguments);
        if (refineConstructor != null) {
            SimpleType expandedType = refineConstructor.getExpandedType();
            if (expandedType != null) {
                return expandedType;
            }
            Annotations annotations = this.$annotations;
            TypeConstructor refinedConstructor = refineConstructor.getRefinedConstructor();
            if (refinedConstructor == null) {
                Intrinsics.throwNpe();
            }
            return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(annotations, refinedConstructor, this.$arguments, this.$nullable, this.$memberScope);
        }
        return null;
    }
}
