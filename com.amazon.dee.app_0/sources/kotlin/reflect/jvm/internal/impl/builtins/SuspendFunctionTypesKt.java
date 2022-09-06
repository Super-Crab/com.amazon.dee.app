package kotlin.reflect.jvm.internal.impl.builtins;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.MutableClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: suspendFunctionTypes.kt */
/* loaded from: classes2.dex */
public final class SuspendFunctionTypesKt {
    private static final MutableClassDescriptor FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL;
    private static final MutableClassDescriptor FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE;

    static {
        List<TypeParameterDescriptor> listOf;
        List<TypeParameterDescriptor> listOf2;
        ModuleDescriptor errorModule = ErrorUtils.getErrorModule();
        Intrinsics.checkExpressionValueIsNotNull(errorModule, "ErrorUtils.getErrorModule()");
        FqName fqName = DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "DescriptorUtils.COROUTIN…KAGE_FQ_NAME_EXPERIMENTAL");
        MutableClassDescriptor mutableClassDescriptor = new MutableClassDescriptor(new EmptyPackageFragmentDescriptor(errorModule, fqName), ClassKind.INTERFACE, false, false, DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL.shortName(), SourceElement.NO_SOURCE, LockBasedStorageManager.NO_LOCKS);
        mutableClassDescriptor.setModality(Modality.ABSTRACT);
        mutableClassDescriptor.setVisibility(Visibilities.PUBLIC);
        listOf = CollectionsKt__CollectionsJVMKt.listOf(TypeParameterDescriptorImpl.createWithDefaultBound(mutableClassDescriptor, Annotations.Companion.getEMPTY(), false, Variance.IN_VARIANCE, Name.identifier(ExifInterface.GPS_DIRECTION_TRUE), 0, LockBasedStorageManager.NO_LOCKS));
        mutableClassDescriptor.setTypeParameterDescriptors(listOf);
        mutableClassDescriptor.createTypeConstructor();
        FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL = mutableClassDescriptor;
        ModuleDescriptor errorModule2 = ErrorUtils.getErrorModule();
        Intrinsics.checkExpressionValueIsNotNull(errorModule2, "ErrorUtils.getErrorModule()");
        FqName fqName2 = DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_RELEASE;
        Intrinsics.checkExpressionValueIsNotNull(fqName2, "DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_RELEASE");
        MutableClassDescriptor mutableClassDescriptor2 = new MutableClassDescriptor(new EmptyPackageFragmentDescriptor(errorModule2, fqName2), ClassKind.INTERFACE, false, false, DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_RELEASE.shortName(), SourceElement.NO_SOURCE, LockBasedStorageManager.NO_LOCKS);
        mutableClassDescriptor2.setModality(Modality.ABSTRACT);
        mutableClassDescriptor2.setVisibility(Visibilities.PUBLIC);
        listOf2 = CollectionsKt__CollectionsJVMKt.listOf(TypeParameterDescriptorImpl.createWithDefaultBound(mutableClassDescriptor2, Annotations.Companion.getEMPTY(), false, Variance.IN_VARIANCE, Name.identifier(ExifInterface.GPS_DIRECTION_TRUE), 0, LockBasedStorageManager.NO_LOCKS));
        mutableClassDescriptor2.setTypeParameterDescriptors(listOf2);
        mutableClassDescriptor2.createTypeConstructor();
        FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE = mutableClassDescriptor2;
    }

    public static final boolean isContinuation(@Nullable FqName fqName, boolean z) {
        if (z) {
            return Intrinsics.areEqual(fqName, DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_RELEASE);
        }
        return Intrinsics.areEqual(fqName, DescriptorUtils.CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL);
    }

    @NotNull
    public static final SimpleType transformSuspendFunctionToRuntimeFunctionType(@NotNull KotlinType suspendFunType, boolean z) {
        int collectionSizeOrDefault;
        TypeConstructor mo11528getTypeConstructor;
        List listOf;
        List plus;
        Intrinsics.checkParameterIsNotNull(suspendFunType, "suspendFunType");
        boolean isSuspendFunctionType = FunctionTypesKt.isSuspendFunctionType(suspendFunType);
        if (_Assertions.ENABLED && !isSuspendFunctionType) {
            throw new AssertionError("This type should be suspend function type: " + suspendFunType);
        }
        KotlinBuiltIns builtIns = TypeUtilsKt.getBuiltIns(suspendFunType);
        Annotations mo12070getAnnotations = suspendFunType.mo12070getAnnotations();
        KotlinType receiverTypeFromFunctionType = FunctionTypesKt.getReceiverTypeFromFunctionType(suspendFunType);
        List<TypeProjection> valueParameterTypesFromFunctionType = FunctionTypesKt.getValueParameterTypesFromFunctionType(suspendFunType);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(valueParameterTypesFromFunctionType, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (TypeProjection typeProjection : valueParameterTypesFromFunctionType) {
            arrayList.add(typeProjection.getType());
        }
        Annotations empty = Annotations.Companion.getEMPTY();
        if (z) {
            mo11528getTypeConstructor = FAKE_CONTINUATION_CLASS_DESCRIPTOR_RELEASE.mo11528getTypeConstructor();
        } else {
            mo11528getTypeConstructor = FAKE_CONTINUATION_CLASS_DESCRIPTOR_EXPERIMENTAL.mo11528getTypeConstructor();
        }
        TypeConstructor typeConstructor = mo11528getTypeConstructor;
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "if (isReleaseCoroutines)…ERIMENTAL.typeConstructor");
        listOf = CollectionsKt__CollectionsJVMKt.listOf(TypeUtilsKt.asTypeProjection(FunctionTypesKt.getReturnTypeFromFunctionType(suspendFunType)));
        plus = CollectionsKt___CollectionsKt.plus((Collection<? extends Object>) ((Collection) arrayList), (Object) KotlinTypeFactory.simpleType$default(empty, typeConstructor, listOf, false, null, 16, null));
        SimpleType nullableAnyType = TypeUtilsKt.getBuiltIns(suspendFunType).getNullableAnyType();
        Intrinsics.checkExpressionValueIsNotNull(nullableAnyType, "suspendFunType.builtIns.nullableAnyType");
        return FunctionTypesKt.createFunctionType$default(builtIns, mo12070getAnnotations, receiverTypeFromFunctionType, plus, null, nullableAnyType, false, 64, null).mo12132makeNullableAsSpecified(suspendFunType.isMarkedNullable());
    }
}
