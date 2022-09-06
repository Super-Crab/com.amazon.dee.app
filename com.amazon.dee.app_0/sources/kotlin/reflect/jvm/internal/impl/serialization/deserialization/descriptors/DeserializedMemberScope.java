package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.MemberComparator;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DeserializedMemberScope.kt */
/* loaded from: classes4.dex */
public abstract class DeserializedMemberScope extends MemberScopeImpl {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedMemberScope.class), "functionNamesLazy", "getFunctionNamesLazy()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedMemberScope.class), "variableNamesLazy", "getVariableNamesLazy()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedMemberScope.class), "classNames", "getClassNames$deserialization()Ljava/util/Set;"))};
    @NotNull
    private final DeserializationContext c;
    @NotNull
    private final NotNullLazyValue classNames$delegate;
    private final NotNullLazyValue functionNamesLazy$delegate;
    private final Map<Name, byte[]> functionProtosBytes;
    private final MemoizedFunctionToNotNull<Name, Collection<SimpleFunctionDescriptor>> functions;
    private final MemoizedFunctionToNotNull<Name, Collection<PropertyDescriptor>> properties;
    private final Map<Name, byte[]> propertyProtosBytes;
    private final MemoizedFunctionToNullable<Name, TypeAliasDescriptor> typeAliasByName;
    private final Map<Name, byte[]> typeAliasBytes;
    private final NotNullLazyValue variableNamesLazy$delegate;

    /* JADX INFO: Access modifiers changed from: protected */
    public DeserializedMemberScope(@NotNull DeserializationContext c, @NotNull Collection<ProtoBuf.Function> functionList, @NotNull Collection<ProtoBuf.Property> propertyList, @NotNull Collection<ProtoBuf.TypeAlias> typeAliasList, @NotNull Function0<? extends Collection<Name>> classNames) {
        Map<Name, byte[]> emptyMap;
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(functionList, "functionList");
        Intrinsics.checkParameterIsNotNull(propertyList, "propertyList");
        Intrinsics.checkParameterIsNotNull(typeAliasList, "typeAliasList");
        Intrinsics.checkParameterIsNotNull(classNames, "classNames");
        this.c = c;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : functionList) {
            Name name = NameResolverUtilKt.getName(this.c.getNameResolver(), ((ProtoBuf.Function) obj).getName());
            Object obj2 = linkedHashMap.get(name);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(name, obj2);
            }
            ((List) obj2).add(obj);
        }
        this.functionProtosBytes = packToByteArray(linkedHashMap);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Object obj3 : propertyList) {
            Name name2 = NameResolverUtilKt.getName(this.c.getNameResolver(), ((ProtoBuf.Property) obj3).getName());
            Object obj4 = linkedHashMap2.get(name2);
            if (obj4 == null) {
                obj4 = new ArrayList();
                linkedHashMap2.put(name2, obj4);
            }
            ((List) obj4).add(obj3);
        }
        this.propertyProtosBytes = packToByteArray(linkedHashMap2);
        if (this.c.getComponents().getConfiguration().getTypeAliasesAllowed()) {
            LinkedHashMap linkedHashMap3 = new LinkedHashMap();
            for (Object obj5 : typeAliasList) {
                Name name3 = NameResolverUtilKt.getName(this.c.getNameResolver(), ((ProtoBuf.TypeAlias) obj5).getName());
                Object obj6 = linkedHashMap3.get(name3);
                if (obj6 == null) {
                    obj6 = new ArrayList();
                    linkedHashMap3.put(name3, obj6);
                }
                ((List) obj6).add(obj5);
            }
            emptyMap = packToByteArray(linkedHashMap3);
        } else {
            emptyMap = MapsKt__MapsKt.emptyMap();
        }
        this.typeAliasBytes = emptyMap;
        this.functions = this.c.getStorageManager().createMemoizedFunction(new DeserializedMemberScope$functions$1(this));
        this.properties = this.c.getStorageManager().createMemoizedFunction(new DeserializedMemberScope$properties$1(this));
        this.typeAliasByName = this.c.getStorageManager().createMemoizedFunctionWithNullableValues(new DeserializedMemberScope$typeAliasByName$1(this));
        this.functionNamesLazy$delegate = this.c.getStorageManager().createLazyValue(new DeserializedMemberScope$functionNamesLazy$2(this));
        this.variableNamesLazy$delegate = this.c.getStorageManager().createLazyValue(new DeserializedMemberScope$variableNamesLazy$2(this));
        this.classNames$delegate = this.c.getStorageManager().createLazyValue(new DeserializedMemberScope$classNames$2(classNames));
    }

    private final void addFunctionsAndProperties(Collection<DeclarationDescriptor> collection, DescriptorKindFilter descriptorKindFilter, Function1<? super Name, Boolean> function1, LookupLocation lookupLocation) {
        if (descriptorKindFilter.acceptsKinds(DescriptorKindFilter.Companion.getVARIABLES_MASK())) {
            Set<Name> variableNames = getVariableNames();
            ArrayList arrayList = new ArrayList();
            for (Name name : variableNames) {
                if (function1.mo12165invoke(name).booleanValue()) {
                    arrayList.addAll(mo12100getContributedVariables(name, lookupLocation));
                }
            }
            MemberComparator.NameAndTypeMemberComparator nameAndTypeMemberComparator = MemberComparator.NameAndTypeMemberComparator.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(nameAndTypeMemberComparator, "MemberComparator.NameAnd…MemberComparator.INSTANCE");
            CollectionsKt__MutableCollectionsJVMKt.sortWith(arrayList, nameAndTypeMemberComparator);
            collection.addAll(arrayList);
        }
        if (descriptorKindFilter.acceptsKinds(DescriptorKindFilter.Companion.getFUNCTIONS_MASK())) {
            Set<Name> functionNames = getFunctionNames();
            ArrayList arrayList2 = new ArrayList();
            for (Name name2 : functionNames) {
                if (function1.mo12165invoke(name2).booleanValue()) {
                    arrayList2.addAll(mo12099getContributedFunctions(name2, lookupLocation));
                }
            }
            MemberComparator.NameAndTypeMemberComparator nameAndTypeMemberComparator2 = MemberComparator.NameAndTypeMemberComparator.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(nameAndTypeMemberComparator2, "MemberComparator.NameAnd…MemberComparator.INSTANCE");
            CollectionsKt__MutableCollectionsJVMKt.sortWith(arrayList2, nameAndTypeMemberComparator2);
            collection.addAll(arrayList2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0023, code lost:
        if (r0 != null) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.Collection<kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor> computeFunctions(kotlin.reflect.jvm.internal.impl.name.Name r6) {
        /*
            r5 = this;
            java.util.Map<kotlin.reflect.jvm.internal.impl.name.Name, byte[]> r0 = r5.functionProtosBytes
            kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function> r1 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.PARSER
            java.lang.String r2 = "ProtoBuf.Function.PARSER"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.Object r0 = r0.get(r6)
            byte[] r0 = (byte[]) r0
            if (r0 == 0) goto L26
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r2.<init>(r0)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$computeDescriptors$$inlined$let$lambda$1 r0 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$computeDescriptors$$inlined$let$lambda$1
            r0.<init>(r2, r5, r1)
            kotlin.sequences.Sequence r0 = kotlin.sequences.SequencesKt.generateSequence(r0)
            java.util.List r0 = kotlin.sequences.SequencesKt.toList(r0)
            if (r0 == 0) goto L26
            goto L2a
        L26:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L2a:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r0 = r0.iterator()
        L33:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L52
            java.lang.Object r2 = r0.next()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function r2 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function) r2
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = r5.c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer r3 = r3.getMemberDeserializer()
            java.lang.String r4 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r4)
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r2 = r3.loadFunction(r2)
            r1.add(r2)
            goto L33
        L52:
            r5.computeNonDeclaredFunctions(r6, r1)
            java.util.List r6 = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.computeFunctions(kotlin.reflect.jvm.internal.impl.name.Name):java.util.Collection");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0023, code lost:
        if (r0 != null) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.Collection<kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor> computeProperties(kotlin.reflect.jvm.internal.impl.name.Name r6) {
        /*
            r5 = this;
            java.util.Map<kotlin.reflect.jvm.internal.impl.name.Name, byte[]> r0 = r5.propertyProtosBytes
            kotlin.reflect.jvm.internal.impl.protobuf.Parser<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property> r1 = kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.PARSER
            java.lang.String r2 = "ProtoBuf.Property.PARSER"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.Object r0 = r0.get(r6)
            byte[] r0 = (byte[]) r0
            if (r0 == 0) goto L26
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r2.<init>(r0)
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$computeDescriptors$$inlined$let$lambda$3 r0 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$computeDescriptors$$inlined$let$lambda$3
            r0.<init>(r2, r5, r1)
            kotlin.sequences.Sequence r0 = kotlin.sequences.SequencesKt.generateSequence(r0)
            java.util.List r0 = kotlin.sequences.SequencesKt.toList(r0)
            if (r0 == 0) goto L26
            goto L2a
        L26:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L2a:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r0 = r0.iterator()
        L33:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L52
            java.lang.Object r2 = r0.next()
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property r2 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property) r2
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r3 = r5.c
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer r3 = r3.getMemberDeserializer()
            java.lang.String r4 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r4)
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r2 = r3.loadProperty(r2)
            r1.add(r2)
            goto L33
        L52:
            r5.computeNonDeclaredProperties(r6, r1)
            java.util.List r6 = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope.computeProperties(kotlin.reflect.jvm.internal.impl.name.Name):java.util.Collection");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TypeAliasDescriptor createTypeAlias(Name name) {
        ProtoBuf.TypeAlias parseDelimitedFrom;
        byte[] bArr = this.typeAliasBytes.get(name);
        if (bArr == null || (parseDelimitedFrom = ProtoBuf.TypeAlias.parseDelimitedFrom(new ByteArrayInputStream(bArr), this.c.getComponents().getExtensionRegistryLite())) == null) {
            return null;
        }
        return this.c.getMemberDeserializer().loadTypeAlias(parseDelimitedFrom);
    }

    private final ClassDescriptor deserializeClass(Name name) {
        return this.c.getComponents().deserializeClass(createClassId(name));
    }

    private final Set<Name> getFunctionNamesLazy() {
        return (Set) StorageKt.getValue(this.functionNamesLazy$delegate, this, $$delegatedProperties[0]);
    }

    private final Set<Name> getTypeAliasNames() {
        return this.typeAliasBytes.keySet();
    }

    private final Set<Name> getVariableNamesLazy() {
        return (Set) StorageKt.getValue(this.variableNamesLazy$delegate, this, $$delegatedProperties[1]);
    }

    private final Map<Name, byte[]> packToByteArray(@NotNull Map<Name, ? extends Collection<? extends AbstractMessageLite>> map) {
        int mapCapacity;
        int collectionSizeOrDefault;
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(map.size());
        LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
        Iterator<T> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            Object key = entry.getKey();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Iterable<AbstractMessageLite> iterable = (Iterable) entry.getValue();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (AbstractMessageLite abstractMessageLite : iterable) {
                abstractMessageLite.writeDelimitedTo(byteArrayOutputStream);
                arrayList.add(Unit.INSTANCE);
            }
            linkedHashMap.put(key, byteArrayOutputStream.toByteArray());
        }
        return linkedHashMap;
    }

    protected abstract void addEnumEntryDescriptors(@NotNull Collection<DeclarationDescriptor> collection, @NotNull Function1<? super Name, Boolean> function1);

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final Collection<DeclarationDescriptor> computeDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter, @NotNull LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(kindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        Intrinsics.checkParameterIsNotNull(location, "location");
        ArrayList arrayList = new ArrayList(0);
        if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getSINGLETON_CLASSIFIERS_MASK())) {
            addEnumEntryDescriptors(arrayList, nameFilter);
        }
        addFunctionsAndProperties(arrayList, kindFilter, nameFilter, location);
        if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getCLASSIFIERS_MASK())) {
            for (Name name : getClassNames$deserialization()) {
                if (nameFilter.mo12165invoke(name).booleanValue()) {
                    CollectionsKt.addIfNotNull(arrayList, deserializeClass(name));
                }
            }
        }
        if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getTYPE_ALIASES_MASK())) {
            for (Name name2 : getTypeAliasNames()) {
                if (nameFilter.mo12165invoke(name2).booleanValue()) {
                    CollectionsKt.addIfNotNull(arrayList, this.typeAliasByName.mo12165invoke(name2));
                }
            }
        }
        return CollectionsKt.compact(arrayList);
    }

    protected void computeNonDeclaredFunctions(@NotNull Name name, @NotNull Collection<SimpleFunctionDescriptor> functions) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(functions, "functions");
    }

    protected void computeNonDeclaredProperties(@NotNull Name name, @NotNull Collection<PropertyDescriptor> descriptors) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(descriptors, "descriptors");
    }

    @NotNull
    protected abstract ClassId createClassId(@NotNull Name name);

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final DeserializationContext getC() {
        return this.c;
    }

    @NotNull
    public final Set<Name> getClassNames$deserialization() {
        return (Set) StorageKt.getValue(this.classNames$delegate, this, $$delegatedProperties[2]);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @Nullable
    /* renamed from: getContributedClassifier */
    public ClassifierDescriptor mo12030getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        if (hasClass(name)) {
            return deserializeClass(name);
        }
        if (!getTypeAliasNames().contains(name)) {
            return null;
        }
        return this.typeAliasByName.mo12165invoke(name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    @NotNull
    /* renamed from: getContributedFunctions */
    public Collection<SimpleFunctionDescriptor> mo12099getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
        List emptyList;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        if (!getFunctionNames().contains(name)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return this.functions.mo12165invoke(name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    /* renamed from: getContributedVariables */
    public Collection<PropertyDescriptor> mo12100getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
        List emptyList;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(location, "location");
        if (!getVariableNames().contains(name)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return this.properties.mo12165invoke(name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getFunctionNames() {
        return getFunctionNamesLazy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public abstract Set<Name> getNonDeclaredFunctionNames();

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public abstract Set<Name> getNonDeclaredVariableNames();

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    @NotNull
    public Set<Name> getVariableNames() {
        return getVariableNamesLazy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasClass(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return getClassNames$deserialization().contains(name);
    }
}
