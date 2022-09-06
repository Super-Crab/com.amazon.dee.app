package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: descriptorUtil.kt */
/* loaded from: classes2.dex */
public final class DescriptorUtilKt {
    @Nullable
    public static final ClassDescriptor resolveClassByFqName(@NotNull ModuleDescriptor resolveClassByFqName, @NotNull FqName fqName, @NotNull LookupLocation lookupLocation) {
        ClassifierDescriptor classifierDescriptor;
        MemberScope unsubstitutedInnerClassesScope;
        Intrinsics.checkParameterIsNotNull(resolveClassByFqName, "$this$resolveClassByFqName");
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(lookupLocation, "lookupLocation");
        if (fqName.isRoot()) {
            return null;
        }
        FqName parent = fqName.parent();
        Intrinsics.checkExpressionValueIsNotNull(parent, "fqName.parent()");
        MemberScope memberScope = resolveClassByFqName.getPackage(parent).getMemberScope();
        Name shortName = fqName.shortName();
        Intrinsics.checkExpressionValueIsNotNull(shortName, "fqName.shortName()");
        ClassifierDescriptor mo12030getContributedClassifier = memberScope.mo12030getContributedClassifier(shortName, lookupLocation);
        if (!(mo12030getContributedClassifier instanceof ClassDescriptor)) {
            mo12030getContributedClassifier = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) mo12030getContributedClassifier;
        if (classDescriptor != null) {
            return classDescriptor;
        }
        FqName parent2 = fqName.parent();
        Intrinsics.checkExpressionValueIsNotNull(parent2, "fqName.parent()");
        ClassDescriptor resolveClassByFqName2 = resolveClassByFqName(resolveClassByFqName, parent2, lookupLocation);
        if (resolveClassByFqName2 == null || (unsubstitutedInnerClassesScope = resolveClassByFqName2.getUnsubstitutedInnerClassesScope()) == null) {
            classifierDescriptor = null;
        } else {
            Name shortName2 = fqName.shortName();
            Intrinsics.checkExpressionValueIsNotNull(shortName2, "fqName.shortName()");
            classifierDescriptor = unsubstitutedInnerClassesScope.mo12030getContributedClassifier(shortName2, lookupLocation);
        }
        if (!(classifierDescriptor instanceof ClassDescriptor)) {
            classifierDescriptor = null;
        }
        return (ClassDescriptor) classifierDescriptor;
    }
}
