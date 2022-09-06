package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: GivenFunctionsMemberScope.kt */
/* loaded from: classes4.dex */
public abstract class GivenFunctionsMemberScope extends MemberScopeImpl {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(GivenFunctionsMemberScope.class), "allDescriptors", "getAllDescriptors()Ljava/util/List;"))};
    private final NotNullLazyValue allDescriptors$delegate;
    @NotNull
    private final ClassDescriptor containingClass;

    public GivenFunctionsMemberScope(@NotNull StorageManager storageManager, @NotNull ClassDescriptor containingClass) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(containingClass, "containingClass");
        this.containingClass = containingClass;
        this.allDescriptors$delegate = storageManager.createLazyValue(new GivenFunctionsMemberScope$allDescriptors$2(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final List<DeclarationDescriptor> createFakeOverrides(List<? extends FunctionDescriptor> list) {
        Collection<? extends CallableMemberDescriptor> emptyList;
        final ArrayList arrayList = new ArrayList(3);
        TypeConstructor mo11528getTypeConstructor = this.containingClass.mo11528getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "containingClass.typeConstructor");
        Collection<KotlinType> mo12135getSupertypes = mo11528getTypeConstructor.mo12135getSupertypes();
        Intrinsics.checkExpressionValueIsNotNull(mo12135getSupertypes, "containingClass.typeConstructor.supertypes");
        ArrayList arrayList2 = new ArrayList();
        for (KotlinType kotlinType : mo12135getSupertypes) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList2, ResolutionScope.DefaultImpls.getContributedDescriptors$default(kotlinType.getMemberScope(), null, null, 3, null));
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : arrayList2) {
            if (obj instanceof CallableMemberDescriptor) {
                arrayList3.add(obj);
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj2 : arrayList3) {
            Name name = ((CallableMemberDescriptor) obj2).getName();
            Object obj3 = linkedHashMap.get(name);
            if (obj3 == null) {
                obj3 = new ArrayList();
                linkedHashMap.put(name, obj3);
            }
            ((List) obj3).add(obj2);
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Name name2 = (Name) entry.getKey();
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Object obj4 : (List) entry.getValue()) {
                Boolean valueOf = Boolean.valueOf(((CallableMemberDescriptor) obj4) instanceof FunctionDescriptor);
                Object obj5 = linkedHashMap2.get(valueOf);
                if (obj5 == null) {
                    obj5 = new ArrayList();
                    linkedHashMap2.put(valueOf, obj5);
                }
                ((List) obj5).add(obj4);
            }
            for (Map.Entry entry2 : linkedHashMap2.entrySet()) {
                boolean booleanValue = ((Boolean) entry2.getKey()).booleanValue();
                List list2 = (List) entry2.getValue();
                OverridingUtil overridingUtil = OverridingUtil.DEFAULT;
                if (booleanValue) {
                    emptyList = new ArrayList<>();
                    for (Object obj6 : list) {
                        if (Intrinsics.areEqual(((FunctionDescriptor) obj6).getName(), name2)) {
                            emptyList.add(obj6);
                        }
                    }
                } else {
                    emptyList = CollectionsKt__CollectionsKt.emptyList();
                }
                overridingUtil.generateOverridesInFunctionGroup(name2, list2, emptyList, this.containingClass, new NonReportingOverrideStrategy() { // from class: kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope$createFakeOverrides$4
                    @Override // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
                    public void addFakeOverride(@NotNull CallableMemberDescriptor fakeOverride) {
                        Intrinsics.checkParameterIsNotNull(fakeOverride, "fakeOverride");
                        OverridingUtil.resolveUnknownVisibilityForMember(fakeOverride, null);
                        arrayList.add(fakeOverride);
                    }

                    @Override // kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy
                    protected void conflict(@NotNull CallableMemberDescriptor fromSuper, @NotNull CallableMemberDescriptor fromCurrent) {
                        Intrinsics.checkParameterIsNotNull(fromSuper, "fromSuper");
                        Intrinsics.checkParameterIsNotNull(fromCurrent, "fromCurrent");
                        throw new IllegalStateException(("Conflict in scope of " + GivenFunctionsMemberScope.this.getContainingClass() + RealTimeTextConstants.COLON_SPACE + fromSuper + " vs " + fromCurrent).toString());
                    }
                });
            }
        }
        return CollectionsKt.compact(arrayList);
    }

    private final List<DeclarationDescriptor> getAllDescriptors() {
        return (List) StorageKt.getValue(this.allDescriptors$delegate, this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public abstract List<FunctionDescriptor> computeDeclaredFunctions();

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final ClassDescriptor getContainingClass() {
        return this.containingClass;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    /* renamed from: getContributedDescriptors */
    public Collection<DeclarationDescriptor> mo12065getContributedDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter) {
        List emptyList;
        Intrinsics.checkParameterIsNotNull(kindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        if (!kindFilter.acceptsKinds(DescriptorKindFilter.CALLABLES.getKindMask())) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return getAllDescriptors();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    /* renamed from: getContributedFunctions */
    public Collection<SimpleFunctionDescriptor> mo12099getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        List<DeclarationDescriptor> allDescriptors = getAllDescriptors();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allDescriptors) {
            if (obj instanceof SimpleFunctionDescriptor) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (Intrinsics.areEqual(((SimpleFunctionDescriptor) obj2).getName(), name)) {
                arrayList2.add(obj2);
            }
        }
        return arrayList2;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    /* renamed from: getContributedVariables */
    public Collection<PropertyDescriptor> mo12100getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        List<DeclarationDescriptor> allDescriptors = getAllDescriptors();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allDescriptors) {
            if (obj instanceof PropertyDescriptor) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (Intrinsics.areEqual(((PropertyDescriptor) obj2).getName(), name)) {
                arrayList2.add(obj2);
            }
        }
        return arrayList2;
    }
}
