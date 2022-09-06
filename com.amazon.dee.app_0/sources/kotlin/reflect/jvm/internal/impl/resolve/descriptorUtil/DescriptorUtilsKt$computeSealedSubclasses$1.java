package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import java.util.LinkedHashSet;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DescriptorUtils.kt */
/* loaded from: classes4.dex */
public final class DescriptorUtilsKt$computeSealedSubclasses$1 extends Lambda implements Function2<MemberScope, Boolean, Unit> {
    final /* synthetic */ LinkedHashSet $result;
    final /* synthetic */ ClassDescriptor $sealedClass;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DescriptorUtilsKt$computeSealedSubclasses$1(ClassDescriptor classDescriptor, LinkedHashSet linkedHashSet) {
        super(2);
        this.$sealedClass = classDescriptor;
        this.$result = linkedHashSet;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12248invoke(MemberScope memberScope, Boolean bool) {
        invoke(memberScope, bool.booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull MemberScope scope, boolean z) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        for (DeclarationDescriptor declarationDescriptor : ResolutionScope.DefaultImpls.getContributedDescriptors$default(scope, DescriptorKindFilter.CLASSIFIERS, null, 2, null)) {
            if (declarationDescriptor instanceof ClassDescriptor) {
                ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
                if (DescriptorUtils.isDirectSubclass(classDescriptor, this.$sealedClass)) {
                    this.$result.add(declarationDescriptor);
                }
                if (z) {
                    MemberScope unsubstitutedInnerClassesScope = classDescriptor.getUnsubstitutedInnerClassesScope();
                    Intrinsics.checkExpressionValueIsNotNull(unsubstitutedInnerClassesScope, "descriptor.unsubstitutedInnerClassesScope");
                    invoke(unsubstitutedInnerClassesScope, z);
                }
            }
        }
    }
}
