package kotlin.reflect.jvm.internal.impl.util;

import java.util.Arrays;
import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.util.CheckResult;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: modifierChecks.kt */
/* loaded from: classes4.dex */
public final class Checks {
    @NotNull
    private final Function1<FunctionDescriptor, String> additionalCheck;
    @NotNull
    private final Check[] checks;
    @Nullable
    private final Name name;
    @Nullable
    private final Collection<Name> nameList;
    @Nullable
    private final Regex regex;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: modifierChecks.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.util.Checks$2  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass2 extends Lambda implements Function1 {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        @Nullable
        /* renamed from: invoke */
        public final Void mo12165invoke(@NotNull FunctionDescriptor receiver) {
            Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: modifierChecks.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.util.Checks$3  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass3 extends Lambda implements Function1 {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        AnonymousClass3() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        @Nullable
        /* renamed from: invoke */
        public final Void mo12165invoke(@NotNull FunctionDescriptor receiver) {
            Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: modifierChecks.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.util.Checks$4  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass4 extends Lambda implements Function1 {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        AnonymousClass4() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        @Nullable
        /* renamed from: invoke */
        public final Void mo12165invoke(@NotNull FunctionDescriptor receiver) {
            Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Checks(Name name, Regex regex, Collection<Name> collection, Function1<? super FunctionDescriptor, String> function1, Check... checkArr) {
        this.name = name;
        this.regex = regex;
        this.nameList = collection;
        this.additionalCheck = function1;
        this.checks = checkArr;
    }

    @NotNull
    public final CheckResult checkAll(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        for (Check check : this.checks) {
            String invoke = check.invoke(functionDescriptor);
            if (invoke != null) {
                return new CheckResult.IllegalSignature(invoke);
            }
        }
        String mo12165invoke = this.additionalCheck.mo12165invoke(functionDescriptor);
        if (mo12165invoke != null) {
            return new CheckResult.IllegalSignature(mo12165invoke);
        }
        return CheckResult.SuccessCheck.INSTANCE;
    }

    public final boolean isApplicable(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        if (this.name == null || !(!Intrinsics.areEqual(functionDescriptor.getName(), this.name))) {
            if (this.regex != null) {
                String asString = functionDescriptor.getName().asString();
                Intrinsics.checkExpressionValueIsNotNull(asString, "functionDescriptor.name.asString()");
                if (!this.regex.matches(asString)) {
                    return false;
                }
            }
            Collection<Name> collection = this.nameList;
            return collection == null || collection.contains(functionDescriptor.getName());
        }
        return false;
    }

    public /* synthetic */ Checks(Name name, Check[] checkArr, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(name, checkArr, (i & 4) != 0 ? AnonymousClass2.INSTANCE : function1);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Checks(@NotNull Name name, @NotNull Check[] checks, @NotNull Function1<? super FunctionDescriptor, String> additionalChecks) {
        this(name, (Regex) null, (Collection<Name>) null, additionalChecks, (Check[]) Arrays.copyOf(checks, checks.length));
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(checks, "checks");
        Intrinsics.checkParameterIsNotNull(additionalChecks, "additionalChecks");
    }

    public /* synthetic */ Checks(Regex regex, Check[] checkArr, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(regex, checkArr, (i & 4) != 0 ? AnonymousClass3.INSTANCE : function1);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Checks(@NotNull Regex regex, @NotNull Check[] checks, @NotNull Function1<? super FunctionDescriptor, String> additionalChecks) {
        this((Name) null, regex, (Collection<Name>) null, additionalChecks, (Check[]) Arrays.copyOf(checks, checks.length));
        Intrinsics.checkParameterIsNotNull(regex, "regex");
        Intrinsics.checkParameterIsNotNull(checks, "checks");
        Intrinsics.checkParameterIsNotNull(additionalChecks, "additionalChecks");
    }

    public /* synthetic */ Checks(Collection collection, Check[] checkArr, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(collection, checkArr, (i & 4) != 0 ? AnonymousClass4.INSTANCE : function1);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Checks(@NotNull Collection<Name> nameList, @NotNull Check[] checks, @NotNull Function1<? super FunctionDescriptor, String> additionalChecks) {
        this((Name) null, (Regex) null, nameList, additionalChecks, (Check[]) Arrays.copyOf(checks, checks.length));
        Intrinsics.checkParameterIsNotNull(nameList, "nameList");
        Intrinsics.checkParameterIsNotNull(checks, "checks");
        Intrinsics.checkParameterIsNotNull(additionalChecks, "additionalChecks");
    }
}
