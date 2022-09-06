package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectJavaType.kt */
/* loaded from: classes2.dex */
public abstract class ReflectJavaType implements JavaType {
    public static final Factory Factory = new Factory(null);

    /* compiled from: ReflectJavaType.kt */
    /* loaded from: classes2.dex */
    public static final class Factory {
        private Factory() {
        }

        @NotNull
        public final ReflectJavaType create(@NotNull Type type) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            boolean z = type instanceof Class;
            if (z) {
                Class cls = (Class) type;
                if (cls.isPrimitive()) {
                    return new ReflectJavaPrimitiveType(cls);
                }
            }
            if ((type instanceof GenericArrayType) || (z && ((Class) type).isArray())) {
                return new ReflectJavaArrayType(type);
            }
            return type instanceof WildcardType ? new ReflectJavaWildcardType((WildcardType) type) : new ReflectJavaClassifierType(type);
        }

        public /* synthetic */ Factory(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof ReflectJavaType) && Intrinsics.areEqual(mo11642getReflectType(), ((ReflectJavaType) obj).mo11642getReflectType());
    }

    @NotNull
    /* renamed from: getReflectType */
    protected abstract Type mo11642getReflectType();

    public int hashCode() {
        return mo11642getReflectType().hashCode();
    }

    @NotNull
    public String toString() {
        return getClass().getName() + RealTimeTextConstants.COLON_SPACE + mo11642getReflectType();
    }
}
