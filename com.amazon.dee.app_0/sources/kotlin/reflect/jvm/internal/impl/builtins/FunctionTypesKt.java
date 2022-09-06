package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.functions.BuiltInFictitiousFunctionClassFactory;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.BuiltInAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: functionTypes.kt */
/* loaded from: classes2.dex */
public final class FunctionTypesKt {
    @JvmOverloads
    @NotNull
    public static final SimpleType createFunctionType(@NotNull KotlinBuiltIns builtIns, @NotNull Annotations annotations, @Nullable KotlinType kotlinType, @NotNull List<? extends KotlinType> parameterTypes, @Nullable List<Name> list, @NotNull KotlinType returnType, boolean z) {
        Map emptyMap;
        List<? extends AnnotationDescriptor> plus;
        Intrinsics.checkParameterIsNotNull(builtIns, "builtIns");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(parameterTypes, "parameterTypes");
        Intrinsics.checkParameterIsNotNull(returnType, "returnType");
        List<TypeProjection> functionTypeArgumentProjections = getFunctionTypeArgumentProjections(kotlinType, parameterTypes, list, returnType, builtIns);
        int size = parameterTypes.size();
        if (kotlinType != null) {
            size++;
        }
        ClassDescriptor suspendFunction = z ? builtIns.getSuspendFunction(size) : builtIns.getFunction(size);
        Intrinsics.checkExpressionValueIsNotNull(suspendFunction, "if (suspendFunction) bui…tFunction(parameterCount)");
        if (kotlinType != null) {
            FqName fqName = KotlinBuiltIns.FQ_NAMES.extensionFunctionType;
            Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.extensionFunctionType");
            if (annotations.mo11701findAnnotation(fqName) == null) {
                Annotations.Companion companion = Annotations.Companion;
                FqName fqName2 = KotlinBuiltIns.FQ_NAMES.extensionFunctionType;
                Intrinsics.checkExpressionValueIsNotNull(fqName2, "KotlinBuiltIns.FQ_NAMES.extensionFunctionType");
                emptyMap = MapsKt__MapsKt.emptyMap();
                plus = CollectionsKt___CollectionsKt.plus(annotations, new BuiltInAnnotationDescriptor(builtIns, fqName2, emptyMap));
                annotations = companion.create(plus);
            }
        }
        return KotlinTypeFactory.simpleNotNullType(annotations, suspendFunction, functionTypeArgumentProjections);
    }

    public static /* synthetic */ SimpleType createFunctionType$default(KotlinBuiltIns kotlinBuiltIns, Annotations annotations, KotlinType kotlinType, List list, List list2, KotlinType kotlinType2, boolean z, int i, Object obj) {
        if ((i & 64) != 0) {
            z = false;
        }
        return createFunctionType(kotlinBuiltIns, annotations, kotlinType, list, list2, kotlinType2, z);
    }

    @Nullable
    public static final Name extractParameterNameFromFunctionTypeArgument(@NotNull KotlinType extractParameterNameFromFunctionTypeArgument) {
        String value;
        Intrinsics.checkParameterIsNotNull(extractParameterNameFromFunctionTypeArgument, "$this$extractParameterNameFromFunctionTypeArgument");
        Annotations mo12070getAnnotations = extractParameterNameFromFunctionTypeArgument.mo12070getAnnotations();
        FqName fqName = KotlinBuiltIns.FQ_NAMES.parameterName;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.parameterName");
        AnnotationDescriptor mo11701findAnnotation = mo12070getAnnotations.mo11701findAnnotation(fqName);
        if (mo11701findAnnotation != null) {
            Object singleOrNull = CollectionsKt.singleOrNull(mo11701findAnnotation.getAllValueArguments().values());
            if (!(singleOrNull instanceof StringValue)) {
                singleOrNull = null;
            }
            StringValue stringValue = (StringValue) singleOrNull;
            if (stringValue != null && (value = stringValue.getValue()) != null) {
                if (!Name.isValidIdentifier(value)) {
                    value = null;
                }
                if (value != null) {
                    return Name.identifier(value);
                }
            }
        }
        return null;
    }

