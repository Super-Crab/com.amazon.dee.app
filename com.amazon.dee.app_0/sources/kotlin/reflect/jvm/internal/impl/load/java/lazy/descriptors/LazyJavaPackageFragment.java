package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryPackageSourceElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LazyJavaPackageFragment.kt */
/* loaded from: classes3.dex */
public final class LazyJavaPackageFragment extends PackageFragmentDescriptorImpl {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaPackageFragment.class), "binaryClasses", "getBinaryClasses$descriptors_jvm()Ljava/util/Map;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaPackageFragment.class), "partToFacade", "getPartToFacade()Ljava/util/HashMap;"))};
    @NotNull
    private final Annotations annotations;
    @NotNull
    private final NotNullLazyValue binaryClasses$delegate;
    private final LazyJavaResolverContext c;
    private final JavaPackage jPackage;
    private final NotNullLazyValue partToFacade$delegate;
    private final JvmPackageScope scope;
    private final NotNullLazyValue<List<FqName>> subPackages;

    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[KotlinClassHeader.Kind.values().length];

        static {
            $EnumSwitchMapping$0[KotlinClassHeader.Kind.MULTIFILE_CLASS_PART.ordinal()] = 1;
            $EnumSwitchMapping$0[KotlinClassHeader.Kind.FILE_FACADE.ordinal()] = 2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaPackageFragment(@NotNull LazyJavaResolverContext outerContext, @NotNull JavaPackage jPackage) {
        super(outerContext.getModule(), jPackage.getFqName());
        List emptyList;
        Intrinsics.checkParameterIsNotNull(outerContext, "outerContext");
        Intrinsics.checkParameterIsNotNull(jPackage, "jPackage");
        this.jPackage = jPackage;
        this.c = ContextKt.childForClassOrPackage$default(outerContext, this, null, 0, 6, null);
        this.binaryClasses$delegate = this.c.getStorageManager().createLazyValue(new LazyJavaPackageFragment$binaryClasses$2(this));
        this.scope = new JvmPackageScope(this.c, this.jPackage, this);
        StorageManager storageManager = this.c.getStorageManager();
        LazyJavaPackageFragment$subPackages$1 lazyJavaPackageFragment$subPackages$1 = new LazyJavaPackageFragment$subPackages$1(this);
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        this.subPackages = storageManager.createRecursionTolerantLazyValue(lazyJavaPackageFragment$subPackages$1, emptyList);
        this.annotations = this.c.getComponents().getAnnotationTypeQualifierResolver().getDisabled() ? Annotations.Companion.getEMPTY() : LazyJavaAnnotationsKt.resolveAnnotations(this.c, this.jPackage);
        this.partToFacade$delegate = this.c.getStorageManager().createLazyValue(new LazyJavaPackageFragment$partToFacade$2(this));
    }

    @Nullable
    public final ClassDescriptor findClassifierByJavaClass$descriptors_jvm(@NotNull JavaClass jClass) {
        Intrinsics.checkParameterIsNotNull(jClass, "jClass");
        return this.scope.getJavaScope$descriptors_jvm().findClassifierByJavaClass$descriptors_jvm(jClass);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotatedImpl, kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    @NotNull
    /* renamed from: getAnnotations */
    public Annotations mo12070getAnnotations() {
        return this.annotations;
    }

    @NotNull
    public final Map<String, KotlinJvmBinaryClass> getBinaryClasses$descriptors_jvm() {
        return (Map) StorageKt.getValue(this.binaryClasses$delegate, this, $$delegatedProperties[0]);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    @NotNull
    public SourceElement getSource() {
        return new KotlinJvmBinaryPackageSourceElement(this);
    }

    @NotNull
    public final List<FqName> getSubPackageFqNames$descriptors_jvm() {
        return this.subPackages.mo12560invoke();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl
    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Lazy Java package fragment: ");
        outline107.append(getFqName());
        return outline107.toString();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
    @NotNull
    /* renamed from: getMemberScope */
    public JvmPackageScope mo11676getMemberScope() {
        return this.scope;
    }
}
