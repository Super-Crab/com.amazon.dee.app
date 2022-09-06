package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.Nullable;
/* compiled from: AbstractTypeAliasDescriptor.kt */
/* loaded from: classes2.dex */
final class AbstractTypeAliasDescriptor$computeDefaultType$1 extends Lambda implements Function1<KotlinTypeRefiner, SimpleType> {
    final /* synthetic */ AbstractTypeAliasDescriptor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractTypeAliasDescriptor$computeDefaultType$1(AbstractTypeAliasDescriptor abstractTypeAliasDescriptor) {
        super(1);
        this.this$0 = abstractTypeAliasDescriptor;
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final SimpleType mo12165invoke(KotlinTypeRefiner kotlinTypeRefiner) {
        ClassifierDescriptor mo12130refineDescriptor = kotlinTypeRefiner.mo12130refineDescriptor(this.this$0);
        if (mo12130refineDescriptor != null) {
            return mo12130refineDescriptor.getDefaultType();
        }
        return null;
    }
}
