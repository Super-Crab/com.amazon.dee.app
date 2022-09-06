package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: methodSignatureMapping.kt */
/* loaded from: classes3.dex */
public abstract class JvmType {

    /* compiled from: methodSignatureMapping.kt */
    /* loaded from: classes3.dex */
    public static final class Array extends JvmType {
        @NotNull
        private final JvmType elementType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Array(@NotNull JvmType elementType) {
            super(null);
            Intrinsics.checkParameterIsNotNull(elementType, "elementType");
            this.elementType = elementType;
        }

        @NotNull
        public final JvmType getElementType() {
            return this.elementType;
        }
    }

    /* compiled from: methodSignatureMapping.kt */
    /* loaded from: classes3.dex */
    public static final class Object extends JvmType {
        @NotNull
        private final String internalName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Object(@NotNull String internalName) {
            super(null);
            Intrinsics.checkParameterIsNotNull(internalName, "internalName");
            this.internalName = internalName;
        }

        @NotNull
        public final String getInternalName() {
            return this.internalName;
        }
    }

    /* compiled from: methodSignatureMapping.kt */
    /* loaded from: classes3.dex */
    public static final class Primitive extends JvmType {
        @Nullable
        private final JvmPrimitiveType jvmPrimitiveType;

        public Primitive(@Nullable JvmPrimitiveType jvmPrimitiveType) {
            super(null);
            this.jvmPrimitiveType = jvmPrimitiveType;
        }

        @Nullable
        public final JvmPrimitiveType getJvmPrimitiveType() {
            return this.jvmPrimitiveType;
        }
    }

    private JvmType() {
    }

    @NotNull
    public String toString() {
        return JvmTypeFactoryImpl.INSTANCE.toString(this);
    }

    public /* synthetic */ JvmType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
