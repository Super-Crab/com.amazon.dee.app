package kotlin.reflect.jvm.internal.impl.resolve.constants;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: IntegerLiteralTypeConstructor.kt */
/* loaded from: classes4.dex */
public final class IntegerLiteralTypeConstructor implements TypeConstructor {
    public static final Companion Companion = new Companion(null);
    private final ModuleDescriptor module;
    @NotNull
    private final Set<KotlinType> possibleTypes;
    private final Lazy supertypes$delegate;
    private final SimpleType type;
    private final long value;

    /* compiled from: IntegerLiteralTypeConstructor.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: IntegerLiteralTypeConstructor.kt */
        /* loaded from: classes4.dex */
        public enum Mode {
            COMMON_SUPER_TYPE,
            INTERSECTION_TYPE
        }

        /* loaded from: classes4.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Mode.values().length];

            static {
                $EnumSwitchMapping$0[Mode.COMMON_SUPER_TYPE.ordinal()] = 1;
                $EnumSwitchMapping$0[Mode.INTERSECTION_TYPE.ordinal()] = 2;
            }
        }

        private Companion() {
        }

        private final SimpleType findCommonSuperTypeOrIntersectionType(Collection<? extends SimpleType> collection, Mode mode) {
            if (collection.isEmpty()) {
                return null;
            }
            Iterator<T> it2 = collection.iterator();
            if (it2.hasNext()) {
                Object next = it2.next();
                while (it2.hasNext()) {
                    Companion companion = IntegerLiteralTypeConstructor.Companion;
                    next = companion.fold((SimpleType) next, (SimpleType) it2.next(), mode);
                }
                return (SimpleType) next;
            }
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }

        private final SimpleType fold(SimpleType simpleType, SimpleType simpleType2, Mode mode) {
            if (simpleType == null || simpleType2 == null) {
                return null;
            }
            TypeConstructor mo12131getConstructor = simpleType.mo12131getConstructor();
            TypeConstructor mo12131getConstructor2 = simpleType2.mo12131getConstructor();
            boolean z = mo12131getConstructor instanceof IntegerLiteralTypeConstructor;
            if (z && (mo12131getConstructor2 instanceof IntegerLiteralTypeConstructor)) {
                return fold((IntegerLiteralTypeConstructor) mo12131getConstructor, (IntegerLiteralTypeConstructor) mo12131getConstructor2, mode);
            }
            if (z) {
                return fold((IntegerLiteralTypeConstructor) mo12131getConstructor, simpleType2);
            }
            if (!(mo12131getConstructor2 instanceof IntegerLiteralTypeConstructor)) {
                return null;
            }
            return fold((IntegerLiteralTypeConstructor) mo12131getConstructor2, simpleType);
        }

        @Nullable
        public final SimpleType findIntersectionType(@NotNull Collection<? extends SimpleType> types) {
            Intrinsics.checkParameterIsNotNull(types, "types");
            return findCommonSuperTypeOrIntersectionType(types, Mode.INTERSECTION_TYPE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final SimpleType fold(IntegerLiteralTypeConstructor integerLiteralTypeConstructor, IntegerLiteralTypeConstructor integerLiteralTypeConstructor2, Mode mode) {
            Set intersect;
            int i = WhenMappings.$EnumSwitchMapping$0[mode.ordinal()];
            if (i == 1) {
                intersect = CollectionsKt___CollectionsKt.intersect(integerLiteralTypeConstructor.getPossibleTypes(), integerLiteralTypeConstructor2.getPossibleTypes());
            } else if (i != 2) {
                throw new NoWhenBranchMatchedException();
            } else {
                intersect = CollectionsKt___CollectionsKt.union(integerLiteralTypeConstructor.getPossibleTypes(), integerLiteralTypeConstructor2.getPossibleTypes());
            }
            return KotlinTypeFactory.integerLiteralType(Annotations.Companion.getEMPTY(), new IntegerLiteralTypeConstructor(integerLiteralTypeConstructor.value, integerLiteralTypeConstructor.module, intersect, null), false);
        }

        private final SimpleType fold(IntegerLiteralTypeConstructor integerLiteralTypeConstructor, SimpleType simpleType) {
            if (integerLiteralTypeConstructor.getPossibleTypes().contains(simpleType)) {
                return simpleType;
            }
            return null;
        }
    }

    public /* synthetic */ IntegerLiteralTypeConstructor(long j, ModuleDescriptor moduleDescriptor, Set set, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, moduleDescriptor, set);
    }

    private final List<KotlinType> getSupertypes() {
        return (List) this.supertypes$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isContainsOnlyUnsignedTypes() {
        Collection<KotlinType> allSignedLiteralTypes = PrimitiveTypeUtilKt.getAllSignedLiteralTypes(this.module);
        if (!(allSignedLiteralTypes instanceof Collection) || !allSignedLiteralTypes.isEmpty()) {
            for (KotlinType kotlinType : allSignedLiteralTypes) {
                if (!(!this.possibleTypes.contains(kotlinType))) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    private final String valueToString() {
        String joinToString$default;
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104(JsonReaderKt.BEGIN_LIST);
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(this.possibleTypes, ",", null, null, 0, null, IntegerLiteralTypeConstructor$valueToString$1.INSTANCE, 30, null);
        return GeneratedOutlineSupport1.outline89(outline104, joinToString$default, JsonReaderKt.END_LIST);
    }

    public final boolean checkConstructor(@NotNull TypeConstructor constructor) {
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        Set<KotlinType> set = this.possibleTypes;
        if (!(set instanceof Collection) || !set.isEmpty()) {
            for (KotlinType kotlinType : set) {
                if (Intrinsics.areEqual(kotlinType.mo12131getConstructor(), constructor)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public KotlinBuiltIns getBuiltIns() {
        return this.module.getBuiltIns();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @Nullable
    /* renamed from: getDeclarationDescriptor */
    public ClassifierDescriptor mo12085getDeclarationDescriptor() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public List<TypeParameterDescriptor> getParameters() {
        List<TypeParameterDescriptor> emptyList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @NotNull
    public final Set<KotlinType> getPossibleTypes() {
        return this.possibleTypes;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: getSupertypes  reason: collision with other method in class */
    public Collection<KotlinType> mo12135getSupertypes() {
        return getSupertypes();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public boolean isDenotable() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: refine */
    public TypeConstructor mo12136refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        return this;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IntegerLiteralType");
        outline107.append(valueToString());
        return outline107.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private IntegerLiteralTypeConstructor(long j, ModuleDescriptor moduleDescriptor, Set<? extends KotlinType> set) {
        Lazy lazy;
        this.type = KotlinTypeFactory.integerLiteralType(Annotations.Companion.getEMPTY(), this, false);
        lazy = LazyKt__LazyJVMKt.lazy(new IntegerLiteralTypeConstructor$supertypes$2(this));
        this.supertypes$delegate = lazy;
        this.value = j;
        this.module = moduleDescriptor;
        this.possibleTypes = set;
    }
}
