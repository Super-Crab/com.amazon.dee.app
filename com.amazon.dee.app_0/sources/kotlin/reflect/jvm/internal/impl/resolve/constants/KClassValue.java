package kotlin.reflect.jvm.internal.impl.resolve.constants;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: constantValues.kt */
/* loaded from: classes4.dex */
public final class KClassValue extends ConstantValue<Value> {
    public static final Companion Companion = new Companion(null);

    /* compiled from: constantValues.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final ConstantValue<?> create(@NotNull KotlinType argumentType) {
            Intrinsics.checkParameterIsNotNull(argumentType, "argumentType");
            if (KotlinTypeKt.isError(argumentType)) {
                return null;
            }
            KotlinType kotlinType = argumentType;
            int i = 0;
            while (KotlinBuiltIns.isArray(kotlinType)) {
                kotlinType = ((TypeProjection) CollectionsKt.single((List<? extends Object>) kotlinType.getArguments())).getType();
                Intrinsics.checkExpressionValueIsNotNull(kotlinType, "type.arguments.single().type");
                i++;
            }
            ClassifierDescriptor mo12085getDeclarationDescriptor = kotlinType.mo12131getConstructor().mo12085getDeclarationDescriptor();
            if (mo12085getDeclarationDescriptor instanceof ClassDescriptor) {
                ClassId classId = DescriptorUtilsKt.getClassId(mo12085getDeclarationDescriptor);
                if (classId != null) {
                    return new KClassValue(classId, i);
                }
                return new KClassValue(new Value.LocalClass(argumentType));
            } else if (!(mo12085getDeclarationDescriptor instanceof TypeParameterDescriptor)) {
                return null;
            } else {
                ClassId classId2 = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.any.toSafe());
                Intrinsics.checkExpressionValueIsNotNull(classId2, "ClassId.topLevel(KotlinB…ns.FQ_NAMES.any.toSafe())");
                return new KClassValue(classId2, 0);
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: constantValues.kt */
    /* loaded from: classes4.dex */
    public static abstract class Value {

        /* compiled from: constantValues.kt */
        /* loaded from: classes4.dex */
        public static final class LocalClass extends Value {
            @NotNull
            private final KotlinType type;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public LocalClass(@NotNull KotlinType type) {
                super(null);
                Intrinsics.checkParameterIsNotNull(type, "type");
                this.type = type;
            }

            public boolean equals(@Nullable Object obj) {
                if (this != obj) {
                    return (obj instanceof LocalClass) && Intrinsics.areEqual(this.type, ((LocalClass) obj).type);
                }
                return true;
            }

            @NotNull
            public final KotlinType getType() {
                return this.type;
            }

            public int hashCode() {
                KotlinType kotlinType = this.type;
                if (kotlinType != null) {
                    return kotlinType.hashCode();
                }
                return 0;
            }

            @NotNull
            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LocalClass(type=");
                outline107.append(this.type);
                outline107.append(")");
                return outline107.toString();
            }
        }

        /* compiled from: constantValues.kt */
        /* loaded from: classes4.dex */
        public static final class NormalClass extends Value {
            @NotNull
            private final ClassLiteralValue value;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NormalClass(@NotNull ClassLiteralValue value) {
                super(null);
                Intrinsics.checkParameterIsNotNull(value, "value");
                this.value = value;
            }

            public boolean equals(@Nullable Object obj) {
                if (this != obj) {
                    return (obj instanceof NormalClass) && Intrinsics.areEqual(this.value, ((NormalClass) obj).value);
                }
                return true;
            }

            public final int getArrayDimensions() {
                return this.value.getArrayNestedness();
            }

            @NotNull
            public final ClassId getClassId() {
                return this.value.getClassId();
            }

            @NotNull
            public final ClassLiteralValue getValue() {
                return this.value;
            }

            public int hashCode() {
                ClassLiteralValue classLiteralValue = this.value;
                if (classLiteralValue != null) {
                    return classLiteralValue.hashCode();
                }
                return 0;
            }

            @NotNull
            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NormalClass(value=");
                outline107.append(this.value);
                outline107.append(")");
                return outline107.toString();
            }
        }

        private Value() {
        }

        public /* synthetic */ Value(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KClassValue(@NotNull Value value) {
        super(value);
        Intrinsics.checkParameterIsNotNull(value, "value");
    }

    @NotNull
    public final KotlinType getArgumentType(@NotNull ModuleDescriptor module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        Value value = getValue();
        if (value instanceof Value.LocalClass) {
            return ((Value.LocalClass) getValue()).getType();
        }
        if (value instanceof Value.NormalClass) {
            ClassLiteralValue value2 = ((Value.NormalClass) getValue()).getValue();
            ClassId component1 = value2.component1();
            int component2 = value2.component2();
            ClassDescriptor findClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(module, component1);
            if (findClassAcrossModuleDependencies != null) {
                SimpleType defaultType = findClassAcrossModuleDependencies.getDefaultType();
                Intrinsics.checkExpressionValueIsNotNull(defaultType, "descriptor.defaultType");
                KotlinType replaceArgumentsWithStarProjections = TypeUtilsKt.replaceArgumentsWithStarProjections(defaultType);
                for (int i = 0; i < component2; i++) {
                    replaceArgumentsWithStarProjections = module.getBuiltIns().getArrayType(Variance.INVARIANT, replaceArgumentsWithStarProjections);
                    Intrinsics.checkExpressionValueIsNotNull(replaceArgumentsWithStarProjections, "module.builtIns.getArray…Variance.INVARIANT, type)");
                }
                return replaceArgumentsWithStarProjections;
            }
            SimpleType createErrorType = ErrorUtils.createErrorType("Unresolved type: " + component1 + " (arrayDimensions=" + component2 + ')');
            Intrinsics.checkExpressionValueIsNotNull(createErrorType, "ErrorUtils.createErrorTy…sions=$arrayDimensions)\")");
            return createErrorType;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    @NotNull
    /* renamed from: getType */
    public KotlinType mo12026getType(@NotNull ModuleDescriptor module) {
        List listOf;
        Intrinsics.checkParameterIsNotNull(module, "module");
        Annotations empty = Annotations.Companion.getEMPTY();
        ClassDescriptor kClass = module.getBuiltIns().getKClass();
        Intrinsics.checkExpressionValueIsNotNull(kClass, "module.builtIns.kClass");
        listOf = CollectionsKt__CollectionsJVMKt.listOf(new TypeProjectionImpl(getArgumentType(module)));
        return KotlinTypeFactory.simpleNotNullType(empty, kClass, listOf);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KClassValue(@NotNull ClassLiteralValue value) {
        this(new Value.NormalClass(value));
        Intrinsics.checkParameterIsNotNull(value, "value");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KClassValue(@NotNull ClassId classId, int i) {
        this(new ClassLiteralValue(classId, i));
        Intrinsics.checkParameterIsNotNull(classId, "classId");
    }
}
