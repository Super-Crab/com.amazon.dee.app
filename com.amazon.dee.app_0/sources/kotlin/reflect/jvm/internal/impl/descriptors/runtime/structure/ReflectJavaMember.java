package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaModifierListOwner;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectJavaMember.kt */
/* loaded from: classes2.dex */
public abstract class ReflectJavaMember extends ReflectJavaElement implements ReflectJavaAnnotationOwner, ReflectJavaModifierListOwner, JavaMember {
    public boolean equals(@Nullable Object obj) {
        return (obj instanceof ReflectJavaMember) && Intrinsics.areEqual(mo11631getMember(), ((ReflectJavaMember) obj).mo11631getMember());
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    @Nullable
    /* renamed from: findAnnotation */
    public ReflectJavaAnnotation mo11638findAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return ReflectJavaAnnotationOwner.DefaultImpls.findAnnotation(this, fqName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotationOwner
    @NotNull
    /* renamed from: getElement */
    public AnnotatedElement mo11620getElement() {
        Member mo11631getMember = mo11631getMember();
        if (mo11631getMember != null) {
            return (AnnotatedElement) mo11631getMember;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.reflect.AnnotatedElement");
    }

    @NotNull
    /* renamed from: getMember */
    public abstract Member mo11631getMember();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaModifierListOwner
    public int getModifiers() {
        return mo11631getMember().getModifiers();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaNamedElement
    @NotNull
    public Name getName() {
        Name identifier;
        String name = mo11631getMember().getName();
        if (name == null || (identifier = Name.identifier(name)) == null) {
            Name name2 = SpecialNames.NO_NAME_PROVIDED;
            Intrinsics.checkExpressionValueIsNotNull(name2, "SpecialNames.NO_NAME_PROVIDED");
            return name2;
        }
        return identifier;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final List<JavaValueParameter> getValueParameters(@NotNull Type[] parameterTypes, @NotNull Annotation[][] parameterAnnotations, boolean z) {
        String str;
        boolean z2;
        int lastIndex;
        Intrinsics.checkParameterIsNotNull(parameterTypes, "parameterTypes");
        Intrinsics.checkParameterIsNotNull(parameterAnnotations, "parameterAnnotations");
        ArrayList arrayList = new ArrayList(parameterTypes.length);
        List<String> loadParameterNames = Java8ParameterNamesLoader.INSTANCE.loadParameterNames(mo11631getMember());
        int size = loadParameterNames != null ? loadParameterNames.size() - parameterTypes.length : 0;
        int length = parameterTypes.length;
        for (int i = 0; i < length; i++) {
            ReflectJavaType create = ReflectJavaType.Factory.create(parameterTypes[i]);
            if (loadParameterNames != null) {
                str = (String) CollectionsKt.getOrNull(loadParameterNames, i + size);
                if (str == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("No parameter with index ");
                    sb.append(i);
                    sb.append('+');
                    sb.append(size);
                    sb.append(" (name=");
                    sb.append(getName());
                    sb.append(" type=");
                    sb.append(create);
                    sb.append(") in ");
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline95(sb, loadParameterNames, "@ReflectJavaMember").toString());
                }
            } else {
                str = null;
            }
            if (z) {
                lastIndex = ArraysKt___ArraysKt.getLastIndex(parameterTypes);
                if (i == lastIndex) {
                    z2 = true;
                    arrayList.add(new ReflectJavaValueParameter(create, parameterAnnotations[i], str, z2));
                }
            }
            z2 = false;
            arrayList.add(new ReflectJavaValueParameter(create, parameterAnnotations[i], str, z2));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    @NotNull
    public Visibility getVisibility() {
        return ReflectJavaModifierListOwner.DefaultImpls.getVisibility(this);
    }

    public int hashCode() {
        return mo11631getMember().hashCode();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public boolean isAbstract() {
        return ReflectJavaModifierListOwner.DefaultImpls.isAbstract(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public boolean isDeprecatedInJavaDoc() {
        return ReflectJavaAnnotationOwner.DefaultImpls.isDeprecatedInJavaDoc(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public boolean isFinal() {
        return ReflectJavaModifierListOwner.DefaultImpls.isFinal(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaModifierListOwner
    public boolean isStatic() {
        return ReflectJavaModifierListOwner.DefaultImpls.isStatic(this);
    }

    @NotNull
    public String toString() {
        return getClass().getName() + RealTimeTextConstants.COLON_SPACE + mo11631getMember();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    @NotNull
    /* renamed from: getAnnotations  reason: collision with other method in class */
    public List<ReflectJavaAnnotation> mo11639getAnnotations() {
        return ReflectJavaAnnotationOwner.DefaultImpls.getAnnotations(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember
    @NotNull
    /* renamed from: getContainingClass */
    public ReflectJavaClass mo11630getContainingClass() {
        Class<?> declaringClass = mo11631getMember().getDeclaringClass();
        Intrinsics.checkExpressionValueIsNotNull(declaringClass, "member.declaringClass");
        return new ReflectJavaClass(declaringClass);
    }
}
