package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: CompanionObjectMapping.kt */
/* loaded from: classes2.dex */
public final class CompanionObjectMapping {
    public static final CompanionObjectMapping INSTANCE = new CompanionObjectMapping();
    private static final LinkedHashSet<ClassId> classIds;

    static {
        int collectionSizeOrDefault;
        List plus;
        List plus2;
        List<FqName> plus3;
        Set<PrimitiveType> set = PrimitiveType.NUMBER_TYPES;
        Intrinsics.checkExpressionValueIsNotNull(set, "PrimitiveType.NUMBER_TYPES");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(set, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (PrimitiveType primitiveType : set) {
            arrayList.add(KotlinBuiltIns.getPrimitiveFqName(primitiveType));
        }
        plus = CollectionsKt___CollectionsKt.plus((Collection<? extends Object>) ((Collection) arrayList), (Object) KotlinBuiltIns.FQ_NAMES.string.toSafe());
        plus2 = CollectionsKt___CollectionsKt.plus((Collection<? extends Object>) ((Collection) plus), (Object) KotlinBuiltIns.FQ_NAMES._boolean.toSafe());
        plus3 = CollectionsKt___CollectionsKt.plus((Collection<? extends Object>) ((Collection) plus2), (Object) KotlinBuiltIns.FQ_NAMES._enum.toSafe());
        LinkedHashSet<ClassId> linkedHashSet = new LinkedHashSet<>();
        for (FqName fqName : plus3) {
            linkedHashSet.add(ClassId.topLevel(fqName));
        }
        classIds = linkedHashSet;
    }

    private CompanionObjectMapping() {
    }

    @NotNull
    public final Set<ClassId> allClassesWithIntrinsicCompanions() {
        Set<ClassId> unmodifiableSet = Collections.unmodifiableSet(classIds);
        Intrinsics.checkExpressionValueIsNotNull(unmodifiableSet, "Collections.unmodifiableSet(classIds)");
        return unmodifiableSet;
    }

    public final boolean isMappedIntrinsicCompanionObject(@NotNull ClassDescriptor classDescriptor) {
        boolean contains;
        Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        if (DescriptorUtils.isCompanionObject(classDescriptor)) {
            LinkedHashSet<ClassId> linkedHashSet = classIds;
            ClassId classId = DescriptorUtilsKt.getClassId(classDescriptor);
            contains = CollectionsKt___CollectionsKt.contains(linkedHashSet, classId != null ? classId.getOuterClassId() : null);
            if (contains) {
                return true;
            }
        }
        return false;
    }
}
