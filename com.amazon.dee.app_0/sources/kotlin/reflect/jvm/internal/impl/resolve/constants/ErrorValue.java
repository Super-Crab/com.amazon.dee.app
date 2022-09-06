package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
/* compiled from: constantValues.kt */
/* loaded from: classes4.dex */
public abstract class ErrorValue extends ConstantValue<Unit> {
    public static final Companion Companion = new Companion(null);

    /* compiled from: constantValues.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final ErrorValue create(@NotNull String message) {
            Intrinsics.checkParameterIsNotNull(message, "message");
            return new ErrorValueWithMessage(message);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: constantValues.kt */
    /* loaded from: classes4.dex */
    public static final class ErrorValueWithMessage extends ErrorValue {
        @NotNull
        private final String message;

        public ErrorValueWithMessage(@NotNull String message) {
            Intrinsics.checkParameterIsNotNull(message, "message");
            this.message = message;
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
        @NotNull
        public String toString() {
            return this.message;
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
        @NotNull
        /* renamed from: getType  reason: collision with other method in class */
        public SimpleType mo12026getType(@NotNull ModuleDescriptor module) {
            Intrinsics.checkParameterIsNotNull(module, "module");
            SimpleType createErrorType = ErrorUtils.createErrorType(this.message);
            Intrinsics.checkExpressionValueIsNotNull(createErrorType, "ErrorUtils.createErrorType(message)");
            return createErrorType;
        }
    }

    public ErrorValue() {
        super(Unit.INSTANCE);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    @NotNull
    public Unit getValue() {
        throw new UnsupportedOperationException();
    }
}
