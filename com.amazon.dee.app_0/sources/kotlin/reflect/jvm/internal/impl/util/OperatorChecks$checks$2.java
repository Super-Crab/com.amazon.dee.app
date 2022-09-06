package kotlin.reflect.jvm.internal.impl.util;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: modifierChecks.kt */
/* loaded from: classes4.dex */
final class OperatorChecks$checks$2 extends Lambda implements Function1<FunctionDescriptor, String> {
    public static final OperatorChecks$checks$2 INSTANCE = new OperatorChecks$checks$2();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: modifierChecks.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$2$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<DeclarationDescriptor, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Boolean mo12165invoke(DeclarationDescriptor declarationDescriptor) {
            return Boolean.valueOf(invoke2(declarationDescriptor));
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final boolean invoke2(@NotNull DeclarationDescriptor isAny) {
            Intrinsics.checkParameterIsNotNull(isAny, "$this$isAny");
            return (isAny instanceof ClassDescriptor) && KotlinBuiltIns.isAny((ClassDescriptor) isAny);
        }
    }

    OperatorChecks$checks$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final String mo12165invoke(@NotNull FunctionDescriptor receiver) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        AnonymousClass1 anonymousClass1 = AnonymousClass1.INSTANCE;
        OperatorChecks operatorChecks = OperatorChecks.INSTANCE;
        DeclarationDescriptor containingDeclaration = receiver.mo11607getContainingDeclaration();
        Intrinsics.checkExpressionValueIsNotNull(containingDeclaration, "containingDeclaration");
        boolean invoke2 = anonymousClass1.invoke2(containingDeclaration);
        boolean z2 = true;
        if (!invoke2) {
            Collection<? extends FunctionDescriptor> overriddenDescriptors = receiver.getOverriddenDescriptors();
            Intrinsics.checkExpressionValueIsNotNull(overriddenDescriptors, "overriddenDescriptors");
            if (!overriddenDescriptors.isEmpty()) {
                for (FunctionDescriptor it2 : overriddenDescriptors) {
                    AnonymousClass1 anonymousClass12 = AnonymousClass1.INSTANCE;
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    DeclarationDescriptor mo11607getContainingDeclaration = it2.mo11607getContainingDeclaration();
                    Intrinsics.checkExpressionValueIsNotNull(mo11607getContainingDeclaration, "it.containingDeclaration");
                    if (anonymousClass12.invoke2(mo11607getContainingDeclaration)) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
            if (!z) {
                z2 = false;
            }
        }
        if (!z2) {
            return "must override ''equals()'' in Any";
        }
        return null;
    }
}
