package kotlin.reflect.jvm.internal.impl.types.checker;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import org.jetbrains.annotations.NotNull;
/* compiled from: IntersectionType.kt */
/* loaded from: classes4.dex */
public final class TypeIntersector {
    public static final TypeIntersector INSTANCE = new TypeIntersector();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
    /* compiled from: IntersectionType.kt */
    /* loaded from: classes4.dex */
    private static final class ResultNullability extends Enum<ResultNullability> {
        private static final /* synthetic */ ResultNullability[] $VALUES;
        public static final ResultNullability ACCEPT_NULL;
        public static final ResultNullability NOT_NULL;
        public static final ResultNullability START;
        public static final ResultNullability UNKNOWN;

        /* compiled from: IntersectionType.kt */
        /* loaded from: classes4.dex */
        static final class ACCEPT_NULL extends ResultNullability {
            ACCEPT_NULL(String str, int i) {
                super(str, i, null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            @NotNull
            /* renamed from: combine */
            public ResultNullability mo12141combine(@NotNull UnwrappedType nextType) {
                Intrinsics.checkParameterIsNotNull(nextType, "nextType");
                return getResultNullability(nextType);
            }
        }

        /* compiled from: IntersectionType.kt */
        /* loaded from: classes4.dex */
        static final class NOT_NULL extends ResultNullability {
            NOT_NULL(String str, int i) {
                super(str, i, null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            @NotNull
            /* renamed from: combine */
            public NOT_NULL mo12141combine(@NotNull UnwrappedType nextType) {
                Intrinsics.checkParameterIsNotNull(nextType, "nextType");
                return this;
            }
        }

        /* compiled from: IntersectionType.kt */
        /* loaded from: classes4.dex */
        static final class START extends ResultNullability {
            START(String str, int i) {
                super(str, i, null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            @NotNull
            /* renamed from: combine */
            public ResultNullability mo12141combine(@NotNull UnwrappedType nextType) {
                Intrinsics.checkParameterIsNotNull(nextType, "nextType");
                return getResultNullability(nextType);
            }
        }

        /* compiled from: IntersectionType.kt */
        /* loaded from: classes4.dex */
        static final class UNKNOWN extends ResultNullability {
            UNKNOWN(String str, int i) {
                super(str, i, null);
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.ResultNullability
            @NotNull
            /* renamed from: combine */
            public ResultNullability mo12141combine(@NotNull UnwrappedType nextType) {
                Intrinsics.checkParameterIsNotNull(nextType, "nextType");
                ResultNullability resultNullability = getResultNullability(nextType);
                return resultNullability == ResultNullability.ACCEPT_NULL ? this : resultNullability;
            }
        }

        static {
            START start = new START("START", 0);
            START = start;
            ACCEPT_NULL accept_null = new ACCEPT_NULL("ACCEPT_NULL", 1);
            ACCEPT_NULL = accept_null;
            UNKNOWN unknown = new UNKNOWN("UNKNOWN", 2);
            UNKNOWN = unknown;
            NOT_NULL not_null = new NOT_NULL("NOT_NULL", 3);
            NOT_NULL = not_null;
            $VALUES = new ResultNullability[]{start, accept_null, unknown, not_null};
        }

        private ResultNullability(String str, int i) {
        }

        public static ResultNullability valueOf(String str) {
            return (ResultNullability) Enum.valueOf(ResultNullability.class, str);
        }

        public static ResultNullability[] values() {
            return (ResultNullability[]) $VALUES.clone();
        }

        @NotNull
        /* renamed from: combine */
        public abstract ResultNullability mo12141combine(@NotNull UnwrappedType unwrappedType);

        @NotNull
        protected final ResultNullability getResultNullability(@NotNull UnwrappedType resultNullability) {
            Intrinsics.checkParameterIsNotNull(resultNullability, "$this$resultNullability");
            return resultNullability.isMarkedNullable() ? ACCEPT_NULL : NullabilityChecker.INSTANCE.isSubtypeOfAny(resultNullability) ? NOT_NULL : UNKNOWN;
        }

        public /* synthetic */ ResultNullability(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i);
        }
    }

    private TypeIntersector() {
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0050 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.Collection<kotlin.reflect.jvm.internal.impl.types.SimpleType> filterTypes(java.util.Collection<? extends kotlin.reflect.jvm.internal.impl.types.SimpleType> r8, kotlin.jvm.functions.Function2<? super kotlin.reflect.jvm.internal.impl.types.SimpleType, ? super kotlin.reflect.jvm.internal.impl.types.SimpleType, java.lang.Boolean> r9) {
        /*
            r7 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r8)
            java.util.Iterator r8 = r0.iterator()
            java.lang.String r1 = "filteredTypes.iterator()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r1)
        Le:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L57
            java.lang.Object r1 = r8.next()
            kotlin.reflect.jvm.internal.impl.types.SimpleType r1 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r1
            boolean r2 = r0.isEmpty()
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L23
            goto L51
        L23:
            java.util.Iterator r2 = r0.iterator()
        L27:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L51
            java.lang.Object r5 = r2.next()
            kotlin.reflect.jvm.internal.impl.types.SimpleType r5 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r5
            if (r5 == r1) goto L4d
            java.lang.String r6 = "lower"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            java.lang.String r6 = "upper"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r6)
            java.lang.Object r5 = r9.mo12248invoke(r5, r1)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L4d
            r5 = r3
            goto L4e
        L4d:
            r5 = r4
        L4e:
            if (r5 == 0) goto L27
            r4 = r3
        L51:
            if (r4 == 0) goto Le
            r8.remove()
            goto Le
        L57:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector.filterTypes(java.util.Collection, kotlin.jvm.functions.Function2):java.util.Collection");
    }

    private final SimpleType intersectTypesWithoutIntersectionType(Set<? extends SimpleType> set) {
        if (set.size() == 1) {
            return (SimpleType) CollectionsKt.single(set);
        }
        TypeIntersector$intersectTypesWithoutIntersectionType$errorMessage$1 typeIntersector$intersectTypesWithoutIntersectionType$errorMessage$1 = new TypeIntersector$intersectTypesWithoutIntersectionType$errorMessage$1(set);
        Collection<SimpleType> filterTypes = filterTypes(set, new TypeIntersector$intersectTypesWithoutIntersectionType$filteredEqualTypes$1(this));
        boolean z = !filterTypes.isEmpty();
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError(typeIntersector$intersectTypesWithoutIntersectionType$errorMessage$1.mo12560invoke());
        }
        SimpleType findIntersectionType = IntegerLiteralTypeConstructor.Companion.findIntersectionType(filterTypes);
        if (findIntersectionType != null) {
            return findIntersectionType;
        }
        Collection<SimpleType> filterTypes2 = filterTypes(filterTypes, new TypeIntersector$intersectTypesWithoutIntersectionType$filteredSuperAndEqualTypes$1(NewKotlinTypeChecker.Companion.getDefault()));
        boolean isEmpty = true ^ filterTypes2.isEmpty();
        if (_Assertions.ENABLED && !isEmpty) {
            throw new AssertionError(typeIntersector$intersectTypesWithoutIntersectionType$errorMessage$1.mo12560invoke());
        }
        return filterTypes2.size() < 2 ? (SimpleType) CollectionsKt.single(filterTypes2) : new IntersectionTypeConstructor(set).createType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isStrictSupertype(KotlinType kotlinType, KotlinType kotlinType2) {
        NewKotlinTypeCheckerImpl newKotlinTypeCheckerImpl = NewKotlinTypeChecker.Companion.getDefault();
        return newKotlinTypeCheckerImpl.isSubtypeOf(kotlinType, kotlinType2) && !newKotlinTypeCheckerImpl.isSubtypeOf(kotlinType2, kotlinType);
    }

    @NotNull
    public final SimpleType intersectTypes$descriptors(@NotNull List<? extends SimpleType> types) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(types, "types");
        boolean z = types.size() > 1;
        if (_Assertions.ENABLED && !z) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Size should be at least 2, but it is ");
            outline107.append(types.size());
            throw new AssertionError(outline107.toString());
        }
        ArrayList<UnwrappedType> arrayList = new ArrayList();
        for (SimpleType simpleType : types) {
            if (simpleType.mo12131getConstructor() instanceof IntersectionTypeConstructor) {
                Collection<KotlinType> mo12135getSupertypes = simpleType.mo12131getConstructor().mo12135getSupertypes();
                Intrinsics.checkExpressionValueIsNotNull(mo12135getSupertypes, "type.constructor.supertypes");
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(mo12135getSupertypes, 10);
                ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
                for (KotlinType it2 : mo12135getSupertypes) {
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    SimpleType upperIfFlexible = FlexibleTypesKt.upperIfFlexible(it2);
                    if (simpleType.isMarkedNullable()) {
                        upperIfFlexible = upperIfFlexible.mo12132makeNullableAsSpecified(true);
                    }
                    arrayList2.add(upperIfFlexible);
                }
                arrayList.addAll(arrayList2);
            } else {
                arrayList.add(simpleType);
            }
        }
        ResultNullability resultNullability = ResultNullability.START;
        for (UnwrappedType unwrappedType : arrayList) {
            resultNullability = resultNullability.mo12141combine(unwrappedType);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            SimpleType simpleType2 = (SimpleType) it3.next();
            if (resultNullability == ResultNullability.NOT_NULL) {
                simpleType2 = SpecialTypesKt.makeSimpleTypeDefinitelyNotNullOrNotNull(simpleType2);
            }
            linkedHashSet.add(simpleType2);
        }
        return intersectTypesWithoutIntersectionType(linkedHashSet);
    }
}
