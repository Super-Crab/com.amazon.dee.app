package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import org.jetbrains.annotations.NotNull;
/* compiled from: ReflectJavaArrayType.kt */
/* loaded from: classes2.dex */
public final class ReflectJavaArrayType extends ReflectJavaType implements JavaArrayType {
    @NotNull
    private final ReflectJavaType componentType;
    @NotNull
    private final Type reflectType;

    public ReflectJavaArrayType(@NotNull Type reflectType) {
        ReflectJavaType create;
        Intrinsics.checkParameterIsNotNull(reflectType, "reflectType");
        this.reflectType = reflectType;
        Type mo11642getReflectType = mo11642getReflectType();
        if (!(mo11642getReflectType instanceof GenericArrayType)) {
            if (mo11642getReflectType instanceof Class) {
                Class cls = (Class) mo11642getReflectType;
                if (cls.isArray()) {
                    ReflectJavaType.Factory factory = ReflectJavaType.Factory;
                    Class<?> componentType = cls.getComponentType();
                    Intrinsics.checkExpressionValueIsNotNull(componentType, "getComponentType()");
                    create = factory.create(componentType);
                }
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Not an array type (");
            outline107.append(mo11642getReflectType().getClass());
            outline107.append("): ");
            outline107.append(mo11642getReflectType());
            throw new IllegalArgumentException(outline107.toString());
        }
        ReflectJavaType.Factory factory2 = ReflectJavaType.Factory;
        Type genericComponentType = ((GenericArrayType) mo11642getReflectType).getGenericComponentType();
        Intrinsics.checkExpressionValueIsNotNull(genericComponentType, "genericComponentType");
        create = factory2.create(genericComponentType);
        this.componentType = create;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType
    @NotNull
    /* renamed from: getReflectType */
    protected Type mo11642getReflectType() {
        return this.reflectType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType
    @NotNull
    /* renamed from: getComponentType */
    public ReflectJavaType mo11616getComponentType() {
        return this.componentType;
    }
}
