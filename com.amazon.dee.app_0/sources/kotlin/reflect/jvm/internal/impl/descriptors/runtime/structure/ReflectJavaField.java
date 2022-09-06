package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import org.jetbrains.annotations.NotNull;
/* compiled from: ReflectJavaField.kt */
/* loaded from: classes2.dex */
public final class ReflectJavaField extends ReflectJavaMember implements JavaField {
    @NotNull
    private final Field member;

    public ReflectJavaField(@NotNull Field member) {
        Intrinsics.checkParameterIsNotNull(member, "member");
        this.member = member;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField
    public boolean getHasConstantNotNullInitializer() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaMember
    @NotNull
    /* renamed from: getMember */
    public Field mo11631getMember() {
        return this.member;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField
    public boolean isEnumEntry() {
        return mo11631getMember().isEnumConstant();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField
    @NotNull
    /* renamed from: getType */
    public ReflectJavaType mo11627getType() {
        ReflectJavaType.Factory factory = ReflectJavaType.Factory;
        Type genericType = mo11631getMember().getGenericType();
        Intrinsics.checkExpressionValueIsNotNull(genericType, "member.genericType");
        return factory.create(genericType);
    }
}
