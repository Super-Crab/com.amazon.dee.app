package kotlin.reflect.jvm.internal.impl.load.kotlin;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeEnhancementKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.SimpleClassicTypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: typeSignatureMapping.kt */
/* loaded from: classes3.dex */
public final class TypeSignatureMappingKt {
    private static final <T> T boxTypeIfNeeded(@NotNull JvmTypeFactory<T> jvmTypeFactory, T t, boolean z) {
        return z ? jvmTypeFactory.boxType(t) : t;
    }

    @NotNull
    public static final String computeInternalName(@NotNull ClassDescriptor klass, @NotNull TypeMappingConfiguration<?> typeMappingConfiguration) {
        String replace$default;
        Intrinsics.checkParameterIsNotNull(klass, "klass");
        Intrinsics.checkParameterIsNotNull(typeMappingConfiguration, "typeMappingConfiguration");
        String predefinedFullInternalNameForClass = typeMappingConfiguration.getPredefinedFullInternalNameForClass(klass);
        if (predefinedFullInternalNameForClass != null) {
            return predefinedFullInternalNameForClass;
        }
        DeclarationDescriptor mo11607getContainingDeclaration = klass.mo11607getContainingDeclaration();
        Intrinsics.checkExpressionValueIsNotNull(mo11607getContainingDeclaration, "klass.containingDeclaration");
        Name safeIdentifier = SpecialNames.safeIdentifier(klass.getName());
        Intrinsics.checkExpressionValueIsNotNull(safeIdentifier, "SpecialNames.safeIdentifier(klass.name)");
        String identifier = safeIdentifier.getIdentifier();
        Intrinsics.checkExpressionValueIsNotNull(identifier, "SpecialNames.safeIdentifier(klass.name).identifier");
        if (mo11607getContainingDeclaration instanceof PackageFragmentDescriptor) {
            FqName fqName = ((PackageFragmentDescriptor) mo11607getContainingDeclaration).getFqName();
            if (fqName.isRoot()) {
                return identifier;
            }
            StringBuilder sb = new StringBuilder();
            String asString = fqName.asString();
            Intrinsics.checkExpressionValueIsNotNull(asString, "fqName.asString()");
            replace$default = StringsKt__StringsJVMKt.replace$default(asString, '.', '/', false, 4, (Object) null);
            sb.append(replace$default);
            sb.append('/');
            sb.append(identifier);
            return sb.toString();
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) (!(mo11607getContainingDeclaration instanceof ClassDescriptor) ? null : mo11607getContainingDeclaration);
        if (classDescriptor != null) {
            String predefinedInternalNameForClass = typeMappingConfiguration.getPredefinedInternalNameForClass(classDescriptor);
            if (predefinedInternalNameForClass == null) {
                predefinedInternalNameForClass = computeInternalName(classDescriptor, typeMappingConfiguration);
            }
            return GeneratedOutlineSupport1.outline48(predefinedInternalNameForClass, '$', identifier);
        }
        throw new IllegalArgumentException("Unexpected container: " + mo11607getContainingDeclaration + " for " + klass);
    }

    public static /* synthetic */ String computeInternalName$default(ClassDescriptor classDescriptor, TypeMappingConfiguration typeMappingConfiguration, int i, Object obj) {
        if ((i & 2) != 0) {
            typeMappingConfiguration = TypeMappingConfigurationImpl.INSTANCE;
        }
        return computeInternalName(classDescriptor, typeMappingConfiguration);
    }

