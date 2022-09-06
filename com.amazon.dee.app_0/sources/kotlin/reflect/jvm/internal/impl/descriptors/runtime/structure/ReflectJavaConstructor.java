package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaConstructor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter;
import org.jetbrains.annotations.NotNull;
/* compiled from: ReflectJavaConstructor.kt */
/* loaded from: classes2.dex */
public final class ReflectJavaConstructor extends ReflectJavaMember implements JavaConstructor {
    @NotNull
    private final Constructor<?> member;

    public ReflectJavaConstructor(@NotNull Constructor<?> member) {
        Intrinsics.checkParameterIsNotNull(member, "member");
        this.member = member;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaMember
    @NotNull
    /* renamed from: getMember */
    public Constructor<?> mo11631getMember() {
        return this.member;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner
    @NotNull
    public List<ReflectJavaTypeParameter> getTypeParameters() {
        TypeVariable<Constructor<?>>[] typeParameters = mo11631getMember().getTypeParameters();
        Intrinsics.checkExpressionValueIsNotNull(typeParameters, "member.typeParameters");
        ArrayList arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable<Constructor<?>> typeVariable : typeParameters) {
            arrayList.add(new ReflectJavaTypeParameter(typeVariable));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaConstructor
    @NotNull
    public List<JavaValueParameter> getValueParameters() {
        List<JavaValueParameter> emptyList;
        Type[] realTypes = mo11631getMember().getGenericParameterTypes();
        Intrinsics.checkExpressionValueIsNotNull(realTypes, "types");
        if (realTypes.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        Class<?> klass = mo11631getMember().getDeclaringClass();
        Intrinsics.checkExpressionValueIsNotNull(klass, "klass");
        if (klass.getDeclaringClass() != null && !Modifier.isStatic(klass.getModifiers())) {
            realTypes = (Type[]) ArraysKt.copyOfRange(realTypes, 1, realTypes.length);
        }
        Annotation[][] realAnnotations = mo11631getMember().getParameterAnnotations();
        if (realAnnotations.length >= realTypes.length) {
            if (realAnnotations.length > realTypes.length) {
                Intrinsics.checkExpressionValueIsNotNull(realAnnotations, "annotations");
                realAnnotations = (Annotation[][]) ArraysKt.copyOfRange(realAnnotations, realAnnotations.length - realTypes.length, realAnnotations.length);
            }
            Intrinsics.checkExpressionValueIsNotNull(realTypes, "realTypes");
            Intrinsics.checkExpressionValueIsNotNull(realAnnotations, "realAnnotations");
            return getValueParameters(realTypes, realAnnotations, mo11631getMember().isVarArgs());
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Illegal generic signature: ");
        outline107.append(mo11631getMember());
        throw new IllegalStateException(outline107.toString());
    }
}
