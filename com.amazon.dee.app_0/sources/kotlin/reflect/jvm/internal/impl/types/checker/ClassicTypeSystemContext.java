package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.Collection;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModalityKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedType;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType;
import kotlin.reflect.jvm.internal.impl.types.DynamicType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StubType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DynamicTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.FlexibleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemInferenceExtensionContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ClassicTypeSystemContext.kt */
/* loaded from: classes4.dex */
public interface ClassicTypeSystemContext extends TypeSystemCommonBackendContext, TypeSystemInferenceExtensionContext {
    @Override // 
    @Nullable
    SimpleTypeMarker asSimpleType(@NotNull KotlinTypeMarker kotlinTypeMarker);

    @Override // 
    @NotNull
    TypeConstructorMarker typeConstructor(@NotNull SimpleTypeMarker simpleTypeMarker);

    /* compiled from: ClassicTypeSystemContext.kt */
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static int argumentsCount(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker argumentsCount) {
            Intrinsics.checkParameterIsNotNull(argumentsCount, "$this$argumentsCount");
            if (argumentsCount instanceof KotlinType) {
                return ((KotlinType) argumentsCount).getArguments().size();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + argumentsCount + ", " + Reflection.getOrCreateKotlinClass(argumentsCount.getClass())).toString());
        }

        @NotNull
        public static TypeArgumentListMarker asArgumentList(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker asArgumentList) {
            Intrinsics.checkParameterIsNotNull(asArgumentList, "$this$asArgumentList");
            if (asArgumentList instanceof SimpleType) {
                return (TypeArgumentListMarker) asArgumentList;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + asArgumentList + ", " + Reflection.getOrCreateKotlinClass(asArgumentList.getClass())).toString());
        }

        @Nullable
        public static CapturedTypeMarker asCapturedType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker asCapturedType) {
            Intrinsics.checkParameterIsNotNull(asCapturedType, "$this$asCapturedType");
            if (asCapturedType instanceof SimpleType) {
                if (!(asCapturedType instanceof NewCapturedType)) {
                    asCapturedType = null;
                }
                return (NewCapturedType) asCapturedType;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + asCapturedType + ", " + Reflection.getOrCreateKotlinClass(asCapturedType.getClass())).toString());
        }

        @Nullable
        public static DefinitelyNotNullTypeMarker asDefinitelyNotNullType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker asDefinitelyNotNullType) {
            Intrinsics.checkParameterIsNotNull(asDefinitelyNotNullType, "$this$asDefinitelyNotNullType");
            if (asDefinitelyNotNullType instanceof SimpleType) {
                if (!(asDefinitelyNotNullType instanceof DefinitelyNotNullType)) {
                    asDefinitelyNotNullType = null;
                }
                return (DefinitelyNotNullType) asDefinitelyNotNullType;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + asDefinitelyNotNullType + ", " + Reflection.getOrCreateKotlinClass(asDefinitelyNotNullType.getClass())).toString());
        }

        @Nullable
        public static DynamicTypeMarker asDynamicType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull FlexibleTypeMarker asDynamicType) {
            Intrinsics.checkParameterIsNotNull(asDynamicType, "$this$asDynamicType");
            if (asDynamicType instanceof FlexibleType) {
                if (!(asDynamicType instanceof DynamicType)) {
                    asDynamicType = null;
                }
                return (DynamicType) asDynamicType;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + asDynamicType + ", " + Reflection.getOrCreateKotlinClass(asDynamicType.getClass())).toString());
        }

        @Nullable
        public static FlexibleTypeMarker asFlexibleType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker asFlexibleType) {
            Intrinsics.checkParameterIsNotNull(asFlexibleType, "$this$asFlexibleType");
            if (asFlexibleType instanceof KotlinType) {
                UnwrappedType unwrap = ((KotlinType) asFlexibleType).unwrap();
                if (!(unwrap instanceof FlexibleType)) {
                    unwrap = null;
                }
                return (FlexibleType) unwrap;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + asFlexibleType + ", " + Reflection.getOrCreateKotlinClass(asFlexibleType.getClass())).toString());
        }

        @Nullable
        public static SimpleTypeMarker asSimpleType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker asSimpleType) {
            Intrinsics.checkParameterIsNotNull(asSimpleType, "$this$asSimpleType");
            if (asSimpleType instanceof KotlinType) {
                UnwrappedType unwrap = ((KotlinType) asSimpleType).unwrap();
                if (!(unwrap instanceof SimpleType)) {
                    unwrap = null;
                }
                return (SimpleType) unwrap;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + asSimpleType + ", " + Reflection.getOrCreateKotlinClass(asSimpleType.getClass())).toString());
        }

        @NotNull
        public static TypeArgumentMarker asTypeArgument(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker asTypeArgument) {
            Intrinsics.checkParameterIsNotNull(asTypeArgument, "$this$asTypeArgument");
            if (asTypeArgument instanceof KotlinType) {
                return TypeUtilsKt.asTypeProjection((KotlinType) asTypeArgument);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + asTypeArgument + ", " + Reflection.getOrCreateKotlinClass(asTypeArgument.getClass())).toString());
        }

        @Nullable
        public static SimpleTypeMarker captureFromArguments(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker type, @NotNull CaptureStatus status) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(status, "status");
            if (type instanceof SimpleType) {
                return NewCapturedTypeKt.captureFromArguments((SimpleType) type, status);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + type + ", " + Reflection.getOrCreateKotlinClass(type.getClass())).toString());
        }

        @Nullable
        public static List<SimpleTypeMarker> fastCorrespondingSupertypes(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker fastCorrespondingSupertypes, @NotNull TypeConstructorMarker constructor) {
            Intrinsics.checkParameterIsNotNull(fastCorrespondingSupertypes, "$this$fastCorrespondingSupertypes");
            Intrinsics.checkParameterIsNotNull(constructor, "constructor");
            return TypeSystemInferenceExtensionContext.DefaultImpls.fastCorrespondingSupertypes(classicTypeSystemContext, fastCorrespondingSupertypes, constructor);
        }

        @NotNull
        public static TypeArgumentMarker get(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeArgumentListMarker get, int i) {
            Intrinsics.checkParameterIsNotNull(get, "$this$get");
            return TypeSystemInferenceExtensionContext.DefaultImpls.get(classicTypeSystemContext, get, i);
        }

        @NotNull
        public static TypeArgumentMarker getArgument(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker getArgument, int i) {
            Intrinsics.checkParameterIsNotNull(getArgument, "$this$getArgument");
            if (getArgument instanceof KotlinType) {
                return ((KotlinType) getArgument).getArguments().get(i);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getArgument + ", " + Reflection.getOrCreateKotlinClass(getArgument.getClass())).toString());
        }

        @Nullable
        public static TypeArgumentMarker getArgumentOrNull(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker getArgumentOrNull, int i) {
            Intrinsics.checkParameterIsNotNull(getArgumentOrNull, "$this$getArgumentOrNull");
            return TypeSystemInferenceExtensionContext.DefaultImpls.getArgumentOrNull(classicTypeSystemContext, getArgumentOrNull, i);
        }

        @NotNull
        public static FqNameUnsafe getClassFqNameUnsafe(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker getClassFqNameUnsafe) {
            Intrinsics.checkParameterIsNotNull(getClassFqNameUnsafe, "$this$getClassFqNameUnsafe");
            if (getClassFqNameUnsafe instanceof TypeConstructor) {
                ClassifierDescriptor mo12085getDeclarationDescriptor = ((TypeConstructor) getClassFqNameUnsafe).mo12085getDeclarationDescriptor();
                if (mo12085getDeclarationDescriptor == null) {
                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                }
                return DescriptorUtilsKt.getFqNameUnsafe((ClassDescriptor) mo12085getDeclarationDescriptor);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getClassFqNameUnsafe + ", " + Reflection.getOrCreateKotlinClass(getClassFqNameUnsafe.getClass())).toString());
        }

        @NotNull
        public static TypeParameterMarker getParameter(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker getParameter, int i) {
            Intrinsics.checkParameterIsNotNull(getParameter, "$this$getParameter");
            if (getParameter instanceof TypeConstructor) {
                TypeParameterDescriptor typeParameterDescriptor = ((TypeConstructor) getParameter).getParameters().get(i);
                Intrinsics.checkExpressionValueIsNotNull(typeParameterDescriptor, "this.parameters[index]");
                return typeParameterDescriptor;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getParameter + ", " + Reflection.getOrCreateKotlinClass(getParameter.getClass())).toString());
        }

        @Nullable
        public static PrimitiveType getPrimitiveArrayType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker getPrimitiveArrayType) {
            Intrinsics.checkParameterIsNotNull(getPrimitiveArrayType, "$this$getPrimitiveArrayType");
            if (getPrimitiveArrayType instanceof TypeConstructor) {
                ClassifierDescriptor mo12085getDeclarationDescriptor = ((TypeConstructor) getPrimitiveArrayType).mo12085getDeclarationDescriptor();
                if (mo12085getDeclarationDescriptor == null) {
                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                }
                return KotlinBuiltIns.getPrimitiveArrayType((ClassDescriptor) mo12085getDeclarationDescriptor);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getPrimitiveArrayType + ", " + Reflection.getOrCreateKotlinClass(getPrimitiveArrayType.getClass())).toString());
        }

        @Nullable
        public static PrimitiveType getPrimitiveType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker getPrimitiveType) {
            Intrinsics.checkParameterIsNotNull(getPrimitiveType, "$this$getPrimitiveType");
            if (getPrimitiveType instanceof TypeConstructor) {
                ClassifierDescriptor mo12085getDeclarationDescriptor = ((TypeConstructor) getPrimitiveType).mo12085getDeclarationDescriptor();
                if (mo12085getDeclarationDescriptor == null) {
                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                }
                return KotlinBuiltIns.getPrimitiveType((ClassDescriptor) mo12085getDeclarationDescriptor);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getPrimitiveType + ", " + Reflection.getOrCreateKotlinClass(getPrimitiveType.getClass())).toString());
        }

        @NotNull
        public static KotlinTypeMarker getRepresentativeUpperBound(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeParameterMarker getRepresentativeUpperBound) {
            Intrinsics.checkParameterIsNotNull(getRepresentativeUpperBound, "$this$getRepresentativeUpperBound");
            if (getRepresentativeUpperBound instanceof TypeParameterDescriptor) {
                return TypeUtilsKt.getRepresentativeUpperBound((TypeParameterDescriptor) getRepresentativeUpperBound);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getRepresentativeUpperBound + ", " + Reflection.getOrCreateKotlinClass(getRepresentativeUpperBound.getClass())).toString());
        }

        @Nullable
        public static KotlinTypeMarker getSubstitutedUnderlyingType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker getSubstitutedUnderlyingType) {
            Intrinsics.checkParameterIsNotNull(getSubstitutedUnderlyingType, "$this$getSubstitutedUnderlyingType");
            if (getSubstitutedUnderlyingType instanceof KotlinType) {
                return InlineClassesUtilsKt.substitutedUnderlyingType((KotlinType) getSubstitutedUnderlyingType);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getSubstitutedUnderlyingType + ", " + Reflection.getOrCreateKotlinClass(getSubstitutedUnderlyingType.getClass())).toString());
        }

        @NotNull
        public static KotlinTypeMarker getType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeArgumentMarker getType) {
            Intrinsics.checkParameterIsNotNull(getType, "$this$getType");
            if (getType instanceof TypeProjection) {
                return ((TypeProjection) getType).getType().unwrap();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getType + ", " + Reflection.getOrCreateKotlinClass(getType.getClass())).toString());
        }

        @Nullable
        public static TypeParameterMarker getTypeParameterClassifier(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker getTypeParameterClassifier) {
            Intrinsics.checkParameterIsNotNull(getTypeParameterClassifier, "$this$getTypeParameterClassifier");
            if (getTypeParameterClassifier instanceof TypeConstructor) {
                ClassifierDescriptor mo12085getDeclarationDescriptor = ((TypeConstructor) getTypeParameterClassifier).mo12085getDeclarationDescriptor();
                if (!(mo12085getDeclarationDescriptor instanceof TypeParameterDescriptor)) {
                    mo12085getDeclarationDescriptor = null;
                }
                return (TypeParameterDescriptor) mo12085getDeclarationDescriptor;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getTypeParameterClassifier + ", " + Reflection.getOrCreateKotlinClass(getTypeParameterClassifier.getClass())).toString());
        }

        @NotNull
        public static TypeVariance getVariance(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeArgumentMarker getVariance) {
            Intrinsics.checkParameterIsNotNull(getVariance, "$this$getVariance");
            if (getVariance instanceof TypeProjection) {
                Variance projectionKind = ((TypeProjection) getVariance).getProjectionKind();
                Intrinsics.checkExpressionValueIsNotNull(projectionKind, "this.projectionKind");
                return ClassicTypeSystemContextKt.convertVariance(projectionKind);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getVariance + ", " + Reflection.getOrCreateKotlinClass(getVariance.getClass())).toString());
        }

        public static boolean hasAnnotation(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker hasAnnotation, @NotNull FqName fqName) {
            Intrinsics.checkParameterIsNotNull(hasAnnotation, "$this$hasAnnotation");
            Intrinsics.checkParameterIsNotNull(fqName, "fqName");
            if (hasAnnotation instanceof KotlinType) {
                return ((KotlinType) hasAnnotation).mo12070getAnnotations().hasAnnotation(fqName);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + hasAnnotation + ", " + Reflection.getOrCreateKotlinClass(hasAnnotation.getClass())).toString());
        }

        public static boolean hasFlexibleNullability(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker hasFlexibleNullability) {
            Intrinsics.checkParameterIsNotNull(hasFlexibleNullability, "$this$hasFlexibleNullability");
            return TypeSystemInferenceExtensionContext.DefaultImpls.hasFlexibleNullability(classicTypeSystemContext, hasFlexibleNullability);
        }

        public static boolean identicalArguments(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker a, @NotNull SimpleTypeMarker b) {
            Intrinsics.checkParameterIsNotNull(a, "a");
            Intrinsics.checkParameterIsNotNull(b, "b");
            if (a instanceof SimpleType) {
                if (b instanceof SimpleType) {
                    return ((SimpleType) a).getArguments() == ((SimpleType) b).getArguments();
                }
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + b + ", " + Reflection.getOrCreateKotlinClass(b.getClass())).toString());
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + a + ", " + Reflection.getOrCreateKotlinClass(a.getClass())).toString());
        }

        @NotNull
        public static KotlinTypeMarker intersectTypes(ClassicTypeSystemContext classicTypeSystemContext, @NotNull List<? extends KotlinTypeMarker> types) {
            Intrinsics.checkParameterIsNotNull(types, "types");
            return IntersectionTypeKt.intersectTypes(types);
        }

        public static boolean isAnyConstructor(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker isAnyConstructor) {
            Intrinsics.checkParameterIsNotNull(isAnyConstructor, "$this$isAnyConstructor");
            if (isAnyConstructor instanceof TypeConstructor) {
                return KotlinBuiltIns.isTypeConstructorForGivenClass((TypeConstructor) isAnyConstructor, KotlinBuiltIns.FQ_NAMES.any);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isAnyConstructor + ", " + Reflection.getOrCreateKotlinClass(isAnyConstructor.getClass())).toString());
        }

        public static boolean isClassType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker isClassType) {
            Intrinsics.checkParameterIsNotNull(isClassType, "$this$isClassType");
            return TypeSystemInferenceExtensionContext.DefaultImpls.isClassType(classicTypeSystemContext, isClassType);
        }

        public static boolean isClassTypeConstructor(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker isClassTypeConstructor) {
            Intrinsics.checkParameterIsNotNull(isClassTypeConstructor, "$this$isClassTypeConstructor");
            if (isClassTypeConstructor instanceof TypeConstructor) {
                return ((TypeConstructor) isClassTypeConstructor).mo12085getDeclarationDescriptor() instanceof ClassDescriptor;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isClassTypeConstructor + ", " + Reflection.getOrCreateKotlinClass(isClassTypeConstructor.getClass())).toString());
        }

        public static boolean isCommonFinalClassConstructor(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker isCommonFinalClassConstructor) {
            Intrinsics.checkParameterIsNotNull(isCommonFinalClassConstructor, "$this$isCommonFinalClassConstructor");
            if (isCommonFinalClassConstructor instanceof TypeConstructor) {
                ClassifierDescriptor mo12085getDeclarationDescriptor = ((TypeConstructor) isCommonFinalClassConstructor).mo12085getDeclarationDescriptor();
                if (!(mo12085getDeclarationDescriptor instanceof ClassDescriptor)) {
                    mo12085getDeclarationDescriptor = null;
                }
                ClassDescriptor classDescriptor = (ClassDescriptor) mo12085getDeclarationDescriptor;
                return (classDescriptor == null || !ModalityKt.isFinalClass(classDescriptor) || classDescriptor.getKind() == ClassKind.ENUM_ENTRY || classDescriptor.getKind() == ClassKind.ANNOTATION_CLASS) ? false : true;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isCommonFinalClassConstructor + ", " + Reflection.getOrCreateKotlinClass(isCommonFinalClassConstructor.getClass())).toString());
        }

        public static boolean isDefinitelyNotNullType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker isDefinitelyNotNullType) {
            Intrinsics.checkParameterIsNotNull(isDefinitelyNotNullType, "$this$isDefinitelyNotNullType");
            return TypeSystemInferenceExtensionContext.DefaultImpls.isDefinitelyNotNullType(classicTypeSystemContext, isDefinitelyNotNullType);
        }

        public static boolean isDenotable(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker isDenotable) {
            Intrinsics.checkParameterIsNotNull(isDenotable, "$this$isDenotable");
            if (isDenotable instanceof TypeConstructor) {
                return ((TypeConstructor) isDenotable).isDenotable();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isDenotable + ", " + Reflection.getOrCreateKotlinClass(isDenotable.getClass())).toString());
        }

        public static boolean isDynamic(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker isDynamic) {
            Intrinsics.checkParameterIsNotNull(isDynamic, "$this$isDynamic");
            return TypeSystemInferenceExtensionContext.DefaultImpls.isDynamic(classicTypeSystemContext, isDynamic);
        }

        public static boolean isEqualTypeConstructors(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker c1, @NotNull TypeConstructorMarker c2) {
            Intrinsics.checkParameterIsNotNull(c1, "c1");
            Intrinsics.checkParameterIsNotNull(c2, "c2");
            if (c1 instanceof TypeConstructor) {
                if (c2 instanceof TypeConstructor) {
                    return Intrinsics.areEqual(c1, c2);
                }
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + c2 + ", " + Reflection.getOrCreateKotlinClass(c2.getClass())).toString());
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + c1 + ", " + Reflection.getOrCreateKotlinClass(c1.getClass())).toString());
        }

        public static boolean isError(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker isError) {
            Intrinsics.checkParameterIsNotNull(isError, "$this$isError");
            if (isError instanceof KotlinType) {
                return KotlinTypeKt.isError((KotlinType) isError);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isError + ", " + Reflection.getOrCreateKotlinClass(isError.getClass())).toString());
        }

        public static boolean isInlineClass(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker isInlineClass) {
            Intrinsics.checkParameterIsNotNull(isInlineClass, "$this$isInlineClass");
            if (isInlineClass instanceof TypeConstructor) {
                ClassifierDescriptor mo12085getDeclarationDescriptor = ((TypeConstructor) isInlineClass).mo12085getDeclarationDescriptor();
                if (!(mo12085getDeclarationDescriptor instanceof ClassDescriptor)) {
                    mo12085getDeclarationDescriptor = null;
                }
                ClassDescriptor classDescriptor = (ClassDescriptor) mo12085getDeclarationDescriptor;
                return classDescriptor != null && classDescriptor.isInline();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isInlineClass + ", " + Reflection.getOrCreateKotlinClass(isInlineClass.getClass())).toString());
        }

        public static boolean isIntegerLiteralType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker isIntegerLiteralType) {
            Intrinsics.checkParameterIsNotNull(isIntegerLiteralType, "$this$isIntegerLiteralType");
            return TypeSystemInferenceExtensionContext.DefaultImpls.isIntegerLiteralType(classicTypeSystemContext, isIntegerLiteralType);
        }

        public static boolean isIntegerLiteralTypeConstructor(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker isIntegerLiteralTypeConstructor) {
            Intrinsics.checkParameterIsNotNull(isIntegerLiteralTypeConstructor, "$this$isIntegerLiteralTypeConstructor");
            if (isIntegerLiteralTypeConstructor instanceof TypeConstructor) {
                return isIntegerLiteralTypeConstructor instanceof IntegerLiteralTypeConstructor;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isIntegerLiteralTypeConstructor + ", " + Reflection.getOrCreateKotlinClass(isIntegerLiteralTypeConstructor.getClass())).toString());
        }

        public static boolean isIntersection(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker isIntersection) {
            Intrinsics.checkParameterIsNotNull(isIntersection, "$this$isIntersection");
            if (isIntersection instanceof TypeConstructor) {
                return isIntersection instanceof IntersectionTypeConstructor;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isIntersection + ", " + Reflection.getOrCreateKotlinClass(isIntersection.getClass())).toString());
        }

        public static boolean isMarkedNullable(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker isMarkedNullable) {
            Intrinsics.checkParameterIsNotNull(isMarkedNullable, "$this$isMarkedNullable");
            return TypeSystemCommonBackendContext.DefaultImpls.isMarkedNullable(classicTypeSystemContext, isMarkedNullable);
        }

        public static boolean isMarkedNullable(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker isMarkedNullable) {
            Intrinsics.checkParameterIsNotNull(isMarkedNullable, "$this$isMarkedNullable");
            if (isMarkedNullable instanceof SimpleType) {
                return ((SimpleType) isMarkedNullable).isMarkedNullable();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isMarkedNullable + ", " + Reflection.getOrCreateKotlinClass(isMarkedNullable.getClass())).toString());
        }

        public static boolean isNothing(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker isNothing) {
            Intrinsics.checkParameterIsNotNull(isNothing, "$this$isNothing");
            return TypeSystemInferenceExtensionContext.DefaultImpls.isNothing(classicTypeSystemContext, isNothing);
        }

        public static boolean isNothingConstructor(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker isNothingConstructor) {
            Intrinsics.checkParameterIsNotNull(isNothingConstructor, "$this$isNothingConstructor");
            if (isNothingConstructor instanceof TypeConstructor) {
                return KotlinBuiltIns.isTypeConstructorForGivenClass((TypeConstructor) isNothingConstructor, KotlinBuiltIns.FQ_NAMES.nothing);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isNothingConstructor + ", " + Reflection.getOrCreateKotlinClass(isNothingConstructor.getClass())).toString());
        }

        public static boolean isNullableType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker isNullableType) {
            Intrinsics.checkParameterIsNotNull(isNullableType, "$this$isNullableType");
            if (isNullableType instanceof KotlinType) {
                return TypeUtils.isNullableType((KotlinType) isNullableType);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isNullableType + ", " + Reflection.getOrCreateKotlinClass(isNullableType.getClass())).toString());
        }

        public static boolean isPrimitiveType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker isPrimitiveType) {
            Intrinsics.checkParameterIsNotNull(isPrimitiveType, "$this$isPrimitiveType");
            if (isPrimitiveType instanceof KotlinType) {
                return KotlinBuiltIns.isPrimitiveType((KotlinType) isPrimitiveType);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isPrimitiveType + ", " + Reflection.getOrCreateKotlinClass(isPrimitiveType.getClass())).toString());
        }

        public static boolean isSingleClassifierType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker isSingleClassifierType) {
            Intrinsics.checkParameterIsNotNull(isSingleClassifierType, "$this$isSingleClassifierType");
            if (isSingleClassifierType instanceof SimpleType) {
                if (!KotlinTypeKt.isError((KotlinType) isSingleClassifierType)) {
                    SimpleType simpleType = (SimpleType) isSingleClassifierType;
                    if (!(simpleType.mo12131getConstructor().mo12085getDeclarationDescriptor() instanceof TypeAliasDescriptor) && (simpleType.mo12131getConstructor().mo12085getDeclarationDescriptor() != null || (isSingleClassifierType instanceof CapturedType) || (isSingleClassifierType instanceof NewCapturedType) || (isSingleClassifierType instanceof DefinitelyNotNullType) || (simpleType.mo12131getConstructor() instanceof IntegerLiteralTypeConstructor))) {
                        return true;
                    }
                }
                return false;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isSingleClassifierType + ", " + Reflection.getOrCreateKotlinClass(isSingleClassifierType.getClass())).toString());
        }

        public static boolean isStarProjection(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeArgumentMarker isStarProjection) {
            Intrinsics.checkParameterIsNotNull(isStarProjection, "$this$isStarProjection");
            if (isStarProjection instanceof TypeProjection) {
                return ((TypeProjection) isStarProjection).isStarProjection();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isStarProjection + ", " + Reflection.getOrCreateKotlinClass(isStarProjection.getClass())).toString());
        }

        public static boolean isStubType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker isStubType) {
            Intrinsics.checkParameterIsNotNull(isStubType, "$this$isStubType");
            if (isStubType instanceof SimpleType) {
                return isStubType instanceof StubType;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isStubType + ", " + Reflection.getOrCreateKotlinClass(isStubType.getClass())).toString());
        }

        public static boolean isUnderKotlinPackage(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker isUnderKotlinPackage) {
            Intrinsics.checkParameterIsNotNull(isUnderKotlinPackage, "$this$isUnderKotlinPackage");
            if (isUnderKotlinPackage instanceof TypeConstructor) {
                ClassifierDescriptor mo12085getDeclarationDescriptor = ((TypeConstructor) isUnderKotlinPackage).mo12085getDeclarationDescriptor();
                return mo12085getDeclarationDescriptor != null && KotlinBuiltIns.isUnderKotlinPackage(mo12085getDeclarationDescriptor);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + isUnderKotlinPackage + ", " + Reflection.getOrCreateKotlinClass(isUnderKotlinPackage.getClass())).toString());
        }

        @NotNull
        public static SimpleTypeMarker lowerBound(ClassicTypeSystemContext classicTypeSystemContext, @NotNull FlexibleTypeMarker lowerBound) {
            Intrinsics.checkParameterIsNotNull(lowerBound, "$this$lowerBound");
            if (lowerBound instanceof FlexibleType) {
                return ((FlexibleType) lowerBound).getLowerBound();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + lowerBound + ", " + Reflection.getOrCreateKotlinClass(lowerBound.getClass())).toString());
        }

        @NotNull
        public static SimpleTypeMarker lowerBoundIfFlexible(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker lowerBoundIfFlexible) {
            Intrinsics.checkParameterIsNotNull(lowerBoundIfFlexible, "$this$lowerBoundIfFlexible");
            return TypeSystemInferenceExtensionContext.DefaultImpls.lowerBoundIfFlexible(classicTypeSystemContext, lowerBoundIfFlexible);
        }

        @Nullable
        public static KotlinTypeMarker lowerType(ClassicTypeSystemContext classicTypeSystemContext, @NotNull CapturedTypeMarker lowerType) {
            Intrinsics.checkParameterIsNotNull(lowerType, "$this$lowerType");
            if (lowerType instanceof NewCapturedType) {
                return ((NewCapturedType) lowerType).getLowerType();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + lowerType + ", " + Reflection.getOrCreateKotlinClass(lowerType.getClass())).toString());
        }

        @NotNull
        public static KotlinTypeMarker makeNullable(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker makeNullable) {
            Intrinsics.checkParameterIsNotNull(makeNullable, "$this$makeNullable");
            return TypeSystemCommonBackendContext.DefaultImpls.makeNullable(classicTypeSystemContext, makeNullable);
        }

        @NotNull
        public static AbstractTypeCheckerContext newBaseTypeCheckerContext(ClassicTypeSystemContext classicTypeSystemContext, boolean z, boolean z2) {
            return new ClassicTypeCheckerContext(z, z2, false, null, 12, null);
        }

        public static int parametersCount(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker parametersCount) {
            Intrinsics.checkParameterIsNotNull(parametersCount, "$this$parametersCount");
            if (parametersCount instanceof TypeConstructor) {
                return ((TypeConstructor) parametersCount).getParameters().size();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + parametersCount + ", " + Reflection.getOrCreateKotlinClass(parametersCount.getClass())).toString());
        }

        @NotNull
        public static Collection<KotlinTypeMarker> possibleIntegerTypes(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker possibleIntegerTypes) {
            Intrinsics.checkParameterIsNotNull(possibleIntegerTypes, "$this$possibleIntegerTypes");
            TypeConstructorMarker typeConstructor = classicTypeSystemContext.typeConstructor(possibleIntegerTypes);
            if (typeConstructor instanceof IntegerLiteralTypeConstructor) {
                return ((IntegerLiteralTypeConstructor) typeConstructor).getPossibleTypes();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + possibleIntegerTypes + ", " + Reflection.getOrCreateKotlinClass(possibleIntegerTypes.getClass())).toString());
        }

        public static int size(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeArgumentListMarker size) {
            Intrinsics.checkParameterIsNotNull(size, "$this$size");
            return TypeSystemInferenceExtensionContext.DefaultImpls.size(classicTypeSystemContext, size);
        }

        @NotNull
        public static Collection<KotlinTypeMarker> supertypes(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeConstructorMarker supertypes) {
            Intrinsics.checkParameterIsNotNull(supertypes, "$this$supertypes");
            if (supertypes instanceof TypeConstructor) {
                Collection<KotlinType> mo12135getSupertypes = ((TypeConstructor) supertypes).mo12135getSupertypes();
                Intrinsics.checkExpressionValueIsNotNull(mo12135getSupertypes, "this.supertypes");
                return mo12135getSupertypes;
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + supertypes + ", " + Reflection.getOrCreateKotlinClass(supertypes.getClass())).toString());
        }

        @NotNull
        public static TypeConstructorMarker typeConstructor(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker typeConstructor) {
            Intrinsics.checkParameterIsNotNull(typeConstructor, "$this$typeConstructor");
            return TypeSystemInferenceExtensionContext.DefaultImpls.typeConstructor(classicTypeSystemContext, typeConstructor);
        }

        @NotNull
        public static TypeConstructorMarker typeConstructor(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker typeConstructor) {
            Intrinsics.checkParameterIsNotNull(typeConstructor, "$this$typeConstructor");
            if (typeConstructor instanceof SimpleType) {
                return ((SimpleType) typeConstructor).mo12131getConstructor();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + typeConstructor + ", " + Reflection.getOrCreateKotlinClass(typeConstructor.getClass())).toString());
        }

        @NotNull
        public static SimpleTypeMarker upperBound(ClassicTypeSystemContext classicTypeSystemContext, @NotNull FlexibleTypeMarker upperBound) {
            Intrinsics.checkParameterIsNotNull(upperBound, "$this$upperBound");
            if (upperBound instanceof FlexibleType) {
                return ((FlexibleType) upperBound).getUpperBound();
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + upperBound + ", " + Reflection.getOrCreateKotlinClass(upperBound.getClass())).toString());
        }

        @NotNull
        public static SimpleTypeMarker upperBoundIfFlexible(ClassicTypeSystemContext classicTypeSystemContext, @NotNull KotlinTypeMarker upperBoundIfFlexible) {
            Intrinsics.checkParameterIsNotNull(upperBoundIfFlexible, "$this$upperBoundIfFlexible");
            return TypeSystemInferenceExtensionContext.DefaultImpls.upperBoundIfFlexible(classicTypeSystemContext, upperBoundIfFlexible);
        }

        @NotNull
        public static SimpleTypeMarker withNullability(ClassicTypeSystemContext classicTypeSystemContext, @NotNull SimpleTypeMarker withNullability, boolean z) {
            Intrinsics.checkParameterIsNotNull(withNullability, "$this$withNullability");
            if (withNullability instanceof SimpleType) {
                return ((SimpleType) withNullability).mo12132makeNullableAsSpecified(z);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + withNullability + ", " + Reflection.getOrCreateKotlinClass(withNullability.getClass())).toString());
        }

        @NotNull
        public static TypeVariance getVariance(ClassicTypeSystemContext classicTypeSystemContext, @NotNull TypeParameterMarker getVariance) {
            Intrinsics.checkParameterIsNotNull(getVariance, "$this$getVariance");
            if (getVariance instanceof TypeParameterDescriptor) {
                Variance variance = ((TypeParameterDescriptor) getVariance).getVariance();
                Intrinsics.checkExpressionValueIsNotNull(variance, "this.variance");
                return ClassicTypeSystemContextKt.convertVariance(variance);
            }
            throw new IllegalArgumentException(("ClassicTypeSystemContext couldn't handle: " + getVariance + ", " + Reflection.getOrCreateKotlinClass(getVariance.getClass())).toString());
        }
    }
}