    public static final boolean hasVoidReturnType(@NotNull CallableDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (descriptor instanceof ConstructorDescriptor) {
            return true;
        }
        KotlinType returnType = descriptor.getReturnType();
        if (returnType == null) {
            Intrinsics.throwNpe();
        }
        if (KotlinBuiltIns.isUnit(returnType)) {
            KotlinType returnType2 = descriptor.getReturnType();
            if (returnType2 == null) {
                Intrinsics.throwNpe();
            }
            if (!TypeUtils.isNullableType(returnType2) && !(descriptor instanceof PropertyGetterDescriptor)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public static final <T> T mapBuiltInType(@NotNull TypeSystemCommonBackendContext mapBuiltInType, @NotNull KotlinTypeMarker type, @NotNull JvmTypeFactory<T> typeFactory, @NotNull TypeMappingMode mode) {
        Intrinsics.checkParameterIsNotNull(mapBuiltInType, "$this$mapBuiltInType");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(typeFactory, "typeFactory");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        TypeConstructorMarker typeConstructor = mapBuiltInType.typeConstructor(type);
        if (!mapBuiltInType.isClassTypeConstructor(typeConstructor)) {
            return null;
        }
        PrimitiveType primitiveType = mapBuiltInType.getPrimitiveType(typeConstructor);
        boolean z = true;
        if (primitiveType != null) {
            JvmPrimitiveType jvmPrimitiveType = JvmPrimitiveType.get(primitiveType);
            Intrinsics.checkExpressionValueIsNotNull(jvmPrimitiveType, "JvmPrimitiveType.get(primitiveType)");
            String desc = jvmPrimitiveType.getDesc();
            Intrinsics.checkExpressionValueIsNotNull(desc, "JvmPrimitiveType.get(primitiveType).desc");
            T createFromString = typeFactory.createFromString(desc);
            if (!mapBuiltInType.isNullableType(type) && !TypeEnhancementKt.hasEnhancedNullability(mapBuiltInType, type)) {
                z = false;
            }
            return (T) boxTypeIfNeeded(typeFactory, createFromString, z);
        }
        PrimitiveType primitiveArrayType = mapBuiltInType.getPrimitiveArrayType(typeConstructor);
        if (primitiveArrayType != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
            JvmPrimitiveType jvmPrimitiveType2 = JvmPrimitiveType.get(primitiveArrayType);
            Intrinsics.checkExpressionValueIsNotNull(jvmPrimitiveType2, "JvmPrimitiveType.get(arrayElementType)");
            outline107.append(jvmPrimitiveType2.getDesc());
            return typeFactory.createFromString(outline107.toString());
        }
        if (mapBuiltInType.isUnderKotlinPackage(typeConstructor)) {
            FqNameUnsafe classFqNameUnsafe = mapBuiltInType.getClassFqNameUnsafe(typeConstructor);
            ClassId mapKotlinToJava = classFqNameUnsafe != null ? JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(classFqNameUnsafe) : null;
            if (mapKotlinToJava != null) {
                if (!mode.getKotlinCollectionsToJavaCollections()) {
                    List<JavaToKotlinClassMap.PlatformMutabilityMapping> mutabilityMappings = JavaToKotlinClassMap.INSTANCE.getMutabilityMappings();
                    if (!(mutabilityMappings instanceof Collection) || !mutabilityMappings.isEmpty()) {
                        for (JavaToKotlinClassMap.PlatformMutabilityMapping platformMutabilityMapping : mutabilityMappings) {
                            if (Intrinsics.areEqual(platformMutabilityMapping.getJavaClass(), mapKotlinToJava)) {
                                break;
                            }
                        }
                    }
                    z = false;
                    if (z) {
                        return null;
                    }
                }
                JvmClassName byClassId = JvmClassName.byClassId(mapKotlinToJava);
                Intrinsics.checkExpressionValueIsNotNull(byClassId, "JvmClassName.byClassId(classId)");
                String internalName = byClassId.getInternalName();
                Intrinsics.checkExpressionValueIsNotNull(internalName, "JvmClassName.byClassId(classId).internalName");
                return typeFactory.createObjectType(internalName);
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v11, types: [T, java.lang.Object] */
    @NotNull
    public static final <T> T mapType(@NotNull KotlinType kotlinType, @NotNull JvmTypeFactory<T> factory, @NotNull TypeMappingMode mode, @NotNull TypeMappingConfiguration<? extends T> typeMappingConfiguration, @Nullable JvmDescriptorTypeWriter<T> jvmDescriptorTypeWriter, @NotNull Function3<? super KotlinType, ? super T, ? super TypeMappingMode, Unit> writeGenericType) {
        Object obj;
        KotlinType kotlinType2;
        Object mapType;
        Intrinsics.checkParameterIsNotNull(kotlinType, "kotlinType");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(typeMappingConfiguration, "typeMappingConfiguration");
        Intrinsics.checkParameterIsNotNull(writeGenericType, "writeGenericType");
        KotlinType preprocessType = typeMappingConfiguration.preprocessType(kotlinType);
        if (preprocessType != null) {
            return (T) mapType(preprocessType, factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
        }
        if (FunctionTypesKt.isSuspendFunctionType(kotlinType)) {
            return (T) mapType(SuspendFunctionTypesKt.transformSuspendFunctionToRuntimeFunctionType(kotlinType, typeMappingConfiguration.releaseCoroutines()), factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
        }
        Object mapBuiltInType = mapBuiltInType(SimpleClassicTypeSystemContext.INSTANCE, kotlinType, factory, mode);
        if (mapBuiltInType != null) {
            ?? r9 = (Object) boxTypeIfNeeded(factory, mapBuiltInType, mode.getNeedPrimitiveBoxing());
            writeGenericType.invoke(kotlinType, r9, mode);
            return r9;
        }
        TypeConstructor mo12131getConstructor = kotlinType.mo12131getConstructor();
        if (mo12131getConstructor instanceof IntersectionTypeConstructor) {
            return (T) mapType(TypeUtilsKt.replaceArgumentsWithStarProjections(typeMappingConfiguration.commonSupertype(((IntersectionTypeConstructor) mo12131getConstructor).mo12135getSupertypes())), factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
        }
        ClassifierDescriptor mo12085getDeclarationDescriptor = mo12131getConstructor.mo12085getDeclarationDescriptor();
        if (mo12085getDeclarationDescriptor != null) {
            Intrinsics.checkExpressionValueIsNotNull(mo12085getDeclarationDescriptor, "constructor.declarationD…structor of $kotlinType\")");
            if (ErrorUtils.isError(mo12085getDeclarationDescriptor)) {
                T t = (T) factory.createObjectType("error/NonExistentClass");
                typeMappingConfiguration.processErrorType(kotlinType, (ClassDescriptor) mo12085getDeclarationDescriptor);
                if (jvmDescriptorTypeWriter != 0) {
                    jvmDescriptorTypeWriter.writeClass(t);
                }
                return t;
            }
            boolean z = mo12085getDeclarationDescriptor instanceof ClassDescriptor;
            if (z && KotlinBuiltIns.isArray(kotlinType)) {
                if (kotlinType.getArguments().size() == 1) {
                    TypeProjection typeProjection = kotlinType.getArguments().get(0);
                    KotlinType type = typeProjection.getType();
                    Intrinsics.checkExpressionValueIsNotNull(type, "memberProjection.type");
                    if (typeProjection.getProjectionKind() == Variance.IN_VARIANCE) {
                        mapType = factory.createObjectType("java/lang/Object");
                        if (jvmDescriptorTypeWriter != 0) {
                            jvmDescriptorTypeWriter.writeArrayType();
                            jvmDescriptorTypeWriter.writeClass(mapType);
                            jvmDescriptorTypeWriter.writeArrayEnd();
                        }
                    } else {
                        if (jvmDescriptorTypeWriter != 0) {
                            jvmDescriptorTypeWriter.writeArrayType();
                        }
                        Variance projectionKind = typeProjection.getProjectionKind();
                        Intrinsics.checkExpressionValueIsNotNull(projectionKind, "memberProjection.projectionKind");
                        mapType = mapType(type, factory, mode.toGenericArgumentMode(projectionKind), typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
                        if (jvmDescriptorTypeWriter != 0) {
                            jvmDescriptorTypeWriter.writeArrayEnd();
                        }
                    }
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
                    outline107.append(factory.toString(mapType));
                    return (T) factory.createFromString(outline107.toString());
                }
                throw new UnsupportedOperationException("arrays must have one type argument");
            } else if (z) {
                ClassDescriptor classDescriptor = (ClassDescriptor) mo12085getDeclarationDescriptor;
                if (classDescriptor.isInline() && !mode.getNeedInlineClassWrapping() && (kotlinType2 = (KotlinType) InlineClassMappingKt.computeExpandedTypeForInlineClass(SimpleClassicTypeSystemContext.INSTANCE, kotlinType)) != null) {
                    return (T) mapType(kotlinType2, factory, mode.wrapInlineClassesMode(), typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
                }
                if (mode.isForAnnotationParameter() && KotlinBuiltIns.isKClass(classDescriptor)) {
                    obj = (Object) factory.getJavaLangClassType();
                } else {
                    ClassDescriptor mo11613getOriginal = classDescriptor.mo11613getOriginal();
                    Intrinsics.checkExpressionValueIsNotNull(mo11613getOriginal, "descriptor.original");
                    T predefinedTypeForClass = typeMappingConfiguration.getPredefinedTypeForClass(mo11613getOriginal);
                    if (predefinedTypeForClass != null) {
                        obj = (Object) predefinedTypeForClass;
                    } else {
                        if (classDescriptor.getKind() == ClassKind.ENUM_ENTRY) {
                            DeclarationDescriptor mo11607getContainingDeclaration = classDescriptor.mo11607getContainingDeclaration();
                            if (mo11607getContainingDeclaration == null) {
                                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                            }
                            classDescriptor = (ClassDescriptor) mo11607getContainingDeclaration;
                        }
                        ClassDescriptor mo11613getOriginal2 = classDescriptor.mo11613getOriginal();
                        Intrinsics.checkExpressionValueIsNotNull(mo11613getOriginal2, "enumClassIfEnumEntry.original");
                        obj = (Object) factory.createObjectType(computeInternalName(mo11613getOriginal2, typeMappingConfiguration));
                    }
                }
                writeGenericType.invoke(kotlinType, obj, mode);
                return (T) obj;
            } else if (mo12085getDeclarationDescriptor instanceof TypeParameterDescriptor) {
                T t2 = (T) mapType(TypeUtilsKt.getRepresentativeUpperBound((TypeParameterDescriptor) mo12085getDeclarationDescriptor), factory, mode, typeMappingConfiguration, null, FunctionsKt.getDO_NOTHING_3());
                if (jvmDescriptorTypeWriter != 0) {
                    Name name = mo12085getDeclarationDescriptor.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name, "descriptor.getName()");
                    jvmDescriptorTypeWriter.writeTypeVariable(name, t2);
                }
                return t2;
            } else {
                throw new UnsupportedOperationException("Unknown type " + kotlinType);
            }
        }
        throw new UnsupportedOperationException("no descriptor for type constructor of " + kotlinType);
    }

    public static /* synthetic */ Object mapType$default(KotlinType kotlinType, JvmTypeFactory jvmTypeFactory, TypeMappingMode typeMappingMode, TypeMappingConfiguration typeMappingConfiguration, JvmDescriptorTypeWriter jvmDescriptorTypeWriter, Function3 function3, int i, Object obj) {
        if ((i & 32) != 0) {
            function3 = FunctionsKt.getDO_NOTHING_3();
        }
        return mapType(kotlinType, jvmTypeFactory, typeMappingMode, typeMappingConfiguration, jvmDescriptorTypeWriter, function3);
    }
}
