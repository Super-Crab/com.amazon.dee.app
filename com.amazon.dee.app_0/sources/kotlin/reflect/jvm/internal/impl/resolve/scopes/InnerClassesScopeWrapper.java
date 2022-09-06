package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: InnerClassesScopeWrapper.kt */
/* loaded from: classes4.dex */
public final class InnerClassesScopeWrapper extends MemberScopeImpl {
    @NotNull
    private final MemberScope workerScope;

    public InnerClassesScopeWrapper(@NotNull MemberScope workerScope) {
        Intrinsics.checkParameterIsNotNull(workerScope, "workerScope");
        this.workerScope = workerScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @Nullable
    /* renamed from: getContributedClassifier */
    public ClassifierDescriptor mo12030getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        ClassifierDescriptor mo12030getContributedClassifier = this.workerScope.mo12030getContributedClassifier(name, location);
        if (mo12030getContributedClassifier != null) {
            ClassDescriptor classDescriptor = (ClassDescriptor) (!(mo12030getContributedClassifier instanceof ClassDescriptor) ? null : mo12030getContributedClassifier);
            if (classDescriptor != null) {
                return classDescriptor;
            }
            if (!(mo12030getContributedClassifier instanceof TypeAliasDescriptor)) {
                mo12030getContributedClassifier = null;
            }
            return (TypeAliasDescriptor) mo12030getContributedClassifier;
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    /* renamed from: getContributedDescriptors */
    public /* bridge */ /* synthetic */ Collection mo12065getContributedDescriptors(DescriptorKindFilter descriptorKindFilter, Function1 function1) {
        return mo12065getContributedDescriptors(descriptorKindFilter, (Function1<? super Name, Boolean>) function1);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getFunctionNames() {
        return this.workerScope.getFunctionNames();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getVariableNames() {
        return this.workerScope.getVariableNames();
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Classes from ");
        outline107.append(this.workerScope);
        return outline107.toString();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    /* renamed from: getContributedDescriptors  reason: collision with other method in class */
    public List<ClassifierDescriptor> mo12065getContributedDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter) {
        List<ClassifierDescriptor> emptyList;
        Intrinsics.checkParameterIsNotNull(kindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        DescriptorKindFilter restrictedToKindsOrNull = kindFilter.restrictedToKindsOrNull(DescriptorKindFilter.Companion.getCLASSIFIERS_MASK());
        if (restrictedToKindsOrNull != null) {
            Collection<DeclarationDescriptor> mo12065getContributedDescriptors = this.workerScope.mo12065getContributedDescriptors(restrictedToKindsOrNull, nameFilter);
            ArrayList arrayList = new ArrayList();
            for (Object obj : mo12065getContributedDescriptors) {
                if (obj instanceof ClassifierDescriptorWithTypeParameters) {
                    arrayList.add(obj);
                }
            }
            return arrayList;
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }
}
