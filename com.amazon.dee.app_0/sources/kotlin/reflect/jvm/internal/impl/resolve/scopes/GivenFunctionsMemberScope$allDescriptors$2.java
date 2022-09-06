package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GivenFunctionsMemberScope.kt */
/* loaded from: classes4.dex */
public final class GivenFunctionsMemberScope$allDescriptors$2 extends Lambda implements Function0<List<? extends DeclarationDescriptor>> {
    final /* synthetic */ GivenFunctionsMemberScope this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GivenFunctionsMemberScope$allDescriptors$2(GivenFunctionsMemberScope givenFunctionsMemberScope) {
        super(0);
        this.this$0 = givenFunctionsMemberScope;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final List<? extends DeclarationDescriptor> mo12560invoke() {
        List createFakeOverrides;
        List<? extends DeclarationDescriptor> plus;
        List<FunctionDescriptor> computeDeclaredFunctions = this.this$0.computeDeclaredFunctions();
        createFakeOverrides = this.this$0.createFakeOverrides(computeDeclaredFunctions);
        plus = CollectionsKt___CollectionsKt.plus((Collection) computeDeclaredFunctions, (Iterable) createFakeOverrides);
        return plus;
    }
}
