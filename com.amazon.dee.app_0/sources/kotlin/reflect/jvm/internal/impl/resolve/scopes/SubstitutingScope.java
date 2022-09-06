package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Substitutable;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SubstitutingScope.kt */
/* loaded from: classes4.dex */
public final class SubstitutingScope implements MemberScope {
    private final Lazy _allDescriptors$delegate;
    private Map<DeclarationDescriptor, DeclarationDescriptor> substitutedDescriptors;
    private final TypeSubstitutor substitutor;
    private final MemberScope workerScope;

    public SubstitutingScope(@NotNull MemberScope workerScope, @NotNull TypeSubstitutor givenSubstitutor) {
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull(workerScope, "workerScope");
        Intrinsics.checkParameterIsNotNull(givenSubstitutor, "givenSubstitutor");
        this.workerScope = workerScope;
        TypeSubstitution substitution = givenSubstitutor.getSubstitution();
        Intrinsics.checkExpressionValueIsNotNull(substitution, "givenSubstitutor.substitution");
        this.substitutor = CapturedTypeConstructorKt.wrapWithCapturingSubstitution$default(substitution, false, 1, null).buildSubstitutor();
        lazy = LazyKt__LazyJVMKt.lazy(new SubstitutingScope$_allDescriptors$2(this));
        this._allDescriptors$delegate = lazy;
    }

    private final Collection<DeclarationDescriptor> get_allDescriptors() {
        return (Collection) this._allDescriptors$delegate.getValue();
    }

    private final <D extends DeclarationDescriptor> D substitute(D d) {
        if (this.substitutor.isEmpty()) {
            return d;
        }
        if (this.substitutedDescriptors == null) {
            this.substitutedDescriptors = new HashMap();
        }
        Map<DeclarationDescriptor, DeclarationDescriptor> map = this.substitutedDescriptors;
        if (map == null) {
            Intrinsics.throwNpe();
        }
        DeclarationDescriptor declarationDescriptor = map.get(d);
        if (declarationDescriptor == null) {
            if (!(d instanceof Substitutable)) {
                throw new IllegalStateException(("Unknown descriptor in scope: " + d).toString());
            }
            declarationDescriptor = ((Substitutable) d).mo12098substitute(this.substitutor);
            if (declarationDescriptor != null) {
                map.put(d, declarationDescriptor);
            } else {
                throw new AssertionError("We expect that no conflict should happen while substitution is guaranteed to generate invariant projection, but " + d + " substitution fails");
            }
        }
        return (D) declarationDescriptor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @Nullable
    /* renamed from: getContributedClassifier */
    public ClassifierDescriptor mo12030getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        ClassifierDescriptor mo12030getContributedClassifier = this.workerScope.mo12030getContributedClassifier(name, location);
        if (mo12030getContributedClassifier != null) {
            return (ClassifierDescriptor) substitute((SubstitutingScope) mo12030getContributedClassifier);
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    /* renamed from: getContributedDescriptors */
    public Collection<DeclarationDescriptor> mo12065getContributedDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter) {
        Intrinsics.checkParameterIsNotNull(kindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        return get_allDescriptors();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    /* renamed from: getContributedFunctions */
    public Collection<? extends SimpleFunctionDescriptor> mo12099getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        return substitute(this.workerScope.mo12099getContributedFunctions(name, location));
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    /* renamed from: getContributedVariables */
    public Collection<? extends PropertyDescriptor> mo12100getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        return substitute(this.workerScope.mo12100getContributedVariables(name, location));
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getFunctionNames() {
        return this.workerScope.getFunctionNames();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getVariableNames() {
        return this.workerScope.getVariableNames();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final <D extends DeclarationDescriptor> Collection<D> substitute(Collection<? extends D> collection) {
        if (!this.substitutor.isEmpty() && !collection.isEmpty()) {
            LinkedHashSet newLinkedHashSetWithExpectedSize = CollectionsKt.newLinkedHashSetWithExpectedSize(collection.size());
            Iterator it2 = collection.iterator();
            while (it2.hasNext()) {
                newLinkedHashSetWithExpectedSize.add(substitute((SubstitutingScope) ((DeclarationDescriptor) it2.next())));
            }
            return newLinkedHashSetWithExpectedSize;
        }
        return collection;
    }
}
