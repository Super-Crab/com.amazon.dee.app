package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;
/* compiled from: JvmBuiltInsSettings.kt */
/* loaded from: classes2.dex */
final class JvmBuiltInsSettings$getFunctions$2 extends Lambda implements Function1<MemberScope, Collection<? extends SimpleFunctionDescriptor>> {
    final /* synthetic */ Name $name;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmBuiltInsSettings$getFunctions$2(Name name) {
        super(1);
        this.$name = name;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Collection<? extends SimpleFunctionDescriptor> mo12165invoke(@NotNull MemberScope it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        return it2.mo12099getContributedFunctions(this.$name, NoLookupLocation.FROM_BUILTINS);
    }
}
