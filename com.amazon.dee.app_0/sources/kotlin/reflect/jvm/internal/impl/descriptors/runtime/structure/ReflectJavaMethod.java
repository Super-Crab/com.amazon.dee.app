package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectJavaMethod.kt */
/* loaded from: classes2.dex */
public final class ReflectJavaMethod extends ReflectJavaMember implements JavaMethod {
    @NotNull
    private final Method member;

    public ReflectJavaMethod(@NotNull Method member) {
        Intrinsics.checkParameterIsNotNull(member, "member");
        this.member = member;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod
    @Nullable
    public JavaAnnotationArgument getAnnotationParameterDefaultValue() {
        Object defaultValue = mo11631getMember().getDefaultValue();
        if (defaultValue != null) {
            return ReflectJavaAnnotationArgument.Factory.create(defaultValue, null);
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod
    public boolean getHasAnnotationParameterDefaultValue() {
        return JavaMethod.DefaultImpls.getHasAnnotationParameterDefaultValue(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner
    @NotNull
    public List<ReflectJavaTypeParameter> getTypeParameters() {
        TypeVariable<Method>[] typeParameters = mo11631getMember().getTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters, "member.typeParameters");
        ArrayList arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable<Method> typeVariable : typeParameters) {
            arrayList.add(new ReflectJavaTypeParameter(typeVariable));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod
    @NotNull
    public List<JavaValueParameter> getValueParameters() {
        Type[] genericParameterTypes = mo11631getMember().getGenericParameterTypes();
        Intrinsics.checkExpressionValueIsNotNull(genericParameterTypes, "member.genericParameterTypes");
        Annotation[][] parameterAnnotations = mo11631getMember().getParameterAnnotations();
        Intrinsics.checkExpressionValueIsNotNull(parameterAnnotations, "member.parameterAnnotations");
        return getValueParameters(genericParameterTypes, parameterAnnotations, mo11631getMember().isVarArgs());
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaMember
    @NotNull
    /* renamed from: getMember  reason: collision with other method in class */
    public Method mo11631getMember() {
        return this.member;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod
    @NotNull
    /* renamed from: getReturnType */
    public ReflectJavaType mo11632getReturnType() {
        ReflectJavaType.Factory factory = ReflectJavaType.Factory;
        Type genericReturnType = mo11631getMember().getGenericReturnType();
        Intrinsics.checkExpressionValueIsNotNull(genericReturnType, "member.genericReturnType");
        return factory.create(genericReturnType);
    }
}
