package kotlin.reflect.jvm.internal.impl.descriptors;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.ClassTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NotFoundClasses.kt */
/* loaded from: classes2.dex */
public final class NotFoundClasses {
    private final MemoizedFunctionToNotNull<ClassRequest, ClassDescriptor> classes;
    private final ModuleDescriptor module;
    private final MemoizedFunctionToNotNull<FqName, PackageFragmentDescriptor> packageFragments;
    private final StorageManager storageManager;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: NotFoundClasses.kt */
    /* loaded from: classes2.dex */
    public static final class ClassRequest {
        @NotNull
        private final ClassId classId;
        @NotNull
        private final List<Integer> typeParametersCount;

        public ClassRequest(@NotNull ClassId classId, @NotNull List<Integer> typeParametersCount) {
            Intrinsics.checkParameterIsNotNull(classId, "classId");
            Intrinsics.checkParameterIsNotNull(typeParametersCount, "typeParametersCount");
            this.classId = classId;
            this.typeParametersCount = typeParametersCount;
        }

        @NotNull
        public final ClassId component1() {
            return this.classId;
        }

        @NotNull
        public final List<Integer> component2() {
            return this.typeParametersCount;
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof ClassRequest)) {
                    return false;
                }
                ClassRequest classRequest = (ClassRequest) obj;
                return Intrinsics.areEqual(this.classId, classRequest.classId) && Intrinsics.areEqual(this.typeParametersCount, classRequest.typeParametersCount);
            }
            return true;
        }

        public int hashCode() {
            ClassId classId = this.classId;
            int i = 0;
            int hashCode = (classId != null ? classId.hashCode() : 0) * 31;
            List<Integer> list = this.typeParametersCount;
            if (list != null) {
                i = list.hashCode();
            }
            return hashCode + i;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ClassRequest(classId=");
            outline107.append(this.classId);
            outline107.append(", typeParametersCount=");
            return GeneratedOutlineSupport1.outline95(outline107, this.typeParametersCount, ")");
        }
    }

    /* compiled from: NotFoundClasses.kt */
    /* loaded from: classes2.dex */
    public static final class MockClassDescriptor extends ClassDescriptorBase {
        private final boolean isInner;
        private final ClassTypeConstructorImpl typeConstructor;
        private final List<TypeParameterDescriptor> typeParameters;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MockClassDescriptor(@NotNull StorageManager storageManager, @NotNull DeclarationDescriptor container, @NotNull Name name, boolean z, int i) {
            super(storageManager, container, name, SourceElement.NO_SOURCE, false);
            IntRange until;
            int collectionSizeOrDefault;
            Set of;
            Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
            Intrinsics.checkParameterIsNotNull(container, "container");
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.isInner = z;
            until = RangesKt___RangesKt.until(0, i);
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(until, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            Iterator<Integer> it2 = until.iterator();
            while (it2.hasNext()) {
                int nextInt = ((IntIterator) it2).nextInt();
                Annotations empty = Annotations.Companion.getEMPTY();
                Variance variance = Variance.INVARIANT;
                StringBuilder sb = new StringBuilder();
                sb.append('T');
                sb.append(nextInt);
                arrayList.add(TypeParameterDescriptorImpl.createWithDefaultBound(this, empty, false, variance, Name.identifier(sb.toString()), nextInt, storageManager));
            }
            this.typeParameters = arrayList;
            List<TypeParameterDescriptor> list = this.typeParameters;
            of = SetsKt__SetsJVMKt.setOf(DescriptorUtilsKt.getModule(this).getBuiltIns().getAnyType());
            this.typeConstructor = new ClassTypeConstructorImpl(this, list, of, storageManager);
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
        @NotNull
        /* renamed from: getAnnotations */
        public Annotations mo12070getAnnotations() {
            return Annotations.Companion.getEMPTY();
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        @Nullable
        /* renamed from: getCompanionObjectDescriptor */
        public ClassDescriptor mo11505getCompanionObjectDescriptor() {
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        @NotNull
        /* renamed from: getConstructors */
        public Collection<ClassConstructorDescriptor> mo11663getConstructors() {
            Set emptySet;
            emptySet = SetsKt__SetsKt.emptySet();
            return emptySet;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
        @NotNull
        public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
            return this.typeParameters;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        @NotNull
        public ClassKind getKind() {
            return ClassKind.CLASS;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
        @NotNull
        public Modality getModality() {
            return Modality.FINAL;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        @NotNull
        /* renamed from: getSealedSubclasses */
        public Collection<ClassDescriptor> mo11508getSealedSubclasses() {
            List emptyList;
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        @Nullable
        /* renamed from: getUnsubstitutedPrimaryConstructor */
        public ClassConstructorDescriptor mo11511getUnsubstitutedPrimaryConstructor() {
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
        @NotNull
        public Visibility getVisibility() {
            Visibility visibility = Visibilities.PUBLIC;
            Intrinsics.checkExpressionValueIsNotNull(visibility, "Visibilities.PUBLIC");
            return visibility;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
        public boolean isActual() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public boolean isCompanionObject() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public boolean isData() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
        public boolean isExpect() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
        public boolean isExternal() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public boolean isInline() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
        public boolean isInner() {
            return this.isInner;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("class ");
            outline107.append(getName());
            outline107.append(" (not found)");
            return outline107.toString();
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        @NotNull
        /* renamed from: getStaticScope */
        public MemberScope.Empty mo12047getStaticScope() {
            return MemberScope.Empty.INSTANCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
        @NotNull
        /* renamed from: getTypeConstructor */
        public ClassTypeConstructorImpl mo11528getTypeConstructor() {
            return this.typeConstructor;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
        @NotNull
        /* renamed from: getUnsubstitutedMemberScope */
        public MemberScope.Empty mo11665getUnsubstitutedMemberScope(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
            Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
            return MemberScope.Empty.INSTANCE;
        }
    }

    public NotFoundClasses(@NotNull StorageManager storageManager, @NotNull ModuleDescriptor module) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(module, "module");
        this.storageManager = storageManager;
        this.module = module;
        this.packageFragments = this.storageManager.createMemoizedFunction(new NotFoundClasses$packageFragments$1(this));
        this.classes = this.storageManager.createMemoizedFunction(new NotFoundClasses$classes$1(this));
    }

    @NotNull
    public final ClassDescriptor getClass(@NotNull ClassId classId, @NotNull List<Integer> typeParametersCount) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        Intrinsics.checkParameterIsNotNull(typeParametersCount, "typeParametersCount");
        return this.classes.mo12165invoke(new ClassRequest(classId, typeParametersCount));
    }
}
