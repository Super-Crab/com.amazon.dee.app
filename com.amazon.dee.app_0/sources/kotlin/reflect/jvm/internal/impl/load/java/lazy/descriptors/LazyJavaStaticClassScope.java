package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.UtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LazyJavaStaticClassScope.kt */
/* loaded from: classes3.dex */
public final class LazyJavaStaticClassScope extends LazyJavaStaticScope {
    private final JavaClass jClass;
    @NotNull
    private final LazyJavaClassDescriptor ownerDescriptor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaStaticClassScope(@NotNull LazyJavaResolverContext c, @NotNull JavaClass jClass, @NotNull LazyJavaClassDescriptor ownerDescriptor) {
        super(c);
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(jClass, "jClass");
        Intrinsics.checkParameterIsNotNull(ownerDescriptor, "ownerDescriptor");
        this.jClass = jClass;
        this.ownerDescriptor = ownerDescriptor;
    }

    private final <R> Set<R> flatMapJavaStaticSupertypesScopes(final ClassDescriptor classDescriptor, final Set<R> set, final Function1<? super MemberScope, ? extends Collection<? extends R>> function1) {
        List listOf;
        listOf = CollectionsKt__CollectionsJVMKt.listOf(classDescriptor);
        DFS.dfs(listOf, LazyJavaStaticClassScope$flatMapJavaStaticSupertypesScopes$1.INSTANCE, new DFS.AbstractNodeHandler<ClassDescriptor, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope$flatMapJavaStaticSupertypesScopes$2
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            /* renamed from: result */
            public /* bridge */ /* synthetic */ Object mo12145result() {
                mo12145result();
                return Unit.INSTANCE;
            }

            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            /* renamed from: result  reason: collision with other method in class */
            public void mo12145result() {
            }

            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(@NotNull ClassDescriptor current) {
                Intrinsics.checkParameterIsNotNull(current, "current");
                if (current == ClassDescriptor.this) {
                    return true;
                }
                MemberScope mo12047getStaticScope = current.mo12047getStaticScope();
                Intrinsics.checkExpressionValueIsNotNull(mo12047getStaticScope, "current.staticScope");
                if (!(mo12047getStaticScope instanceof LazyJavaStaticScope)) {
                    return true;
                }
                set.addAll((Collection) function1.mo12165invoke(mo12047getStaticScope));
                return false;
            }
        });
        return set;
    }

    private final PropertyDescriptor getRealOriginal(@NotNull PropertyDescriptor propertyDescriptor) {
        int collectionSizeOrDefault;
        List distinct;
        CallableMemberDescriptor.Kind kind = propertyDescriptor.getKind();
        Intrinsics.checkExpressionValueIsNotNull(kind, "this.kind");
        if (kind.isReal()) {
            return propertyDescriptor;
        }
        Collection<? extends PropertyDescriptor> overriddenDescriptors = propertyDescriptor.getOverriddenDescriptors();
        Intrinsics.checkExpressionValueIsNotNull(overriddenDescriptors, "this.overriddenDescriptors");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(overriddenDescriptors, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (PropertyDescriptor it2 : overriddenDescriptors) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            arrayList.add(getRealOriginal(it2));
        }
        distinct = CollectionsKt___CollectionsKt.distinct(arrayList);
        return (PropertyDescriptor) CollectionsKt.single((List<? extends Object>) distinct);
    }

    private final Set<SimpleFunctionDescriptor> getStaticFunctionsFromJavaSuperClasses(Name name, ClassDescriptor classDescriptor) {
        Set<SimpleFunctionDescriptor> emptySet;
        Set<SimpleFunctionDescriptor> set;
        LazyJavaStaticClassScope parentJavaStaticClassScope = UtilKt.getParentJavaStaticClassScope(classDescriptor);
        if (parentJavaStaticClassScope != null) {
            set = CollectionsKt___CollectionsKt.toSet(parentJavaStaticClassScope.mo12099getContributedFunctions(name, NoLookupLocation.WHEN_GET_SUPER_MEMBERS));
            return set;
        }
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    public Set<Name> computeClassNames(@NotNull DescriptorKindFilter kindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Set<Name> emptySet;
        Intrinsics.checkParameterIsNotNull(kindFilter, "kindFilter");
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    /* renamed from: computeFunctionNames */
    public Set<Name> mo11669computeFunctionNames(@NotNull DescriptorKindFilter kindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Set<Name> mutableSet;
        List listOf;
        Intrinsics.checkParameterIsNotNull(kindFilter, "kindFilter");
        mutableSet = CollectionsKt___CollectionsKt.toMutableSet(getDeclaredMemberIndex().mo12560invoke().getMethodNames());
        LazyJavaStaticClassScope parentJavaStaticClassScope = UtilKt.getParentJavaStaticClassScope(mo11690getOwnerDescriptor());
        Set<Name> functionNames = parentJavaStaticClassScope != null ? parentJavaStaticClassScope.getFunctionNames() : null;
        if (functionNames == null) {
            functionNames = SetsKt__SetsKt.emptySet();
        }
        mutableSet.addAll(functionNames);
        if (this.jClass.isEnum()) {
            listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Name[]{DescriptorUtils.ENUM_VALUE_OF, DescriptorUtils.ENUM_VALUES});
            mutableSet.addAll(listOf);
        }
        return mutableSet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public void computeNonDeclaredFunctions(@NotNull Collection<SimpleFunctionDescriptor> result, @NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(result, "result");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Collection<? extends SimpleFunctionDescriptor> resolveOverridesForStaticMembers = DescriptorResolverUtils.resolveOverridesForStaticMembers(name, getStaticFunctionsFromJavaSuperClasses(name, mo11690getOwnerDescriptor()), result, mo11690getOwnerDescriptor(), getC().getComponents().getErrorReporter(), getC().getComponents().getKotlinTypeChecker().getOverridingUtil());
        Intrinsics.checkExpressionValueIsNotNull(resolveOverridesForStaticMembers, "resolveOverridesForStati….overridingUtil\n        )");
        result.addAll(resolveOverridesForStaticMembers);
        if (this.jClass.isEnum()) {
            if (Intrinsics.areEqual(name, DescriptorUtils.ENUM_VALUE_OF)) {
                SimpleFunctionDescriptor createEnumValueOfMethod = DescriptorFactory.createEnumValueOfMethod(mo11690getOwnerDescriptor());
                Intrinsics.checkExpressionValueIsNotNull(createEnumValueOfMethod, "createEnumValueOfMethod(ownerDescriptor)");
                result.add(createEnumValueOfMethod);
            } else if (!Intrinsics.areEqual(name, DescriptorUtils.ENUM_VALUES)) {
            } else {
                SimpleFunctionDescriptor createEnumValuesMethod = DescriptorFactory.createEnumValuesMethod(mo11690getOwnerDescriptor());
                Intrinsics.checkExpressionValueIsNotNull(createEnumValuesMethod, "createEnumValuesMethod(ownerDescriptor)");
                result.add(createEnumValuesMethod);
            }
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticScope, kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeNonDeclaredProperties(@NotNull Name name, @NotNull Collection<PropertyDescriptor> result) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(result, "result");
        Set flatMapJavaStaticSupertypesScopes = flatMapJavaStaticSupertypesScopes(mo11690getOwnerDescriptor(), new LinkedHashSet(), new LazyJavaStaticClassScope$computeNonDeclaredProperties$propertiesFromSupertypes$1(name));
        if (!result.isEmpty()) {
            Collection<? extends PropertyDescriptor> resolveOverridesForStaticMembers = DescriptorResolverUtils.resolveOverridesForStaticMembers(name, flatMapJavaStaticSupertypesScopes, result, mo11690getOwnerDescriptor(), getC().getComponents().getErrorReporter(), getC().getComponents().getKotlinTypeChecker().getOverridingUtil());
            Intrinsics.checkExpressionValueIsNotNull(resolveOverridesForStaticMembers, "resolveOverridesForStati…ingUtil\n                )");
            result.addAll(resolveOverridesForStaticMembers);
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : flatMapJavaStaticSupertypesScopes) {
            PropertyDescriptor realOriginal = getRealOriginal((PropertyDescriptor) obj);
            Object obj2 = linkedHashMap.get(realOriginal);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(realOriginal, obj2);
            }
            ((List) obj2).add(obj);
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, DescriptorResolverUtils.resolveOverridesForStaticMembers(name, (Collection) entry.getValue(), result, mo11690getOwnerDescriptor(), getC().getComponents().getErrorReporter(), getC().getComponents().getKotlinTypeChecker().getOverridingUtil()));
        }
        result.addAll(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    public Set<Name> computePropertyNames(@NotNull DescriptorKindFilter kindFilter, @Nullable Function1<? super Name, Boolean> function1) {
        Set<Name> mutableSet;
        Intrinsics.checkParameterIsNotNull(kindFilter, "kindFilter");
        mutableSet = CollectionsKt___CollectionsKt.toMutableSet(getDeclaredMemberIndex().mo12560invoke().getFieldNames());
        flatMapJavaStaticSupertypesScopes(mo11690getOwnerDescriptor(), mutableSet, LazyJavaStaticClassScope$computePropertyNames$1$1.INSTANCE);
        return mutableSet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @Nullable
    /* renamed from: getContributedClassifier */
    public ClassifierDescriptor mo12030getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    /* renamed from: computeMemberIndex */
    public ClassDeclaredMemberIndex mo11689computeMemberIndex() {
        return new ClassDeclaredMemberIndex(this.jClass, LazyJavaStaticClassScope$computeMemberIndex$1.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    @NotNull
    /* renamed from: getOwnerDescriptor  reason: collision with other method in class */
    public LazyJavaClassDescriptor mo11690getOwnerDescriptor() {
        return this.ownerDescriptor;
    }
}
