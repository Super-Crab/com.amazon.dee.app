package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public abstract class AbstractClassTypeConstructor extends AbstractTypeConstructor implements TypeConstructor {
    private int hashCode;

    /* JADX WARN: Removed duplicated region for block: B:23:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static /* synthetic */ void $$$reportNull$$$0(int r9) {
        /*
            r0 = 4
            r1 = 3
            r2 = 1
            if (r9 == r2) goto Lc
            if (r9 == r1) goto Lc
            if (r9 == r0) goto Lc
            java.lang.String r3 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            goto Le
        Lc:
            java.lang.String r3 = "@NotNull method %s.%s must not return null"
        Le:
            r4 = 2
            if (r9 == r2) goto L17
            if (r9 == r1) goto L17
            if (r9 == r0) goto L17
            r5 = r1
            goto L18
        L17:
            r5 = r4
        L18:
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/types/AbstractClassTypeConstructor"
            r7 = 0
            if (r9 == r2) goto L2f
            if (r9 == r4) goto L2a
            if (r9 == r1) goto L2f
            if (r9 == r0) goto L2f
            java.lang.String r8 = "storageManager"
            r5[r7] = r8
            goto L31
        L2a:
            java.lang.String r8 = "descriptor"
            r5[r7] = r8
            goto L31
        L2f:
            r5[r7] = r6
        L31:
            if (r9 == r2) goto L3f
            if (r9 == r1) goto L3a
            if (r9 == r0) goto L3a
            r5[r2] = r6
            goto L43
        L3a:
            java.lang.String r6 = "getAdditionalNeighboursInSupertypeGraph"
            r5[r2] = r6
            goto L43
        L3f:
            java.lang.String r6 = "getBuiltIns"
            r5[r2] = r6
        L43:
            if (r9 == r2) goto L54
            if (r9 == r4) goto L50
            if (r9 == r1) goto L54
            if (r9 == r0) goto L54
            java.lang.String r6 = "<init>"
            r5[r4] = r6
            goto L54
        L50:
            java.lang.String r6 = "hasMeaningfulFqName"
            r5[r4] = r6
        L54:
            java.lang.String r3 = java.lang.String.format(r3, r5)
            if (r9 == r2) goto L64
            if (r9 == r1) goto L64
            if (r9 == r0) goto L64
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r9.<init>(r3)
            goto L69
        L64:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>(r3)
        L69:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor.$$$reportNull$$$0(int):void");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractClassTypeConstructor(@NotNull StorageManager storageManager) {
        super(storageManager);
        if (storageManager == null) {
            $$$reportNull$$$0(0);
        }
        this.hashCode = 0;
    }

    private static boolean areFqNamesEqual(ClassDescriptor classDescriptor, ClassDescriptor classDescriptor2) {
        if (!classDescriptor.getName().equals(classDescriptor2.getName())) {
            return false;
        }
        DeclarationDescriptor mo11607getContainingDeclaration = classDescriptor.mo11607getContainingDeclaration();
        for (DeclarationDescriptor mo11607getContainingDeclaration2 = classDescriptor2.mo11607getContainingDeclaration(); mo11607getContainingDeclaration != null && mo11607getContainingDeclaration2 != null; mo11607getContainingDeclaration2 = mo11607getContainingDeclaration2.mo11607getContainingDeclaration()) {
            if (mo11607getContainingDeclaration instanceof ModuleDescriptor) {
                return mo11607getContainingDeclaration2 instanceof ModuleDescriptor;
            }
            if (mo11607getContainingDeclaration2 instanceof ModuleDescriptor) {
                return false;
            }
            if (mo11607getContainingDeclaration instanceof PackageFragmentDescriptor) {
                return (mo11607getContainingDeclaration2 instanceof PackageFragmentDescriptor) && ((PackageFragmentDescriptor) mo11607getContainingDeclaration).getFqName().equals(((PackageFragmentDescriptor) mo11607getContainingDeclaration2).getFqName());
            } else if ((mo11607getContainingDeclaration2 instanceof PackageFragmentDescriptor) || !mo11607getContainingDeclaration.getName().equals(mo11607getContainingDeclaration2.getName())) {
                return false;
            } else {
                mo11607getContainingDeclaration = mo11607getContainingDeclaration.mo11607getContainingDeclaration();
            }
        }
        return true;
    }

    private static boolean hasMeaningfulFqName(@NotNull ClassifierDescriptor classifierDescriptor) {
        if (classifierDescriptor == null) {
            $$$reportNull$$$0(2);
        }
        return !ErrorUtils.isError(classifierDescriptor) && !DescriptorUtils.isLocal(classifierDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
    @Nullable
    public KotlinType defaultSupertypeIfEmpty() {
        if (KotlinBuiltIns.isSpecialClassWithNoSupertypes(mo12085getDeclarationDescriptor())) {
            return null;
        }
        return getBuiltIns().getAnyType();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypeConstructor) || obj.hashCode() != hashCode()) {
            return false;
        }
        TypeConstructor typeConstructor = (TypeConstructor) obj;
        if (typeConstructor.getParameters().size() != getParameters().size()) {
            return false;
        }
        ClassDescriptor mo12085getDeclarationDescriptor = mo12085getDeclarationDescriptor();
        ClassifierDescriptor mo12085getDeclarationDescriptor2 = typeConstructor.mo12085getDeclarationDescriptor();
        if (hasMeaningfulFqName(mo12085getDeclarationDescriptor) && ((mo12085getDeclarationDescriptor2 == null || hasMeaningfulFqName(mo12085getDeclarationDescriptor2)) && (mo12085getDeclarationDescriptor2 instanceof ClassDescriptor))) {
            return areFqNamesEqual(mo12085getDeclarationDescriptor, (ClassDescriptor) mo12085getDeclarationDescriptor2);
        }
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
    @NotNull
    protected Collection<KotlinType> getAdditionalNeighboursInSupertypeGraph(boolean z) {
        DeclarationDescriptor mo11607getContainingDeclaration = mo12085getDeclarationDescriptor().mo11607getContainingDeclaration();
        if (!(mo11607getContainingDeclaration instanceof ClassDescriptor)) {
            List emptyList = Collections.emptyList();
            if (emptyList == null) {
                $$$reportNull$$$0(3);
            }
            return emptyList;
        }
        SmartList smartList = new SmartList();
        ClassDescriptor classDescriptor = (ClassDescriptor) mo11607getContainingDeclaration;
        smartList.add(classDescriptor.getDefaultType());
        ClassDescriptor mo11505getCompanionObjectDescriptor = classDescriptor.mo11505getCompanionObjectDescriptor();
        if (z && mo11505getCompanionObjectDescriptor != null) {
            smartList.add(mo11505getCompanionObjectDescriptor.getDefaultType());
        }
        return smartList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public KotlinBuiltIns getBuiltIns() {
        KotlinBuiltIns builtIns = DescriptorUtilsKt.getBuiltIns(mo12085getDeclarationDescriptor());
        if (builtIns == null) {
            $$$reportNull$$$0(1);
        }
        return builtIns;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor, kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: getDeclarationDescriptor */
    public abstract ClassDescriptor mo12085getDeclarationDescriptor();

    public final int hashCode() {
        int identityHashCode;
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        ClassDescriptor mo12085getDeclarationDescriptor = mo12085getDeclarationDescriptor();
        if (hasMeaningfulFqName(mo12085getDeclarationDescriptor)) {
            identityHashCode = DescriptorUtils.getFqName(mo12085getDeclarationDescriptor).hashCode();
        } else {
            identityHashCode = System.identityHashCode(this);
        }
        this.hashCode = identityHashCode;
        return identityHashCode;
    }
}
