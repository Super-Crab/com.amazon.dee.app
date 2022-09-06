package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UnsignedType.kt */
/* loaded from: classes2.dex */
public final class UnsignedTypes {
    public static final UnsignedTypes INSTANCE = new UnsignedTypes();
    private static final HashMap<ClassId, ClassId> arrayClassIdToUnsignedClassId;
    private static final Set<Name> arrayClassesShortNames;
    private static final HashMap<ClassId, ClassId> unsignedClassIdToArrayClassId;
    private static final Set<Name> unsignedTypeNames;

    static {
        Set<Name> set;
        UnsignedType[] values;
        UnsignedType[] values2 = UnsignedType.values();
        ArrayList arrayList = new ArrayList(values2.length);
        for (UnsignedType unsignedType : values2) {
            arrayList.add(unsignedType.getTypeName());
        }
        set = CollectionsKt___CollectionsKt.toSet(arrayList);
        unsignedTypeNames = set;
        arrayClassIdToUnsignedClassId = new HashMap<>();
        unsignedClassIdToArrayClassId = new HashMap<>();
        UnsignedType[] values3 = UnsignedType.values();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (UnsignedType unsignedType2 : values3) {
            linkedHashSet.add(unsignedType2.getArrayClassId().getShortClassName());
        }
        arrayClassesShortNames = linkedHashSet;
        for (UnsignedType unsignedType3 : UnsignedType.values()) {
            arrayClassIdToUnsignedClassId.put(unsignedType3.getArrayClassId(), unsignedType3.getClassId());
            unsignedClassIdToArrayClassId.put(unsignedType3.getClassId(), unsignedType3.getArrayClassId());
        }
    }

    private UnsignedTypes() {
    }

    @Nullable
    public final ClassId getUnsignedClassIdByArrayClassId(@NotNull ClassId arrayClassId) {
        Intrinsics.checkParameterIsNotNull(arrayClassId, "arrayClassId");
        return arrayClassIdToUnsignedClassId.get(arrayClassId);
    }

    public final boolean isShortNameOfUnsignedArray(@NotNull Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return arrayClassesShortNames.contains(name);
    }

    public final boolean isUnsignedClass(@NotNull DeclarationDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        DeclarationDescriptor mo11607getContainingDeclaration = descriptor.mo11607getContainingDeclaration();
        return (mo11607getContainingDeclaration instanceof PackageFragmentDescriptor) && Intrinsics.areEqual(((PackageFragmentDescriptor) mo11607getContainingDeclaration).getFqName(), KotlinBuiltIns.BUILT_INS_PACKAGE_FQ_NAME) && unsignedTypeNames.contains(descriptor.getName());
    }

    public final boolean isUnsignedType(@NotNull KotlinType type) {
        ClassifierDescriptor mo12085getDeclarationDescriptor;
        Intrinsics.checkParameterIsNotNull(type, "type");
        if (!TypeUtils.noExpectedType(type) && (mo12085getDeclarationDescriptor = type.mo12131getConstructor().mo12085getDeclarationDescriptor()) != null) {
            Intrinsics.checkExpressionValueIsNotNull(mo12085getDeclarationDescriptor, "type.constructor.declara…escriptor ?: return false");
            return isUnsignedClass(mo12085getDeclarationDescriptor);
        }
        return false;
    }
}
