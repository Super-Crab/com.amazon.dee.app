package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.Set;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: AbstractTypeChecker.kt */
/* loaded from: classes4.dex */
public final class AbstractNullabilityChecker {
    public static final AbstractNullabilityChecker INSTANCE = new AbstractNullabilityChecker();

    private AbstractNullabilityChecker() {
    }

    private final boolean isApplicableAsEndNode(@NotNull AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        if (abstractTypeCheckerContext.isNothing(simpleTypeMarker)) {
            return true;
        }
        if (abstractTypeCheckerContext.isMarkedNullable(simpleTypeMarker)) {
            return false;
        }
        if (abstractTypeCheckerContext.isStubTypeEqualsToAnything() && abstractTypeCheckerContext.isStubType(simpleTypeMarker)) {
            return true;
        }
        return abstractTypeCheckerContext.isEqualTypeConstructors(abstractTypeCheckerContext.typeConstructor(simpleTypeMarker), typeConstructorMarker);
    }

    private final boolean runIsPossibleSubtype(@NotNull AbstractTypeCheckerContext abstractTypeCheckerContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        if (AbstractTypeChecker.RUN_SLOW_ASSERTIONS) {
            boolean z = abstractTypeCheckerContext.isSingleClassifierType(simpleTypeMarker) || abstractTypeCheckerContext.isIntersection(abstractTypeCheckerContext.typeConstructor(simpleTypeMarker)) || abstractTypeCheckerContext.isAllowedTypeVariable(simpleTypeMarker);
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError("Not singleClassifierType and not intersection subType: " + simpleTypeMarker);
            }
            boolean z2 = abstractTypeCheckerContext.isSingleClassifierType(simpleTypeMarker2) || abstractTypeCheckerContext.isAllowedTypeVariable(simpleTypeMarker2);
            if (_Assertions.ENABLED && !z2) {
                throw new AssertionError("Not singleClassifierType superType: " + simpleTypeMarker2);
            }
        }
        if (!abstractTypeCheckerContext.isMarkedNullable(simpleTypeMarker2) && !abstractTypeCheckerContext.isDefinitelyNotNullType(simpleTypeMarker) && !hasNotNullSupertype(abstractTypeCheckerContext, simpleTypeMarker, AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE)) {
            if (abstractTypeCheckerContext.isDefinitelyNotNullType(simpleTypeMarker2) || hasNotNullSupertype(abstractTypeCheckerContext, simpleTypeMarker2, AbstractTypeCheckerContext.SupertypesPolicy.UpperIfFlexible.INSTANCE) || abstractTypeCheckerContext.isClassType(simpleTypeMarker)) {
                return false;
            }
            return hasPathByNotMarkedNullableNodes(abstractTypeCheckerContext, simpleTypeMarker, abstractTypeCheckerContext.typeConstructor(simpleTypeMarker2));
        }
        return true;
    }

    public final boolean hasNotNullSupertype(@NotNull AbstractTypeCheckerContext hasNotNullSupertype, @NotNull SimpleTypeMarker type, @NotNull AbstractTypeCheckerContext.SupertypesPolicy supertypesPolicy) {
        String joinToString$default;
        Intrinsics.checkParameterIsNotNull(hasNotNullSupertype, "$this$hasNotNullSupertype");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(supertypesPolicy, "supertypesPolicy");
        if ((hasNotNullSupertype.isClassType(type) && !hasNotNullSupertype.isMarkedNullable(type)) || hasNotNullSupertype.isDefinitelyNotNullType(type)) {
            return true;
        }
        hasNotNullSupertype.initialize();
        ArrayDeque<SimpleTypeMarker> supertypesDeque = hasNotNullSupertype.getSupertypesDeque();
        if (supertypesDeque == null) {
            Intrinsics.throwNpe();
        }
        Set<SimpleTypeMarker> supertypesSet = hasNotNullSupertype.getSupertypesSet();
        if (supertypesSet == null) {
            Intrinsics.throwNpe();
        }
        supertypesDeque.push(type);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() <= 1000) {
                SimpleTypeMarker current = supertypesDeque.pop();
                Intrinsics.checkExpressionValueIsNotNull(current, "current");
                if (supertypesSet.add(current)) {
                    AbstractTypeCheckerContext.SupertypesPolicy supertypesPolicy2 = hasNotNullSupertype.isMarkedNullable(current) ? AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE : supertypesPolicy;
                    if (!(!Intrinsics.areEqual(supertypesPolicy2, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                        supertypesPolicy2 = null;
                    }
                    if (supertypesPolicy2 != null) {
                        for (KotlinTypeMarker kotlinTypeMarker : hasNotNullSupertype.supertypes(hasNotNullSupertype.typeConstructor(current))) {
                            SimpleTypeMarker mo12079transformType = supertypesPolicy2.mo12079transformType(hasNotNullSupertype, kotlinTypeMarker);
                            if ((hasNotNullSupertype.isClassType(mo12079transformType) && !hasNotNullSupertype.isMarkedNullable(mo12079transformType)) || hasNotNullSupertype.isDefinitelyNotNullType(mo12079transformType)) {
                                hasNotNullSupertype.clear();
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
                sb.append(type);
                sb.append(". Supertypes = ");
                joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null);
                sb.append(joinToString$default);
                throw new IllegalStateException(sb.toString().toString());
            }
        }
        hasNotNullSupertype.clear();
        return false;
    }

    public final boolean hasPathByNotMarkedNullableNodes(@NotNull AbstractTypeCheckerContext hasPathByNotMarkedNullableNodes, @NotNull SimpleTypeMarker start, @NotNull TypeConstructorMarker end) {
        String joinToString$default;
        Intrinsics.checkParameterIsNotNull(hasPathByNotMarkedNullableNodes, "$this$hasPathByNotMarkedNullableNodes");
        Intrinsics.checkParameterIsNotNull(start, "start");
        Intrinsics.checkParameterIsNotNull(end, "end");
        if (INSTANCE.isApplicableAsEndNode(hasPathByNotMarkedNullableNodes, start, end)) {
            return true;
        }
        hasPathByNotMarkedNullableNodes.initialize();
        ArrayDeque<SimpleTypeMarker> supertypesDeque = hasPathByNotMarkedNullableNodes.getSupertypesDeque();
        if (supertypesDeque == null) {
            Intrinsics.throwNpe();
        }
        Set<SimpleTypeMarker> supertypesSet = hasPathByNotMarkedNullableNodes.getSupertypesSet();
        if (supertypesSet == null) {
            Intrinsics.throwNpe();
        }
        supertypesDeque.push(start);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() <= 1000) {
                SimpleTypeMarker current = supertypesDeque.pop();
                Intrinsics.checkExpressionValueIsNotNull(current, "current");
                if (supertypesSet.add(current)) {
                    AbstractTypeCheckerContext.SupertypesPolicy supertypesPolicy = hasPathByNotMarkedNullableNodes.isMarkedNullable(current) ? AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE : AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                    if (!(!Intrinsics.areEqual(supertypesPolicy, AbstractTypeCheckerContext.SupertypesPolicy.None.INSTANCE))) {
                        supertypesPolicy = null;
                    }
                    if (supertypesPolicy != null) {
                        for (KotlinTypeMarker kotlinTypeMarker : hasPathByNotMarkedNullableNodes.supertypes(hasPathByNotMarkedNullableNodes.typeConstructor(current))) {
                            SimpleTypeMarker mo12079transformType = supertypesPolicy.mo12079transformType(hasPathByNotMarkedNullableNodes, kotlinTypeMarker);
                            if (INSTANCE.isApplicableAsEndNode(hasPathByNotMarkedNullableNodes, mo12079transformType, end)) {
                                hasPathByNotMarkedNullableNodes.clear();
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
                sb.append(start);
                sb.append(". Supertypes = ");
                joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null);
                sb.append(joinToString$default);
                throw new IllegalStateException(sb.toString().toString());
            }
        }
        hasPathByNotMarkedNullableNodes.clear();
        return false;
    }

    public final boolean isPossibleSubtype(@NotNull AbstractTypeCheckerContext context, @NotNull SimpleTypeMarker subType, @NotNull SimpleTypeMarker superType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(subType, "subType");
        Intrinsics.checkParameterIsNotNull(superType, "superType");
        return runIsPossibleSubtype(context, subType, superType);
    }
}
