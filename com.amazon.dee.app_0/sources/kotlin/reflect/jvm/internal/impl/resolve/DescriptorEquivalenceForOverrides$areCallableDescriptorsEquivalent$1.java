package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import org.jetbrains.annotations.Nullable;
/* compiled from: DescriptorEquivalenceForOverrides.kt */
/* loaded from: classes4.dex */
final class DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$1 extends Lambda implements Function2<DeclarationDescriptor, DeclarationDescriptor, Boolean> {
    public static final DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$1 INSTANCE = new DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$1();

    DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12248invoke(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
        return Boolean.valueOf(invoke2(declarationDescriptor, declarationDescriptor2));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(@Nullable DeclarationDescriptor declarationDescriptor, @Nullable DeclarationDescriptor declarationDescriptor2) {
        return false;
    }
}
