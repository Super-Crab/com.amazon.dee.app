package kotlin.reflect.jvm.internal.impl.renderer;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__ReversedViewsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClassifierNamePolicy.kt */
/* loaded from: classes4.dex */
public interface ClassifierNamePolicy {

    /* compiled from: ClassifierNamePolicy.kt */
    /* loaded from: classes4.dex */
    public static final class FULLY_QUALIFIED implements ClassifierNamePolicy {
        public static final FULLY_QUALIFIED INSTANCE = new FULLY_QUALIFIED();

        private FULLY_QUALIFIED() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy
        @NotNull
        public String renderClassifier(@NotNull ClassifierDescriptor classifier, @NotNull DescriptorRenderer renderer) {
            Intrinsics.checkParameterIsNotNull(classifier, "classifier");
            Intrinsics.checkParameterIsNotNull(renderer, "renderer");
            if (classifier instanceof TypeParameterDescriptor) {
                Name name = ((TypeParameterDescriptor) classifier).getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "classifier.name");
                return renderer.renderName(name, false);
            }
            FqNameUnsafe fqName = DescriptorUtils.getFqName(classifier);
            Intrinsics.checkExpressionValueIsNotNull(fqName, "DescriptorUtils.getFqName(classifier)");
            return renderer.renderFqName(fqName);
        }
    }

    /* compiled from: ClassifierNamePolicy.kt */
    /* loaded from: classes4.dex */
    public static final class SHORT implements ClassifierNamePolicy {
        public static final SHORT INSTANCE = new SHORT();

        private SHORT() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy
        @NotNull
        public String renderClassifier(@NotNull ClassifierDescriptor classifier, @NotNull DescriptorRenderer renderer) {
            boolean z;
            List asReversedMutable;
            Intrinsics.checkParameterIsNotNull(classifier, "classifier");
            Intrinsics.checkParameterIsNotNull(renderer, "renderer");
            if (classifier instanceof TypeParameterDescriptor) {
                Name name = ((TypeParameterDescriptor) classifier).getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "classifier.name");
                return renderer.renderName(name, false);
            }
            ArrayList arrayList = new ArrayList();
            ClassifierDescriptor classifierDescriptor = classifier;
            do {
                arrayList.add(classifierDescriptor.getName());
                DeclarationDescriptor mo11607getContainingDeclaration = classifierDescriptor.mo11607getContainingDeclaration();
                z = mo11607getContainingDeclaration instanceof ClassDescriptor;
                classifierDescriptor = mo11607getContainingDeclaration;
            } while (z);
            asReversedMutable = CollectionsKt__ReversedViewsKt.asReversedMutable(arrayList);
            return RenderingUtilsKt.renderFqName(asReversedMutable);
        }
    }

    /* compiled from: ClassifierNamePolicy.kt */
    /* loaded from: classes4.dex */
    public static final class SOURCE_CODE_QUALIFIED implements ClassifierNamePolicy {
        public static final SOURCE_CODE_QUALIFIED INSTANCE = new SOURCE_CODE_QUALIFIED();

        private SOURCE_CODE_QUALIFIED() {
        }

        private final String qualifiedNameForSourceCode(ClassifierDescriptor classifierDescriptor) {
            Name name = classifierDescriptor.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "descriptor.name");
            String render = RenderingUtilsKt.render(name);
            if (classifierDescriptor instanceof TypeParameterDescriptor) {
                return render;
            }
            DeclarationDescriptor mo11607getContainingDeclaration = classifierDescriptor.mo11607getContainingDeclaration();
            Intrinsics.checkExpressionValueIsNotNull(mo11607getContainingDeclaration, "descriptor.containingDeclaration");
            String qualifierName = qualifierName(mo11607getContainingDeclaration);
            return (qualifierName == null || !(Intrinsics.areEqual(qualifierName, "") ^ true)) ? render : GeneratedOutlineSupport1.outline75(qualifierName, ".", render);
        }

        private final String qualifierName(DeclarationDescriptor declarationDescriptor) {
            if (declarationDescriptor instanceof ClassDescriptor) {
                return qualifiedNameForSourceCode((ClassifierDescriptor) declarationDescriptor);
            }
            if (!(declarationDescriptor instanceof PackageFragmentDescriptor)) {
                return null;
            }
            FqNameUnsafe unsafe = ((PackageFragmentDescriptor) declarationDescriptor).getFqName().toUnsafe();
            Intrinsics.checkExpressionValueIsNotNull(unsafe, "descriptor.fqName.toUnsafe()");
            return RenderingUtilsKt.render(unsafe);
        }

        @Override // kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy
        @NotNull
        public String renderClassifier(@NotNull ClassifierDescriptor classifier, @NotNull DescriptorRenderer renderer) {
            Intrinsics.checkParameterIsNotNull(classifier, "classifier");
            Intrinsics.checkParameterIsNotNull(renderer, "renderer");
            return qualifiedNameForSourceCode(classifier);
        }
    }

    @NotNull
    String renderClassifier(@NotNull ClassifierDescriptor classifierDescriptor, @NotNull DescriptorRenderer descriptorRenderer);
}
