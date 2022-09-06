package kotlin.reflect.jvm.internal.impl.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.util.Check;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: modifierChecks.kt */
/* loaded from: classes4.dex */
public abstract class ReturnsCheck implements Check {
    @NotNull
    private final String description;
    @NotNull
    private final String name;
    @NotNull
    private final Function1<KotlinBuiltIns, KotlinType> type;

    /* compiled from: modifierChecks.kt */
    /* loaded from: classes4.dex */
    public static final class ReturnsBoolean extends ReturnsCheck {
        public static final ReturnsBoolean INSTANCE = new ReturnsBoolean();

        /* compiled from: modifierChecks.kt */
        /* renamed from: kotlin.reflect.jvm.internal.impl.util.ReturnsCheck$ReturnsBoolean$1  reason: invalid class name */
        /* loaded from: classes4.dex */
        static final class AnonymousClass1 extends Lambda implements Function1<KotlinBuiltIns, SimpleType> {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

            AnonymousClass1() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final SimpleType mo12165invoke(@NotNull KotlinBuiltIns receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                SimpleType booleanType = receiver.getBooleanType();
                Intrinsics.checkExpressionValueIsNotNull(booleanType, "booleanType");
                return booleanType;
            }
        }

        private ReturnsBoolean() {
            super("Boolean", AnonymousClass1.INSTANCE, null);
        }
    }

    /* compiled from: modifierChecks.kt */
    /* loaded from: classes4.dex */
    public static final class ReturnsInt extends ReturnsCheck {
        public static final ReturnsInt INSTANCE = new ReturnsInt();

        /* compiled from: modifierChecks.kt */
        /* renamed from: kotlin.reflect.jvm.internal.impl.util.ReturnsCheck$ReturnsInt$1  reason: invalid class name */
        /* loaded from: classes4.dex */
        static final class AnonymousClass1 extends Lambda implements Function1<KotlinBuiltIns, SimpleType> {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

            AnonymousClass1() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final SimpleType mo12165invoke(@NotNull KotlinBuiltIns receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                SimpleType intType = receiver.getIntType();
                Intrinsics.checkExpressionValueIsNotNull(intType, "intType");
                return intType;
            }
        }

        private ReturnsInt() {
            super("Int", AnonymousClass1.INSTANCE, null);
        }
    }

    /* compiled from: modifierChecks.kt */
    /* loaded from: classes4.dex */
    public static final class ReturnsUnit extends ReturnsCheck {
        public static final ReturnsUnit INSTANCE = new ReturnsUnit();

        /* compiled from: modifierChecks.kt */
        /* renamed from: kotlin.reflect.jvm.internal.impl.util.ReturnsCheck$ReturnsUnit$1  reason: invalid class name */
        /* loaded from: classes4.dex */
        static final class AnonymousClass1 extends Lambda implements Function1<KotlinBuiltIns, SimpleType> {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

            AnonymousClass1() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final SimpleType mo12165invoke(@NotNull KotlinBuiltIns receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                SimpleType unitType = receiver.getUnitType();
                Intrinsics.checkExpressionValueIsNotNull(unitType, "unitType");
                return unitType;
            }
        }

        private ReturnsUnit() {
            super("Unit", AnonymousClass1.INSTANCE, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ReturnsCheck(String str, Function1<? super KotlinBuiltIns, ? extends KotlinType> function1) {
        this.name = str;
        this.type = function1;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("must return ");
        outline107.append(this.name);
        this.description = outline107.toString();
    }

    @Override // kotlin.reflect.jvm.internal.impl.util.Check
    public boolean check(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        return Intrinsics.areEqual(functionDescriptor.getReturnType(), this.type.mo12165invoke(DescriptorUtilsKt.getBuiltIns(functionDescriptor)));
    }

    @Override // kotlin.reflect.jvm.internal.impl.util.Check
    @NotNull
    public String getDescription() {
        return this.description;
    }

    @Override // kotlin.reflect.jvm.internal.impl.util.Check
    @Nullable
    public String invoke(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        return Check.DefaultImpls.invoke(this, functionDescriptor);
    }

    public /* synthetic */ ReturnsCheck(String str, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, function1);
    }
}