    @NotNull
    public static final List<TypeProjection> getFunctionTypeArgumentProjections(@Nullable KotlinType kotlinType, @NotNull List<? extends KotlinType> parameterTypes, @Nullable List<Name> list, @NotNull KotlinType returnType, @NotNull KotlinBuiltIns builtIns) {
        Name name;
        Map mapOf;
        List<? extends AnnotationDescriptor> plus;
        Intrinsics.checkParameterIsNotNull(parameterTypes, "parameterTypes");
        Intrinsics.checkParameterIsNotNull(returnType, "returnType");
        Intrinsics.checkParameterIsNotNull(builtIns, "builtIns");
        int i = 0;
        ArrayList arrayList = new ArrayList(parameterTypes.size() + (kotlinType != null ? 1 : 0) + 1);
        kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList, kotlinType != null ? TypeUtilsKt.asTypeProjection(kotlinType) : null);
        for (Object obj : parameterTypes) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            KotlinType kotlinType2 = (KotlinType) obj;
            if (list == null || (name = list.get(i)) == null || name.isSpecial()) {
                name = null;
            }
            if (name != null) {
                FqName fqName = KotlinBuiltIns.FQ_NAMES.parameterName;
                Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.parameterName");
                Name identifier = Name.identifier("name");
                String asString = name.asString();
                Intrinsics.checkExpressionValueIsNotNull(asString, "name.asString()");
                mapOf = MapsKt__MapsJVMKt.mapOf(TuplesKt.to(identifier, new StringValue(asString)));
                BuiltInAnnotationDescriptor builtInAnnotationDescriptor = new BuiltInAnnotationDescriptor(builtIns, fqName, mapOf);
                Annotations.Companion companion = Annotations.Companion;
                plus = CollectionsKt___CollectionsKt.plus(kotlinType2.mo12070getAnnotations(), builtInAnnotationDescriptor);
                kotlinType2 = TypeUtilsKt.replaceAnnotations(kotlinType2, companion.create(plus));
            }
            arrayList.add(TypeUtilsKt.asTypeProjection(kotlinType2));
            i = i2;
        }
        arrayList.add(TypeUtilsKt.asTypeProjection(returnType));
        return arrayList;
    }

    @Nullable
    public static final FunctionClassDescriptor.Kind getFunctionalClassKind(@NotNull DeclarationDescriptor getFunctionalClassKind) {
        Intrinsics.checkParameterIsNotNull(getFunctionalClassKind, "$this$getFunctionalClassKind");
        if ((getFunctionalClassKind instanceof ClassDescriptor) && KotlinBuiltIns.isUnderKotlinPackage(getFunctionalClassKind)) {
            return getFunctionalClassKind(DescriptorUtilsKt.getFqNameUnsafe(getFunctionalClassKind));
        }
        return null;
    }

    @Nullable
    public static final KotlinType getReceiverTypeFromFunctionType(@NotNull KotlinType getReceiverTypeFromFunctionType) {
        Intrinsics.checkParameterIsNotNull(getReceiverTypeFromFunctionType, "$this$getReceiverTypeFromFunctionType");
        boolean isBuiltinFunctionalType = isBuiltinFunctionalType(getReceiverTypeFromFunctionType);
        if (!_Assertions.ENABLED || isBuiltinFunctionalType) {
            if (!isTypeAnnotatedWithExtensionFunctionType(getReceiverTypeFromFunctionType)) {
                return null;
            }
            return ((TypeProjection) CollectionsKt.first((List<? extends Object>) getReceiverTypeFromFunctionType.getArguments())).getType();
        }
        throw new AssertionError("Not a function type: " + getReceiverTypeFromFunctionType);
    }

    @NotNull
    public static final KotlinType getReturnTypeFromFunctionType(@NotNull KotlinType getReturnTypeFromFunctionType) {
        Intrinsics.checkParameterIsNotNull(getReturnTypeFromFunctionType, "$this$getReturnTypeFromFunctionType");
        boolean isBuiltinFunctionalType = isBuiltinFunctionalType(getReturnTypeFromFunctionType);
        if (!_Assertions.ENABLED || isBuiltinFunctionalType) {
            KotlinType type = ((TypeProjection) CollectionsKt.last((List<? extends Object>) getReturnTypeFromFunctionType.getArguments())).getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "arguments.last().type");
            return type;
        }
        throw new AssertionError("Not a function type: " + getReturnTypeFromFunctionType);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [boolean] */
    @NotNull
    public static final List<TypeProjection> getValueParameterTypesFromFunctionType(@NotNull KotlinType getValueParameterTypesFromFunctionType) {
        Intrinsics.checkParameterIsNotNull(getValueParameterTypesFromFunctionType, "$this$getValueParameterTypesFromFunctionType");
        boolean isBuiltinFunctionalType = isBuiltinFunctionalType(getValueParameterTypesFromFunctionType);
        if (_Assertions.ENABLED && !isBuiltinFunctionalType) {
            throw new AssertionError("Not a function type: " + getValueParameterTypesFromFunctionType);
        }
        List<TypeProjection> arguments = getValueParameterTypesFromFunctionType.getArguments();
        ?? isBuiltinExtensionFunctionalType = isBuiltinExtensionFunctionalType(getValueParameterTypesFromFunctionType);
        boolean z = true;
        int size = arguments.size() - 1;
        if (isBuiltinExtensionFunctionalType > size) {
            z = false;
        }
        if (!_Assertions.ENABLED || z) {
            return arguments.subList(isBuiltinExtensionFunctionalType == true ? 1 : 0, size);
        }
        throw new AssertionError("Not an exact function type: " + getValueParameterTypesFromFunctionType);
    }

    public static final boolean isBuiltinExtensionFunctionalType(@NotNull KotlinType isBuiltinExtensionFunctionalType) {
        Intrinsics.checkParameterIsNotNull(isBuiltinExtensionFunctionalType, "$this$isBuiltinExtensionFunctionalType");
        return isBuiltinFunctionalType(isBuiltinExtensionFunctionalType) && isTypeAnnotatedWithExtensionFunctionType(isBuiltinExtensionFunctionalType);
    }

    public static final boolean isBuiltinFunctionalType(@NotNull KotlinType isBuiltinFunctionalType) {
        Intrinsics.checkParameterIsNotNull(isBuiltinFunctionalType, "$this$isBuiltinFunctionalType");
        ClassifierDescriptor mo12085getDeclarationDescriptor = isBuiltinFunctionalType.mo12131getConstructor().mo12085getDeclarationDescriptor();
        FunctionClassDescriptor.Kind functionalClassKind = mo12085getDeclarationDescriptor != null ? getFunctionalClassKind(mo12085getDeclarationDescriptor) : null;
        return functionalClassKind == FunctionClassDescriptor.Kind.Function || functionalClassKind == FunctionClassDescriptor.Kind.SuspendFunction;
    }

    public static final boolean isFunctionType(@NotNull KotlinType isFunctionType) {
        Intrinsics.checkParameterIsNotNull(isFunctionType, "$this$isFunctionType");
        ClassifierDescriptor mo12085getDeclarationDescriptor = isFunctionType.mo12131getConstructor().mo12085getDeclarationDescriptor();
        return (mo12085getDeclarationDescriptor != null ? getFunctionalClassKind(mo12085getDeclarationDescriptor) : null) == FunctionClassDescriptor.Kind.Function;
    }

    public static final boolean isSuspendFunctionType(@NotNull KotlinType isSuspendFunctionType) {
        Intrinsics.checkParameterIsNotNull(isSuspendFunctionType, "$this$isSuspendFunctionType");
        ClassifierDescriptor mo12085getDeclarationDescriptor = isSuspendFunctionType.mo12131getConstructor().mo12085getDeclarationDescriptor();
        return (mo12085getDeclarationDescriptor != null ? getFunctionalClassKind(mo12085getDeclarationDescriptor) : null) == FunctionClassDescriptor.Kind.SuspendFunction;
    }

    private static final boolean isTypeAnnotatedWithExtensionFunctionType(@NotNull KotlinType kotlinType) {
        Annotations mo12070getAnnotations = kotlinType.mo12070getAnnotations();
        FqName fqName = KotlinBuiltIns.FQ_NAMES.extensionFunctionType;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.extensionFunctionType");
        return mo12070getAnnotations.mo11701findAnnotation(fqName) != null;
    }

    private static final FunctionClassDescriptor.Kind getFunctionalClassKind(@NotNull FqNameUnsafe fqNameUnsafe) {
        if (!fqNameUnsafe.isSafe() || fqNameUnsafe.isRoot()) {
            return null;
        }
        BuiltInFictitiousFunctionClassFactory.Companion companion = BuiltInFictitiousFunctionClassFactory.Companion;
        String asString = fqNameUnsafe.shortName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "shortName().asString()");
        FqName parent = fqNameUnsafe.toSafe().parent();
        Intrinsics.checkExpressionValueIsNotNull(parent, "toSafe().parent()");
        return companion.getFunctionalClassKind(asString, parent);
    }
}
