package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SubstitutingScope.kt */
/* loaded from: classes4.dex */
public final class SubstitutingScope$_allDescriptors$2 extends Lambda implements Function0<Collection<? extends DeclarationDescriptor>> {
    final /* synthetic */ SubstitutingScope this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubstitutingScope$_allDescriptors$2(SubstitutingScope substitutingScope) {
        super(0);
        this.this$0 = substitutingScope;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final Collection<? extends DeclarationDescriptor> mo12560invoke() {
        MemberScope memberScope;
        Collection<? extends DeclarationDescriptor> substitute;
        SubstitutingScope substitutingScope = this.this$0;
        memberScope = substitutingScope.workerScope;
        substitute = substitutingScope.substitute(ResolutionScope.DefaultImpls.getContributedDescriptors$default(memberScope, null, null, 3, null));
        return substitute;
    }
}
