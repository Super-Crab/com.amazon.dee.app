package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.model.ArgumentList;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AbstractTypeChecker.kt */
/* loaded from: classes4.dex */
public final class AbstractTypeChecker {
    public static final AbstractTypeChecker INSTANCE = new AbstractTypeChecker();
    @JvmField
    public static boolean RUN_SLOW_ASSERTIONS;

    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TypeVariance.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[TypeVariance.INV.ordinal()] = 1;
            $EnumSwitchMapping$0[TypeVariance.OUT.ordinal()] = 2;
            $EnumSwitchMapping$0[TypeVariance.IN.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[AbstractTypeCheckerContext.LowerCapturedTypePolicy.values().length];
            $EnumSwitchMapping$1[AbstractTypeCheckerContext.LowerCapturedTypePolicy.CHECK_ONLY_LOWER.ordinal()] = 1;
            $EnumSwitchMapping$1[AbstractTypeCheckerContext.LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER.ordinal()] = 2;
            $EnumSwitchMapping$1[AbstractTypeCheckerContext.LowerCapturedTypePolicy.SKIP_LOWER.ordinal()] = 3;
        }
    }

    private AbstractTypeChecker() {
    }

    private final Boolean checkSubtypeForIntegerLiteralType(@NotNull AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        if (abstractTypeCheckerContext.isIntegerLiteralType(simpleTypeMarker) || abstractTypeCheckerContext.isIntegerLiteralType(simpleTypeMarker2)) {
            AbstractTypeChecker$checkSubtypeForIntegerLiteralType$1 abstractTypeChecker$checkSubtypeForIntegerLiteralType$1 = new AbstractTypeChecker$checkSubtypeForIntegerLiteralType$1(abstractTypeCheckerContext);
            if (abstractTypeCheckerContext.isIntegerLiteralType(simpleTypeMarker) && abstractTypeCheckerContext.isIntegerLiteralType(simpleTypeMarker2)) {
                return true;
            }
            if (abstractTypeCheckerContext.isIntegerLiteralType(simpleTypeMarker)) {
                if (abstractTypeChecker$checkSubtypeForIntegerLiteralType$1.invoke(simpleTypeMarker, simpleTypeMarker2, false)) {
                    return true;
                }
            } else if (abstractTypeCheckerContext.isIntegerLiteralType(simpleTypeMarker2) && abstractTypeChecker$checkSubtypeForIntegerLiteralType$1.invoke(simpleTypeMarker2, simpleTypeMarker, true)) {
                return true;
            }
            return null;
        }
        return null;
    }

    private final Boolean checkSubtypeForSpecialCases(@NotNull AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        boolean z = true;
        if (!abstractTypeCheckerContext.isError(simpleTypeMarker) && !abstractTypeCheckerContext.isError(simpleTypeMarker2)) {
            if (!abstractTypeCheckerContext.isStubType(simpleTypeMarker) && !abstractTypeCheckerContext.isStubType(simpleTypeMarker2)) {
                CapturedTypeMarker asCapturedType = abstractTypeCheckerContext.asCapturedType(simpleTypeMarker2);
                KotlinTypeMarker lowerType = asCapturedType != null ? abstractTypeCheckerContext.lowerType(asCapturedType) : null;
                if (asCapturedType != null && lowerType != null) {
                    int i = WhenMappings.$EnumSwitchMapping$1[abstractTypeCheckerContext.getLowerCapturedTypePolicy(simpleTypeMarker, asCapturedType).ordinal()];
                    if (i != 1) {
                        if (i == 2 && isSubtypeOf(abstractTypeCheckerContext, simpleTypeMarker, lowerType)) {
                            return true;
                        }
                    } else {
                        return Boolean.valueOf(isSubtypeOf(abstractTypeCheckerContext, simpleTypeMarker, lowerType));
                    }
                }
                TypeConstructorMarker typeConstructor = abstractTypeCheckerContext.typeConstructor(simpleTypeMarker2);
                if (!abstractTypeCheckerContext.isIntersection(typeConstructor)) {
                    return null;
                }
                boolean z2 = !abstractTypeCheckerContext.isMarkedNullable(simpleTypeMarker2);
                if (_Assertions.ENABLED && !z2) {
                    throw new AssertionError("Intersection type should not be marked nullable!: " + simpleTypeMarker2);
                }
                Collection<KotlinTypeMarker> supertypes = abstractTypeCheckerContext.supertypes(typeConstructor);
                if (!(supertypes instanceof Collection) || !supertypes.isEmpty()) {
                    Iterator<T> it2 = supertypes.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        if (!INSTANCE.isSubtypeOf(abstractTypeCheckerContext, simpleTypeMarker, (KotlinTypeMarker) it2.next())) {
                            z = false;
                            break;
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
            return Boolean.valueOf(abstractTypeCheckerContext.isStubTypeEqualsToAnything());
        } else if (abstractTypeCheckerContext.isErrorTypeEqualsToAnything()) {
            return true;
        } else {
            if (abstractTypeCheckerContext.isMarkedNullable(simpleTypeMarker) && !abstractTypeCheckerContext.isMarkedNullable(simpleTypeMarker2)) {
                return false;
            }
            return Boolean.valueOf(AbstractStrictEqualityTypeChecker.INSTANCE.strictEqualTypes(abstractTypeCheckerContext, abstractTypeCheckerContext.withNullability(simpleTypeMarker, false), abstractTypeCheckerContext.withNullability(simpleTypeMarker2, false)));
        }
    }

    private final List<SimpleTypeMarker> collectAllSupertypesWithGivenTypeConstructor(@NotNull AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        String joinToString$default;
        AbstractTypeCheckerContext.SupertypesPolicy mo12129substitutionSupertypePolicy;
        List<SimpleTypeMarker> emptyList;
        List<SimpleTypeMarker> listOf;
        List<SimpleTypeMarker> emptyList2;
        List<SimpleTypeMarker> fastCorrespondingSupertypes = abstractTypeCheckerContext.fastCorrespondingSupertypes(simpleTypeMarker, typeConstructorMarker);
        if (fastCorrespondingSupertypes != null) {
            return fastCorrespondingSupertypes;
        }
        if (!abstractTypeCheckerContext.isClassTypeConstructor(typeConstructorMarker) && abstractTypeCheckerContext.isClassType(simpleTypeMarker)) {
            emptyList2 = CollectionsKt__CollectionsKt.emptyList();
            return emptyList2;
        } else if (abstractTypeCheckerContext.isCommonFinalClassConstructor(typeConstructorMarker)) {
            if (abstractTypeCheckerContext.areEqualTypeConstructors(abstractTypeCheckerContext.typeConstructor(simpleTypeMarker), typeConstructorMarker)) {
                SimpleTypeMarker captureFromArguments = abstractTypeCheckerContext.captureFromArguments(simpleTypeMarker, CaptureStatus.FOR_SUBTYPING);
                if (captureFromArguments == null) {
                    captureFromArguments = simpleTypeMarker;
                }
                listOf = CollectionsKt__CollectionsJVMKt.listOf(captureFromArguments);
                return listOf;
            }
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else {
            SmartList smartList = new SmartList();
            abstractTypeCheckerContext.initialize();
            ArrayDeque<SimpleTypeMarker> supertypesDeque = abstractTypeCheckerContext.getSupertypesDeque();
            if (supertypesDeque == null) {
                Intrinsics.throwNpe();
            }
            Set<SimpleTypeMarker> supertypesSet = abstractTypeCheckerContext.getSupertypesSet();
            if (supertypesSet == null) {
                Intrinsics.throwNpe();
            }
            supertypesDeque.push(simpleTypeMarker);
            while (!supertypesDeque.isEmpty()) {
                if (supertypesSet.size() <= 1000) {
                    SimpleTypeMarker current = supertypesDeque.pop();
                    Intrinsics.checkExpressionValueIsNotNull(current, "current");
                    if (supertypesSet.add(current)) {
                        SimpleTypeMarker captureFromArguments2 = abstractTypeCheckerContext.captureFromArguments(current, CaptureStatus.FOR_SUBTYPING);
                        if (captureFromArguments2 == null) {
                            captureFromArguments2 = current;
                        }
                        if (abstractTypeCheckerContext.areEqualTypeConstructors(abstractTypeCheckerContext.typeConstructor(captureFromArguments2), typeConstructorMarker)) {
                            smartList.add(captureFromArguments2);
                            mo12129substitutionSupertypePolicy = AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE;
                        } else if (abstractTypeCheckerContext.argumentsCount(captureFromArguments2) == 0) {
                            mo12129substitutionSupertypePolicy = AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                        } else {
                            mo12129substitutionSupertypePolicy = abstractTypeCheckerContext.mo12129substitutionSupertypePolicy(captureFromArguments2);
                        }
                        if (!(!Intrinsics.areEqual(mo12129substitutionSupertypePolicy, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                            mo12129substitutionSupertypePolicy = null;
                        }
                        if (mo12129substitutionSupertypePolicy != null) {
                            for (KotlinTypeMarker kotlinTypeMarker : abstractTypeCheckerContext.supertypes(abstractTypeCheckerContext.typeConstructor(current))) {
                                supertypesDeque.add(mo12129substitutionSupertypePolicy.mo12079transformType(abstractTypeCheckerContext, kotlinTypeMarker));
                            }
                        }
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Too many supertypes for type: ");
                    sb.append(simpleTypeMarker);
                    sb.append(". Supertypes = ");
                    joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null);
                    sb.append(joinToString$default);
                    throw new IllegalStateException(sb.toString().toString());
                }
            }
            abstractTypeCheckerContext.clear();
            return smartList;
        }
    }

    private final List<SimpleTypeMarker> collectAndFilter(@NotNull AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        return selectOnlyPureKotlinSupertypes(abstractTypeCheckerContext, collectAllSupertypesWithGivenTypeConstructor(abstractTypeCheckerContext, simpleTypeMarker, typeConstructorMarker));
    }

    private final boolean completeIsSubTypeOf(@NotNull AbstractTypeCheckerContext abstractTypeCheckerContext, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2) {
        Boolean checkSubtypeForSpecialCases = checkSubtypeForSpecialCases(abstractTypeCheckerContext, abstractTypeCheckerContext.lowerBoundIfFlexible(kotlinTypeMarker), abstractTypeCheckerContext.upperBoundIfFlexible(kotlinTypeMarker2));
        if (checkSubtypeForSpecialCases != null) {
            boolean booleanValue = checkSubtypeForSpecialCases.booleanValue();
            abstractTypeCheckerContext.addSubtypeConstraint(kotlinTypeMarker, kotlinTypeMarker2);
            return booleanValue;
        }
        Boolean addSubtypeConstraint = abstractTypeCheckerContext.addSubtypeConstraint(kotlinTypeMarker, kotlinTypeMarker2);
        return addSubtypeConstraint != null ? addSubtypeConstraint.booleanValue() : isSubtypeOfForSingleClassifierType(abstractTypeCheckerContext, abstractTypeCheckerContext.lowerBoundIfFlexible(kotlinTypeMarker), abstractTypeCheckerContext.upperBoundIfFlexible(kotlinTypeMarker2));
    }

    private final boolean hasNothingSupertype(@NotNull AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker) {
        String joinToString$default;
        AbstractTypeCheckerContext.SupertypesPolicy supertypesPolicy;
        TypeConstructorMarker typeConstructor = abstractTypeCheckerContext.typeConstructor(simpleTypeMarker);
        if (abstractTypeCheckerContext.isClassTypeConstructor(typeConstructor)) {
            return abstractTypeCheckerContext.isNothingConstructor(typeConstructor);
        }
        if (abstractTypeCheckerContext.isNothingConstructor(abstractTypeCheckerContext.typeConstructor(simpleTypeMarker))) {
            return true;
        }
        abstractTypeCheckerContext.initialize();
        ArrayDeque<SimpleTypeMarker> supertypesDeque = abstractTypeCheckerContext.getSupertypesDeque();
        if (supertypesDeque == null) {
            Intrinsics.throwNpe();
        }
        Set<SimpleTypeMarker> supertypesSet = abstractTypeCheckerContext.getSupertypesSet();
        if (supertypesSet == null) {
            Intrinsics.throwNpe();
        }
        supertypesDeque.push(simpleTypeMarker);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() <= 1000) {
                SimpleTypeMarker current = supertypesDeque.pop();
                Intrinsics.checkExpressionValueIsNotNull(current, "current");
                if (supertypesSet.add(current)) {
                    if (abstractTypeCheckerContext.isClassType(current)) {
                        supertypesPolicy = AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE;
                    } else {
                        supertypesPolicy = AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                    }
                    if (!(!Intrinsics.areEqual(supertypesPolicy, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                        supertypesPolicy = null;
                    }
                    if (supertypesPolicy != null) {
                        for (KotlinTypeMarker kotlinTypeMarker : abstractTypeCheckerContext.supertypes(abstractTypeCheckerContext.typeConstructor(current))) {
                            SimpleTypeMarker mo12079transformType = supertypesPolicy.mo12079transformType(abstractTypeCheckerContext, kotlinTypeMarker);
                            if (abstractTypeCheckerContext.isNothingConstructor(abstractTypeCheckerContext.typeConstructor(mo12079transformType))) {
                                abstractTypeCheckerContext.clear();
                                return true;
                            }
                            supertypesDeque.add(mo12079transformType);
                        }
                        continue;
                    } else {
                        continue;
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Too many supertypes for type: ");
                sb.append(simpleTypeMarker);
                sb.append(". Supertypes = ");
                joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null);
                sb.append(joinToString$default);
                throw new IllegalStateException(sb.toString().toString());
            }
        }
        abstractTypeCheckerContext.clear();
        return false;
    }

    private final boolean isCommonDenotableType(@NotNull AbstractTypeCheckerContext abstractTypeCheckerContext, KotlinTypeMarker kotlinTypeMarker) {
        return abstractTypeCheckerContext.isDenotable(abstractTypeCheckerContext.typeConstructor(kotlinTypeMarker)) && !abstractTypeCheckerContext.isDynamic(kotlinTypeMarker) && !abstractTypeCheckerContext.isDefinitelyNotNullType(kotlinTypeMarker) && Intrinsics.areEqual(abstractTypeCheckerContext.typeConstructor(abstractTypeCheckerContext.lowerBoundIfFlexible(kotlinTypeMarker)), abstractTypeCheckerContext.typeConstructor(abstractTypeCheckerContext.upperBoundIfFlexible(kotlinTypeMarker)));
    }

    private final boolean isSubtypeOfForSingleClassifierType(@NotNull AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        int collectionSizeOrDefault;
        KotlinTypeMarker type;
        boolean z = false;
        boolean z2 = true;
        if (RUN_SLOW_ASSERTIONS) {
            boolean z3 = abstractTypeCheckerContext.isSingleClassifierType(simpleTypeMarker) || abstractTypeCheckerContext.isIntersection(abstractTypeCheckerContext.typeConstructor(simpleTypeMarker)) || abstractTypeCheckerContext.isAllowedTypeVariable(simpleTypeMarker);
            if (_Assertions.ENABLED && !z3) {
                throw new AssertionError("Not singleClassifierType and not intersection subType: " + simpleTypeMarker);
            }
            boolean z4 = abstractTypeCheckerContext.isSingleClassifierType(simpleTypeMarker2) || abstractTypeCheckerContext.isAllowedTypeVariable(simpleTypeMarker2);
            if (_Assertions.ENABLED && !z4) {
                throw new AssertionError("Not singleClassifierType superType: " + simpleTypeMarker2);
            }
        }
        if (!AbstractNullabilityChecker.INSTANCE.isPossibleSubtype(abstractTypeCheckerContext, simpleTypeMarker, simpleTypeMarker2)) {
            return false;
        }
        Boolean checkSubtypeForIntegerLiteralType = checkSubtypeForIntegerLiteralType(abstractTypeCheckerContext, abstractTypeCheckerContext.lowerBoundIfFlexible(simpleTypeMarker), abstractTypeCheckerContext.upperBoundIfFlexible(simpleTypeMarker2));
        if (checkSubtypeForIntegerLiteralType != null) {
            boolean booleanValue = checkSubtypeForIntegerLiteralType.booleanValue();
            abstractTypeCheckerContext.addSubtypeConstraint(simpleTypeMarker, simpleTypeMarker2);
            return booleanValue;
        }
        TypeConstructorMarker typeConstructor = abstractTypeCheckerContext.typeConstructor(simpleTypeMarker2);
        if ((abstractTypeCheckerContext.isEqualTypeConstructors(abstractTypeCheckerContext.typeConstructor(simpleTypeMarker), typeConstructor) && abstractTypeCheckerContext.parametersCount(typeConstructor) == 0) || abstractTypeCheckerContext.isAnyConstructor(abstractTypeCheckerContext.typeConstructor(simpleTypeMarker2))) {
            return true;
        }
        List<SimpleTypeMarker> findCorrespondingSupertypes = findCorrespondingSupertypes(abstractTypeCheckerContext, simpleTypeMarker, typeConstructor);
        int size = findCorrespondingSupertypes.size();
        if (size == 0) {
            return hasNothingSupertype(abstractTypeCheckerContext, simpleTypeMarker);
        }
        if (size != 1) {
            ArgumentList argumentList = new ArgumentList(abstractTypeCheckerContext.parametersCount(typeConstructor));
            int parametersCount = abstractTypeCheckerContext.parametersCount(typeConstructor);
            int i = 0;
            boolean z5 = false;
            while (i < parametersCount) {
                z5 = (z5 || abstractTypeCheckerContext.getVariance(abstractTypeCheckerContext.getParameter(typeConstructor, i)) != TypeVariance.OUT) ? z2 : z;
                if (!z5) {
                    collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(findCorrespondingSupertypes, 10);
                    ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                    for (SimpleTypeMarker simpleTypeMarker3 : findCorrespondingSupertypes) {
                        TypeArgumentMarker argumentOrNull = abstractTypeCheckerContext.getArgumentOrNull(simpleTypeMarker3, i);
                        if (argumentOrNull != null) {
                            if (!(abstractTypeCheckerContext.getVariance(argumentOrNull) == TypeVariance.INV)) {
                                argumentOrNull = null;
                            }
                            if (argumentOrNull != null && (type = abstractTypeCheckerContext.getType(argumentOrNull)) != null) {
                                arrayList.add(type);
                            }
                        }
                        throw new IllegalStateException(("Incorrect type: " + simpleTypeMarker3 + ", subType: " + simpleTypeMarker + ", superType: " + simpleTypeMarker2).toString());
                    }
                    argumentList.add(abstractTypeCheckerContext.asTypeArgument(abstractTypeCheckerContext.intersectTypes(arrayList)));
                }
                i++;
                z = false;
                z2 = true;
            }
            if (!z5 && isSubtypeForSameConstructor(abstractTypeCheckerContext, argumentList, simpleTypeMarker2)) {
                return true;
            }
            if (!findCorrespondingSupertypes.isEmpty()) {
                for (SimpleTypeMarker simpleTypeMarker4 : findCorrespondingSupertypes) {
                    if (INSTANCE.isSubtypeForSameConstructor(abstractTypeCheckerContext, abstractTypeCheckerContext.asArgumentList(simpleTypeMarker4), simpleTypeMarker2)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return isSubtypeForSameConstructor(abstractTypeCheckerContext, abstractTypeCheckerContext.asArgumentList((SimpleTypeMarker) CollectionsKt.first((List<? extends Object>) findCorrespondingSupertypes)), simpleTypeMarker2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<SimpleTypeMarker> selectOnlyPureKotlinSupertypes(@NotNull AbstractTypeCheckerContext abstractTypeCheckerContext, List<? extends SimpleTypeMarker> list) {
        if (list.size() < 2) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it2 = list.iterator();
        while (true) {
            boolean z = true;
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            TypeArgumentListMarker asArgumentList = abstractTypeCheckerContext.asArgumentList((SimpleTypeMarker) next);
            int size = abstractTypeCheckerContext.size(asArgumentList);
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                if (!(abstractTypeCheckerContext.asFlexibleType(abstractTypeCheckerContext.getType(abstractTypeCheckerContext.get(asArgumentList, i))) == null)) {
                    z = false;
                    break;
                }
                i++;
            }
            if (z) {
                arrayList.add(next);
            }
        }
        return arrayList.isEmpty() ^ true ? arrayList : list;
    }

    @Nullable
    public final TypeVariance effectiveVariance(@NotNull TypeVariance declared, @NotNull TypeVariance useSite) {
        Intrinsics.checkParameterIsNotNull(declared, "declared");
        Intrinsics.checkParameterIsNotNull(useSite, "useSite");
        TypeVariance typeVariance = TypeVariance.INV;
        if (declared == typeVariance) {
            return useSite;
        }
        if (useSite != typeVariance && declared != useSite) {
            return null;
        }
        return declared;
    }

    public final boolean equalTypes(@NotNull AbstractTypeCheckerContext context, @NotNull KotlinTypeMarker a, @NotNull KotlinTypeMarker b) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        if (a == b) {
            return true;
        }
        if (INSTANCE.isCommonDenotableType(context, a) && INSTANCE.isCommonDenotableType(context, b)) {
            KotlinTypeMarker refineType = context.refineType(a);
            KotlinTypeMarker refineType2 = context.refineType(b);
            SimpleTypeMarker lowerBoundIfFlexible = context.lowerBoundIfFlexible(refineType);
            if (!context.areEqualTypeConstructors(context.typeConstructor(refineType), context.typeConstructor(refineType2))) {
                return false;
            }
            if (context.argumentsCount(lowerBoundIfFlexible) == 0) {
                return context.hasFlexibleNullability(refineType) || context.hasFlexibleNullability(refineType2) || context.isMarkedNullable(lowerBoundIfFlexible) == context.isMarkedNullable(context.lowerBoundIfFlexible(refineType2));
            }
        }
        return INSTANCE.isSubtypeOf(context, a, b) && INSTANCE.isSubtypeOf(context, b, a);
    }

    @NotNull
    public final List<SimpleTypeMarker> findCorrespondingSupertypes(@NotNull AbstractTypeCheckerContext findCorrespondingSupertypes, @NotNull SimpleTypeMarker subType, @NotNull TypeConstructorMarker superConstructor) {
        String joinToString$default;
        AbstractTypeCheckerContext.SupertypesPolicy supertypesPolicy;
        Intrinsics.checkParameterIsNotNull(findCorrespondingSupertypes, "$this$findCorrespondingSupertypes");
        Intrinsics.checkParameterIsNotNull(subType, "subType");
        Intrinsics.checkParameterIsNotNull(superConstructor, "superConstructor");
        if (findCorrespondingSupertypes.isClassType(subType)) {
            return collectAndFilter(findCorrespondingSupertypes, subType, superConstructor);
        }
        if (!findCorrespondingSupertypes.isClassTypeConstructor(superConstructor) && !findCorrespondingSupertypes.isIntegerLiteralTypeConstructor(superConstructor)) {
            return collectAllSupertypesWithGivenTypeConstructor(findCorrespondingSupertypes, subType, superConstructor);
        }
        SmartList<SimpleTypeMarker> smartList = new SmartList();
        findCorrespondingSupertypes.initialize();
        ArrayDeque<SimpleTypeMarker> supertypesDeque = findCorrespondingSupertypes.getSupertypesDeque();
        if (supertypesDeque == null) {
            Intrinsics.throwNpe();
        }
        Set<SimpleTypeMarker> supertypesSet = findCorrespondingSupertypes.getSupertypesSet();
        if (supertypesSet == null) {
            Intrinsics.throwNpe();
        }
        supertypesDeque.push(subType);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() <= 1000) {
                SimpleTypeMarker current = supertypesDeque.pop();
                Intrinsics.checkExpressionValueIsNotNull(current, "current");
                if (supertypesSet.add(current)) {
                    if (findCorrespondingSupertypes.isClassType(current)) {
                        smartList.add(current);
                        supertypesPolicy = AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE;
                    } else {
                        supertypesPolicy = AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                    }
                    if (!(!Intrinsics.areEqual(supertypesPolicy, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                        supertypesPolicy = null;
                    }
                    if (supertypesPolicy != null) {
                        for (KotlinTypeMarker kotlinTypeMarker : findCorrespondingSupertypes.supertypes(findCorrespondingSupertypes.typeConstructor(current))) {
                            supertypesDeque.add(supertypesPolicy.mo12079transformType(findCorrespondingSupertypes, kotlinTypeMarker));
                        }
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Too many supertypes for type: ");
                sb.append(subType);
                sb.append(". Supertypes = ");
                joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null);
                sb.append(joinToString$default);
                throw new IllegalStateException(sb.toString().toString());
            }
        }
        findCorrespondingSupertypes.clear();
        ArrayList arrayList = new ArrayList();
        for (SimpleTypeMarker it2 : smartList) {
            AbstractTypeChecker abstractTypeChecker = INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, abstractTypeChecker.collectAndFilter(findCorrespondingSupertypes, it2, superConstructor));
        }
        return arrayList;
    }

    public final boolean isSubtypeForSameConstructor(@NotNull AbstractTypeCheckerContext isSubtypeForSameConstructor, @NotNull TypeArgumentListMarker capturedSubArguments, @NotNull SimpleTypeMarker superType) {
        int i;
        int i2;
        boolean equalTypes;
        int i3;
        Intrinsics.checkParameterIsNotNull(isSubtypeForSameConstructor, "$this$isSubtypeForSameConstructor");
        Intrinsics.checkParameterIsNotNull(capturedSubArguments, "capturedSubArguments");
        Intrinsics.checkParameterIsNotNull(superType, "superType");
        TypeConstructorMarker typeConstructor = isSubtypeForSameConstructor.typeConstructor(superType);
        int parametersCount = isSubtypeForSameConstructor.parametersCount(typeConstructor);
        for (int i4 = 0; i4 < parametersCount; i4++) {
            TypeArgumentMarker argument = isSubtypeForSameConstructor.getArgument(superType, i4);
            if (!isSubtypeForSameConstructor.isStarProjection(argument)) {
                KotlinTypeMarker type = isSubtypeForSameConstructor.getType(argument);
                TypeArgumentMarker typeArgumentMarker = isSubtypeForSameConstructor.get(capturedSubArguments, i4);
                boolean z = isSubtypeForSameConstructor.getVariance(typeArgumentMarker) == TypeVariance.INV;
                if (_Assertions.ENABLED && !z) {
                    throw new AssertionError("Incorrect sub argument: " + typeArgumentMarker);
                }
                KotlinTypeMarker type2 = isSubtypeForSameConstructor.getType(typeArgumentMarker);
                TypeVariance effectiveVariance = effectiveVariance(isSubtypeForSameConstructor.getVariance(isSubtypeForSameConstructor.getParameter(typeConstructor, i4)), isSubtypeForSameConstructor.getVariance(argument));
                if (effectiveVariance != null) {
                    i = isSubtypeForSameConstructor.argumentsDepth;
                    if (i > 100) {
                        throw new IllegalStateException(("Arguments depth is too high. Some related argument: " + type2).toString());
                    }
                    i2 = isSubtypeForSameConstructor.argumentsDepth;
                    isSubtypeForSameConstructor.argumentsDepth = i2 + 1;
                    int i5 = WhenMappings.$EnumSwitchMapping$0[effectiveVariance.ordinal()];
                    if (i5 == 1) {
                        equalTypes = INSTANCE.equalTypes(isSubtypeForSameConstructor, type2, type);
                    } else if (i5 == 2) {
                        equalTypes = INSTANCE.isSubtypeOf(isSubtypeForSameConstructor, type2, type);
                    } else if (i5 != 3) {
                        throw new NoWhenBranchMatchedException();
                    } else {
                        equalTypes = INSTANCE.isSubtypeOf(isSubtypeForSameConstructor, type, type2);
                    }
                    i3 = isSubtypeForSameConstructor.argumentsDepth;
                    isSubtypeForSameConstructor.argumentsDepth = i3 - 1;
                    if (!equalTypes) {
                        return false;
                    }
                } else {
                    return isSubtypeForSameConstructor.isErrorTypeEqualsToAnything();
                }
            }
        }
        return true;
    }

    public final boolean isSubtypeOf(@NotNull AbstractTypeCheckerContext context, @NotNull KotlinTypeMarker subType, @NotNull KotlinTypeMarker superType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(subType, "subType");
        Intrinsics.checkParameterIsNotNull(superType, "superType");
        if (subType == superType) {
            return true;
        }
        return INSTANCE.completeIsSubTypeOf(context, context.prepareType(context.refineType(subType)), context.prepareType(context.refineType(superType)));
    }
}
