package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ScopesHolderForClass;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.UtilsKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.StaticScopeForKotlinEnum;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DeserializedClassDescriptor.kt */
/* loaded from: classes4.dex */
public final class DeserializedClassDescriptor extends AbstractClassDescriptor {
    @NotNull
    private final Annotations annotations;
    @NotNull
    private final DeserializationContext c;
    private final ClassId classId;
    @NotNull
    private final ProtoBuf.Class classProto;
    private final NullableLazyValue<ClassDescriptor> companionObjectDescriptor;
    private final NotNullLazyValue<Collection<ClassConstructorDescriptor>> constructors;
    private final DeclarationDescriptor containingDeclaration;
    private final EnumEntryClassDescriptors enumEntries;
    private final ClassKind kind;
    private final ScopesHolderForClass<DeserializedClassMemberScope> memberScopeHolder;
    @NotNull
    private final BinaryVersion metadataVersion;
    private final Modality modality;
    private final NullableLazyValue<ClassConstructorDescriptor> primaryConstructor;
    private final NotNullLazyValue<Collection<ClassDescriptor>> sealedSubclasses;
    private final SourceElement sourceElement;
    private final MemberScopeImpl staticScope;
    @NotNull
    private final ProtoContainer.Class thisAsProtoContainer;
    private final DeserializedClassTypeConstructor typeConstructor;
    private final Visibility visibility;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DeserializedClassDescriptor.kt */
    /* loaded from: classes4.dex */
    public final class DeserializedClassMemberScope extends DeserializedMemberScope {
        private final NotNullLazyValue<Collection<DeclarationDescriptor>> allDescriptors;
        private final KotlinTypeRefiner kotlinTypeRefiner;
        private final NotNullLazyValue<Collection<KotlinType>> refinedSupertypes;
        final /* synthetic */ DeserializedClassDescriptor this$0;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public DeserializedClassMemberScope(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor r8, kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner r9) {
            /*
                r7 = this;
                java.lang.String r0 = "kotlinTypeRefiner"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
                r7.this$0 = r8
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r2 = r8.getC()
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class r0 = r8.getClassProto()
                java.util.List r3 = r0.getFunctionList()
                java.lang.String r0 = "classProto.functionList"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r0)
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class r0 = r8.getClassProto()
                java.util.List r4 = r0.getPropertyList()
                java.lang.String r0 = "classProto.propertyList"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r0)
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class r0 = r8.getClassProto()
                java.util.List r5 = r0.getTypeAliasList()
                java.lang.String r0 = "classProto.typeAliasList"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class r0 = r8.getClassProto()
                java.util.List r0 = r0.getNestedClassNameList()
                java.lang.String r1 = "classProto.nestedClassNameList"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r8 = r8.getC()
                kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r8 = r8.getNameResolver()
                java.util.ArrayList r1 = new java.util.ArrayList
                r6 = 10
                int r6 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r6)
                r1.<init>(r6)
                java.util.Iterator r0 = r0.iterator()
            L56:
                boolean r6 = r0.hasNext()
                if (r6 == 0) goto L6e
                java.lang.Object r6 = r0.next()
                java.lang.Number r6 = (java.lang.Number) r6
                int r6 = r6.intValue()
                kotlin.reflect.jvm.internal.impl.name.Name r6 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt.getName(r8, r6)
                r1.add(r6)
                goto L56
            L6e:
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$2$1 r6 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$2$1
                r6.<init>(r1)
                r1 = r7
                r1.<init>(r2, r3, r4, r5, r6)
                r7.kotlinTypeRefiner = r9
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r8 = r7.getC()
                kotlin.reflect.jvm.internal.impl.storage.StorageManager r8 = r8.getStorageManager()
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$allDescriptors$1 r9 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$allDescriptors$1
                r9.<init>(r7)
                kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue r8 = r8.createLazyValue(r9)
                r7.allDescriptors = r8
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext r8 = r7.getC()
                kotlin.reflect.jvm.internal.impl.storage.StorageManager r8 = r8.getStorageManager()
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$refinedSupertypes$1 r9 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$refinedSupertypes$1
                r9.<init>(r7)
                kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue r8 = r8.createLazyValue(r9)
                r7.refinedSupertypes = r8
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor.DeserializedClassMemberScope.<init>(kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor, kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner):void");
        }

        private final <D extends CallableMemberDescriptor> void generateFakeOverrides(Name name, Collection<? extends D> collection, final Collection<D> collection2) {
            getC().getComponents().getKotlinTypeChecker().getOverridingUtil().generateOverridesInFunctionGroup(name, collection, new ArrayList(collection2), getClassDescriptor(), new NonReportingOverrideStrategy() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$generateFakeOverrides$1
                @Override // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
                public void addFakeOverride(@NotNull CallableMemberDescriptor fakeOverride) {
                    Intrinsics.checkParameterIsNotNull(fakeOverride, "fakeOverride");
                    OverridingUtil.resolveUnknownVisibilityForMember(fakeOverride, null);
                    collection2.add(fakeOverride);
                }

                @Override // kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy
                protected void conflict(@NotNull CallableMemberDescriptor fromSuper, @NotNull CallableMemberDescriptor fromCurrent) {
                    Intrinsics.checkParameterIsNotNull(fromSuper, "fromSuper");
                    Intrinsics.checkParameterIsNotNull(fromCurrent, "fromCurrent");
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final DeserializedClassDescriptor getClassDescriptor() {
            return this.this$0;
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected void addEnumEntryDescriptors(@NotNull Collection<DeclarationDescriptor> result, @NotNull Function1<? super Name, Boolean> nameFilter) {
            Intrinsics.checkParameterIsNotNull(result, "result");
            Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
            EnumEntryClassDescriptors enumEntryClassDescriptors = getClassDescriptor().enumEntries;
            Collection<ClassDescriptor> all = enumEntryClassDescriptors != null ? enumEntryClassDescriptors.all() : null;
            if (all == null) {
                all = CollectionsKt__CollectionsKt.emptyList();
            }
            result.addAll(all);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected void computeNonDeclaredFunctions(@NotNull Name name, @NotNull Collection<SimpleFunctionDescriptor> functions) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(functions, "functions");
            ArrayList arrayList = new ArrayList();
            for (KotlinType kotlinType : this.refinedSupertypes.mo12560invoke()) {
                arrayList.addAll(kotlinType.getMemberScope().mo12099getContributedFunctions(name, NoLookupLocation.FOR_ALREADY_TRACKED));
            }
            CollectionsKt__MutableCollectionsKt.retainAll(functions, new DeserializedClassDescriptor$DeserializedClassMemberScope$computeNonDeclaredFunctions$1(this));
            functions.addAll(getC().getComponents().getAdditionalClassPartsProvider().getFunctions(name, this.this$0));
            generateFakeOverrides(name, arrayList, functions);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected void computeNonDeclaredProperties(@NotNull Name name, @NotNull Collection<PropertyDescriptor> descriptors) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(descriptors, "descriptors");
            ArrayList arrayList = new ArrayList();
            for (KotlinType kotlinType : this.refinedSupertypes.mo12560invoke()) {
                arrayList.addAll(kotlinType.getMemberScope().mo12100getContributedVariables(name, NoLookupLocation.FOR_ALREADY_TRACKED));
            }
            generateFakeOverrides(name, arrayList, descriptors);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        @NotNull
        protected ClassId createClassId(@NotNull Name name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            ClassId createNestedClassId = this.this$0.classId.createNestedClassId(name);
            Intrinsics.checkExpressionValueIsNotNull(createNestedClassId, "classId.createNestedClassId(name)");
            return createNestedClassId;
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        @Nullable
        /* renamed from: getContributedClassifier */
        public ClassifierDescriptor mo12030getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
            ClassDescriptor findEnumEntry;
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(location, "location");
            recordLookup(name, location);
            EnumEntryClassDescriptors enumEntryClassDescriptors = getClassDescriptor().enumEntries;
            return (enumEntryClassDescriptors == null || (findEnumEntry = enumEntryClassDescriptors.findEnumEntry(name)) == null) ? super.mo12030getContributedClassifier(name, location) : findEnumEntry;
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        @NotNull
        /* renamed from: getContributedDescriptors */
        public Collection<DeclarationDescriptor> mo12065getContributedDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter) {
            Intrinsics.checkParameterIsNotNull(kindFilter, "kindFilter");
            Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
            return this.allDescriptors.mo12560invoke();
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        @NotNull
        /* renamed from: getContributedFunctions */
        public Collection<SimpleFunctionDescriptor> mo12099getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(location, "location");
            recordLookup(name, location);
            return super.mo12099getContributedFunctions(name, location);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
        @NotNull
        /* renamed from: getContributedVariables */
        public Collection<PropertyDescriptor> mo12100getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(location, "location");
            recordLookup(name, location);
            return super.mo12100getContributedVariables(name, location);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        @NotNull
        protected Set<Name> getNonDeclaredFunctionNames() {
            List<KotlinType> mo12135getSupertypes = getClassDescriptor().typeConstructor.mo12135getSupertypes();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (KotlinType kotlinType : mo12135getSupertypes) {
                CollectionsKt__MutableCollectionsKt.addAll(linkedHashSet, kotlinType.getMemberScope().getFunctionNames());
            }
            linkedHashSet.addAll(getC().getComponents().getAdditionalClassPartsProvider().mo11519getFunctionsNames(this.this$0));
            return linkedHashSet;
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        @NotNull
        protected Set<Name> getNonDeclaredVariableNames() {
            List<KotlinType> mo12135getSupertypes = getClassDescriptor().typeConstructor.mo12135getSupertypes();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (KotlinType kotlinType : mo12135getSupertypes) {
                CollectionsKt__MutableCollectionsKt.addAll(linkedHashSet, kotlinType.getMemberScope().getVariableNames());
            }
            return linkedHashSet;
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
        public void recordLookup(@NotNull Name name, @NotNull LookupLocation location) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(location, "location");
            UtilsKt.record(getC().getComponents().getLookupTracker(), location, getClassDescriptor(), name);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DeserializedClassDescriptor.kt */
    /* loaded from: classes4.dex */
    public final class EnumEntryClassDescriptors {
        private final MemoizedFunctionToNullable<Name, ClassDescriptor> enumEntryByName;
        private final Map<Name, ProtoBuf.EnumEntry> enumEntryProtos;
        private final NotNullLazyValue<Set<Name>> enumMemberNames;

        public EnumEntryClassDescriptors() {
            int collectionSizeOrDefault;
            int mapCapacity;
            int coerceAtLeast;
            List<ProtoBuf.EnumEntry> enumEntryList = DeserializedClassDescriptor.this.getClassProto().getEnumEntryList();
            Intrinsics.checkExpressionValueIsNotNull(enumEntryList, "classProto.enumEntryList");
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(enumEntryList, 10);
            mapCapacity = MapsKt__MapsJVMKt.mapCapacity(collectionSizeOrDefault);
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
            LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
            for (Object obj : enumEntryList) {
                ProtoBuf.EnumEntry it2 = (ProtoBuf.EnumEntry) obj;
                NameResolver nameResolver = DeserializedClassDescriptor.this.getC().getNameResolver();
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                linkedHashMap.put(NameResolverUtilKt.getName(nameResolver, it2.getName()), obj);
            }
            this.enumEntryProtos = linkedHashMap;
            this.enumEntryByName = DeserializedClassDescriptor.this.getC().getStorageManager().createMemoizedFunctionWithNullableValues(new DeserializedClassDescriptor$EnumEntryClassDescriptors$enumEntryByName$1(this));
            this.enumMemberNames = DeserializedClassDescriptor.this.getC().getStorageManager().createLazyValue(new DeserializedClassDescriptor$EnumEntryClassDescriptors$enumMemberNames$1(this));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Set<Name> computeEnumMemberNames() {
            Set<Name> plus;
            HashSet hashSet = new HashSet();
            for (KotlinType kotlinType : DeserializedClassDescriptor.this.mo11528getTypeConstructor().mo12135getSupertypes()) {
                for (DeclarationDescriptor declarationDescriptor : ResolutionScope.DefaultImpls.getContributedDescriptors$default(kotlinType.getMemberScope(), null, null, 3, null)) {
                    if ((declarationDescriptor instanceof SimpleFunctionDescriptor) || (declarationDescriptor instanceof PropertyDescriptor)) {
                        hashSet.add(declarationDescriptor.getName());
                    }
                }
            }
            List<ProtoBuf.Function> functionList = DeserializedClassDescriptor.this.getClassProto().getFunctionList();
            Intrinsics.checkExpressionValueIsNotNull(functionList, "classProto.functionList");
            for (ProtoBuf.Function it2 : functionList) {
                NameResolver nameResolver = DeserializedClassDescriptor.this.getC().getNameResolver();
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                hashSet.add(NameResolverUtilKt.getName(nameResolver, it2.getName()));
            }
            List<ProtoBuf.Property> propertyList = DeserializedClassDescriptor.this.getClassProto().getPropertyList();
            Intrinsics.checkExpressionValueIsNotNull(propertyList, "classProto.propertyList");
            for (ProtoBuf.Property it3 : propertyList) {
                NameResolver nameResolver2 = DeserializedClassDescriptor.this.getC().getNameResolver();
                Intrinsics.checkExpressionValueIsNotNull(it3, "it");
                hashSet.add(NameResolverUtilKt.getName(nameResolver2, it3.getName()));
            }
            plus = SetsKt___SetsKt.plus((Set) hashSet, (Iterable) hashSet);
            return plus;
        }

        @NotNull
        public final Collection<ClassDescriptor> all() {
            Set<Name> keySet = this.enumEntryProtos.keySet();
            ArrayList arrayList = new ArrayList();
            for (Name name : keySet) {
                ClassDescriptor findEnumEntry = findEnumEntry(name);
                if (findEnumEntry != null) {
                    arrayList.add(findEnumEntry);
                }
            }
            return arrayList;
        }

        @Nullable
        public final ClassDescriptor findEnumEntry(@NotNull Name name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return this.enumEntryByName.mo12165invoke(name);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeserializedClassDescriptor(@NotNull DeserializationContext outerContext, @NotNull ProtoBuf.Class classProto, @NotNull NameResolver nameResolver, @NotNull BinaryVersion metadataVersion, @NotNull SourceElement sourceElement) {
        super(outerContext.getStorageManager(), NameResolverUtilKt.getClassId(nameResolver, classProto.getFqName()).getShortClassName());
        Annotations nonEmptyDeserializedAnnotations;
        Intrinsics.checkParameterIsNotNull(outerContext, "outerContext");
        Intrinsics.checkParameterIsNotNull(classProto, "classProto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(metadataVersion, "metadataVersion");
        Intrinsics.checkParameterIsNotNull(sourceElement, "sourceElement");
        this.classProto = classProto;
        this.metadataVersion = metadataVersion;
        this.sourceElement = sourceElement;
        this.classId = NameResolverUtilKt.getClassId(nameResolver, this.classProto.getFqName());
        this.modality = ProtoEnumFlags.INSTANCE.modality(Flags.MODALITY.mo11937get(this.classProto.getFlags()));
        this.visibility = ProtoEnumFlags.INSTANCE.visibility(Flags.VISIBILITY.mo11937get(this.classProto.getFlags()));
        this.kind = ProtoEnumFlags.INSTANCE.classKind(Flags.CLASS_KIND.mo11937get(this.classProto.getFlags()));
        List<ProtoBuf.TypeParameter> typeParameterList = this.classProto.getTypeParameterList();
        Intrinsics.checkExpressionValueIsNotNull(typeParameterList, "classProto.typeParameterList");
        ProtoBuf.TypeTable typeTable = this.classProto.getTypeTable();
        Intrinsics.checkExpressionValueIsNotNull(typeTable, "classProto.typeTable");
        TypeTable typeTable2 = new TypeTable(typeTable);
        VersionRequirementTable.Companion companion = VersionRequirementTable.Companion;
        ProtoBuf.VersionRequirementTable versionRequirementTable = this.classProto.getVersionRequirementTable();
        Intrinsics.checkExpressionValueIsNotNull(versionRequirementTable, "classProto.versionRequirementTable");
        this.c = outerContext.childContext(this, typeParameterList, nameResolver, typeTable2, companion.create(versionRequirementTable), this.metadataVersion);
        this.staticScope = this.kind == ClassKind.ENUM_CLASS ? new StaticScopeForKotlinEnum(this.c.getStorageManager(), this) : MemberScope.Empty.INSTANCE;
        this.typeConstructor = new DeserializedClassTypeConstructor();
        this.memberScopeHolder = ScopesHolderForClass.Companion.create(this, this.c.getStorageManager(), this.c.getComponents().getKotlinTypeChecker().getKotlinTypeRefiner(), new DeserializedClassDescriptor$memberScopeHolder$1(this));
        ProtoContainer.Class r11 = null;
        this.enumEntries = this.kind == ClassKind.ENUM_CLASS ? new EnumEntryClassDescriptors() : null;
        this.containingDeclaration = outerContext.getContainingDeclaration();
        this.primaryConstructor = this.c.getStorageManager().createNullableLazyValue(new DeserializedClassDescriptor$primaryConstructor$1(this));
        this.constructors = this.c.getStorageManager().createLazyValue(new DeserializedClassDescriptor$constructors$1(this));
        this.companionObjectDescriptor = this.c.getStorageManager().createNullableLazyValue(new DeserializedClassDescriptor$companionObjectDescriptor$1(this));
        this.sealedSubclasses = this.c.getStorageManager().createLazyValue(new DeserializedClassDescriptor$sealedSubclasses$1(this));
        ProtoBuf.Class r1 = this.classProto;
        NameResolver nameResolver2 = this.c.getNameResolver();
        TypeTable typeTable3 = this.c.getTypeTable();
        SourceElement sourceElement2 = this.sourceElement;
        DeclarationDescriptor declarationDescriptor = this.containingDeclaration;
        DeserializedClassDescriptor deserializedClassDescriptor = (DeserializedClassDescriptor) (!(declarationDescriptor instanceof DeserializedClassDescriptor) ? null : declarationDescriptor);
        this.thisAsProtoContainer = new ProtoContainer.Class(r1, nameResolver2, typeTable3, sourceElement2, deserializedClassDescriptor != null ? deserializedClassDescriptor.thisAsProtoContainer : r11);
        if (!Flags.HAS_ANNOTATIONS.mo11937get(this.classProto.getFlags()).booleanValue()) {
            nonEmptyDeserializedAnnotations = Annotations.Companion.getEMPTY();
        } else {
            nonEmptyDeserializedAnnotations = new NonEmptyDeserializedAnnotations(this.c.getStorageManager(), new DeserializedClassDescriptor$annotations$1(this));
        }
        this.annotations = nonEmptyDeserializedAnnotations;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassDescriptor computeCompanionObjectDescriptor() {
        if (!this.classProto.hasCompanionObjectName()) {
            return null;
        }
        ClassifierDescriptor mo12030getContributedClassifier = getMemberScope().mo12030getContributedClassifier(NameResolverUtilKt.getName(this.c.getNameResolver(), this.classProto.getCompanionObjectName()), NoLookupLocation.FROM_DESERIALIZATION);
        if (!(mo12030getContributedClassifier instanceof ClassDescriptor)) {
            mo12030getContributedClassifier = null;
        }
        return (ClassDescriptor) mo12030getContributedClassifier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Collection<ClassConstructorDescriptor> computeConstructors() {
        List listOfNotNull;
        List plus;
        List plus2;
        List<ClassConstructorDescriptor> computeSecondaryConstructors = computeSecondaryConstructors();
        listOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull(mo11511getUnsubstitutedPrimaryConstructor());
        plus = CollectionsKt___CollectionsKt.plus((Collection) computeSecondaryConstructors, (Iterable) listOfNotNull);
        plus2 = CollectionsKt___CollectionsKt.plus((Collection) plus, (Iterable) this.c.getComponents().getAdditionalClassPartsProvider().getConstructors(this));
        return plus2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassConstructorDescriptor computePrimaryConstructor() {
        Object obj;
        if (this.kind.isSingleton()) {
            ClassConstructorDescriptorImpl createPrimaryConstructorForObject = DescriptorFactory.createPrimaryConstructorForObject(this, SourceElement.NO_SOURCE);
            createPrimaryConstructorForObject.setReturnType(getDefaultType());
            return createPrimaryConstructorForObject;
        }
        List<ProtoBuf.Constructor> constructorList = this.classProto.getConstructorList();
        Intrinsics.checkExpressionValueIsNotNull(constructorList, "classProto.constructorList");
        Iterator<T> it2 = constructorList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            ProtoBuf.Constructor it3 = (ProtoBuf.Constructor) obj;
            Flags.BooleanFlagField booleanFlagField = Flags.IS_SECONDARY;
            Intrinsics.checkExpressionValueIsNotNull(it3, "it");
            if (!booleanFlagField.mo11937get(it3.getFlags()).booleanValue()) {
                break;
            }
        }
        ProtoBuf.Constructor constructor = (ProtoBuf.Constructor) obj;
        if (constructor == null) {
            return null;
        }
        return this.c.getMemberDeserializer().loadConstructor(constructor, true);
    }

    private final List<ClassConstructorDescriptor> computeSecondaryConstructors() {
        int collectionSizeOrDefault;
        List<ProtoBuf.Constructor> constructorList = this.classProto.getConstructorList();
        Intrinsics.checkExpressionValueIsNotNull(constructorList, "classProto.constructorList");
        ArrayList<ProtoBuf.Constructor> arrayList = new ArrayList();
        for (Object obj : constructorList) {
            ProtoBuf.Constructor it2 = (ProtoBuf.Constructor) obj;
            Flags.BooleanFlagField booleanFlagField = Flags.IS_SECONDARY;
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            Boolean mo11937get = booleanFlagField.mo11937get(it2.getFlags());
            Intrinsics.checkExpressionValueIsNotNull(mo11937get, "Flags.IS_SECONDARY.get(it.flags)");
            if (mo11937get.booleanValue()) {
                arrayList.add(obj);
            }
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        for (ProtoBuf.Constructor it3 : arrayList) {
            MemberDeserializer memberDeserializer = this.c.getMemberDeserializer();
            Intrinsics.checkExpressionValueIsNotNull(it3, "it");
            arrayList2.add(memberDeserializer.loadConstructor(it3, false));
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Collection<ClassDescriptor> computeSubclassesForSealedClass() {
        List emptyList;
        if (this.modality != Modality.SEALED) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        List<Integer> fqNames = this.classProto.getSealedSubclassFqNameList();
        Intrinsics.checkExpressionValueIsNotNull(fqNames, "fqNames");
        if (!fqNames.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (Integer index : fqNames) {
                DeserializationComponents components = this.c.getComponents();
                NameResolver nameResolver = this.c.getNameResolver();
                Intrinsics.checkExpressionValueIsNotNull(index, "index");
                ClassDescriptor deserializeClass = components.deserializeClass(NameResolverUtilKt.getClassId(nameResolver, index.intValue()));
                if (deserializeClass != null) {
                    arrayList.add(deserializeClass);
                }
            }
            return arrayList;
        }
        return DescriptorUtilsKt.computeSealedSubclasses(this);
    }

    private final DeserializedClassMemberScope getMemberScope() {
        return this.memberScopeHolder.getScope(this.c.getComponents().getKotlinTypeChecker().getKotlinTypeRefiner());
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    @NotNull
    /* renamed from: getAnnotations */
    public Annotations mo12070getAnnotations() {
        return this.annotations;
    }

    @NotNull
    public final DeserializationContext getC() {
        return this.c;
    }

    @NotNull
    public final ProtoBuf.Class getClassProto() {
        return this.classProto;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @Nullable
    /* renamed from: getCompanionObjectDescriptor */
    public ClassDescriptor mo11505getCompanionObjectDescriptor() {
        return this.companionObjectDescriptor.mo12560invoke();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    /* renamed from: getConstructors */
    public Collection<ClassConstructorDescriptor> mo11663getConstructors() {
        return this.constructors.mo12560invoke();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    @NotNull
    /* renamed from: getContainingDeclaration */
    public DeclarationDescriptor mo11607getContainingDeclaration() {
        return this.containingDeclaration;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    @NotNull
    public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
        return this.c.getTypeDeserializer().getOwnTypeParameters();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    public ClassKind getKind() {
        return this.kind;
    }

    @NotNull
    public final BinaryVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    @NotNull
    public Modality getModality() {
        return this.modality;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    /* renamed from: getSealedSubclasses */
    public Collection<ClassDescriptor> mo11508getSealedSubclasses() {
        return this.sealedSubclasses.mo12560invoke();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    @NotNull
    public SourceElement getSource() {
        return this.sourceElement;
    }

    @NotNull
    public final ProtoContainer.Class getThisAsProtoContainer$deserialization() {
        return this.thisAsProtoContainer;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    @NotNull
    /* renamed from: getTypeConstructor */
    public TypeConstructor mo11528getTypeConstructor() {
        return this.typeConstructor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    @NotNull
    /* renamed from: getUnsubstitutedMemberScope */
    public MemberScope mo11665getUnsubstitutedMemberScope(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        return this.memberScopeHolder.getScope(kotlinTypeRefiner);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @Nullable
    /* renamed from: getUnsubstitutedPrimaryConstructor */
    public ClassConstructorDescriptor mo11511getUnsubstitutedPrimaryConstructor() {
        return this.primaryConstructor.mo12560invoke();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    @NotNull
    public Visibility getVisibility() {
        return this.visibility;
    }

    public final boolean hasNestedClass$deserialization(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return getMemberScope().getClassNames$deserialization().contains(name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isCompanionObject() {
        return Flags.CLASS_KIND.mo11937get(this.classProto.getFlags()) == ProtoBuf.Class.Kind.COMPANION_OBJECT;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isData() {
        Boolean mo11937get = Flags.IS_DATA.mo11937get(this.classProto.getFlags());
        Intrinsics.checkExpressionValueIsNotNull(mo11937get, "Flags.IS_DATA.get(classProto.flags)");
        return mo11937get.booleanValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        Boolean mo11937get = Flags.IS_EXPECT_CLASS.mo11937get(this.classProto.getFlags());
        Intrinsics.checkExpressionValueIsNotNull(mo11937get, "Flags.IS_EXPECT_CLASS.get(classProto.flags)");
        return mo11937get.booleanValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        Boolean mo11937get = Flags.IS_EXTERNAL_CLASS.mo11937get(this.classProto.getFlags());
        Intrinsics.checkExpressionValueIsNotNull(mo11937get, "Flags.IS_EXTERNAL_CLASS.get(classProto.flags)");
        return mo11937get.booleanValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isInline() {
        Boolean mo11937get = Flags.IS_INLINE_CLASS.mo11937get(this.classProto.getFlags());
        Intrinsics.checkExpressionValueIsNotNull(mo11937get, "Flags.IS_INLINE_CLASS.get(classProto.flags)");
        return mo11937get.booleanValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public boolean isInner() {
        Boolean mo11937get = Flags.IS_INNER.mo11937get(this.classProto.getFlags());
        Intrinsics.checkExpressionValueIsNotNull(mo11937get, "Flags.IS_INNER.get(classProto.flags)");
        return mo11937get.booleanValue();
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("deserialized ");
        outline107.append(isExpect() ? "expect" : "");
        outline107.append(" class ");
        outline107.append(getName());
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DeserializedClassDescriptor.kt */
    /* loaded from: classes4.dex */
    public final class DeserializedClassTypeConstructor extends AbstractClassTypeConstructor {
        private final NotNullLazyValue<List<TypeParameterDescriptor>> parameters;

        public DeserializedClassTypeConstructor() {
            super(DeserializedClassDescriptor.this.getC().getStorageManager());
            this.parameters = DeserializedClassDescriptor.this.getC().getStorageManager().createLazyValue(new DeserializedClassDescriptor$DeserializedClassTypeConstructor$parameters$1(this));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        @NotNull
        public Collection<KotlinType> computeSupertypes() {
            int collectionSizeOrDefault;
            List<KotlinType> plus;
            List list;
            int collectionSizeOrDefault2;
            String asString;
            FqName asSingleFqName;
            List<ProtoBuf.Type> supertypes = ProtoTypeTableUtilKt.supertypes(DeserializedClassDescriptor.this.getClassProto(), DeserializedClassDescriptor.this.getC().getTypeTable());
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(supertypes, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (ProtoBuf.Type type : supertypes) {
                arrayList.add(DeserializedClassDescriptor.this.getC().getTypeDeserializer().type(type));
            }
            plus = CollectionsKt___CollectionsKt.plus((Collection) arrayList, (Iterable) DeserializedClassDescriptor.this.getC().getComponents().getAdditionalClassPartsProvider().getSupertypes(DeserializedClassDescriptor.this));
            ArrayList<NotFoundClasses.MockClassDescriptor> arrayList2 = new ArrayList();
            for (KotlinType kotlinType : plus) {
                ClassifierDescriptor mo12085getDeclarationDescriptor = kotlinType.mo12131getConstructor().mo12085getDeclarationDescriptor();
                if (!(mo12085getDeclarationDescriptor instanceof NotFoundClasses.MockClassDescriptor)) {
                    mo12085getDeclarationDescriptor = null;
                }
                NotFoundClasses.MockClassDescriptor mockClassDescriptor = (NotFoundClasses.MockClassDescriptor) mo12085getDeclarationDescriptor;
                if (mockClassDescriptor != null) {
                    arrayList2.add(mockClassDescriptor);
                }
            }
            if (!arrayList2.isEmpty()) {
                ErrorReporter errorReporter = DeserializedClassDescriptor.this.getC().getComponents().getErrorReporter();
                DeserializedClassDescriptor deserializedClassDescriptor = DeserializedClassDescriptor.this;
                collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10);
                ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault2);
                for (NotFoundClasses.MockClassDescriptor mockClassDescriptor2 : arrayList2) {
                    ClassId classId = DescriptorUtilsKt.getClassId(mockClassDescriptor2);
                    if (classId == null || (asSingleFqName = classId.asSingleFqName()) == null || (asString = asSingleFqName.asString()) == null) {
                        asString = mockClassDescriptor2.getName().asString();
                    }
                    arrayList3.add(asString);
                }
                errorReporter.reportIncompleteHierarchy(deserializedClassDescriptor, arrayList3);
            }
            list = CollectionsKt___CollectionsKt.toList(plus);
            return list;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        public List<TypeParameterDescriptor> getParameters() {
            return this.parameters.mo12560invoke();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        @NotNull
        public SupertypeLoopChecker getSupertypeLoopChecker() {
            return SupertypeLoopChecker.EMPTY.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return true;
        }

        @NotNull
        public String toString() {
            String name = DeserializedClassDescriptor.this.getName().toString();
            Intrinsics.checkExpressionValueIsNotNull(name, "name.toString()");
            return name;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor, kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor, kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        /* renamed from: getDeclarationDescriptor */
        public DeserializedClassDescriptor mo12085getDeclarationDescriptor() {
            return DeserializedClassDescriptor.this;
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    @NotNull
    /* renamed from: getStaticScope  reason: collision with other method in class */
    public MemberScopeImpl mo12047getStaticScope() {
        return this.staticScope;
    }
}
